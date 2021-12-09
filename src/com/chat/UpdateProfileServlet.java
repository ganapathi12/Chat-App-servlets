package com.chat;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

@WebServlet("/updateprofile")
@MultipartConfig
public class UpdateProfileServlet extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String pwd=req.getParameter("pwd");
        String fname=req.getParameter("fname");
        String lname=req.getParameter("lname");
        Part photo=req.getPart("photo");
        HttpSession session=req.getSession();
        ServletContext ctx=req.getServletContext();
        String filename=session.getAttribute("photo").toString();
        try{
        	if(photo.getSubmittedFileName()!=null) {
        		String path=ctx.getRealPath("/");
        		Files.copy(photo.getInputStream(),Paths.get(path,filename) , StandardCopyOption.REPLACE_EXISTING);
        	}
            Connection con=DbConfig.connect();
            PreparedStatement ps=con.prepareStatement("update users set fname=?,lname=?,pwd=? where id=?");
            ps.setString(1,fname);
            ps.setString(2,lname);
            ps.setString(3,pwd);
            ps.setString(4,session.getAttribute("id").toString());
            
            ps.executeUpdate();  
            con.close();
            session.setAttribute("uname", fname+" "+lname);
            session.setAttribute("msg", "User Registered Successfully..!");
            resp.sendRedirect("Chatroom.jsp");            
        }
        catch(Exception ex){
            System.err.println("Error "+ex.getMessage());
        }
	}
}
