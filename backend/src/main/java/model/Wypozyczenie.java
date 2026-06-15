package model;

import java.util.Date;

public class Wypozyczenie {

    private int id;
    private Date start;
    private Date end;
    private double wplacono;
    private double doZaplaty;
    private Sprzet sprzet;
    private Uzytkownik uzytkownik;
    private WypozyczenieStatus status;

    public Wypozyczenie(Date start, Date end, Sprzet sprzet, Uzytkownik uzytkownik) {
        this.start = start;
        this.end = end;
        this.sprzet = sprzet;
        this.uzytkownik = uzytkownik;
        this.wplacono = 0.0;
        this.doZaplaty = obliczKwoteDoZaplaty();
        this.status = WypozyczenieStatus.REZERWACJA;
    }

    private double obliczKwoteDoZaplaty() {
        if (start == null || end == null) return 0.0;
        long miliseconds = end.getTime() - start.getTime();
        long dni = miliseconds / (1000 * 60 * 60 * 24);
        double cenaZaDzien = sprzet.wezEtykieta() != null
                ? sprzet.wezEtykieta().wezCenaZaDzien()
                : Sprzet.wezDomyslnaCeneZaDzien();
        double cenaZaWypozyczenie = sprzet.wezEtykieta() != null
                ? sprzet.wezEtykieta().wezCenaZaWypozyczenie()
                : Sprzet.wezDomyslnaCeneZaWyporzyczenie();
        return cenaZaWypozyczenie + (dni * cenaZaDzien);
    }

    public void wplac(double kwota) {
        if (kwota <= 0) throw new IllegalArgumentException("Kwota musi być dodatnia");
        this.wplacono += kwota;
        aktualizujStatus();
    }

    public void wydaj() {
        this.status = WypozyczenieStatus.W_TRAKCIE;
    }

    public double wezDoZaplaty() {
        return Math.max(0, doZaplaty - wplacono);
    }

    private void aktualizujStatus() {
        Date teraz = new Date();
        if (wplacono >= doZaplaty) {
            status = WypozyczenieStatus.OPLACONE;
        } else if (teraz.after(end)) {
            status = WypozyczenieStatus.PRZEKROCZONO_TERMIN;
        } else if (status == WypozyczenieStatus.W_TRAKCIE) {
            status = WypozyczenieStatus.NIE_OPLACONE;
        }
    }

    // --- Gettery ---

    public int wezId() { return id; }

    public Date wezStart() { return start; }

    public Date wezEnd() { return end; }

    public double wezWplacono() { return wplacono; }

    public Sprzet wezSprzet() { return sprzet; }

    public Uzytkownik wezUzytkownik() { return uzytkownik; }

    public WypozyczenieStatus wezStatus() { return status; }

    // -------------------------------------------------------------------------
    // Demonstracja: tworzenie wypożyczenia, wpłata i śledzenie statusu
    // -------------------------------------------------------------------------
    public static void main(String[] args) {
        System.out.println("=== Demonstracja klasy Wypozyczenie ===\n");

        // Przygotuj infrastrukturę
        Kategoria sprzety = new Kategoria("Sprzęty zimowe");
        Etykieta narty = new Etykieta("Narty", "Atomic Redster 7 170cm", sprzety);
        narty.ustawCeneZaDzien(30.0);
        narty.ustawCeneZaWyporzyczenie(10.0);
        Sprzet kamera   = new Sprzet(narty);

        Uzytkownik klient = new Kreator("Tomasz")
                .nazwisko("Malinowski").dokument("TM555555")
                .email("tomasz@test.pl").haslo("haslo").stworz();

        // Utwórz wypożyczenie na 5 dni
        long teraz = System.currentTimeMillis();
        Date start = new Date(teraz);
        Date end   = new Date(teraz + 5L * 24 * 60 * 60 * 1000);
        Wypozyczenie wyp = new Wypozyczenie(start, end, kamera, klient);

        System.out.println("Status po utworzeniu: " + wyp.wezStatus());
        System.out.printf("Kwota do zapłaty:     %.2f zł%n", wyp.wezDoZaplaty());
        System.out.printf("Wpłacono:             %.2f zł%n", wyp.wezWplacono());

        // Wydaj sprzęt klientowi
        wyp.wydaj();
        System.out.println("\nPo wydaniu:           " + wyp.wezStatus());

        // Częściowa wpłata
        wyp.wplac(50.0);
        System.out.printf("\nPo wpłacie 50 zł:%n");
        System.out.printf("  Wpłacono:           %.2f zł%n", wyp.wezWplacono());
        System.out.printf("  Pozostało:          %.2f zł%n", wyp.wezDoZaplaty());
        System.out.println("  Status:             " + wyp.wezStatus());

        // Dopłata do pełnej kwoty
        wyp.wplac(wyp.wezDoZaplaty());
        System.out.printf("%nPo pełnej opłacie:%n");
        System.out.printf("  Pozostało:          %.2f zł%n", wyp.wezDoZaplaty());
        System.out.println("  Status:             " + wyp.wezStatus());
    }
}
