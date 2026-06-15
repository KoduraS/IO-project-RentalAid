package model;

import java.util.Date;

/**
 * Kompleksowa demonstracja całego modelu domeny wypożyczalni sprzętu.
 *
 * Scenariusz:
 *   1. Skonfiguruj katalog (Kategoria → Etykieta → Sprzet)
 *   2. Zarejestruj klienta przez Kreator
 *   3. Klient składa rezerwację na termin A
 *   4. Inny klient wypożycza ten sam sprzęt na termin B (brak kolizji)
 *   5. Przechowuj płatność i sprawdzaj statusy
 *   6. Klient składa wniosek o przedłużenie – operator rozpatruje
 *   7. Wystawiaj zlecenia wydania i odbioru
 *   8. Zablokuj konto użytkownika z zaległościami
 */
public class DemoWypozyczalnia {

    private static final String SEP = "\n" + "─".repeat(55) + "\n";

    public static void main(String[] args) {

        // ── 1. KATALOG ────────────────────────────────────────────
        System.out.println(SEP + " 1. KATALOG" + SEP);

        Kategoria sprzety = new Kategoria("Sprzęty zimowe");
        sprzety.ustawCeneZaDzien(0.0);         // ceny ustawiane na etykiecie
        sprzety.ustawCeneZaWyporzyczenie(0.0);

        Etykieta etykietaNarty = new Etykieta(
                "Narty", "Atomic Redster 7 170cm", sprzety);
        etykietaNarty.ustawCeneZaDzien(35.0);
        etykietaNarty.ustawCeneZaWyporzyczenie(10.0);
        etykietaNarty.ustawCeneZaOpoznienie(50.0);
        etykietaNarty.ustawCeneZaOpoznienieZaDzien(40.0);

        Sprzet narty1 = new Sprzet(etykietaNarty);
        Sprzet narty2 = new Sprzet(etykietaNarty);
        etykietaNarty.wezSprzety().add(narty1);
        etykietaNarty.wezSprzety().add(narty2);
        sprzety.wezEtykiety().add(etykietaNarty);

        System.out.println("Kategoria:          " + sprzety.wezNazwa());
        System.out.println("Etykieta:           " + etykietaNarty.wezNazwa());
        System.out.println("Opis:               " + etykietaNarty.wezOpis());
        System.out.printf ("Cena/dzień:         %.2f zł%n",  etykietaNarty.wezCenaZaDzien());
        System.out.printf ("Opłata wyp.:        %.2f zł%n",  etykietaNarty.wezCenaZaWypozyczenie());
        System.out.println("Egzemplarze:        " + etykietaNarty.wezSprzety().size());
        System.out.println("Status narty1:       " + narty1.wezStatus());

        // ── 2. REJESTRACJA KLIENTÓW ───────────────────────────────
        System.out.println(SEP + " 2. REJESTRACJA KLIENTÓW" + SEP);

        Uzytkownik anna = new Kreator("Anna")
                .nazwisko("Kowalska")
                .dokument("AK100001")
                .email("anna@example.pl")
                .haslo("hasloAnna!")
                .nrTel("600-111-222")
                .adres("ul. Słoneczna 3, Kraków")
                .stworz();

        Uzytkownik piotr = new Kreator("Piotr")
                .nazwisko("Nowak")
                .dokument("PN200002")
                .email("piotr@example.pl")
                .haslo("hasloPiotr!")
                .stworz();

        System.out.println("Klient 1: " + anna.wezDane());
        System.out.println("  Telefon: " + anna.wezNrTel());
        System.out.println("  Adres:   " + anna.wezAdres());
        System.out.println("Klient 2: " + piotr.wezDane());
        System.out.println("  Telefon: " + piotr.wezNrTel() + "  (brak – opcjonalne)");

        System.out.println("\nWeryfikacja hasła Anny (poprawne): "
                + anna.sprawdzHaslo("hasloAnna!"));
        System.out.println("Weryfikacja hasła Anny (błędne):   "
                + anna.sprawdzHaslo("zleHaslo"));

        // ── 3. REZERWACJA (Anna, za 10 dni, na 4 dni) ────────────
        System.out.println(SEP + " 3. REZERWACJA – Anna" + SEP);

        long teraz = System.currentTimeMillis();
        long dzien = 24L * 60 * 60 * 1000;

        Date rezStart = new Date(teraz + 10 * dzien);
        Date rezEnd   = new Date(teraz + 14 * dzien);
        anna.rezerwuj(rezStart, rezEnd, narty1);

        System.out.println("Rezerwacje narty1:   " + narty1.wezRezerwacje().size());
        Wypozyczenie rez = narty1.wezRezerwacje().iterator().next();
        System.out.println("Status rezerwacji:  " + rez.wezStatus());
        System.out.printf ("Do zapłaty:         %.2f zł%n", rez.wezDoZaplaty());

        // ── 4. WYPOŻYCZENIE (Piotr, od zaraz, na 3 dni – inna maszyna) ──
        System.out.println(SEP + " 4. WYPOŻYCZENIE – Piotr (narty2)" + SEP);

        Date wypStart = new Date(teraz);
        Date wypEnd   = new Date(teraz + 3 * dzien);
        piotr.wypozyc(wypStart, wypEnd, narty2);

        Wypozyczenie wyp = piotr.wezWypozyczenia().iterator().next();
        System.out.println("Status wypożyczenia: " + wyp.wezStatus());
        System.out.println("Status narty2:        " + narty2.wezStatus());
        System.out.printf ("Do zapłaty:          %.2f zł%n", wyp.wezDoZaplaty());
        System.out.println("Dni eksploatacji:    " + narty2.wezDniEksploatacji());

        // Wydanie sprzętu i zlecenie
        wyp.wydaj();
        Zlecenie zlWydanie = new Zlecenie(ZlecenieStatus.WYDANIE, narty2);
        narty2.ustawStatus(SprzetStatus.WYPOZYCZONY);
        System.out.println("\nPo wydaniu:");
        System.out.println("  Status wyp.:     " + wyp.wezStatus());
        System.out.println("  Status narty2:    " + narty2.wezStatus());
        System.out.println("  Zlecenie:        " + zlWydanie.wezStatus());

        // ── 5. PŁATNOŚĆ – Piotr opłaca wypożyczenie ──────────────
        System.out.println(SEP + " 5. PŁATNOŚĆ – Piotr" + SEP);

        double caloscDoZaplaty = wyp.wezDoZaplaty();
        piotr.oplac(wyp, caloscDoZaplaty / 2);  // wpłaca połowę
        System.out.printf("Po wpłacie 50%%:%n");
        System.out.printf("  Wpłacono:        %.2f zł%n", wyp.wezWplacono());
        System.out.printf("  Pozostało:       %.2f zł%n", wyp.wezDoZaplaty());
        System.out.println("  Status wyp.:     " + wyp.wezStatus());

        piotr.oplac(wyp, wyp.wezDoZaplaty());   // dopłaca resztę
        System.out.printf("%nPo pełnej opłacie:%n");
        System.out.printf("  Pozostało:       %.2f zł%n", wyp.wezDoZaplaty());
        System.out.println("  Status wyp.:     " + wyp.wezStatus());
        System.out.println("  Płatności Piotra:" + piotr.wezPlatnosci().size());

        for (Platnosc p : piotr.wezPlatnosci()) {
            System.out.println("  " + p.wezDane());
        }

        // ── 6. WNIOSEK O PRZEDŁUŻENIE – Anna ─────────────────────
        System.out.println(SEP + " 6. WNIOSEK O PRZEDŁUŻENIE – Anna" + SEP);

        // Anna chce 7 dodatkowych dni
        Date zadanaData     = new Date(teraz + 21 * dzien);
        Wniosek wniosek     = new Wniosek(zadanaData, anna, rez);
        System.out.println("Status wniosku po złożeniu: " + wniosek.wezStatus());

        // Operator proponuje tylko 3 dni dodatkowe
        Date alternatywa = new Date(teraz + 17 * dzien);
        wniosek.rozpatrz(alternatywa);
        System.out.println("Status po rozpatrzeniu:     " + wniosek.wezStatus());
        System.out.println("Nowa data zwrotu:           " + wniosek.wezNowaDate());

        // ── 7. ZWROT I ZLECENIE ODBIORU ──────────────────────────
        System.out.println(SEP + " 7. ZWROT I ZLECENIE ODBIORU – narty2" + SEP);

        narty2.zakoncWypozyczenie();
        Zlecenie zlOdbior = new Zlecenie(ZlecenieStatus.ODBIOR, narty2);
        System.out.println("Status narty2:      " + narty2.wezStatus());
        System.out.println("Zlecenie odbioru:  " + zlOdbior.wezStatus());

        narty2.ustawStatus(SprzetStatus.W_MAGAZYNIE);
        System.out.println("Po magazynowaniu:  " + narty2.wezStatus());

        // ── 8. BLOKADA KONTA ──────────────────────────────────────
        System.out.println(SEP + " 8. BLOKADA KONTA – Piotr" + SEP);

        System.out.println("Blokada przed:     " + piotr.wezBlokade());
        Date dataBlokady = new Date(teraz + 30 * dzien);
        piotr.zablokuj(dataBlokady);
        System.out.println("Blokada po:        " + piotr.wezBlokade());

        System.out.println(SEP + " KONIEC DEMONSTRACJI" + SEP);
    }
}
