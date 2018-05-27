

<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<head>


	<title>AddTask.jsp</title>

	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<link type="text/css" rel="stylesheet"
		href="${pageContext.request.contextPath}/css/dhtmlgoodies_calendar.css?random=20051112"
		media="screen"></link>
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/js/dhtmlgoodies_calendar.js?random=20060118"></script>

	<script type="text/javascript">
function custom_close(){
if 
(confirm("您确定要关闭本页吗？")){
window.opener=null;
window.open('','_self');
window.close();
}
else{}
}
function startAnalyse(){
			if(document.forms[0].startDate.value=="" || document.forms[0].endDate.value==""){
				alert("请选择开始和结束日期");
				return;
			}
			document.forms[0].method="post";
			document.forms[0].target="hideFrame";
			document.forms[0].submit();
}
function addok(){
	alert("tran分析完成");
	//window.retureValue = "ok";
	window.returnValue=true;
	window.close();
}
function adderror(){
	alert("tran分析错误");
	//window.retureValue = "ok";
	window.returnValue=true;
	window.close();
}
</script>
</head>



<body>
	<iframe src="" id="hideFrame" name="hideFrame" style="display: none"></iframe>
	<form id="form1"
		action="${pageContext.request.contextPath}/AnalysisTransByDate_sm.do"
		method="post">
		<table border="0">
			<tr>
				<td>
					<span>开始日期：<input type="text" value="" readonly
							name="startDate">
						<input type="button" value="..."
							onclick="displayCalendar(document.forms[0].startDate,'yyyy-mm-dd',this)">
					</span>
				</td>

			</tr>
			<tr>
				<td>
					<span>结束日期：<input type="text" value="" readonly
							name="endDate">
						<input type="button" value="..."
							onclick="displayCalendar(document.forms[0].endDate,'yyyy-mm-dd',this)">
					</span>
			</tr>

			<tr>
				<td align="center">
					<input type="button" value="开始分析"
						onclick="javascript:startAnalyse();" />
					<input type="button" value="取消" onclick="javascript:custom_close()" />
				</td>

			</tr>
		</table>
	</form>
</body>


