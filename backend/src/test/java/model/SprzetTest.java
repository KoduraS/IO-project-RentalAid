package model;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SprzetTest {

    @Test
    void testSprzetStatus() {
        Kategoria k = new Kategoria("Zimowe");
        Etykieta e = new Etykieta("Narty", "Opis", k);
        Sprzet s = new Sprzet(e);

        assertNotNull(s.wezStatus());

        s.ustawStatus(SprzetStatus.W_NAPRAWIE);
        assertEquals(SprzetStatus.W_NAPRAWIE, s.wezStatus());
    }
}