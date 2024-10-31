package model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Quarto {
    private String tipo; // Ex: "Solteiro", "Casal"
    private int classificacao; // Classificação do quarto
    private List<Reserva> reservas;

    public Quarto(String tipo, int classificacao) {
        this.tipo = tipo;
        this.classificacao = classificacao;
        this.reservas = new ArrayList<>();
    }

    public String getTipo() {
        return tipo;
    }

    public int getClassificacao() {
        return classificacao;
    }

    public boolean isDisponivel(Date dataInicio, Date dataFim) {
        // Verifica se o quarto está disponível para as datas solicitadas
        for (Reserva reserva : reservas) {
            if ((dataInicio.before(reserva.getDataFim()) && dataFim.after(reserva.getDataInicio()))) {
                return false; // Já existe uma reserva para o período
            }
        }
        return true; // Quarto disponível
    }

    public void reservar(Date dataInicio, Date dataFim) {
        // Cria uma nova reserva e a adiciona à lista de reservas
        Reserva novaReserva = new Reserva(this, dataInicio, dataFim);
        reservas.add(novaReserva);
    }

    public double calcularValorDiaria() {
        // Defina a lógica de cálculo da diária conforme a classificação
        return 100.0 * classificacao; // Exemplo de cálculo
    }

    public List<Reserva> getReservas() {
        return reservas;
    }

    public String getReservasDetalhes() {
        StringBuilder detalhes = new StringBuilder("Reservas:\n");
        for (Reserva reserva : reservas) {
            detalhes.append(String.format(" - %s a %s\n", reserva.getDataInicio(), reserva.getDataFim()));
        }
        return detalhes.toString();
    }

    public void cancelarReserva(Reserva reserva) {
        // Remove a reserva especificada da lista de reservas
        reservas.remove(reserva);
    }

    public boolean isReservado() {
        // Verifica se o quarto está reservado
        return !reservas.isEmpty();
    }
}
