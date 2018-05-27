<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>用户登录</title>

<link href="css/login.css" type="text/css" rel="stylesheet" />
<script src="${pageContext.request.contextPath}/js/jquery-1.11.3.min.js"></script>
</head>
<body id="userlogin_body">

<div id="user_login">
	<dl>
		<dd id="user_top">
			<ul>
				<li class="user_top_l"></li>
				<li class="user_top_c"></li>
				<li class="user_top_r"></li>
			</ul>
		</dd>
		<dd id="user_main">
			<ul>
				<li class="user_main_l"></li>
				<li class="user_main_c">
					<div class="user_main_box">
						<ul>
						<form id="form0" action="${pageContext.request.contextPath}/Login.do" method="post">
						<c:if test="${errormsg!=null}"><div style="color:#F00">${errormsg}</div></c:if>
							<li class="user_main_text">用户名： </li>
							<li class="user_main_input"><input name="username" maxlength="20" id="username" class="txtusernamecssclass"> </li>
						</ul>
						<ul>
							<li class="user_main_text">密 码： </li>
							<li class="user_main_input"><input type="password" name="pwd" id="pwd" class="txtpasswordcssclass"> </li>
						</ul>
						
						<ul>
							<li class="user_main_text">Cookie： </li>
							<li class="user_main_input"><select name="DropExpiration" id="DropExpiration"> 
							<option selected="" value="None">不保存</option> <option value="Day">保存一天</option> 
							<option value="Month">保存一月</option> <option value="Year">保存一年</option></select> </li>
						</ul>
					</div>
				</li>
				<li class="user_main_r"><input type="image" name="IbtnEnter" src="img/user_botton.gif" onclick="login();" class="ibtnentercssclass"></li>
			</ul></form>
		</dd>
		<dd id="user_bottom">
			<ul>
				<li class="user_bottom_l"></li>
				<li class="user_bottom_c"></li>
				<li class="user_bottom_r"></li>
			</ul>
		</dd>
	</dl>
</div>


</body>
<script>
function login(){
var expd=$("#DropExpiration").val();
if(expd=="None"){
$.cookie("username",null);
$.cookie("pwd",null);
}
if(expd=="Day"){
$.cookie("username",$("#username").val(),"{expires:1}");   
$.cookie("pwd",$("#pwd").val(),"{expires:1}");  
}
if(expd=="Month"){
$.cookie("username",$("#username").val(),"{expires:31}");   
$.cookie("pwd",$("#pwd").val(),"{expires:31}");
}
if(expd=="Year"){
$.cookie("username",$("#username").val(),"{expires:365}");   
$.cookie("pwd",$("#pwd").val(),"{expires:365}");
}
document.forms[0].submit();
}


</script>
</html>