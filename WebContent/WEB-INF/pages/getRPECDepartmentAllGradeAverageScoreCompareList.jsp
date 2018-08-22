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
<script type="text/javascript">

	/* 选中seclet值刷新页面不更改 */
	$(function() {         
		if (localStorage.getItem('year')) {
			$("#year option").eq(localStorage.getItem('year')).prop('selected',
					true);
		}
		$("#year").on('change', function() {
			localStorage.setItem('year', $('option:selected', this).index());
		}); 
		
		if (localStorage.getItem('term')) {
			$("#term option").eq(localStorage.getItem('term')).prop('selected',
					true);
		}
		$("#term").on('change', function() {
			localStorage.setItem('term', $('option:selected', this).index());
		});
	});
	
	/* 显示分析图 */
	function getUniversityRPECScoreDistributedData() {
		
	 	if($("#picTitle").css('display')=='none'){
            $("#picTitle").css("display","block");
            
		} 
		if($("#RPECDepartmentAllGradeAverageScoreComparePic").css('display')=='none'){
	        $("#RPECDepartmentAllGradeAverageScoreComparePic").css("display","block");
	    } 
		
		getLinePic();     //显示折线图
		
	/* var options = document.getElementById('year').children;
		options[0].selected = true;
	var options = document.getElementById('term').children;
		options[0].selected = true;  */
	/* 	$("#year").get(0).selectedIndex=0;
		$("#term").get(0).selectedIndex=0; */
		 
	};
	
	function getLinePic(){
		var app1 = {};
		option1 = null;
		option1 = {
			color: ['#003366', '#006699', '#4cabce', '#e5323e'],
		    title: {
		        text: ''
		    },
		    tooltip: {
		        trigger: 'axis'
		    },
		    legend: {
		        data:[]
		    },
		    grid: {
		        left: '3%',
		        right: '4%',
		        bottom: '3%',
		        containLabel: true
		    },
		    toolbox: {
		        feature: {
		            saveAsImage: {}
		        }
		    },
		    xAxis: {
		    	axisLabel: {                                //让x轴坐标文字竖直显示
		        	interval: 0,
		        	formatter:function(value){
		            	return value.split("").join("\n");
		                }
		        },
		        type: 'category',
		        boundaryGap: false,
		        data: ['优秀率','良好率','中等率','及格率','不及格率']
		    },
		    yAxis: {  
	              type: 'value',  
	              axisLabel: {  
	                    show: true,  
	                    interval: 'auto',  
	                    formatter: '{value}'  
	                  },  
	              show: true  
	          },  
		    series: [
		        {
		            name:'邮件营销',
		            type:'line',
		            stack: '总量1',
		            data:[ 13, 10, 13, 9, 23]
		        },
		        {
		            name:'联盟广告',
		            type:'line',
		            stack: '总量2',
		            data:[20, 18, 19, 24, 20]
		        },
		        {
		            name:'视频广告',
		            type:'line',
		            stack: '总量3',
		            data:[10, 23, 21, 14, 90]
		        },
		        {
		            name:'直接访问',
		            type:'line',
		            stack: '总量4',
		            data:[32, 13, 30, 34, 30]
		        }
		    ]
		};
		;
		
		/* 与后台连接传递数据 */
		var aRateList = new Array();
		var bRateList = new Array();
		var cRateList = new Array();
		var dRateList = new Array();
		
		url1 = "getGradeListData";
		var args1 = {
			year : $("#year").val()
		};
		$.post(url1, args1, function(gradeList){
			for(var i=0; i<gradeList.length ; i++){
				option1.legend.data[i] = gradeList[i];
				option1.series[i].name = gradeList[i];
			}
		}); 
		
		url = "getRPECDepartmentAllGradeAverageScoreCompareListData";
		var args = {
			year : $("#year").val(),
			term : $("#term").val()
		};
		$.post(url, args, function(dagascList){
			
			for(var i=0; i<dagascList.length ; i++){
				option1.xAxis.data[i] = dagascList[i].departmentName;
				
/*  				if(odList[i].grade != "全校"){
					option1.legend.data[i] = odList[i].grade + '级';
					option1.series[i].name = odList[i].grade + '级';
				}
				else{
					option1.legend.data[i] = odList[i].grade ; //得到年级号
					option1.series[i].name = odList[i].grade ;
				}  */
				var aNumber = parseFloat(dagascList[i].gradeFourDifference);
				var bNumber = parseFloat(dagascList[i].gradeThreeDifference);
				var cNumber = parseFloat(dagascList[i].gradeTwoDifference);
				var dNumber = parseFloat(dagascList[i].gradeOneDifference);
				aRateList[i] = aNumber;
				bRateList[i] = bNumber;
				cRateList[i] = cNumber;
				dRateList[i] = dNumber;
			}
			
			option1.series[0].data = aRateList;
			option1.series[1].data = bRateList;
			option1.series[2].data = cRateList;
			option1.series[3].data = dRateList;
			
			var dom1 = document.getElementById("RPECDepartmentAllGradeAverageScoreComparePic");
			var myChart1 = echarts.init(dom1);
			if (option1 && typeof option1 === "object") {
			    myChart1.setOption(option1, true);
			}
		
		});
		
	
	}
	
