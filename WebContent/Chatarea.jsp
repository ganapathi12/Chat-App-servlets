<%@page import="java.sql.ResultSet"%>
<%@page import="com.chat.DbConfig"%>
<%@page import="java.sql.Connection"%>
<%
String fromid=session.getAttribute("id").toString();
String id=request.getParameter("id");
Connection con=DbConfig.connect();
ResultSet rs=con.createStatement().executeQuery("select * from users where id="+id);
if(rs.next()){
%>
<header>
  <img src="<%=rs.getString("photo") %>" alt="">
  <div class="details">
    <span><%= rs.getString("fname") %> <%= rs.getString("lname") %></span>
    <p><%= rs.getString("status") %></p>
  </div>
</header>
<div class="chat-box">

</div>
<form action="sendmsg" method="post" class="typing-area">
  <input type="hidden" class="incoming_id" name="toid" value="${param.id }">
  <input type="text" name="message" class="input-field" placeholder="Type a message here..." autocomplete="off">
  <button><i class="fab fa-telegram-plane"></i></button>
</form>
<% } %>
<script>
setInterval(function(){
	$(".chat-box").load("Chatbox.jsp?id="+<%= id %>);
},2000);
</script>