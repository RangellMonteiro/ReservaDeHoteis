package controller;
import java.util.HashMap;

public class Cadastro { 
	private String email;
	private String senha; 
	static HashMap<String, String> usuariosDados = new HashMap<String, String>();

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}
	 public void cadastrar(){

		if (usuariosDados.containsKey(email)) {
			System.out.println("Email já existe no sistema");
		}else{
			usuariosDados.put(email,senha);
		}
		
		for (String i : usuariosDados.keySet()) {
			  System.out.println("key: " + i + " value: " + usuariosDados.get(i));
			}
		
		
	}
}
