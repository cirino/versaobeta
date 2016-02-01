<!DOCTYPE html>
<%@page import="br.com.versaobeta.persistencia.entidade.Usuario"%>
<html>
<head>
<meta charset="UTF-8">
<title>Formulario de cadastro</title>
</head>
<body>
<%@include file="/WEB-INF/includes/menu.jsp" %>
<%
	Usuario u = (Usuario)request.getAttribute("usu");
%>
<form action="UsuarioController.do" method="POST">
	ID: <input type="number" name="id" value="<%=u.getId()%>" /><br>
	Nome: <input type="text" name="nome" value="<%=u.getNome()%>" /><br>
	Login: <input type="text" name="login" value="<%=u.getLogin()%>" /><br>
	Senha: <input type="text" name="senha" value="<%=u.getSenha()%>" /><br>

	<input type="submit" value="Salvar"/>
</form>
</body>
</html>