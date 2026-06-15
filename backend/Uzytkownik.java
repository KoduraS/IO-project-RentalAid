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

    public String wezNrTelLubNull() { return nrTel.orElse(null); }

    public String wezAdresLubNull() { return adres.orElse(null); }

    public String wezDane() {
        return imie + " " + nazwisko + " (" + dokument + ", " + email + ")";
    }

    public Date wezBlokadeLubNull() { return blokada.orElse(null); }

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
}
