<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String path = request.getContextPath();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>学生管理系统</title>

<!-- DataTables -->
<link rel="stylesheet"
	href="<%=path%>/plugins/datatables/dataTables.bootstrap4.min.css">
<!-- Tell the browser to be responsive to screen width -->
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- Font Awesome -->
<link rel="stylesheet"
	href="<%=path%>/plugins/font-awesome/css/font-awesome.min.css">
<!-- Ionicons -->
<link rel="stylesheet"
	href="https://code.ionicframework.com/ionicons/2.0.1/css/ionicons.min.css">
<!-- Theme style -->
<link rel="stylesheet" href="<%=path%>/dist/css/adminlte.css">
<!-- iCheck -->
<link rel="stylesheet" href="<%=path%>/plugins/iCheck/flat/blue.css">
<!-- Morris chart -->
<link rel="stylesheet" href="<%=path%>/plugins/morris/morris.css">
<!-- jvectormap -->
<link rel="stylesheet"
	href="<%=path%>/plugins/jvectormap/jquery-jvectormap-1.2.2.css">
<!-- Date Picker -->
<link rel="stylesheet"
	href="<%=path%>/plugins/datepicker/datepicker3.css">
<!-- Daterange picker -->
<link rel="stylesheet"
	href="<%=path%>/plugins/daterangepicker/daterangepicker-bs3.css">
<!-- bootstrap wysihtml5 - text editor -->
<link rel="stylesheet"
	href="<%=path%>/plugins/bootstrap-wysihtml5/bootstrap3-wysihtml5.min.css">
<!-- Google Font: Source Sans Pro -->
<link
	href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,400i,700"
	rel="stylesheet">

<link href="<%=path%>/table/css/font-awesome.css" rel="stylesheet" />
<!-- Google Fonts-->
<link href='https://fonts.googleapis.com/css?family=Open+Sans'
	rel='stylesheet' type='text/css' />
<!-- TABLE STYLES-->
<link href="<%=path%>/table/js/dataTables/dataTables.bootstrap.css"
	rel="stylesheet" />


<!-- jQuery -->
<script src="<%=path%>/plugins/jquery/jquery.min.js"></script>
<!-- jQuery UI 1.11.4 -->
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.min.js"></script>
<!-- Resolve conflict in jQuery UI tooltip with Bootstrap tooltip -->
<script>
	$.widget.bridge('uibutton', $.ui.button)
</script>
<!-- Bootstrap 4 -->
<script src="<%=path%>/plugins/bootstrap/js/bootstrap.bundle.min.js"></script>
<!-- Morris.js charts -->
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/raphael/2.1.0/raphael-min.js"></script>
<script src="<%=path%>/plugins/morris/morris.min.js"></script>
<!-- Sparkline -->
<script src="<%=path%>/plugins/sparkline/jquery.sparkline.min.js"></script>
<!-- jvectormap -->
<script
	src="<%=path%>/plugins/jvectormap/jquery-jvectormap-1.2.2.min.js"></script>
<script
	src="<%=path%>/plugins/jvectormap/jquery-jvectormap-world-mill-en.js"></script>
<!-- jQuery Knob Chart -->
<script src="<%=path%>/plugins/knob/jquery.knob.js"></script>
<!-- daterangepicker -->
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.10.2/moment.min.js"></script>
<script src="<%=path%>/plugins/daterangepicker/daterangepicker.js"></script>
<!-- datepicker -->
<script src="<%=path%>/plugins/datepicker/bootstrap-datepicker.js"></script>
<!-- Bootstrap WYSIHTML5 -->
<script
	src="<%=path%>/plugins/bootstrap-wysihtml5/bootstrap3-wysihtml5.all.min.js"></script>
