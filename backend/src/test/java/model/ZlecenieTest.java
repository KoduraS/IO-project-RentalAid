package model;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ZlecenieTest {

    @Test
    void testZlecenie() {
        Kategoria k = new Kategoria("Zimowe");
        Etykieta e = new Etykieta("Narty", "Opis", k);
        Sprzet s = new Sprzet(e);

        Zlecenie z = new Zlecenie(ZlecenieStatus.WYDANIE, s);

        assertEquals(ZlecenieStatus.WYDANIE, z.wezStatus());
        assertEquals(s, z.wezSprzet());
    }
}