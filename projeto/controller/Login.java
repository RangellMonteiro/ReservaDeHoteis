package controller;
import java.util.Scanner;
import java.util.HashMap;
import java.util.regex.Pattern;

public class Login extends Cadastro {
    String emailLogin;
    String senhaLogin;
    Scanner scanner = new Scanner(System.in);

    public Login() {
        usuariosDados = new HashMap<>();
        usuariosDados.put("emailadm@domain.com", "adm123");
        usuariosDados.put("emailteste@domain.com", "teste123");
    }

    private boolean validarEmail(String email) {
        String regex = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$";
        return Pattern.matches(regex, email);
    }

    public void logar() {
        System.out.println("Digite o email para logar:");
        emailLogin = scanner.nextLine();

        if (!validarEmail(emailLogin)) {
            System.out.println("Erro: Formato de email inválido.");
            return;
        }

        if (!usuariosDados.containsKey(emailLogin)) {
            System.out.println("Erro: Email não encontrado.");
            return;
        }

        System.out.println("Digite a senha para logar:");
        senhaLogin = scanner.nextLine();

        if (usuariosDados.get(emailLogin).equals(senhaLogin)) {
            if (emailLogin.equals("emailadm@domain.com")) {
                System.out.println("Usuário administrador logado com sucesso.");
            } else {
                System.out.println("Usuário logado com sucesso.");
            }
        } else {
            System.out.println("Erro: Senha incorreta.");
        }
    }
}
