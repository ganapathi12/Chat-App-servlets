package com.chat;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/logout")
public class LogoutServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session=request.getSession();
        try {
	        DbConfig.updateStatus(Integer.parseInt(session.getAttribute("id").toString()), "Not Available");
	        session.invalidate();
	        response.sendRedirect("index.jsp");
        }catch(Exception ex) {
        	System.err.println(ex);
        }
    }   

}
