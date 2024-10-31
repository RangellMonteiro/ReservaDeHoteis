package model;

import java.util.Date;

public class Reserva {
    private String codigo;
    private Date dataCheckIn;
    private Date dataCheckOut;
    private Quarto quarto;
    private Cliente cliente;
    private double valorTotal;
    private boolean confirmada;

    public Reserva(String codigo, Date dataCheckIn, Date dataCheckOut, Quarto quarto, Cliente cliente) {
        this.codigo = codigo;
        this.dataCheckIn = dataCheckIn;
        this.dataCheckOut = dataCheckOut;
        this.quarto = quarto;
        this.cliente = cliente;
        this.confirmada = false; // Inicialmente a reserva não está confirmada
    }

    public String getCodigo() {
        return codigo;
    }

    public Date getDataCheckIn() {
        return dataCheckIn;
    }

    public Date getDataCheckOut() {
        return dataCheckOut;
    }

    public Quarto getQuarto() {
        return quarto;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public double getValorTotal() {
        return valorTotal;
    }

    public boolean isConfirmada() {
        return confirmada;
    }

    public void setValorTotal(double valorTotal) {
        this.valorTotal = valorTotal;
    }

    public boolean validarDatas() {
        // Verifica se a data de check-out é posterior à data de check-in
        return dataCheckOut.after(dataCheckIn);
    }

    public void confirmarReserva() throws Exception {
        if (confirmada) {
            throw new Exception("A reserva já está confirmada.");
        }
        confirmada = true;
    }

    public void cancelarReserva() {
        confirmada = false; // Pode implementar lógica adicional para cancelamento
    }

    public String getDetalhes() {
        String estado = confirmada ? "Confirmada" : "Pendente";
        return String.format(
                "Código: %s, Cliente: %s, Quarto: %s, Data Check-in: %s, Data Check-out: %s, Valor Total: %.2f, Estado: %s",
                codigo,
                cliente.getNome(),
                quarto.getNumero() + " (" + quarto.getTipo() + ")",
                dataCheckIn,
                dataCheckOut,
                valorTotal,
                estado);
    }
}
