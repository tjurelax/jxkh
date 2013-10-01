<%@ page pageEncoding="GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>登陆</title>
<style type="text/css">
<!--
body {
	font: 100%/1.4 Verdana, Arial, Helvetica, sans-serif;
	background: #42413C;
	margin: 0;
	padding: 90px;
	color: #000;
}

/* ~~ 此固定宽度容器包含其它 div ~~ */
.container {
	width: 460px;
	background: #FFF;
	margin: 0 auto; /* 侧边的自动值与宽度结合使用，可以将布局居中对齐 */
}

/* ~~ 标题未指定宽度。它将扩展到布局的完整宽度。标题包含一个图像占位符，该占位符应替换为您自己的链接徽标 ~~ */
.header {
	background: #ADB96E;
}

.content {
	padding: 4px 0 4px 45px;
}

/* ~~ 脚注 ~~ */
.footer {
	padding: 10px 0;
	background: #CCC49F;
}
-->
</style>
</head>

<body>

	<div class="container">
		<div class="header">
			<p>&nbsp;</p>
			<!-- end .header -->
		</div>
		<div class="content">
			<h1>开发登陆</h1>
			<form id="form1" name="form1" method="post" action="<%=request.getContextPath()%>/j_spring_security_check">
				<p>
					<label for="textfield">用户名：</label><input type="text" name="j_username" id="textfield"  value="ecloud"/>
				</p>
				<p>
					<label for="textfield2">密　码：</label><input type="password" name="j_password" id="textfield2" value="ecloud"/>
				</p>
				<p>
					<input type="submit" name="button" id="button" value="提交" /> <input type="reset" name="button2" id="button2" value="重置" />
				</p>
			</form>
			<!-- end .content -->
		</div>
		<div class="footer">
			<p>&nbsp;</p>
			<!-- end .footer -->
		</div>
		<!-- end .container -->
	</div>
</body>
</html>
