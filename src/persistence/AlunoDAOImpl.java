package persistence;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.JOptionPane;

import entity.Aluno;


public class AlunoDAOImpl implements AlunoDAO {
	
	Conexao con = new Conexao();

	@Override
	public void adicionar(Aluno aluno) {
		String sql = "insert into tblAlunos values(?,?,?,?,?,?,?,?,?,?,?,?,?)";
		try {
			PreparedStatement ps = con.getConnection().prepareStatement(sql);
			ps.setInt(1, aluno.getRa());
			ps.setString(2, aluno.getNome());			
			ps.setDate(3, new java.sql.Date(aluno.getDataNascimento().getTime()));
			ps.setInt(4, aluno.getSexo());
			ps.setString(5, aluno.getCpf());
			ps.setString(6, aluno.getLogradouro());
			ps.setString(7, aluno.getNumero());
			ps.setString(8, aluno.getBairro());
			ps.setString(9, aluno.getCidade());
			ps.setString(10, aluno.getEstado());
			ps.setString(11, aluno.getTelefone());
			ps.setString(12, aluno.getCelular());
			ps.setString(13, aluno.getEmail());
			ps.executeUpdate();
			JOptionPane.showMessageDialog(null, "Aluno inserido com sucesso!", "Cadastro de Alunos", JOptionPane.INFORMATION_MESSAGE);
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Não foi possível inserir o aluno!", "Cadastro de Alunos", JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		}
	}
	
	@Override
	public List<Aluno> listar(){
		List<Aluno> listaAlunos = new ArrayList<Aluno>();
		String sql = "select * from tblAlunos";
		try {
			PreparedStatement ps = con.getConnection().prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				Aluno aluno = new Aluno();
				aluno.setRa(rs.getInt("ra"));
				aluno.setNome(rs.getString("nome"));
				aluno.setDataNascimento(rs.getDate("dataNascimento"));
				aluno.setSexo(rs.getInt("sexo"));
				aluno.setCpf(rs.getString("cpf"));
				aluno.setLogradouro(rs.getString("logradouro"));
				aluno.setNumero(rs.getString("numero"));
				aluno.setBairro(rs.getString("bairro"));
				aluno.setCidade(rs.getString("cidade"));
				aluno.setEstado(rs.getString("estado"));
				aluno.setTelefone(rs.getString("telefone"));
				aluno.setCelular(rs.getString("celular"));
				aluno.setEmail(rs.getString("email"));
				listaAlunos.add(aluno);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listaAlunos;
	}

	@Override
	public List<Aluno> pesquisarPorRa(int ra) {
		String sql = "select * from tblAlunos where ra like ?";
		List<Aluno> listaAlunos = new ArrayList<Aluno>();
		try {
			PreparedStatement ps = con.getConnection().prepareStatement(sql);
			ps.setString(1, "%" + ra + "%");
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				Aluno aluno = new Aluno();
				aluno.setRa(rs.getInt("ra"));
				aluno.setNome(rs.getString("nome"));
				aluno.setDataNascimento(rs.getDate("dataNascimento"));
				aluno.setSexo(rs.getInt("sexo"));
				aluno.setCpf(rs.getString("cpf"));
				aluno.setLogradouro(rs.getString("logradouro"));
				aluno.setNumero(rs.getString("numero"));
				aluno.setBairro(rs.getString("bairro"));
				aluno.setCidade(rs.getString("cidade"));
				aluno.setEstado(rs.getString("estado"));
				aluno.setTelefone(rs.getString("telefone"));
				aluno.setCelular(rs.getString("celular"));
				aluno.setEmail(rs.getString("email"));
				listaAlunos.add(aluno);
			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Não foi possível pesquisar o aluno!", "Pesquisa de Alunos", JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		}
		return listaAlunos;
	}

	@Override
	public List<Aluno> pesquisarPorNome(String nome) {
		String sql = "select * from tblAlunos where nome like ?";
		List<Aluno> listaAlunos = new ArrayList<Aluno>();
		try {
			PreparedStatement ps = con.getConnection().prepareStatement(sql);
			ps.setString(1, "%" + nome + "%");
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				Aluno aluno = new Aluno();
				aluno.setRa(rs.getInt("ra"));
				aluno.setNome(rs.getString("nome"));
				aluno.setDataNascimento(rs.getDate("dataNascimento"));
				aluno.setSexo(rs.getInt("sexo"));
				aluno.setCpf(rs.getString("cpf"));
				aluno.setLogradouro(rs.getString("logradouro"));
				aluno.setNumero(rs.getString("numero"));
				aluno.setBairro(rs.getString("bairro"));
				aluno.setCidade(rs.getString("cidade"));
				aluno.setEstado(rs.getString("estado"));
				aluno.setTelefone(rs.getString("telefone"));
				aluno.setCelular(rs.getString("celular"));
				aluno.setEmail(rs.getString("email"));
				listaAlunos.add(aluno);
			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Não foi possível pesquisar o aluno!", "Pesquisa de Alunos", JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		}
		return listaAlunos;
	}
		
	@Override
	public List<Aluno> pesquisarPorCpf(String cpf) {
		String sql = "select * from tblAlunos where cpf like ?";
		List<Aluno> listaAlunos = new ArrayList<Aluno>();
		try {
			PreparedStatement ps = con.getConnection().prepareStatement(sql);
			ps.setString(1, "%" + cpf + "%");
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				Aluno aluno = new Aluno();
				aluno.setRa(rs.getInt("ra"));
				aluno.setNome(rs.getString("nome"));
				aluno.setDataNascimento(rs.getDate("dataNascimento"));
				aluno.setSexo(rs.getInt("sexo"));
				aluno.setCpf(rs.getString("cpf"));
				aluno.setLogradouro(rs.getString("logradouro"));
				aluno.setNumero(rs.getString("numero"));
				aluno.setBairro(rs.getString("bairro"));
				aluno.setCidade(rs.getString("cidade"));
				aluno.setEstado(rs.getString("estado"));
				aluno.setTelefone(rs.getString("telefone"));
				aluno.setCelular(rs.getString("celular"));
				aluno.setEmail(rs.getString("email"));
				listaAlunos.add(aluno);
			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Não foi possível pesquisar o aluno!", "Pesquisa de Alunos", JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		}
		return listaAlunos;
	}	

	@Override
	public void atualizar(int ra, Aluno aluno) {
		String sql = "update tblAlunos set  ra = ?, nome = ?, dataNascimento = ?, sexo = ?, cpf = ?, logradouro = ?, numero = ?, bairro = ?, cidade = ?, estado = ?, telefone = ?, celular = ?, email = ? where ra = ?";
		
		try {
			PreparedStatement ps = con.getConnection().prepareStatement(sql);
			ps.setInt(1, aluno.getRa());
			ps.setString(2, aluno.getNome());			
			ps.setDate(3, new java.sql.Date(aluno.getDataNascimento().getTime()));
			ps.setInt(4, aluno.getSexo());
			ps.setString(5, aluno.getCpf());
			ps.setString(6, aluno.getLogradouro());
			ps.setString(7, aluno.getNumero());
			ps.setString(8, aluno.getBairro());
			ps.setString(9, aluno.getCidade());
			ps.setString(10, aluno.getEstado());
			ps.setString(11, aluno.getTelefone());
			ps.setString(12, aluno.getCelular());
			ps.setString(13, aluno.getEmail());
			ps.setInt(14, ra);
			ps.executeUpdate();
			JOptionPane.showMessageDialog(null, "Aluno alterado com sucesso!", "Alteração de Alunos", JOptionPane.INFORMATION_MESSAGE);
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Não foi possível alterar o aluno!", "Alteração de Alunos", JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		}
		

	}

	@Override
	public void remover(int ra) {
		String sql = "delete from tblAlunos where ra = ?";
		try {
			PreparedStatement ps = con.getConnection().prepareStatement(sql);
			ps.setInt(1, ra);
			ps.executeUpdate();
			JOptionPane.showMessageDialog(null, "Aluno excluído com sucesso!", "Cadastro de Alunos", JOptionPane.INFORMATION_MESSAGE);
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Não foi possível excluir o aluno!", "Exclusão de Alunos", JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		}
	}

}
