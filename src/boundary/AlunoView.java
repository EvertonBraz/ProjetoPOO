package boundary;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dialog;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;
import javax.swing.JTextField;

import persistence.AlunoDAOImpl;
import validations.EstadosBrasileiros;
import validations.MascaraEntrada;
import control.AlunoControl;
import entity.Aluno;

public class AlunoView extends JDialog {
	
	//JLabels
	private JLabel lblRa, lblNome, lblDataNascimento, lblSexo, lblCpf, lblLogradouro, lblNumero;
	private JLabel lblBairro, lblCidade, lblEstado, lblTelefone, lblCelular, lblEmail;
	private JLabel lbl, lblPesquisarPor, lblPesquisar;
	//JTextFields
	private JTextField txtRa, txtNome, txtLogradouro, txtNumero; 
	private JTextField txtBairro, txtCidade, txtEmail;
	private JTextField txtPesquisar;
	private JFormattedTextField txtPesquisarCpf;
	private JFormattedTextField txtDataNascimento, txtCpf, txtTelefone, txtCelular;
	//JComboBoxs
	private JComboBox cbSexo, cbEstado;
	//JRadioButtons
	private ButtonGroup bgPesquisa;
	private JRadioButton rbNome, rbRa, rbCpf;
	//JButtons
	private JButton btnNovo, btnInserir, btnAtualizar, btnExcluir, btnCancelar;
	//JTable
	private static JTable tabela;
	private JScrollPane barraRolagem;
	
	private static AlunoTableModel modelo;
	//listaJTable
	static List<Aluno> lista;
	
	//Mascara de Entrada
	MascaraEntrada mascara = new MascaraEntrada();
	
	//Controle do tamanho da janela
	private boolean janelaGrande;
	
	AlunoControl ctrl = new AlunoControl();
	
	//Se a função = inserir(inserir novo registro) ou funcao = salvar(salvar alteração de regitro)
	private String funcao;

	/**
	 * Create the dialog.
	 */
	public AlunoView() {
		criaJTable();
		criarJanela();
	}//Fim método construtor
	
