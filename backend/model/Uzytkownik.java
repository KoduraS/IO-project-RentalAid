package model;

import java.util.Date;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

public class Uzytkownik {

    private int id;
    private String imie;
    private String nazwisko;
    private String dokument;
    private String email;
    private String haslo;
    private Optional<String> nrTel;
    private Optional<String> adres;
    private Optional<Date> blokada;
    private Set<Wypozyczenie> wypozyczenia;
    private Set<Platnosc> platnosci;
    private Set<Wniosek> wnioski;

    private Uzytkownik(String imie, String nazwisko, String dokument,
                       String email, String haslo, String nrTel, String adres) {
        this.imie = imie;
        this.nazwisko = nazwisko;
        this.dokument = dokument;
        this.email = email;
        this.haslo = haslo;
        this.nrTel = Optional.ofNullable(nrTel);
        this.adres = Optional.ofNullable(adres);
        this.blokada = Optional.empty();
        this.wypozyczenia = new HashSet<>();
        this.platnosci = new HashSet<>();
        this.wnioski = new HashSet<>();
    }

    /** Używany przez Kreator */
    static Uzytkownik stworz(String imie, String nazwisko, String dokument,
                              String email, String haslo, String nrTel, String adres) {
        return new Uzytkownik(imie, nazwisko, dokument, email, haslo, nrTel, adres);
    }

    public boolean sprawdzHaslo(String haslo) {
        return this.haslo.equals(haslo);
    }

    public void zablokuj(Date data) {
        this.blokada = Optional.ofNullable(data);
    }

    public void oplac(Wypozyczenie wypozyczenie, double kwota) {
        wypozyczenie.wplac(kwota);
        Platnosc platnosc = new Platnosc(new Date(), wypozyczenie, kwota);
        platnosci.add(platnosc);
    }

    public void wypozyc(Date start, Date end, Sprzet sprzet) {
        Wypozyczenie wypozyczenie = new Wypozyczenie(start, end, sprzet, this);
        sprzet.rozpocznijWypozyczenie(wypozyczenie);
        wypozyczenia.add(wypozyczenie);
    }

    public void rezerwuj(Date start, Date end, Sprzet sprzet) {
        Wypozyczenie rezerwacja = new Wypozyczenie(start, end, sprzet, this);
        sprzet.dodajRezerwacje(rezerwacja);
        wypozyczenia.add(rezerwacja);
    }

    // --- Gettery ---

    public int wezId() { return id; }

    public String wezImie() { return imie; }

    public String wezNazwisko() { return nazwisko; }

    public String wezDokument() { return dokument; }

    public String wezEmail() { return email; }

    public String wezNrTel() { return nrTel.orElse(null); }

    public String wezAdres() { return adres.orElse(null); }

    public String wezDane() {
        return imie + " " + nazwisko + " (" + dokument + ", " + email + ")";
    }

    public Date wezBlokade() { return blokada.orElse(null); }

    public Set<Wypozyczenie> wezWypozyczenia() { return wypozyczenia; }

    public Set<Platnosc> wezPlatnosci() { return platnosci; }

    public Set<Wniosek> wezWnioski() { return wnioski; }

    // --- Settery ---

    public void ustawImie(String imie) { this.imie = imie; }

    public void ustawNazwisko(String nazwisko) { this.nazwisko = nazwisko; }

    public void ustawDokument(String dokument) { this.dokument = dokument; }

    public void ustawEmail(String email) { this.email = email; }

    public void ustawHaslo(String haslo) { this.haslo = haslo; }

    public void ustawNrTel(String nrTel) { this.nrTel = Optional.ofNullable(nrTel); }

    public void ustawAdres(String adres) { this.adres = Optional.ofNullable(adres); }

    public void ustawBlokade(Date data) { this.blokada = Optional.ofNullable(data); }

    // -------------------------------------------------------------------------
    // Demonstracja: rejestracja, logowanie, wypożyczenie i blokada użytkownika
    // -------------------------------------------------------------------------
    public static void main(String[] args) {
        System.out.println("=== Demonstracja klasy Uzytkownik ===\n");

        // Rejestracja przez Kreator
        Uzytkownik jan = new Kreator("Jan")
                .nazwisko("Kowalski")
                .dokument("PA1234567")
                .email("jan.kowalski@example.pl")
                .haslo("bezpieczneHaslo!")
                .nrTel("600-100-200")
                .adres("ul. Krakowska 1, Kraków")
                .stworz();

        System.out.println("Dane użytkownika: " + jan.wezDane());
        System.out.println("Telefon:          " + jan.wezNrTel());
        System.out.println("Adres:            " + jan.wezAdres());

        // Weryfikacja hasła
        System.out.println("\nSprawdzanie hasła (poprawne):   " + jan.sprawdzHaslo("bezpieczneHaslo!"));
        System.out.println("Sprawdzanie hasła (błędne):     " + jan.sprawdzHaslo("złeHaslo"));

        // Wypożyczenie sprzętu
        Kategoria sprzety = new Kategoria("Sprzęty zimowe");
        Etykieta narty = new Etykieta("Narty", "Atomic Redster 7 170cm", sprzety);
        narty.ustawCeneZaDzien(25.0);
        Sprzet projektor = new Sprzet(narty);

        long teraz = System.currentTimeMillis();
        Date start = new Date(teraz);
        Date end   = new Date(teraz + 2L * 24 * 60 * 60 * 1000);
        jan.wypozyc(start, end, projektor);

        System.out.println("\nLiczba wypożyczeń:  " + jan.wezWypozyczenia().size());
        Wypozyczenie wyp = jan.wezWypozyczenia().iterator().next();
        System.out.println("Status wypożyczenia: " + wyp.wezStatus());
        System.out.println("Do zapłaty:          " + wyp.wezDoZaplaty() + " zł");

        // Opłata
        jan.oplac(wyp, wyp.wezDoZaplaty());
        System.out.println("Po opłacie status:   " + wyp.wezStatus());
        System.out.println("Liczba płatności:    " + jan.wezPlatnosci().size());

        // Blokada konta
        jan.zablokuj(new Date(teraz + 30L * 24 * 60 * 60 * 1000));
        System.out.println("\nBlokada do:          " + jan.wezBlokade());
    }
}
