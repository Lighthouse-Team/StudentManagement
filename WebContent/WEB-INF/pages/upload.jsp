<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>上传文件</title>
<meta name="viewport" content="width=device-width, initial-scale=1">

<!-- Font Awesome -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/font-awesome/4.4.0/css/font-awesome.min.css">
<!-- Ionicons -->
<link rel="stylesheet"
	href="https://code.ionicframework.com/ionicons/2.0.1/css/ionicons.min.css">
<!-- Theme style -->
<link rel="stylesheet" href="<%=path%>/dist/css/adminlte.min.css">
<!-- iCheck -->
<link rel="stylesheet" href="<%=path%>/plugins/iCheck/square/blue.css">
<!-- Google Font: Source Sans Pro -->
<link
	href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,400i,700"
	rel="stylesheet">
</head>
<body class="hold-transition login-page">
	<div class="login-box">
		<div class="login-logo">
			<!-- <a href="#"><b>上传文件</b></a> -->
		</div>
		<!-- /.login-logo -->
		<div class="card">
			<div class="card-body login-card-body">
				<p class="login-box-msg">导入成绩到数据库</p>

				<form action="fileUpload" method="post" enctype="multipart/form-data">
					<div class="form-group has-feedback">
						<input type="file" class="form-control" placeholder="Email" name="files"/>
						<input type="file" class="form-control" placeholder="Email" name="files"/>
						<input type="file" class="form-control" placeholder="Email" name="files"/>
						<input type="file" class="form-control" placeholder="Email" name="files"/>
					</div>
					<div class="col">
						<!-- /.col -->
						<div class="col-12">
							<input type="submit" value="确认上传" class="btn btn-primary btn-block btn-flat"/>
						</div>
						<!-- /.col -->
					</div>
				</form>
				
				<form action="retToMain" method="post" enctype="multipart/form-data">
					<div class="form-group has-feedback">
					</div>
					<div class="col">
						<!-- /.col -->
						<div class="col-12">
							<button type="submit" class="btn btn-primary btn-block btn-flat">返回</button>
						</div>
						<!-- /.col -->
					</div>
				</form>
				

				<!-- <div class="social-auth-links text-center mb-3">
					<p>- OR -</p>
					<a href="#" class="btn btn-block btn-primary"> <i
						class="fa fa-facebook mr-2"></i> Sign in using Facebook
					</a> <a href="#" class="btn btn-block btn-danger"> <i
						class="fa fa-google-plus mr-2"></i> Sign in using Google+
					</a>
				</div> -->
				<!-- /.social-auth-links -->

				<!-- <p class="mb-1">
					<a href="#">I forgot my password</a>
				</p>
				<p class="mb-0">
					<a href="register.html" class="text-center">Register a new
						membership</a>
				</p> -->
			</div>
			<!-- /.login-card-body -->
		</div>
	</div>
	<!-- /.login-box -->

	<!-- jQuery -->
	<script src="<%=path%>/plugins/jquery/jquery.min.js"></script>
	<!-- Bootstrap 4 -->
	<script src="<%=path%>/plugins/bootstrap/js/bootstrap.bundle.min.js"></script>
	<!-- iCheck -->
	<script src="<%=path%>/plugins/iCheck/icheck.min.js"></script>
	<script>
		$(function() {
			$('input').iCheck({
				checkboxClass : 'icheckbox_square-blue',
				radioClass : 'iradio_square-blue',
				increaseArea : '20%' // optional
			})
		})
	</script>
</body>
</html>