</script>


</head>
<body class="hold-transition sidebar-mini">
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
							<li class="breadcrumb-item active">各院系分年级成绩平均分比较</li>
						</ol>
					</div>
				</div>
			</div>
			<!-- /.container-fluid --> </section>
			
			<section class="content-header">
			<div class="container-fluid">
				<!-- SELECT2 EXAMPLE -->
				<div class="card card-default">
					<div class="card-header">
						<h3 class="card-title">选择学年和学期</h3>
					</div>
					<!-- /.card-header -->
					<div class="card-body">
						<form action="getRPECDepartmentAllGradeAverageScoreCompareList" method="post">
						<div class="row">
							<div class="col-md-6">
								<div class="form-group">
									<label>学年</label> 
									<select id = "year" class="form-control select2" name="year"
										data-placeholder="Select a State" style="width: 100%;">
										<option value="">选择学年</option>
										<c:forEach items="${yearList}" var="year">
											<option value="${year}">${year }</option>
										</c:forEach>
									</select>
								</div>
							</div>
							<!-- /.col -->
							<div class="col-md-6">
								<!-- /.form-group -->
								<div class="form-group">
									<label>学期</label> 
									<select id = "term" class="form-control select2" name="term"
										data-placeholder="Select a State" style="width: 100%;">
										<option value="">选择学期</option>
										<option>1</option>
										<option>2</option>
									</select>
								</div>
								<!-- /.form-group -->
							</div>
							<!-- /.col -->
						</div>
						<!-- /.row -->
						<input  type="submit" class="btn btn-info float-left" value="查询" /> 
						</form>
						
					</div>
					<!-- /.card-body -->
				</div>
				<!-- /.card -->
			</div>
			</section>

			<section class="content">
			<div class="row">
				<div class="col-12">
					<div class="card">
						<div class="card-header">
							<h3 class="card-title">所有课程成绩分布</h3> 
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
							<button  class="btn btn-info float-left" onclick = "getUniversityRPECScoreDistributedData()">显示成绩分析图</button>
						</div>
						<!-- /.card-body -->
					</div>
					<!-- /.card -->
				</div>
				<!-- /.col -->
			</div>
			<!-- /.row --> 
			</section>
			
			<div class="row">
				<div class="col-12">
					<div class="card">
						<div id = "picTitle" class="card-header" style="display:none">
							<h3 class="card-title">成绩分析图</h3> 
						</div>
						<div id="RPECDepartmentAllGradeAverageScoreComparePic"  style="display:none;  height: 400%; width:95%; margin: 0;float:left">
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>

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