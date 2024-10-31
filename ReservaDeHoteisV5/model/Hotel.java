package model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Hotel {
    private String nome;
    private String endereco;
    private int classificacao;
    private List<Quarto> quartos;

    public Hotel(String nome, String endereco, int classificacao) {
        this.nome = nome;
        this.endereco = endereco;
        this.classificacao = classificacao;
        this.quartos = new ArrayList<>();
    }

    public void adicionarQuarto(Quarto quarto) {
        quartos.add(quarto);
    }

    public List<Quarto> buscarQuartoDisponivel(String tipo, Date dataInicio, Date dataFim) {
        List<Quarto> quartosDisponiveis = new ArrayList<>();
        for (Quarto quarto : quartos) {
            // Verifica se o quarto é do tipo especificado e está disponível para as datas
            if (quarto.getTipo().equalsIgnoreCase(tipo) && quarto.isDisponivel(dataInicio, dataFim)) {
                quartosDisponiveis.add(quarto);
            }
        }
        return quartosDisponiveis;
    }

    public String getDetalhes() {
        return String.format("Hotel: %s, Endereço: %s, Classificação: %d", nome, endereco, classificacao);
    }

    public String getNome() {
        return nome;
    }

    public int getClassificacao() {
        return classificacao;
    }
}
