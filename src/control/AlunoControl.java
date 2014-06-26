package control;

import java.util.ArrayList;
import java.util.List;

import boundary.AlunoTableModel;
import boundary.AlunoView;
import persistence.AlunoDAOImpl;
import entity.Aluno;

public class AlunoControl {
	
	AlunoDAOImpl alunoDAO = new AlunoDAOImpl();
	List<Aluno> listaAluno = new ArrayList<Aluno>();
	
	public List<Aluno> listar(){
		return alunoDAO.listar();
	}
	
	public List<Aluno> pesquisarPorRa(int ra){
		listaAluno = alunoDAO.pesquisarPorRa(ra);
		AlunoView.showTableAluno(listaAluno); 
		return listaAluno;
	}
	
	public List<Aluno> pesquisarPorNome(String nome){
		listaAluno = alunoDAO.pesquisarPorNome(nome);
		AlunoView.showTableAluno(listaAluno);
		return listaAluno;
	}
	
	public List<Aluno> pesquisarPorCpf(String cpf){
		listaAluno = alunoDAO.pesquisarPorCpf(cpf);
		AlunoView.showTableAluno(listaAluno);
		return listaAluno;
	}
	
	public void adicionar(Aluno aluno){
		alunoDAO.adicionar(aluno);
		aluno = alunoDAO.pesquisarPorRa(aluno.getRa()).get(0);
		AlunoView.addTableAluno(aluno);
	}
	
	public void remover(int ra){
		alunoDAO.remover(ra);
	}

}
