package model;

import java.util.ArrayList;
import java.util.List;

public class Hoteis {
    private List<Hotel> listaHoteis;

    public Hoteis() {
        this.listaHoteis = new ArrayList<>();
    }

    public void adicionarHotel(Hotel hotel) {
        listaHoteis.add(hotel);
    }

    public Hotel buscarHotelPorNome(String nome) throws Exception {
        for (Hotel hotel : listaHoteis) {
            if (hotel.getNome().equalsIgnoreCase(nome)) {
                return hotel;

            }
        }
        throw new Exception("Hotel não encontrado.");
    }

    public void filtrarHoteisPorClassificacao(int classificacao) {
        for (Hotel hotel : listaHoteis) {
            if (hotel.getClassificacao() >= classificacao) {
                System.out.println(hotel.getDetalhes());
                System.out.println("===================================");

            }
        }
    }

    public void listarTodos() {
        for (Hotel hotel : listaHoteis) {
            System.out.println(hotel.getDetalhes());
            System.out.println("===================================");
        }
    }

    public Hotel getHotel(String nome) {
        for (Hotel hotel : listaHoteis) {
            if (hotel.getNome().equalsIgnoreCase(nome)) {
                return hotel;
            }
        }
        return null; // Retorna null se não encontrar
    }
}
