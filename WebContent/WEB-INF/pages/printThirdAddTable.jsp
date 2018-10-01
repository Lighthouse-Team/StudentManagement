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
<title>&nbsp;&nbsp;</title>

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
<script src="<%=path%>/assets/js/jquery-1.7.2.min.js"></script>
<script src="<%=path%>/assets/js/jquery.blockUI.js"></script>
<script type="text/javascript">
	
	$(function() {   
		/* 表格标题处理 */
 		var year1 = "${year}";
		var yearSelected = parseInt(year1.substring(0,4));
		
		var gradeList = new Array();
		for( var i=0; i<4; i++){
			gradeList[i] = (yearSelected - 3 + i).toString();
		}
		
		var term1 = "${term}";
		if(term1 == "1"){
			
 			var courseTitle = document.getElementById("courseTitle");
			courseTitle.innerHTML = "附表9&nbsp;&nbsp;大学英语（三）各班级优秀率、不及格率情况";
			var courseTitle1 = document.getElementById("courseTitle1");
			courseTitle1.innerHTML = "附表10&nbsp;&nbsp;大学物理下A各班级优秀率、不及格率情况";
			var courseTitle2 = document.getElementById("courseTitle2");
			courseTitle2.innerHTML = "附表11&nbsp;&nbsp;大学物理下B各班级优秀率、不及格率情况";
			var courseTitle3 = document.getElementById("courseTitle3");
			courseTitle3.innerHTML = "附表12&nbsp;&nbsp;大学物理实验（二）各班级优秀率、不及格率情况";
			var courseTitle4 = document.getElementById("courseTitle4");
			courseTitle4.innerHTML = "附表13&nbsp;&nbsp;复变函数与积分变换各班级优秀率、不及格率情况";
			var courseTitle5 = document.getElementById("courseTitle5");
			courseTitle5.innerHTML = "附表14&nbsp;&nbsp;大学英语（一）各班级优秀率、不及格率情况";
			var courseTitle6 = document.getElementById("courseTitle6");
			courseTitle6.innerHTML = "附表15&nbsp;&nbsp;大学计算机基础A各班级优秀率、不及格率情况";
			var courseTitle7 = document.getElementById("courseTitle7");
			courseTitle7.innerHTML = "附表16&nbsp;&nbsp;普通化学各班级优秀率、不及格率情况";
			var courseTitle8 = document.getElementById("courseTitle8");
			courseTitle8.innerHTML = "附表17&nbsp;&nbsp;线性代数与解析几何A各班级优秀率、不及格率情况";
			var courseTitle9 = document.getElementById("courseTitle9");
			courseTitle9.innerHTML = "附表18&nbsp;&nbsp;微积分A（一）各班级优秀率、不及格率情况"; 
			
 			var secondTermAddCourse1 = document.getElementById("secondTermAddCourse1");
			secondTermAddCourse1.style.display = "none";
			
			var secondTermAddCourse2 = document.getElementById("secondTermAddCourse2");
			secondTermAddCourse2.style.display = "none";
			
			var secondTermAddCourse3 = document.getElementById("secondTermAddCourse3");
			secondTermAddCourse3.style.display = "none"; 
			
			var courseTitle10 = document.getElementById("courseTitle10");
			courseTitle10.style.display = "none";
			
			var courseTitle11 = document.getElementById("courseTitle11");
			courseTitle11.style.display = "none";
			
			var courseTitle12 = document.getElementById("courseTitle12");
			courseTitle12.style.display = "none";
		}
		else if(term1 == "2"){
			
 			var courseTitle = document.getElementById("courseTitle");
			courseTitle.innerHTML = "附表9&nbsp;&nbsp;大学英语（六）各班级优秀率、不及格率情况";
			var courseTitle1 = document.getElementById("courseTitle1");
			courseTitle1.innerHTML = "附表10&nbsp;&nbsp;电子电路综合实验各班级优秀率、不及格率情况";
			var courseTitle2 = document.getElementById("courseTitle2");
			courseTitle2.innerHTML = "附表11&nbsp;&nbsp;管理学B各班级优秀率、不及格率情况";
			var courseTitle3 = document.getElementById("courseTitle3");
			courseTitle3.innerHTML = "附表12&nbsp;&nbsp;工程实践A各班级优秀率、不及格率情况";
			var courseTitle4 = document.getElementById("courseTitle4");
			courseTitle4.innerHTML = "附表13&nbsp;&nbsp;机械设计基础B各班级优秀率、不及格率情况";
			var courseTitle5 = document.getElementById("courseTitle5");
			courseTitle5.innerHTML = "附表14&nbsp;&nbsp;大学英语（四）各班级优秀率、不及格率情况";
			var courseTitle6 = document.getElementById("courseTitle6");
			courseTitle6.innerHTML = "附表15&nbsp;&nbsp;模拟电子技术各班级优秀率、不及格率情况";
			var courseTitle7 = document.getElementById("courseTitle7");
			courseTitle7.innerHTML = "附表16&nbsp;&nbsp;大学英语（二）各班级优秀率、不及格率情况";
			var courseTitle8 = document.getElementById("courseTitle8");
			courseTitle8.innerHTML = "附表17&nbsp;&nbsp;程序设计基础各班级优秀率、不及格率情况";
			var courseTitle9 = document.getElementById("courseTitle9");
			courseTitle9.innerHTML = "附表18&nbsp;&nbsp;大学物理上各班级优秀率、不及格率情况";
			var courseTitle10 = document.getElementById("courseTitle10");
			courseTitle10.innerHTML = "附表19&nbsp;&nbsp;概率论与数理统计各班级优秀率、不及格率情况";
			var courseTitle11 = document.getElementById("courseTitle11");
			courseTitle11.innerHTML = "附表20&nbsp;&nbsp;微积分A（二）各班级优秀率、不及格率情况";
			var courseTitle12 = document.getElementById("courseTitle12");
			courseTitle12.innerHTML = "附表21&nbsp;&nbsp;工程图学基础各班级优秀率、不及格率情况"; 
		}
  		
	    /* 处理表格分页搜索底部信息显示搜索方法栏 */
		$('#example1').dataTable({
	        bFilter: false,    //去掉搜索框
	        bLengthChange: false,   //去掉每页显示多少条数据方法
	        "paging" : false, //去掉底部分页框
	        "info": false, //去掉表格底部信息
	        "bSort": false, //排序功能
	    });

	});
	
	$(function() {
		$('#getPic').click(function(){
			window.print();
		});
	});

