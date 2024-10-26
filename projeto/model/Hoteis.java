package model;

import java.util.ArrayList;
import java.util.List;

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
            }
        }
        return null;
    }

    public List<Hotel> filtrarHoteisPorClassificacao(int classificacaoMinima) {
        for (Hotel hotel : hoteis) {
            if (hotel.getClassificacao() >= classificacaoMinima) {
                System.out.println(hotel.getDetalhes());
            }
        }
        return null;
    }

    public void listarTodos() {
        for (Hotel hotel : hoteis) {
        	System.out.println(hotel.getDetalhes());

        };
    }

    public List<Hotel> getHoteis() {
        return hoteis;
    }
}
