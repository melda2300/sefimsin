package model;

public class Garson {
    private int id;
    private String ad;
    private int sifre;
    private Double maas;


    public Garson(String ad, int sifre, int id, Double maas) {
        this.ad = ad;
        this.id = id;
        this.sifre = sifre;
        this.maas = maas;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAd() {
        return ad;
    }

    public void setAd(String ad) {
        this.ad = ad;
    }

    public int getSifre() {
        return sifre;
    }

    public void setSifre(int sifre) {
        this.sifre = sifre;
    }

    public Double getMaas() {
        return maas;
    }

    public void setMaas(Double maas) {
        this.maas = maas;
    }

    @Override
    public String toString() {
        return "Garson ID: " + id + " | Ad: " + ad + " | Sifre: " + sifre + " | Maas: " + maas;
    }

}
