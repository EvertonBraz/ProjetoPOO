package boundary;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import entity.Aluno;

public class AlunoTableModel extends AbstractTableModel {

	private static final int colRa = 0;
	private static final int colNome = 1;
	private static final int colDataNasc = 2;
	private static final int colSexo = 3;
	private static final int colCpf = 4;
	private static final int colLogradouro = 5;
	private static final int colNumero = 6;
	private static final int colBairro = 7;
	private static final int colCidade = 8;
	private static final int colEstado = 9;
	private static final int colTelefone = 10;
	private static final int colCelular = 11;
	private static final int colEmail = 12;
	
	List<Aluno> linhas;
	private String[] colunas = new String[]{"RA", "Nome", "Data Nascimento",
			"Sexo", "CPF", "Logradouro", "Número", "Bairro", "Cidade", "Estado",
			"Telefone", "Celular", "Email"};

	public AlunoTableModel(List<Aluno> listaAlunos) {
		this.linhas = new ArrayList<Aluno>(listaAlunos);
	}

	public int getRowCount() {
		return linhas.size();
	}

	public int getColumnCount() {
		return colunas.length;
	}

	public String getColumnName(int columnIndex) {
		return colunas[columnIndex];
	}

	public Class getColumnClass(int columnIndex) {
		if (columnIndex == colRa) {
			return Integer.class;
		}
		return String.class;
	}

	public boolean isCellEditable(int rowIndex, int columnIndex) {
		return false;
	}

	public Object getValueAt(int row, int column) {

		Aluno a = linhas.get(row);

		if (column == colRa) {
			return a.getRa();
		} 
		else if (column == colNome) {
			return a.getNome();
		} 
		else if (column == colDataNasc) {
			return a.getDataNascimento();
		} 
		else if (column == colSexo) {
			if(a.getSexo() == 1)
				return "Masculino";
			if(a.getSexo() == 2)
				return "Feminino";
		}
		else if (column == colCpf) {
			return a.getCpf();
		} 
		else if (column == colLogradouro) {
			return a.getLogradouro();
		} 
		else if (column == colNumero) {
			return a.getNumero();
		} 
		else if (column == colBairro) {
			return a.getBairro();
		}
		else if (column == colCidade) {
			return a.getCidade();
		}
		else if (column == colEstado) {
			return a.getEstado();
		}
		else if (column == colTelefone) {
			return a.getTelefone();
		}
		else if (column == colCelular) {
			return a.getCelular();
		}
		else if (column == colEmail) {
			return a.getEmail();
		}
		return "";
	}

	public void setValueAt(Object aValue, int row, int column) {
		Aluno a = linhas.get(row);

		if (column == colRa) {
			a.setRa((Integer) aValue);
		} 
		else if (column == colNome) {
			a.setNome(aValue.toString());
		} 
		else if (column == colDataNasc) {
			a.setDataNascimento((Date) aValue);
		} 
		else if (column == colSexo) {
			a.setSexo((Integer) aValue);
		}
		else if (column == colCpf) {
			a.setCpf(aValue.toString());
		} 
		else if (column == colLogradouro) {
			a.setLogradouro(aValue.toString());
		} 
		else if (column == colNumero) {
			a.setNumero(aValue.toString());
		} 
		else if (column == colBairro) {
			a.setBairro(aValue.toString());
		}
		else if (column == colCidade) {
			a.setCidade(aValue.toString());
		}
		else if (column == colEstado) {
			a.setEstado(aValue.toString());
		}
		else if (column == colTelefone) {
			a.setTelefone(aValue.toString());
		}
		else if (column == colCelular) {
			a.setCelular(aValue.toString());
		}
		else if (column == colEmail) {
			a.setEmail(aValue.toString());
		}
	}

	public Aluno getAluno(int indiceLinha) {
		return linhas.get(indiceLinha);
	}

	public void addAluno(Aluno aluno) {
		linhas.add(aluno);
		int ultimoIndice = getRowCount() - 1;
		fireTableRowsInserted(ultimoIndice, ultimoIndice);

	}

	public void updateAluno(int indiceLinha, Aluno marca) {
		linhas.set(indiceLinha, marca);
		fireTableRowsUpdated(indiceLinha, indiceLinha);

	}

	public void removeAluno(int indiceLinha) {
		linhas.remove(indiceLinha);
		fireTableRowsDeleted(indiceLinha, indiceLinha);

	}
}

