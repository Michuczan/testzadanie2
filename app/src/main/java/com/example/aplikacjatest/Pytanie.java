package com.example.aplikacjatest;

public class Pytanie {
    private int idZdjecia;
    private String trescPytania;
    private boolean odpowiedz;
    //tak true, nie false
    private boolean czyPoprawnaOdpowiedz;
    //udzielona odpowiedz
    private String podpowiedz;

    private boolean wykorzystanapodpowiedz;

    public Pytanie(int idZdjecia, String trescPytania, boolean odpowiedz, String podpowiedz) {
        this.idZdjecia = idZdjecia;
        this.trescPytania = trescPytania;
        this.odpowiedz = odpowiedz;
        this.podpowiedz = podpowiedz;
    }

    public Pytanie(int idZdjecia, String trescPytania, boolean odpowiedz) {
        this.idZdjecia = idZdjecia;
        this.trescPytania = trescPytania;
        this.odpowiedz = odpowiedz;
        czyPoprawnaOdpowiedz = false;
    }

    public int getIdZdjecia() {
        return idZdjecia;
    }

    public String getTrescPytania() {
        return trescPytania;
    }

    public boolean isOdpowiedz() {
        return odpowiedz;
    }

    public boolean isCzyPoprawnaOdpowiedz() {
        return czyPoprawnaOdpowiedz;
    }

    public void setCzyPoprawnaOdpowiedz(boolean czyPoprawnaOdpowiedz) {
        this.czyPoprawnaOdpowiedz = czyPoprawnaOdpowiedz;
    }
}
