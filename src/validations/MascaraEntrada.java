package validations;

import javax.swing.text.MaskFormatter;

public class MascaraEntrada {

	public MaskFormatter formatar(String mascara){  
        
		MaskFormatter fMascara = new MaskFormatter();  
        try{  
            fMascara.setMask(mascara); //Atribui a mascara  
            fMascara.setPlaceholderCharacter(' '); //Caracter para preencimento   
        }  
        catch (Exception excecao) {  
        	excecao.printStackTrace();  
        }   
        return fMascara;  
	}         
	
}
