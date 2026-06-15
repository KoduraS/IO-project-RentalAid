package model;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class UzytkownikTest {

    @Test
    void testHaslo() {
        Uzytkownik u = new Kreator("Jan")
                .nazwisko("Kowalski")
                .dokument("123")
                .email("a@a.pl")
                .haslo("abc")
                .stworz();

        assertTrue(u.sprawdzHaslo("abc"));
        assertFalse(u.sprawdzHaslo("xyz"));
    }

    @Test
    void testBlokada() {
        Uzytkownik u = new Kreator("Jan")
                .nazwisko("Kowalski")
                .dokument("123")
                .email("a@a.pl")
                .haslo("abc")
                .stworz();

        Date d = new Date();
        u.zablokuj(d);

        assertEquals(d, u.wezBlokade());
    }
}