package com.chat;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/sendmsg")
public class SendMessageServlet extends HttpServlet {
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session=req.getSession();
		try {
			String msg=req.getParameter("message");
			String to=req.getParameter("toid");
			String from=session.getAttribute("id").toString();
			Connection con=DbConfig.connect();
			PreparedStatement ps=con.prepareStatement("insert into messages(msg,fromid,toid) values(?,?,?)");
			ps.setString(1, msg);
			ps.setString(2, from);
			ps.setString(3, to);
			ps.executeUpdate();
			resp.sendRedirect("Chatroom.jsp?id="+to);
		}
		catch(Exception ex) {
			
		}
	}

}
