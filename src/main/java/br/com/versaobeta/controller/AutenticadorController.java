package br.com.versaobeta.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.com.versaobeta.persistencia.entidade.Usuario;
import br.com.versaobeta.persistencia.jdbc.UsuarioDAO;

@WebServlet("/autenticador.do")
public class AutenticadorController extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession sessao = req.getSession(false);
		
		if(sessao!=null){
			sessao.invalidate();
		}
		
		resp.sendRedirect("login.html");
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 1) capturando dados da tela
		String login = req.getParameter("login");
		String senha = req.getParameter("senha");
		// 2) colocando dados em objeto Usuario
		Usuario usuario = new Usuario();
		usuario.setLogin(login);
		usuario.setSenha(senha);
		// 3) consultando se usuario existe no banco
		UsuarioDAO usuarioDAO = new UsuarioDAO();
		Usuario usuAutenticado = usuarioDAO.autenticar(usuario);
		
		// 4) verificando se usuario foi encontrado
		if(usuAutenticado!=null){
			// 5) colocando usuario na sessao
			HttpSession sessao = req.getSession();
			sessao.setAttribute("usuAutenticado", usuAutenticado);

			sessao.setMaxInactiveInterval(60*5);
			// 6) redirecionando usuario para tela principal
			req.getRequestDispatcher("WEB-INF/index.jsp").forward(req, resp);
		}else{
			resp.getWriter().print("<script> window.alert('Usuario não encontrado!'); location.href='login.html';</script>");
		}
	}
}