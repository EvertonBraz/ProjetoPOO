package boundary;

import javax.swing.*;
import javax.swing.text.*;
import javax.swing.event.*;

import entity.Usuario;
import persistence.UsuarioDAO;
import persistence.UsuarioDAOImpl;

import java.awt.*;
import java.awt.event.*;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Image;
import java.sql.*;


public class Login extends JFrame{
	
	private JLabel lblNomeClinica, lblImagem, lblImagemUsuario, lblImagemSenha;
	private JLabel lblUsuario, lblSenha, lblFundo;
	
	public JTextField txtUsuario;
	public JPasswordField txtSenha;
	
	private JButton btnOk, btnCancelar;
	
	public int tentativas = 0;
	
	public Login(){
		super("Login");
		this.setSize(360,200);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		Container container = getContentPane();
		container.setLayout(null);
		
		Font fontLabel = new Font("Comic Sans MS", Font.BOLD, 15);
		Font fontText = new Font("Comic Sans MS", Font.PLAIN, 15);
	       
		lblImagemUsuario = new JLabel();
		lblImagemUsuario.setIcon(new ImageIcon("imagens/icones_botoes/user.png"));
		lblImagemUsuario.setBounds(10,53,32,32);
		container.add( lblImagemUsuario );
	      
		lblImagemSenha = new JLabel();
		lblImagemSenha.setIcon(new ImageIcon("imagens/icones_botoes/password.png"));
		lblImagemSenha.setBounds(10,110,32,32);
		container.add( lblImagemSenha );	      
		
		ImageIcon imagemTituloJanela = new ImageIcon("imagens/icones_botoes/user.png");  
		setIconImage(imagemTituloJanela.getImage()); 
		
		lblUsuario = new JLabel("Login:");
		lblUsuario.setBounds(45,60,70,20);
		lblUsuario.setFont(fontLabel);
		container.add(lblUsuario);
		
		txtUsuario = new JTextField("");
		txtUsuario.setBounds(105,60,200,30);
		txtUsuario.setFont(fontText);
		container.add(txtUsuario)	;
		
		lblSenha = new JLabel("Senha:");
		lblSenha.setBounds(45,110,70,20);
		lblSenha.setFont(fontLabel);
		container.add(lblSenha);
		
		txtSenha = new JPasswordField("");
		txtSenha.setBounds(105,110,200,30);
		txtSenha.setFont(fontText);
		container.add(txtSenha);
		
		btnOk = new JButton("");
		btnOk.setIcon(new ImageIcon ("imagens/icones_botoes/ok.png"));
		btnOk.setBounds(310,59,32,32);
		container.add(btnOk);
		
		btnCancelar = new JButton("");
		btnCancelar.setIcon(new ImageIcon ("Imagens/icones_botoes/cancel.png"));
		btnCancelar.setBounds(310,108,32,32);
		container.add(btnCancelar);
		
		btnOk.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				efetuarLogin();
			}
		});
		
		btnCancelar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

			}
		});
		
	}
	    
	    
	public void efetuarLogin(){
		UsuarioDAO usuarioDAO = new UsuarioDAOImpl();
		Usuario usuario = usuarioDAO.pesquisarUsuario(txtUsuario.getText(), txtSenha.getText());
		if(usuario != null){
			PrincipalView chama = new PrincipalView();
			chama.setVisible(true);
			dispose();
		}
		else{
			tentativas += 1;
		}
		if(tentativas == 3){
			JOptionPane.showMessageDialog(null, "Número de tentativas excedido!");
			dispose();
		}
	}
	    
	public static void main(String[] args){
		Login chama = new Login();
	    chama.setVisible(true);
	}
}
