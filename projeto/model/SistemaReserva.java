package model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class SistemaReserva {

    private List<Reserva> reservas;
    private List<Quarto> quartos;
    private List<Cliente> clientes;
    Scanner scanner = new Scanner(System.in);

    public SistemaReserva() {
        this.reservas = new ArrayList<>();
        this.quartos = new ArrayList<>();
        this.clientes = new ArrayList<>();
        this.scanner = new Scanner(System.in);
        inicializarQuartos();
        inicializarClientes();
    }

    private void inicializarQuartos() {
        quartos.add(new Quarto(101, "Simples", 100.0));
        quartos.add(new Quarto(102, "Duplo", 150.0));
        quartos.add(new Quarto(103, "Luxo", 300.0));
    }

    private void inicializarClientes() {
        clientes.add(new Cliente("Maria Silva", "maria@gmail.com", "1234", "111.222.333-44", "11987654321"));
    }

    public void iniciar() {
        while (true) {
            System.out.println("\nMenu de Reservas:");
            System.out.println("1. Criar nova reserva");
            System.out.println("2. Confirmar reserva");
            System.out.println("3. Cancelar reserva");
            System.out.println("4. Visualizar reservas");
            System.out.println("5. Sair");
            System.out.print("Escolha uma opção: ");
            
            String opcao = scanner.nextLine();
         

            switch (opcao) {
                case "1":
                    criarReserva();
                    break;
                case "2":
                    confirmarReserva();
                    break;
                case "3":
                    cancelarReserva();
                    break;
                case "4":
                    visualizarReservas();
                    break;
                case "5":
                    System.out.println("Encerrando o sistema.");
                    return;
                default:
                    System.out.println("Opção inválida.");
            }
        }
    }

    private void criarReserva() {
        System.out.println("Escolha um cliente:");
        for (int i = 0; i < clientes.size(); i++) {
            System.out.println((i + 1) + ". " + clientes.get(i).getDetalhes());
        }
        int opcaoCliente = scanner.nextInt();
        scanner.nextLine();

        if (opcaoCliente < 1 || opcaoCliente > clientes.size()) {
            System.out.println("Cliente inválido.");
            return;
        }
        Cliente clienteEscolhido = clientes.get(opcaoCliente - 1);

        System.out.println("Escolha um quarto:");
        for (int i = 0; i < quartos.size(); i++) {
            System.out.println((i + 1) + ". " + quartos.get(i).getDetalhes());
        }
        int opcaoQuarto = scanner.nextInt();
        scanner.nextLine();

        if (opcaoQuarto < 1 || opcaoQuarto > quartos.size()) {
            System.out.println("Quarto inválido.");
            return;
        }
        Quarto quartoEscolhido = quartos.get(opcaoQuarto - 1);

        System.out.print("Data de Check-in (dd/MM/yyyy): ");
        Date dataCheckIn = lerData();

        System.out.print("Data de Check-out (dd/MM/yyyy): ");
        Date dataCheckOut = lerData();

        String codigoReserva = "R" + (reservas.size() + 1);
        Reserva reserva = new Reserva(codigoReserva, dataCheckIn, dataCheckOut, quartoEscolhido, clienteEscolhido);
        
        if (reserva.validarDatas()) {
            reservas.add(reserva);
            clienteEscolhido.adicionarReserva(reserva);
            System.out.println("Reserva criada: " + reserva.getDetalhes());
        }
    }

    private void confirmarReserva() {
        System.out.print("Informe o código da reserva para confirmar: ");
        String codigo = scanner.nextLine();

        Reserva reserva = encontrarReservaPorCodigo(codigo);
        if (reserva != null) {
            try {
                reserva.confirmarReserva();
            } catch (Exception e) {
                System.out.println("Erro ao confirmar reserva: " + e.getMessage());
            }
        } else {
            System.out.println("Reserva não encontrada.");
        }
    }

    private void cancelarReserva() {
        System.out.print("Informe o código da reserva para cancelar: ");
        String codigo = scanner.nextLine();

        Reserva reserva = encontrarReservaPorCodigo(codigo);
        if (reserva != null) {
            try {
                reserva.getCliente().cancelarReserva(reserva);
                reserva.cancelarReserva();
            } catch (Exception e) {
                System.out.println("Erro ao cancelar reserva: " + e.getMessage());
            }
        } else {
            System.out.println("Reserva não encontrada.");
        }
    }

    private void visualizarReservas() {
        if (reservas.isEmpty()) {
            System.out.println("Nenhuma reserva disponível.");
        } else {
            System.out.println("Reservas:");
            for (Reserva reserva : reservas) {
                System.out.println(reserva.getDetalhes());
            }
        }
    }

    private Date lerData() {
        SimpleDateFormat formatoData = new SimpleDateFormat("dd/MM/yyyy");
        while (true) {
            try {
                return formatoData.parse(scanner.nextLine());
            } catch (ParseException e) {
                System.out.print("Data inválida. Tente novamente (dd/MM/yyyy): ");
            }
        }
    }

    private Reserva encontrarReservaPorCodigo(String codigo) {
        for (Reserva reserva : reservas) {
            if (reserva.getCodigo().equalsIgnoreCase(codigo)) {
                return reserva;
            }
        }
        return null;
    }
}
