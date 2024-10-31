package view;

import java.util.Scanner;

public class HomeView {
    private Scanner scanner;

    public HomeView() {
        this.scanner = new Scanner(System.in);
    }

    public String exibirMenuInicial() {
        System.out.println("1 - Cadastrar");
        System.out.println("2 - Login");
        System.out.println("Escolha uma opção:");
        return scanner.nextLine();
    }

    public String solicitarEmail() {
        System.out.println("Digite seu email:");
        return scanner.nextLine();
    }

    public String solicitarSenha() {
        System.out.println("Digite sua senha:");
        return scanner.nextLine();
    }

    public void fecharScanner() {
        scanner.close();
    }

    public void exibirOpcoes() {
        System.out.println("1 - Buscar Hotel");
        System.out.println("2 - Filtrar Hotéis por Classificação");
        System.out.println("3 - Listar todos os Hotéis");
        System.out.println("4 - Realizar Reserva");
        System.out.println("5- Listar reservas");
        System.out.println("6 - Cancelar reserva");
    }

    public String obterEscolha() {
        return scanner.nextLine();
    }
}
