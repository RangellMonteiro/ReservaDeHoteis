package controller;

import model.Hoteis;
import model.Hotel;
import controller.*;
import view.HomeView;

public class HomeController {

    private static HomeController instance; // Instância única
    private HomeView view;
    private Cadastro cadastro;
    private Login login;

    // Construtor privado para impedir criação externa de instâncias
    private HomeController() {
        this.view = new HomeView();
        this.cadastro = new Cadastro();
        this.login = new Login();
    }

    // Método para obter a instância única do controlador
    public static HomeController getInstance() {
        if (instance == null) {
            instance = new HomeController();
        }
        return instance;
    }

    public void iniciar() {
        String opcao = view.exibirMenuInicial();

        while (opcao.equals("1")) {
            cadastro.setEmail(view.solicitarEmail());
            cadastro.setSenha(view.solicitarSenha());
            cadastro.cadastrar();

            opcao = view.exibirMenuInicial();
        }

        if (opcao.equals("2")) {
            login.logar();
            Hoteis hoteis = new Hoteis();
            hoteis.adicionarHotel(new Hotel("São Bernardo", "Rua 1 de A", 3));
            hoteis.adicionarHotel(new Hotel("São Cristo", "Rua 2 de B", 4));
            hoteis.adicionarHotel(new Hotel("São Pedro", "Rua 3 de C", 5));

            view.exibirOpcoes();
            String escolha = view.obterEscolha();

            switch (escolha) {
                case "1":
                    System.out.println("Digite o nome do hotel: ");
                    hoteis.buscarHotelPorNome(view.obterEscolha());
                    break;
                case "2":
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

        view.fecharScanner();
    }
}