	private void criaJTable() {
		tabela = new JTable(modelo);
		pesquisar();
		tabela.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		tabela.getColumnModel().getColumn(0).setMinWidth(60);
		tabela.getColumnModel().getColumn(1).setMinWidth(200);
		tabela.getColumnModel().getColumn(2).setMinWidth(100);
		tabela.getColumnModel().getColumn(3).setMinWidth(50);
		tabela.getColumnModel().getColumn(4).setMinWidth(100);
		tabela.getColumnModel().getColumn(5).setMinWidth(170);
		tabela.getColumnModel().getColumn(6).setMinWidth(50);
		tabela.getColumnModel().getColumn(7).setMinWidth(120);
		tabela.getColumnModel().getColumn(8).setMinWidth(120);
		tabela.getColumnModel().getColumn(9).setMinWidth(50);
		tabela.getColumnModel().getColumn(10).setMinWidth(100);
		tabela.getColumnModel().getColumn(11).setMinWidth(100);
		tabela.getColumnModel().getColumn(12).setMinWidth(200);
		
		tabela.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e){
				if(tabela.isEnabled()){
					if(e.getClickCount() == 2){
						pesquisarAlunoTabela();
					}
				}
			}
		});						
	}
		
	
	public static void pesquisar() {
		AlunoDAOImpl dao = new AlunoDAOImpl();
		lista = dao.listar();
		modelo = new AlunoTableModel(lista);
		tabela.setModel(modelo);
	}

	
	public void criarJanela(){
		this.setTitle("Cadastro de Alunos");
		this.setSize(700,400);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		JPanel panTabela = new JPanel(new BorderLayout());
		Container janela = getContentPane();
		janela.setLayout(null);
		
		Font fontLabel = new Font("Comic Sans MS", Font.BOLD, 14);
		Font fontText = new Font("Comic Sans MS", Font.PLAIN, 14);
		Font fontButton = new Font("Comic Sans MS", Font.BOLD, 13);
		
		lblPesquisarPor = new JLabel("Pesquisar por:");
		lblPesquisarPor.setBounds(20,15,100,25);
		lblPesquisarPor.setFont(fontLabel);
		lblPesquisarPor.setVisible(true);
		janela.add(lblPesquisarPor);
		
		rbNome = new JRadioButton("Nome:");
		rbNome.setBounds(120,17,80,20);
		rbNome.setSelected(true);
		rbNome.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				trocarTipoPesquisa(rbNome);
			}
		});
		janela.add(rbNome);
		
		rbRa = new JRadioButton("RA:");
		rbRa.setBounds(200,17,60,20);
		rbRa.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				trocarTipoPesquisa(rbRa);
			}
		});
		janela.add(rbRa);
		
		rbCpf = new JRadioButton("CPF:");
		rbCpf.setBounds(260, 17, 70, 20);
		rbCpf.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				trocarTipoPesquisa(rbCpf);
			}
		});
		janela.add(rbCpf);
		
		bgPesquisa = new ButtonGroup();
		bgPesquisa.add(rbNome);
		bgPesquisa.add(rbRa);
		bgPesquisa.add(rbCpf);
		
		lblPesquisar = new JLabel("Nome:");
		lblPesquisar.setBounds(20,40,60,25);
		lblPesquisar.setFont(fontLabel);
		lblPesquisar.setVisible(true);
		janela.add(lblPesquisar);
		
		txtPesquisar = new JFormattedTextField();
		txtPesquisar.setBounds(70,40,200,25);
		txtPesquisar.setFont(fontText);
		txtPesquisar.setVisible(true);
		janela.add(txtPesquisar);
		txtPesquisar.getDocument().addDocumentListener(new DocumentListener() {
			@Override
			public void removeUpdate(DocumentEvent arg0) {
				if(txtPesquisar.getText().equals(""))
					pesquisar();
				else
					pesquisaAproximada();
			}
			
			@Override
			public void insertUpdate(DocumentEvent arg0) {
				pesquisaAproximada();
			}
			
			@Override
			public void changedUpdate(DocumentEvent arg0) {
				pesquisaAproximada();
			}
		});
		
		txtPesquisarCpf = new JFormattedTextField(mascara.formatar("###.###.###-##"));
		txtPesquisarCpf.setBounds(55,40,130,25);
		txtPesquisarCpf.setFont(fontText);
		txtPesquisarCpf.setVisible(false);
		janela.add(txtPesquisarCpf);
		txtPesquisarCpf.getDocument().addDocumentListener(new DocumentListener() {
			@Override
			public void removeUpdate(DocumentEvent arg0) {
				if(txtPesquisarCpf.getText().equals(""))
					pesquisar();
				else
					pesquisaAproximada();
			}
			
			@Override
			public void insertUpdate(DocumentEvent arg0) {
				pesquisaAproximada();
			}
			
			@Override
			public void changedUpdate(DocumentEvent arg0) {
				pesquisaAproximada();
			}
		});
		
		barraRolagem = new JScrollPane(tabela, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		JLabel lblTeste = new JLabel("Testando");
		barraRolagem.setBounds(20,100,655,250);
		barraRolagem.setVisible(true);
		janela.add(barraRolagem);
		
		lblRa = new JLabel("RA:");
		lblRa.setBounds(20,90,30,25);
		lblRa.setFont(fontLabel);
		lblRa.setVisible(false);
		janela.add(lblRa);
		
		txtRa = new JTextField(7);
		txtRa.setBounds(170,90,80,25);
		txtRa.setFont(fontText);
		txtRa.setEditable(false);
		txtRa.setVisible(false);
		janela.add(txtRa);
		
		lblNome = new JLabel("Nome:");
		lblNome.setBounds(20,120,70,25);
		lblNome.setFont(fontLabel);
		lblNome.setVisible(false);
		janela.add(lblNome);
		
		txtNome = new JTextField(40);
		txtNome.setBounds(170,120,320,25);
		txtNome.setFont(fontText);
		txtNome.setEditable(false);
		txtNome.setVisible(false);
		janela.add(txtNome);
		
		lblDataNascimento = new JLabel("Data de Nascimento:");
		lblDataNascimento.setBounds(20,150,150,25);
		lblDataNascimento.setFont(fontLabel);
		lblDataNascimento.setVisible(false);
		janela.add(lblDataNascimento);
		
		txtDataNascimento = new JFormattedTextField(mascara.formatar("##/##/####"));
		txtDataNascimento.setBounds(170,150,120,25);
		txtDataNascimento.setFont(fontText);
		txtDataNascimento.setEditable(false);
		txtDataNascimento.setVisible(false);
		janela.add(txtDataNascimento);
		
		lblSexo = new JLabel("Sexo:");
		lblSexo.setBounds(20,180,40,25);
		lblSexo.setFont(fontLabel);
		lblSexo.setVisible(false);
		janela.add(lblSexo);
		
		cbSexo = new JComboBox();
		cbSexo.setBounds(170,180,100,25);
		cbSexo.setFont(fontText);
		cbSexo.addItem("Masculino");
		cbSexo.addItem("Feminino");
		cbSexo.setEnabled(false);
		cbSexo.setVisible(false);
		janela.add(cbSexo);
		
		lblCpf = new JLabel("CPF:");
		lblCpf.setBounds(320,180,40,25);
		lblCpf.setFont(fontLabel);
		lblCpf.setVisible(false);
		janela.add(lblCpf);
		
		txtCpf = new JFormattedTextField(mascara.formatar("###.###.###-##"));
		txtCpf.setBounds(360,180,130,25);
		txtCpf.setFont(fontText);
		txtCpf.setEditable(false);
		txtCpf.setVisible(false);
		janela.add(txtCpf);
		
		lblLogradouro = new JLabel("Logradouro:");
		lblLogradouro.setBounds(20,210,100,25);
		lblLogradouro.setFont(fontLabel);
		lblLogradouro.setVisible(false);
		janela.add(lblLogradouro);
		
		txtLogradouro = new JTextField();
		txtLogradouro.setBounds(170,210,220,25);
		txtLogradouro.setFont(fontText);
		txtLogradouro.setEditable(false);
		txtLogradouro.setVisible(false);
		janela.add(txtLogradouro);
		
		lblNumero = new JLabel("Nº:");
		lblNumero.setBounds(410,210,30,25);
		lblNumero.setFont(fontLabel);
		lblNumero.setVisible(false);
		janela.add(lblNumero);
		
		txtNumero = new JTextField();
		txtNumero.setBounds(440,210,50,25);
		txtNumero.setFont(fontText);
		txtNumero.setEditable(false);
		txtNumero.setVisible(false);
		janela.add(txtNumero);
		
		lblBairro = new JLabel("Bairro:");
		lblBairro.setBounds(20,240,60,25);
		lblBairro.setFont(fontLabel);
		lblBairro.setVisible(false);
		janela.add(lblBairro);
		
		txtBairro = new JTextField();
		txtBairro.setBounds(170,240,170,25);
		txtBairro.setFont(fontText);
		txtBairro.setEditable(false);
		txtBairro.setVisible(false);
		janela.add(txtBairro);
		
		lblCidade = new JLabel("Cidade:");
		lblCidade.setBounds(20,270,60,25);
		lblCidade.setFont(fontLabel);
		lblCidade.setVisible(false);
		janela.add(lblCidade);
		
		txtCidade = new JTextField();
		txtCidade.setBounds(170,270,170,25);
		txtCidade.setFont(fontText);
		txtCidade.setEditable(false);
		txtCidade.setVisible(false);
		janela.add(txtCidade);
		
		lblEstado = new JLabel("Estado:");
		lblEstado.setBounds(380,270,60,25);
		lblEstado.setFont(fontLabel);
		lblEstado.setVisible(false);
		janela.add(lblEstado);
		
		String[] estados = EstadosBrasileiros.estados();
		cbEstado = new JComboBox(estados);
		cbEstado.setBounds(440,270,50,25);
		cbEstado.setFont(fontText);
		cbEstado.setEnabled(false);
		cbEstado.setVisible(false);
		janela.add(cbEstado);
		
		lblTelefone = new JLabel("Telefone:");
		lblTelefone.setBounds(20,300,80,25);
		lblTelefone.setFont(fontLabel);
		lblTelefone.setVisible(false);
		janela.add(lblTelefone);
		
		txtTelefone = new JFormattedTextField(mascara.formatar("(##)####-####"));
		txtTelefone.setBounds(170,300,120,25);
		txtTelefone.setFont(fontText);
		txtTelefone.setEditable(false);
		txtTelefone.setVisible(false);
		janela.add(txtTelefone);
		
		lblCelular = new JLabel("Celular:");
		lblCelular.setBounds(310,300,70,25);
		lblCelular.setFont(fontLabel);
		lblCelular.setVisible(false);
		janela.add(lblCelular);
		
		txtCelular = new JFormattedTextField(mascara.formatar("(##)#####-####"));
		txtCelular.setBounds(370,300,120,25);
		txtCelular.setFont(fontText);
		txtCelular.setEditable(false);
		txtCelular.setVisible(false);
		janela.add(txtCelular);
		
		lblEmail = new JLabel("Email:");
		lblEmail.setBounds(20,330,50,25);
		lblEmail.setFont(fontLabel);
		lblEmail.setVisible(false);
		janela.add(lblEmail);
		
		txtEmail = new JTextField();
		txtEmail.setBounds(170,330,320,25);
		txtEmail.setFont(fontText);
		txtEmail.setEditable(false);
		txtEmail.setVisible(false);
		janela.add(txtEmail);
		
		btnNovo = new JButton("Novo");
		btnNovo.setBounds(565,40,120,30);
		btnNovo.setFont(fontButton);
		btnNovo.setToolTipText("Novo aluno");
		btnNovo.setIcon(new ImageIcon("imagens/icones_botoes/add.png"));
		btnNovo.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				funcao = "inserir";
				novoAluno();
			}
		});
		janela.add(btnNovo);
		
		btnInserir = new JButton("Salvar");
		btnInserir.setBounds(565,90,120,30);
		btnInserir.setFont(fontButton);
		btnInserir.setToolTipText("Inserir aluno");
		btnInserir.setIcon(new ImageIcon("imagens/icones_botoes/save.png"));
		btnInserir.setEnabled(false);
		btnInserir.setVisible(false);
		btnInserir.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				if(funcao == "inserir")
					inserirAluno();
				if(funcao == "salvar"){
					salvarAtualizacaoAluno(Integer.parseInt(tabela.getModel().getValueAt(0, 0).toString()));
				}
			}
		});
		janela.add(btnInserir);
		
		btnAtualizar = new JButton("Alterar");
		btnAtualizar.setBounds(565,140,120,30);
		btnAtualizar.setFont(fontButton);
		btnAtualizar.setToolTipText("Atualizar aluno");
		btnAtualizar.setIcon(new ImageIcon("imagens/icones_botoes/update.png"));
		btnAtualizar.setEnabled(false);
		btnAtualizar.setVisible(false);
		btnAtualizar.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				funcao = "salvar";
				atualizarAluno();
			}
		});
		janela.add(btnAtualizar);
		
		btnExcluir = new JButton("Excluir");
		btnExcluir.setBounds(565,190,120,30);
		btnExcluir.setFont(fontButton);
		btnExcluir.setToolTipText("Excluir aluno");
		btnExcluir.setIcon(new ImageIcon("imagens/icones_botoes/remove.png"));
		btnExcluir.setEnabled(false);
		btnExcluir.setVisible(false);
		btnExcluir.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				removerAluno();
			}
		});
		janela.add(btnExcluir);
		
		btnCancelar = new JButton("Cancelar");
		btnCancelar.setBounds(565,240,120,30);
		btnCancelar.setFont(fontButton);
		btnCancelar.setToolTipText("Cancelar operação");
		btnCancelar.setIcon(new ImageIcon("imagens/icones_botoes/remove.png"));
		btnCancelar.setEnabled(false);
		btnCancelar.setVisible(false);
		btnCancelar.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				cancelar();
			}
		});
		janela.add(btnCancelar);
		
	}
	
	public void trocarTipoPesquisa(JRadioButton rbTipo){
		if(rbTipo.getText().equals("Nome:")){
			lblPesquisar.setText(rbNome.getText());
			txtPesquisarCpf.setVisible(false);
			txtPesquisar.setVisible(true);
			txtPesquisar.setBounds(70,40,250,25);
		}
		if(rbTipo.getText().equals("RA:")){
			lblPesquisar.setText(rbRa.getText());
			txtPesquisarCpf.setVisible(false);
			txtPesquisar.setVisible(true);
			txtPesquisar.setBounds(50,40,80,25);
		}
		if(rbTipo.getText().equals("CPF:")){
			lblPesquisar.setText(rbCpf.getText());
			txtPesquisar.setVisible(false);
			txtPesquisarCpf.setVisible(true);
		}
	}
	
	public Aluno fromAlunoView(){
		Aluno aluno = new Aluno();
		aluno.setRa(Integer.parseInt(txtRa.getText()));
		aluno.setNome(txtNome.getText());
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Date data = null;
		try {
			data = sdf.parse(txtDataNascimento.getText());
		} catch (ParseException e) {
			e.printStackTrace();
		}
		aluno.setDataNascimento(data);
		if(cbSexo.getSelectedItem().equals("Masculino"))
			aluno.setSexo(1);
		if(cbSexo.getSelectedItem().equals("Feminino"))
			aluno.setSexo(2);
		aluno.setCpf(txtCpf.getText());
		aluno.setLogradouro(txtLogradouro.getText());
		aluno.setNumero(txtNumero.getText());
		aluno.setBairro(txtBairro.getText());
		aluno.setCidade(txtCidade.getText());
		aluno.setEstado(cbEstado.getSelectedItem().toString());
		aluno.setTelefone(txtTelefone.getText());
		aluno.setCelular(txtCelular.getText());
		aluno.setEmail(txtEmail.getText());
		return aluno;
	}
	
	public void toAlunoView(Aluno aluno){
		txtRa.setText(String.valueOf(aluno.getRa()));
		txtNome.setText(aluno.getNome());
		txtDataNascimento.setText("06/04/1994");
		if(aluno.getSexo() == 1)
			cbSexo.setToolTipText("Masculino");
		if(aluno.getSexo() == 2)
			cbSexo.setToolTipText("Feminino");
		txtCpf.setText(aluno.getCpf());
		txtLogradouro.setText(aluno.getLogradouro());
		txtNumero.setText(aluno.getNumero());
		txtBairro.setText(aluno.getBairro());
		txtCidade.setText(aluno.getCidade());
		cbEstado.setSelectedItem(aluno.getEstado());
		txtTelefone.setText(aluno.getTelefone());
		txtCelular.setText(aluno.getCelular());
		txtEmail.setText(aluno.getEmail());
	}
	
	public void pesquisaAproximada(){
		List<Aluno> listaAlunos = new ArrayList<Aluno>();
		if(rbRa.isSelected()){
			listaAlunos = ctrl.pesquisarPorRa(Integer.parseInt(txtPesquisar.getText()));
			AlunoView.showTableAluno(listaAlunos);
		}
		if(rbNome.isSelected()){
			listaAlunos = ctrl.pesquisarPorNome(txtPesquisar.getText());
			AlunoView.showTableAluno(listaAlunos);
		}
		if(rbCpf.isSelected()){
			listaAlunos = ctrl.pesquisarPorCpf(txtPesquisarCpf.getText());
			AlunoView.showTableAluno(listaAlunos);
		}
	}
	
	public void pesquisarAlunoTabela(){
		if(!janelaGrande)
			aumentarJanela();
		btnNovo.setEnabled(true);
		btnAtualizar.setEnabled(true);
		btnExcluir.setEnabled(true);
		Aluno aluno =  modelo.getAluno(tabela.getSelectedRow());
		ctrl.pesquisarPorRa(aluno.getRa());
		List<Aluno> listaAlunos = new ArrayList();
		listaAlunos.add(aluno);
		AlunoView.showTableAluno(listaAlunos);
		toAlunoView(aluno);
	}
	
	public void novoAluno(){
		if (!janelaGrande)
			aumentarJanela();
		btnNovo.setEnabled(false);
		btnInserir.setEnabled(true);
		btnAtualizar.setEnabled(false);
		btnExcluir.setEnabled(false);
		txtPesquisar.setEnabled(false);
		txtPesquisarCpf.setEnabled(false);
		tabela.setEnabled(false);
		limparCampos();
		liberarDigitacaoCampos();
		pesquisar();
	}
	
	public void inserirAluno(){
			ctrl.adicionar(fromAlunoView());
			btnInserir.setEnabled(false);
			limparCampos();
			bloquearDigitacaoCampos();
			btnNovo.setEnabled(true);
			tabela.setEnabled(true);
	}
	
	public void atualizarAluno(){
		liberarDigitacaoCampos();
		btnAtualizar.setEnabled(false);
		btnExcluir.setEnabled(false);
		btnInserir.setEnabled(true);
	}
	
	public void salvarAtualizacaoAluno(int ra){
		int respostaSair;
   	 	respostaSair =  JOptionPane.showOptionDialog(null, "Deseja realmente alterar este aluno?","Atenção",JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);  
	    if(respostaSair == JOptionPane.YES_OPTION){
	    	ctrl.atualizar(ra, fromAlunoView());
	    	btnInserir.setEnabled(false);
	    	limparCampos();
	    	bloquearDigitacaoCampos();
	    	btnNovo.setEnabled(true);
	    	tabela.setEnabled(true);
	    }
	}
	
	public void removerAluno(){
		int respostaSair;
   	 	respostaSair =  JOptionPane.showOptionDialog(null, "Deseja realmente excluir este aluno?","Atenção",JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);  
	    if(respostaSair == JOptionPane.YES_OPTION){  
	    	ctrl.remover(Integer.parseInt(txtRa.getText()));
	    	limparCampos();
	    	btnAtualizar.setEnabled(false);
	    	btnExcluir.setEnabled(false);
	    	pesquisar();
	    }
	}
	
	public void cancelar(){
		btnCancelar.setEnabled(false);
		txtPesquisar.setEnabled(true);
		txtPesquisarCpf.setEnabled(true);
		limparCampos();
		bloquearDigitacaoCampos();
		diminuirJanela();
		pesquisar();
		tabela.setEnabled(true);
	}
	
	public void aumentarJanela(){
		janelaGrande = true;
		this.setSize(700,650);
		barraRolagem.setLocation(barraRolagem.getX(), barraRolagem.getY() + 270);
		btnNovo.setEnabled(false);
		btnInserir.setVisible(true);
		btnInserir.setEnabled(false);
		btnAtualizar.setVisible(true);
		btnExcluir.setVisible(true);
		btnCancelar.setVisible(true);
		btnCancelar.setEnabled(true);
		lblRa.setVisible(true);
		txtRa.setVisible(true);
		lblNome.setVisible(true);
		txtNome.setVisible(true);
		lblDataNascimento.setVisible(true);
		txtDataNascimento.setVisible(true);
		lblSexo.setVisible(true);
		cbSexo.setVisible(true);
		lblCpf.setVisible(true);
		txtCpf.setVisible(true);
		lblLogradouro.setVisible(true);
		txtLogradouro.setVisible(true);
		lblNumero.setVisible(true);
		txtNumero.setVisible(true);
		lblBairro.setVisible(true);
		txtBairro.setVisible(true);
		lblCidade.setVisible(true);
		txtCidade.setVisible(true);
		lblEstado.setVisible(true);
		cbEstado.setVisible(true);
		lblTelefone.setVisible(true);
		txtTelefone.setVisible(true);
		lblCelular.setVisible(true);
		txtCelular.setVisible(true);
		lblEmail.setVisible(true);
		txtEmail.setVisible(true);
		this.setLocationRelativeTo(null);
	}
	
	public void diminuirJanela(){
		janelaGrande = false;
		btnNovo.setEnabled(true);
		btnInserir.setVisible(false);
		btnAtualizar.setVisible(false);
		btnAtualizar.setEnabled(false);
		btnExcluir.setVisible(false);
		btnExcluir.setEnabled(false);
		btnCancelar.setVisible(false);
		btnCancelar.setEnabled(false);
		lblRa.setVisible(false);
		txtRa.setVisible(false);
		lblNome.setVisible(false);
		txtNome.setVisible(false);
		lblDataNascimento.setVisible(false);
		txtDataNascimento.setVisible(false);
		lblSexo.setVisible(false);
		cbSexo.setVisible(false);
		lblCpf.setVisible(false);
		txtCpf.setVisible(false);
		lblLogradouro.setVisible(false);
		txtLogradouro.setVisible(false);
		lblNumero.setVisible(false);
		txtNumero.setVisible(false);
		lblBairro.setVisible(false);
		txtBairro.setVisible(false);
		lblCidade.setVisible(false);
		txtCidade.setVisible(false);
		lblEstado.setVisible(false);
		cbEstado.setVisible(false);
		lblTelefone.setVisible(false);
		txtTelefone.setVisible(false);
		lblCelular.setVisible(false);
		txtCelular.setVisible(false);
		lblEmail.setVisible(false);
		txtEmail.setVisible(false);
		this.setSize(700,400);
		barraRolagem.setLocation(barraRolagem.getX(), barraRolagem.getY() - 270);
		this.setLocationRelativeTo(null);
	}
	
	public void liberarDigitacaoCampos(){
		liberarCampos(true);
	}
	
	public void bloquearDigitacaoCampos(){
		liberarCampos(false);
	}
	
	public void liberarCampos(boolean estado){
		txtRa.setEditable(estado);
		txtNome.setEditable(estado);
		txtDataNascimento.setEditable(estado);
		cbSexo.setEnabled(estado);
		txtCpf.setEditable(estado);
		txtLogradouro.setEditable(estado);
		txtNumero.setEditable(estado);
		txtBairro.setEditable(estado);
		txtCidade.setEditable(estado);
		cbEstado.setEnabled(estado);
		txtTelefone.setEditable(estado);
		txtCelular.setEditable(estado);
		txtEmail.setEditable(estado);
	}
	
	public void limparCampos(){
		txtPesquisar.setText("");
		txtPesquisarCpf.setText("");
		txtRa.setText("");
		txtNome.setText("");
		txtDataNascimento.setText("");
		cbSexo.setToolTipText("");
		txtCpf.setText("");
		txtLogradouro.setText("");
		txtNumero.setText("");
		txtBairro.setText("");
		txtCidade.setText("");
		cbEstado.setSelectedItem("Masculino");
		txtTelefone.setText("");
		txtCelular.setText("");
		txtEmail.setText("");
	}
	
	public static void addTableAluno(Aluno aluno){
		modelo.addAluno(aluno);
	}
	
	
	public static void showTableAluno(List<Aluno> lista){
		AlunoDAOImpl dao = new AlunoDAOImpl();
		AlunoTableModel modeloPesquisa = new AlunoTableModel(lista);
		tabela.setModel(modeloPesquisa);
	}

}
