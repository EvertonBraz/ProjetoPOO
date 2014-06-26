package persistence;
import java.util.List;

import entity.Aluno;


public interface AlunoDAO {
	
	public void adicionar(Aluno aluno);
	public List<Aluno> listar();
	public List<Aluno> pesquisarPorRa(int ra);
	public List<Aluno> pesquisarPorNome(String nome);
	public List<Aluno> pesquisarPorCpf(String cpf);
	public void atualizar(int ra, Aluno aluno);
	public void remover(int ra);

}
