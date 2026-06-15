package model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EtykietaTest {

    @Test
    void testTworzenieIEdytowanieEtykiety() {
        Kategoria k = new Kategoria("Zimowe");
        Etykieta e = new Etykieta("Narty", "Opis", k);

        e.ustawCeneZaDzien(10);
        e.ustawCeneZaWyporzyczenie(5);

        assertEquals("Narty", e.wezNazwa());
        assertEquals("Opis", e.wezOpis());
        assertEquals(10, e.wezCenaZaDzien());
        assertEquals(5, e.wezCenaZaWypozyczenie());

        e.ustawOpis("Nowy opis");
        assertEquals("Nowy opis", e.wezOpis());
    }
}