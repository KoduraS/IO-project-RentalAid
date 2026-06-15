package model;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class KategoriaTest {

    @Test
    void testKategoria() {
        Kategoria k = new Kategoria("Sport");

        k.ustawCeneZaDzien(10);
        k.ustawCeneZaWyporzyczenie(2);

        assertEquals("Sport", k.wezNazwa());
        assertEquals(10, k.wezCenaZaDzien());
        assertEquals(2, k.wezCenaZaWypozyczenie());
    }
}