package br.com.versaobeta.persistencia.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.versaobeta.persistencia.entidade.Usuario;

public class UsuarioDAO {
	private Connection con = ConexaoFactory.getConnection();

	public void cadastrar(Usuario usu) {
		String sql = "insert into usuario (nome, login, senha) values (?,?,md5(?))";
		try (PreparedStatement preparador = con.prepareStatement(sql)) {
			preparador.setString(1, usu.getNome()); // substitui o ? pelo dado
													// do usu�rio
			preparador.setString(2, usu.getLogin());
			preparador.setString(3, usu.getSenha());
			// Executando o comando SQL no banco
			preparador.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void alterar(Usuario usu) {
		String sql = "update usuario set nome=?, login=? , senha=md5(?) where id=? ";
		try (PreparedStatement preparador = con.prepareStatement(sql)) {
			preparador.setString(1, usu.getNome()); // substitui o ? pelo dado
													// do usu�rio
			preparador.setString(2, usu.getLogin());
			preparador.setString(3, usu.getSenha());
			preparador.setInt(4, usu.getId());
			// Executando o comando SQL no banco
			preparador.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public void excluir(Usuario usu) {
		String sql = "delete from usuario  where id=? ";
		try (PreparedStatement preparador = con.prepareStatement(sql)) {
			preparador.setInt(1, usu.getId());
			// Executando o comando SQL no banco
			preparador.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public void salvar(Usuario usuario) {
		if (usuario.getId() != null && usuario.getId() != 0) {
			alterar(usuario);
		} else {
			cadastrar(usuario);
		}
	}

	/**
	 * Buscar de um registro no banco de dados pelo n�mero do id do usu�rio
	 * 
	 * @param id
	 *            � um inteiro que representa o n�mero do id do usu�rio a ser
	 *            buscado
	 * @return Um objeto usu�rio quando encontra ou null quando n�o encontra
	 */
	public Usuario buscarPorId(Integer id) {

		String sql = "Select * from usuario where id=?";

		try (PreparedStatement preparador = con.prepareStatement(sql)) {
			preparador.setInt(1, id);

			ResultSet resultado = preparador.executeQuery();
			// Posicionando o cursor no primeiro registro
			if (resultado.next()) {
				Usuario usuario = new Usuario();
				usuario.setId(resultado.getInt("id"));
				usuario.setNome(resultado.getString("nome"));
				usuario.setLogin(resultado.getString("login"));
				usuario.setSenha(resultado.getString("senha"));

				return usuario;
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;

	}

	/**
	 * Realiza a busca de todos registros da tabela de usu�rios
	 * 
	 * @return Um lista de objetos Usuario contendo 0 elementos quando n�o tiver
	 *         registro ou n elementos quando tiver resultado
	 */
	public List<Usuario> buscarTodos() {

		String sql = "Select * from usuario ";
		List<Usuario> lista = new ArrayList<Usuario>();
		try (PreparedStatement preparador = con.prepareStatement(sql)) {

			ResultSet resultado = preparador.executeQuery();
			// Posicionando o cursor no primeiro registro
			while (resultado.next()) {
				Usuario usuario = new Usuario();
				usuario.setId(resultado.getInt("id"));
				usuario.setNome(resultado.getString("nome"));
				usuario.setLogin(resultado.getString("login"));
				usuario.setSenha(resultado.getString("senha"));
				// Adicionando usuario na lista
				lista.add(usuario);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return lista;

	}

	public Usuario autenticar(Usuario usuConsulta) {

		String sql = "Select * from usuario where login=? and senha=md5(?)";

		try (PreparedStatement preparador = con.prepareStatement(sql)) {
			preparador.setString(1, usuConsulta.getLogin());
			preparador.setString(2, usuConsulta.getSenha());
			ResultSet resultado = preparador.executeQuery();

			if (resultado.next()) {
				Usuario usuario = new Usuario();
				usuario.setId(resultado.getInt("id"));
				usuario.setNome(resultado.getString("nome"));
				usuario.setLogin(resultado.getString("login"));
				usuario.setSenha(resultado.getString("senha"));

				return usuario;
			}

		} catch (SQLException e) {

			e.printStackTrace();
		}
		
		return null;

	}

}