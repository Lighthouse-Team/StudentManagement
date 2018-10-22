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
<script src="<%=path%>/assets/js/jquery-1.7.2.min.js"></script>
<script src="<%=path%>/assets/js/jquery.blockUI.js"></script>
<script type="text/javascript">

	/* 选中seclet值刷新页面不更改 */
	$(function() {  
		$.blockUI.defaults.message = '<h1> 成绩数据正在加载中，请稍后... <img src="<%=path%>/pic/busy.gif" /></h1>';
		$(document).ajaxStart($.blockUI).ajaxStop($.unblockUI);
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
	
	$(function() {
		$('#getData').click(function(){
			$.blockUI({ message: '<h1> 成绩数据正在加载中，请稍后... <img src="<%=path%>/pic/busy.gif" /></h1>' });
		});
	});

	
	$(function() {
		$('#getPic').click(function(){
			if($("#picTitle").css('display')=='none'){
	            $("#picTitle").css("display","block");
	            
			} 
			if($("#variousScRateBarPic").css('display')=='none'){
	            $("#variousScRateBarPic").css("display","block");
	        } 
			if($("#variousScRateLinePic").css('display')=='none'){
		        $("#variousScRateLinePic").css("display","block");
		    } 
			getBarPic();      //显示柱状图
			getLinePic();     //显示折线图
		});
	});
	
	/* 显示柱状图 */
	function getBarPic(){
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
		        formatter: '{c}% {name|{a}}',
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
		            data: ['优秀率', '良好率', '中等率', '及格率', '不及格率']
		        }
		    ],
		    yAxis: [
		    	  {  
		              type: 'value',  
		              axisLabel: {  
		                    show: true,  
		                    interval: 'auto',  
		                    formatter: '{value}%'  
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
		            label: labelOption,
		            data: [220, 182, 191, 234, 290]
		        },
		        {
		            name: 'Desert',
		            type: 'bar',
		            label: labelOption,
		            data: [150, 232, 201, 154, 190]
		        },
		        {
		            name: 'Wetland',
		            type: 'bar',
		            label: labelOption,
		            data: [98, 77, 101, 99, 40]
		        }
		    ]
		};;
		
		/* 与后台连接传递数据 */
		var aRateList = new Array();
		var bRateList = new Array();
		var cRateList = new Array();
		var dRateList = new Array();
		var eRateList = new Array();
		var firstRateList = new Array();
		var secondRateList = new Array();
		var thirdRateList = new Array();
		var forthRateList = new Array();
		url = "getVariousCourseDistributedData";
		var args = {
			year : $("#year").val(),
			term : $("#term").val()
		};
		$.post(url, args, function(odList){
			for(var i=0; i<odList.length ; i++){
				if(odList[i].courseType == "通识<br>教育"){
					option.legend.data[i] = "通识教育";
					option.series[i].name = "通识教育";
				}
				else if (odList[i].courseType == "专业<br>选修"){
					option.legend.data[i] = "专业选修";
					option.series[i].name = "专业选修";
				}
				else{
					option.legend.data[i] = odList[i].courseType;
					option.series[i].name = odList[i].courseType;
				}
				
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
			
			firstRateList[0] = aRateList[0]; //得到必修课成绩的百分比
			firstRateList[1] = bRateList[0];
			firstRateList[2] = cRateList[0];
			firstRateList[3] = dRateList[0];
			firstRateList[4] = eRateList[0];

			secondRateList[0] = aRateList[1]; //得到专业选修成绩的百分比
			secondRateList[1] = bRateList[1];
			secondRateList[2] = cRateList[1];
			secondRateList[3] = dRateList[1];
			secondRateList[4] = eRateList[1];

			thirdRateList[0] = aRateList[2]; //得到通识教育成绩的百分比
			thirdRateList[1] = bRateList[2];
			thirdRateList[2] = cRateList[2];
			thirdRateList[3] = dRateList[2];
			thirdRateList[4] = eRateList[2];

			forthRateList[0] = aRateList[3]; //得到第全校成绩的百分比
			forthRateList[1] = bRateList[3];
			forthRateList[2] = cRateList[3];
			forthRateList[3] = dRateList[3];
			forthRateList[4] = eRateList[3];
			
			option.series[0].data = firstRateList;
			option.series[1].data = secondRateList;
			option.series[2].data = thirdRateList;
			option.series[3].data = forthRateList;
			
			var dom = document.getElementById("variousScRateBarPic");
			var myChart = echarts.init(dom);
			if (option && typeof option === "object") {
			    myChart.setOption(option, true);
			}
		});
		
	}
	
	function getLinePic(){
		var app1 = {};
		option1 = null;
		option1 = {
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
		        left: '8%',
		        right: '12%',
		        bottom: '3%',
		        containLabel: true
		    },
		    toolbox: {
		        feature: {
		            saveAsImage: {}
		        }
		    },
		    xAxis: {
		        type: 'category',
		        boundaryGap: false,
		        data: ['优秀率','良好率','中等率','及格率','不及格率']
		    },
		    yAxis: {  
	              type: 'value',  
	              axisLabel: {  
	                    show: true,  
	                    interval: 'auto',  
	                    formatter: '{value}%'  
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
		var eRateList = new Array();
		var firstRateList = new Array();
		var secondRateList = new Array();
		var thirdRateList = new Array();
		var forthRateList = new Array();
		url = "getVariousCourseDistributedData";
		var args = {
			year : $("#year").val(),
			term : $("#term").val()
		};
		$.post(url, args, function(odList){
			
			
			for(var i=0; i<odList.length ; i++){
				if(odList[i].courseType == "通识<br>教育"){
					option1.legend.data[i] = "通识教育";
					option1.series[i].name = "通识教育";
				}
				else if (odList[i].courseType == "专业<br>选修"){
					option1.legend.data[i] = "专业选修";
					option1.series[i].name = "专业选修";
				}
				else{
					option1.legend.data[i] = odList[i].courseType;
					option1.series[i].name = odList[i].courseType;
				}
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
			
			firstRateList[0] = aRateList[0]; //得到必修课成绩的百分比
			firstRateList[1] = bRateList[0];
			firstRateList[2] = cRateList[0];
			firstRateList[3] = dRateList[0];
			firstRateList[4] = eRateList[0];

			secondRateList[0] = aRateList[1]; //得到专业选修成绩的百分比
			secondRateList[1] = bRateList[1];
			secondRateList[2] = cRateList[1];
			secondRateList[3] = dRateList[1];
			secondRateList[4] = eRateList[1];

			thirdRateList[0] = aRateList[2]; //得到通识教育成绩的百分比
			thirdRateList[1] = bRateList[2];
			thirdRateList[2] = cRateList[2];
			thirdRateList[3] = dRateList[2];
			thirdRateList[4] = eRateList[2];

			forthRateList[0] = aRateList[3]; //得到第全校成绩的百分比
			forthRateList[1] = bRateList[3];
			forthRateList[2] = cRateList[3];
			forthRateList[3] = dRateList[3];
			forthRateList[4] = eRateList[3];
			
			option1.series[0].data = firstRateList;
			option1.series[1].data = secondRateList;
			option1.series[2].data = thirdRateList;
			option1.series[3].data = forthRateList;
			
			var dom1 = document.getElementById("variousScRateLinePic");
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
						<input id="getData" type="submit" class="btn btn-info float-left" value="查询" /> 
						</form>
						
					</div>
					<!-- /.card-body -->
				</div>
				<!-- /.card -->
			</div>
			</section>

			<section class="content">
				<div class="col-12">
					<div class="card">
						<div class="card-header">
							<h3 class="card-title">各类课程成绩分布</h3> 
						</div>
						<!-- /.card-header -->
						<div class="card-body" style="margin: 0">
							<table id="example1" class="table table-bordered table-striped">
								<thead>
									<tr>
										<th style = "text-align:center ; vertical-align: middle ">序号</th>
										<th style = "text-align:center ; vertical-align: middle ">课程种类</th>
										<th style = "text-align:center ; vertical-align: middle ">成绩记录总数</th>
										<th style = "text-align:center ; vertical-align: middle ">优秀(90-100)</th>
										<th style = "text-align:center ; vertical-align: middle ">良好(80-89)</th>
										<th style = "text-align:center ; vertical-align: middle ">中等(70-79)</th>
										<th style = "text-align:center ; vertical-align: middle ">及格(60-69)</th>
										<th style = "text-align:center ; vertical-align: middle ">不及格(0-59)</th>
										<th style = "text-align:center ; vertical-align: middle ">平均分</th>
										<th style = "text-align:center ; vertical-align: middle ">优秀率</th>
										<th style = "text-align:center ; vertical-align: middle ">良好率</th>
										<th style = "text-align:center ; vertical-align: middle ">中等率</th>
										<th style = "text-align:center ; vertical-align: middle ">及格率</th>
										<th style = "text-align:center ; vertical-align: middle ">不及格率</th>
									</tr>
								</thead>
								<tbody>
									<c:forEach items="${odList}" var="OverallDistribution">
										<tr>
											<td style = "text-align:center ; vertical-align: middle ">${OverallDistribution.id }</td>
											<td style = "text-align:center ; vertical-align: middle ">${OverallDistribution.courseType }</td>
											<td style = "text-align:center ; vertical-align: middle ">${OverallDistribution.totalNumber }</td>
											<td style = "text-align:center ; vertical-align: middle ">${OverallDistribution.excellentNumber }</td>
											<td style = "text-align:center ; vertical-align: middle ">${OverallDistribution.goodNumber }</td>
											<td style = "text-align:center ; vertical-align: middle ">${OverallDistribution.mediumNumber }</td>
											<td style = "text-align:center ; vertical-align: middle ">${OverallDistribution.passNumber }</td>
											<td style = "text-align:center ; vertical-align: middle ">${OverallDistribution.failNumber }</td>
											<td style = "text-align:center ; vertical-align: middle ">${OverallDistribution.averageScore }</td>
											<td style = "text-align:center ; vertical-align: middle ">${OverallDistribution.excellentRate }</td>
											<td style = "text-align:center ; vertical-align: middle ">${OverallDistribution.goodRate }</td>
											<td style = "text-align:center ; vertical-align: middle ">${OverallDistribution.mediumRate }</td>
											<td style = "text-align:center ; vertical-align: middle ">${OverallDistribution.passRate }</td>
											<td style = "text-align:center ; vertical-align: middle ">${OverallDistribution.failRate }</td>
										</tr>
									</c:forEach>
								</tbody>
							</table>
							<button id="getPic" class="btn btn-info float-left">显示成绩分析图</button>
						</div>
						<!-- /.card-body -->
					</div>
					<!-- /.card -->
				</div>
				<!-- /.col -->
			<!-- /.row --> 
			</section>
			
			<section class="content">
				<div class="col-12">
					<div class="card">
						<div id = "picTitle" class="card-header" style="display:none">
							<h3 class="card-title">成绩分析图</h3> 
						</div>
						<div id="variousScRateBarPic"  style="display:none; height: 400%; width:95%; margin: 0;float:left">
						</div>
						<div id="variousScRateLinePic"  style="display:none;  height: 400%; width:95%; margin: 0;float:left">
						</div>
					</div>
				</div>
			</section>
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