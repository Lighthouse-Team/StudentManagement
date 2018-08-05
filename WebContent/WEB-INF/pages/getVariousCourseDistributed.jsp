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
<link rel="stylesheet" href="<%=path%>/dist/css/adminlte.min.css">
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
	function getDistributedData() {
		
	 	if($("#picTitle").css('display')=='none'){
            $("#picTitle").css("display","block");
            
		} 
		if($("#scNumberPic").css('display')=='none'){
            $("#scNumberPic").css("display","block");
        } 
		if($("#scRatePic").css('display')=='none'){
	        $("#scRatePic").css("display","block");
	    } 
		
		var aNumberList = new Array();
		var bNumberList = new Array();
		var cNumberList = new Array();
		var dNumberList = new Array();
		var eNumberList = new Array();
		var firstNumberList = new Array();
		var secondNumberList = new Array();
		var thirdNumberList = new Array();
		var forthNumberList = new Array();
		url = "getAllCourseDistributedData";
		var args = {
			year : $("#year").val(),
			term : $("#term").val()
		};
		$.post(url, args, function(odList) {
			for (var i = 0; i < odList.length - 1; i++) {
				option.legend.data[i] = odList[i].grade + '级'; //得到年级号
				option.series[i].name = odList[i].grade + '级';
				aNumberList[i] = odList[i].excellentNumber;
				bNumberList[i] = odList[i].goodNumber;
				cNumberList[i] = odList[i].mediumNumber;
				dNumberList[i] = odList[i].passNumber;
				eNumberList[i] = odList[i].failNumber;

			}
			firstNumberList[0] = aNumberList[0]; //得到第1个年级各个成绩的数量
			firstNumberList[1] = bNumberList[0];
			firstNumberList[2] = cNumberList[0];
			firstNumberList[3] = dNumberList[0];
			firstNumberList[4] = eNumberList[0];

			secondNumberList[0] = aNumberList[1]; //得到第2个年级各个成绩的数量
			secondNumberList[1] = bNumberList[1];
			secondNumberList[2] = cNumberList[1];
			secondNumberList[3] = dNumberList[1];
			secondNumberList[4] = eNumberList[1];

			thirdNumberList[0] = aNumberList[2]; //得到第3个年级各个成绩的数量
			thirdNumberList[1] = bNumberList[2];
			thirdNumberList[2] = cNumberList[2];
			thirdNumberList[3] = dNumberList[2];
			thirdNumberList[4] = eNumberList[2];

			forthNumberList[0] = aNumberList[3]; //得到第4个年级各个成绩的数量
			forthNumberList[1] = bNumberList[3];
			forthNumberList[2] = cNumberList[3];
			forthNumberList[3] = dNumberList[3];
			forthNumberList[4] = eNumberList[3];

			/* 赋值给图表 */
			option.series[0].data = firstNumberList;
			option.series[1].data = secondNumberList;
			option.series[2].data = thirdNumberList;
			option.series[3].data = forthNumberList;
			
			option.series[4].name = '';  //将第五个类别设置为空
			option.series[4].data = [];
			
			option.xAxis[0].data[0] = '优秀(90-100)';
			option.xAxis[0].data[1] = '良好(80-89)';
			option.xAxis[0].data[2] = '中等(70-79)';
			option.xAxis[0].data[3] = '及格(60-69)';
			option.xAxis[0].data[4] = '不及格(0-59)';
			option.yAxis[0].axisLabel.formatter = '{value}';  //只显示数字
			labelOption.normal.formatter = '{c} {name|{a}}';
			
			var dom1 = document.getElementById("scNumberPic");   //显示第一张图
			var myChart1 = echarts.init(dom1);
			if (option && typeof option === "object") {
				myChart1.setOption(option, true);
			}
		});
		
		
		//选择第二张图
		var aRateList = new Array();
		var bRateList = new Array();
		var cRateList = new Array();
		var dRateList = new Array();
		var eRateList = new Array();
		var firstRateList = new Array();
		var secondRateList = new Array();
		var thirdRateList = new Array();
		var forthRateList = new Array();
		var fifthRateList = new Array();
		
		$.post(url, args, function(odList) {
			for (var i = 0; i < odList.length ; i++) {
				option.legend.data[i] = odList[i].grade + '级' ; //得到年级号
				option.series[i].name = odList[i].grade + '级' ;
				
				/*将后台传回来的百分比去掉百分号并转换为数字类型 */
				var aRateNumber = parseFloat(odList[i].excellentRate.substring(0,odList[i].excellentRate.length-1));
				var bRateNumber = parseFloat(odList[i].goodRate.substring(0,odList[i].goodRate.length-1));
				var cRateNumber = parseFloat(odList[i].mediumRate.substring(0,odList[i].mediumRate.length-1));
				var dRateNumber = parseFloat(odList[i].passRate.substring(0,odList[i].passRate.length-1));
				var eRateNumber = parseFloat(odList[i].failRate.substring(0,odList[i].failRate.length-1));
				aRateList[i] = aRateNumber;
				bRateList[i] = bRateNumber;
				cRateList[i] = cRateNumber;
				dRateList[i] = dRateNumber;
				eRateList[i] = eRateNumber;

			}
			firstRateList[0] = aRateList[0]; //得到第1个年级各个等级成绩的百分比
			firstRateList[1] = bRateList[0];
			firstRateList[2] = cRateList[0];
			firstRateList[3] = dRateList[0];
			firstRateList[4] = eRateList[0];

			secondRateList[0] = aRateList[1]; //得到第2个年级各个等级成绩的百分比
			secondRateList[1] = bRateList[1];
			secondRateList[2] = cRateList[1];
			secondRateList[3] = dRateList[1];
			secondRateList[4] = eRateList[1];

			thirdRateList[0] = aRateList[2]; //得到第3个年级各个等级成绩的百分比
			thirdRateList[1] = bRateList[2];
			thirdRateList[2] = cRateList[2];
			thirdRateList[3] = dRateList[2];
			thirdRateList[4] = eRateList[2];

			forthRateList[0] = aRateList[3]; //得到第4个年级各个等级成绩的百分比
			forthRateList[1] = bRateList[3];
			forthRateList[2] = cRateList[3];
			forthRateList[3] = dRateList[3];
			forthRateList[4] = eRateList[3];
			
			fifthRateList[0] = aRateList[4]; //得到整个学校的成绩百分比
			fifthRateList[1] = bRateList[4];
			fifthRateList[2] = cRateList[4];
			fifthRateList[3] = dRateList[4];
			fifthRateList[4] = eRateList[4];

			option.series[0].data = firstRateList;
			option.series[1].data = secondRateList;
			option.series[2].data = thirdRateList;
			option.series[3].data = forthRateList;
			option.series[4].data = fifthRateList;
			
			option.xAxis[0].data[0] = '优秀率';
			option.xAxis[0].data[1] = '良好率';
			option.xAxis[0].data[2] = '中等率';
			option.xAxis[0].data[3] = '及格率';
			option.xAxis[0].data[4] = '不及格率';
			option.yAxis[0].axisLabel.formatter = '{value}%';    //显示百分比
			labelOption.normal.formatter = '{c}% {name|{a}}';
			
			
			var dom2 = document.getElementById("scRatePic");     //显示第二张图
			var myChart2 = echarts.init(dom2);
			
			if (option && typeof option === "object") {
				myChart2.setOption(option, true);
			} 
		});
		
	/* var options = document.getElementById('year').children;
		options[0].selected = true;
	var options = document.getElementById('term').children;
		options[0].selected = true;  */
	/* 	$("#year").get(0).selectedIndex=0;
		$("#term").get(0).selectedIndex=0; */
		 
	};
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
							<li class="breadcrumb-item active">各类课程成绩分布</li>
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
						<form action="getVariousCourseDistributed" method="post">
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
						<input  type="submit" value="查询" /> 
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
									<c:forEach items="${odList}" var="OverallDistribution">
										<tr>
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
							<button  onclick = "getDistributedData()">显示成绩分析图</button>
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
						<div id="scNumberPic"  style="display:none; height: 400%; width:95%; margin: 0;float:left">
						</div>
						<div id="scRatePic"  style="display:none;  height: 400%; width:95%; margin: 0;float:left">
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>


