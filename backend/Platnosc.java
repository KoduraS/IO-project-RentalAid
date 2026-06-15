package model;

import java.util.Date;

public class Platnosc {

    private int id;
    private Date data;
    private Wypozyczenie wypozyczenie;
    private double kwota;

    public Platnosc(Date data, Wypozyczenie wypozyczenie, double kwota) {
        this.data = data;
        this.wypozyczenie = wypozyczenie;
        this.kwota = kwota;
    }

    public int wezId() { return id; }

    public Date wezDate() { return data; }

    public Wypozyczenie wezWypozyczenie() { return wypozyczenie; }

    public double wezKwota() { return kwota; }

    public String wezDane() {
        return "Płatność #" + id + " | " + data + " | " + kwota + " zł";
    }
}
