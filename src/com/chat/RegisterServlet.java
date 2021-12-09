package com.chat;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;


@WebServlet("/register")
@MultipartConfig
public class RegisterServlet   extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String userid=req.getParameter("userid");
        String pwd=req.getParameter("pwd");
        String fname=req.getParameter("fname");
        String lname=req.getParameter("lname");
        Part photo=req.getPart("photo");
        HttpSession session=req.getSession();
        ServletContext ctx=req.getServletContext();
        try{
        	
        	String filename="user"+DbConfig.findCount("users")+DbConfig.getExtension(photo.getSubmittedFileName()).get();
        	String path=ctx.getRealPath("/photos");
        	Files.copy(photo.getInputStream(),Paths.get(path,filename) , StandardCopyOption.REPLACE_EXISTING);
        	
            Connection con=DbConfig.connect();
            PreparedStatement ps=con.prepareStatement("INSERT INTO users(userid,fname,lname,pwd,photo,status) VALUES(?,?,?,?,?,?)");
            ps.setString(1,userid);
            ps.setString(2,fname);
            ps.setString(3,lname);
            ps.setString(4,pwd);
            ps.setString(5,"photos/"+filename);
            ps.setString(6,"Not Active");
            
            ps.executeUpdate();  
            con.close();
            session.setAttribute("msg", "User Registered Successfully..!");
            resp.sendRedirect("index.jsp");            
        }
        catch(Exception ex){
            System.err.println("Error "+ex.getMessage());
        }
    }
    
}
