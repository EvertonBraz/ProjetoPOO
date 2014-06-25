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
	public void adicionar(Aluno aluno){
		alunoDAO.adicionar(aluno);
		AlunoView.addTableAluno(pesquisarPorRa(aluno.getRa()));
	}
	
	public Aluno pesquisarPorRa(int ra){
		Aluno aluno = alunoDAO.pesquisarPorRa(ra);
		return aluno;
	}
	
	public Aluno pesquisarPorNome(String nome){
		Aluno aluno = alunoDAO.pesquisarPorNome(nome);
		AlunoView.showAluno(aluno);
		return aluno;
	}
	
	public Aluno pesquisarPorCpf(String cpf){
		Aluno aluno = alunoDAO.pesquisarPorCpf(cpf);
		AlunoView.showAluno(aluno);
		return aluno;
	}
	
	public List<Aluno> listar(){
		return alunoDAO.listar();
	}

}
