package view;

import java.util.Scanner;

public class HomeView {

    private Scanner scanner = new Scanner(System.in);

    public String exibirMenuInicial() {
        System.out.println("1-Cadastro\n2-Login");
        return scanner.nextLine();
    }

    public String solicitarEmail() {
        System.out.println("Por favor, digite seu email");
        return scanner.nextLine();
    }

    public String solicitarSenha() {
        System.out.println("Por favor, digite sua senha");
        return scanner.nextLine();
    }

    public void exibirOpcoes() {
        System.out.println("1-Buscar hotel\n2-Filtrar hoteis\n3-Visualizar toda a lista de hotéis.");
    }

    public String obterEscolha() {
        return scanner.nextLine();
    }

    public void fecharScanner() {
        scanner.close();
    }
}