<!-- Slimscroll -->
<script src="<%=path%>/plugins/slimScroll/jquery.slimscroll.min.js"></script>
<!-- FastClick -->
<script src="<%=path%>/plugins/fastclick/fastclick.js"></script>
<!-- AdminLTE App -->
<script src="<%=path%>/dist/js/adminlte.js"></script>
<!-- AdminLTE dashboard demo (This is only for demo purposes) -->
<script src="<%=path%>/dist/js/pages/dashboard.js"></script>
<!-- AdminLTE for demo purposes -->
<script src="<%=path%>/dist/js/demo.js"></script>
<!-- echarts -->
<script type="text/javascript" src="http://echarts.baidu.com/gallery/vendors/echarts/echarts.min.js"></script>
<script type="text/javascript" src="http://echarts.baidu.com/gallery/vendors/echarts-gl/echarts-gl.min.js"></script>
<script type="text/javascript" src="http://echarts.baidu.com/gallery/vendors/echarts-stat/ecStat.min.js"></script>
<script type="text/javascript" src="http://echarts.baidu.com/gallery/vendors/echarts/extension/dataTool.min.js"></script>
<script type="text/javascript" src="http://echarts.baidu.com/gallery/vendors/echarts/map/js/china.js"></script>
<script type="text/javascript" src="http://echarts.baidu.com/gallery/vendors/echarts/map/js/world.js"></script>
<script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=ZUONbpqGBsYGXNIYHicvbAbM"></script>
<script type="text/javascript" src="http://echarts.baidu.com/gallery/vendors/echarts/extension/bmap.min.js"></script>
<script type="text/javascript" src="http://echarts.baidu.com/gallery/vendors/simplex.js"></script>

<!-- jQuery -->
<script src="<%=path%>/plugins/jquery/jquery.min.js"></script>

