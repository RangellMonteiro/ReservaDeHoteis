package model;

import java.util.Date;

public class Reserva {
    private Quarto quarto;
    private Date dataInicio;
    private Date dataFim;

    public Reserva(Quarto quarto, Date dataInicio, Date dataFim) {
        this.quarto = quarto;
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
    }

    public double calcularValorTotal() {
        long diffInMillies = dataFim.getTime() - dataInicio.getTime();
        long diff = diffInMillies / (24 * 60 * 60 * 1000); // Converte milissegundos para dias
        return quarto.calcularValorDiaria() * diff; // Usa o valor da diária do quarto
    }

    public Quarto getQuarto() {
        return quarto;
    }

    public Date getDataInicio() {
        return dataInicio;
    }

    public Date getDataFim() {
        return dataFim;
    }
}
