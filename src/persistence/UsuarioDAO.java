package persistence;

import entity.Usuario;

public interface UsuarioDAO {
	public Usuario pesquisarUsuario(String login, String senha);
}