</head>
<body class="hold-transition sidebar-mini" onload="window.print();">
	<div class="wrapper">
		<%@include file="/menu.jsp"%>
		<!-- Content Wrapper. Contains page content -->
		<div class="content-wrapper">
			<section class="content-header">
			<div class="container-fluid">
				<div class="row mb-2">
					<div class="col-sm-6">
						<h1>成绩数据</h1>
					</div>
					<div class="col-sm-6">
						<ol class="breadcrumb float-sm-right">
							<li class="breadcrumb-item"><a href="#">首页</a></li>
							<li class="breadcrumb-item active">所有课程成绩分布</li>
						</ol>
					</div>
				</div>
			</div>
			<!-- /.container-fluid --> </section>

			<section class="content">
			<div class="row">
				<div class="col-12">
					<div class="card">
						<div class="card-header">
							<h3 class="card-title">所有课程成绩分布情况</h3> 
						</div>
						<!-- /.card-header -->
						<div class="card-body" style="margin: 0">
							<table id="example1" class="table table-bordered table-striped">
								<thead>
									<tr>
										<!-- <th>序号</th> -->
										<th>年级</th>
										<th>成绩记录总数</th>
										<th>优秀(90-100)</th>
										<th>良好(80-89)</th>
										<th>中等(70-79)</th>
										<th>及格(60-69)</th>
										<th>不及格(0-59)</th>
										<th>平均分</th>
										<th>优秀率</th>
										<th>良好率</th>
										<th>中等率</th>
										<th>及格率</th>
										<th>不及格率</th>
									</tr>
								</thead>
								<tbody>
									<c:forEach items="${odList }" var="OverallDistribution">
										<tr>
											<td>${OverallDistribution.grade }</td>
											<td>${OverallDistribution.totalNumber }</td>
											<td>${OverallDistribution.excellentNumber }</td>
											<td>${OverallDistribution.goodNumber }</td>
											<td>${OverallDistribution.mediumNumber }</td>
											<td>${OverallDistribution.passNumber }</td>
											<td>${OverallDistribution.failNumber }</td>
											<td>${OverallDistribution.averageScore }</td>
											<td>${OverallDistribution.excellentRate }</td>
											<td>${OverallDistribution.goodRate }</td>
											<td>${OverallDistribution.mediumRate }</td>
											<td>${OverallDistribution.passRate }</td>
											<td>${OverallDistribution.failRate }</td>
										</tr>
									</c:forEach>
								</tbody>
							</table>
						</div>
						<!-- /.card-body -->
					</div>
					<!-- /.card -->
				</div>
				<!-- /.col -->
			</div>
			<!-- /.row --> 
			</section>
			
			<section class="content">
			<div class="row">
				<div class="col-12">
					<div class="card">
						<div class="card-header">
							<h3 class="card-title">必修、专业选修、通识选修课程成绩整体分布情况</h3> 
						</div>
						<!-- /.card-header -->
						<div class="card-body" style="margin: 0">
							<table id="example1" class="table table-bordered table-striped">
								<thead>
									<tr>
										<th>序号</th>
										<th>课程种类</th>
										<th>成绩记录总数</th>
										<th>优秀(90-100)</th>
										<th>良好(80-89)</th>
										<th>中等(70-79)</th>
										<th>及格(60-69)</th>
										<th>不及格(0-59)</th>
										<th>平均分</th>
										<th>优秀率</th>
										<th>良好率</th>
										<th>中等率</th>
										<th>及格率</th>
										<th>不及格率</th>
									</tr>
								</thead>
								<tbody>
									<c:forEach items="${odList1}" var="OverallDistribution">
										<tr>
											<td>${OverallDistribution.id }</td>
											<td>${OverallDistribution.courseType }</td>
											<td>${OverallDistribution.totalNumber }</td>
											<td>${OverallDistribution.excellentNumber }</td>
											<td>${OverallDistribution.goodNumber }</td>
											<td>${OverallDistribution.mediumNumber }</td>
											<td>${OverallDistribution.passNumber }</td>
											<td>${OverallDistribution.failNumber }</td>
											<td>${OverallDistribution.averageScore }</td>
											<td>${OverallDistribution.excellentRate }</td>
											<td>${OverallDistribution.goodRate }</td>
											<td>${OverallDistribution.mediumRate }</td>
											<td>${OverallDistribution.passRate }</td>
											<td>${OverallDistribution.failRate }</td>
										</tr>
									</c:forEach>
								</tbody>
							</table>
						</div>
						<!-- /.card-body -->
					</div>
					<!-- /.card -->
				</div>
				<!-- /.col -->
			</div>
			<!-- /.row --> 
			</section>
			
			<section class="content">
			<div class="row">
				<div class="col-12">
					<div class="card">
						<div class="card-header">
							<h3 class="card-title">全校必修、专业选修整体成绩分布情况</h3> 
						</div>
						<!-- /.card-header -->
						<div class="card-body" style="margin: 0">
							<table id="example1" class="table table-bordered table-striped">
								<thead>
									<tr>
										<th>年级</th>
										<th>成绩记录总数</th>
										<th>优秀(90-100)</th>
										<th>良好(80-89)</th>
										<th>中等(70-79)</th>
										<th>及格(60-69)</th>
										<th>不及格(0-59)</th>
										<th>平均分</th>
										<th>优秀率</th>
										<th>良好率</th>
										<th>中等率</th>
										<th>及格率</th>
										<th>不及格率</th>
									</tr>
								</thead>
								<tbody>
									<c:forEach items="${odList3}" var="OverallDistribution">
										<tr>
											<td>${OverallDistribution.grade }</td>
											<td>${OverallDistribution.totalNumber }</td>
											<td>${OverallDistribution.excellentNumber }</td>
											<td>${OverallDistribution.goodNumber }</td>
											<td>${OverallDistribution.mediumNumber }</td>
											<td>${OverallDistribution.passNumber }</td>
											<td>${OverallDistribution.failNumber }</td>
											<td>${OverallDistribution.averageScore }</td>
											<td>${OverallDistribution.excellentRate }</td>
											<td>${OverallDistribution.goodRate }</td>
											<td>${OverallDistribution.mediumRate }</td>
											<td>${OverallDistribution.passRate }</td>
											<td>${OverallDistribution.failRate }</td>
										</tr>
									</c:forEach>
								</tbody>
							</table>
						</div>
						<!-- /.card-body -->
					</div>
					<!-- /.card -->
				</div>
				<!-- /.col -->
			</div>
			<!-- /.row --> 
			</section>
			
			<section class="content">
			<div class="row">
				<div class="col-12">
					<div class="card">
						<div class="card-header">
							<h3 class="card-title">各院系分年级成绩平均分比较</h3> 
						</div>
						<!-- /.card-header -->
						<div class="card-body" style="margin: 0">
							<table id="example1" class="table table-bordered table-striped">
								<thead>
									<tr>
										<th rowspan="2"><center>序号</center></th>
										<th rowspan="2"><center>院系</center></th>
										<c:forEach items="${gradeList}" var="keyword" varStatus="id">	
											<th colspan="2"><center>${keyword}</center></th>
										</c:forEach>	
									</tr>
									<tr>
										<td>平均分</td>
										<td>差值</td>
										
										<td>平均分</td>
										<td>差值</td>
										
										<td>平均分</td>
										<td>差值</td>
										
										<td>平均分</td>
										<td>差值</td>
									</tr>
								</thead>
								<tbody>
									<c:forEach items="${dagascList}" var="DepartmentAllGradeAverageScoreCompare">
										<tr>
											<td>${DepartmentAllGradeAverageScoreCompare.id }</td>
											<td>${DepartmentAllGradeAverageScoreCompare.departmentName }</td>
											<td>${DepartmentAllGradeAverageScoreCompare.gradeFourAverageScore }</td>
											<td>${DepartmentAllGradeAverageScoreCompare.gradeFourDifference }</td>
											<td>${DepartmentAllGradeAverageScoreCompare.gradeThreeAverageScore }</td>
											<td>${DepartmentAllGradeAverageScoreCompare.gradeThreeDifference }</td>
											<td>${DepartmentAllGradeAverageScoreCompare.gradeTwoAverageScore }</td>
											<td>${DepartmentAllGradeAverageScoreCompare.gradeTwoDifference }</td>
											<td>${DepartmentAllGradeAverageScoreCompare.gradeOneAverageScore }</td>
											<td>${DepartmentAllGradeAverageScoreCompare.gradeOneDifference }</td>
										</tr>
									</c:forEach>
								</tbody>
							</table>
						</div>
						<!-- /.card-body -->
					</div>
					<!-- /.card -->
				</div>
				<!-- /.col -->
			</div>
			<!-- /.row --> 
			</section>
			
		</div>
	</div>


