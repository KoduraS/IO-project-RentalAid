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

    // -------------------------------------------------------------------------
    // Demonstracja: tworzenie kategorii i konfiguracja stawek cenowych
    // -------------------------------------------------------------------------
    public static void main(String[] args) {
        System.out.println("=== Demonstracja klasy Kategoria ===\n");

        // Stwórz kategorię i ustaw stawki
        Kategoria zimowe = new Kategoria("Zimowe");
        zimowe.ustawCeneZaDzien(15.0);
        zimowe.ustawCeneZaWyporzyczenie(10.0);
        zimowe.ustawCeneZaOpoznienie(30.0);
        zimowe.ustawCeneZaOpoznienieZaDzien(25.0);

        System.out.println("Kategoria:              " + zimowe.wezNazwa());
        System.out.println("Cena za dzień:          " + zimowe.wezCenaZaDzien()         + " zł");
        System.out.println("Cena za wypożyczenie:   " + zimowe.wezCenaZaWypozyczenie()  + " zł");
        System.out.println("Kara za opóźnienie:     " + zimowe.wezCenaZaOpoznienie()    + " zł");
        System.out.println("Kara/dzień opóźnienia:  " + zimowe.wezCenaZaOpoznienieZaDzien() + " zł");

        // Zmiana nazwy
        zimowe.ustawNazwa("Sporty Zimowe");
        System.out.println("\nPo zmianie nazwy:       " + zimowe.wezNazwa());

        // Dodaj etykietę i sprawdź kolekcję
        Etykieta etykieta = new Etykieta("Narty", "Atomic Redster 7 170cm", zimowe);
        zimowe.wezEtykiety().add(etykieta);
        System.out.println("Liczba etykiet:         " + zimowe.wezEtykiety().size());
    }
}
