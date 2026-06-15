package model;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

public class Sprzet {

    private int id;
    private int dniEksploatacji;
    private Etykieta etykieta;
    private SprzetStatus status;
    private Optional<Wypozyczenie> wypozyczenie;
    private Set<Wypozyczenie> rezerwacje;

    private static double domyslnaCenaZaDzien = 10.0;
    private static double domyslnaCenaZaWyporzyczenie = 5.0;
    private static double domyslnaCenaZaOpoznienie = 20.0;
    private static double domyslnaCenaZaOpoznienieZaDzien = 15.0;

    public Sprzet(Etykieta etykieta) {
        this.etykieta = etykieta;
        this.dniEksploatacji = 0;
        this.status = SprzetStatus.W_MAGAZYNIE;
        this.wypozyczenie = Optional.empty();
        this.rezerwacje = new HashSet<>();
    }

    public int wezId() { return id; }

    public int wezDniEksploatacji() { return dniEksploatacji; }

    public Etykieta wezEtykieta() { return etykieta; }

    public SprzetStatus wezStatus() { return status; }

    public Wypozyczenie wezWypozyczenie() { return wypozyczenie.orElse(null); }

    public Set<Wypozyczenie> wezRezerwacje() { return rezerwacje; }

    public static double wezDomyslnaCeneZaDzien() { return domyslnaCenaZaDzien; }

    public static double wezDomyslnaCeneZaWyporzyczenie() { return domyslnaCenaZaWyporzyczenie; }

    public static double wezDomyslnaCeneZaOpoznienie() { return domyslnaCenaZaOpoznienie; }

    public static double wezDomyslnaCeneZaOpoznienieZaDzien() { return domyslnaCenaZaOpoznienieZaDzien; }

    public void ustawStatus(SprzetStatus status) { this.status = status; }

    public void rozpocznijWypozyczenie(Wypozyczenie wypozyczenie) {
        if (!czyMoznaWypozyczyc(wypozyczenie)) {
            throw new IllegalStateException("Sprzęt nie może zostać wypożyczony w podanym terminie");
        }
        this.wypozyczenie = Optional.of(wypozyczenie);
        this.status = SprzetStatus.CZEKA_NA_WYDANIE_KLIENTOWI;
        eksploatuj(1);
    }

    public void rozpocznijWypozyczenie() {
        this.status = SprzetStatus.CZEKA_NA_WYDANIE_KLIENTOWI;
    }

    public void zakoncWypozyczenie() {
        this.wypozyczenie = Optional.empty();
        this.status = SprzetStatus.W_DRODZE_DO_MAGAZYNU;
    }

    public void dodajRezerwacje(Wypozyczenie rezerwacja) {
        rezerwacje.add(rezerwacja);
    }

    public static void ustawDomyslnaCeneZaDzien(double cena) {
        domyslnaCenaZaDzien = cena;
    }

    public static void ustawDomyslnaCeneZaWyporzyczenie(double cena) {
        domyslnaCenaZaWyporzyczenie = cena;
    }

    public static void ustawDomyslnaCeneZaOpoznienie(double cena) {
        domyslnaCenaZaOpoznienie = cena;
    }

    public static void ustawDomyslnaCeneZaOpoznienieZaDzien(double cena) {
        domyslnaCenaZaOpoznienieZaDzien = cena;
    }

    private void eksploatuj(int dni) {
        this.dniEksploatacji += dni;
    }

    private boolean czyMoznaWypozyczyc(Wypozyczenie noweWypozyczenie) {
        if (this.wypozyczenie.isPresent()) return false;
        if (this.status == SprzetStatus.W_NAPRAWIE) return false;
        for (Wypozyczenie rez : rezerwacje) {
            boolean kolizja = noweWypozyczenie.wezStart().before(rez.wezEnd())
                    && noweWypozyczenie.wezEnd().after(rez.wezStart());
            if (kolizja) return false;
        }
        return true;
    }

    // -------------------------------------------------------------------------
    // Demonstracja: cykl życia egzemplarza – wypożyczenie i zwrot
    // -------------------------------------------------------------------------
    public static void main(String[] args) {
        System.out.println("=== Demonstracja klasy Sprzet ===\n");

        // Konfiguracja domyślnych stawek globalnych
        Sprzet.ustawDomyslnaCeneZaDzien(12.0);
        Sprzet.ustawDomyslnaCeneZaWyporzyczenie(6.0);
        System.out.println("Domyślna cena/dzień:         " + Sprzet.wezDomyslnaCeneZaDzien()        + " zł");
        System.out.println("Domyślna cena wypożyczenia:  " + Sprzet.wezDomyslnaCeneZaWyporzyczenie() + " zł");

        // Utwórz sprzęt
        Kategoria sprzety = new Kategoria("Sprzęty zimowe");
        Etykieta etk = new Etykieta("Narty", "Atomic Redster 7 170cm", sprzety);
        etk.ustawCeneZaDzien(18.0);
        Sprzet narty = new Sprzet(etk);

        System.out.println("\nStatus początkowy:    " + narty.wezStatus());
        System.out.println("Dni eksploatacji:     " + narty.wezDniEksploatacji());

        // Symuluj wypożyczenie przez Uzytkownik
        Uzytkownik klient = new Kreator("Anna")
                .nazwisko("Nowak").dokument("ABC123")
                .email("anna@test.pl").haslo("tajne").stworz();

        long teraz = System.currentTimeMillis();
        java.util.Date start = new java.util.Date(teraz);
        java.util.Date end   = new java.util.Date(teraz + 3L * 24 * 60 * 60 * 1000);
        klient.wypozyc(start, end, narty);

        System.out.println("\nPo wypożyczeniu:");
        System.out.println("  Status:               \t" + narty.wezStatus());
        System.out.println("  Dni eksploatacji:     \t" + narty.wezDniEksploatacji());
        System.out.println("  Aktualnie wypozyczone:\t" + (narty.wezWypozyczenie() != null));

        // Zakończ wypożyczenie
        narty.zakoncWypozyczenie();
        System.out.println("\nPo zwrocie:");
        System.out.println("  Status:                      \t" + narty.wezStatus());
        System.out.println("  Aktualnie wypozyczone:       \t" + (narty.wezWypozyczenie() != null));

        // Zmień status ręcznie
        narty.ustawStatus(SprzetStatus.W_NAPRAWIE);
        System.out.println("  Status po oddaniu do naprawy:\t" + narty.wezStatus());
    }
}
