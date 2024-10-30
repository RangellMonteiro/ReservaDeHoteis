package view;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import model.Hotel;

public class Hoteis {
    private final List<Hotel> hoteis;

    public Hoteis() {
        this.hoteis = new ArrayList<>();
    }

    public void adicionarHotel(Hotel hotel) {
        hoteis.add(hotel);
    }

    public Hotel buscarHotelPorNome(String nome) {
        for (Hotel hotel : hoteis) {
            if (hotel.getNome().equals(nome)) {
                System.out.println(hotel.getDetalhes());
                return hotel;
            }
        }
        return null;
    }

    public List<Hotel> filtrarHoteisPorClassificacao(int classificacaoMinima) {
        List<Hotel> hoteisFiltrados = new ArrayList<>();
        for (Hotel hotel : hoteis) {
            if (hotel.getClassificacao() >= classificacaoMinima) {
                hoteisFiltrados.add(hotel);
                System.out.println(hotel.getDetalhes());
            }
        }
        return hoteisFiltrados;
    }

    public void listarTodos() {
        for (Hotel hotel : hoteis) {
            System.out.println(hotel.getDetalhes());
        }
    }

    public List<Hotel> getHoteis() {
        return hoteis;
    }

    public static void main(String[] args) {
        Hoteis hoteis = new Hoteis();
        hoteis.adicionarHotel(new Hotel("Hotel A", null, 5));
        hoteis.adicionarHotel(new Hotel("Hotel B", null, 3));
        hoteis.adicionarHotel(new Hotel("Hotel C", null, 4));

        try (Scanner scanner = new Scanner(System.in)) {
            System.out.println("1-Buscar hotel\n2-Filtrar hoteis\n3-Visualizar toda a lista de hotéis.");
            int opcao = scanner.nextInt();
            
            switch (opcao) {
                case 1 -> {
                    System.out.println("Digite o nome do hotel:");
                    String nomeHotel = scanner.next();
                    hoteis.buscarHotelPorNome(nomeHotel);
                }
                case 2 -> {
                    System.out.println("Digite a classificação mínima:");
                    int classificacao = scanner.nextInt();
                    hoteis.filtrarHoteisPorClassificacao(classificacao);
                }
                case 3 -> hoteis.listarTodos();
                default -> System.out.println("Opção inválida.");
            }
        }
    }
}