<script type="text/javascript">
	

	var app = {};
	option = null;
	var posList = [
	    'left', 'right', 'top', 'bottom',
	    'inside',
	    'insideTop', 'insideLeft', 'insideRight', 'insideBottom',
	    'insideTopLeft', 'insideTopRight', 'insideBottomLeft', 'insideBottomRight'
	];
	
	app.configParameters = {
	    rotate: {
	        min: -90,
	        max: 90
	    },
	    align: {
	        options: {
	            left: 'left',
	            center: 'center',
	            right: 'right'
	        }
	    },
	    verticalAlign: {
	        options: {
	            top: 'top',
	            middle: 'middle',
	            bottom: 'bottom'
	        }
	    },
	    position: {
	        options: echarts.util.reduce(posList, function (map, pos) {
	            map[pos] = pos;
	            return map;
	        }, {})
	    },
	    distance: {
	        min: 0,
	        max: 100
	    }
	};
	
	app.config = {
	    rotate: 90,
	    align: 'left',
	    verticalAlign: 'middle',
	    position: 'insideBottom',
	    distance: 15,
	    onChange: function () {
	        var labelOption = {
	            normal: {
	                rotate: app.config.rotate,
	                align: app.config.align,
	                verticalAlign: app.config.verticalAlign,
	                position: app.config.position,
	                distance: app.config.distance
	            }
	        };
	        myChart.setOption({
	            series: [{
	                label: labelOption
	            }, {
	                label: labelOption
	            }, {
	                label: labelOption
	            }, {
	                label: labelOption
	            }]
	        });
	    }
	};
	
	
	var labelOption = {
	    normal: {
	        show: true,
	        position: app.config.position,
	        distance: app.config.distance,
	        align: app.config.align,
	        verticalAlign: app.config.verticalAlign,
	        rotate: app.config.rotate,
	        formatter: '{c} {name|{a}}',
	        fontSize: 16,
	        rich: {
	            name: {
	                textBorderColor: '#fff'
	            }
	        }
	    }
	};
	
	option = {
	    color: ['#003366', '#006699', '#4cabce', '#e5323e'],
	    tooltip: {
	        trigger: 'axis',
	        axisPointer: {
	            type: 'shadow'
	        }
	    },
	    legend: {
	        data: []
	    },
	    toolbox: {
	        show: true,
	        orient: 'vertical',
	        left: 'right',
	        top: 'center',
	        feature: {
	            mark: {show: true},
	            dataView: {show: true, readOnly: false},
	            magicType: {show: true, type: ['line', 'bar', 'stack', 'tiled']},
	            restore: {show: true},
	            saveAsImage: {show: true}
	        }
	    },
	    calculable: true,
	    xAxis: [
	        {
	            type: 'category',
	            axisTick: {show: false},
	            data: []
	        }
	    ],
	    yAxis: [
	        {
	        	type: 'value',
	        	axisLabel: {  
	                  show: true,  
	                  interval: 'auto',  
	                  formatter: '{value}'
	                },  
	            show: true 
	        }
	    ],
	    series: [
 	        {
	            name: 'Forest',
	            type: 'bar',
	            barGap: 0,
	            label: labelOption,
	            data: [320, 332, 301, 334, 390]
	        },
	        {
	            name: 'Steppe',
	            type: 'bar',
	            barGap: 0,
	            label: labelOption,
	            data: [220, 182, 191, 234, 290]
	        },
	        {
	            name: 'Desert',
	            type: 'bar',
	            barGap: 0,
	            label: labelOption,
	            data: [150, 232, 201, 154, 190]
	        },
	        {
	            name: 'Wetland',
	            type: 'bar',
	            barGap: 0,
	            label: labelOption,
	            data: [98, 77, 101, 99, 40]
	        },
	        {
	        	name: 'Wetland',
	            type: 'bar',
	            barGap: 0,
	            label: labelOption,
	            data: []
	        }   
	    ]
	};;
	
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
</body>
</html>