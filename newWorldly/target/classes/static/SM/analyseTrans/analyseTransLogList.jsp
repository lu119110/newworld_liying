<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>


<html>
    <head>
    
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
        <meta http-equiv="content-type" content="text/html; charset=UTF-8" />

        <title></title>

        <link href="./css/mine.css" type="text/css" rel="stylesheet" />
        <script>
        function addtask(){
        	var ret=showModalDialog("${pageContext.request.contextPath}/SM/analyseTrans/AddTask.jsp",window,"dialogHeight:300px;dialogWidth:400spx;status:no;scroll:yes;help:no;status:no;resizable:yes");
  			if(ret){
  				location.reload();
  			}      
        }
        function bydate(){
              var ret=showModalDialog("${pageContext.request.contextPath}/SM/analyseTrans/analyseByDate.jsp",window,"dialogHeight:300px;dialogWidth:600spx;status:no;scroll:yes;help:no;status:no;resizable:yes");  
        }
		$(function(){
			$(".clsync a").click(function(){
				$.post("${pageContext.request.contextPath}/sycnDSBasicData.do",function(ret){
					alert(ret);
				});
			});
		});
        </script>
    </head>
    <body>
        <style>
            .tr_color{background-color: #9F88FF}
        </style>
        <div class="div_head">
            <span>
                <span style="float: left;">超市>Trans分析</span>
                <span style="float: right; margin-right: 8px; font-weight: bold;">
                    <a style="text-decoration: none;" href="javascript:addtask()">添加任务</a>
                </span>
                <span style="float: right; margin-right: 8px; font-weight: bold;">
                    <a style="text-decoration: none;" href="javascript:bydate()">按日期分析</a>
                </span>
                 <span class="clsync" style="float: right; margin-right: 8px; font-weight: bold;">
                    <a style="text-decoration: none;" href="javascript:void(0)">同步基础数据</a>
                </span>
            </span>
        </div>
        <div></div>
        <div class="div_search">
            <span>
                <!-- form action="#" method="get">
                   		 品牌<select name="s_product_mark" style="width: 100px;">
                        <option selected="selected" value="0">请选择</option>
                        <option value="1">苹果apple</option>
                    </select>
                    <input value="查询" type="submit" />
                </form-->
            </span>
        </div>
        <div style="font-size: 13px; margin: 10px 5px;">
            <table class="table_a" border="1" width="100%">
                <tbody><tr style="font-weight: bold;">
                        <th width=10%>序号</th>
                        <th width=15%>Trans记录时间</th>
                        <th width=20%>分析建立时间</th>
                        <th width=20%>分析完成时间</th>
                        <th width=15%>状态</th>
                        <th width=25% align="center">操作</th>
                    </tr>
  			<c:forEach var="rl" items="${loglist}">
				<tr align="center" bgcolor="#ffffff">
					<td>
						${ rl.id}
					</td>
					<td>
						${ rl.transmonth}
					</td>
					<td>
						${ rl.createDate}
					</td>
					<td>
						${rl.finishDate}
					</td>
					<td>
						<c:if test="${rl.status=='0'}">待处理</c:if>
						<c:if test="${rl.status=='1'}">处理中</c:if>
						<c:if test="${rl.status=='2'}">处理完成</c:if>
						<c:if test="${rl.status=='3'}">处理错误</c:if>
					</td>
					<td>
						
					<td>
				</tr>
			</c:forEach>
                </tbody>
            </table>
        </div>
    </body>
</html>