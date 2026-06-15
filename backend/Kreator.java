package model;

public class Kreator {

    private String imie;
    private String nazwisko;
    private String dokument;
    private String email;
    private String haslo;
    private String nrTel;
    private String adres;

    public Kreator(String imie) {
        this.imie = imie;
    }

    public Kreator nazwisko(String nazwisko) {
        this.nazwisko = nazwisko;
        return this;
    }

    public Kreator dokument(String dokument) {
        this.dokument = dokument;
        return this;
    }

    public Kreator email(String email) {
        this.email = email;
        return this;
    }

    public Kreator haslo(String haslo) {
        this.haslo = haslo;
        return this;
    }

    public Kreator nrTel(String nrTel) {
        this.nrTel = nrTel;
        return this;
    }

    public Kreator adres(String adres) {
        this.adres = adres;
        return this;
    }

    public Uzytkownik stworz() {
        if (imie == null || nazwisko == null || dokument == null
                || email == null || haslo == null) {
            throw new IllegalStateException(
                    "Pola wymagane: imie, nazwisko, dokument, email, haslo");
        }
        return Uzytkownik.stworz(imie, nazwisko, dokument, email, haslo, nrTel, adres);
    }
}
