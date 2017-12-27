<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="zh">

<head>
<meta name="save" content="history">
<meta name="renderer" content="webkit">
<meta content="text/html; charset=utf-8" http-equiv="Content-Type">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"> 
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="shortcut icon" href="images/favicon.ico" /> 
<title>肇庆学院教学管理系统</title>
<meta name="keywords" content="肇庆学院学生管理系统，肇庆学院">
<meta name="description" content="">

<!-- CSS -->

<link href="style/bootstrap.min.css" rel="stylesheet">
<link rel="stylesheet" href="style/login.css">

<script type="text/javascript" src="style/js/jquery-1.8.0.min.js"></script>
<script src="style/js/supersized.3.2.7.min.js"></script>

<script type="text/javascript" src="style/js/login.js"></script>
<script type="text/javascript" src="js/jquery.js"></script>
<script type="text/javascript">
	$(function(){
		$("#id").focusout(function(){
			
			var username=$("#id").val();
			alert(username);
			$.post("${pageContext.request.contextPath}/user!vali",{'username':username},
					function(msg){
				$("#validate").html(msg);
			});
		});
	})
</script>
</head>
<body background="images/1.jpg">

<div class="page-container" >
	<div class="main_box">
		<div class="login_box">
			<div class="login_logo">
				<img src="images/logo_school.png" >
			</div>
			
			<div class="login_form">
				<form action="user!login" id="login_form" method="post">
					<div class="form-group">
						<label for="j_username" class="t">账　号：</label>
						<input id="id" value="" name="user.userName" type="text" class="form-control x319 in" 
						autocomplete="off" placeholder="请输入学号或工号">
					</div>
					<div class="form-group">
						<label for="j_password" class="t">密　码：</label> 
						<input id="password" value="" name="user.password" type="password" 
						class="password form-control x319 in" placeholder="请输入密码">
					</div>
				
			

					<div class="form-group space">
						<label class="t"></label>　　　

						<input type="submit" value="登  录" class="btn btn-primary btn-lg" id="submit_btn">
						&nbsp;&nbsp;&nbsp;&nbsp;<font color="red">${error} </font><span id="validate"></span>
						
					</div>
				</form>
			</div>
		</div>
		<div class="bottom">Copyright &copy; 2018&nbsp;&nbsp;&nbsp; 
			
	</div>
</div>
</div>



</body>
</html>