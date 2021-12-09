<%@page import="com.chat.DbConfig"%>
<%@page import="java.sql.*"%>
<%
String fromid=session.getAttribute("id").toString();
String id=request.getParameter("id");
Connection con=DbConfig.connect();
ResultSet rs2=con.createStatement()
.executeQuery("select * from messages where (fromid="+fromid+" and toid="+id+") or (fromid="+id+" and toid="+fromid+")");
while(rs2.next()){
	if(rs2.getString("toid").equals(fromid)){
%>
	<div class="chat incoming">
	<div class="details">
	    <p><%= rs2.getString("msg") %></p>
	</div>
	</div>
	<% } else { %>
	<div class="chat outgoing">
	<div class="details">
	    <p><%= rs2.getString("msg") %></p>
	</div>
	</div>
<% } }%>