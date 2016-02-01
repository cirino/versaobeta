package br.com.versaobeta.controller;

import java.io.IOException;
import java.util.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.versaobeta.persistencia.entidade.Usuario;
import br.com.versaobeta.persistencia.jdbc.UsuarioDAO;

@WebServlet("/UsuarioController.do")
public class UsuarioController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	public UsuarioController(){
		System.out.println("Controller..");
	}
	
	@Override
	public void init() throws ServletException{
		System.out.println("Init..");
		super.init();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException ,IOException {
		resp.setContentType("text/html");
		String acao = req.getParameter("acao");
		if(acao.equals("excluir")){
			String id = req.getParameter("id");
			Usuario usu = new Usuario();
			if(id != null)
				usu.setId(Integer.parseInt(id));
			
			UsuarioDAO usuarioDAO = new UsuarioDAO();
			usuarioDAO.excluir(usu);		
			
			resp.sendRedirect("UsuarioController.do?acao=lis");
		}else if(acao.equals("lis")){
			UsuarioDAO usuarioDAO = new UsuarioDAO();
			List<Usuario> lista = usuarioDAO.buscarTodos();	
			
			req.setAttribute("lista", lista);
			
			RequestDispatcher dispatcher = req.getRequestDispatcher("WEB-INF/listausu.jsp");
			dispatcher.forward(req, resp);
		}else if(acao.equals("alt")){
			String id = req.getParameter("id");
			
			UsuarioDAO usuarioDAO = new UsuarioDAO();
			Usuario usuario = usuarioDAO.buscarPorId(Integer.parseInt(id));
			
			req.setAttribute("usu", usuario);
			
			RequestDispatcher dispatcher = req.getRequestDispatcher("WEB-INF/formulario.jsp");
			dispatcher.forward(req, resp);
		}else if(acao.equals("cad")){
			Usuario usuario = new Usuario();
			usuario.setId(0);
			usuario.setNome("");
			usuario.setLogin("");
			usuario.setSenha("");
			
			req.setAttribute("usu", usuario);
			
			RequestDispatcher dispatcher = req.getRequestDispatcher("WEB-INF/formulario.jsp");
			dispatcher.forward(req, resp);
		}
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		String id = null;
		if (request.getParameter("id") != null){
			if(!request.getParameter("id").equals("")){
				id = request.getParameter("id");
			}
		}
			
		String nome = request.getParameter("nome") ;
		String login = request.getParameter("login");
		String senha = request.getParameter("senha");
		
		Usuario usu = new Usuario();
		if(id != null)
			usu.setId(Integer.parseInt(id));

		usu.setNome(nome);
		usu.setLogin(login);
		usu.setSenha(senha);
		
		UsuarioDAO usuarioDAO = new UsuarioDAO();
		usuarioDAO.salvar(usu);		
		
		response.getWriter().print("<b>Sucesso!</b>");
	}

	@Override
	public void destroy(){
		System.out.println("Destroy..");
		super.destroy();
	}
}