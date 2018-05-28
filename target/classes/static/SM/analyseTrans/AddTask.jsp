<%@ page language="java" pageEncoding="utf-8"%>


  <head>
    <html:base />
    
    <title>AddTask.jsp</title>

	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
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
function addTask(){
			document.forms[0].method="post";
			document.forms[0].target="hideFrame";
			document.forms[0].submit();
}
function addok(){
	alert("任务添加成功");
	//window.retureValue = "ok";
	window.returnValue=true;
	window.close();
}
</script>
  </head>


  
  <body>
  <iframe src="" id="hideFrame" name="hideFrame" style="display:none"></iframe>
    <form id="form1" action="${pageContext.request.contextPath}/addSPMTAnalysisTransTask_sm.do" method="post" >
      <table border="0">
        <tr>
          <td>选择年份：</td>
          <td>  <select id="selectYear" name="selectYear""></select></td>
          <td>选择月份：</td>
          <td>  <select id="selectMonth" name="selectMonth"></select></td>
        </tr>

        <tr>
          <td colspan="2" align="center"><input type="button" value="添加任务" onclick="javascript:addTask();"/></td>
          <td colspan="2" align="center"><input type="button" value="关闭"  onclick="javascript:custom_close()"/></td>
        </tr>
      </table>
    </form>
  </body>
  <script language="javascript">

var startYear=new Date().getUTCFullYear()-10;//起始年份
var endYear=new Date().getUTCFullYear()+10;//结束年份，默认为当前年份
var obj=document.getElementById('selectYear');
var startM=1;
var endM=12;
var curM=new Date().getUTCMonth();
var obj2=document.getElementById('selectMonth');
onload=function(){
for (var i=startYear;i<=endYear;i++)obj.options.add(new Option(i,i));
obj.options[obj.options.length-11].selected=1;
for (var i=startM;i<=endM;i++)obj2.options.add(new Option(i,i));
obj2.options[curM].selected=1;
}

/*
var startM=1;var endM=12;
var curM=new Date().getUTCMonth());
var obj=document.getElementById('selectMonth');
onload=function(){
for (var i=startM;i<=endM;i++)obj.options.add(new Option(i,i));
obj.options[curM].selected=1
}
*/

</script>
</html:html>
