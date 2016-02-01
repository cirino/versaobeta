package br.com.versaobeta;

import java.util.List;

import br.com.versaobeta.persistencia.entidade.Usuario;
import br.com.versaobeta.persistencia.jdbc.UsuarioDAO;

public class TestUsuarioDAO {

	public static void main(String[] args) {
		//testExcluir();
		//testSalvar();
		//testBuscarPorId();
		
		testBuscarTodos();
		//testAlterar();
		//testCadastrar();
		//testAutenticar();
	}

	private static void testAutenticar() {
		UsuarioDAO usuarioDAO = new UsuarioDAO();
		
		Usuario usu = new Usuario();
		usu.setLogin("mi");
		usu.setSenha("123");
		
		Usuario usuRetorno = usuarioDAO.autenticar(usu);
		System.out.println(usuRetorno);
	}

	private static void testBuscarPorId() {
		UsuarioDAO usuarioDAO = new UsuarioDAO();
		Usuario usuario = usuarioDAO.buscarPorId(11);
		System.out.println(usuario);
	}

	private static void testBuscarTodos() {
		UsuarioDAO usuarioDAO = new UsuarioDAO();
		List<Usuario> lista = usuarioDAO.buscarTodos();
		for (Usuario u : lista) {
			System.out.println(u);
		}
	}

	public static void testExcluir() {
		Usuario usu = new Usuario();
		usu.setId(13);

		UsuarioDAO usuDAO = new UsuarioDAO();
		usuDAO.excluir(usu);

		System.out.println("Excluído com sucesso!");
	}

	public static void testAlterar() {
		Usuario usu = new Usuario();
		usu.setId(4);
		usu.setNome("Dagmar Aparecido");
		usu.setLogin("cirino");
		usu.setSenha("12345678");

		UsuarioDAO usuDAO = new UsuarioDAO();
		usuDAO.alterar(usu);

		System.out.println("Alterado com sucesso!");
	}

	public static void testCadastrar() {
		// Criando o Usuário
		Usuario usu = new Usuario();
		usu.setNome("Mirele Araujo");
		usu.setLogin("mi");
		usu.setSenha("123");

		// Cadastrando usuário no banco de dados
		UsuarioDAO usuDAO = new UsuarioDAO();
		usuDAO.cadastrar(usu);

		System.out.println("Cadastrado com sucesso!");
	}

	public static void testSalvar() {
		Usuario usuario = new Usuario();

		usuario.setNome("Luiza Isabele");
		usuario.setLogin("luiza");
		usuario.setSenha("123456");

		UsuarioDAO usuDAO = new UsuarioDAO();
		usuDAO.salvar(usuario);

		System.out.println("Salvo com sucesso!");
	}
}