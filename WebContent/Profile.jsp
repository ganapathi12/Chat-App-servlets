<%@page import="com.chat.DbConfig"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Connection"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Profile Page</title>
<link rel="stylesheet" href="css/bootstrap.min.css">
<link rel="stylesheet" href="css/style.css">
<style>
body{
display: flex;
    align-items: center;
    justify-content: center;
    }
</style>
</head>
<body>

<div class="wrapper" style="max-width:450px;">
    <section class="users">
      <header>
        <div class="content">
          <img src="${sessionScope.photo }" alt="">
          <div class="details">
            <span>${sessionScope.uname }</span>
            <p>Active now</p>
          </div>
        </div>
        <a href="Chatroom.jsp" class="logout">Back</a>
      </header>
      <div class="users-list">
      <%
      String id=session.getAttribute("id").toString();
Connection con=DbConfig.connect();
ResultSet rs=con.createStatement().executeQuery("select * from users where id="+id);
if(rs.next()){
%>
      	<form method="post" enctype="multipart/form-data" action="updateprofile" class="mt-3">
      		<div class="form-group">
      			<label>First Name</label>
      			<input type="text" name="fname" value="<%= rs.getString("fname") %>" class="form-control">
      		</div>
      		<div class="form-group">
      			<label>Last Name</label>
      			<input type="text" name="lname" value="<%= rs.getString("lname") %>" class="form-control">
      		</div>
      		<div class="form-group">
      			<label>Password</label>
      			<input type="password" name="pwd" value="<%= rs.getString("pwd") %>" class="form-control">
      		</div>
      		<div class="form-group">
      			<label>Select Photo</label>
      			<input type="file" name="photo" class="form-control" accept=".jpg,.png">
      		</div>
      		<button class="btn btn-dark float-right">Update Profile</button>
      	</form>
      	<% } %>
      </div>
    </section>
  </div>
</body>
</html>