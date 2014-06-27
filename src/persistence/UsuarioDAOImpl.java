package persistence;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import entity.Aluno;
import entity.Usuario;

public class UsuarioDAOImpl implements UsuarioDAO {

	Conexao con = new Conexao();
	
	@Override
	public Usuario pesquisarUsuario(String login, String senha) {
		String sql = "select * from tblUsuarios where login like ? and senha like ?";
		Usuario usuario = new Usuario();
		try {
			PreparedStatement ps = con.getConnection().prepareStatement(sql);
			ps.setString(1, login);
			ps.setString(2, senha);
			ResultSet rs = ps.executeQuery();
			if(rs.first()){
				usuario.setLogin(rs.getString("login"));
				usuario.setSenha(rs.getString("senha"));
			}
			else{
				usuario = null;
				JOptionPane.showMessageDialog(null, "Usuário ou senha não cadastrados!", "Login", JOptionPane.ERROR_MESSAGE);
			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Não foi possível encontrar o registro!", "Login", JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
			usuario = null;
		}
		return usuario;
	}

}
