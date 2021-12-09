package com.chat;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/validate")
public class ValidateServlet  extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String userid=req.getParameter("userid");
        String pwd=req.getParameter("pwd");
        HttpSession session=req.getSession();
        try{
            Connection con=DbConfig.connect();
            PreparedStatement ps=con.prepareStatement("SELECT * FROM users WHERE userid=? and pwd=?");
            ps.setString(1,userid);
            ps.setString(2,pwd);
            ResultSet rs=ps.executeQuery();
            if(rs.next()){
                session.setAttribute("userid", userid);
                session.setAttribute("id", rs.getInt("id"));
                session.setAttribute("photo", rs.getString("photo"));
                session.setAttribute("uname", rs.getString("fname")+" "+rs.getString("lname"));
                DbConfig.updateStatus(rs.getInt("id"), "Active Now");
                con.close();
                resp.sendRedirect("Chatroom.jsp");
            }else{
                con.close();
                session.setAttribute("msg", "Invalid username or password");
                resp.sendRedirect("index.jsp");
            }
        }
        catch(Exception ex){
            resp.getWriter().println("Error "+ex.getMessage());
        }
    }
    
    
    
}
