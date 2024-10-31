package controller;

import model.Hoteis;
import model.Hotel;
import model.Quarto;
import model.Reserva;
import view.HomeView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ReservaController {
    private Hoteis hoteis;
    private HomeView view;
    private List<Reserva> reservas; // Lista para armazenar as reservas

    public ReservaController(Hoteis hoteis) {
        this.hoteis = hoteis;
        this.view = new HomeView();
        this.reservas = new ArrayList<>(); // Inicializa a lista de reservas
    }

    public void realizarReserva() {
        System.out.println("Digite o nome do hotel: ");
        String nomeHotel = view.obterEscolha();

        try {
            Hotel hotel = hoteis.buscarHotelPorNome(nomeHotel);
            System.out.println("Digite o tipo de quarto (Solteiro, Casal): ");
            String tipoQuarto = view.obterEscolha();

            // Solicita as datas
            System.out.println("Digite a data de Check-in (formato: dd/MM/yyyy): ");
            String dataInicioStr = view.obterEscolha();
            System.out.println("Digite a data de Check-out (formato: dd/MM/yyyy): ");
            String dataFimStr = view.obterEscolha();

            // Formatação das datas
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            Date dataInicio = sdf.parse(dataInicioStr);
            Date dataFim = sdf.parse(dataFimStr);

            // Busca quartos disponíveis
            List<Quarto> quartosDisponiveis = hotel.buscarQuartoDisponivel(tipoQuarto, dataInicio, dataFim);

            if (quartosDisponiveis.isEmpty()) {
                System.out.println("Nenhum quarto disponível para as datas informadas.");
                return;
            }

            Quarto quarto = quartosDisponiveis.get(0); // Assume o primeiro quarto disponível
            quarto.reservar(dataInicio, dataFim); // Reserva o quarto

            // Cria a reserva e calcula o valor total
            Reserva reserva = new Reserva(quarto, dataInicio, dataFim);
            reservas.add(reserva); // Adiciona a reserva à lista
            double valorTotal = reserva.calcularValorTotal();
            double valorDiaria = quarto.calcularValorDiaria();

            // Exibe os valores para o usuário
            System.out.printf("Reserva realizada com sucesso!%nValor total: R$ %.2f%n", valorTotal);
            System.out.println("Valor de cada diária: R$ " + valorDiaria);
        } catch (Exception e) {
            System.out.println("Erro ao realizar a reserva: " + e.getMessage());
        }
    }

    // Método para listar as reservas
    public void listarReservas() {
        if (reservas.isEmpty()) {
            System.out.println("Não há reservas cadastradas.");
            return;
        }

        System.out.println("Reservas:");
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        for (int i = 0; i < reservas.size(); i++) {
            Reserva reserva = reservas.get(i);
            System.out.printf("Reserva %d: Quarto: %s, Data de Check-in: %s, Data de Check-out: %s%n",
                    i + 1,
                    reserva.getQuarto().getTipo(),
                    sdf.format(reserva.getDataInicio()),
                    sdf.format(reserva.getDataFim()));
        }
    }

    // Método para cancelar uma reserva
    public void cancelarReserva() {
        if (reservas.isEmpty()) {
            System.out.println("Não há reservas para cancelar.");
            return;
        }

        listarReservas(); // Lista as reservas para o usuário escolher
        System.out.println("Digite o número da reserva que deseja cancelar: ");
        int indiceReserva = Integer.parseInt(view.obterEscolha());

        if (indiceReserva < 1 || indiceReserva > reservas.size()) {
            System.out.println("Número de reserva inválido.");
            return;
        }

        // Recupera a reserva a ser cancelada
        Reserva reserva = reservas.get(indiceReserva - 1);
        Quarto quarto = reserva.getQuarto();

        // Cancela a reserva
        quarto.cancelarReserva(reserva); // Passa a reserva a ser cancelada
        reservas.remove(indiceReserva - 1); // Remove a reserva da lista

        System.out.println("Reserva cancelada com sucesso!");
    }
}
