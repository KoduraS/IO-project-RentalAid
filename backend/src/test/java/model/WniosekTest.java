package model;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class WniosekTest {

    @Test
    void testRozpatrzenie() {
        Uzytkownik u = new Kreator("Jan")
                .nazwisko("Kowalski")
                .dokument("123")
                .email("a@a.pl")
                .haslo("x")
                .stworz();

        Kategoria k = new Kategoria("Zimowe");
        Etykieta e = new Etykieta("Narty", "Opis", k);
        Sprzet s = new Sprzet(e);

        Date nowa = new Date();
        Wypozyczenie w = new Wypozyczenie(nowa, nowa, s, u);

        Wniosek wn = new Wniosek(nowa, u, w);
        wn.rozpatrz(true);

        assertEquals(WniosekStatus.ROZPATRZONY_POZYTYWNIE, wn.wezStatus());
    }
}