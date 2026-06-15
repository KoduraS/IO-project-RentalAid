package model;

import java.util.Date;

public class Wniosek {

    private int id;
    private Date nowaData;
    private Uzytkownik klient;
    private Wypozyczenie wypozyczenie;
    private WniosekStatus status;

    public Wniosek(Date nowaData, Uzytkownik klient, Wypozyczenie wypozyczenie) {
        this.nowaData = nowaData;
        this.klient = klient;
        this.wypozyczenie = wypozyczenie;
        this.status = WniosekStatus.NIEROZPATRZONY;
    }

    /**
     * Rozpatrz wniosek – akceptacja (true) lub odrzucenie (false).
     */
    public void rozpatrz(boolean decyzja) {
        if (decyzja) {
            this.status = WniosekStatus.ROZPATRZONY_POZYTYWNIE;
        } else {
            this.status = WniosekStatus.ROZPATRZONY_NEGATYWNIE;
        }
    }

    /**
     * Rozpatrz wniosek z częściową akceptacją – przyznanie alternatywnej daty.
     */
    public void rozpatrz(Date alternatywnaData) {
        this.nowaData = alternatywnaData;
        this.status = WniosekStatus.ROZPATRZONY_CZESCIOWO_POZYTYWNIE;
    }

    public int wezId() { return id; }

    public Date wezNowaDate() { return nowaData; }

    public Uzytkownik wezKlienta() { return klient; }

    public Wypozyczenie wezWypozyczenie() { return wypozyczenie; }

    public WniosekStatus wezStatus() { return status; }

    // -------------------------------------------------------------------------
    // Demonstracja: składanie i rozpatrywanie wniosków o przedłużenie
    // -------------------------------------------------------------------------
    public static void main(String[] args) {
        System.out.println("=== Demonstracja klasy Wniosek ===\n");

        // Przygotuj kontekst
        Kategoria sprzety = new Kategoria("Sprzęty zimowe");
        Etykieta narty = new Etykieta("Narty", "Atomic Redster 7 170cm", sprzety);
        narty.ustawCeneZaDzien(80.0);
        narty.ustawCeneZaWyporzyczenie(20.0);
        Sprzet betoniara = new Sprzet(narty);

        Uzytkownik klient = new Kreator("Krzysztof")
                .nazwisko("Dąbrowski").dokument("KD333333")
                .email("krzysiek@test.pl").haslo("haslo").stworz();

        long teraz = System.currentTimeMillis();
        Date start = new Date(teraz - 5L * 24 * 60 * 60 * 1000);  // 5 dni temu
        Date end   = new Date(teraz);                               // kończy się dziś
        klient.wypozyc(start, end, betoniara);
        Wypozyczenie wyp = klient.wezWypozyczenia().iterator().next();

        // --- Wniosek 1: prośba o przedłużenie o 3 dni ---
        Date zadanaData = new Date(teraz + 3L * 24 * 60 * 60 * 1000);
        Wniosek w1 = new Wniosek(zadanaData, klient, wyp);
        System.out.println("Wniosek 1 – status po złożeniu:   " + w1.wezStatus());

        // Operator akceptuje
        w1.rozpatrz(true);
        System.out.println("Wniosek 1 – po akceptacji:        " + w1.wezStatus());

        // --- Wniosek 2: odrzucenie ---
        Wniosek w2 = new Wniosek(zadanaData, klient, wyp);
        w2.rozpatrz(false);
        System.out.println("\nWniosek 2 – po odrzuceniu:        " + w2.wezStatus());

        // --- Wniosek 3: częściowa akceptacja – operator proponuje 1 dzień zamiast 3 ---
        Date alternatywa = new Date(teraz + 1L * 24 * 60 * 60 * 1000);
        Wniosek w3 = new Wniosek(zadanaData, klient, wyp);
        w3.rozpatrz(alternatywa);
        System.out.println("\nWniosek 3 – częściowa akceptacja: " + w3.wezStatus());
        System.out.println("  Nowa data zwrotu:                " + w3.wezNowaDate());
        System.out.println("  Klient:                          " + w3.wezKlienta().wezDane());
    }
}
