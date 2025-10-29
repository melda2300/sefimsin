package model;

public class Urun {
    private int id;
    private String name;
    private double fiyat;

    public Urun(int id, String name, double fiyat) {
        this.id = id;
        this.name = name;
        this.fiyat = fiyat;
    }

    public int getId() {return id;}
    public String getName() {return name;}
    public double getFiyat() {return fiyat;}
    public void setId(int id) {this.id = id;}
    public void setName(String name) {this.name = name;}
    public void setFiyat(double fiyat) {this.fiyat = fiyat;}

    @Override
    public String toString() {
        return " ürün  " + name + "  " + fiyat +" TL";
    }

}
