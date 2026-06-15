package model;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class KreatorTest {

    @Test
    void testTworzenieUzytkownika() {
        Uzytkownik u = new Kreator("Jan")
                .nazwisko("Kowalski")
                .dokument("123")
                .email("a@a.pl")
                .haslo("haslo")
                .stworz();

        assertEquals("Jan", u.wezImie());
        assertEquals("Kowalski", u.wezNazwisko());
        assertEquals("123", u.wezDokument());
    }

    @Test
    void testBrakWymaganychPol() {
        assertThrows(IllegalStateException.class, () ->
                new Kreator("Jan").stworz()
        );
    }
}