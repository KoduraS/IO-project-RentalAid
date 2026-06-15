package model;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class PlatnoscTest {

    @Test
    void testPlatnosc() {
        Kategoria k = new Kategoria("Zimowe");
        Etykieta e = new Etykieta("Narty", "Opis", k);
        Sprzet s = new Sprzet(e);

        Uzytkownik u = new Kreator("Jan")
                .nazwisko("Kowalski")
                .dokument("123")
                .email("a@a.pl")
                .haslo("x")
                .stworz();

        Wypozyczenie w = new Wypozyczenie(new Date(), new Date(), s, u);

        Platnosc p = new Platnosc(new Date(), w, 100);

        assertEquals(100, p.wezKwota());
        assertNotNull(p.wezDate());
    }
}