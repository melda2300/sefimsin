package model;


import java.util.List;

public class Siparis {
    private int siparisId;
    private int masaId;
    private int garsonId;
    private List<Urun> urunler;
    private double toplam;
    private String durum;
    private boolean odendi;


    public Siparis(int siparisId, int masaId, int garsonId, List<Urun> urunler, double toplam, String durum, boolean odendi) {
        this.siparisId = siparisId;
        this.masaId = masaId;
        this.garsonId = garsonId;
        this.urunler = urunler;
        this.toplam = toplam;
        this.durum = durum;
        this.odendi = odendi;

    }

    public int getSiparisId() {
        return siparisId;
    }

    public void setSiparisId(int siparisId) {
        this.siparisId = siparisId;
    }

    public int getMasaId() {
        return masaId;
    }

    public int getGarsonId() {
        return garsonId;
    }

    public List<Urun> getUrunler() {
        return urunler;
    }

    public double getToplam() {
        return toplam;
    }

    public String getDurum() {
        return durum;
    }

    public void setDurum(String durum) {
        this.durum = durum;
    }

    public boolean isOdendi() {
        return odendi;
    }

    public void setOdendi(boolean odendi) {
        this.odendi = odendi;
    }



    @Override
    public String toString() {
        return "Sipariş ID: " + siparisId +
                " | Masa: " + masaId +
                " | Garson: " + garsonId +
                " | Ürün Sayısı: " + urunler.size() +
                " | Toplam: " + toplam + "₺" +
                " | Durum: " + durum+
                " | ödeme : " + isOdendi();
    }


}
