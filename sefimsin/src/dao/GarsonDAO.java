package dao;

import model.Garson;

import java.util.ArrayList;
import java.util.List;

public class GarsonDAO {
    public static final List<Garson> garsonListesi = new ArrayList<Garson>();
    private int idSayac = 1;


    // Garson ekle
    public void garsonEkle(String ad, int sifre, int idSayac, double maas) {
        Garson garson = new Garson(ad, sifre, idSayac, maas); // idSayac en başta artmalı
        garsonListesi.add(garson);
        System.out.println("✅ Yeni garson eklendi: " + ad + "\n Şİfre : " + garson.getSifre() + "\n ID: " + garson.getId() + " " + "\n Maaşı: " + garson.getMaas());
    }

    // Garsonları getir
    public List<Garson> garsonlariGetir() {
        return garsonListesi;
    }

    // Garson bul
    public Garson garsonBul(int id) {
        for (Garson g : garsonListesi) {
            if (g.getId() == id) {
                return g;
            }
        }
        return null;
    }

    // Garson sil
    public boolean garsonSil(int id) {
        Garson g = garsonBul(id);
        if (g != null) {
            garsonListesi.remove(g);
            System.out.println("❌ Garson silindi: " + g.getAd());
        } else {
            System.out.println("⚠️ Garson bulunamadı!");
        }
        return false;
    }

    // Garson güncelle
    public boolean garsonGuncelle(int id, String yeniAd, int yeniSifre) {
        Garson g = garsonBul(id);
        if (g != null) {
            g.setAd(yeniAd);
            g.setSifre(yeniSifre);
            System.out.println("🔄 Garson bilgileri güncellendi: " + yeniAd);
        } else {
            System.out.println("⚠️ Garson bulunamadı!");
        }
        return false;
    }

}
