package model;

public class Masa {

    private int masaId;
    private String kapasite;
    private String durum; // "Boş", "Dolu", "Rezerve"

    public Masa(int id, String kapasite) {
        this.masaId = id;
        this.kapasite = kapasite;
        this.durum = "Boş";

    }
    public int getMasaId() {
        return masaId;
    }

    public String getKapasite () {
        return kapasite;

    }
    public String getDurum () {
            return durum;
    }
    public void setDurum (String durum){
        this.durum = durum;
    }

    @Override
    public String toString(){
        return "Masa ID: " + masaId + " | Kapasite: " + kapasite;
    }

}