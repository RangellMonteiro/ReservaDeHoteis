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
        super();
        this.nome = nome;
        this.endereco = endereco;
        this.classificacao = classificacao;
        this.quartos = new ArrayList<>();
    }
    

    // Métodos get e set
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public int getClassificacao() {
        return classificacao;
    }

    public void setClassificacao(int classificacao) {
        this.classificacao = classificacao;
    }

    public void adicionarQuarto(Quarto quarto) {
        quartos.add(quarto);
    }

    public List<Quarto> buscarQuartoDisponivel(String tipo, Date dataInicio, Date dataFim) throws Exception {
        List<Quarto> quartosDisponiveis = new ArrayList<>();
        for (Quarto quarto : quartos) {
            if (quarto.getTipo().equalsIgnoreCase(tipo) && quarto.isDisponivel(dataInicio, dataFim)) {
                quartosDisponiveis.add(quarto);
            }
        }
        if (quartosDisponiveis.isEmpty()) {
            throw new Exception("Nenhum quarto disponível.");
        }
        return quartosDisponiveis;
    }

    // Obter detalhes do hotel
    public String getDetalhes() {
        return "Nome: " + nome + ", Endereço: " + endereco + ", Classificação: " + classificacao + " estrelas.";
    }
}
