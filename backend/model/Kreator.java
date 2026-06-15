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

    // -------------------------------------------------------------------------
    // Demonstracja: fluent builder – pełny i minimalny wariant tworzenia konta
    // -------------------------------------------------------------------------
    public static void main(String[] args) {
        System.out.println("=== Demonstracja klasy Kreator ===\n");

        // Pełny zestaw danych
        Uzytkownik pelny = new Kreator("Maria")
                .nazwisko("Wiśniewska")
                .dokument("XY9876543")
                .email("maria@example.pl")
                .haslo("tajne123")
                .nrTel("700-200-300")
                .adres("ul. Długa 5, Warszawa")
                .stworz();

        System.out.println("Konto pełne:      " + pelny.wezDane());
        System.out.println("  Telefon:        " + pelny.wezNrTel());
        System.out.println("  Adres:          " + pelny.wezAdres());

        // Minimalny zestaw (bez tel i adresu – pola opcjonalne)
        Uzytkownik minimalny = new Kreator("Piotr")
                .nazwisko("Zając")
                .dokument("ZZ1111111")
                .email("piotr@example.pl")
                .haslo("haslo456")
                .stworz();

        System.out.println("\nKonto minimalne:  " + minimalny.wezDane());
        System.out.println("  Telefon:        " + minimalny.wezNrTel());   // null
        System.out.println("  Adres:          " + minimalny.wezAdres());   // null

        // Próba stworzenia bez wymaganego pola – oczekiwany wyjątek
        System.out.println("\nPróba bez hasła:");
        try {
            new Kreator("Adam").nazwisko("Błąd").dokument("ERR000").email("e@e.pl").stworz();
        } catch (IllegalStateException e) {
            System.out.println("  Złapano wyjątek: " + e.getMessage());
        }
    }
}
