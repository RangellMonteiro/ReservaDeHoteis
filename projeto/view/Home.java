package view;

import controller.Cadastro;
import controller.Login;
import controller.ReservaController;
import model.Hoteis;
import model.Hotel;
import controller.*;

import java.util.Scanner;

public class Home {

    // Instância única da classe Home conforme o padrão singleton
    private static Home instanciaUnica;

    // Construtor privado para evitar criação de múltiplas instâncias
    private Home() {
    }

    // Método estático que retorna a única instância da classe
    public static Home getInstance() {
        if (instanciaUnica == null) {
            instanciaUnica = new Home();
        }
        return instanciaUnica;
    }

    // Método principal que integra as funcionalidades de cadastro, login e busca de
    // hotéis
    public void iniciar() {
        Scanner scanner = new Scanner(System.in);
        Cadastro cadastro = new Cadastro();
        Login login = new Login();

        System.out.println("1-Cadastro\n2-Login");
        String opcao = scanner.nextLine();

        while (opcao.equals("1")) {
            System.out.println("Por favor, digite seu email");
            cadastro.setEmail(scanner.nextLine());
            System.out.println("Por favor, digite sua senha");
            cadastro.setSenha(scanner.nextLine());
            cadastro.cadastrar();

            System.out.println("1-Cadastrar novo usuário\n2-Logar");
            opcao = scanner.nextLine();
        }

        if (opcao.equals("2")) {
            login.logar();
            Hoteis hoteis = new Hoteis();
            hoteis.adicionarHotel(new Hotel("São Bernado", "Rua 1 de A", 3));
            hoteis.adicionarHotel(new Hotel("São Cristo", "Rua 2 de B", 4));
            hoteis.adicionarHotel(new Hotel("São Pedro", "Rua 3 de C", 5));

            System.out.println("1-Buscar hotel\n2-Filtrar hoteis\n3-Visualizar toda a lista de hotéis.");
            String escolha = scanner.nextLine();

            switch (escolha) {
                case "1":
                    System.out.println("Digite o nome do hotel: ");
                    String nomeHotel = scanner.nextLine();
                    hoteis.buscarHotelPorNome(nomeHotel);
                    break;
                case "2":
                    System.out.println("Filtrar hotéis");
                    hoteis.filtrarHoteisPorClassificacao(4);
                    break;
                case "3":
                    hoteis.listarTodos();
                    break;
                default:
                    System.out.println("Opção inválida.");
                    break;
            }

            ReservaController reservaController = new ReservaController();
            reservaController.iniciar();
        }

        scanner.close();
    }

    public static void main(String[] args) {
        // Chama o método para iniciar a única instância da aplicação
        Home.getInstance().iniciar();
    }
}
