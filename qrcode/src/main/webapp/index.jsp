<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>生成通讯录二维码</title>
</head>
<body>
	<form id="qrcodeForm" name="qrcodeForm" method="post" action="qrcode/qrcode/generate">
		手机号(必填):<input type="text" name="telephone" id="telephone"> <br>
		姓名(必填):<input type="text" name="username" id="username"> <br>
		邮箱:<input type="text" name="email" id="email"> <br>
		家庭住址:<input type="text" name="address" id="address"> <br>
		公司地址:<input type="text" name="organization" id="organization"> <br>
		工作:<input type="text" name="job" id="job"> <br>
		生日:<input type="text" name="bday" id="bday"> <br>
		注释:<input type="text" name="note" id="note"> <br>
		<input id="saveBtn" name="saveBtn" type="button" value="保存"> 
	</form>
	<div style="width:300px;height:300px">
	 	<img id="codeImg" name="codeImg" src=''>
	</div>
	<script type="text/javascript" src="resources/js/jquery-1.7.2.min.js"></script>
	<script type="text/javascript" src="resources/form/jquery.form.js"></script>
	<script type="text/javascript">
	$("#saveBtn").click(function(){
		// 提交表单
		$("#qrcodeForm").submit();
	});
	$("#qrcodeForm").ajaxForm({
		beforeSubmit:function(){
			alert("即将保存");
		},
		success:function(jsonObject){
			alert(jsonObject);
			$("#codeImg").attr("src","qrcodeImgs/"+jsonObject);
		}
	});
	</script>
</body>
</html>