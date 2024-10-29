package view;
import java.util.Scanner;
import model.Hotel;
import model.Hoteis;

public class Home2 {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
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
