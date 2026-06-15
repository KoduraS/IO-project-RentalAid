package model;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class WypozyczenieTest {

    @Test
    void testWypozyczenieKwota() {
        Kategoria k = new Kategoria("Zimowe");
        Etykieta e = new Etykieta("Narty", "Opis", k);
        e.ustawCeneZaDzien(10);
        e.ustawCeneZaWyporzyczenie(5);

        Sprzet s = new Sprzet(e);
        Uzytkownik u = new Kreator("Jan")
                .nazwisko("Kowalski")
                .dokument("123")
                .email("a@a.pl")
                .haslo("x")
                .stworz();

        Date start = new Date();
        Date end = new Date(start.getTime() + 2L * 24 * 60 * 60 * 1000);

        Wypozyczenie w = new Wypozyczenie(start, end, s, u);

        assertTrue(w.wezDoZaplaty() > 0);
    }

    @Test
    void testWplata() {
        Kategoria k = new Kategoria("Zimowe");
        Etykieta e = new Etykieta("Narty", "Opis", k);
        e.ustawCeneZaDzien(10);
        e.ustawCeneZaWyporzyczenie(5);

        Sprzet s = new Sprzet(e);
        Uzytkownik u = new Kreator("Jan")
                .nazwisko("Kowalski")
                .dokument("123")
                .email("a@a.pl")
                .haslo("x")
                .stworz();

        Date start = new Date();
        Date end = new Date(start.getTime() + 2L * 24 * 60 * 60 * 1000);

        Wypozyczenie w = new Wypozyczenie(start, end, s, u);

        w.wplac(100);
        assertEquals(WypozyczenieStatus.OPLACONE, w.wezStatus());
    }
}