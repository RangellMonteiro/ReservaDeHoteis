package view;
import controller.Cadastro;
import controller.Login;
import java.util.Scanner;

public class Home {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		Cadastro cadastro = new Cadastro();
		Login login = new Login();
		
		System.out.println("1-Cadastro\n"
				+ "2-Login");
		String opcao = scanner.nextLine();
		while (opcao.equals("1")) { 
			
			System.out.println("Por favor, digite seu email");
			cadastro.setEmail(scanner.nextLine());
			System.out.println("Por favor, digite sua senha");
			cadastro.setSenha(scanner.nextLine()); 
			cadastro.cadastrar();
		
			System.out.println("1-Cadastrar novo usuário\n"
					+ "2-Logar");
			opcao = scanner.nextLine();
			}
		
			if (opcao.equals("2")) {
				login.logar();
			}
		 
		scanner.close();
	}
	

}
