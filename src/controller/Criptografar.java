package controller;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.io.UnsupportedEncodingException;
/**
 *
 * @author thiago
 * 
 * link: https://www.devmedia.com.br/como-funciona-a-criptografia-hash-em-java/31139
 * 
 */
public class Criptografar {
    
    public String criptografar(String senha){
        
        String senhaCript = null;
        
        try{
            
            MessageDigest algoritmo = MessageDigest.getInstance("SHA-256");
            byte messageDigest[] = algoritmo.digest(senha.getBytes("UTF-8"));
                       
            StringBuilder hexString = new StringBuilder();
            
            for (byte b : messageDigest) {
                hexString.append(String.format("%02X", 0xFF & b));
            }
            
            senhaCript = hexString.toString();
            
        }catch(NoSuchAlgorithmException erro){
            System.out.println("Erro de Criptografia: " + erro);
            
        }catch(UnsupportedEncodingException erro){
            System.out.println("Erro de Encoding: " + erro);
        }
        
        return senhaCript;
    }
    
}
