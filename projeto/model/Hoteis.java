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
            if (hotel.getDetalhes().contains(nome)) {
                return hotel;
            }
        }
        return null;
    }

    public List<Hotel> filtrarHoteisPorClassificacao(int classificacaoMinima) {
        List<Hotel > filtrados = new ArrayList<>();
        for (Hotel hotel : hoteis) {
            if (hotel.getClassificacao() >= classificacaoMinima) {
                filtrados.add(hotel);
            }
        }
        return filtrados;
    }

    public List<Hotel> listarTodos() {
        return hoteis;
    }

    public List<Hotel> getHoteis() {
        return hoteis;
    }
}
