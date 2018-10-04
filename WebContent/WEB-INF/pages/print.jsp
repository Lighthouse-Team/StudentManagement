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
<title>&nbsp;&nbsp;&nbsp;</title>

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
 		var term1 = "${term}";
		var yearSelected = parseInt(year1.substring(0,4));
		
		var gradeList = new Array();
		for( var i=0; i<4; i++){
			gradeList[i] = (yearSelected - 3 + i).toString();
		}
		
		/* 具体的表的标题设置 */
		var tableTitle1=document.getElementById("table1.1");
		tableTitle1.innerHTML="表1.1&nbsp;&nbsp;各年级" +  year1 + "学年第" + term1 + "学期所有课程成绩分布情况";
		var tableTitle2=document.getElementById("table1.2");
		tableTitle2.innerHTML="表1.2&nbsp;&nbsp;" +  year1 + "学年第" + term1 + "学期各类课程成绩分布情况";
		var tableTitle3=document.getElementById("table2.1");
		tableTitle3.innerHTML="表2.1&nbsp;&nbsp;我校本科生" +  year1 + "学年第" + term1 + "必修、专业选修课程成绩分布情况";
		var tableTitle4=document.getElementById("table2.2");
		tableTitle4.innerHTML="表2.2&nbsp;&nbsp;" +  gradeList[0] + "级各院系必修、专业选修成绩统计表";
		var tableTitle5=document.getElementById("table2.3");
		tableTitle5.innerHTML="表2.3&nbsp;&nbsp;" +  gradeList[1] + "级各院系必修、专业选修成绩统计表";
		var tableTitle6=document.getElementById("table2.4");
		tableTitle6.innerHTML="表2.4&nbsp;&nbsp;" +  gradeList[2] + "级各院系必修、专业选修成绩统计表";
		var tableTitle6=document.getElementById("table2.5");
		tableTitle6.innerHTML="表2.5&nbsp;&nbsp;" +  gradeList[3] + "级各院系必修、专业选修成绩统计表";
		
		/* 具体的图的标题设置 */
		var picTitle1=document.getElementById("pic1");
		picTitle1.innerHTML="图1.1-1.2&nbsp;&nbsp;" +  year1 + "学年第" + term1 + "学期本科生成绩整体分布情况图";
		var picTitle2=document.getElementById("pic2");
		picTitle2.innerHTML="图1.3-1.4&nbsp;&nbsp;" +  year1 + "学年第" + term1 + "学期各类课程成绩分布情况图";
		var picTitle3=document.getElementById("pic3");
		picTitle3.innerHTML="图2.1-2.2&nbsp;&nbsp;" +  year1 + "学年第" + term1 + "学期各院系必修、专业选修课程成绩分布图";
		var picTitle4=document.getElementById("pic4");
		picTitle4.innerHTML="图2.3&nbsp;&nbsp;" +  year1 + "学年第" + term1 + "学期各院系分年级必修、专业选修平均分对比";
		
		
  		var RPECTitle1=document.getElementById("RPECTitle1");  // 大四
  		RPECTitle1.innerHTML="2.2.1&nbsp;&nbsp;" +  gradeList[0] + "级本科生必修、专业选修课程成绩情况";
  		var RPECTitle2=document.getElementById("RPECTitle2");  // 大三
  		RPECTitle2.innerHTML="2.2.2&nbsp;&nbsp;" +  gradeList[1] + "级本科生必修、专业选修课程成绩情况"; 
  		var RPECTitle3=document.getElementById("RPECTitle3");  // 大二
  		RPECTitle3.innerHTML="2.2.3&nbsp;&nbsp;" +  gradeList[2] + "级本科生必修、专业选修课程成绩情况"; 
  		var RPECTitle4=document.getElementById("RPECTitle4");  // 大一
  		RPECTitle4.innerHTML="2.2.4&nbsp;&nbsp;" +  gradeList[3] + "级本科生必修、专业选修课程成绩情况"; 
		
  		var firstGradeTitle=document.getElementById("firstGradeDepartmentRPEC");  // 大四
  		firstGradeTitle.innerHTML="2.2.1.1&nbsp;&nbsp;" +  gradeList[0] + "级各院系必修、专业选修课程成绩情况";    
  		var secondGradeTitle=document.getElementById("secondGradeDepartmentRPEC");  // 大三
  		secondGradeTitle.innerHTML="2.2.2.1&nbsp;&nbsp;" +   gradeList[1] + "级各院系必修、专业选修课程成绩情况";     
  		var thirdGradeTitle=document.getElementById("thirdGradeDepartmentRPEC");  // 大二
  		thirdGradeTitle.innerHTML="2.2.3.1&nbsp;&nbsp;" +   gradeList[2] + "级各院系必修、专业选修课程成绩情况";    
  		var forthGradeTitle=document.getElementById("forthGradeDepartmentRPEC");  // 大一
  		forthGradeTitle.innerHTML="2.2.4.1&nbsp;&nbsp;" +   gradeList[3] + "级各院系必修、专业选修课程成绩情况";    
  		
  		var classRPECTitle1=document.getElementById("classRPECTitle1");  // 大四
  		classRPECTitle1.innerHTML="2.2.1.2&nbsp;&nbsp;" +  gradeList[0] + "级各班级必修、专业选修课程成绩情况";
  		var classRPECTitle2=document.getElementById("classRPECTitle2");  // 大三
  		classRPECTitle2.innerHTML="2.2.2.2&nbsp;&nbsp;" +  gradeList[1] + "级各班级必修、专业选修课程成绩情况"; 
  		var classRPECTitle3=document.getElementById("classRPECTitle3");  // 大二
  		classRPECTitle3.innerHTML="2.2.3.2&nbsp;&nbsp;" +  gradeList[2] + "级各班级必修、专业选修课程成绩情况"; 
  		var classRPECTitle4=document.getElementById("classRPECTitle4");  // 大一
  		classRPECTitle4.innerHTML="2.2.4.2&nbsp;&nbsp;" +  gradeList[3] + "级各班级必修、专业选修课程成绩情况"; 
		
  		var termCourseTitle1 = document.getElementById("termCourseTitle1");
  		termCourseTitle1.innerHTML = "4.2.1&nbsp;&nbsp;" + gradeList[2] + "级主要基础课程成绩情况";
  		
		/* 基础课程上学期和下学期不一样 判断处理 */
		if(term1 == "1"){
			
			document.getElementById("ssj").style.display = "none";
			document.getElementById("secondTermAddDiv").style.display = "none";
			document.getElementById("secondTermAddDiv2").style.display = "none";
			document.getElementById("secondTermAddDiv3").style.display = "none";
			document.getElementById("secondTermAddDiv4").style.display = "none";
			
			var firstTermCourseTitle2 = document.getElementById("firstTermCourseTitle2");
			firstTermCourseTitle2.innerHTML = "4.2.2&nbsp;&nbsp;" + gradeList[3] + "级主要基础课程成绩情况";
			document.getElementById("secondTermCourseTitle2").style.display="none";
			
			var basicCourseClass = document.getElementById("basicCourseClass");
			basicCourseClass.innerHTML = "&nbsp;&nbsp;&nbsp;&nbsp;详见附表9-附表18。";
			
			var courseDepartmentTitle = document.getElementById("courseDepartmentTitle");
			courseDepartmentTitle.innerHTML = "4.2.1.1&nbsp;&nbsp;大学英语（三）成绩情况";
			var courseDepartmentTitle1 = document.getElementById("courseDepartmentTitle1");
			courseDepartmentTitle1.innerHTML = "4.2.1.2&nbsp;&nbsp;大学物理下A成绩情况";
			var courseDepartmentTitle2 = document.getElementById("courseDepartmentTitle2");
			id=courseDepartmentTitle2.innerHTML = "4.2.1.3&nbsp;&nbsp;大学物理下B成绩情况";
			var courseDepartmentTitle3 = document.getElementById("courseDepartmentTitle3");
			courseDepartmentTitle3.innerHTML = "4.2.1.4&nbsp;&nbsp;大学物理实验（二）成绩情况";
			var courseDepartmentTitle4 = document.getElementById("courseDepartmentTitle4");
			courseDepartmentTitle4.innerHTML = "4.2.1.5&nbsp;&nbsp;复变函数与积分变换成绩情况";
			var courseDepartmentTitle5 = document.getElementById("courseDepartmentTitle5");
			courseDepartmentTitle5.innerHTML = "4.2.2.1&nbsp;&nbsp;大学英语（一）成绩情况";
			var courseDepartmentTitle6 = document.getElementById("courseDepartmentTitle6");
			courseDepartmentTitle6.innerHTML = "4.2.2.2&nbsp;&nbsp;大学计算机基础A成绩情况";
			var courseDepartmentTitle7 = document.getElementById("courseDepartmentTitle7");
			courseDepartmentTitle7.innerHTML = "4.2.2.3&nbsp;&nbsp;普通化学成绩情况";
			var courseDepartmentTitle8 = document.getElementById("courseDepartmentTitle8");
			courseDepartmentTitle8.innerHTML = "4.2.2.4&nbsp;&nbsp;线性代数与解析几何A成绩情况";
			var courseDepartmentTitle9 = document.getElementById("courseDepartmentTitle9");
			courseDepartmentTitle9.innerHTML = "4.2.2.5&nbsp;&nbsp;微积分A（一）成绩情况";
			
			var tableTitle7=document.getElementById("table4.2");
			tableTitle7.innerHTML="表4.2&nbsp;&nbsp;大学英语（三）成绩情况";
			var tableTitle8=document.getElementById("table4.3");
			tableTitle8.innerHTML="表4.3&nbsp;&nbsp;大学物理下A成绩情况";
			var tableTitle9=document.getElementById("table4.4");
			tableTitle9.innerHTML="表4.4&nbsp;&nbsp;大学物理下B成绩情况";
			var tableTitle10=document.getElementById("table4.5");
			tableTitle10.innerHTML="表4.5&nbsp;&nbsp;大学物理实验（二）成绩情况";
			var tableTitle11=document.getElementById("table4.6");
			tableTitle11.innerHTML="表4.6&nbsp;&nbsp;复变函数与积分变换成绩情况";
			var tableTitle12=document.getElementById("table4.7");
			tableTitle12.innerHTML="表4.7&nbsp;&nbsp;大学英语（一）成绩情况";
			var tableTitle13=document.getElementById("table4.8");
			tableTitle13.innerHTML="表4.8&nbsp;&nbsp;大学计算机基础A成绩情况";
			var tableTitle14=document.getElementById("table4.9");
			tableTitle14.innerHTML="表4.9&nbsp;&nbsp;普通化学成绩情况";
			var tableTitle15=document.getElementById("table4.10");
			tableTitle15.innerHTML="表4.10&nbsp;&nbsp;线性代数与解析几何A成绩情况";
			var tableTitle16=document.getElementById("table4.11");
			tableTitle16.innerHTML="表4.11&nbsp;&nbsp;微积分A（一）成绩情况";
			
			var pic9=document.getElementById("pic9");
			pic9.innerHTML="图4.2&nbsp;&nbsp;各院系大学英语（三）成绩情况";
			var pic10=document.getElementById("pic10");
			pic10.innerHTML="图4.3&nbsp;&nbsp;各院系大学物理下A成绩情况";
			var pic11=document.getElementById("pic11");
			pic11.innerHTML="图4.4&nbsp;&nbsp;各院系大学物理下B成绩情况";
			var pic12=document.getElementById("pic12");
			pic12.innerHTML="图4.5&nbsp;&nbsp;各院系大学物理实验（二）成绩情况";
			var pic13=document.getElementById("pic13");
			pic13.innerHTML="图4.6&nbsp;&nbsp;各院系复变函数与积分变换成绩情况";
			var pic14=document.getElementById("pic14");
			pic14.innerHTML="图4.7&nbsp;&nbsp;各院系大学英语（一）成绩情况";
			var pic15=document.getElementById("pic15");
			pic15.innerHTML="图4.8&nbsp;&nbsp;各院系大学计算机基础A成绩情况";
			var pic16=document.getElementById("pic16");
			pic16.innerHTML="图4.9&nbsp;&nbsp;各院系普通化学成绩情况";
			var pic17=document.getElementById("pic17");
			pic17.innerHTML="图4.10&nbsp;&nbsp;各院系线性代数与解析几何A成绩情况";
			var pic18=document.getElementById("pic18");
			pic18.innerHTML="图4.11&nbsp;&nbsp;各院系微积分A（一）成绩情况";	
		}
		else if(term1 == "2"){
			
			document.getElementById("analysisFirstTermAdd").style.display = "none";
			document.getElementById("zlq").style.display = "none";
			document.getElementById("firstTermAddDiv").style.display = "none";
			document.getElementById("firstTermAddDiv2").style.display = "none";
			document.getElementById("firstTermAddDiv3").style.display = "none";
			document.getElementById("firstTermAddDiv4").style.display = "none";
			document.getElementById("firstTermAddDiv5").style.display = "none";
			
			var secondTermCourseTitle2 = document.getElementById("secondTermCourseTitle2");
			secondTermCourseTitle2.innerHTML = "4.2.2&nbsp;&nbsp;" + gradeList[3] + "级主要基础课程成绩情况";
			document.getElementById("firstTermCourseTitle2").style.display="none";
			
			var basicCourseClass = document.getElementById("basicCourseClass");
			basicCourseClass.innerHTML = "&nbsp;&nbsp;&nbsp;&nbsp;详见附表9-附表21。";
			
 			var firstTermAddCourse = document.getElementById("firstTermAddCourse");
			firstTermAddCourse.style.display = "none"; 
			
			var courseDepartmentTitle = document.getElementById("courseDepartmentTitle");
			courseDepartmentTitle.innerHTML = "4.2.1.1&nbsp;&nbsp;工程实践A成绩情况";
			var courseDepartmentTitle1 = document.getElementById("courseDepartmentTitle1");
			courseDepartmentTitle1.innerHTML = "4.2.1.2&nbsp;&nbsp;机械设计基础B成绩情况";
			var courseDepartmentTitle2 = document.getElementById("courseDepartmentTitle2");
			id=courseDepartmentTitle2.innerHTML = "4.2.1.3&nbsp;&nbsp;大学英语（四）成绩情况";
			var courseDepartmentTitle3 = document.getElementById("courseDepartmentTitle3");
			courseDepartmentTitle3.innerHTML = "4.2.2.1&nbsp;&nbsp;大学英语（二）成绩情况";
			var courseDepartmentTitle4 = document.getElementById("courseDepartmentTitle4");
			courseDepartmentTitle4.innerHTML = "4.2.2.2&nbsp;&nbsp;程序设计基础成绩情况";
			var courseDepartmentTitle5 = document.getElementById("courseDepartmentTitle5");
			courseDepartmentTitle5.innerHTML = "4.2.2.3&nbsp;&nbsp;大学物理上成绩情况";
			var courseDepartmentTitle6 = document.getElementById("courseDepartmentTitle6");
			courseDepartmentTitle6.innerHTML = "4.2.2.4&nbsp;&nbsp;概率论与数理统计成绩情况";
			var courseDepartmentTitle7 = document.getElementById("courseDepartmentTitle7");
			courseDepartmentTitle7.innerHTML = "4.2.2.5&nbsp;&nbsp;微积分A（二）成绩情况";
			var courseDepartmentTitle8 = document.getElementById("courseDepartmentTitle8");
			courseDepartmentTitle8.innerHTML = "4.2.2.6&nbsp;&nbsp;工程图学基础成绩情况";
			
			var tableTitle7=document.getElementById("table4.2");
			tableTitle7.innerHTML="表4.2&nbsp;&nbsp;工程实践A成绩情况";
			var tableTitle8=document.getElementById("table4.3");
			tableTitle8.innerHTML="表4.3&nbsp;&nbsp;机械设计基础B成绩情况";
			var tableTitle9=document.getElementById("table4.4");
			tableTitle9.innerHTML="表4.4&nbsp;&nbsp;大学英语（四）成绩情况";
			var tableTitle10=document.getElementById("table4.5");
			tableTitle10.innerHTML="表4.5&nbsp;&nbsp;大学英语（二）成绩情况";
			var tableTitle11=document.getElementById("table4.6");
			tableTitle11.innerHTML="表4.6&nbsp;&nbsp;程序设计基础成绩情况";
			var tableTitle12=document.getElementById("table4.7");
			tableTitle12.innerHTML="表4.7&nbsp;&nbsp;大学物理上成绩情况";
			var tableTitle13=document.getElementById("table4.8");
			tableTitle13.innerHTML="表4.8&nbsp;&nbsp;概率论与数理统计成绩情况";
			var tableTitle14=document.getElementById("table4.9");
			tableTitle14.innerHTML="表4.9&nbsp;&nbsp;微积分A（二）成绩情况";
			var tableTitle15=document.getElementById("table4.10");
			tableTitle15.innerHTML="表4.10&nbsp;&nbsp;工程图学基础成绩情况";
			
			var pic9=document.getElementById("pic9");
			pic9.innerHTML="图4.2&nbsp;&nbsp;各院系工程实践A成绩情况";
			var pic10=document.getElementById("pic10");
			pic10.innerHTML="图4.3&nbsp;&nbsp;各院系机械设计基础B成绩情况";
			var pic11=document.getElementById("pic11");
			pic11.innerHTML="图4.4&nbsp;&nbsp;各院系大学英语（四）成绩情况";
			var pic12=document.getElementById("pic12");
			pic12.innerHTML="图4.5&nbsp;&nbsp;各院系大学英语（二）成绩情况";
			var pic13=document.getElementById("pic13");
			pic13.innerHTML="图4.6&nbsp;&nbsp;各院系程序设计基础成绩情况";
			var pic14=document.getElementById("pic14");
			pic14.innerHTML="图4.7&nbsp;&nbsp;各院系大学物理上成绩情况";
			var pic15=document.getElementById("pic15");
			pic15.innerHTML="图4.8&nbsp;&nbsp;各院系概率论与数理统计成绩情况";
			var pic16=document.getElementById("pic16");
			pic16.innerHTML="图4.9&nbsp;&nbsp;各院系微积分A（二）成绩情况";
			var pic17=document.getElementById("pic17");
			pic17.innerHTML="图4.10&nbsp;&nbsp;各院系工程图学基础成绩情况";
			
		}
  		
		$.blockUI.defaults.message = '<h1> 成绩数据正在加载中，请稍后... <img src="<%=path%>/pic/busy.gif" /></h1>';
		$(document).ajaxStart($.blockUI).ajaxStop($.unblockUI);
	    /* 处理表格分页搜索底部信息显示搜索方法栏 */
		$('#example1').dataTable({
	        bFilter: false,    //去掉搜索框
	        bLengthChange: false,   //去掉每页显示多少条数据方法
	        "paging" : false, //去掉底部分页框
	        "info": false, //去掉表格底部信息
	        "bSort": false, //排序功能
	    });
	  
   	    getFirstPic();      //第一章第一个功能成绩分析图
 		getSecondPic1();
		getSecondPic2();	//第一章第二个功能两张成绩分析图
		getThirdPic1();
		getThirdPic2();		//第二章第一个功能两张成绩分析图 
		getForthPic(); 		//第二章第四个功能成绩分析图 
 		getFifthPic1();		
		getFifthPic2();     //第三章第一个功能 
		getSixthPic();      //第三章第二个功能 
		getSeventhPic();    //第三章第三个功能  
 		getEighthPic();     //第四章第一个功能 
		getNinthPic();      //第四章第二个功能9张或10张分析图         
		getTenthPic();      //第二章第二个功能四张分析图
		getEleventhPic();   //第三章第五个功能		
	});
	
	$(function() {
		$('#getPic').click(function(){
			window.print();
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
				if(odList[i].courseType == "通识<br>教育"){
					option2.legend.data[i] = "通识教育";
					option2.series[i].name = "通识教育";
				}
				else if (odList[i].courseType == "专业<br>选修"){
					option2.legend.data[i] = "专业选修";
					option2.series[i].name = "专业选修";
				}
				else{
					option2.legend.data[i] = odList[i].courseType;
					option2.series[i].name = odList[i].courseType;
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
				if(odList[i].courseType == "通识<br>教育"){
					option3.legend.data[i] = "通识教育";
					option3.series[i].name = "通识教育";
				}
				else if (odList[i].courseType == "专业<br>选修"){
					option3.legend.data[i] = "专业选修";
					option3.series[i].name = "专业选修";
				}
				else{
					option3.legend.data[i] = odList[i].courseType;
					option3.series[i].name = odList[i].courseType;
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

			forthRateList[0] = aRateList[3]; //得到全校成绩的百分比
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
		    color: ['#003366', '#e5323e', '#4cabce', '#e5323e','#000000'],
		    tooltip: {
		        trigger: 'axis',
		        axisPointer: {
		            type: 'shadow'
		        }
		    },
		    legend: {
		        data: ['优秀率', '不及格率']
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
		optionCourse1 = {
			    color: ['#003366', '#e5323e', '#4cabce', '#e5323e','#000000'],
			    tooltip: {
			        trigger: 'axis',
			        axisPointer: {
			            type: 'shadow'
			        }
			    },
			    legend: {
			        data: ['优秀率', '不及格率']
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
	optionCourse2 = {
		    color: ['#003366', '#e5323e', '#4cabce', '#e5323e','#000000'],
		    tooltip: {
		        trigger: 'axis',
		        axisPointer: {
		            type: 'shadow'
		        }
		    },
		    legend: {
		        data: ['优秀率', '不及格率']
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
		
	optionCourse3 = {
		    color: ['#003366', '#e5323e', '#4cabce', '#e5323e','#000000'],
		    tooltip: {
		        trigger: 'axis',
		        axisPointer: {
		            type: 'shadow'
		        }
		    },
		    legend: {
		        data: ['优秀率', '不及格率']
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
		
	optionCourse4 = {
		    color: ['#003366', '#e5323e', '#4cabce', '#e5323e','#000000'],
		    tooltip: {
		        trigger: 'axis',
		        axisPointer: {
		            type: 'shadow'
		        }
		    },
		    legend: {
		        data: ['优秀率', '不及格率']
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
		
	optionCourse5 = {
		    color: ['#003366', '#e5323e', '#4cabce', '#e5323e','#000000'],
		    tooltip: {
		        trigger: 'axis',
		        axisPointer: {
		            type: 'shadow'
		        }
		    },
		    legend: {
		        data: ['优秀率', '不及格率']
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
		
	optionCourse6 = {
		    color: ['#003366', '#e5323e', '#4cabce', '#e5323e','#000000'],
		    tooltip: {
		        trigger: 'axis',
		        axisPointer: {
		            type: 'shadow'
		        }
		    },
		    legend: {
		        data: ['优秀率', '不及格率']
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
		
	optionCourse7 = {
		    color: ['#003366', '#e5323e', '#4cabce', '#e5323e','#000000'],
		    tooltip: {
		        trigger: 'axis',
		        axisPointer: {
		            type: 'shadow'
		        }
		    },
		    legend: {
		        data: ['优秀率', '不及格率']
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
		
	optionCourse8 = {
		    color: ['#003366', '#e5323e', '#4cabce', '#e5323e','#000000'],
		    tooltip: {
		        trigger: 'axis',
		        axisPointer: {
		            type: 'shadow'
		        }
		    },
		    legend: {
		        data: ['优秀率', '不及格率']
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
		
	optionCourse9 = {
		    color: ['#003366', '#e5323e', '#4cabce', '#e5323e','#000000'],
		    tooltip: {
		        trigger: 'axis',
		        axisPointer: {
		            type: 'shadow'
		        }
		    },
		    legend: {
		        data: ['优秀率', '不及格率']
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
		
		var aRateList = new Array();
		var bRateList = new Array();
		var aRateList1 = new Array();
		var bRateList1 = new Array();
		var aRateList2 = new Array();
		var bRateList2 = new Array();
		var aRateList3 = new Array();
		var bRateList3 = new Array();
		var aRateList4 = new Array();
		var bRateList4 = new Array();
		var aRateList5 = new Array();
		var bRateList5 = new Array();
		var aRateList6 = new Array();
		var bRateList6 = new Array();
		var aRateList7 = new Array();
		var bRateList7 = new Array();
		var aRateList8 = new Array();
		var bRateList8 = new Array();
		var aRateList9 = new Array();
		var bRateList9 = new Array();
		
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
				optionCourse1.xAxis[0].data[i] = bcddList[i].departmentName;
				/*将后台传回来的百分比去掉百分号并转换为数字类型 */
				aRateList1[i] = parseFloat(bcddList[i].excellentRate.substring(0,bcddList[i].excellentRate.length-1));
				bRateList1[i] = parseFloat(bcddList[i].failRate.substring(0,bcddList[i].failRate.length-1));
			}
			
			optionCourse1.series[0].data = aRateList1;
			optionCourse1.series[1].data = bRateList1;
			
			var dom13 = document.getElementById("basicCourseDetailDistributionListByCourseNameBarPic1");
			var myChart13 = echarts.init(dom13);
			if (optionCourse1 && typeof optionCourse1 === "object") {
			    myChart13.setOption(optionCourse1, true);
			}
		});

		args = {
				year : year1,
				term : term1,
				courseName : courseList[2]
			};

			$.post(url, args, function(bcddList){
				for(var i = 0; i < bcddList.length ; i++){
					optionCourse2.xAxis[0].data[i] = bcddList[i].departmentName;
					/*将后台传回来的百分比去掉百分号并转换为数字类型 */
					aRateList2[i] = parseFloat(bcddList[i].excellentRate.substring(0,bcddList[i].excellentRate.length-1));
					bRateList2[i] = parseFloat(bcddList[i].failRate.substring(0,bcddList[i].failRate.length-1));
				}
				
				optionCourse2.series[0].data = aRateList2;
				optionCourse2.series[1].data = bRateList2;
				
				var dom14 = document.getElementById("basicCourseDetailDistributionListByCourseNameBarPic2");
				var myChart14 = echarts.init(dom14);
				if (optionCourse2 && typeof optionCourse2 === "object") {
				    myChart14.setOption(optionCourse2, true);
				}
			});
			
		args = {
				year : year1,
				term : term1,
				courseName : courseList[3]
			};

			$.post(url, args, function(bcddList){
				for(var i = 0; i < bcddList.length ; i++){
					optionCourse3.xAxis[0].data[i] = bcddList[i].departmentName;
					/*将后台传回来的百分比去掉百分号并转换为数字类型 */
					aRateList3[i] = parseFloat(bcddList[i].excellentRate.substring(0,bcddList[i].excellentRate.length-1));
					bRateList3[i] = parseFloat(bcddList[i].failRate.substring(0,bcddList[i].failRate.length-1));
				}
				
				optionCourse3.series[0].data = aRateList3;
				optionCourse3.series[1].data = bRateList3;
				
				var dom15 = document.getElementById("basicCourseDetailDistributionListByCourseNameBarPic3");
				var myChart15 = echarts.init(dom15);
				if (optionCourse3 && typeof optionCourse3 === "object") {
				    myChart15.setOption(optionCourse3, true);
				}
			});

			
		args = {
				year : year1,
				term : term1,
				courseName : courseList[4]
			};

			$.post(url, args, function(bcddList){
				for(var i = 0; i < bcddList.length ; i++){
					optionCourse4.xAxis[0].data[i] = bcddList[i].departmentName;
					/*将后台传回来的百分比去掉百分号并转换为数字类型 */
					aRateList4[i] = parseFloat(bcddList[i].excellentRate.substring(0,bcddList[i].excellentRate.length-1));
					bRateList4[i] = parseFloat(bcddList[i].failRate.substring(0,bcddList[i].failRate.length-1));
				}
				
				optionCourse4.series[0].data = aRateList4;
				optionCourse4.series[1].data = bRateList4;
				
				var dom16 = document.getElementById("basicCourseDetailDistributionListByCourseNameBarPic4");
				var myChart16 = echarts.init(dom16);
				if (optionCourse4 && typeof optionCourse4 === "object") {
				    myChart16.setOption(optionCourse4, true);
				}
			});
			
		args = {
				year : year1,
				term : term1,
				courseName : courseList[5]
			};

			$.post(url, args, function(bcddList){
				for(var i = 0; i < bcddList.length ; i++){
					optionCourse5.xAxis[0].data[i] = bcddList[i].departmentName;
					/*将后台传回来的百分比去掉百分号并转换为数字类型 */
					aRateList5[i] = parseFloat(bcddList[i].excellentRate.substring(0,bcddList[i].excellentRate.length-1));
					bRateList5[i] = parseFloat(bcddList[i].failRate.substring(0,bcddList[i].failRate.length-1));
				}
				
				optionCourse5.series[0].data = aRateList5;
				optionCourse5.series[1].data = bRateList5;
				
				var dom17 = document.getElementById("basicCourseDetailDistributionListByCourseNameBarPic5");
				var myChart17 = echarts.init(dom17);
				if (optionCourse5 && typeof optionCourse5 === "object") {
				    myChart17.setOption(optionCourse5, true);
				}
			});
			
		args = {
				year : year1,
				term : term1,
				courseName : courseList[6]
			};

			$.post(url, args, function(bcddList){
				for(var i = 0; i < bcddList.length ; i++){
					optionCourse6.xAxis[0].data[i] = bcddList[i].departmentName;
					/*将后台传回来的百分比去掉百分号并转换为数字类型 */
					aRateList6[i] = parseFloat(bcddList[i].excellentRate.substring(0,bcddList[i].excellentRate.length-1));
					bRateList6[i] = parseFloat(bcddList[i].failRate.substring(0,bcddList[i].failRate.length-1));
				}
				
				optionCourse6.series[0].data = aRateList6;
				optionCourse6.series[1].data = bRateList6;
				
				var dom18 = document.getElementById("basicCourseDetailDistributionListByCourseNameBarPic6");
				var myChart18 = echarts.init(dom18);
				if (optionCourse6 && typeof optionCourse6 === "object") {
				    myChart18.setOption(optionCourse6, true);
				}
			});
				
			
		args = {
				year : year1,
				term : term1,
				courseName : courseList[7]
			};

			$.post(url, args, function(bcddList){
				for(var i = 0; i < bcddList.length ; i++){
					optionCourse7.xAxis[0].data[i] = bcddList[i].departmentName;
					/*将后台传回来的百分比去掉百分号并转换为数字类型 */
					aRateList7[i] = parseFloat(bcddList[i].excellentRate.substring(0,bcddList[i].excellentRate.length-1));
					bRateList7[i] = parseFloat(bcddList[i].failRate.substring(0,bcddList[i].failRate.length-1));
				}
				
				optionCourse7.series[0].data = aRateList7;
				optionCourse7.series[1].data = bRateList7;
				
				var dom19 = document.getElementById("basicCourseDetailDistributionListByCourseNameBarPic7");
				var myChart19 = echarts.init(dom19);
				if (optionCourse7 && typeof optionCourse7 === "object") {
				    myChart19.setOption(optionCourse7, true);
				}
			});
			
		args = {
				year : year1,
				term : term1,
				courseName : courseList[8]
			};

			$.post(url, args, function(bcddList){
				for(var i = 0; i < bcddList.length ; i++){
					optionCourse8.xAxis[0].data[i] = bcddList[i].departmentName;
					/*将后台传回来的百分比去掉百分号并转换为数字类型 */
					aRateList8[i] = parseFloat(bcddList[i].excellentRate.substring(0,bcddList[i].excellentRate.length-1));
					bRateList8[i] = parseFloat(bcddList[i].failRate.substring(0,bcddList[i].failRate.length-1));
				}
				
				optionCourse8.series[0].data = aRateList8;
				optionCourse8.series[1].data = bRateList8;
				
				var dom20 = document.getElementById("basicCourseDetailDistributionListByCourseNameBarPic8");
				var myChart20 = echarts.init(dom20);
				if (optionCourse8 && typeof optionCourse8 === "object") {
				    myChart20.setOption(optionCourse8, true);
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
						optionCourse9.xAxis[0].data[i] = bcddList[i].departmentName;
						/*将后台传回来的百分比去掉百分号并转换为数字类型 */
						aRateList9[i] = parseFloat(bcddList[i].excellentRate.substring(0,bcddList[i].excellentRate.length-1));
						bRateList9[i] = parseFloat(bcddList[i].failRate.substring(0,bcddList[i].failRate.length-1));
					}
					
					optionCourse9.series[0].data = aRateList9;
					optionCourse9.series[1].data = bRateList9;
					
					var dom21 = document.getElementById("basicCourseDetailDistributionListByCourseNameBarPic9");
					var myChart21 = echarts.init(dom21);
					if (optionCourse9 && typeof optionCourse9 === "object") {
					    myChart21.setOption(optionCourse9, true);
					}
				});
			
		}
				
 	}
 	
 	function getTenthPic(){
 		var app = {};
		option22 = null;
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

		option22 = {
		    color: ['#003366', '#006699', '#4cabce', '#000000','#e5323e'],
		    tooltip: {
		        trigger: 'axis',
		        axisPointer: {
		            type: 'shadow'
		        }
		    },
		    legend: {
		        data: ['优秀率', '良好率', '中等率', '及格率', '不及格率']
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
		            data: []
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
		            name: '良好率',
		            type: 'bar',
		            label: labelOption,
		            data: [220, 182, 191, 234, 290]
		        },
		        {
		            name: '中等率',
		            type: 'bar',
		            label: labelOption,
		            data: [150, 232, 201, 154, 190]
		        },
		        {
		            name: '及格率',
		            type: 'bar',
		            label: labelOption,
		            data: [98, 77, 101, 99, 40]
		        },
		        {
		            name: '不及格率',
		            type: 'bar',
		            label: labelOption,
		            data: [98, 77, 101, 99, 40]
		        }
		    ]
		};;
		
 		option23 = {
		    color: ['#003366', '#006699', '#4cabce', '#000000','#e5323e'],
		    tooltip: {
		        trigger: 'axis',
		        axisPointer: {
		            type: 'shadow'
		        }
		    },
		    legend: {
		        data: ['优秀率', '良好率', '中等率', '及格率', '不及格率']
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
		            data: []
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
		            name: '良好率',
		            type: 'bar',
		            label: labelOption,
		            data: [220, 182, 191, 234, 290]
		        },
		        {
		            name: '中等率',
		            type: 'bar',
		            label: labelOption,
		            data: [150, 232, 201, 154, 190]
		        },
		        {
		            name: '及格率',
		            type: 'bar',
		            label: labelOption,
		            data: [98, 77, 101, 99, 40]
		        },
		        {
		            name: '不及格率',
		            type: 'bar',
		            label: labelOption,
		            data: [98, 77, 101, 99, 40]
		        }
		    ]
		};;
		
		
		option24 = {
		    color: ['#003366', '#006699', '#4cabce', '#000000','#e5323e'],
		    tooltip: {
		        trigger: 'axis',
		        axisPointer: {
		            type: 'shadow'
		        }
		    },
		    legend: {
		        data: ['优秀率', '良好率', '中等率', '及格率', '不及格率']
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
		            data: []
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
		            name: '良好率',
		            type: 'bar',
		            label: labelOption,
		            data: [220, 182, 191, 234, 290]
		        },
		        {
		            name: '中等率',
		            type: 'bar',
		            label: labelOption,
		            data: [150, 232, 201, 154, 190]
		        },
		        {
		            name: '及格率',
		            type: 'bar',
		            label: labelOption,
		            data: [98, 77, 101, 99, 40]
		        },
		        {
		            name: '不及格率',
		            type: 'bar',
		            label: labelOption,
		            data: [98, 77, 101, 99, 40]
		        }
		    ]
		};;
		
		option25 = {
		    color: ['#003366', '#006699', '#4cabce', '#000000','#e5323e'],
		    tooltip: {
		        trigger: 'axis',
		        axisPointer: {
		            type: 'shadow'
		        }
		    },
		    legend: {
		        data: ['优秀率', '良好率', '中等率', '及格率', '不及格率']
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
		            data: []
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
		            name: '良好率',
		            type: 'bar',
		            label: labelOption,
		            data: [220, 182, 191, 234, 290]
		        },
		        {
		            name: '中等率',
		            type: 'bar',
		            label: labelOption,
		            data: [150, 232, 201, 154, 190]
		        },
		        {
		            name: '及格率',
		            type: 'bar',
		            label: labelOption,
		            data: [98, 77, 101, 99, 40]
		        },
		        {
		            name: '不及格率',
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
		var aRateList1 = new Array();
		var bRateList1 = new Array();
		var cRateList1 = new Array();
		var dRateList1 = new Array();
		var eRateList1 = new Array();
		var aRateList2 = new Array();
		var bRateList2 = new Array();
		var cRateList2 = new Array();
		var dRateList2 = new Array();
		var eRateList2 = new Array();
		var aRateList3= new Array();
		var bRateList3 = new Array();
		var cRateList3 = new Array();
		var dRateList3 = new Array();
		var eRateList3 = new Array();
		url = "getDepartmentRPECScoreDistributionListData";
		args = {
				year : year1,
				term : term1
		};  
		$.post(url, args, function(ddAllList){
			for(var i = 0; i < ddAllList[0].length ; i++){
				option22.xAxis[0].data[i] = ddAllList[0][i].departmentName;
				aRateList[i] = parseFloat(ddAllList[0][i].excellentRate.substring(0,ddAllList[0][i].excellentRate.length-1));
				bRateList[i] = parseFloat(ddAllList[0][i].goodRate.substring(0,ddAllList[0][i].goodRate.length-1));
				cRateList[i] = parseFloat(ddAllList[0][i].mediumRate.substring(0,ddAllList[0][i].mediumRate.length-1));
				dRateList[i] = parseFloat(ddAllList[0][i].passRate.substring(0,ddAllList[0][i].passRate.length-1));
				eRateList[i] = parseFloat(ddAllList[0][i].failRate.substring(0,ddAllList[0][i].failRate.length-1));
			}
			
			option22.series[0].data = aRateList;
			option22.series[1].data = bRateList;
			option22.series[2].data = cRateList;
			option22.series[3].data = dRateList;
			option22.series[4].data = eRateList;
			
			var dom22 = document.getElementById("departmentRPECScoreDistributionListByGradeBarPic");
			var myChart22 = echarts.init(dom22);
			if (option22 && typeof option22 === "object") {
			    myChart22.setOption(option22, true);
			}
			
			for(var i = 0; i < ddAllList[1].length ; i++){
				option23.xAxis[0].data[i] = ddAllList[1][i].departmentName;
				aRateList1[i] = parseFloat(ddAllList[1][i].excellentRate.substring(0,ddAllList[1][i].excellentRate.length-1));
				bRateList1[i] = parseFloat(ddAllList[1][i].goodRate.substring(0,ddAllList[1][i].goodRate.length-1));
				cRateList1[i] = parseFloat(ddAllList[1][i].mediumRate.substring(0,ddAllList[1][i].mediumRate.length-1));
				dRateList1[i] = parseFloat(ddAllList[1][i].passRate.substring(0,ddAllList[1][i].passRate.length-1));
				eRateList1[i] = parseFloat(ddAllList[1][i].failRate.substring(0,ddAllList[1][i].failRate.length-1));
			}
			
			option23.series[0].data = aRateList1;
			option23.series[1].data = bRateList1;
			option23.series[2].data = cRateList1;
			option23.series[3].data = dRateList1;
			option23.series[4].data = eRateList1;
			
			var dom23 = document.getElementById("departmentRPECScoreDistributionListByGradeBarPic1");
			var myChart23 = echarts.init(dom23);
			if (option23 && typeof option23 === "object") {
			    myChart23.setOption(option23, true);
			}
			
			for(var i = 0; i < ddAllList[2].length ; i++){
				option24.xAxis[0].data[i] = ddAllList[2][i].departmentName;
				aRateList2[i] = parseFloat(ddAllList[2][i].excellentRate.substring(0,ddAllList[2][i].excellentRate.length-1));
				bRateList2[i] = parseFloat(ddAllList[2][i].goodRate.substring(0,ddAllList[2][i].goodRate.length-1));
				cRateList2[i] = parseFloat(ddAllList[2][i].mediumRate.substring(0,ddAllList[2][i].mediumRate.length-1));
				dRateList2[i] = parseFloat(ddAllList[2][i].passRate.substring(0,ddAllList[2][i].passRate.length-1));
				eRateList2[i] = parseFloat(ddAllList[2][i].failRate.substring(0,ddAllList[2][i].failRate.length-1));
			}
			
			option24.series[0].data = aRateList2;
			option24.series[1].data = bRateList2;
			option24.series[2].data = cRateList2;
			option24.series[3].data = dRateList2;
			option24.series[4].data = eRateList2;
			
			var dom24 = document.getElementById("departmentRPECScoreDistributionListByGradeBarPic2");
			var myChart24 = echarts.init(dom24);
			if (option24 && typeof option24 === "object") {
			    myChart24.setOption(option24, true);
			}
			
			for(var i = 0; i < ddAllList[3].length ; i++){
				option25.xAxis[0].data[i] = ddAllList[3][i].departmentName;
				aRateList3[i] = parseFloat(ddAllList[3][i].excellentRate.substring(0,ddAllList[3][i].excellentRate.length-1));
				bRateList3[i] = parseFloat(ddAllList[3][i].goodRate.substring(0,ddAllList[3][i].goodRate.length-1));
				cRateList3[i] = parseFloat(ddAllList[3][i].mediumRate.substring(0,ddAllList[3][i].mediumRate.length-1));
				dRateList3[i] = parseFloat(ddAllList[3][i].passRate.substring(0,ddAllList[3][i].passRate.length-1));
				eRateList3[i] = parseFloat(ddAllList[3][i].failRate.substring(0,ddAllList[3][i].failRate.length-1));
			}
			
			option25.series[0].data = aRateList3;
			option25.series[1].data = bRateList3;
			option25.series[2].data = cRateList3;
			option25.series[3].data = dRateList3;
			option25.series[4].data = eRateList3;
			
			var dom25 = document.getElementById("departmentRPECScoreDistributionListByGradeBarPic3");
			var myChart25 = echarts.init(dom25);
			if (option25 && typeof option25 === "object") {
			    myChart25.setOption(option25, true);
			}
		}); 
 	}
 	
 	function getEleventhPic(){
 		var app = {};
		option26 = null;
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

		option26 = {
		    color: ['#003366', '#e5323e', '#4cabce', '#e5323e','#000000'],
		    tooltip: {
		        trigger: 'axis',
		        axisPointer: {
		            type: 'shadow'
		        }
		    },
		    legend: {
		        data: ['必修', '专业选修', '通识教育']
		    },
		    calculable: true,
		    xAxis: [
		        {	
		        	axisLabel: {
                        interval:0,
                        rotate:0
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
		                    formatter: '{value}'  
		                  },  
		              show: true  
		          }  
		    ],
		    series: [
		        {
		            name: '必修',
		            type: 'bar',
		            barGap: 0,
		            label: labelOption,
		            data: [320, 332, 301, 334, 390]
		        },
		        {
		            name: '专业选修',
		            type: 'bar',
		            label: labelOption,
		            data: [220, 182, 191, 234, 290]
		        },
		        {
		            name: '通识教育',
		            type: 'bar',
		            label: labelOption,
		            data: [150, 232, 201, 154, 190]
		        }
		    ]
		};;
		
		
		var year1 = "${year}";
		var term1 = "${term}";
		var aRateList = new Array();
		var bRateList = new Array();
		var cRateList = new Array();
		
		url = "getGradeAbsenceDistributionListData";
		var args = {
			year : year1,
			term : term1
		};
		$.post(url, args, function(gadList){
			for(var i = 0; i < gadList.length ; i++){
				option26.xAxis[0].data[i] = gadList[i].grade;
				aRateList[i] = gadList[i].rcAbsenceNumber;
				bRateList[i] = gadList[i].pecAbsenceNumber;
				cRateList[i] = gadList[i].gecAbsenceNumber;
			}
			
			option26.series[0].data = aRateList;
			option26.series[1].data = bRateList;
			option26.series[2].data = cRateList;
			
			var dom26 = document.getElementById("gradeAbsenceDistributionListBarPic");
			var myChart26 = echarts.init(dom26);
			if (option26 && typeof option26 === "object") {
			    myChart26.setOption(option26, true);
			}
		});
 	}

</script>

<style media=print type="text/css">
 .noprint{display:none}
</style>

<style media="print" type="text/css">
@page {
/* 	size: A4; */
	/* margin: 20mm; */
/* 	@bottom-left.content = ""; */
}

@page:right{ 
  @bottom-left {
    margin: 10pt 0 30pt 0;
    border-top: .25pt solid #666;
    content: "Our Cats";
    font-size: 9pt;
    color: #333;
  }
  @bottom-right { 
    margin: 10pt 0 30pt 0;
    border-top: .25pt solid #666;
    content: counter(page);
    font-size: 9pt;
  }
  @top-right {
    content: "2222";
    margin: 30pt 0 10pt 0;
    font-size: 9pt;
    color: #333;
  }
}
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
			
			<div style = "width: 100%;display: relative; ">
			<div style="width:1015px; margin:0 auto" >
			<h2 style="text-align:center">第一章&nbsp;&nbsp;总体成绩分析</h2>
			<textarea rows = "3" style = "width: 100% ; font-size:15pt; border-style: none ; background: transparent" >${analysis1.split("#")[1]}${analysis2.split("#")[1]} </textarea>			
			<h4 style = "font-size:18pt">1.1&nbsp;&nbsp;各年级本科生总体分布情况</h4>
			<h5 style = "font-size:17pt">1.1.1&nbsp;&nbsp;所有课程成绩分布情况</h5> 
			<%-- <font size="4">${analysis1.split("#")[2]}</font> --%>
			<div style="font-size:15pt">${analysis1.split("#")[2]}</div>
			<div class="row">
						<!-- /.card-header -->
						<div class="card-body" style="margin: 0">
						<h5 id = "table1.1" style="text-align:center"></h5> 
							<table id="example1" class="table table-bordered table-striped">
								<thead>
									<tr>
										<!-- <th style = "text-align:center ; vertical-align: middle ">序号</th> -->
										<th style = "text-align:center ; vertical-align: middle ">年级</th>
										<th style = "text-align:center ; vertical-align: middle ">成绩记<br>录总数</th>
										<th style = "text-align:center ; vertical-align: middle ">优秀<br>(90-100)</th>
										<th style = "text-align:center ; vertical-align: middle ">良好<br>(80-89)</th>
										<th style = "text-align:center ; vertical-align: middle ">中等<br>(70-79)</th>
										<th style = "text-align:center ; vertical-align: middle ">及格<br>(60-69)</th>
										<th style = "text-align:center ; vertical-align: middle ">不及格<br>(0-59)</th>
										<th style = "text-align:center ; vertical-align: middle ">平均分</th>
										<th style = "text-align:center ; vertical-align: middle ">优秀率</th>
										<th style = "text-align:center ; vertical-align: middle ">良好率</th>
										<th style = "text-align:center ; vertical-align: middle ">中等率</th>
										<th style = "text-align:center ; vertical-align: middle ">及格率</th>
										<th style = "text-align:center ; vertical-align: middle ">不及格率</th>
									</tr>
								</thead>
								<tbody>
									<c:forEach items="${odList }" var="OverallDistribution">
										<tr>
											<td style = "text-align:center ; vertical-align: middle ">${OverallDistribution.grade }</td>
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
						</div>
						<!-- /.card-body -->
					<!-- /.card -->
				<!-- /.col -->
			</div>
			<!-- /.row --> 

			<div class="row">
				<div class="col-12">
					<div class="card">
						<div id="scNumberPic"  
							style="display:block; height: 350%; width: 100%;  position:relative; ">
						</div>
						<div id="scRatePic"    
							style="display:block; height: 350%; width: 100%;  position:relative; ">
						</div>
					</div>
				</div>
			</div>
			<h5 id = "pic1" style="text-align:center"></h5> 
			
			<div class="row">
				<div class="col-12">
				<div class="card">
					<textarea rows = "10" style = "width: 100% ;font-size:15pt; border-style: none ; background: transparent" >${analysis1.split("#")[0] } </textarea>	
				</div>
				</div>
			</div>
			
			<h3 class="card-title" style = "font-size:17pt">1.1.2&nbsp;&nbsp;必修、专业选修、通识选修课程成绩整体分布情况</h3> 
			<div style="font-size:15pt">${analysis2.split("#")[2]}</div>
			<div class="row">
				<div class="col-12">
						<!-- /.card-header -->
						<div class="card-body" style="margin: 0">
						<h5 id = "table1.2" style="text-align:center"></h5> 
							<table id="example1" class="table table-bordered table-striped">
								<thead>
									<tr>
										<!-- <th >序号</th> -->
										<th style = "text-align:center ; vertical-align: middle ">课程<br>种类</th>
										<th style = "text-align:center ; vertical-align: middle ">成绩记<br>录总数</th>
										<th style = "text-align:center ; vertical-align: middle ">优秀<br>(90-100)</th>
										<th style = "text-align:center ; vertical-align: middle ">良好<br>(80-89)</th>
										<th style = "text-align:center ; vertical-align: middle ">中等<br>(70-79)</th>
										<th style = "text-align:center ; vertical-align: middle ">及格<br>(60-69)</th>
										<th style = "text-align:center ; vertical-align: middle ">不及格<br>(0-59)</th>
										<th style = "text-align:center ; vertical-align: middle ">平均分</th>
										<th style = "text-align:center ; vertical-align: middle ">优秀率</th>
										<th style = "text-align:center ; vertical-align: middle ">良好率</th>
										<th style = "text-align:center ; vertical-align: middle ">中等率</th>
										<th style = "text-align:center ; vertical-align: middle ">及格率</th>
										<th style = "text-align:center ; vertical-align: middle ">不及格率</th>
									</tr>
								</thead>
								
								<tbody>
									<c:forEach items="${odList1}" var="OverallDistribution">
										<tr>
											<%-- <td style = "text-align:center ; vertical-align: middle ">${OverallDistribution.id }</td> --%>
											<input type="hidden" value="${OverallDistribution.id }"/>
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
						</div>
						<!-- /.card-body -->
					<!-- /.card -->
				</div>
				<!-- /.col -->
			</div>
			<!-- /.row --> 
			
			<div class="row">
				<div class="col-12">
					<div class="card">
						<div id="variousScRateBarPic"  style="display:block; height: 350%; width:100%; position:relative; ">
						</div>
						<div id="variousScRateLinePic"  style="display:block;  height: 350%; width:100%; position:relative; ">
						</div>
					</div>
				</div>
			</div>
			<h5 id = "pic2" style="text-align:center"></h5> 
			
			<div class="row">
				<div class="col-12">
					<div class="card">
					<textarea rows = "7" style = "width: 100% ; border-style: none ;font-size:15pt; background: transparent" >${analysis2.split("#")[0] }  </textarea>	
					</div>
				</div>
			</div>
			
			<h2 style="text-align:center">第二章&nbsp;&nbsp;必修、专业选修课程成绩整体情况分析</h2>
			<textarea rows = "3" style = "width: 100% ;font-size:15pt; border-style: none ; background: transparent" >${analysis3.split("#")[1] }  </textarea>	
			<h4 style="font-size:18pt">2.1&nbsp;&nbsp;全校必修、专业选修整体成绩分布情况</h4>
			<div style="font-size:15pt">${analysis3.split("#")[2] }</div>
			<div class="row">
						<div class="card-body" style="margin: 0">
						<h5 id = "table2.1" style="text-align:center"></h5>
							<table id="example1" class="table table-bordered table-striped">
								<thead>
									<tr>
										<th style = "text-align:center ; vertical-align: middle ">年级</th>
										<th style = "text-align:center ; vertical-align: middle ">成绩记<br>录总数</th>
										<th style = "text-align:center ; vertical-align: middle ">优秀<br>(90-100)</th>
										<th style = "text-align:center ; vertical-align: middle ">良好<br>(80-89)</th>
										<th style = "text-align:center ; vertical-align: middle ">中等<br>(70-79)</th>
										<th style = "text-align:center ; vertical-align: middle ">及格<br>(60-69)</th>
										<th style = "text-align:center ; vertical-align: middle ">不及格<br>(0-59)</th>
										<th style = "text-align:center ; vertical-align: middle ">平均分</th>
										<th style = "text-align:center ; vertical-align: middle ">优秀率</th>
										<th style = "text-align:center ; vertical-align: middle ">良好率</th>
										<th style = "text-align:center ; vertical-align: middle ">中等率</th>
										<th style = "text-align:center ; vertical-align: middle ">及格率</th>
										<th style = "text-align:center ; vertical-align: middle ">不及格率</th>
									</tr>
								</thead>
								<tbody>
									<c:forEach items="${odList2}" var="OverallDistribution">
										<tr>
											<td style = "text-align:center ; vertical-align: middle ">${OverallDistribution.grade }</td>
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
						</div>
						<!-- /.card-body -->
			</div>
			<!-- /.row --> 
			
			<div class="row">
				<div class="col-12">
					<div class="card">
						<div id="RPECScoreRateBarPic"  style="display:block; height: 350%; width:100%; position:relative; ">
						</div>
						<div id="RPECScoreRateLinePic"  style="display:block;  height: 350%; width:100%; position:relative; ">
						</div>
					</div>
				</div>
			</div>
			<h5 id = "pic3" style="text-align:center"></h5>
			<p>&nbsp;</p>
			<div class="row">
				<div class="col-12">
					<div class="card">
					<textarea rows = "7" style = "width: 100% ;font-size:15pt; border-style: none ; background: transparent" >${analysis3.split("#")[0] } </textarea>	
					</div>
				</div>
			</div>
			
			<h4 style="font-size:18pt">2.2&nbsp;&nbsp;各年级本科生必修、专业选修课程成绩情况</h4>
			<h5 id = "RPECTitle1" style="font-size:17pt">2.2.1&nbsp;&nbsp;各年级本科生必修、专业选修课程成绩情况</h5>
			<h5 id = "firstGradeDepartmentRPEC" style="font-size:16pt" class="card-title"></h5> 
			<div id = "table1" class="row">
						<!-- /.card-header -->
						<div class="card-body" style="margin: 0">
						<h5 id = "table2.2" style="text-align:center"></h5> 
							<table id="example1" class="table table-bordered table-striped">
								<thead>
									<tr>
										<th style = "text-align:center ; vertical-align: middle ">序号</th> 
										<th style = "text-align:center ; vertical-align: middle ">院系</th>
										<th style = "text-align:center ; vertical-align: middle ">成绩记录总数</th>
										<th style = "text-align:center ; vertical-align: middle ">平均分</th>
										<th style = "text-align:center ; vertical-align: middle ">优秀率</th>
										<th style = "text-align:center ; vertical-align: middle ">良好率</th>
										<th style = "text-align:center ; vertical-align: middle ">中等率</th>
										<th style = "text-align:center ; vertical-align: middle ">及格率</th>
										<th style = "text-align:center ; vertical-align: middle ">不及格率</th>
									</tr>
								</thead>
								<tbody>
									<c:forEach items="${ddList }"  var="DepartmentDistribution">
										<tr>
											<td style = "text-align:center ; vertical-align: middle ">${DepartmentDistribution.id }</td>
											<td style = "text-align:center ; vertical-align: middle ">${DepartmentDistribution.departmentName }</td>
											<td style = "text-align:center ; vertical-align: middle ">${DepartmentDistribution.totalNumber }</td>
											<td style = "text-align:center ; vertical-align: middle ">${DepartmentDistribution.averageScore }</td>
											<td style = "text-align:center ; vertical-align: middle ">${DepartmentDistribution.excellentRate }</td>
											<td style = "text-align:center ; vertical-align: middle ">${DepartmentDistribution.goodRate }</td>
											<td style = "text-align:center ; vertical-align: middle ">${DepartmentDistribution.mediumRate }</td>
											<td style = "text-align:center ; vertical-align: middle ">${DepartmentDistribution.passRate }</td>
											<td style = "text-align:center ; vertical-align: middle ">${DepartmentDistribution.failRate }</td>
										</tr>
									</c:forEach>
								</tbody>
							</table>
						</div>
						<!-- /.card-body -->
			</div>
			<!-- /.row -->

			<div class="row">
				<div class="col-12">
					<div class="card">
						<div id="departmentRPECScoreDistributionListByGradeBarPic"
							style="display:block; height: 350%; width:100%; position:relative; ">
						</div>
					</div>
				</div>
			</div>

			<div class="row">
				<div class="col-12">
					<div class="card">
					<textarea rows = "10" style = "width: 100% ;font-size:15pt; border-style: none ; background: transparent" >&nbsp;&nbsp;&nbsp;&nbsp;表2.2${analysis4.split("#")[0] } </textarea>	
					</div>
				</div>
			</div>
			
			<div class="card">
				<h3 id = "classRPECTitle1" style="font-size: 16pt" class="card-title"></h3> 
				<p style="font-size: 15pt">&nbsp;&nbsp;&nbsp;&nbsp;详见附表1 。</p>
			</div>
			
			<h5 id = "RPECTitle2" style="font-size: 17pt">2.2.1&nbsp;&nbsp;各年级本科生必修、专业选修课程成绩情况</h5>
			<h5 id = "secondGradeDepartmentRPEC" style="font-size: 16pt" class="card-title"></h5>
			<div id = "table1" class="row">
						<!-- /.card-header -->
						<div class="card-body" style="margin: 0">
						<h5 id = "table2.3" style="text-align:center"></h5>  
							<table id="example1" class="table table-bordered table-striped">
								<thead>
									<tr>
										<th style = "text-align:center ; vertical-align: middle ">序号</th> 
										<th style = "text-align:center ; vertical-align: middle ">院系</th>
										<th style = "text-align:center ; vertical-align: middle ">成绩记录总数</th>
										<th style = "text-align:center ; vertical-align: middle ">平均分</th>
										<th style = "text-align:center ; vertical-align: middle ">优秀率</th>
										<th style = "text-align:center ; vertical-align: middle ">良好率</th>
										<th style = "text-align:center ; vertical-align: middle ">中等率</th>
										<th style = "text-align:center ; vertical-align: middle ">及格率</th>
										<th style = "text-align:center ; vertical-align: middle ">不及格率</th>
									</tr>
								</thead>
								<tbody>
									<c:forEach items="${ddList1 }"  var="DepartmentDistribution">
										<tr>
											<td style = "text-align:center ; vertical-align: middle ">${DepartmentDistribution.id }</td>
											<td style = "text-align:center ; vertical-align: middle ">${DepartmentDistribution.departmentName }</td>
											<td style = "text-align:center ; vertical-align: middle ">${DepartmentDistribution.totalNumber }</td>
											<td style = "text-align:center ; vertical-align: middle ">${DepartmentDistribution.averageScore }</td>
											<td style = "text-align:center ; vertical-align: middle ">${DepartmentDistribution.excellentRate }</td>
											<td style = "text-align:center ; vertical-align: middle ">${DepartmentDistribution.goodRate }</td>
											<td style = "text-align:center ; vertical-align: middle ">${DepartmentDistribution.mediumRate }</td>
											<td style = "text-align:center ; vertical-align: middle ">${DepartmentDistribution.passRate }</td>
											<td style = "text-align:center ; vertical-align: middle ">${DepartmentDistribution.failRate }</td>
										</tr>
									</c:forEach>
								</tbody>
							</table>
						</div>
						<!-- /.card-body -->
			</div>
			<!-- /.row --> 
			
			<div class="row">
				<div class="col-12">
					<div class="card">
					<textarea rows = "8" style = "width: 100% ; font-size: 15pt;border-style: none ; background: transparent" >&nbsp;&nbsp;&nbsp;&nbsp;表2.3${analysis5.split("#")[0] }  </textarea>	
					</div>
				</div>
			</div>
			
			<div class="row">
				<div class="col-12">
					<div class="card">
						<div id="departmentRPECScoreDistributionListByGradeBarPic1"
							style="display:block; height: 350%; width:100%; position:relative; ">
						</div>
					</div>
				</div>
			</div>
			
			<div class="card">
				<h3 id = "classRPECTitle2" style="font-size:16pt" class="card-title"></h3> 
				<p style="font-size:15pt">&nbsp;&nbsp;&nbsp;&nbsp;详见附表2。</p>
			</div>
			
			<h5 id = "RPECTitle3" style="font-size:17pt">2.2.1&nbsp;&nbsp;各年级本科生必修、专业选修课程成绩情况</h5>
			<h5 id = "thirdGradeDepartmentRPEC" style="font-size:16pt" class="card-title"></h5> 
			<div id = "table1" class="row">
						<!-- /.card-header -->
						<div class="card-body" style="margin: 0">
						<h5 id = "table2.4" style="text-align:center"></h5> 
							<table id="example1" class="table table-bordered table-striped">
								<thead>
									<tr>
										<th style = "text-align:center ; vertical-align: middle ">序号</th> 
										<th style = "text-align:center ; vertical-align: middle ">院系</th>
										<th style = "text-align:center ; vertical-align: middle ">成绩记录总数</th>
										<th style = "text-align:center ; vertical-align: middle ">平均分</th>
										<th style = "text-align:center ; vertical-align: middle ">优秀率</th>
										<th style = "text-align:center ; vertical-align: middle ">良好率</th>
										<th style = "text-align:center ; vertical-align: middle ">中等率</th>
										<th style = "text-align:center ; vertical-align: middle ">及格率</th>
										<th style = "text-align:center ; vertical-align: middle ">不及格率</th>
									</tr>
								</thead>
								<tbody>
									<c:forEach items="${ddList2 }"  var="DepartmentDistribution">
										<tr>
											<td style = "text-align:center ; vertical-align: middle ">${DepartmentDistribution.id }</td>
											<td style = "text-align:center ; vertical-align: middle ">${DepartmentDistribution.departmentName }</td>
											<td style = "text-align:center ; vertical-align: middle ">${DepartmentDistribution.totalNumber }</td>
											<td style = "text-align:center ; vertical-align: middle ">${DepartmentDistribution.averageScore }</td>
											<td style = "text-align:center ; vertical-align: middle ">${DepartmentDistribution.excellentRate }</td>
											<td style = "text-align:center ; vertical-align: middle ">${DepartmentDistribution.goodRate }</td>
											<td style = "text-align:center ; vertical-align: middle ">${DepartmentDistribution.mediumRate }</td>
											<td style = "text-align:center ; vertical-align: middle ">${DepartmentDistribution.passRate }</td>
											<td style = "text-align:center ; vertical-align: middle ">${DepartmentDistribution.failRate }</td>
										</tr>
									</c:forEach>
								</tbody>
							</table>
						</div>
						<!-- /.card-body -->
			</div>
			<!-- /.row --> 
			
			<div class="row">
				<div class="col-12">
					<div class="card">
					<textarea rows = "9" style = "width: 100% ; font-size:15pt; border-style: none ; background: transparent" >&nbsp;&nbsp;&nbsp;&nbsp;表2.4${analysis6.split("#")[0] }  </textarea>	
					</div>
				</div>
			</div>
			
			<div class="row">
				<div class="col-12">
					<div class="card">
						<div id="departmentRPECScoreDistributionListByGradeBarPic2"
							style="display:block; height: 350%; width:100%; position:relative; ">
						</div>
					</div>
				</div>
			</div>
			
			<div class="card">
				<h3 id = "classRPECTitle3" style="font-size:16pt" class="card-title"></h3> 
				<p style="font-size:15pt">&nbsp;&nbsp;&nbsp;&nbsp;详见附表3。</p>
			</div>
			
			<h5 id = "RPECTitle4" style="font-size:17pt">2.2.1&nbsp;&nbsp;各年级本科生必修、专业选修课程成绩情况</h5>
			<h5 id = "forthGradeDepartmentRPEC" style="font-size:16pt" class="card-title"></h5> 
			<div id = "table1" class="row">
						<!-- /.card-header -->
						<div class="card-body" style="margin: 0">
						<h5 id = "table2.5" style="text-align:center"></h5> 
							<table id="example1" class="table table-bordered table-striped">
								<thead>
									<tr>
										<th style = "text-align:center ; vertical-align: middle ">序号</th> 
										<th style = "text-align:center ; vertical-align: middle ">院系</th>
										<th style = "text-align:center ; vertical-align: middle ">成绩记录总数</th>
										<th style = "text-align:center ; vertical-align: middle ">平均分</th>
										<th style = "text-align:center ; vertical-align: middle ">优秀率</th>
										<th style = "text-align:center ; vertical-align: middle ">良好率</th>
										<th style = "text-align:center ; vertical-align: middle ">中等率</th>
										<th style = "text-align:center ; vertical-align: middle ">及格率</th>
										<th style = "text-align:center ; vertical-align: middle ">不及格率</th>
									</tr>
								</thead>
								<tbody>
									<c:forEach items="${ddList3 }"  var="DepartmentDistribution">
										<tr>
											<td style = "text-align:center ; vertical-align: middle ">${DepartmentDistribution.id }</td>
											<td style = "text-align:center ; vertical-align: middle ">${DepartmentDistribution.departmentName }</td>
											<td style = "text-align:center ; vertical-align: middle ">${DepartmentDistribution.totalNumber }</td>
											<td style = "text-align:center ; vertical-align: middle ">${DepartmentDistribution.averageScore }</td>
											<td style = "text-align:center ; vertical-align: middle ">${DepartmentDistribution.excellentRate }</td>
											<td style = "text-align:center ; vertical-align: middle ">${DepartmentDistribution.goodRate }</td>
											<td style = "text-align:center ; vertical-align: middle ">${DepartmentDistribution.mediumRate }</td>
											<td style = "text-align:center ; vertical-align: middle ">${DepartmentDistribution.passRate }</td>
											<td style = "text-align:center ; vertical-align: middle ">${DepartmentDistribution.failRate }</td>
										</tr>
									</c:forEach>
								</tbody>
							</table>
						</div>
						<!-- /.card-body -->
			</div>
			<!-- /.row --> 
			
			<div class="row">
				<div class="col-12">
					<div class="card">
					<textarea rows = "9" style = "width: 100% ; font-size:15pt;border-style: none ; background: transparent" >&nbsp;&nbsp;&nbsp;&nbsp;表2.5${analysis7.split("#")[0] }  </textarea>	
					</div>
				</div>
			</div>
			
			<div class="row">
				<div class="col-12">
					<div class="card">
						<div id="departmentRPECScoreDistributionListByGradeBarPic3"
							style="display:block; height: 350%; width:100%; position:relative; ">
						</div>
					</div>
				</div>
			</div>
			
			<div class="card">
				<h3 id = "classRPECTitle4" style="font-size:16pt" class="card-title"></h3> 
				<p style="font-size:15pt">&nbsp;&nbsp;&nbsp;&nbsp;详见附表4。</p>
			</div>
			
			<h5 style="font-size:17pt" class="card-title">2.2.5&nbsp;&nbsp;各院系分年级成绩平均分比较</h5> 
			<div style="font-size:15pt">${analysis8.split("#")[2] } </div>
			<div class="row">
						<!-- /.card-header -->
						<div class="card-body" style="margin: 0">
							<h5 id = "table2.6" style="text-align:center">表2.6&nbsp;&nbsp;各院系分年级成绩平均分比较</h5> 
							<table id="example1" class="table table-bordered table-striped">
								<thead>
									<tr>
										<th rowspan="2" style = "text-align:center ; vertical-align: middle" ><center>序号</center></th>
										<th rowspan="2" style = "text-align:center ; vertical-align: middle" ><center>院系</center></th>
										<c:forEach items="${gradeListString}" var="keyword" varStatus="id">	
											<th colspan="2"><center>${keyword}</center></th>
										</c:forEach>	
									</tr>
									<tr>
										<td style = "text-align:center ; vertical-align: middle ">平均分</td>
										<td style = "text-align:center ; vertical-align: middle ">差值</td>
										
										<td style = "text-align:center ; vertical-align: middle ">平均分</td>
										<td style = "text-align:center ; vertical-align: middle ">差值</td>
										
										<td style = "text-align:center ; vertical-align: middle ">平均分</td>
										<td style = "text-align:center ; vertical-align: middle ">差值</td>
										
										<td style = "text-align:center ; vertical-align: middle ">平均分</td>
										<td style = "text-align:center ; vertical-align: middle ">差值</td>
									</tr>
								</thead>
								<tbody>
									<c:forEach items="${dagascList}" var="DepartmentAllGradeAverageScoreCompare">
										<tr>
											<td style = "text-align:center ; vertical-align: middle ">${DepartmentAllGradeAverageScoreCompare.id }</td>
											<td style = "text-align:center ; vertical-align: middle ">${DepartmentAllGradeAverageScoreCompare.departmentName }</td>
											<td style = "text-align:center ; vertical-align: middle ">${DepartmentAllGradeAverageScoreCompare.gradeFourAverageScore }</td>
											<td style = "text-align:center ; vertical-align: middle ">${DepartmentAllGradeAverageScoreCompare.gradeFourDifference }</td>
											<td style = "text-align:center ; vertical-align: middle ">${DepartmentAllGradeAverageScoreCompare.gradeThreeAverageScore }</td>
											<td style = "text-align:center ; vertical-align: middle ">${DepartmentAllGradeAverageScoreCompare.gradeThreeDifference }</td>
											<td style = "text-align:center ; vertical-align: middle ">${DepartmentAllGradeAverageScoreCompare.gradeTwoAverageScore }</td>
											<td style = "text-align:center ; vertical-align: middle ">${DepartmentAllGradeAverageScoreCompare.gradeTwoDifference }</td>
											<td style = "text-align:center ; vertical-align: middle ">${DepartmentAllGradeAverageScoreCompare.gradeOneAverageScore }</td>
											<td style = "text-align:center ; vertical-align: middle ">${DepartmentAllGradeAverageScoreCompare.gradeOneDifference }</td>
										</tr>
									</c:forEach>
								</tbody>
							</table>
						</div>
						<!-- /.card-body -->
			</div>
			<!-- /.row --> 

			<div class="row">
				<div class="col-12">
					<div class="card">
						<div id="RPECDepartmentAllGradeAverageScoreComparePic"
							style="display:block;  height: 350%; width:100%; position:relative;">
						</div>
					</div>
				</div>
			</div>
			<h5 id = "pic4" style="text-align:center"></h5>
			
			<div class="row">
				<div class="col-12">
					<div class="card">
					<textarea rows = "14" style = "width: 100% ; font-size:15pt; border-style: none ; background: transparent" >${analysis8.split("#")[0] } </textarea>	
					</div>
				</div>
			</div>
			
			<h2 style="text-align:center">第三章&nbsp;&nbsp;不及格成绩情况分析</h2>
			<textarea rows = "4" style = "width: 100% ; font-size:15pt; border-style: none ; background: transparent" >${analysis9.split("#")[1] } </textarea>	
			<h4 style="font-size:18pt">3.1&nbsp;&nbsp;全校本科生不及格整体情况</h4> 
			<div style="font-size:15pt">${analysis9.split("#")[2] } </div>
			<div class="row">
						<!-- /.card-header -->
						<div class="card-body" style="margin: 0">
						<h5 id = "table3.1" style="text-align:center">表3.1&nbsp;&nbsp;各年级不及格门数统计</h5> 
							<table id="example1" class="table table-bordered table-striped">
								<thead>
									<tr>
										<th rowspan="2" style = "text-align:center ; vertical-align: middle" ><center>年级</center></th>
										<th colspan="8" style = "text-align:center ; vertical-align: middle" ><center>不及格学生情况（人数）</center></th>
										<th rowspan="2" style = "text-align:center ; vertical-align: middle" ><center>不及格人数合计</center></th>
										<th rowspan="2" style = "text-align:center ; vertical-align: middle" ><center>年级人数</center></th>
										<th rowspan="2" style = "text-align:center ; vertical-align: middle" ><center>学生不及格率</center></th>
									</tr>
									<tr>
										<td style = "text-align:center ; vertical-align: middle ">1门</td>
										<td style = "text-align:center ; vertical-align: middle ">2门</td>
										<td style = "text-align:center ; vertical-align: middle ">3门</td>
										<td style = "text-align:center ; vertical-align: middle ">4门</td>
										<td style = "text-align:center ; vertical-align: middle ">5门</td>
										<td style = "text-align:center ; vertical-align: middle ">6门</td>
										<td style = "text-align:center ; vertical-align: middle ">7门</td>
										<td style = "text-align:center ; vertical-align: middle ">8门</td>
									</tr>
								</thead>
								<tbody>
									<c:forEach items="${gfdList}" var="GradeFailDistribution">
										<tr>
											<td style = "text-align:center ; vertical-align: middle ">${GradeFailDistribution.grade }</td>
											<td style = "text-align:center ; vertical-align: middle ">${GradeFailDistribution.oneFailNumber }</td>
											<td style = "text-align:center ; vertical-align: middle ">${GradeFailDistribution.twoFailNumber }</td>
											<td style = "text-align:center ; vertical-align: middle ">${GradeFailDistribution.threeFailNumber }</td>
											<td style = "text-align:center ; vertical-align: middle ">${GradeFailDistribution.fourFailNumber }</td>
											<td style = "text-align:center ; vertical-align: middle ">${GradeFailDistribution.fiveFailNumber }</td>
											<td style = "text-align:center ; vertical-align: middle ">${GradeFailDistribution.sixFailNumber }</td>
											<td style = "text-align:center ; vertical-align: middle ">${GradeFailDistribution.sevenFailNumber }</td>
											<td style = "text-align:center ; vertical-align: middle ">${GradeFailDistribution.eightFailNumber }</td>
											<td style = "text-align:center ; vertical-align: middle ">${GradeFailDistribution.totalFailNumber }</td>
											<td style = "text-align:center ; vertical-align: middle ">${GradeFailDistribution.totalStudentNumber }</td>
											<td style = "text-align:center ; vertical-align: middle ">${GradeFailDistribution.failRate }</td>
										</tr>
									</c:forEach>
								</tbody>
							</table>
						</div>
						<!-- /.card-body -->
			</div>
			<!-- /.row --> 

			<div class="row">
				<div class="col-12">
					<div class="card">
						<div id="barPic"  style="display:block;  height: 350%; width:100%; position:relative; ">
						</div>
						<p>&nbsp;</p>
						<p>&nbsp;</p>
						<p>&nbsp;</p>
						<p>&nbsp;</p>
						<p>&nbsp;</p>
						<p>&nbsp;</p>
						<p>&nbsp;</p>
						<div id="linePic"  style="display:block;  height: 350%; width:100%; position:relative; ">
						</div>
					</div>
				</div>
			</div>
			<h5 id = "pic5" style="text-align:center">图3.1-3.2&nbsp;&nbsp;各年级不及格学生情况</h5>
			
			<div class="row">
				<div class="col-12">
					<div class="card">
						<textarea rows = "6" style = "width: 100% ; font-size:15pt; border-style: none ; background: transparent" >${analysis9.split("#")[0] } </textarea>	
					</div>
				</div>
			</div>
			
			<h4 style="font-size:18pt">3.2&nbsp;&nbsp;各院系不及格学生情况比较分析</h4> 
			<h5 style="font-size:17pt" class="card-title">3.2.1&nbsp;&nbsp;各院系不及格学生整体情况</h5> 
			<div style="font-size:15pt">${analysis10.split("#")[2] }</div>
			<div class="row">
						<!-- /.card-header -->
						<div class="card-body" style="margin: 0">
						<h5 id = "table3.2" style="text-align:center">表3.2&nbsp;&nbsp;各院系不及格学生情况统计表</h5> 
							<table id="example1" class="table table-bordered table-striped">
								<thead>
									<tr>
										<th rowspan="2" style = "text-align:center ; vertical-align: middle" ><center>序号</center></th>
										<th rowspan="2" style = "text-align:center ; vertical-align: middle" ><center>院系</center></th>
										<th rowspan="2" style = "text-align:center ; vertical-align: middle" ><center>参与统计学生总数</center></th>
										<th colspan="5" style = "text-align:center ; vertical-align: middle" ><center>不及格学生情况</center></th>
										<th rowspan="2" style = "text-align:center ; vertical-align: middle" ><center>学生不及格率</center></th>
											
									</tr>
									<tr>
										<td style = "text-align:center ; vertical-align: middle ">1门</td>
										<td style = "text-align:center ; vertical-align: middle ">2门</td>
										<td style = "text-align:center ; vertical-align: middle ">3门</td>
										<td style = "text-align:center ; vertical-align: middle ">≥4门</td>
										<td style = "text-align:center ; vertical-align: middle ">不及格总人数</td>
									</tr>
								</thead>
								<tbody>
									<c:forEach items="${dfdList}" var="DepartmentFailDistribution">
										<tr>
											<td style = "text-align:center ; vertical-align: middle ">${DepartmentFailDistribution.id }</td>
											<td style = "text-align:center ; vertical-align: middle ">${DepartmentFailDistribution.departmentName }</td>
											<td style = "text-align:center ; vertical-align: middle ">${DepartmentFailDistribution.totalStudentNumber }</td>
											<td style = "text-align:center ; vertical-align: middle ">${DepartmentFailDistribution.oneFailNumber }</td>
											<td style = "text-align:center ; vertical-align: middle ">${DepartmentFailDistribution.twoFailNumber }</td>
											<td style = "text-align:center ; vertical-align: middle ">${DepartmentFailDistribution.threeFailNumber }</td>
											<td style = "text-align:center ; vertical-align: middle ">${DepartmentFailDistribution.geFourFailNumber }</td>
											<td style = "text-align:center ; vertical-align: middle ">${DepartmentFailDistribution.totalFailNumber }</td>
											<td style = "text-align:center ; vertical-align: middle ">${DepartmentFailDistribution.totalFailRate }</td>
										</tr>
									</c:forEach>
								</tbody>
							</table>
						</div>
						<!-- /.card-body -->
			</div>
			<!-- /.row --> 
			
			<div class="row">
				<div class="col-12">
					<div class="card">
						<div id="RCDepartmentFailDistributionListBarPic"  style="display:block;  height: 350%; width:100%; position:relative; ">
						</div>
					</div>
				</div>
			</div>
			<h5 id = "pic6" style="text-align:center">图3.3&nbsp;&nbsp;各院系学生不及格率情况</h5>
			
			<div class="row">
				<div class="col-12">
					<div class="card">
					<textarea rows = "5" style = "width: 100% ; font-size:15pt; border-style: none ; background: transparent" >${analysis10.split("#")[0] } </textarea>	
					</div>
				</div>
			</div>
			
			
			<h5 style="font-size:17pt" class="card-title">3.2.2&nbsp;&nbsp;各院系分年级学生不及格情况统计分布</h5> 
			<div style="font-size:15pt">${analysis11.split("#")[2] } </div>
			<div class="row">
						<!-- /.card-header -->
						<div class="card-body" style="margin: 0">
						<h5 id = "table3.3" style="text-align:center">表3.3&nbsp;&nbsp;各院系分年级不及格情况统计</h5>
							<table id="example1" class="table table-bordered table-striped">
								<thead>
									<tr>
										<th rowspan="2" style = "text-align:center ; vertical-align: middle "><center>序号</center></th>
										<th rowspan="2" style = "text-align:center ; vertical-align: middle "><center>院系</center></th>
										<c:forEach items="${gradeListString}" var="keyword" varStatus="id">
										<th colspan="3"><center>${keyword}</center></th>
										</c:forEach>
											
									</tr>
									<tr>
										<td style = "text-align:center ; vertical-align: middle ">学生数</td>
										<td style = "text-align:center ; vertical-align: middle ">不及格数</td>
										<td style = "text-align:center ; vertical-align: middle ">不及格率</td>
										
										<td style = "text-align:center ; vertical-align: middle ">学生数</td>
										<td style = "text-align:center ; vertical-align: middle ">不及格数</td>
										<td style = "text-align:center ; vertical-align: middle ">不及格率</td>
										
										<td style = "text-align:center ; vertical-align: middle ">学生数</td>
										<td style = "text-align:center ; vertical-align: middle ">不及格数</td>
										<td style = "text-align:center ; vertical-align: middle ">不及格率</td>
										
										<td style = "text-align:center ; vertical-align: middle ">学生数</td>
										<td style = "text-align:center ; vertical-align: middle ">不及格数</td>
										<td style = "text-align:center ; vertical-align: middle ">不及格率</td>
									</tr>
								</thead>
								<tbody>
									<c:forEach items="${dagfdList}" var="DepartmentAllGradeFailDistribution">
										<tr>
											<td style = "text-align:center ; vertical-align: middle ">${DepartmentAllGradeFailDistribution.id }</td>
											<td style = "text-align:center ; vertical-align: middle ">${DepartmentAllGradeFailDistribution.departmentName }</td>
											<td style = "text-align:center ; vertical-align: middle ">${DepartmentAllGradeFailDistribution.gradeFourStudentNumber }</td>
											<td style = "text-align:center ; vertical-align: middle ">${DepartmentAllGradeFailDistribution.gradeFourFailNumber }</td>
											<td style = "text-align:center ; vertical-align: middle ">${DepartmentAllGradeFailDistribution.gradeFourFailRate }</td>
											<td style = "text-align:center ; vertical-align: middle ">${DepartmentAllGradeFailDistribution.gradeThreeStudentNumber }</td>
											<td style = "text-align:center ; vertical-align: middle ">${DepartmentAllGradeFailDistribution.gradeThreeFailNumber }</td>
											<td style = "text-align:center ; vertical-align: middle ">${DepartmentAllGradeFailDistribution.gradeThreeFailRate }</td>
											<td style = "text-align:center ; vertical-align: middle ">${DepartmentAllGradeFailDistribution.gradeTwoStudentNumber }</td>
											<td style = "text-align:center ; vertical-align: middle ">${DepartmentAllGradeFailDistribution.gradeTwoFailNumber }</td>
											<td style = "text-align:center ; vertical-align: middle ">${DepartmentAllGradeFailDistribution.gradeTwoFailRate }</td>
											<td style = "text-align:center ; vertical-align: middle ">${DepartmentAllGradeFailDistribution.gradeOneStudentNumber }</td>
											<td style = "text-align:center ; vertical-align: middle ">${DepartmentAllGradeFailDistribution.gradeOneFailNumber }</td>
											<td style = "text-align:center ; vertical-align: middle ">${DepartmentAllGradeFailDistribution.gradeOneFailRate }</td>
										</tr>
									</c:forEach>
								</tbody>
							</table>
						</div>
						<!-- /.card-body -->
			</div>
			<!-- /.row --> 
			
			<div class="row">
				<div class="col-12">
					<div class="card">
						<div id="getRCDepartmentAllGradeFailDistributionListBarPic"  style="display:block;  height: 350%; width:100%; position:relative;">
						</div>
					</div>
				</div>
			</div>
			<h5 id = "pic7" style="text-align:center">图3.4&nbsp;&nbsp; 各院系分年级不及格情况</h5>
			
			<div class="row">
				<div class="col-12">
					<div class="card">
					<textarea rows = "11" style = "width: 100% ;font-size: 15pt; border-style: none ; background: transparent" >${analysis11.split("#")[0] } </textarea>	
					</div>
				</div>
			</div>
			
			<h4 style="font-size:18pt">3.3&nbsp;&nbsp; 各班级不及格情况统计</h4> 
			<div class="card">
					<p style="font-size:15pt">&nbsp;&nbsp;&nbsp;&nbsp;详见附表5-附表8。</p> 
			</div>
			
			<h4 style="font-size:18pt">3.4&nbsp;&nbsp; 各年级缺考情况统计</h4> 
			<div style="font-size:15pt">${analysis12.split("#")[2] }</div> 
			<div class="row">
						<!-- /.card-header -->
						<div class="card-body" style="margin: 0">
						<h5 id = "table3.4" style="text-align:center">表3.4&nbsp;&nbsp;各年级缺考情况统计表</h5> 
							<table id="example1" class="table table-bordered table-striped" >
								<thead>
									<tr>
										<th style = "text-align:center ; vertical-align: middle ">年级</th>
										<th style = "text-align:center ; vertical-align: middle ">缺考总数</th>
										<th style = "text-align:center ; vertical-align: middle ">必修</th>
										<th style = "text-align:center ; vertical-align: middle ">专业选修</th>
										<th style = "text-align:center ; vertical-align: middle ">通识教育</th>
									</tr>
								</thead>
								<tbody>
									<c:forEach items="${gadList }" var="GradeAbsenceDistribution">
										<tr>
											<td style = "text-align:center ; vertical-align: middle ">${GradeAbsenceDistribution.grade }</td>
											<td style = "text-align:center ; vertical-align: middle ">${GradeAbsenceDistribution.totalAbsenceNumber }</td>
											<td style = "text-align:center ; vertical-align: middle ">${GradeAbsenceDistribution.rcAbsenceNumber }</td>
											<td style = "text-align:center ; vertical-align: middle ">${GradeAbsenceDistribution.pecAbsenceNumber }</td>
											<td style = "text-align:center ; vertical-align: middle ">${GradeAbsenceDistribution.gecAbsenceNumber }</td>
										</tr>
									</c:forEach>
								</tbody>
							</table>
						</div>
						<!-- /.card-body -->
			</div>
			<!-- /.row --> 
			
			<div class="row">
				<div class="col-12">
					<div class="card">
						<div id="gradeAbsenceDistributionListBarPic" style="display:block;  height: 350%; width:100%; position:relative;">
						</div>
					</div>
				</div>
			</div>
			
			<div class="row">
				<div class="col-12">
					<div class="card">
					<textarea rows = "4" style = "width: 100% ; font-size: 15pt; border-style: none ; background: transparent" >${analysis12.split("#")[0] } </textarea>	
					</div>
				</div>
			</div>
			
			<h2 style="text-align:center">第四章&nbsp;&nbsp;基础课程成绩情况分析</h2>
			<div style="font-size:15pt">${analysis13.split("#")[1] }</div>  
			<h4 style="font-size:18pt">4.1&nbsp;&nbsp;主要基础课程成绩情况分布</h4> 
			<div style="font-size:15pt">${analysis13.split("#")[2] } </div>
			<div class="row">
						<!-- /.card-header -->
						<div class="card-body" style="margin: 0">
						<h5 id = "table4.1" style="text-align:center">表4.1&nbsp;&nbsp;主要基础课程成绩情况</h5> 
							<table id="example1" class="table table-bordered table-striped">
								<thead>
									<tr>
										<th style = "text-align:center ; vertical-align: middle "><center>序号</center></th>
										<th style = "text-align:center ; vertical-align: middle "><center>年级</center></th>
										<th style = "text-align:center ; vertical-align: middle "><center>课程名称</center></th>
										<th style = "text-align:center ; vertical-align: middle "><center>成绩总数</center></th>
										<th style = "text-align:center ; vertical-align: middle "><center>优秀数</center></th>
										<th style = "text-align:center ; vertical-align: middle "><center>优秀率</center></th>
										<th style = "text-align:center ; vertical-align: middle "><center>不及格数</center></th>
										<th style = "text-align:center ; vertical-align: middle "><center>不及格率</center></th>
										<th style = "text-align:center ; vertical-align: middle "><center>平均分</center></th>
											
									</tr>
								</thead>
								<tbody>
									<c:forEach items="${bcodList}" var="BasicCourseOverallDistribution">
										<tr>
											<td style = "text-align:center ; vertical-align: middle ">${BasicCourseOverallDistribution.id }</td>
											<td style = "text-align:center ; vertical-align: middle ">${BasicCourseOverallDistribution.grade }</td>
											<td style = "text-align:center ; vertical-align: middle ">${BasicCourseOverallDistribution.courseName }</td>
											<td style = "text-align:center ; vertical-align: middle ">${BasicCourseOverallDistribution.totalNumber }</td>
											<td style = "text-align:center ; vertical-align: middle ">${BasicCourseOverallDistribution.excellentNumber }</td>
											<td style = "text-align:center ; vertical-align: middle ">${BasicCourseOverallDistribution.excellentRate }</td>
											<td style = "text-align:center ; vertical-align: middle ">${BasicCourseOverallDistribution.failNumber }</td>
											<td style = "text-align:center ; vertical-align: middle ">${BasicCourseOverallDistribution.failRate }</td>
											<td style = "text-align:center ; vertical-align: middle ">${BasicCourseOverallDistribution.averageScore }</td>
										</tr>
									</c:forEach>
								</tbody>
							</table>
						</div>
						<!-- /.card-body -->
			</div>
			<!-- /.row --> 
			
			<div class="row">
				<div class="col-12">
					<div class="card">
						<div id="basicCourseOverallDistributionListBarPic"  style="display:block;  height: 350%; width:100%; position:relative;">
						</div>
					</div>
				</div>
			</div>
			<h5 id = "pic8" style="text-align:center">图4.1&nbsp;&nbsp;主要基础课程成绩优秀率、不及格率情况</h5>
			<div class="row">
				<div class="col-12">
					<div class="card">
					<textarea rows = "8" style = "width: 100% ; font-size: 15pt; border-style: none ; background: transparent" >${analysis13.split("#")[0] } </textarea>	
					</div>
				</div>
			</div>
			
			<h4 style="font-size:18pt">4.2&nbsp;&nbsp;主要基础课程成绩具体分析</h4> 
			<div style="font-size:15pt">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${analysis13.split("#")[3] }</div>  
			<h5 id = "termCourseTitle1" style="font-size:17pt"></h5> 
			<div style="font-size:15pt">${analysis13.split("#")[4] }</div> 
			<h5 id="courseDepartmentTitle" style="font-size:16pt" class="card-title" ></h5> 
			<div style="font-size:15pt">${analysis14.split("#")[2] } </div>
			<div class="row">
						<div class="card-body" style="margin: 0">
						<h5 id = "table4.2" style="text-align:center"></h5>
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
									<c:forEach items="${bcddList0}" var="BasicCourseDetailDistribution">
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
						</div>
						<!-- /.card-body -->
			</div>
			<!-- /.row --> 
			
			<div class="row">
				<div class="col-12">
					<div class="card">
						<div id="basicCourseDetailDistributionListByCourseNameBarPic0"  style="display:block;  height: 350%; width:100%; position:relative; ">
						</div>
					</div>
				</div>
			</div>
			<h5 id = "pic9" style="text-align:center"></h5>
			
			<div class="row">
				<div class="col-12">
					<div class="card">
					<textarea rows = "6" style = "width: 100% ;font-size: 15pt; border-style: none ; background: transparent" >${analysis14.split("#")[0] } </textarea>	
					</div>
				</div>
			</div>
			
			<h5 id="courseDepartmentTitle1" style="font-size:16pt" class="card-title"></h5> 
			<div style="font-size:15pt">${analysis15.split("#")[2] } </div>
			<div class="row">
						<!-- /.card-header -->
						<div class="card-body" style="margin: 0">
						<h5 id = "table4.3" style="text-align:center"></h5> 
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
									<c:forEach items="${bcddList1}" var="BasicCourseDetailDistribution">
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
						</div>
						<!-- /.card-body -->
			</div>
			<!-- /.row --> 
			<div id = "secondTermAddDiv">
			<div>&nbsp;&nbsp;</div>
			<div>&nbsp;&nbsp;</div>
			<div>&nbsp;&nbsp;</div>
			<div>&nbsp;&nbsp;</div>
			<div>&nbsp;&nbsp;</div>
			<div>&nbsp;&nbsp;</div>
			<div>&nbsp;&nbsp;</div>
			<div>&nbsp;&nbsp;</div>
			<div>&nbsp;&nbsp;</div>
			<div>&nbsp;&nbsp;</div>
			</div>
			<div class="row">
			
				<div class="col-12">
					<div class="card">
						<div id="basicCourseDetailDistributionListByCourseNameBarPic1"  style="display:block;  height: 350%; width:100%; position:relative;">
						</div>
					
				
			</div>		
			<h5 id = "pic10" style="text-align:center"></h5>	
			
			<div class="row">
				<div class="col-12">
					<div class="card">
					<textarea rows = "5" style = "width: 100% ; font-size: 15pt; border-style: none ; background: transparent" >${analysis15.split("#")[0] } </textarea>	
					</div>
				</div>
			</div>
			
			<h5 id="courseDepartmentTitle2" style="font-size:16pt" class="card-title" ></h5> 
			<div style="font-size:15pt">${analysis16.split("#")[2] }</div> 
			<div class="row">
						<!-- /.card-header -->
						<div class="card-body" style="margin: 0">
						<h5 id = "table4.4" style="text-align:center"></h5> 
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
									<c:forEach items="${bcddList2}" var="BasicCourseDetailDistribution">
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
						</div>
						<!-- /.card-body -->
			</div>
			<!-- /.row --> 
			<div id = "firstTermAddDiv">
			<div>&nbsp;&nbsp;</div>
			<div>&nbsp;&nbsp;</div>
			<div>&nbsp;&nbsp;</div>
			<div>&nbsp;&nbsp;</div>
			<div>&nbsp;&nbsp;</div>
			<div>&nbsp;&nbsp;</div>
			<div>&nbsp;&nbsp;</div>
			<div>&nbsp;&nbsp;</div>
			<div>&nbsp;&nbsp;</div>
			<div>&nbsp;&nbsp;</div>
			<div>&nbsp;&nbsp;</div>
			<div>&nbsp;&nbsp;</div>
			<div>&nbsp;&nbsp;</div>
			</div>
			<div class="row">
				<div class="col-12">
					<div class="card">
						<div id="basicCourseDetailDistributionListByCourseNameBarPic2"  style="display:block;  height: 350%; width:100%; position:relative;">
						</div>
					</div>
				</div>
			</div>		
			<h5 id = "pic11" style="text-align:center"></h5>
			
			<div class="row">
				<div class="col-12">
					<div class="card">
					<textarea rows = "5" style = "width: 100% ;font-size: 15pt; border-style: none ; background: transparent" >${analysis16.split("#")[0] } </textarea>	
					</div>
				</div>
			</div>		
			
			<h5 id = "secondTermCourseTitle2" style="font-size:17pt"></h5> 
			<p id = "ssj" style="font-size:15pt">${analysis13.split("#")[5] } </p>
			<h5 id="courseDepartmentTitle3" style="font-size:16pt" class="card-title" ></h5>
			<div style="font-size:15pt">${analysis17.split("#")[2] }</div>  
			<div class="row">
						<!-- /.card-header -->
						<div class="card-body" style="margin: 0">
						<h5 id = "table4.5" style="text-align:center"></h5> 
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
									<c:forEach items="${bcddList3}" var="BasicCourseDetailDistribution">
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
						</div>
						<!-- /.card-body -->
			</div>
			<!-- /.row --> 
			<div id = "firstTermAddDiv2">
			<div>&nbsp;&nbsp;</div>
			<div>&nbsp;&nbsp;</div>
			<div>&nbsp;&nbsp;</div>
			<div>&nbsp;&nbsp;</div>
			</div>
			<div class="row">
				<div class="col-12">
					<div class="card">
						<div id="basicCourseDetailDistributionListByCourseNameBarPic3"  style="display:block;  height: 350%; width:100%; position:relative;">
						</div>
					</div>
				</div>
			</div>
			<h5 id = "pic12" style="text-align:center"></h5>		
			
			<div class="row">
				<div class="col-12">
					<div class="card">
					<textarea rows = "5" style = "width: 100% ; font-size: 15pt; border-style: none ; background: transparent" >${analysis17.split("#")[0] } </textarea>	
					</div>
				</div>
			</div>	
			
			<h5 id="courseDepartmentTitle4" style="font-size:16pt" class="card-title"></h5> 
			<div style="font-size:15pt">${analysis18.split("#")[2] }</div> 
			<div class="row">
						<!-- /.card-header -->
						<div class="card-body" style="margin: 0">
						<h5 id = "table4.6" style="text-align:center"></h5> 
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
									<c:forEach items="${bcddList4}" var="BasicCourseDetailDistribution">
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
						</div>
						<!-- /.card-body -->
			</div>
			<!-- /.row --> 
			
			<div id = "firstTermAddDiv3">
			<div>&nbsp;&nbsp;</div>
			<div>&nbsp;&nbsp;</div>
			<div>&nbsp;&nbsp;</div>
			<div>&nbsp;&nbsp;</div>
			<div>&nbsp;&nbsp;</div>
			<div>&nbsp;&nbsp;</div>
			<div>&nbsp;&nbsp;</div>
			<div>&nbsp;&nbsp;</div>
			<div>&nbsp;&nbsp;</div>
			<div>&nbsp;&nbsp;</div>
			<div>&nbsp;&nbsp;</div>
			<div>&nbsp;&nbsp;</div>
			</div>
			
			<div class="row">
				<div class="col-12">
					<div class="card">
						<div id="basicCourseDetailDistributionListByCourseNameBarPic4"  style="display:block;  height: 350%; width:100%; position:relative;">
						</div>
					</div>
				</div>
			</div>
			<h5 id = "pic13" style="text-align:center"></h5>	
			
			<div class="row">
				<div class="col-12">
					<div class="card">
					<textarea rows = "5" style = "width: 100% ; font-size: 15pt;border-style: none ; background: transparent" >${analysis18.split("#")[0] } </textarea>	
					</div>
				</div>
			</div>
			
			<h5 id = "firstTermCourseTitle2" style="font-size:16pt"></h5> 
			<p id = "zlq" style="font-size:15pt">${analysis13.split("#")[5] } </p>
			<h5 id="courseDepartmentTitle5" style="font-size:16pt" class="card-title" ></h5> 
			<div style="font-size:15pt">${analysis19.split("#")[2] }</div> 
			<div class="row">
						<!-- /.card-header -->
						<div class="card-body" style="margin: 0">
						<h5 id = "table4.7" style="text-align:center"></h5>
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
									<c:forEach items="${bcddList5}" var="BasicCourseDetailDistribution">
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
						</div>
						<!-- /.card-body -->
			</div>
			<!-- /.row --> 
			<div id = "secondTermAddDiv2">
			<div>&nbsp;</div>
			<div>&nbsp;</div>
			</div>
			<div class="row">
				<div class="col-12">
					<div class="card">
						<div id="basicCourseDetailDistributionListByCourseNameBarPic5"  style="display:block;  height: 350%;width:100%; position:relative;">
						</div>
					</div>
				</div>
			</div>
			<h5 id = "pic14" style="text-align:center"></h5>
			
			<div class="row">
				<div class="col-12">
					<div class="card">
					<textarea rows = "5" style = "width: 100% ;font-size: 15pt; border-style: none ; background: transparent" >${analysis19.split("#")[0] } </textarea>	
					</div>
				</div>
			</div>
			
			<h5 id="courseDepartmentTitle6" style="font-size:16pt" class="card-title" ></h5> 
			<div style="font-size:15pt">${analysis20.split("#")[2] }</div>
			<div class="row">
						<div class="card-body" style="margin: 0">
						<h5 id = "table4.8" style="text-align:center"></h5> 
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
									<c:forEach items="${bcddList6}" var="BasicCourseDetailDistribution">
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
						</div>
						<!-- /.card-body -->
			</div>
			<!-- /.row --> 
			<div id = "secondTermAddDiv3">
			<div>&nbsp;</div>
			<div>&nbsp;</div>
			<div>&nbsp;</div>
			<div>&nbsp;</div>
			</div>
			<div class="row">
				<div class="col-12">
					<div class="card">
						<div id="basicCourseDetailDistributionListByCourseNameBarPic6"  style="display:block;  height: 350%; width:100%; position:relative;">
						</div>
					</div>
				</div>
			</div>
			<h5 id = "pic15" style="text-align:center"></h5>
			
			<div class="row">
				<div class="col-12">
					<div class="card">
					<textarea rows = "5" style = "width: 100% ; font-size: 15pt;border-style: none ; background: transparent" >${analysis20.split("#")[0] } </textarea>	
					</div>
				</div>
			</div>
			
			<h3 id="courseDepartmentTitle7" style="font-size:16pt" class="card-title" ></h3> 
			<div style="font-size:15pt">${analysis21.split("#")[2] }</div>
			<div class="row">
						<div class="card-body" style="margin: 0">
							<h5 id = "table4.9" style="text-align:center"></h5> 
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
									<c:forEach items="${bcddList7}" var="BasicCourseDetailDistribution">
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
						</div>
						<!-- /.card-body -->
			</div>
			<!-- /.row --> 
			<div id = "secondTermAddDiv4">
			<div>&nbsp;</div>
			<div>&nbsp;</div>
			<div>&nbsp;</div>
			<div>&nbsp;</div>
			<div>&nbsp;</div>
			<div>&nbsp;</div>
			</div>
		    <div class="row">
				<div class="col-12">
					<div class="card">
						<div id="basicCourseDetailDistributionListByCourseNameBarPic7"  style="display:block;  height: 350%; width:100%; position:relative;">
						</div>
					</div>
				</div>
			</div>
			<h5 id = "pic16" style="text-align:center"></h5>
			
			<div class="row">
				<div class="col-12">
					<div class="card">
					<textarea rows = "5" style = "width: 100% ;font-size: 15pt; border-style: none ; background: transparent" >${analysis21.split("#")[0] } </textarea>	
					</div>
				</div>
			</div>
			
			<h3 id="courseDepartmentTitle8" style="font-size:16pt" class="card-title" ></h3> 
			<div style="font-size:15pt">${analysis22.split("#")[2] }</div>
			<div class="row">
						<div class="card-body" style="margin: 0">
							<h5 id = "table4.10" style="text-align:center"></h5> 
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
									<c:forEach items="${bcddList8}" var="BasicCourseDetailDistribution">
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
						</div>
						<!-- /.card-body -->
			</div>
			<!-- /.row --> 
			<div>&nbsp;</div>
			<div>&nbsp;</div>
			<div>&nbsp;</div>
			<div>&nbsp;</div>
			<div id = "firstTermAddDiv4">&nbsp;</div>
			
		    <div class="row">
				<div class="col-12">
					<div class="card">
						<div id="basicCourseDetailDistributionListByCourseNameBarPic8"  style="display:block;  height: 350%; width:100%; position:relative;">
						</div>
					</div>
				</div>
			</div>
			<h5 id = "pic17" style="text-align:center"></h5>
			<div class="row">
				<div class="col-12">
					<div class="card">
					<textarea rows = "5" style = "width: 100% ; font-size: 15pt; border-style: none ; background: transparent" >${analysis22.split("#")[0] } </textarea>	
					</div>
				</div>
			</div>
			
			<div id = "firstTermAddCourse" class="row">
				<h3 id="courseDepartmentTitle9" style="font-size:16pt" class="card-title"></h3> 
				<div style="font-size:15pt">${analysis23.split("#")[2] }</div>
						<div class="card-body" style="margin: 0">
							<h5 id = "table4.11" style="text-align:center"></h5> 
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
									<c:forEach items="${bcddList9}" var="BasicCourseDetailDistribution">
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
						</div>
						<!-- /.card-body -->
			</div>
			<!-- /.row --> 
			
			<div id = "firstTermAddDiv5">
			<div>&nbsp;</div>
			<div>&nbsp;</div>
			<div>&nbsp;</div>
			<div>&nbsp;</div>
			<div>&nbsp;</div>
			<div>&nbsp;</div>
			<div>&nbsp;</div>
			</div>					
	        <div class="row">
				<div class="col-12">
					<div class="card">
						<div id="basicCourseDetailDistributionListByCourseNameBarPic9"  style="display:block;  height: 350%; width:100%; position:relative;">
						</div>
					</div>
				</div>
			</div>
			<h5 id = "pic18" style="text-align:center"></h5>
			
			<div id = "analysisFirstTermAdd" class="row">
				<div class="col-12">
					<div class="card">
					<textarea rows = "5" style = "width: 100% ; font-size: 15pt;border-style: none ; background: transparent" >${analysis23.split("#")[0] } </textarea>	
					</div>
				</div>
			</div>
			
			<div class="card">
					<h5 style="font-size:17pt">4.2.3&nbsp;&nbsp;主要基础课程各班成绩情况</h5> 
					<p id = "basicCourseClass" style="font-size:15pt">&nbsp;&nbsp;&nbsp;&nbsp;详见附表9-附表21。</p>
			</div>
			
		</div>
		
		    </div>
		    
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