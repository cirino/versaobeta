<%@page import="br.com.versaobeta.persistencia.entidade.Usuario"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Lista de Usuários</title>
<script type="text/javascript">
	function confirmaExclusao(id){
		if(window.confirm('Tem certeza que deseja excluir?')){
			location.href="UsuarioController.do?acao=excluir&id="+id;
		}
	}
</script>
</head>
<body>
<%@include file="/WEB-INF/includes/menu.jsp" %>
<%
	List<Usuario> lista= (List<Usuario>)request.getAttribute("lista");
%>
	<table border=1>
		<tr><th>Id</th><th>Nome</th><th>Excluir</th><th>Alterar</th>

	<% for (Usuario u:lista){ %>
		<tr>
			<td><%=u.getId()%></td>
			<td><%=u.getNome()%></td>
			<td><a href="javascript:confirmaExclusao(<%=u.getId()%>)"> Excluir</a></td>
			<td><a href="UsuarioController.do?acao=alt&id=<%=u.getId()%>"> Alterar</a></td>
		</tr>
	<% } %>
	</table>
	
	<p><a href="UsuarioController.do?acao=cad">Cadastrar</a>
</body>
</html>