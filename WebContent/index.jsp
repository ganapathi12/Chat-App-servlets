<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" href="css/bootstrap.min.css">
</head>
<body style="background-color:#f7f7f7;">
<div class="container">
<div class="row">
	<div class="col-sm-6 mx-auto">
		<div class="card shadow" style="margin-top:30%;">
			<div class="card-header text-center">
				<h5>Chat Application - Login Page</h5>
			</div>
			<div class="card-body">
				<form method="post" action="validate">
					<div class="form-row form-group">
						<label class="col-sm-4 col-form-label">User Id</label>
						<div class="col-sm-8">
							<input type="text" name="userid" class="form-control">
						</div>
					</div>
					<div class="form-row form-group">
						<label class="col-sm-4 col-form-label">Password</label>
						<div class="col-sm-8">
							<input type="password" name="pwd" class="form-control">
						</div>
					</div>
					<button class="btn btn-primary float-right px-4">Login</button>
				</form>
				<div class="clearfix"></div>
				<a href="register.jsp">Not regsitered</a>
			</div>
		</div>
	</div>
</div>
</div>
</body>
</html>