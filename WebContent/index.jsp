<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	Olá mundo!!

	<form method="post" action="<%=request.getContextPath()%>/hello">
		Nome: <input type="text" name="nome"> <br /> <br />
		Sobrenome: <input type="text" name="sobrenome"> <br /> 
		<input type="submit" name="enviar" />
	</form>

</body>
</html>