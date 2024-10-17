package model;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Quarto {
    private int numero;
    private String tipo;
    private double precoDiaria;
    private Map<Date, Boolean> disponibilidade;

    public Quarto(int numero, String tipo, double precoDiaria) {
        this.numero = numero;
        this.tipo = tipo;
        this.precoDiaria = precoDiaria;
        this.disponibilidade = new HashMap<>();
    }

    public boolean isDisponivel(Date dataInicio, Date dataFim) throws Exception {
        for (Date data = dataInicio; !data.after(dataFim); data = new Date(data.getTime() + (1000 * 60 * 60 * 24))) {
            Boolean disponivel = disponibilidade.get(data);
            if (disponivel == null || !disponivel) {
                throw new Exception("Quarto indisponível nas datas selecionadas.");
            }
        }
        return true;
    }

    public void atualizarDisponibilidade(Date data, boolean disponivel) {
        disponibilidade.put(data, disponivel);
    }

    public String getDetalhes() {
        return "Quarto número: " + numero + ", Tipo: " + tipo + ", Preço por diária: R$" + precoDiaria;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public double getPrecoDiaria() {
        return precoDiaria;
    }

    public void setPrecoDiaria(double precoDiaria) {
        this.precoDiaria = precoDiaria;
    }

    public Map<Date, Boolean> getDisponibilidade() {
        return disponibilidade;
    }

    public void setDisponibilidade(Map<Date, Boolean> disponibilidade) {
        this.disponibilidade = disponibilidade;
    }

    public String getTipo() {
        return tipo;
    }

}
