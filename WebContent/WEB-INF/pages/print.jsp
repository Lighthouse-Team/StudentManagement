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
		/* 基础课程上学期和下学期不一样 判断处理 */
		var term1 = "${term}";
		if(term1 == "1"){
			
			var courseDepartmentTitle = document.getElementById("courseDepartmentTitle");
			courseDepartmentTitle.innerHTML = "大学英语（三）" + "成绩情况";
			var courseDepartmentTitle1 = document.getElementById("courseDepartmentTitle1");
			courseDepartmentTitle1.innerHTML = "大学物理下A"+ "成绩情况";
			var courseDepartmentTitle2 = document.getElementById("courseDepartmentTitle2");
			id=courseDepartmentTitle2.innerHTML = "大学物理下B"+ "成绩情况";
			var courseDepartmentTitle3 = document.getElementById("courseDepartmentTitle3");
			courseDepartmentTitle3.innerHTML = "大学物理实验（二）"+ "成绩情况";
			var courseDepartmentTitle4 = document.getElementById("courseDepartmentTitle4");
			courseDepartmentTitle4.innerHTML = "复变函数与积分变换"+ "成绩情况";
			var courseDepartmentTitle5 = document.getElementById("courseDepartmentTitle5");
			courseDepartmentTitle5.innerHTML = "大学英语（一）"+ "成绩情况";
			var courseDepartmentTitle6 = document.getElementById("courseDepartmentTitle6");
			courseDepartmentTitle6.innerHTML = "大学计算机基础A"+ "成绩情况";
			var courseDepartmentTitle7 = document.getElementById("courseDepartmentTitle7");
			courseDepartmentTitle7.innerHTML = "普通化学"+ "成绩情况";
			var courseDepartmentTitle8 = document.getElementById("courseDepartmentTitle8");
			courseDepartmentTitle8.innerHTML = "线性代数与解析几何A"+ "成绩情况";
			var courseDepartmentTitle9 = document.getElementById("courseDepartmentTitle9");
			courseDepartmentTitle9.innerHTML = "微积分A（一）"+ "成绩情况";
			
			var courseTitle = document.getElementById("courseTitle");
			courseTitle.innerHTML = "大学英语（三）" + "各班级优秀率、不及格率情况";
			var courseTitle1 = document.getElementById("courseTitle1");
			courseTitle1.innerHTML = "大学物理下A"+ "各班级优秀率、不及格率情况";
			var courseTitle2 = document.getElementById("courseTitle2");
			courseTitle2.innerHTML = "大学物理下B"+ "各班级优秀率、不及格率情况";
			var courseTitle3 = document.getElementById("courseTitle3");
			courseTitle3.innerHTML = "大学物理实验（二）"+ "各班级优秀率、不及格率情况";
			var courseTitle4 = document.getElementById("courseTitle4");
			courseTitle4.innerHTML = "复变函数与积分变换"+ "各班级优秀率、不及格率情况";
			var courseTitle5 = document.getElementById("courseTitle5");
			courseTitle5.innerHTML = "大学英语（一）"+ "各班级优秀率、不及格率情况";
			var courseTitle6 = document.getElementById("courseTitle6");
			courseTitle6.innerHTML = "大学计算机基础A"+ "各班级优秀率、不及格率情况";
			var courseTitle7 = document.getElementById("courseTitle7");
			courseTitle7.innerHTML = "普通化学"+ "各班级优秀率、不及格率情况";
			var courseTitle8 = document.getElementById("courseTitle8");
			courseTitle8.innerHTML = "线性代数与解析几何A"+ "各班级优秀率、不及格率情况";
			var courseTitle9 = document.getElementById("courseTitle9");
			courseTitle9.innerHTML = "微积分A（一）"+ "各班级优秀率、不及格率情况";
			
			var secondTermAddCourse1 = document.getElementById("secondTermAddCourse1");
			secondTermAddCourse1.style.display = "none";
			
			var secondTermAddCourse2 = document.getElementById("secondTermAddCourse2");
			secondTermAddCourse2.style.display = "none";
			
			var secondTermAddCourse3 = document.getElementById("secondTermAddCourse3");
			secondTermAddCourse3.style.display = "none";
				
		}
		else if(term1 == "2"){
			var firstTermAddCourse = document.getElementById("firstTermAddCourse");
			firstTermAddCourse.style.display = "none";
			
			var courseDepartmentTitle = document.getElementById("courseDepartmentTitle");
			courseDepartmentTitle.innerHTML = "工程实践A" + "成绩情况";
			var courseDepartmentTitle1 = document.getElementById("courseDepartmentTitle1");
			courseDepartmentTitle1.innerHTML = "机械设计基础B"+ "成绩情况";
			var courseDepartmentTitle2 = document.getElementById("courseDepartmentTitle2");
			id=courseDepartmentTitle2.innerHTML = "大学英语（四）"+ "成绩情况";
			var courseDepartmentTitle3 = document.getElementById("courseDepartmentTitle3");
			courseDepartmentTitle3.innerHTML = "大学英语（二）"+ "成绩情况";
			var courseDepartmentTitle4 = document.getElementById("courseDepartmentTitle4");
			courseDepartmentTitle4.innerHTML = "程序设计基础"+ "成绩情况";
			var courseDepartmentTitle5 = document.getElementById("courseDepartmentTitle5");
			courseDepartmentTitle5.innerHTML = "大学物理上"+ "成绩情况";
			var courseDepartmentTitle6 = document.getElementById("courseDepartmentTitle6");
			courseDepartmentTitle6.innerHTML = "概率论与数理统计"+ "成绩情况";
			var courseDepartmentTitle7 = document.getElementById("courseDepartmentTitle7");
			courseDepartmentTitle7.innerHTML = "微积分A（二）"+ "成绩情况";
			var courseDepartmentTitle8 = document.getElementById("courseDepartmentTitle8");
			courseDepartmentTitle8.innerHTML = "工程图学基础"+ "成绩情况";
			
			var courseTitle = document.getElementById("courseTitle");
			courseTitle.innerHTML = "大学英语（六）"+ "各班级优秀率、不及格率情况";
			var courseTitle1 = document.getElementById("courseTitle1");
			courseTitle1.innerHTML = "电子电路综合实验"+ "各班级优秀率、不及格率情况";
			var courseTitle2 = document.getElementById("courseTitle2");
			courseTitle2.innerHTML = "管理学B"+ "各班级优秀率、不及格率情况";
			var courseTitle3 = document.getElementById("courseTitle3");
			courseTitle3.innerHTML = "工程实践A"+ "各班级优秀率、不及格率情况";
			var courseTitle4 = document.getElementById("courseTitle4");
			courseTitle4.innerHTML = "机械设计基础B"+ "各班级优秀率、不及格率情况";
			var courseTitle5 = document.getElementById("courseTitle5");
			courseTitle5.innerHTML = "大学英语（四）"+ "各班级优秀率、不及格率情况";
			var courseTitle6 = document.getElementById("courseTitle6");
			courseTitle6.innerHTML = "模拟电子技术"+ "各班级优秀率、不及格率情况";
			var courseTitle7 = document.getElementById("courseTitle7");
			courseTitle7.innerHTML = "大学英语（二）"+ "各班级优秀率、不及格率情况";
			var courseTitle8 = document.getElementById("courseTitle8");
			courseTitle8.innerHTML = "程序设计基础"+ "各班级优秀率、不及格率情况";
			var courseTitle9 = document.getElementById("courseTitle9");
			courseTitle9.innerHTML = "大学物理上"+ "各班级优秀率、不及格率情况";
			var courseTitle10 = document.getElementById("courseTitle10");
			courseTitle10.innerHTML = "概率论与数理统计"+ "各班级优秀率、不及格率情况";
			var courseTitle11 = document.getElementById("courseTitle11");
			courseTitle11.innerHTML = "微积分A（二）"+ "各班级优秀率、不及格率情况";
			var courseTitle12 = document.getElementById("courseTitle12");
			courseTitle12.innerHTML = "工程图学基础"+ "各班级优秀率、不及格率情况";
			
		}
		
		/* 表格标题处理 */
 		var year1 = "${year}";
		var yearSelected = parseInt(year1.substring(0,4));
		
		var gradeList = new Array();
		for( var i=0; i<4; i++){
			gradeList[i] = (yearSelected - 3 + i).toString();
		}
		
  		var firstGradeTitle=document.getElementById("firstGradeDepartmentRPEC");  // 大四
  		firstGradeTitle.innerHTML= gradeList[0] + "级各院系必修、专业选修课程成绩情况";    
  		var secondGradeTitle=document.getElementById("secondGradeDepartmentRPEC");  // 大三
  		secondGradeTitle.innerHTML= gradeList[1] + "级各院系必修、专业选修课程成绩情况";     
  		var thirdGradeTitle=document.getElementById("thirdGradeDepartmentRPEC");  // 大二
  		thirdGradeTitle.innerHTML= gradeList[2] + "级各院系必修、专业选修课程成绩情况";    
  		var forthGradeTitle=document.getElementById("forthGradeDepartmentRPEC");  // 大一
  		forthGradeTitle.innerHTML= gradeList[3] + "级各院系必修、专业选修课程成绩情况";    
  		
  		var firstGradeClassTitle=document.getElementById("firstGradeRPECClass");  // 大四
  		firstGradeClassTitle.innerHTML= gradeList[0] + "级各班级优秀率、不及格率情况";       
  		var secondGradeClassTitle=document.getElementById("secondGradeRPECClass");  // 大三
  		secondGradeClassTitle.innerHTML= gradeList[1] + "级各班级优秀率、不及格率情况";    
  		var thirdGradeClassTitle=document.getElementById("thirdGradeRPECClass");  // 大二
  		thirdGradeClassTitle.innerHTML= gradeList[2] + "级各班级优秀率、不及格率情况";     
  		var forthGradeClassTitle=document.getElementById("forthGradeRPECClass");  // 大一
  		forthGradeClassTitle.innerHTML= gradeList[3] + "级各班级优秀率、不及格率情况";       
  		
  		
		
		$.blockUI.defaults.message = '<h1> 成绩数据正在加载中，请稍后... <img src="<%=path%>/pic/busy.gif" /></h1>';
		$(document).ajaxStart($.blockUI).ajaxStop($.unblockUI);
	    /* 处理表格分页搜索底部信息显示搜索方法栏 */
		$('#example1').dataTable({
	        bFilter: false,    //去掉搜索框
	        bLengthChange: false,   //去掉每页显示多少条数据方法
	        "paging" : false, //去掉底部分页框
	        "info": false, //去掉表格底部信息
	    });
	  
 	    getFirstPic();      //第一章第一个功能成绩分析图
		getSecondPic1();
		getSecondPic2();	//第一章第二个功能两张成绩分析图
		getThirdPic1();
		getThirdPic2();		//第二章第一个功能两张成绩分析图 
		getForthPic(); 
 		getFifthPic1();
		getFifthPic2();     //第三章第一个功能 
		getSixthPic();      //第三章第二个功能 
		getSeventhPic();    //第三章第三个功能  
 		getEighthPic();     //第四章第一个功能 
		getNinthPic();      //第四章第二个功能9张或10张分析图     

	});
	
	$(function() {
		$('#getPic').click(function(){
/* 			var newstr = document.getElementById("sss").innerHTML;
			var oldstr = document.body.innerHTML;
			document.body.innerHTML = newstr; */
			window.print();
/* 			document.body.innerHTML = oldstr; */
		});
	});
	
 	function getFirstPic(){
 		var year1 = "${year}";
		var term1 = "${term}";
		
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
		    color: ['#003366', '#006699', '#4cabce', '#e5323e',	'#000000'],
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
		};
		
			
		
			
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
				year : year1,
				term : term1
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
					if(odList[i].grade != "全校"){
						option.legend.data[i] = odList[i].grade + '级';
						option.series[i].name = odList[i].grade + '级';
					}
					else{
						option.legend.data[i] = odList[i].grade ; //得到年级号
						option.series[i].name = odList[i].grade ;
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
			
 	}
 	
 	function getSecondPic1(){
 		var year1 = "${year}";
		var term1 = "${term}";
 		var app = {};
		option2 = null;
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

		option2 = {
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
			year : year1,
			term : term1
		};
		$.post(url, args, function(odList){
			for(var i=0; i<odList.length ; i++){
				option2.legend.data[i] = odList[i].courseType;
				option2.series[i].name = odList[i].courseType;
				
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
			
			option2.series[0].data = firstRateList;
			option2.series[1].data = secondRateList;
			option2.series[2].data = thirdRateList;
			option2.series[3].data = forthRateList;
			
			var dom2 = document.getElementById("variousScRateBarPic");
			var myChart2 = echarts.init(dom2);
			if (option2 && typeof option2 === "object") {
			    myChart2.setOption(option2, true);
			}
		});
 	}
 	
 	function getSecondPic2(){
 		var year1 = "${year}";
		var term1 = "${term}";
 		var app1 = {};
		option3 = null;
		option3 = {
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
			year : year1,
			term : term1
		};
		$.post(url, args, function(odList){
			
			
			for(var i=0; i<odList.length ; i++){
				option3.legend.data[i] = odList[i].courseType;
				option3.series[i].name = odList[i].courseType;
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
			
			option3.series[0].data = firstRateList;
			option3.series[1].data = secondRateList;
			option3.series[2].data = thirdRateList;
			option3.series[3].data = forthRateList;
			
			var dom3 = document.getElementById("variousScRateLinePic");
			var myChart3 = echarts.init(dom3);
			if (option3 && typeof option3 === "object") {
			    myChart3.setOption(option3, true);
			}
		});
 	}
		 
 	function getThirdPic1(){
 		var app = {};
		option4 = null;
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

		option4 = {
		    color: ['#003366', '#006699', '#4cabce', '#e5323e','#000000'],
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
		        },
		        {
		            name: 'Wetland',
		            type: 'bar',
		            label: labelOption,
		            data: [98, 77, 101, 99, 40]
		        }
		    ]
		};;
		
		
		var year1 = "${year}";
		var term1 = "${term}";
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
		var fifthRateList = new Array();
		url = "getUniversityRPECScoreDistributedData";
		var args = {
			year : year1,
			term : term1
		};


		$.post(url, args, function(odList){
			for(var i = 0; i < odList.length ; i++){
				if(odList[i].grade != "全校"){
					option4.legend.data[i] = odList[i].grade + '级';
					option4.series[i].name = odList[i].grade + '级';
				}
				else{
					option4.legend.data[i] = odList[i].grade ; //得到年级号
					option4.series[i].name = odList[i].grade ;
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
			
			fifthRateList[0] = aRateList[4]; //得到整个学校的成绩百分比
			fifthRateList[1] = bRateList[4];
			fifthRateList[2] = cRateList[4];
			fifthRateList[3] = dRateList[4];
			fifthRateList[4] = eRateList[4];
			
			option4.series[0].data = firstRateList;
			option4.series[1].data = secondRateList;
			option4.series[2].data = thirdRateList;
			option4.series[3].data = forthRateList;
			option4.series[4].data = fifthRateList;
			
			var dom4 = document.getElementById("RPECScoreRateBarPic");
			var myChart4 = echarts.init(dom4);
			if (option4 && typeof option4 === "object") {
			    myChart4.setOption(option4, true);
			}
		});
 	}
 	
 	function getThirdPic2(){
 		var app5 = {};
		option5 = null;
		option5 = {
			color: ['#003366', '#006699', '#4cabce', '#e5323e','#000000'],
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
		        },
		        {
		            name:'直接访问',
		            type:'line',
		            stack: '总量5',
		            data:[32, 13, 30, 34, 30]
		        }
		    ]
		};
		;
		
		var year1 = "${year}";
		var term1 = "${term}";
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
		var fifthRateList = new Array();
		url = "getUniversityRPECScoreDistributedData";
		var args = {
			year : year1,
			term : term1
		};
		$.post(url, args, function(odList){
			
			
			for(var i=0; i<odList.length ; i++){
				if(odList[i].grade != "全校"){
					option5.legend.data[i] = odList[i].grade + '级';
					option5.series[i].name = odList[i].grade + '级';
				}
				else{
					option5.legend.data[i] = odList[i].grade ; //得到年级号
					option5.series[i].name = odList[i].grade ;
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
			
			fifthRateList[0] = aRateList[4]; //得到整个学校的成绩百分比
			fifthRateList[1] = bRateList[4];
			fifthRateList[2] = cRateList[4];
			fifthRateList[3] = dRateList[4];
			fifthRateList[4] = eRateList[4];
			
			option5.series[0].data = firstRateList;
			option5.series[1].data = secondRateList;
			option5.series[2].data = thirdRateList;
			option5.series[3].data = forthRateList;
			option5.series[4].data = fifthRateList;
			
			var dom5 = document.getElementById("RPECScoreRateLinePic");
			var myChart5 = echarts.init(dom5);
			if (option5 && typeof option5 === "object") {
			    myChart5.setOption(option5, true);
			}
		});
 	}
 	
 	function getForthPic(){
 		var app = {};
		option6 = null;
		option6 = {
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
		
		var year1 = "${year}";
		var term1 = "${term}";
		
		/* 与后台连接传递数据 */
		var aRateList = new Array();
		var bRateList = new Array();
		var cRateList = new Array();
		var dRateList = new Array();
		
		url1 = "getGradeListData";
		var args1 = {
			year : year1
		};
		$.post(url1, args1, function(gradeList){
			for(var i=0; i<gradeList.length ; i++){
				option6.legend.data[i] = gradeList[i];
				option6.series[i].name = gradeList[i];
			}
		}); 
		
		url = "getRPECDepartmentAllGradeAverageScoreCompareListData";
		var args = {
			year : year1,
			term : term1
		};
		$.post(url, args, function(dagascList){
			
			for(var i=0; i<dagascList.length ; i++){
				option6.xAxis.data[i] = dagascList[i].departmentName;
				var aNumber = parseFloat(dagascList[i].gradeFourDifference);
				var bNumber = parseFloat(dagascList[i].gradeThreeDifference);
				var cNumber = parseFloat(dagascList[i].gradeTwoDifference);
				var dNumber = parseFloat(dagascList[i].gradeOneDifference);
				aRateList[i] = aNumber;
				bRateList[i] = bNumber;
				cRateList[i] = cNumber;
				dRateList[i] = dNumber;
			}
			
			option6.series[0].data = aRateList;
			option6.series[1].data = bRateList;
			option6.series[2].data = cRateList;
			option6.series[3].data = dRateList;
			
			var dom6 = document.getElementById("RPECDepartmentAllGradeAverageScoreComparePic");
			var myChart6 = echarts.init(dom6);
			if (option6 && typeof option6 === "object") {
			    myChart6.setOption(option6, true);
			}
		
		});
		
 	}
 	
 	function getFifthPic1(){
 		var app = {};
		option7 = null;
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
		        formatter: '{name|{a}} {c}',
		        fontSize: 16,
		        rich: {
		            name: {
		                textBorderColor: '#fff'
		            }
		        }
		    }
		};

		option7 = {
		    color: ['#003366', '#006699', '#4cabce', '#e5323e','#000000'],
		    tooltip: {
		        trigger: 'axis',
		        axisPointer: {
		            type: 'shadow'
		        }
		    },
		    legend: {
		        data: ['1门不及格', '2门不及格', '3门不及格', '≥4门不及格']
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
		            name: '1门不及格',
		            type: 'bar',
		            barGap: 0,
		            label: labelOption,
		            data: [320, 332, 301, 334, 390]
		        },
		        {
		            name: '2门不及格',
		            type: 'bar',
		            label: labelOption,
		            data: [220, 182, 191, 234, 290]
		        },
		        {
		            name: '3门不及格',
		            type: 'bar',
		            label: labelOption,
		            data: [150, 232, 201, 154, 190]
		        },
		        {
		            name: '≥4门不及格',
		            type: 'bar',
		            label: labelOption,
		            data: [98, 77, 101, 99, 40]
		        }
		    ]
		};;
		
		var year1 = "${year}";
		var term1 = "${term}";

		/* 与后台连接传递数据 */
		var aList = new Array();
		var bList = new Array();
		var cList = new Array();
		var dList = new Array();
		url = "getRCGradeFailDistributionListData";
		var args = {
			year : year1,
			term : term1
		};

		$.post(url, args, function(gfdList){
			for(var i = 0; i < gfdList.length - 1 ; i++){
				option7.xAxis[0].data[i] = gfdList[i].grade + "级"; 
				/*分别得到每一个年级几门不及格的人数 */
				aList[i] = gfdList[i].oneFailNumber;
				bList[i] = gfdList[i].twoFailNumber;
				cList[i] = gfdList[i].threeFailNumber;
				dList[i] = gfdList[i].fourFailNumber + gfdList[i].fiveFailNumber + gfdList[i].sixFailNumber + gfdList[i].sevenFailNumber + gfdList[i].eightFailNumber;
			}
			
			
			option7.series[0].data = aList;
			option7.series[1].data = bList;
			option7.series[2].data = cList;
			option7.series[3].data = dList;
			
			var dom7 = document.getElementById("barPic");
			var myChart7 = echarts.init(dom7);
			if (option7 && typeof option7 === "object") {
			    myChart7.setOption(option7, true);
			}
		});
 	}
 	
 	function getFifthPic2(){
 		var app8 = {};
		option8 = null;
		option8 = {
			color: ['#003366', '#006699', '#4cabce', '#e5323e','#000000'],
		    title: {
		        text: ''
		    },
		    tooltip: {
		        trigger: 'axis'
		    },
		    legend: {
		        data:['不及格率']
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
		            name:'不及格率',
		            type:'line',
		            stack: '总量1',
		            data:[ 13, 10, 13, 9, 23]
		        }
		    ]
		};
		;
		
		var year1 = "${year}";
		var term1 = "${term}";
		/* 与后台连接传递数据 */
		var aRateList = new Array();
		url = "getRCGradeFailDistributionListData";
		var args = {
			year : year1,
			term : term1
		};
		$.post(url, args, function(gfdList){
			
			for(var i=0; i<gfdList.length ; i++){
				if(gfdList[i].grade != "合计"){
					option8.xAxis.data[i] = gfdList[i].grade + '级';
				}
				else{
					option8.xAxis.data[i] = "全校" ; //得到年级号
				} 
				aRateList[i] = parseFloat(gfdList[i].failRate.substring(0,gfdList[i].failRate.length-1));
			}
			option8.series[0].data = aRateList;
			
			var dom8 = document.getElementById("linePic");
			var myChart8 = echarts.init(dom8);
			if (option8 && typeof option8 === "object") {
			    myChart8.setOption(option8, true);
			}
		});
	
 	}
 	
 	function getSixthPic(){
 		var app = {};
		option9 = null;
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

		option9 = {
		    color: ['#003366', '#006699', '#4cabce', '#e5323e','#000000'],
		    tooltip: {
		        trigger: 'axis',
		        axisPointer: {
		            type: 'shadow'
		        }
		    },
		    legend: {
		        data: ['1门不及格', '2门不及格', '3门不及格', '≥4门不及格']
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
                        rotate:30
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
		            name: '1门不及格',
		            type: 'bar',
		            barGap: 0,
		            label: labelOption,
		            data: [320, 332, 301, 334, 390]
		        },
		        {
		            name: '2门不及格',
		            type: 'bar',
		            label: labelOption,
		            data: [220, 182, 191, 234, 290]
		        },
		        {
		            name: '3门不及格',
		            type: 'bar',
		            label: labelOption,
		            data: [150, 232, 201, 154, 190]
		        },
		        {
		            name: '≥4门不及格',
		            type: 'bar',
		            label: labelOption,
		            data: [98, 77, 101, 99, 40]
		        }
		    ]
		};;
		
		var year1 = "${year}";
		var term1 = "${term}";		

		/* 与后台连接传递数据 */
		var aRateList = new Array();
		var bRateList = new Array();
		var cRateList = new Array();
		var dRateList = new Array();
		url = "getRCDepartmentFailDistributionListData";
		var args = {
			year : year1,
			term : term1
		};


		$.post(url, args, function(dfdList){
			for(var i = 0; i < dfdList.length ; i++){
				option9.xAxis[0].data[i] = dfdList[i].departmentName;
				/*将后台传回来的百分比去掉百分号并转换为数字类型 */
				aRateList[i] = parseFloat(dfdList[i].oneFailRate.substring(0,dfdList[i].oneFailRate.length-1));
				bRateList[i] = parseFloat(dfdList[i].twoFailRate.substring(0,dfdList[i].twoFailRate.length-1));
				cRateList[i] = parseFloat(dfdList[i].threeFailRate.substring(0,dfdList[i].threeFailRate.length-1));
				dRateList[i] = parseFloat(dfdList[i].geFourFailRate.substring(0,dfdList[i].geFourFailRate.length-1));
			}
			
			option9.series[0].data = aRateList;
			option9.series[1].data = bRateList;
			option9.series[2].data = cRateList;
			option9.series[3].data = dRateList;
			
			var dom9 = document.getElementById("RCDepartmentFailDistributionListBarPic");
			var myChart9 = echarts.init(dom9);
			if (option9 && typeof option9 === "object") {
			    myChart9.setOption(option9, true);
			}
		});
 	}
 	
 	function getSeventhPic(){
 		var app = {};
		option10 = null;
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

		option10 = {
		    color: ['#003366', '#006699', '#4cabce', '#e5323e','#000000'],
		    tooltip: {
		        trigger: 'axis',
		        axisPointer: {
		            type: 'shadow'
		        }
		    },
		    legend: {
		        data: ['1门不及格', '2门不及格', '3门不及格', '≥4门不及格']
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
                        rotate:30
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
		            name: '1门不及格',
		            type: 'bar',
		            barGap: 0,
		            label: labelOption,
		            data: [320, 332, 301, 334, 390]
		        },
		        {
		            name: '2门不及格',
		            type: 'bar',
		            label: labelOption,
		            data: [220, 182, 191, 234, 290]
		        },
		        {
		            name: '3门不及格',
		            type: 'bar',
		            label: labelOption,
		            data: [150, 232, 201, 154, 190]
		        },
		        {
		            name: '≥4门不及格',
		            type: 'bar',
		            label: labelOption,
		            data: [98, 77, 101, 99, 40]
		        }
		    ]
		};;
		var year1 = "${year}";
		var term1 = "${term}";			

		/* 与后台连接传递数据 */
		var aRateList = new Array();
		var bRateList = new Array();
		var cRateList = new Array();
		var dRateList = new Array();
		
		url1 = "getGradeListData";
		var args1 = {
			year : year1
		};
		$.post(url1, args1, function(gradeList){
			for(var i=0; i<gradeList.length ; i++){
				option10.legend.data[i] = gradeList[i];
				option10.series[i].name = gradeList[i];
			}
		}); 
		url = "getRCDepartmentAllGradeFailDistributionListData";
		var args = {
			year : year1,
			term : term1
		};
		$.post(url, args, function(dagfdList){
			for(var i = 0; i < dagfdList.length ; i++){
				option10.xAxis[0].data[i] = dagfdList[i].departmentName;
				/*将后台传回来的百分比去掉百分号并转换为数字类型 */
				aRateList[i] = parseFloat(dagfdList[i].gradeFourFailRate.substring(0,dagfdList[i].gradeFourFailRate.length-1));
				bRateList[i] = parseFloat(dagfdList[i].gradeThreeFailRate.substring(0,dagfdList[i].gradeThreeFailRate.length-1));
				cRateList[i] = parseFloat(dagfdList[i].gradeTwoFailRate.substring(0,dagfdList[i].gradeTwoFailRate.length-1));
				dRateList[i] = parseFloat(dagfdList[i].gradeOneFailRate.substring(0,dagfdList[i].gradeOneFailRate.length-1));
			}
			
			option10.series[0].data = aRateList;
			option10.series[1].data = bRateList;
			option10.series[2].data = cRateList;
			option10.series[3].data = dRateList;
			
			var dom10 = document.getElementById("getRCDepartmentAllGradeFailDistributionListBarPic");
			var myChart10 = echarts.init(dom10);
			if (option10 && typeof option10 === "object") {
			    myChart10.setOption(option10, true);
			}
		});
 	}
 	
 	function getEighthPic(){
 		var app = {};
		option11 = null;
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

		option11 = {
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
		
		var year1 = "${year}";
		var term1 = "${term}";			

		/* 与后台连接传递数据 */
		var aRateList = new Array();
		var bRateList = new Array();
		url = "getBasicCourseOverallDistributionListData";
		var args = {
			year : year1,
			term : term1
		};

		$.post(url, args, function(bcodList){
			for(var i = 0; i < bcodList.length ; i++){
				option11.xAxis[0].data[i] = bcodList[i].courseName;
				/*将后台传回来的百分比去掉百分号并转换为数字类型 */
				aRateList[i] = parseFloat(bcodList[i].excellentRate.substring(0,bcodList[i].excellentRate.length-1));
				bRateList[i] = parseFloat(bcodList[i].failRate.substring(0,bcodList[i].failRate.length-1));
			}
			
			option11.series[0].data = aRateList;
			option11.series[1].data = bRateList;
			
			var dom11 = document.getElementById("basicCourseOverallDistributionListBarPic");
			var myChart11 = echarts.init(dom11);
			if (option11 && typeof option11 === "object") {
			    myChart11.setOption(option11, true);
			}
		});
		
 	}
 	
 	function getNinthPic(){
 		var app = {};
		optionCourse = null;
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

		optionCourse = {
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
		
		var courseList = new Array();
		var year1 = "${year}";
		var term1 = "${term}";	
		if (term1 == "1"){
			courseList.push("大学英语（三）");
			courseList.push("大学物理下A");
			courseList.push("大学物理下B");
			courseList.push("大学物理实验（二）");
			courseList.push("复变函数与积分变换");
			courseList.push("大学英语（一）");
			courseList.push("大学计算机基础A");
			courseList.push("普通化学");
			courseList.push("线性代数与解析几何A");
			courseList.push("微积分A（一）");
		}
		else{
			courseList.push("工程实践A");
			courseList.push("机械设计基础B");
			courseList.push("大学英语（四）");
			courseList.push("大学英语（二）");
			courseList.push("程序设计基础");
			courseList.push("大学物理上");
			courseList.push("概率论与数理统计");
			courseList.push("微积分A（二）");
			courseList.push("工程图学基础");
		}
		
		/* 与后台连接传递数据 */
		var aRateList = new Array();
		var bRateList = new Array();
		url = "getBasicCourseDetailDistributionListByCourseNameData";
		var args = {
			year : year1,
			term : term1,
			courseName : courseList[0]
		};

		$.post(url, args, function(bcddList){
			for(var i = 0; i < bcddList.length ; i++){
				optionCourse.xAxis[0].data[i] = bcddList[i].departmentName;
				/*将后台传回来的百分比去掉百分号并转换为数字类型 */
				aRateList[i] = parseFloat(bcddList[i].excellentRate.substring(0,bcddList[i].excellentRate.length-1));
				bRateList[i] = parseFloat(bcddList[i].failRate.substring(0,bcddList[i].failRate.length-1));
			}
			
			optionCourse.series[0].data = aRateList;
			optionCourse.series[1].data = bRateList;
			
			var dom12 = document.getElementById("basicCourseDetailDistributionListByCourseNameBarPic0");
			var myChart12 = echarts.init(dom12);
			if (optionCourse && typeof optionCourse === "object") {
			    myChart12.setOption(optionCourse, true);
			}
		});
		
		/* 与后台连接传递数据 */
		args = {
			year : year1,
			term : term1,
			courseName : courseList[1]
		};

		$.post(url, args, function(bcddList){
			for(var i = 0; i < bcddList.length ; i++){
				optionCourse.xAxis[0].data[i] = bcddList[i].departmentName;
				/*将后台传回来的百分比去掉百分号并转换为数字类型 */
				aRateList[i] = parseFloat(bcddList[i].excellentRate.substring(0,bcddList[i].excellentRate.length-1));
				bRateList[i] = parseFloat(bcddList[i].failRate.substring(0,bcddList[i].failRate.length-1));
			}
			
			optionCourse.series[0].data = aRateList;
			optionCourse.series[1].data = bRateList;
			
			var dom13 = document.getElementById("basicCourseDetailDistributionListByCourseNameBarPic1");
			var myChart13 = echarts.init(dom13);
			if (optionCourse && typeof optionCourse === "object") {
			    myChart13.setOption(optionCourse, true);
			}
		});
		
		args = {
				year : year1,
				term : term1,
				courseName : courseList[2]
			};

			$.post(url, args, function(bcddList){
				for(var i = 0; i < bcddList.length ; i++){
					optionCourse.xAxis[0].data[i] = bcddList[i].departmentName;
					/*将后台传回来的百分比去掉百分号并转换为数字类型 */
					aRateList[i] = parseFloat(bcddList[i].excellentRate.substring(0,bcddList[i].excellentRate.length-1));
					bRateList[i] = parseFloat(bcddList[i].failRate.substring(0,bcddList[i].failRate.length-1));
				}
				
				optionCourse.series[0].data = aRateList;
				optionCourse.series[1].data = bRateList;
				
				var dom14 = document.getElementById("basicCourseDetailDistributionListByCourseNameBarPic2");
				var myChart14 = echarts.init(dom14);
				if (optionCourse && typeof optionCourse === "object") {
				    myChart14.setOption(optionCourse, true);
				}
			});
			
		args = {
				year : year1,
				term : term1,
				courseName : courseList[3]
			};

			$.post(url, args, function(bcddList){
				for(var i = 0; i < bcddList.length ; i++){
					optionCourse.xAxis[0].data[i] = bcddList[i].departmentName;
					/*将后台传回来的百分比去掉百分号并转换为数字类型 */
					aRateList[i] = parseFloat(bcddList[i].excellentRate.substring(0,bcddList[i].excellentRate.length-1));
					bRateList[i] = parseFloat(bcddList[i].failRate.substring(0,bcddList[i].failRate.length-1));
				}
				
				optionCourse.series[0].data = aRateList;
				optionCourse.series[1].data = bRateList;
				
				var dom15 = document.getElementById("basicCourseDetailDistributionListByCourseNameBarPic3");
				var myChart15 = echarts.init(dom15);
				if (optionCourse && typeof optionCourse === "object") {
				    myChart15.setOption(optionCourse, true);
				}
			});

		args = {
				year : year1,
				term : term1,
				courseName : courseList[4]
			};

			$.post(url, args, function(bcddList){
				for(var i = 0; i < bcddList.length ; i++){
					optionCourse.xAxis[0].data[i] = bcddList[i].departmentName;
					/*将后台传回来的百分比去掉百分号并转换为数字类型 */
					aRateList[i] = parseFloat(bcddList[i].excellentRate.substring(0,bcddList[i].excellentRate.length-1));
					bRateList[i] = parseFloat(bcddList[i].failRate.substring(0,bcddList[i].failRate.length-1));
				}
				
				optionCourse.series[0].data = aRateList;
				optionCourse.series[1].data = bRateList;
				
				var dom16 = document.getElementById("basicCourseDetailDistributionListByCourseNameBarPic4");
				var myChart16 = echarts.init(dom16);
				if (optionCourse && typeof optionCourse === "object") {
				    myChart16.setOption(optionCourse, true);
				}
			});
			
		args = {
				year : year1,
				term : term1,
				courseName : courseList[5]
			};

			$.post(url, args, function(bcddList){
				for(var i = 0; i < bcddList.length ; i++){
					optionCourse.xAxis[0].data[i] = bcddList[i].departmentName;
					/*将后台传回来的百分比去掉百分号并转换为数字类型 */
					aRateList[i] = parseFloat(bcddList[i].excellentRate.substring(0,bcddList[i].excellentRate.length-1));
					bRateList[i] = parseFloat(bcddList[i].failRate.substring(0,bcddList[i].failRate.length-1));
				}
				
				optionCourse.series[0].data = aRateList;
				optionCourse.series[1].data = bRateList;
				
				var dom17 = document.getElementById("basicCourseDetailDistributionListByCourseNameBarPic5");
				var myChart17 = echarts.init(dom17);
				if (optionCourse && typeof optionCourse === "object") {
				    myChart17.setOption(optionCourse, true);
				}
			});
			
		args = {
				year : year1,
				term : term1,
				courseName : courseList[6]
			};

			$.post(url, args, function(bcddList){
				for(var i = 0; i < bcddList.length ; i++){
					optionCourse.xAxis[0].data[i] = bcddList[i].departmentName;
					/*将后台传回来的百分比去掉百分号并转换为数字类型 */
					aRateList[i] = parseFloat(bcddList[i].excellentRate.substring(0,bcddList[i].excellentRate.length-1));
					bRateList[i] = parseFloat(bcddList[i].failRate.substring(0,bcddList[i].failRate.length-1));
				}
				
				optionCourse.series[0].data = aRateList;
				optionCourse.series[1].data = bRateList;
				
				var dom18 = document.getElementById("basicCourseDetailDistributionListByCourseNameBarPic6");
				var myChart18 = echarts.init(dom18);
				if (optionCourse && typeof optionCourse === "object") {
				    myChart18.setOption(optionCourse, true);
				}
			});
				
			
		args = {
				year : year1,
				term : term1,
				courseName : courseList[7]
			};

			$.post(url, args, function(bcddList){
				for(var i = 0; i < bcddList.length ; i++){
					optionCourse.xAxis[0].data[i] = bcddList[i].departmentName;
					/*将后台传回来的百分比去掉百分号并转换为数字类型 */
					aRateList[i] = parseFloat(bcddList[i].excellentRate.substring(0,bcddList[i].excellentRate.length-1));
					bRateList[i] = parseFloat(bcddList[i].failRate.substring(0,bcddList[i].failRate.length-1));
				}
				
				optionCourse.series[0].data = aRateList;
				optionCourse.series[1].data = bRateList;
				
				var dom19 = document.getElementById("basicCourseDetailDistributionListByCourseNameBarPic7");
				var myChart19 = echarts.init(dom19);
				if (optionCourse && typeof optionCourse === "object") {
				    myChart19.setOption(optionCourse, true);
				}
			});
			
		args = {
				year : year1,
				term : term1,
				courseName : courseList[8]
			};

			$.post(url, args, function(bcddList){
				for(var i = 0; i < bcddList.length ; i++){
					optionCourse.xAxis[0].data[i] = bcddList[i].departmentName;
					/*将后台传回来的百分比去掉百分号并转换为数字类型 */
					aRateList[i] = parseFloat(bcddList[i].excellentRate.substring(0,bcddList[i].excellentRate.length-1));
					bRateList[i] = parseFloat(bcddList[i].failRate.substring(0,bcddList[i].failRate.length-1));
				}
				
				optionCourse.series[0].data = aRateList;
				optionCourse.series[1].data = bRateList;
				
				var dom20 = document.getElementById("basicCourseDetailDistributionListByCourseNameBarPic8");
				var myChart20 = echarts.init(dom20);
				if (optionCourse && typeof optionCourse === "object") {
				    myChart20.setOption(optionCourse, true);
				}
			});
			
		if(courseList[9] != null){
			
			args = {
					year : year1,
					term : term1,
					courseName : courseList[9]
				};

				$.post(url, args, function(bcddList){
					for(var i = 0; i < bcddList.length ; i++){
						optionCourse.xAxis[0].data[i] = bcddList[i].departmentName;
						/*将后台传回来的百分比去掉百分号并转换为数字类型 */
						aRateList[i] = parseFloat(bcddList[i].excellentRate.substring(0,bcddList[i].excellentRate.length-1));
						bRateList[i] = parseFloat(bcddList[i].failRate.substring(0,bcddList[i].failRate.length-1));
					}
					
					optionCourse.series[0].data = aRateList;
					optionCourse.series[1].data = bRateList;
					
					var dom21 = document.getElementById("basicCourseDetailDistributionListByCourseNameBarPic9");
					var myChart21 = echarts.init(dom21);
					if (optionCourse && typeof optionCourse === "object") {
					    myChart21.setOption(optionCourse, true);
					}
				});
			
		}
				
 	}
 	
		
		

</script>



</head>
<body class="hold-transition sidebar-mini" >
	<div class="wrapper">
		<%@include file="/menu.jsp"%>
		<!-- Content Wrapper. Contains page content -->
		<div class="content-wrapper">
			<div class="container-fluid">
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

			<div id = "sss" class="row">
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

			<div class="row">
				<div class="col-12">
					<div class="card">
						<div id="scNumberPic"  
							style="display:block; height: 400%; width: 70%;  position:relative; left:-50px;">
						</div>
						<div id="scRatePic"    
							style="display:block; height: 400%; width: 70%;  position:relative; left:-50px;">
						</div>
					</div>
				</div>
			</div>

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
			
			<div class="row">
				<div class="col-12">
					<div class="card">
						<div id="variousScRateBarPic"  style="display:block; height: 400%; width:70%; position:relative; left:-50px;">
						</div>
						<div id="variousScRateLinePic"  style="display:block;  height: 400%; width:70%; position:relative; left:-50px;">
						</div>
					</div>
				</div>
			</div>
			
			
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
									<c:forEach items="${odList2}" var="OverallDistribution">
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
			
			<div class="row">
				<div class="col-12">
					<div class="card">
						<div id="RPECScoreRateBarPic"  style="display:block; height: 400%; width:70%; position:relative; left:-50px;">
						</div>
						<div id="RPECScoreRateLinePic"  style="display:block;  height: 400%; width:70%; position:relative; left:-50px;">
						</div>
					</div>
				</div>
			</div>
			
			<div id = "table1" class="row">
				<div class="col-12">
					<div class="card">
						<div class="card-header">
							<h3 id = "firstGradeDepartmentRPEC" class="card-title">大四各院系必修、专业选修课程成绩情况</h3> 
						</div>
						<!-- /.card-header -->
						<div class="card-body" style="margin: 0">
							<table id="example1" class="table table-bordered table-striped">
								<thead>
									<tr>
										<th>序号</th> 
										<th>院系</th>
										<th>成绩记录总数</th>
										<th>平均分</th>
										<th>优秀率</th>
										<th>良好率</th>
										<th>中等率</th>
										<th>及格率</th>
										<th>不及格率</th>
									</tr>
								</thead>
								<tbody>
									<c:forEach items="${ddList }"  var="DepartmentDistribution">
										<tr>
											<td>${DepartmentDistribution.id }</td>
											<td>${DepartmentDistribution.departmentName }</td>
											<td>${DepartmentDistribution.totalNumber }</td>
											<td>${DepartmentDistribution.averageScore }</td>
											<td>${DepartmentDistribution.excellentRate }</td>
											<td>${DepartmentDistribution.goodRate }</td>
											<td>${DepartmentDistribution.mediumRate }</td>
											<td>${DepartmentDistribution.passRate }</td>
											<td>${DepartmentDistribution.failRate }</td>
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
			
			<div id = "table1" class="row">
				<div class="col-12">
					<div class="card">
						<div class="card-header">
							<h3 id = "secondGradeDepartmentRPEC" class="card-title">大三各院系必修、专业选修课程成绩情况</h3> 
						</div>
						<!-- /.card-header -->
						<div class="card-body" style="margin: 0">
							<table id="example1" class="table table-bordered table-striped">
								<thead>
									<tr>
										<th>序号</th> 
										<th>院系</th>
										<th>成绩记录总数</th>
										<th>平均分</th>
										<th>优秀率</th>
										<th>良好率</th>
										<th>中等率</th>
										<th>及格率</th>
										<th>不及格率</th>
									</tr>
								</thead>
								<tbody>
									<c:forEach items="${ddList1 }"  var="DepartmentDistribution">
										<tr>
											<td>${DepartmentDistribution.id }</td>
											<td>${DepartmentDistribution.departmentName }</td>
											<td>${DepartmentDistribution.totalNumber }</td>
											<td>${DepartmentDistribution.averageScore }</td>
											<td>${DepartmentDistribution.excellentRate }</td>
											<td>${DepartmentDistribution.goodRate }</td>
											<td>${DepartmentDistribution.mediumRate }</td>
											<td>${DepartmentDistribution.passRate }</td>
											<td>${DepartmentDistribution.failRate }</td>
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
			
			<div id = "table1" class="row">
				<div class="col-12">
					<div class="card">
						<div class="card-header">
							<h3 id = "thirdGradeDepartmentRPEC" class="card-title">大二各院系必修、专业选修课程成绩情况</h3> 
						</div>
						<!-- /.card-header -->
						<div class="card-body" style="margin: 0">
							<table id="example1" class="table table-bordered table-striped">
								<thead>
									<tr>
										<th>序号</th> 
										<th>院系</th>
										<th>成绩记录总数</th>
										<th>平均分</th>
										<th>优秀率</th>
										<th>良好率</th>
										<th>中等率</th>
										<th>及格率</th>
										<th>不及格率</th>
									</tr>
								</thead>
								<tbody>
									<c:forEach items="${ddList2 }"  var="DepartmentDistribution">
										<tr>
											<td>${DepartmentDistribution.id }</td>
											<td>${DepartmentDistribution.departmentName }</td>
											<td>${DepartmentDistribution.totalNumber }</td>
											<td>${DepartmentDistribution.averageScore }</td>
											<td>${DepartmentDistribution.excellentRate }</td>
											<td>${DepartmentDistribution.goodRate }</td>
											<td>${DepartmentDistribution.mediumRate }</td>
											<td>${DepartmentDistribution.passRate }</td>
											<td>${DepartmentDistribution.failRate }</td>
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
			
			<div id = "table1" class="row">
				<div class="col-12">
					<div class="card">
						<div class="card-header">
							<h3 id = "forthGradeDepartmentRPEC" class="card-title">大一各院系必修、专业选修课程成绩情况</h3> 
						</div>
						<!-- /.card-header -->
						<div class="card-body" style="margin: 0">
							<table id="example1" class="table table-bordered table-striped">
								<thead>
									<tr>
										<th>序号</th> 
										<th>院系</th>
										<th>成绩记录总数</th>
										<th>平均分</th>
										<th>优秀率</th>
										<th>良好率</th>
										<th>中等率</th>
										<th>及格率</th>
										<th>不及格率</th>
									</tr>
								</thead>
								<tbody>
									<c:forEach items="${ddList3 }"  var="DepartmentDistribution">
										<tr>
											<td>${DepartmentDistribution.id }</td>
											<td>${DepartmentDistribution.departmentName }</td>
											<td>${DepartmentDistribution.totalNumber }</td>
											<td>${DepartmentDistribution.averageScore }</td>
											<td>${DepartmentDistribution.excellentRate }</td>
											<td>${DepartmentDistribution.goodRate }</td>
											<td>${DepartmentDistribution.mediumRate }</td>
											<td>${DepartmentDistribution.passRate }</td>
											<td>${DepartmentDistribution.failRate }</td>
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

			<div class="row">
				<div class="col-12">
					<div class="card">
						<div id="RPECDepartmentAllGradeAverageScoreComparePic"
							style="display:block;  height: 400%; width:70%; position:relative; left:-50px;">
						</div>
					</div>
				</div>
			</div>
			
			<div class="row">
				<div class="col-12">
					<div class="card">
						<div class="card-header">
							<h3 class="card-title">全校本科生不及格整体情况</h3> 
						</div>
						<!-- /.card-header -->
						<div class="card-body" style="margin: 0">
							<table id="example1" class="table table-bordered table-striped">
								<thead>
									<tr>
										<th rowspan="2"><center>年级</center></th>
										<th colspan="8"><center>不及格学生情况（人数）</center></th>
										<th rowspan="2"><center>不及格人数合计</center></th>
										<th rowspan="2"><center>年级人数</center></th>
										<th rowspan="2"><center>学生不及格率</center></th>
									</tr>
									<tr>
										<td>1门不及格</td>
										<td>2门不及格</td>
										<td>3门不及格</td>
										<td>4门不及格</td>
										<td>5门不及格</td>
										<td>6门不及格</td>
										<td>7门不及格</td>
										<td>8门不及格</td>
									</tr>
								</thead>
								<tbody>
									<c:forEach items="${gfdList}" var="GradeFailDistribution">
										<tr>
											<td>${GradeFailDistribution.grade }</td>
											<td>${GradeFailDistribution.oneFailNumber }</td>
											<td>${GradeFailDistribution.twoFailNumber }</td>
											<td>${GradeFailDistribution.threeFailNumber }</td>
											<td>${GradeFailDistribution.fourFailNumber }</td>
											<td>${GradeFailDistribution.fiveFailNumber }</td>
											<td>${GradeFailDistribution.sixFailNumber }</td>
											<td>${GradeFailDistribution.sevenFailNumber }</td>
											<td>${GradeFailDistribution.eightFailNumber }</td>
											<td>${GradeFailDistribution.totalFailNumber }</td>
											<td>${GradeFailDistribution.totalStudentNumber }</td>
											<td>${GradeFailDistribution.failRate }</td>
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

			<div class="row">
				<div class="col-12">
					<div class="card">
						<div id="barPic"  style="display:block;  height: 400%; width:70%; position:relative; left:-50px;">
						</div>
						<div id="linePic"  style="display:block;  height: 400%; width:70%; position:relative; left:-50px;">
						</div>
					</div>
				</div>
			</div>
			
			<div class="row">
				<div class="col-12">
					<div class="card">
						<div class="card-header">
							<h3 class="card-title">各院系不及格学生整体情况分布</h3> 
						</div>
						<!-- /.card-header -->
						<div class="card-body" style="margin: 0">
							<table id="example1" class="table table-bordered table-striped">
								<thead>
									<tr>
										<th rowspan="2"><center>序号</center></th>
										<th rowspan="2"><center>院系</center></th>
										<th rowspan="2"><center>参与统计学生总数</center></th>
										<th colspan="5"><center>不及格学生情况</center></th>
										<th rowspan="2"><center>学生不及格率</center></th>
											
									</tr>
									<tr>
										<td>1门不及格人数</td>
										<td>2门不及格人数</td>
										<td>3门不及格人数</td>
										<td>≥4门不及格人数</td>
										<td>不及格总人数</td>
									</tr>
								</thead>
								<tbody>
									<c:forEach items="${dfdList}" var="DepartmentFailDistribution">
										<tr>
											<td>${DepartmentFailDistribution.id }</td>
											<td>${DepartmentFailDistribution.departmentName }</td>
											<td>${DepartmentFailDistribution.totalStudentNumber }</td>
											<td>${DepartmentFailDistribution.oneFailNumber }</td>
											<td>${DepartmentFailDistribution.twoFailNumber }</td>
											<td>${DepartmentFailDistribution.threeFailNumber }</td>
											<td>${DepartmentFailDistribution.geFourFailNumber }</td>
											<td>${DepartmentFailDistribution.totalFailNumber }</td>
											<td>${DepartmentFailDistribution.totalFailRate }</td>
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
			
			<div class="row">
				<div class="col-12">
					<div class="card">
						<div id="RCDepartmentFailDistributionListBarPic"  style="display:block;  height: 400%; width:70%; position:relative; left:-50px;">
						</div>
					</div>
				</div>
			</div>
			
			<div class="row">
				<div class="col-12">
					<div class="card">
						<div class="card-header">
							<h3 class="card-title">各院系分年级学生不及格情况统计分布</h3> 
						</div>
						<!-- /.card-header -->
						<div class="card-body" style="margin: 0">
							<table id="example1" class="table table-bordered table-striped">
								<thead>
									<tr>
										<th rowspan="2"><center>序号</center></th>
										<th rowspan="2"><center>院系</center></th>
										<c:forEach items="${gradeListString}" var="keyword" varStatus="id">
										<th colspan="3"><center>${keyword}</center></th>
										</c:forEach>
											
									</tr>
									<tr>
										<td>学生数</td>
										<td>不及格数</td>
										<td>不及格率</td>
										
										<td>学生数</td>
										<td>不及格数</td>
										<td>不及格率</td>
										
										<td>学生数</td>
										<td>不及格数</td>
										<td>不及格率</td>
										
										<td>学生数</td>
										<td>不及格数</td>
										<td>不及格率</td>
									</tr>
								</thead>
								<tbody>
									<c:forEach items="${dagfdList}" var="DepartmentAllGradeFailDistribution">
										<tr>
											<td>${DepartmentAllGradeFailDistribution.id }</td>
											<td>${DepartmentAllGradeFailDistribution.departmentName }</td>
											<td>${DepartmentAllGradeFailDistribution.gradeFourStudentNumber }</td>
											<td>${DepartmentAllGradeFailDistribution.gradeFourFailNumber }</td>
											<td>${DepartmentAllGradeFailDistribution.gradeFourFailRate }</td>
											<td>${DepartmentAllGradeFailDistribution.gradeThreeStudentNumber }</td>
											<td>${DepartmentAllGradeFailDistribution.gradeThreeFailNumber }</td>
											<td>${DepartmentAllGradeFailDistribution.gradeThreeFailRate }</td>
											<td>${DepartmentAllGradeFailDistribution.gradeTwoStudentNumber }</td>
											<td>${DepartmentAllGradeFailDistribution.gradeTwoFailNumber }</td>
											<td>${DepartmentAllGradeFailDistribution.gradeTwoFailRate }</td>
											<td>${DepartmentAllGradeFailDistribution.gradeOneStudentNumber }</td>
											<td>${DepartmentAllGradeFailDistribution.gradeOneFailNumber }</td>
											<td>${DepartmentAllGradeFailDistribution.gradeOneFailRate }</td>
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
			
			<div class="row">
				<div class="col-12">
					<div class="card">
						<div id="getRCDepartmentAllGradeFailDistributionListBarPic"  style="display:block;  height: 400%; width:70%; position:relative; left:-50px;">
						</div>
					</div>
				</div>
			</div>
			
			<div class="row">
				<div class="col-12">
					<div class="card">
						<div class="card-header">
							<h3 class="card-title">各年级缺考情况统计</h3> 
						</div>
						<!-- /.card-header -->
						<div class="card-body" style="margin: 0">
							<table id="example1" class="table table-bordered table-striped" >
								<thead>
									<tr>
										<th>年级</th>
										<th>缺考总数</th>
										<th>必修</th>
										<th>专业选修</th>
										<th>通识教育</th>
									</tr>
								</thead>
								<tbody>
									<c:forEach items="${gadList }" var="GradeAbsenceDistribution">
										<tr>
											<td>${GradeAbsenceDistribution.grade }</td>
											<td>${GradeAbsenceDistribution.totalAbsenceNumber }</td>
											<td>${GradeAbsenceDistribution.rcAbsenceNumber }</td>
											<td>${GradeAbsenceDistribution.pecAbsenceNumber }</td>
											<td>${GradeAbsenceDistribution.gecAbsenceNumber }</td>
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
			
			<div class="row">
				<div class="col-12">
					<div class="card">
						<div class="card-header">
							<h3 class="card-title">主要基础课程成绩情况分布</h3> 
						</div>
						<!-- /.card-header -->
						<div class="card-body" style="margin: 0">
							<table id="example1" class="table table-bordered table-striped">
								<thead>
									<tr>
										<th><center>序号</center></th>
										<th><center>年级</center></th>
										<th><center>课程名称</center></th>
										<th><center>成绩总数</center></th>
										<th><center>优秀数</center></th>
										<th><center>优秀率</center></th>
										<th><center>不及格数</center></th>
										<th><center>不及格率</center></th>
										<th><center>平均分</center></th>
											
									</tr>
								</thead>
								<tbody>
									<c:forEach items="${bcodList}" var="BasicCourseOverallDistribution">
										<tr>
											<td>${BasicCourseOverallDistribution.id }</td>
											<td>${BasicCourseOverallDistribution.grade }</td>
											<td>${BasicCourseOverallDistribution.courseName }</td>
											<td>${BasicCourseOverallDistribution.totalNumber }</td>
											<td>${BasicCourseOverallDistribution.excellentNumber }</td>
											<td>${BasicCourseOverallDistribution.excellentRate }</td>
											<td>${BasicCourseOverallDistribution.failNumber }</td>
											<td>${BasicCourseOverallDistribution.failRate }</td>
											<td>${BasicCourseOverallDistribution.averageScore }</td>
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
			
			<div class="row">
				<div class="col-12">
					<div class="card">
						<div id="basicCourseOverallDistributionListBarPic"  style="display:block;  height: 400%; width:70%; position:relative; left:-50px;">
						</div>
					</div>
				</div>
			</div>
			
			<div class="row">
				<div class="col-12">
					<div class="card">
						<div class="card-header"> 
							<h3 id="courseDepartmentTitle" class="card-title" id = "head">主要基础课程成绩情况统计分布</h3> 
						</div>
						<!-- /.card-header -->
						<div class="card-body" style="margin: 0">
							<table id="example1" class="table table-bordered table-striped">
								<thead>
									<tr>
										<th><center>序号</center></th>
										<th><center>院系</center></th>
										<th><center>成绩数</center></th>
										<th><center>优秀人数</center></th>
										<th><center>优秀率</center></th> 
										<th><center>不及格人数</center></th>
										<th><center>不及格率</center></th>
									</tr>
								</thead>
								<tbody>
									<c:forEach items="${bcddList0}" var="BasicCourseDetailDistribution">
										<tr>
											<td>${BasicCourseDetailDistribution.id }</td>
											<td>${BasicCourseDetailDistribution.departmentName }</td>
											<td>${BasicCourseDetailDistribution.totalStudentNumber }</td>
											<td>${BasicCourseDetailDistribution.excellentNumber }</td>
											<td>${BasicCourseDetailDistribution.excellentRate }</td>
											<td>${BasicCourseDetailDistribution.failNumber }</td>
											<td>${BasicCourseDetailDistribution.failRate }</td>
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
			
			<div class="row">
				<div class="col-12">
					<div class="card">
						<div id="basicCourseDetailDistributionListByCourseNameBarPic0"  style="display:block;  height: 400%; width:70%; position:relative; left:-50px;">
						</div>
					</div>
				</div>
			</div>
			
			<div class="row">
				<div class="col-12">
					<div class="card">
						<div class="card-header"> 
							<h3 id="courseDepartmentTitle1" class="card-title" id = "head">主要基础课程成绩情况统计分布</h3> 
						</div>
						<!-- /.card-header -->
						<div class="card-body" style="margin: 0">
							<table id="example1" class="table table-bordered table-striped">
								<thead>
									<tr>
										<th><center>序号</center></th>
										<th><center>院系</center></th>
										<th><center>成绩数</center></th>
										<th><center>优秀人数</center></th>
										<th><center>优秀率</center></th> 
										<th><center>不及格人数</center></th>
										<th><center>不及格率</center></th>
									</tr>
								</thead>
								<tbody>
									<c:forEach items="${bcddList1}" var="BasicCourseDetailDistribution">
										<tr>
											<td>${BasicCourseDetailDistribution.id }</td>
											<td>${BasicCourseDetailDistribution.departmentName }</td>
											<td>${BasicCourseDetailDistribution.totalStudentNumber }</td>
											<td>${BasicCourseDetailDistribution.excellentNumber }</td>
											<td>${BasicCourseDetailDistribution.excellentRate }</td>
											<td>${BasicCourseDetailDistribution.failNumber }</td>
											<td>${BasicCourseDetailDistribution.failRate }</td>
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
			
			<div class="row">
				<div class="col-12">
					<div class="card">
						<div id="basicCourseDetailDistributionListByCourseNameBarPic1"  style="display:block;  height: 400%; width:70%; position:relative; left:-50px;">
						</div>
					</div>
				</div>
			</div>			
			
			<div class="row">
				<div class="col-12">
					<div class="card">
						<div class="card-header"> 
							<h3 id="courseDepartmentTitle2" class="card-title" id = "head">主要基础课程成绩情况统计分布</h3> 
						</div>
						<!-- /.card-header -->
						<div class="card-body" style="margin: 0">
							<table id="example1" class="table table-bordered table-striped">
								<thead>
									<tr>
										<th><center>序号</center></th>
										<th><center>院系</center></th>
										<th><center>成绩数</center></th>
										<th><center>优秀人数</center></th>
										<th><center>优秀率</center></th> 
										<th><center>不及格人数</center></th>
										<th><center>不及格率</center></th>
									</tr>
								</thead>
								<tbody>
									<c:forEach items="${bcddList2}" var="BasicCourseDetailDistribution">
										<tr>
											<td>${BasicCourseDetailDistribution.id }</td>
											<td>${BasicCourseDetailDistribution.departmentName }</td>
											<td>${BasicCourseDetailDistribution.totalStudentNumber }</td>
											<td>${BasicCourseDetailDistribution.excellentNumber }</td>
											<td>${BasicCourseDetailDistribution.excellentRate }</td>
											<td>${BasicCourseDetailDistribution.failNumber }</td>
											<td>${BasicCourseDetailDistribution.failRate }</td>
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
			
			<div class="row">
				<div class="col-12">
					<div class="card">
						<div id="basicCourseDetailDistributionListByCourseNameBarPic2"  style="display:block;  height: 400%; width:70%; position:relative; left:-50px;">
						</div>
					</div>
				</div>
			</div>			
			
			<div class="row">
				<div class="col-12">
					<div class="card">
						<div class="card-header"> 
							<h3 id="courseDepartmentTitle3" class="card-title" id = "head">主要基础课程成绩情况统计分布</h3> 
						</div>
						<!-- /.card-header -->
						<div class="card-body" style="margin: 0">
							<table id="example1" class="table table-bordered table-striped">
								<thead>
									<tr>
										<th><center>序号</center></th>
										<th><center>院系</center></th>
										<th><center>成绩数</center></th>
										<th><center>优秀人数</center></th>
										<th><center>优秀率</center></th> 
										<th><center>不及格人数</center></th>
										<th><center>不及格率</center></th>
									</tr>
								</thead>
								<tbody>
									<c:forEach items="${bcddList3}" var="BasicCourseDetailDistribution">
										<tr>
											<td>${BasicCourseDetailDistribution.id }</td>
											<td>${BasicCourseDetailDistribution.departmentName }</td>
											<td>${BasicCourseDetailDistribution.totalStudentNumber }</td>
											<td>${BasicCourseDetailDistribution.excellentNumber }</td>
											<td>${BasicCourseDetailDistribution.excellentRate }</td>
											<td>${BasicCourseDetailDistribution.failNumber }</td>
											<td>${BasicCourseDetailDistribution.failRate }</td>
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
			
			<div class="row">
				<div class="col-12">
					<div class="card">
						<div id="basicCourseDetailDistributionListByCourseNameBarPic3"  style="display:block;  height: 400%; width:70%; position:relative; left:-50px;">
						</div>
					</div>
				</div>
			</div>
			
			<div class="row">
				<div class="col-12">
					<div class="card">
						<div class="card-header"> 
							<h3 id="courseDepartmentTitle4" class="card-title" id = "head">主要基础课程成绩情况统计分布</h3> 
						</div>
						<!-- /.card-header -->
						<div class="card-body" style="margin: 0">
							<table id="example1" class="table table-bordered table-striped">
								<thead>
									<tr>
										<th><center>序号</center></th>
										<th><center>院系</center></th>
										<th><center>成绩数</center></th>
										<th><center>优秀人数</center></th>
										<th><center>优秀率</center></th> 
										<th><center>不及格人数</center></th>
										<th><center>不及格率</center></th>
									</tr>
								</thead>
								<tbody>
									<c:forEach items="${bcddList4}" var="BasicCourseDetailDistribution">
										<tr>
											<td>${BasicCourseDetailDistribution.id }</td>
											<td>${BasicCourseDetailDistribution.departmentName }</td>
											<td>${BasicCourseDetailDistribution.totalStudentNumber }</td>
											<td>${BasicCourseDetailDistribution.excellentNumber }</td>
											<td>${BasicCourseDetailDistribution.excellentRate }</td>
											<td>${BasicCourseDetailDistribution.failNumber }</td>
											<td>${BasicCourseDetailDistribution.failRate }</td>
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
			
			<div class="row">
				<div class="col-12">
					<div class="card">
						<div id="basicCourseDetailDistributionListByCourseNameBarPic4"  style="display:block;  height: 400%; width:70%; position:relative; left:-50px;">
						</div>
					</div>
				</div>
			</div>
			
			<div class="row">
				<div class="col-12">
					<div class="card">
						<div class="card-header"> 
							<h3 id="courseDepartmentTitle5" class="card-title" id = "head">主要基础课程成绩情况统计分布</h3> 
						</div>
						<!-- /.card-header -->
						<div class="card-body" style="margin: 0">
							<table id="example1" class="table table-bordered table-striped">
								<thead>
									<tr>
										<th><center>序号</center></th>
										<th><center>院系</center></th>
										<th><center>成绩数</center></th>
										<th><center>优秀人数</center></th>
										<th><center>优秀率</center></th> 
										<th><center>不及格人数</center></th>
										<th><center>不及格率</center></th>
									</tr>
								</thead>
								<tbody>
									<c:forEach items="${bcddList5}" var="BasicCourseDetailDistribution">
										<tr>
											<td>${BasicCourseDetailDistribution.id }</td>
											<td>${BasicCourseDetailDistribution.departmentName }</td>
											<td>${BasicCourseDetailDistribution.totalStudentNumber }</td>
											<td>${BasicCourseDetailDistribution.excellentNumber }</td>
											<td>${BasicCourseDetailDistribution.excellentRate }</td>
											<td>${BasicCourseDetailDistribution.failNumber }</td>
											<td>${BasicCourseDetailDistribution.failRate }</td>
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
			
			<div class="row">
				<div class="col-12">
					<div class="card">
						<div id="basicCourseDetailDistributionListByCourseNameBarPic5"  style="display:block;  height: 400%; width:70%; position:relative; left:-50px;">
						</div>
					</div>
				</div>
			</div>
			
			<div class="row">
				<div class="col-12">
					<div class="card">
						<div class="card-header"> 
							<h3 id="courseDepartmentTitle6" class="card-title" id = "head">主要基础课程成绩情况统计分布</h3> 
						</div>
						<!-- /.card-header -->
						<div class="card-body" style="margin: 0">
							<table id="example1" class="table table-bordered table-striped">
								<thead>
									<tr>
										<th><center>序号</center></th>
										<th><center>院系</center></th>
										<th><center>成绩数</center></th>
										<th><center>优秀人数</center></th>
										<th><center>优秀率</center></th> 
										<th><center>不及格人数</center></th>
										<th><center>不及格率</center></th>
									</tr>
								</thead>
								<tbody>
									<c:forEach items="${bcddList6}" var="BasicCourseDetailDistribution">
										<tr>
											<td>${BasicCourseDetailDistribution.id }</td>
											<td>${BasicCourseDetailDistribution.departmentName }</td>
											<td>${BasicCourseDetailDistribution.totalStudentNumber }</td>
											<td>${BasicCourseDetailDistribution.excellentNumber }</td>
											<td>${BasicCourseDetailDistribution.excellentRate }</td>
											<td>${BasicCourseDetailDistribution.failNumber }</td>
											<td>${BasicCourseDetailDistribution.failRate }</td>
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
			
			<div class="row">
				<div class="col-12">
					<div class="card">
						<div id="basicCourseDetailDistributionListByCourseNameBarPic6"  style="display:block;  height: 400%; width:70%; position:relative; left:-50px;">
						</div>
					</div>
				</div>
			</div>
			
			<div class="row">
				<div class="col-12">
					<div class="card">
						<div class="card-header"> 
							<h3 id="courseDepartmentTitle7" class="card-title" id = "head">主要基础课程成绩情况统计分布</h3> 
						</div>
						<!-- /.card-header -->
						<div class="card-body" style="margin: 0">
							<table id="example1" class="table table-bordered table-striped">
								<thead>
									<tr>
										<th><center>序号</center></th>
										<th><center>院系</center></th>
										<th><center>成绩数</center></th>
										<th><center>优秀人数</center></th>
										<th><center>优秀率</center></th> 
										<th><center>不及格人数</center></th>
										<th><center>不及格率</center></th>
									</tr>
								</thead>
								<tbody>
									<c:forEach items="${bcddList7}" var="BasicCourseDetailDistribution">
										<tr>
											<td>${BasicCourseDetailDistribution.id }</td>
											<td>${BasicCourseDetailDistribution.departmentName }</td>
											<td>${BasicCourseDetailDistribution.totalStudentNumber }</td>
											<td>${BasicCourseDetailDistribution.excellentNumber }</td>
											<td>${BasicCourseDetailDistribution.excellentRate }</td>
											<td>${BasicCourseDetailDistribution.failNumber }</td>
											<td>${BasicCourseDetailDistribution.failRate }</td>
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
			
		    <div class="row">
				<div class="col-12">
					<div class="card">
						<div id="basicCourseDetailDistributionListByCourseNameBarPic7"  style="display:block;  height: 400%; width:70%; position:relative; left:-50px;">
						</div>
					</div>
				</div>
			</div>
			
			<div class="row">
				<div class="col-12">
					<div class="card">
						<div class="card-header"> 
							<h3 id="courseDepartmentTitle8" class="card-title" id = "head">主要基础课程成绩情况统计分布</h3> 
						</div>
						<!-- /.card-header -->
						<div class="card-body" style="margin: 0">
							<table id="example1" class="table table-bordered table-striped">
								<thead>
									<tr>
										<th><center>序号</center></th>
										<th><center>院系</center></th>
										<th><center>成绩数</center></th>
										<th><center>优秀人数</center></th>
										<th><center>优秀率</center></th> 
										<th><center>不及格人数</center></th>
										<th><center>不及格率</center></th>
									</tr>
								</thead>
								<tbody>
									<c:forEach items="${bcddList8}" var="BasicCourseDetailDistribution">
										<tr>
											<td>${BasicCourseDetailDistribution.id }</td>
											<td>${BasicCourseDetailDistribution.departmentName }</td>
											<td>${BasicCourseDetailDistribution.totalStudentNumber }</td>
											<td>${BasicCourseDetailDistribution.excellentNumber }</td>
											<td>${BasicCourseDetailDistribution.excellentRate }</td>
											<td>${BasicCourseDetailDistribution.failNumber }</td>
											<td>${BasicCourseDetailDistribution.failRate }</td>
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
			
		    <div class="row">
				<div class="col-12">
					<div class="card">
						<div id="basicCourseDetailDistributionListByCourseNameBarPic8"  style="display:block;  height: 400%; width:70%; position:relative; left:-50px;">
						</div>
					</div>
				</div>
			</div>
			
			<div id = "firstTermAddCourse" class="row">
				<div class="col-12">
					<div class="card">
						<div class="card-header"> 
							<h3 id="courseDepartmentTitle9" class="card-title" id = "head">主要基础课程成绩情况统计分布</h3> 
						</div>
						<!-- /.card-header -->
						<div class="card-body" style="margin: 0">
							<table id="example1" class="table table-bordered table-striped">
								<thead>
									<tr>
										<th><center>序号</center></th>
										<th><center>院系</center></th>
										<th><center>成绩数</center></th>
										<th><center>优秀人数</center></th>
										<th><center>优秀率</center></th> 
										<th><center>不及格人数</center></th>
										<th><center>不及格率</center></th>
									</tr>
								</thead>
								<tbody>
									<c:forEach items="${bcddList9}" var="BasicCourseDetailDistribution">
										<tr>
											<td>${BasicCourseDetailDistribution.id }</td>
											<td>${BasicCourseDetailDistribution.departmentName }</td>
											<td>${BasicCourseDetailDistribution.totalStudentNumber }</td>
											<td>${BasicCourseDetailDistribution.excellentNumber }</td>
											<td>${BasicCourseDetailDistribution.excellentRate }</td>
											<td>${BasicCourseDetailDistribution.failNumber }</td>
											<td>${BasicCourseDetailDistribution.failRate }</td>
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
			
	        <div class="row">
				<div class="col-12">
					<div class="card">
						<div id="basicCourseDetailDistributionListByCourseNameBarPic9"  style="display:block;  height: 400%; width:70%; position:relative; left:-50px;">
						</div>
					</div>
				</div>
			</div>
			
			<div class="row">
				<div class="col-12">
					<div class="card">
						<div class="card-header">
							<h3  id = "firstGradeRPECClass" class="card-title">所有课程成绩分布（按班级）</h3> 
						</div>
						<!-- /.card-header -->
						<div class="card-body" style="margin: 0">
							<table id="example1" class="table table-bordered table-striped">
								<thead>
									<tr>
										<th>序号</th>  
										<th>班级</th>
										<th>成绩记录总数</th>
										<th>优秀成绩数</th>
										<th>不及格成绩数</th>
										<th>优秀率</th>
										<th>不及格率</th>
									</tr>
								</thead>
								<tbody>
									<c:forEach items="${cefdList }"  var="ClassExcellentFailDistribution">
										<tr>
											<td>${ClassExcellentFailDistribution.id }</td>
											<td>${ClassExcellentFailDistribution.classNumber }</td>
											<td>${ClassExcellentFailDistribution.totalNumber }</td>
											<td>${ClassExcellentFailDistribution.excellentNumber }</td>
											<td>${ClassExcellentFailDistribution.failNumber }</td>
											<td>${ClassExcellentFailDistribution.excellentRate }</td>
											<td>${ClassExcellentFailDistribution.failRate }</td>
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
			
			<div class="row">
				<div class="col-12">
					<div class="card">
						<div class="card-header">
							<h3 id="secondGradeRPECClass" class="card-title">所有课程成绩分布（按班级）</h3> 
						</div>
						<!-- /.card-header -->
						<div class="card-body" style="margin: 0">
							<table id="example1" class="table table-bordered table-striped">
								<thead>
									<tr>
										<th>序号</th>  
										<th>班级</th>
										<th>成绩记录总数</th>
										<th>优秀成绩数</th>
										<th>不及格成绩数</th>
										<th>优秀率</th>
										<th>不及格率</th>
									</tr>
								</thead>
								<tbody>
									<c:forEach items="${cefdList1 }"  var="ClassExcellentFailDistribution">
										<tr>
											<td>${ClassExcellentFailDistribution.id }</td>
											<td>${ClassExcellentFailDistribution.classNumber }</td>
											<td>${ClassExcellentFailDistribution.totalNumber }</td>
											<td>${ClassExcellentFailDistribution.excellentNumber }</td>
											<td>${ClassExcellentFailDistribution.failNumber }</td>
											<td>${ClassExcellentFailDistribution.excellentRate }</td>
											<td>${ClassExcellentFailDistribution.failRate }</td>
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
			
			<div class="row">
				<div class="col-12">
					<div class="card">
						<div class="card-header">
							<h3  id = "thirdGradeRPECClass" class="card-title">所有课程成绩分布（按班级）</h3> 
						</div>
						<!-- /.card-header -->
						<div class="card-body" style="margin: 0">
							<table id="example1" class="table table-bordered table-striped">
								<thead>
									<tr>
										<th>序号</th>  
										<th>班级</th>
										<th>成绩记录总数</th>
										<th>优秀成绩数</th>
										<th>不及格成绩数</th>
										<th>优秀率</th>
										<th>不及格率</th>
									</tr>
								</thead>
								<tbody>
									<c:forEach items="${cefdList2 }"  var="ClassExcellentFailDistribution">
										<tr>
											<td>${ClassExcellentFailDistribution.id }</td>
											<td>${ClassExcellentFailDistribution.classNumber }</td>
											<td>${ClassExcellentFailDistribution.totalNumber }</td>
											<td>${ClassExcellentFailDistribution.excellentNumber }</td>
											<td>${ClassExcellentFailDistribution.failNumber }</td>
											<td>${ClassExcellentFailDistribution.excellentRate }</td>
											<td>${ClassExcellentFailDistribution.failRate }</td>
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
			
			<div id = "table1" class="row">
				<div class="col-12">
					<div class="card">
						<div class="card-header">
							<h3 id = "forthGradeRPECClass" class="card-title">所有课程成绩分布（按班级）</h3> 
						</div>
						<!-- /.card-header -->
						<div class="card-body" style="margin: 0">
							<table id="example1" class="table table-bordered table-striped">
								<thead>
									<tr>
										<th>序号</th>  
										<th>班级</th>
										<th>成绩记录总数</th>
										<th>优秀成绩数</th>
										<th>不及格成绩数</th>
										<th>优秀率</th>
										<th>不及格率</th>
									</tr>
								</thead>
								<tbody>
									<c:forEach items="${cefdList3 }"  var="ClassExcellentFailDistribution">
										<tr>
											<td>${ClassExcellentFailDistribution.id }</td>
											<td>${ClassExcellentFailDistribution.classNumber }</td>
											<td>${ClassExcellentFailDistribution.totalNumber }</td>
											<td>${ClassExcellentFailDistribution.excellentNumber }</td>
											<td>${ClassExcellentFailDistribution.failNumber }</td>
											<td>${ClassExcellentFailDistribution.excellentRate }</td>
											<td>${ClassExcellentFailDistribution.failRate }</td>
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
			
			<div class="row">
				<div class="col-12">
					<div class="card">
						<div class="card-header">
							<h3  class="card-title" id = "courseTitle">主要基础课程各班成绩情况统计分布</h3> 
						</div>
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
					</div>
					<!-- /.card -->
				</div>
				<!-- /.col -->
			</div>
			<!-- /.row --> 
			
			<div class="row">
				<div class="col-12">
					<div class="card">
						<div class="card-header">
							<h3 class="card-title" id = "courseTitle1">主要基础课程各班成绩情况统计分布</h3> 
						</div>
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
					</div>
					<!-- /.card -->
				</div>
				<!-- /.col -->
			</div>
			<!-- /.row --> 
			
			<div class="row">
				<div class="col-12">
					<div class="card">
						<div class="card-header">
							<h3 class="card-title" id = "courseTitle2">主要基础课程各班成绩情况统计分布</h3> 
						</div>
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
					</div>
					<!-- /.card -->
				</div>
				<!-- /.col -->
			</div>
			<!-- /.row --> 
			
			<div class="row">
				<div class="col-12">
					<div class="card">
						<div class="card-header">
							<h3 class="card-title" id = "courseTitle3">主要基础课程各班成绩情况统计分布</h3> 
						</div>
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
					</div>
					<!-- /.card -->
				</div>
				<!-- /.col -->
			</div>
			<!-- /.row --> 
			
			<div class="row">
				<div class="col-12">
					<div class="card">
						<div class="card-header">
							<h3 class="card-title" id = "courseTitle4">主要基础课程各班成绩情况统计分布</h3> 
						</div>
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
					</div>
					<!-- /.card -->
				</div>
				<!-- /.col -->
			</div>
			<!-- /.row --> 
			
			<div class="row">
				<div class="col-12">
					<div class="card">
						<div class="card-header">
							<h3 class="card-title" id = "courseTitle5">主要基础课程各班成绩情况统计分布</h3> 
						</div>
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
					</div>
					<!-- /.card -->
				</div>
				<!-- /.col -->
			</div>
			<!-- /.row --> 
			
			<div class="row">
				<div class="col-12">
					<div class="card">
						<div class="card-header">
							<h3 class="card-title" id = "courseTitle6">主要基础课程各班成绩情况统计分布</h3> 
						</div>
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
					</div>
					<!-- /.card -->
				</div>
				<!-- /.col -->
			</div>
			<!-- /.row --> 
			
			<div class="row">
				<div class="col-12">
					<div class="card">
						<div class="card-header">
							<h3 class="card-title" id = "courseTitle7">主要基础课程各班成绩情况统计分布</h3> 
						</div>
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
					</div>
					<!-- /.card -->
				</div>
				<!-- /.col -->
			</div>
			<!-- /.row --> 
			
			<div class="row">
				<div class="col-12">
					<div class="card">
						<div class="card-header">
							<h3 class="card-title" id = "courseTitle8">主要基础课程各班成绩情况统计分布</h3> 
						</div>
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
					</div>
					<!-- /.card -->
				</div>
				<!-- /.col -->
			</div>
			<!-- /.row --> 
			
			<div class="row">
				<div class="col-12">
					<div class="card">
						<div class="card-header">
							<h3 class="card-title" id = "courseTitle9">主要基础课程各班成绩情况统计分布</h3> 
						</div>
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
					</div>
					<!-- /.card -->
				</div>
				<!-- /.col -->
			</div>
			<!-- /.row --> 
			
			<div id = "secondTermAddCourse1" class="row">
				<div class="col-12">
					<div class="card">
						<div class="card-header">
							<h3 class="card-title" id = "courseTitle10">主要基础课程各班成绩情况统计分布</h3> 
						</div>
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
					</div>
					<!-- /.card -->
				</div>
				<!-- /.col -->
			</div>
			<!-- /.row --> 
			
			<div id = "secondTermAddCourse2" class="row">
				<div class="col-12">
					<div class="card">
						<div class="card-header">
							<h3 class="card-title" id = "courseTitle11">主要基础课程各班成绩情况统计分布</h3> 
						</div>
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
					</div>
					<!-- /.card -->
				</div>
				<!-- /.col -->
			</div>
			<!-- /.row --> 
			
			<div id = "secondTermAddCourse3" class="row">
				<div class="col-12">
					<div class="card">
						<div class="card-header">
							<h3 class="card-title" id = "courseTitle12">主要基础课程各班成绩情况统计分布</h3> 
						</div>
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
					</div>
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