<script type="text/javascript">

</script>
	

	<!-- Bootstrap 4 -->
	<script src="<%=path%>/plugins/bootstrap/js/bootstrap.bundle.min.js"></script>
	<!-- DataTables -->
	<script src="<%=path%>/plugins/datatables/jquery.dataTables.min.js"></script>
	<script src="<%=path%>/plugins/datatables/dataTables.bootstrap4.min.js"></script>
	<!-- SlimScroll -->
	<script src="<%=path%>/plugins/slimScroll/jquery.slimscroll.min.js"></script>
	<!-- FastClick -->
	<script src="<%=path%>/plugins/fastclick/fastclick.js"></script>
	<!-- AdminLTE App -->
	<script src="<%=path%>/dist/js/adminlte.min.js"></script>
	<!-- AdminLTE for demo purposes -->
	<script src="<%=path%>/dist/js/demo.js"></script>
	<!-- page script -->
	<script>
		$(function() {
			$("#example1").DataTable();
			$('#example2').DataTable({
				"paging" : true,
				"lengthChange" : false,
				"searching" : false,
				"ordering" : true,
				"info" : true,
				"autoWidth" : false
			});
		});
	</script>
	
	<script src="<%=path%>/table/js/jquery-1.10.2.js"></script>
	<!-- Bootstrap Js -->
	<script src="<%=path%>/table/js/bootstrap.min.js"></script>
	<!-- Metis Menu Js -->
	<script src="<%=path%>/table/js/jquery.metisMenu.js"></script>
	<!-- DATA TABLE SCRIPTS -->
	<script src="<%=path%>/table/js/dataTables/jquery.dataTables.js"></script>
	<script src="<%=path%>/table/js/dataTables/dataTables.bootstrap.js"></script>
	<script>
		$(document).ready(function() {
			$('#dataTables-example').dataTable();
		});
	</script>
	<!-- Custom Js -->
	<script src="<%=path%>/table/js/custom-scripts.js"></script>
	
</body>
</html>