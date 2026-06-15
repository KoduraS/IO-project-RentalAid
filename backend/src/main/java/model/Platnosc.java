package model;

import java.util.Date;

public class Platnosc {
    public static int id_counter = 0;
    private int id;
    private Date data;
    private Wypozyczenie wypozyczenie;
    private double kwota;

    public Platnosc(Date data, Wypozyczenie wypozyczenie, double kwota) {
        this.data = data;
        this.wypozyczenie = wypozyczenie;
        this.kwota = kwota;
        this.id = id_counter;
        id_counter += 1;
    }

    public int wezId() { return id; }

    public Date wezDate() { return data; }

    public Wypozyczenie wezWypozyczenie() { return wypozyczenie; }

    public double wezKwota() { return kwota; }

    public String wezDane() {
        return "Płatność #" + id + " | " + data + " | " + kwota + " zł";
    }

    // -------------------------------------------------------------------------
    // Demonstracja: rejestrowanie płatności i podgląd danych transakcji
    // -------------------------------------------------------------------------
    public static void main(String[] args) {
        System.out.println("=== Demonstracja klasy Platnosc ===\n");

        // Przygotuj kontekst
        Kategoria sprzety = new Kategoria("Zimowe");
        Etykieta etk  = new Etykieta("Narty", "Atomic Redster 7 170cm", sprzety);
        etk.ustawCeneZaDzien(40.0);
        etk.ustawCeneZaWyporzyczenie(15.0);
        Sprzet narty = new Sprzet(etk);

        Uzytkownik klient = new Kreator("Ola")
                .nazwisko("Pawlak").dokument("OP222222")
                .email("ola@test.pl").haslo("haslo").stworz();

        long teraz = System.currentTimeMillis();
        Date start = new Date(teraz);
        Date end   = new Date(teraz + 3L * 24 * 60 * 60 * 1000);
        Wypozyczenie wyp = new Wypozyczenie(start, end, narty, klient);

        // Pierwsza częściowa płatność
        double kwota1 = 50.0;
        Platnosc p1 = new Platnosc(new Date(), wyp, kwota1);
        System.out.println("Płatność 1:");
        System.out.println("  " + p1.wezDane());
        System.out.printf("  Kwota:    %.2f zł%n", p1.wezKwota());
        System.out.println("  Data:     " + p1.wezDate());

        // Druga płatność – reszta
        // (doZaplaty przed wpłatami, liczymy ręcznie dla demonstracji)
        double kwotaTotal = 15.0 + 3 * 40.0; // cenaZaWyporzyczenie + 3 * cenaZaDzien
        Platnosc p2 = new Platnosc(new Date(), wyp, kwotaTotal - kwota1);
        System.out.println("\nPłatność 2:");
        System.out.println("  " + p2.wezDane());

        System.out.printf("%nŁączna wartość transakcji: %.2f zł%n",
                p1.wezKwota() + p2.wezKwota());
    }
}
