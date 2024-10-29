package view;
import controller.Cadastro;
import controller.Login;
import model.Hoteis;
import model.Hotel;
import model.SistemaReserva;

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
				System.out.println("1-Buscar hotel\n2-Filtrar hoteis\n3-Visualizar toda a lista de hóteis.");
				String escolha = scanner.nextLine();
				Hotel hotel = new Hotel("São Bernado", "Rua 1 de A", 3);
				Hotel hotel2 = new Hotel("São Cristo", "Rua 2 de B", 4);
				Hotel hotel3 = new Hotel("São Pedro", "Rua 3 de C", 5);
				Hoteis hoteis = new Hoteis();
				
				hoteis.adicionarHotel(hotel);
				hoteis.adicionarHotel(hotel2);
				hoteis.adicionarHotel(hotel3);
				
				if (escolha.equals("1")) {
					System.out.println("Digite o nome do hotel: ");
					String nomeHotel = scanner.nextLine();
					hoteis.buscarHotelPorNome(nomeHotel);
				}else if(escolha.equals("2")) {
					System.out.println("Filtrar hoteis");
					hoteis.filtrarHoteisPorClassificacao(4);
				}else if(escolha.equals("3")) {
					hoteis.listarTodos();
				}
				
				SistemaReserva sistemaReserva = new SistemaReserva();
				sistemaReserva.iniciar();
				scanner.close();
			}
			}
		 
		
	}
	


