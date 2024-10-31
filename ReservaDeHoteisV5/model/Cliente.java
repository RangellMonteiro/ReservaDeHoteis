package model;

import java.util.ArrayList;
import java.util.List;

public class Cliente extends Usuario {
    private String cpf;
    private String telefone;
    private List<Reserva> reservas;

    public Cliente(String nome, String email, String senha, String cpf, String telefone) {
        super(nome, email, senha);
        this.cpf = cpf;
        this.telefone = telefone;
        this.reservas = new ArrayList<>();
    }

    public void adicionarReserva(Reserva reserva) {
        reservas.add(reserva);
    }

    public void cancelarReserva(Reserva reserva) throws Exception {
        if (!reservas.contains(reserva)) {
            throw new Exception("Reserva não encontrada.");
        }
        reservas.remove(reserva);
    }

    @Override
    public String getDetalhes() {
        return "Cliente: " + nome + ", CPF: " + cpf + ", Telefone: " + telefone;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public List<Reserva> getReservas() {
        return reservas;
    }

    public void setReservas(List<Reserva> reservas) {
        this.reservas = reservas;
    }
}
