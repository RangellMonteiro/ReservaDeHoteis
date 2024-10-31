package app;

import controller.HomeController;

public class Main {
    public static void main(String[] args) {
        // Obtém a instância única do HomeController e inicia o sistema conforme o
        // padrão singleton
        HomeController controller = HomeController.getInstance();
        controller.iniciar();
    }
}
