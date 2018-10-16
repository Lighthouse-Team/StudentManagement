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
	
	$(function() {       
		$.blockUI.defaults.message = '<h1> 成绩数据正在加载中，请稍后... <img src="<%=path%>/pic/busy.gif" /></h1>';
		$(document).ajaxStart($.blockUI).ajaxStop($.unblockUI);
		/* 选中seclet值刷新页面不更改 */
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
		
		
	 	var courseNameSelected =document.getElementById("courseName").value;
		document.getElementById("head").innerHTML=courseNameSelected + "成绩情况";
				
		var termSelected =document.getElementById("term").value;
		var termSelectedNumber = parseInt(termSelected);
		
		$("#courseName").empty();
		
		var firstTermCourse = new Array();
		var secondTermCourse = new Array();
		firstTermCourse.push("大学英语（一）");
		firstTermCourse.push("大学计算机基础A");
		firstTermCourse.push("普通化学");
		firstTermCourse.push("线性代数与解析几何A");
		firstTermCourse.push("微积分A（一）");
		firstTermCourse.push("大学英语（三）");
		firstTermCourse.push("大学物理下A");
		firstTermCourse.push("大学物理下B");
		firstTermCourse.push("大学物理实验（二）");
		firstTermCourse.push("复变函数与积分变换");
		
		secondTermCourse.push("大学英语（二）");
		secondTermCourse.push("程序设计基础");
		secondTermCourse.push("大学物理上");
		secondTermCourse.push("概率论与数理统计");
		secondTermCourse.push("微积分A（二）");
		secondTermCourse.push("工程图学基础");
		secondTermCourse.push("工程实践A");
		secondTermCourse.push("机械设计基础B");
		secondTermCourse.push("大学英语（四）");
	

		if (termSelectedNumber == 1) {
			for (var i = 0; i < firstTermCourse.length; i++) {
				//先创建好select里面的option元素
				var option = document.createElement("option");
				//转换DOM对象为JQ对象,好用JQ里面提供的方法 给option的value赋值
				option.val = firstTermCourse[i];
				//给option的text赋值,这就是你点开下拉框能够看到的东西
				option.text = firstTermCourse[i];
				//获取select 下拉框对象,并将option添加进select
				courseName.appendChild(option);
			}
		} else if (termSelectedNumber == 2) {
			for (var i = 0; i < secondTermCourse.length; i++) {
				//先创建好select里面的option元素
				var option = document.createElement("option");
				//转换DOM对象为JQ对象,好用JQ里面提供的方法 给option的value赋值
				option.val = secondTermCourse[i];
				//给option的text赋值,这就是你点开下拉框能够看到的东西
				option.text = secondTermCourse[i];
				//获取select 下拉框对象,并将option添加进select
				courseName.appendChild(option);
			}
		}
	 	
	});
	

	$(function() {
		$('#getData').click(function(){
			$.blockUI({ message: '<h1> 成绩数据正在加载中，请稍后... <img src="<%=path%>/pic/busy.gif" /></h1>' });
		});
	});
	
	function setSelect(){
		var termSelected =document.getElementById("term").value;
		var termSelectedNumber = parseInt(termSelected);
		
		$("#courseName").empty();
		
		var firstTermCourse = new Array();
		var secondTermCourse = new Array();
		firstTermCourse.push("大学英语（一）");
		firstTermCourse.push("大学计算机基础A");
		firstTermCourse.push("普通化学");
		firstTermCourse.push("线性代数与解析几何A");
		firstTermCourse.push("微积分A（一）");
		firstTermCourse.push("大学英语（三）");
		firstTermCourse.push("大学物理下A");
		firstTermCourse.push("大学物理下B");
		firstTermCourse.push("大学物理实验（二）");
		firstTermCourse.push("复变函数与积分变换");
		
		secondTermCourse.push("大学英语（二）");
		secondTermCourse.push("程序设计基础");
		secondTermCourse.push("大学物理上");
		secondTermCourse.push("概率论与数理统计");
		secondTermCourse.push("微积分A（二）");
		secondTermCourse.push("工程图学基础");
		secondTermCourse.push("工程实践A");
		secondTermCourse.push("机械设计基础B");
		secondTermCourse.push("大学英语（四）");
	

		if (termSelectedNumber == 1) {
			for (var i = 0; i < firstTermCourse.length; i++) {
				//先创建好select里面的option元素
				var option = document.createElement("option");
				//转换DOM对象为JQ对象,好用JQ里面提供的方法 给option的value赋值
				option.val = firstTermCourse[i];
				//给option的text赋值,这就是你点开下拉框能够看到的东西
				option.text = firstTermCourse[i];
				//获取select 下拉框对象,并将option添加进select
				courseName.appendChild(option);
			}
		} else if (termSelectedNumber == 2) {
			for (var i = 0; i < secondTermCourse.length; i++) {
				//先创建好select里面的option元素
				var option = document.createElement("option");
				//转换DOM对象为JQ对象,好用JQ里面提供的方法 给option的value赋值
				option.val = secondTermCourse[i];
				//给option的text赋值,这就是你点开下拉框能够看到的东西
				option.text = secondTermCourse[i];
				//获取select 下拉框对象,并将option添加进select
				courseName.appendChild(option);
			}
		}
		
	};
	
	$(function() {
		$('#getPic').click(function(){
			if($("#picTitle").css('display')=='none'){
	            $("#picTitle").css("display","block");
	            
			} 
			if($("#basicCourseDetailDistributionListByCourseNameBarPic").css('display')=='none'){
	            $("#basicCourseDetailDistributionListByCourseNameBarPic").css("display","block");
	        } 
			getBarPic();      //显示柱状图
		});
		
		$('#getNormalAnalysis').click(function(){
			if($("#normalAnalysis").css('display')=='none'){
	            $("#normalAnalysis").css("display","block");
	            
			}
			
			var colors = ['#5793f3', '#d14a61', '#675bba'];
			
			
			option = {
			color: colors,
			
			tooltip: {
			    trigger: 'none',
			    axisPointer: {
			        type: 'cross'
			    }
			},
			legend: {
			    data:['正态分布', '实际分布']
			},
			grid: {
			    top: 70,
			    bottom: 50
			},
			xAxis: [
			    {
			        type: 'category',
			        axisTick: {
			            alignWithLabel: true
			        },
			        axisLine: {
			            onZero: false,
			            lineStyle: {
			                color: colors[1]
			            }
			        },
			        axisPointer: {
			            label: {
			                formatter: function (params) {
			                    return  + params.value
			                        + (params.seriesData.length ? '：' + params.seriesData[0].data : '');
			                }
			            }
			        },
			        data: ["0-9", "10-19", "20-29","30-39", "40-49", "50-59","60-69", "70-79", "80-89","90-100"]
			    },
			    {
			    	show : false, 
			        type: 'category',
			        axisTick: {
			            alignWithLabel: true
			        },
			        axisLine: {
			            onZero: false,
			            lineStyle: {
			                color: colors[0]
			            }
			        },
			        axisPointer: {
			            label: {
			                formatter: function (params) {
			                    return  + params.value
			                        + (params.seriesData.length ? '：' + params.seriesData[0].data : '');
			                }
			            }
			        },
			        data: ["0","0", "0","0","0","5", "0","85", "0", "0","0","0","90","0", "0","0"]
			    }
			    
			],
			yAxis: [
				 {
			         type: 'value',
			     },
			     {
			    	 show : false, 
			         name: '正态分布',
			         type: 'value',
			     }
			],
			series: [
			    {
			        name:'正态分布',
			        type:'line',
			        xAxisIndex: 1,
			        yAxisIndex : 1, 
			        smooth: true,
			        data: [0.1,1.3,2.4,3.2,4.1,5.7,6.4,7.9,9,11,11.6,12.4,11,5,3,0]
			    },
			    {
			        name:'实际分布',
			        type:'bar',
			        smooth: true,
			        data: [3.9, 5.9, 11.1,26.4, 28.7, 70.7, 175.6, 182.2, 48.7, 18.8]
			    }
			]
			};
			    // 使用刚指定的配置项和数据显示图表
			    var dom = document.getElementById("main");     //显示第二张图
				var myChart = echarts.init(dom);
				
				if (option && typeof option === "object") {
					myChart.setOption(option, true);
				}
			
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
		        formatter: '',
		        fontSize: 16,
		        rich: {
		            name: {
		                textBorderColor: '#fff'
		            }
		        }
		    }
		};

		option = {
		    color: ['#003366', '#006699', '#4cabce', '#e5323e','#000000'],
		    tooltip: {
		        trigger: 'axis',
		        axisPointer: {
		            type: 'shadow'
		        }
		    },
		    legend: {
		        data: ['优秀率', '不及格率']
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
		        	axisLabel: {
                        interval:0,
                        rotate:20
                    },
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
		            name: '优秀率',
		            type: 'bar',
		            barGap: 0,
		            label: labelOption,
		            data: [320, 332, 301, 334, 390]
		        },
		        {
		            name: '不及格率',
		            type: 'bar',
		            label: labelOption,
		            data: [220, 182, 191, 234, 290]
		        }
		    ]
		};;
		
		

		/* 与后台连接传递数据 */
		var aRateList = new Array();
		var bRateList = new Array();
		url = "getBasicCourseDetailDistributionListByCourseNameData";
		var args = {
			year : $("#year").val(),
			term : $("#term").val(),
			courseName : $("#courseName").val()
		};

		$.post(url, args, function(bcddList){
			for(var i = 0; i < bcddList.length ; i++){
				option.xAxis[0].data[i] = bcddList[i].departmentName;
				/*将后台传回来的百分比去掉百分号并转换为数字类型 */
				aRateList[i] = parseFloat(bcddList[i].excellentRate.substring(0,bcddList[i].excellentRate.length-1));
				bRateList[i] = parseFloat(bcddList[i].failRate.substring(0,bcddList[i].failRate.length-1));
			}
			
			option.series[0].data = aRateList;
			option.series[1].data = bRateList;
			
			var dom = document.getElementById("basicCourseDetailDistributionListByCourseNameBarPic");
			var myChart = echarts.init(dom);
			if (option && typeof option === "object") {
			    myChart.setOption(option, true);
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
							<li class="breadcrumb-item active">主要基础课程成绩情况统计分布</li>
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
						<form action="getBasicCourseDetailDistributionListByCourseName" method="post">
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
										data-placeholder="Select a State" style="width: 100%;" onchange = "setSelect()">
										<option value="">选择学期</option>
										<option>1</option>
										<option>2</option>
									</select>
								</div>
								<!-- /.form-group -->
							</div>
							<div class="col-md-6">
								<!-- /.form-group -->
								<div class="form-group">
									<label>课程名称</label> 
									<select id = "courseName" class="form-control select2" name="courseName"
										data-placeholder="Select a State" style="width: 100%;">
										<option value="">选择课程名称</option>
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
			<div class="row">
				<div class="col-12">
					<div class="card">
						<div class="card-header"> 
							<h3 class="card-title" id = "head">主要基础课程成绩情况统计分布</h3> 
						</div>
						<!-- /.card-header -->
						<div class="card-body" style="margin: 0">
							<table id="example1" class="table table-bordered table-striped">
								<thead>
									<tr>
										<th style = "text-align:center ; vertical-align: middle "><center>序号</center></th>
										<th style = "text-align:center ; vertical-align: middle "><center>院系</center></th>
										<th style = "text-align:center ; vertical-align: middle "><center>成绩数</center></th>
										<th style = "text-align:center ; vertical-align: middle "><center>优秀人数</center></th>
										<th style = "text-align:center ; vertical-align: middle "><center>优秀率</center></th> 
										<th style = "text-align:center ; vertical-align: middle "><center>不及格人数</center></th>
										<th style = "text-align:center ; vertical-align: middle "><center>不及格率</center></th>
									</tr>
								</thead>
								<tbody>
									<c:forEach items="${bcddList}" var="BasicCourseDetailDistribution">
										<tr>
											<td style = "text-align:center ; vertical-align: middle ">${BasicCourseDetailDistribution.id }</td>
											<td style = "text-align:center ; vertical-align: middle ">${BasicCourseDetailDistribution.departmentName }</td>
											<td style = "text-align:center ; vertical-align: middle ">${BasicCourseDetailDistribution.totalStudentNumber }</td>
											<td style = "text-align:center ; vertical-align: middle ">${BasicCourseDetailDistribution.excellentNumber }</td>
											<td style = "text-align:center ; vertical-align: middle ">${BasicCourseDetailDistribution.excellentRate }</td>
											<td style = "text-align:center ; vertical-align: middle ">${BasicCourseDetailDistribution.failNumber }</td>
											<td style = "text-align:center ; vertical-align: middle ">${BasicCourseDetailDistribution.failRate }</td>
										</tr>
									</c:forEach>
								</tbody>
							</table>
							<button id="getPic" class="btn btn-info float-left" >显示成绩分析图</button>
							<button id="getNormalAnalysis" class="btn btn-info float-right" >显示成绩正态分析</button>
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
						<div id="basicCourseDetailDistributionListByCourseNameBarPic"  style="display:none; height: 400%; width:95%; margin: 0;float:left">
						</div>
					</div>
				</div>
			</div>
			
			<!--  id ="normalAnalysis"  style="display:none"-->
			<div id ="normalAnalysis" class="row" style="display:none" >
				<div class="col-12">
					<div class="card">
						<div class="card-header"> 
							<h3 class="card-title" id = "head">成绩正态分布分析</h3> 
						</div>
						<!-- /.card-header -->
						<div class="card-body" style="margin: 0">
							<table id="example1" class="table table-bordered table-striped">
								<thead>
									<tr>
										<th style = "text-align:center ; vertical-align: middle "><center>开课单位</center></th>
										<td style = "text-align:center ; vertical-align: middle" colspan="2"><center>信通学院</center></td>
										<th style = "text-align:center ; vertical-align: middle "><center>授课学期</center></th>
										<td style = "text-align:center ; vertical-align: middle" colspan="2"><center>第5学期</center></td>
									</tr>
									
									<tr>
										<th style = "text-align:center ; vertical-align: middle "><center>课程编号</center></th>
										<td style = "text-align:center ; vertical-align: middle"  colspan="2"><center>08010050</center></td>
										<th style = "text-align:center ; vertical-align: middle "><center>课程名称</center></th>
										<td style = "text-align:center ; vertical-align: middle"  colspan="2"><center>数字电子技术</center></td>
									</tr>
									
									<tr>
										<th style = "text-align:center ; vertical-align: middle "><center>授课对象</center></th>
										<td style = "text-align:center ; vertical-align: middle"  colspan="5"><center>2005级本科生</center></td>
									</tr>
								</thead>
								<tbody>
									<tr>
										<th style = "text-align:center ; vertical-align: middle ">统计成绩记录数</th>
										<td style = "text-align:center ; vertical-align: middle ">877</td>
										<th style = "text-align:center ; vertical-align: middle ">平均分</th>
										<td style = "text-align:center ; vertical-align: middle ">69.25</td>
										<th style = "text-align:center ; vertical-align: middle ">标准差</th>
										<td style = "text-align:center ; vertical-align: middle ">19.5</td>
									</tr>
									
									<tr>
										<th style = "text-align:center ; vertical-align: middle ">不及格人数</th>
										<td style = "text-align:center ; vertical-align: middle ">189</td>
										<th style = "text-align:center ; vertical-align: middle ">不及格率</th>
										<td style = "text-align:center ; vertical-align: middle ">19.34%</td>
										<th style = "text-align:center ; vertical-align: middle"  rowspan="2" colspan="2"></th>
									</tr>
									
									<tr>
										<th style = "text-align:center ; vertical-align: middle ">优良人数</th>
										<td style = "text-align:center ; vertical-align: middle ">273</td>
										<th style = "text-align:center ; vertical-align: middle ">优良率</th>
										<td style = "text-align:center ; vertical-align: middle ">35.34%</td>
									</tr>
									
									<tr>
										<th style = "text-align:center ; vertical-align: middle ">分段数</th>
										<th style = "text-align:center ; vertical-align: middle ">人数</th>
										<th style = "text-align:center ; vertical-align: middle"  colspan="4">成绩分布图</th>
									</tr>
									
									<tr>
										<th style = "text-align:center ; vertical-align: middle ">0-9</th>
										<td style = "text-align:center ; vertical-align: middle ">10</td>
										<td width = 60% align = center colspan="4" rowspan="10"> <div id="main" style="height: 400% ;width:95%;"></div></td>
									</tr>
									
									
									<tr>
										<th style = "text-align:center ; vertical-align: middle ">10-19</th>
										<td style = "text-align:center ; vertical-align: middle ">21</td>
									</tr>
									
									<tr>
										<th style = "text-align:center ; vertical-align: middle ">20-29</th>
										<td style = "text-align:center ; vertical-align: middle ">28</td>
									</tr>
									
									<tr>
										<th style = "text-align:center ; vertical-align: middle ">40-49</th>
										<td style = "text-align:center ; vertical-align: middle ">52</td>
									</tr>
									
									<tr>
										<th style = "text-align:center ; vertical-align: middle ">50-59</th>
										<td style = "text-align:center ; vertical-align: middle ">48</td>
									</tr>
									
									<tr>
										<th style = "text-align:center ; vertical-align: middle ">60-69</th>
										<td style = "text-align:center ; vertical-align: middle ">238</td>
									</tr>
									
									<tr>
										<th style = "text-align:center ; vertical-align: middle ">70-79</th>
										<td style = "text-align:center ; vertical-align: middle ">216</td>
									</tr>
									
									<tr>
										<th style = "text-align:center ; vertical-align: middle ">80-89</th>
										<td style = "text-align:center ; vertical-align: middle ">215</td>
									</tr>
									
									<tr>
										<th style = "text-align:center ; vertical-align: middle ">90-99</th>
										<td style = "text-align:center ; vertical-align: middle ">119</td>
									</tr>
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