package model;

public class Funcionario extends Usuario {
    private String cpf;
    private String cargo;
    private double salario;

    public Funcionario(String nome, String email, String senha, String cpf, String cargo, double salario) {
        super(nome, email, senha);
        this.cpf = cpf;
        this.cargo = cargo;
        this.salario = salario;
    }

    public void gerenciarReserva(Reserva reserva, String acao) throws Exception {
        switch (acao.toLowerCase()) {
            case "confirmar":
                reserva.confirmarReserva();
                break;
            case "cancelar":
                reserva.cancelarReserva();
                break;
            default:
                throw new Exception("Ação inválida.");
        }
    }

    @Override
    public String getDetalhes() {
        return "Funcionário: " + nome + ", Cargo: " + cargo + ", Salário: " + salario;
    }
}
