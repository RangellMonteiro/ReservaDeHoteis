package controller;
import java.util.Scanner;
import java.util.HashMap;

public class Login extends Cadastro {
    String emailLogin;
    String senhaLogin;
    Scanner scanner = new Scanner(System.in);

    public Login() {
        usuariosDados = new HashMap<>();
        usuariosDados.put("emailadm", "adm123");
        usuariosDados.put("emailteste", "teste123");
    }

    public void logar() {
        System.out.println("Digite o email para logar:");
        emailLogin = scanner.nextLine();
        
        if (!usuariosDados.containsKey(emailLogin)) {
            System.out.println("Erro: Email não encontrado.");
            return;
        }

        System.out.println("Digite a senha para logar:");
        senhaLogin = scanner.nextLine();

        if (usuariosDados.get(emailLogin).equals(senhaLogin)) {
            if (emailLogin.equals("emailadm")) {
                System.out.println("Usuário administrador logado com sucesso.");
            } else {
                System.out.println("Usuário logado com sucesso.");
            }
        } else {
            System.out.println("Erro: Senha incorreta.");
        }
    }
}