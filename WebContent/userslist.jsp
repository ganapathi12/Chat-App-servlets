<%@page import="java.sql.ResultSet"%>
<%@page import="com.chat.DbConfig"%>
<%@page import="java.sql.Connection"%>
<% 
int id=Integer.parseInt(session.getAttribute("id").toString());
Connection con=DbConfig.connect();
ResultSet rs=con.createStatement().executeQuery("select * from users where id!="+id);
while(rs.next()){
%>
<a href="Chatroom.jsp?id=<%= rs.getInt("id") %>">
    <div class="content">
    <img src="<%=rs.getString("photo") %>" alt="">
    <div class="details">
        <span><%= rs.getString("fname") %> <%= rs.getString("lname") %></span>
    </div>
    </div>
    <div class="status-dot"><i class="fas fa-circle"></i></div>
</a>
       <% } 
       con.close();
       %>