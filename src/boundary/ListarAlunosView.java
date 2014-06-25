package boundary;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import entity.Aluno;
import persistence.AlunoDAOImpl;

/**
 * @author Rosicléia Frasson
 */
public class ListarAlunosView extends JFrame {

	private JPanel painelFundo;
	private JPanel painelBotoes;
	private static JTable tabela;
	private JScrollPane barraRolagem;
	private JButton btInserir;
	private JButton btExcluir;
	private JButton btEditar;
	//private DefaultTableModel modelo = new DefaultTableModel();
	
	private static AlunoTableModel modelo;
	static List<Aluno> lista;

	public ListarAlunosView() {
		super("Alunos");
		criaJTable();
		criaJanela();
	}

	public void criaJanela() {
		btInserir = new JButton("Inserir");
		btExcluir = new JButton("Excluir");
		btEditar = new JButton("Editar");
		painelBotoes = new JPanel();
		barraRolagem = new JScrollPane(tabela);
		painelFundo = new JPanel();
		painelFundo.setLayout(new BorderLayout());
		painelFundo.add(BorderLayout.CENTER, barraRolagem);
		painelBotoes.add(btInserir);
		painelBotoes.add(btEditar);
		painelBotoes.add(btExcluir);
		painelFundo.add(BorderLayout.SOUTH, painelBotoes);

		getContentPane().add(painelFundo);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(500, 320);
		setVisible(true);
		btInserir.addActionListener(new BtInserirListener());
		//btEditar.addActionListener(new BtEditarListener());
		//btExcluir.addActionListener(new BtExcluirListener());
	}

	private void criaJTable() {
		tabela = new JTable(modelo);
		pesquisar();
		/*tabela = new JTable(modelo);
		modelo.addColumn("RA");
		modelo.addColumn("Nome");
		modelo.addColumn("Data Nasc.");
		modelo.addColumn("Sexo");
		modelo.addColumn("CPF");
		modelo.addColumn("Logradouro");
		modelo.addColumn("Número");
		modelo.addColumn("Bairro");
		modelo.addColumn("Cidade");
		modelo.addColumn("Estado");
		modelo.addColumn("Telefone");
		modelo.addColumn("Celular.");
		modelo.addColumn("Email");
		tabela.getColumnModel().getColumn(0).setPreferredWidth(80);
		tabela.getColumnModel().getColumn(1).setPreferredWidth(200);
		tabela.getColumnModel().getColumn(1).setPreferredWidth(100);
		tabela.getColumnModel().getColumn(1).setPreferredWidth(80);
		tabela.getColumnModel().getColumn(1).setPreferredWidth(100);
		tabela.getColumnModel().getColumn(1).setPreferredWidth(200);
		tabela.getColumnModel().getColumn(1).setPreferredWidth(40);
		tabela.getColumnModel().getColumn(1).setPreferredWidth(120);
		tabela.getColumnModel().getColumn(1).setPreferredWidth(120);
		tabela.getColumnModel().getColumn(1).setPreferredWidth(30);
		tabela.getColumnModel().getColumn(1).setPreferredWidth(100);
		tabela.getColumnModel().getColumn(1).setPreferredWidth(100);
		tabela.getColumnModel().getColumn(1).setPreferredWidth(200);
		pesquisar(modelo);*/
	}

	public static void pesquisar() {
		AlunoDAOImpl dao = new AlunoDAOImpl();
		lista = dao.listar();
		modelo = new AlunoTableModel(lista);
		tabela.setModel(modelo);
		
		/*modelo.setNumRows(0);
		AlunoDAOImpl dao = new AlunoDAOImpl();

		for (Aluno a : dao.listar()) {
			modelo.addRow(new Object[]{
			a.getRa(),
			a.getNome(),
			a.getDataNascimento(),
			a.getSexo(),
			a.getCpf(),
			a.getLogradouro(),
			a.getNumero(),
			a.getBairro(),
			a.getCidade(),
			a.getEstado(),
			a.getTelefone(),
			a.getCelular(),
			a.getEmail()});
		}*/
	}

	private class BtInserirListener implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			//AlunoView ic = new AlunoView(modelo);
			//ic.setVisible(true);
		}
	}

	/*private class BtEditarListener implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			int linhaSelecionada = -1;
			linhaSelecionada = tabela.getSelectedRow();
			if (linhaSelecionada >= 0) {
				int idContato = (int) tabela.getValueAt(linhaSelecionada, 0);
				AtualizarContato ic = new AtualizarContato(modelo, idContato, linhaSelecionada);
				ic.setVisible(true);
			} else {
				JOptionPane.showMessageDialog(null, "É necesário selecionar uma linha.");
			}
		}
	}*/

	/*private class BtExcluirListener implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			int linhaSelecionada = -1;
			linhaSelecionada = tabela.getSelectedRow();
			if (linhaSelecionada >= 0) {
				int idContato = (int) tabela.getValueAt(linhaSelecionada, 0);
				ContatoDao dao = new ContatoDao();
				dao.remover(idContato);
				modelo.removeRow(linhaSelecionada);
			} else {
				JOptionPane.showMessageDialog(null, "É necesário selecionar uma linha.");
			}
		}
	}*/

	/*public static void main(String[] args) {
		ListarAlunosView lc = new ListarAlunosView();
		lc.setVisible(true);
	}*/
}




