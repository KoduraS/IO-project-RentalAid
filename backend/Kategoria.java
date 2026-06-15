package model;

import java.util.HashSet;
import java.util.Set;

public class Kategoria {

    private int id;
    private String nazwa;
    private Set<Etykieta> etykiety;
    private double cenaZaDzien;
    private double cenaZaWyporzyczenie;
    private double cenaZaOpoznienie;
    private double cenaZaOpoznienieZaDzien;

    public Kategoria(String nazwa) {
        this.nazwa = nazwa;
        this.etykiety = new HashSet<>();
    }

    public int wezId() {
        return id;
    }

    public String wezNazwa() {
        return nazwa;
    }

    public Set<Etykieta> wezEtykiety() {
        return etykiety;
    }

    public double wezCenaZaDzien() {
        return cenaZaDzien;
    }

    public double wezCenaZaWypozyczenie() {
        return cenaZaWyporzyczenie;
    }

    public double wezCenaZaOpoznienie() {
        return cenaZaOpoznienie;
    }

    public double wezCenaZaOpoznienieZaDzien() {
        return cenaZaOpoznienieZaDzien;
    }

    public void ustawNazwa(String nazwa) {
        this.nazwa = nazwa;
    }

    public void ustawCeneZaDzien(double cena) {
        this.cenaZaDzien = cena;
    }

    public void ustawCeneZaWyporzyczenie(double cena) {
        this.cenaZaWyporzyczenie = cena;
    }

    public void ustawCeneZaOpoznienie(double cena) {
        this.cenaZaOpoznienie = cena;
    }

    public void ustawCeneZaOpoznienieZaDzien(double cena) {
        this.cenaZaOpoznienieZaDzien = cena;
    }
}
