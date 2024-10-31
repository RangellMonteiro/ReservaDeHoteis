package controller;

import model.Hoteis;
import model.Hotel;
import model.Quarto;
import model.Reserva;
import view.HomeView;

public class HomeController {
    private static HomeController instance;
    private HomeView view;
    private Cadastro cadastro;
    private Login login;
    private ReservaController reservaController;

    private HomeController() {
        this.view = new HomeView();
        this.cadastro = new Cadastro();
        this.login = new Login();
    }

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
            boolean loggedIn = false; // Flag para controle do login

            while (!loggedIn) { // Loop para controle do login
                if (login.logar()) {
                    loggedIn = true;
                    Hoteis hoteis = new Hoteis();
                    hoteis.adicionarHotel(new Hotel("Laguna", "Rua 1 de A", 3));
                    hoteis.adicionarHotel(new Hotel("Lagoa Azul", "Rua 2 de B", 4));
                    hoteis.adicionarHotel(new Hotel("Meriva", "Rua 3 de C", 5));

                    // Adicione quartos aos hotéis (exemplo)
                    adicionarQuartos(hoteis);

                    reservaController = new ReservaController(hoteis); // Inicializa o ReservaController

                    while (true) {
                        view.exibirOpcoes();
                        String escolha = view.obterEscolha();

                        switch (escolha) {
                            case "1":
                                System.out.println("Digite o nome do hotel: ");
                                String nomeHotel = view.obterEscolha();
                                try {
                                    Hotel hotel = hoteis.buscarHotelPorNome(nomeHotel);
                                    System.out.println(hotel.getDetalhes()); // Exibe os detalhes do hotel encontrado
                                } catch (Exception e) {
                                    System.out.println(e.getMessage()); // Exibe a mensagem da exceção
                                    System.out.println("Tente novamente."); // Opção de retorno
                                }
                                break;
                            case "2":
                                System.out.println("Digite a classificação mínima:");
                                int classificacao = Integer.parseInt(view.obterEscolha());
                                hoteis.filtrarHoteisPorClassificacao(classificacao);
                                break;
                            case "3":
                                hoteis.listarTodos();
                                break;
                            case "4":
                                reservaController.realizarReserva(); // Chama o método de realizar reserva
                                break;
                            case "5": // Nova opção para listar reservas
                                reservaController.listarReservas();
                                break;
                            case "6": // Nova opção para cancelar reservas
                                reservaController.cancelarReserva();
                                break;
                            case "0":
                                System.out.println("Saindo do sistema.");
                                return; // Encerra o loop e o sistema
                            default:
                                System.out.println("Opção inválida. Tente novamente.");
                                break;
                        }
                    }
                } else {
                    System.out.println("Falha no login. Tente novamente.");
                    opcao = view.exibirMenuInicial(); // Exibe novamente o menu inicial
                }
            }
        }
        view.fecharScanner();
    }

    private void adicionarQuartos(Hoteis hoteis) {
        try {
            // Adiciona quartos aos hotéis
            Hotel laguna = hoteis.buscarHotelPorNome("Laguna");
            laguna.adicionarQuarto(new Quarto("Solteiro", 3));
            laguna.adicionarQuarto(new Quarto("Casal", 4));

            Hotel lagoaAzul = hoteis.buscarHotelPorNome("Lagoa Azul");
            lagoaAzul.adicionarQuarto(new Quarto("Solteiro", 4));
            lagoaAzul.adicionarQuarto(new Quarto("Casal", 5));

            Hotel meriva = hoteis.buscarHotelPorNome("Meriva");
            meriva.adicionarQuarto(new Quarto("Solteiro", 2));
            meriva.adicionarQuarto(new Quarto("Casal", 3));
        } catch (Exception e) {
            System.out.println("Erro ao adicionar quartos: " + e.getMessage());
        }
    }
}
