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

    // -------------------------------------------------------------------------
    // Demonstracja: tworzenie etykiety, zmiana opisu i przypisanie do kategorii
    // -------------------------------------------------------------------------
    public static void main(String[] args) {
        System.out.println("=== Demonstracja klasy Etykieta ===\n");

        Kategoria sprzety = new Kategoria("Sprzęty zimowe");
        Etykieta narty = new Etykieta("Narty", "Atomic Redster 7 170cm", sprzety);
        narty.ustawCeneZaDzien(20.0);
        narty.ustawCeneZaWyporzyczenie(5.0);
        narty.ustawCeneZaOpoznienie(40.0);
        narty.ustawCeneZaOpoznienieZaDzien(30.0);

        System.out.println("Etykieta:             " + narty.wezNazwa());
        System.out.println("Opis:                 " + narty.wezOpis());
        System.out.println("Kategoria:            " + narty.wezKategoria().wezNazwa());
        System.out.println("Cena za dzień:        " + narty.wezCenaZaDzien()        + " zł");
        System.out.println("Cena za wypożyczenie: " + narty.wezCenaZaWypozyczenie() + " zł");

        // Zaktualizuj opis
        narty.ustawOpis("Atomic Redster 8 170cm");
        System.out.println("\nZaktualizowany opis:  " + narty.wezOpis());

        // Przypisz egzemplarz sprzętu
        Sprzet egzemplarz = new Sprzet(narty);
        narty.wezSprzety().add(egzemplarz);
        System.out.println("Liczba egzemplarzy:   " + narty.wezSprzety().size());

        // Przenieś etykietę do nowej kategorii
        Kategoria nastok = new Kategoria("Sprzęty na stok");
        narty.ustawKategoria(nastok);
        System.out.println("Nowa kategoria:       " + narty.wezKategoria().wezNazwa());
    }
}
