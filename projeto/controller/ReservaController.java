package controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import model.Cliente;
import model.Quarto;
import model.Reserva;

public class ReservaController {

    private List<Reserva> reservas;
    private List<Quarto> quartos;
    private List<Cliente> clientes;
    Scanner scanner = new Scanner(System.in);
    SimpleDateFormat formatoData = new SimpleDateFormat("dd/MM/yyyy");

    public ReservaController() {
        this.reservas = new ArrayList<>();
        this.quartos = new ArrayList<>();
        this.clientes = new ArrayList<>();
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
        Reserva reserva = new Reserva(codigoReserva, dataCheckIn, dataCheckOut, quartoEscolhido);

        if (reserva.validarDatas()) {
            // Calcula o valor total da reserva e exibe
            double valorTotal = calcularValorDiarias(quartoEscolhido.getPrecoDiaria(), dataCheckIn, dataCheckOut);
            reserva.setValorTotal(valorTotal);

            reservas.add(reserva);
            clienteEscolhido.adicionarReserva(reserva);
            System.out.println("Reserva criada: " + reserva.getDetalhes());
        } else {
            System.out.println("As datas de reserva são inválidas.");
        }
    }

    private double calcularValorDiarias(double precoDiaria, Date dataCheckIn, Date dataCheckOut) {
        long diferencaEmMillis = dataCheckOut.getTime() - dataCheckIn.getTime();
        long diferencaEmDias = diferencaEmMillis / (1000 * 60 * 60 * 24); // Convertendo de millis para dias
        return precoDiaria * diferencaEmDias;
    }

    private void confirmarReserva() {
        System.out.print("Informe o código da reserva para confirmar: ");
        String codigo = scanner.nextLine();

        Reserva reserva = encontrarReservaPorCodigo(codigo);
        if (reserva != null) {
            try {
                reserva.confirmarReserva();
                System.out.println("Reserva confirmada: " + reserva.getDetalhes());
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
                System.out.println("Reserva cancelada: " + reserva.getDetalhes());
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
