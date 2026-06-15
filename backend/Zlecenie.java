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
}
