package model;

import java.util.Date;

public class Wniosek {

    private int id;
    private Date nowaData;
    private Uzytkownik klient;
    private Wypozyczenie wypozyczenie;
    private WniosekStatus status;

    public Wniosek(Date nowaData, Uzytkownik klient, Wypozyczenie wypozyczenie) {
        this.nowaData = nowaData;
        this.klient = klient;
        this.wypozyczenie = wypozyczenie;
        this.status = WniosekStatus.NIEROZPATRZONY;
    }

    /**
     * Rozpatrz wniosek – akceptacja (true) lub odrzucenie (false).
     */
    public void rozpatrz(boolean decyzja) {
        if (decyzja) {
            this.status = WniosekStatus.ROZPATRZONY_POZYTYWNIE;
        } else {
            this.status = WniosekStatus.ROZPATRZONY_NEGATYWNIE;
        }
    }

    /**
     * Rozpatrz wniosek z częściową akceptacją – przyznanie alternatywnej daty.
     */
    public void rozpatrz(Date alternatywnaData) {
        this.nowaData = alternatywnaData;
        this.status = WniosekStatus.ROZPATRZONY_CZESCIOWO_POZYTYWNIE;
    }

    public int wezId() { return id; }

    public Date wezNowaDate() { return nowaData; }

    public Uzytkownik wezKlienta() { return klient; }

    public Wypozyczenie wezWypozyczenie() { return wypozyczenie; }

    public WniosekStatus wezStatus() { return status; }
}