</script>

<style media=print type="text/css">
 .noprint{display:none}
</style>

</head>
<body class="hold-transition sidebar-mini" >
	<div class="wrapper">
		<%@include file="/menu.jsp"%>
		<!-- Content Wrapper. Contains page content -->
		<div class="content-wrapper">
			<div class="container-fluid noprint">
				<div class="row mb-2">
					<div class="col-sm-6">
						<button id="getPic" class="btn btn-info float-left" >打印或保存为PDF</button>
					</div>
					<div class="col-sm-6">
						<ol class="breadcrumb float-sm-right">
							<li class="breadcrumb-item"><a href="#">首页</a></li>
							<li class="breadcrumb-item active">打印页面</li>
						</ol>
					</div>
				</div>
			</div>

			<h3 style="text-align:center" class="card-title" id = "courseTitle"></h3> 
			<div class="row">
				<div class="col-12">
						<!-- /.card-header --> 
						<div class="card-body" style="margin: 0">
							<table id="example1" class="table table-bordered table-striped">
								<thead>
									<tr>
										<th><center>序号</center></th>
										<th><center>班级</center></th>
										<th><center>考试人数</center></th>
										<th><center>优秀率</center></th>
										<th><center>不及格率</center></th>
									</tr>
								</thead>
								<tbody>
									<c:forEach items="${bccdList}" var="BasicCourseClassDistribution">
										<tr>
											<td>${BasicCourseClassDistribution.id }</td>
											<td>${BasicCourseClassDistribution.classNumber }</td>
											<td>${BasicCourseClassDistribution.totalNumber }</td>
											<td>${BasicCourseClassDistribution.excellentRate }</td>
											<td>${BasicCourseClassDistribution.failRate }</td>
										</tr>
									</c:forEach>
								</tbody>
							</table>
						</div>
						<!-- /.card-body -->
					<!-- /.card -->
				</div>
				<!-- /.col -->
			</div>
			<!-- /.row --> 
			
			<h3 style="text-align:center" class="card-title" id = "courseTitle1"></h3> 
			<div class="row">
				<div class="col-12">
						<!-- /.card-header --> 
						<div class="card-body" style="margin: 0">
							<table id="example1" class="table table-bordered table-striped">
								<thead>
									<tr>
										<th><center>序号</center></th>
										<th><center>班级</center></th>
										<th><center>考试人数</center></th>
										<th><center>优秀率</center></th>
										<th><center>不及格率</center></th>
									</tr>
								</thead>
								<tbody>
									<c:forEach items="${bccdList1}" var="BasicCourseClassDistribution">
										<tr>
											<td>${BasicCourseClassDistribution.id }</td>
											<td>${BasicCourseClassDistribution.classNumber }</td>
											<td>${BasicCourseClassDistribution.totalNumber }</td>
											<td>${BasicCourseClassDistribution.excellentRate }</td>
											<td>${BasicCourseClassDistribution.failRate }</td>
										</tr>
									</c:forEach>
								</tbody>
							</table>
						</div>
						<!-- /.card-body -->
					<!-- /.card -->
				</div>
				<!-- /.col -->
			</div>
			<!-- /.row --> 
			
			<h3 style="text-align:center" class="card-title" id = "courseTitle2"></h3> 
			<div class="row">
				<div class="col-12">
						<!-- /.card-header --> 
						<div class="card-body" style="margin: 0">
							<table id="example1" class="table table-bordered table-striped">
								<thead>
									<tr>
										<th><center>序号</center></th>
										<th><center>班级</center></th>
										<th><center>考试人数</center></th>
										<th><center>优秀率</center></th>
										<th><center>不及格率</center></th>
									</tr>
								</thead>
								<tbody>
									<c:forEach items="${bccdList2}" var="BasicCourseClassDistribution">
										<tr>
											<td>${BasicCourseClassDistribution.id }</td>
											<td>${BasicCourseClassDistribution.classNumber }</td>
											<td>${BasicCourseClassDistribution.totalNumber }</td>
											<td>${BasicCourseClassDistribution.excellentRate }</td>
											<td>${BasicCourseClassDistribution.failRate }</td>
										</tr>
									</c:forEach>
								</tbody>
							</table>
						</div>
						<!-- /.card-body -->
					<!-- /.card -->
				</div>
				<!-- /.col -->
			</div>
			<!-- /.row --> 
			
			<h3 style="text-align:center" class="card-title" id = "courseTitle3"></h3> 
			<div class="row">
				<div class="col-12">
						<!-- /.card-header --> 
						<div class="card-body" style="margin: 0">
							<table id="example1" class="table table-bordered table-striped">
								<thead>
									<tr>
										<th><center>序号</center></th>
										<th><center>班级</center></th>
										<th><center>考试人数</center></th>
										<th><center>优秀率</center></th>
										<th><center>不及格率</center></th>
									</tr>
								</thead>
								<tbody>
									<c:forEach items="${bccdList3}" var="BasicCourseClassDistribution">
										<tr>
											<td>${BasicCourseClassDistribution.id }</td>
											<td>${BasicCourseClassDistribution.classNumber }</td>
											<td>${BasicCourseClassDistribution.totalNumber }</td>
											<td>${BasicCourseClassDistribution.excellentRate }</td>
											<td>${BasicCourseClassDistribution.failRate }</td>
										</tr>
									</c:forEach>
								</tbody>
							</table>
						</div>
						<!-- /.card-body -->
					<!-- /.card -->
				</div>
				<!-- /.col -->
			</div>
			<!-- /.row --> 
			
			<h3  style="text-align:center" class="card-title" id = "courseTitle4"></h3> 
			<div class="row">
				<div class="col-12">
						<!-- /.card-header --> 
						<div class="card-body" style="margin: 0">
							<table id="example1" class="table table-bordered table-striped">
								<thead>
									<tr>
										<th><center>序号</center></th>
										<th><center>班级</center></th>
										<th><center>考试人数</center></th>
										<th><center>优秀率</center></th>
										<th><center>不及格率</center></th>
									</tr>
								</thead>
								<tbody>
									<c:forEach items="${bccdList4}" var="BasicCourseClassDistribution">
										<tr>
											<td>${BasicCourseClassDistribution.id }</td>
											<td>${BasicCourseClassDistribution.classNumber }</td>
											<td>${BasicCourseClassDistribution.totalNumber }</td>
											<td>${BasicCourseClassDistribution.excellentRate }</td>
											<td>${BasicCourseClassDistribution.failRate }</td>
										</tr>
									</c:forEach>
								</tbody>
							</table>
						</div>
						<!-- /.card-body -->
					<!-- /.card -->
				</div>
				<!-- /.col -->
			</div>
			<!-- /.row --> 
			
			<h3 style="text-align:center" class="card-title" id = "courseTitle5"></h3> 
			<div class="row">
				<div class="col-12">
						<!-- /.card-header --> 
						<div class="card-body" style="margin: 0">
							<table id="example1" class="table table-bordered table-striped">
								<thead>
									<tr>
										<th><center>序号</center></th>
										<th><center>班级</center></th>
										<th><center>考试人数</center></th>
										<th><center>优秀率</center></th>
										<th><center>不及格率</center></th>
									</tr>
								</thead>
								<tbody>
									<c:forEach items="${bccdList5}" var="BasicCourseClassDistribution">
										<tr>
											<td>${BasicCourseClassDistribution.id }</td>
											<td>${BasicCourseClassDistribution.classNumber }</td>
											<td>${BasicCourseClassDistribution.totalNumber }</td>
											<td>${BasicCourseClassDistribution.excellentRate }</td>
											<td>${BasicCourseClassDistribution.failRate }</td>
										</tr>
									</c:forEach>
								</tbody>
							</table>
						</div>
						<!-- /.card-body -->
					<!-- /.card -->
				</div>
				<!-- /.col -->
			</div>
			<!-- /.row --> 
			
			<h3 style="text-align:center" class="card-title" id = "courseTitle6"></h3> 
			<div class="row">
				<div class="col-12">
						<!-- /.card-header --> 
						<div class="card-body" style="margin: 0">
							<table id="example1" class="table table-bordered table-striped">
								<thead>
									<tr>
										<th><center>序号</center></th>
										<th><center>班级</center></th>
										<th><center>考试人数</center></th>
										<th><center>优秀率</center></th>
										<th><center>不及格率</center></th>
									</tr>
								</thead>
								<tbody>
									<c:forEach items="${bccdList6}" var="BasicCourseClassDistribution">
										<tr>
											<td>${BasicCourseClassDistribution.id }</td>
											<td>${BasicCourseClassDistribution.classNumber }</td>
											<td>${BasicCourseClassDistribution.totalNumber }</td>
											<td>${BasicCourseClassDistribution.excellentRate }</td>
											<td>${BasicCourseClassDistribution.failRate }</td>
										</tr>
									</c:forEach>
								</tbody>
							</table>
						</div>
						<!-- /.card-body -->
					<!-- /.card -->
				</div>
				<!-- /.col -->
			</div>
			<!-- /.row --> 
			
			<h3 style="text-align:center" class="card-title" id = "courseTitle7"></h3> 
			<div class="row">
				<div class="col-12">
						<!-- /.card-header --> 
						<div class="card-body" style="margin: 0">
							<table id="example1" class="table table-bordered table-striped">
								<thead>
									<tr>
										<th><center>序号</center></th>
										<th><center>班级</center></th>
										<th><center>考试人数</center></th>
										<th><center>优秀率</center></th>
										<th><center>不及格率</center></th>
									</tr>
								</thead>
								<tbody>
									<c:forEach items="${bccdList7}" var="BasicCourseClassDistribution">
										<tr>
											<td>${BasicCourseClassDistribution.id }</td>
											<td>${BasicCourseClassDistribution.classNumber }</td>
											<td>${BasicCourseClassDistribution.totalNumber }</td>
											<td>${BasicCourseClassDistribution.excellentRate }</td>
											<td>${BasicCourseClassDistribution.failRate }</td>
										</tr>
									</c:forEach>
								</tbody>
							</table>
						</div>
						<!-- /.card-body -->
					<!-- /.card -->
				</div>
				<!-- /.col -->
			</div>
			<!-- /.row --> 
			
			<h3 style="text-align:center" class="card-title" id = "courseTitle8"></h3> 
			<div class="row">
				<div class="col-12">
						<!-- /.card-header --> 
						<div class="card-body" style="margin: 0">
							<table id="example1" class="table table-bordered table-striped">
								<thead>
									<tr>
										<th><center>序号</center></th>
										<th><center>班级</center></th>
										<th><center>考试人数</center></th>
										<th><center>优秀率</center></th>
										<th><center>不及格率</center></th>
									</tr>
								</thead>
								<tbody>
									<c:forEach items="${bccdList8}" var="BasicCourseClassDistribution">
										<tr>
											<td>${BasicCourseClassDistribution.id }</td>
											<td>${BasicCourseClassDistribution.classNumber }</td>
											<td>${BasicCourseClassDistribution.totalNumber }</td>
											<td>${BasicCourseClassDistribution.excellentRate }</td>
											<td>${BasicCourseClassDistribution.failRate }</td>
										</tr>
									</c:forEach>
								</tbody>
							</table>
						</div>
						<!-- /.card-body -->
					<!-- /.card -->
				</div>
				<!-- /.col -->
			</div>
			<!-- /.row --> 
			
			<h3 style="text-align:center" class="card-title" id = "courseTitle9"></h3> 
			<div class="row">
				<div class="col-12">
						<!-- /.card-header --> 
						<div class="card-body" style="margin: 0">
							<table id="example1" class="table table-bordered table-striped">
								<thead>
									<tr>
										<th><center>序号</center></th>
										<th><center>班级</center></th>
										<th><center>考试人数</center></th>
										<th><center>优秀率</center></th>
										<th><center>不及格率</center></th>
									</tr>
								</thead>
								<tbody>
									<c:forEach items="${bccdList9}" var="BasicCourseClassDistribution">
										<tr>
											<td>${BasicCourseClassDistribution.id }</td>
											<td>${BasicCourseClassDistribution.classNumber }</td>
											<td>${BasicCourseClassDistribution.totalNumber }</td>
											<td>${BasicCourseClassDistribution.excellentRate }</td>
											<td>${BasicCourseClassDistribution.failRate }</td>
										</tr>
									</c:forEach>
								</tbody>
							</table>
						</div>
						<!-- /.card-body -->
					<!-- /.card -->
				</div>
				<!-- /.col -->
			</div>
			<!-- /.row --> 
			
			<h3 style="text-align:center" class="card-title" id = "courseTitle10"></h3> 
			<div id = "secondTermAddCourse1" class="row">
				<div class="col-12">
						<!-- /.card-header --> 
						<div class="card-body" style="margin: 0">
							<table id="example1" class="table table-bordered table-striped">
								<thead>
									<tr>
										<th><center>序号</center></th>
										<th><center>班级</center></th>
										<th><center>考试人数</center></th>
										<th><center>优秀率</center></th>
										<th><center>不及格率</center></th>
									</tr>
								</thead>
								<tbody>
									<c:forEach items="${bccdList10}" var="BasicCourseClassDistribution">
										<tr>
											<td>${BasicCourseClassDistribution.id }</td>
											<td>${BasicCourseClassDistribution.classNumber }</td>
											<td>${BasicCourseClassDistribution.totalNumber }</td>
											<td>${BasicCourseClassDistribution.excellentRate }</td>
											<td>${BasicCourseClassDistribution.failRate }</td>
										</tr>
									</c:forEach>
								</tbody>
							</table>
						</div>
						<!-- /.card-body -->
					<!-- /.card -->
				</div>
				<!-- /.col -->
			</div>
			<!-- /.row --> 
			
			<h3 style="text-align:center" class="card-title" id = "courseTitle11"></h3> 
			<div id = "secondTermAddCourse2" class="row">
				<div class="col-12">
						<!-- /.card-header --> 
						<div class="card-body" style="margin: 0">
							<table id="example1" class="table table-bordered table-striped">
								<thead>
									<tr>
										<th><center>序号</center></th>
										<th><center>班级</center></th>
										<th><center>考试人数</center></th>
										<th><center>优秀率</center></th>
										<th><center>不及格率</center></th>
									</tr>
								</thead>
								<tbody>
									<c:forEach items="${bccdList11}" var="BasicCourseClassDistribution">
										<tr>
											<td>${BasicCourseClassDistribution.id }</td>
											<td>${BasicCourseClassDistribution.classNumber }</td>
											<td>${BasicCourseClassDistribution.totalNumber }</td>
											<td>${BasicCourseClassDistribution.excellentRate }</td>
											<td>${BasicCourseClassDistribution.failRate }</td>
										</tr>
									</c:forEach>
								</tbody>
							</table>
						</div>
						<!-- /.card-body -->
					<!-- /.card -->
				</div>
				<!-- /.col -->
			</div>
			<!-- /.row --> 
			
			<h3 style="text-align:center" class="card-title" id = "courseTitle12"></h3> 
			<div id = "secondTermAddCourse3" class="row">
				<div class="col-12">
						<!-- /.card-header --> 
						<div class="card-body" style="margin: 0">
							<table id="example1" class="table table-bordered table-striped">
								<thead>
									<tr>
										<th><center>序号</center></th>
										<th><center>班级</center></th>
										<th><center>考试人数</center></th>
										<th><center>优秀率</center></th>
										<th><center>不及格率</center></th>
									</tr>
								</thead>
								<tbody>
									<c:forEach items="${bccdList12}" var="BasicCourseClassDistribution">
										<tr>
											<td>${BasicCourseClassDistribution.id }</td>
											<td>${BasicCourseClassDistribution.classNumber }</td>
											<td>${BasicCourseClassDistribution.totalNumber }</td>
											<td>${BasicCourseClassDistribution.excellentRate }</td>
											<td>${BasicCourseClassDistribution.failRate }</td>
										</tr>
									</c:forEach>
								</tbody>
							</table>
						</div>
						<!-- /.card-body -->
					<!-- /.card -->
				</div>
				<!-- /.col --> 
			</div>
			<!-- /.row -->
			
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