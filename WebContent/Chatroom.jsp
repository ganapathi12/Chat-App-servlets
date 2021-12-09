<%@page import="com.chat.DbConfig"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Connection"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Chatroom</title>
<link rel="stylesheet" href="css/bootstrap.min.css">
<link rel="stylesheet" href="css/style.css">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.2/css/all.min.css">
<script src="js/jquery-3.4.1.js"></script>
</head>
<body>
<div class="container-fluid">
<div class="row">
	<div class="col-sm-4">
	<div class="wrapper">
    <section class="users">
      <header>
        <a href="Profile.jsp">
        <div class="content">
           <img src="${sessionScope.photo }" alt="">
          <div class="details">
            <span>${sessionScope.uname }</span>
            <p>Active now</p>
          </div>
        </div>
          </a>
        <a href="logout" class="logout">Logout</a>
      </header>
      <div class="search">
        <span class="text">Select an user to start chat</span>
        <input type="text" placeholder="Enter name to search..." class="show">
        <button class="active"><i class="fas fa-search"></i></button>
      </div>
      <div class="users-list" id="users-list">
      
       </div>
    </section>
  </div>
	</div>
	<div class="col-sm-8">
	<div class="wrapper">
    <section class="chat-area" id="chat-area">
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
<form onSubmit="sendmessage(this)" class="typing-area">
  <input type="hidden" class="incoming_id" name="toid" value="${param.id }">
  <input type="text" name="message" class="input-field" placeholder="Type a message here..." autocomplete="off">
  <button><i class="fab fa-telegram-plane"></i></button>
</form>
<% } %>
    </section>
  </div>
	</div>
</div>
</div>
</body>
<script>
function sendmessage(f){
	event.preventDefault();
	console.log(f);
	$.post("sendmsg",{"message":f.message.value,"toid":f.toid.value},function(resp){
		$(".chat-box").load("Chatbox.jsp?id=+<%= id %>");
	});
	$(".input-field").val("");
	$(".input-field").focus();
}
setInterval(function(){
	$(".chat-box").load("Chatbox.jsp?id=+<%= id %>");
	$("#users-list").load("userslist.jsp");	
},2000);
</script>
</script>
</html>