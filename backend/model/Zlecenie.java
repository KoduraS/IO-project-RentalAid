package model;

public class Zlecenie {

    private int id;
    private ZlecenieStatus status;
    private Sprzet sprzet;

    public Zlecenie(ZlecenieStatus status, Sprzet sprzet) {
        this.status = status;
        this.sprzet = sprzet;
    }

    public int wezId() { return id; }

    public ZlecenieStatus wezStatus() { return status; }

    public Sprzet wezSprzet() { return sprzet; }

    // -------------------------------------------------------------------------
    // Demonstracja: tworzenie zleceń wydania i odbioru sprzętu
    // -------------------------------------------------------------------------
    public static void main(String[] args) {
        System.out.println("=== Demonstracja klasy Zlecenie ===\n");

        // Przygotuj sprzęt
        Kategoria sprzety = new Kategoria("Sprzęty zimowe");
        Etykieta narty = new Etykieta("Narty", "Atomic Redster 7 170cm", sprzety);
        Sprzet atomic  = new Sprzet(narty);

        System.out.println("Status sprzętu przed zleceniem: " + atomic.wezStatus());

        // Zlecenie WYDANIA – magazynier przygotowuje sprzęt do odbioru przez klienta
        Zlecenie wydanie = new Zlecenie(ZlecenieStatus.WYDANIE, atomic);
        atomic.ustawStatus(SprzetStatus.CZEKA_NA_WYDANIE_KLIENTOWI);

        System.out.println("\nZlecenie wydania:");
        System.out.println("  Typ:            " + wydanie.wezStatus());
        System.out.println("  Sprzęt:         " + wydanie.wezSprzet().wezEtykieta().wezNazwa());
        System.out.println("  Status sprzętu: " + wydanie.wezSprzet().wezStatus());

        // Symuluj odebranie przez klienta – sprzęt staje się WYPOZYCZONY
        atomic.ustawStatus(SprzetStatus.WYPOZYCZONY);
        System.out.println("\nPo wydaniu klientowi: " + atomic.wezStatus());

        // Zlecenie ODBIORU – sprzęt wróciło do operatora, trzeba go przetransportować
        Zlecenie odbior = new Zlecenie(ZlecenieStatus.ODBIOR, atomic);
        atomic.ustawStatus(SprzetStatus.CZEKA_NA_WYDANIE_MAGAZYNIEROWI);

        System.out.println("\nZlecenie odbioru:");
        System.out.println("  Typ:            " + odbior.wezStatus());
        System.out.println("  Status sprzętu: " + odbior.wezSprzet().wezStatus());

        // Przekazanie do magazynu
        atomic.zakoncWypozyczenie();
        System.out.println("\nPo transporcie do magazynu: " + atomic.wezStatus());
    }
}
