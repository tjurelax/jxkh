<%@ page pageEncoding="GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>��½</title>
<style type="text/css">
<!--
body {
	font: 100%/1.4 Verdana, Arial, Helvetica, sans-serif;
	background: #42413C;
	margin: 0;
	padding: 90px;
	color: #000;
}

/* ~~ �˹̶���������������� div ~~ */
.container {
	width: 460px;
	background: #FFF;
	margin: 0 auto; /* ��ߵ��Զ�ֵ���Ƚ��ʹ�ã����Խ����־��ж��� */
}

/* ~~ ����δָ����ȡ�������չ�����ֵ�������ȡ��������һ��ͼ��ռλ������ռλ��Ӧ�滻Ϊ���Լ������ӻձ� ~~ */
.header {
	background: #ADB96E;
}

.content {
	padding: 4px 0 4px 45px;
}

/* ~~ ��ע ~~ */
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
			<h1>������½</h1>
			<form id="form1" name="form1" method="post" action="<%=request.getContextPath()%>/j_spring_security_check">
				<p>
					<label for="textfield">�û�����</label><input type="text" name="j_username" id="textfield"  value="ecloud"/>
				</p>
				<p>
					<label for="textfield2">�ܡ��룺</label><input type="password" name="j_password" id="textfield2" value="ecloud"/>
				</p>
				<p>
					<input type="submit" name="button" id="button" value="�ύ" /> <input type="reset" name="button2" id="button2" value="����" />
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
