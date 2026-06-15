package model;

import java.util.HashSet;
import java.util.Set;

public class Etykieta {

    private int id;
    private String nazwa;
    private String opis;
    private Kategoria kategoria;
    private Set<Sprzet> sprzety;
    private double cenaZaDzien;
    private double cenaZaWyporzyczenie;
    private double cenaZaOpoznienie;
    private double cenaZaOpoznienieZaDzien;

    public Etykieta(String nazwa, String opis, Kategoria kategoria) {
        this.nazwa = nazwa;
        this.opis = opis;
        this.kategoria = kategoria;
        this.sprzety = new HashSet<>();
    }

    public int wezId() {
        return id;
    }

    public String wezNazwa() {
        return nazwa;
    }

    public String wezOpis() {
        return opis;
    }

    public Kategoria wezKategoria() {
        return kategoria;
    }

    public Set<Sprzet> wezSprzety() {
        return sprzety;
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

    public void ustawOpis(String opis) {
        this.opis = opis;
    }

    public void ustawKategoria(Kategoria kategoria) {
        this.kategoria = kategoria;
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
