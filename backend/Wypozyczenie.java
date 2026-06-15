package model;

import java.util.Date;

public class Wypozyczenie {

    private int id;
    private Date start;
    private Date end;
    private double wplacono;
    private double doZaplaty;
    private Sprzet sprzet;
    private Uzytkownik uzytkownik;
    private WypozyczenieStatus status;

    public Wypozyczenie(Date start, Date end, Sprzet sprzet, Uzytkownik uzytkownik) {
        this.start = start;
        this.end = end;
        this.sprzet = sprzet;
        this.uzytkownik = uzytkownik;
        this.wplacono = 0.0;
        this.doZaplaty = obliczKwoteDoZaplaty();
        this.status = WypozyczenieStatus.REZERWACJA;
    }

    private double obliczKwoteDoZaplaty() {
        if (start == null || end == null) return 0.0;
        long miliseconds = end.getTime() - start.getTime();
        long dni = miliseconds / (1000 * 60 * 60 * 24);
        double cenaZaDzien = sprzet.wezEtykieta() != null
                ? sprzet.wezEtykieta().wezCenaZaDzien()
                : Sprzet.wezDomyslnaCeneZaDzien();
        double cenaZaWypozyczenie = sprzet.wezEtykieta() != null
                ? sprzet.wezEtykieta().wezCenaZaWypozyczenie()
                : Sprzet.wezDomyslnaCeneZaWyporzyczenie();
        return cenaZaWypozyczenie + (dni * cenaZaDzien);
    }

    public void wplac(double kwota) {
        if (kwota <= 0) throw new IllegalArgumentException("Kwota musi być dodatnia");
        this.wplacono += kwota;
        aktualizujStatus();
    }

    public void wydaj() {
        this.status = WypozyczenieStatus.W_TRAKCIE;
    }

    public double wezDoZaplaty() {
        return Math.max(0, doZaplaty - wplacono);
    }

    private void aktualizujStatus() {
        Date teraz = new Date();
        if (wplacono >= doZaplaty) {
            status = WypozyczenieStatus.OPLACONE;
        } else if (teraz.after(end)) {
            status = WypozyczenieStatus.PRZEKROCZONO_TERMIN;
        } else if (status == WypozyczenieStatus.W_TRAKCIE) {
            status = WypozyczenieStatus.NIE_OPLACONE;
        }
    }

    // --- Gettery ---

    public int wezId() { return id; }

    public Date wezStart() { return start; }

    public Date wezEnd() { return end; }

    public double wezWplacono() { return wplacono; }

    public Sprzet wezSprzet() { return sprzet; }

    public Uzytkownik wezUzytkownik() { return uzytkownik; }

    public WypozyczenieStatus wezStatus() { return status; }
}
