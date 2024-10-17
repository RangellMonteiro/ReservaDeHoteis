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
        this.confirmada = false; 
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public Date getDataCheckIn() {
        return dataCheckIn;
    }

    public void setDataCheckIn(Date dataCheckIn) {
        this.dataCheckIn = dataCheckIn;
    }

    public Date getDataCheckOut() {
        return dataCheckOut;
    }

    public void setDataCheckOut(Date dataCheckOut) {
        this.dataCheckOut = dataCheckOut;
    }

    public Quarto getQuarto() {
        return quarto;
    }

    public void setQuarto(Quarto quarto) {
        this.quarto = quarto;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public double getValorTotal() {
        return valorTotal;
    }

    public boolean isConfirmada() {
        return confirmada;
    }


    public void confirmarReserva() throws Exception {
        if (!quarto.isDisponivel(dataCheckIn, dataCheckOut)) {
            System.out.println("Quarto não está disponível para as datas selecionadas.");
            return;
        }

        this.confirmada = true;
        System.out.println("Reserva confirmada para o quarto " + quarto.getTipo() + " de " + dataCheckIn + " a " + dataCheckOut);
    }

    public void cancelarReserva() {
        if (!confirmada) {
            System.out.println("Reserva ainda não foi confirmada, não é necessário cancelamento.");
            return;
        }

        this.confirmada = false;
        System.out.println("Reserva cancelada.");
    }

    public void alterarDatas(Date novaDataCheckIn, Date novaDataCheckOut) throws Exception {
        if (!quarto.isDisponivel(novaDataCheckIn, novaDataCheckOut)) {
            System.out.println("O quarto não está disponível nas novas datas.");
            return;
        }

        this.dataCheckIn = novaDataCheckIn;
        this.dataCheckOut = novaDataCheckOut;
        System.out.println("Datas da reserva alteradas.");
    }


    public boolean validarDatas() {
        if (dataCheckIn.after(dataCheckOut)) {
            System.out.println("A data de check-out deve ser após a data de check-in.");
            return false;
        }
        return true;
    }

    public String getDetalhes() {
        return "Reserva código: " + codigo +
               "\nCliente: " + cliente.getNome() +
               "\nQuarto: " + quarto.getTipo() +
               "\nCheck-in: " + dataCheckIn +
               "\nCheck-out: " + dataCheckOut +
               "\nValor Total: R$ " + valorTotal +
               "\nStatus: " + (confirmada ? "Confirmada" : "Não Confirmada");
    }
}
