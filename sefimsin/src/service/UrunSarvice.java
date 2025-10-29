package service;

import dao.UrunDAO;
import model.Urun;

import java.util.List;

public class UrunSarvice {
    private final UrunDAO urunDAO = new UrunDAO();

    // ➕ Ürün ekle
    public void urunEkle(String ad, double fiyat, int id) {
        Urun urun = new Urun(id, ad, fiyat);
        urunDAO.urunEkle(urun);
    }

    // 📋 Ürünleri listele
    public List<Urun> urunleriListele() {
        return urunDAO.urunleriGetir();
    }

    // 🔍 Ürün bul
    public Urun urunBul(String ad) {
        return urunDAO.urunBul(ad);
    }

    // ❌ Ürün sil
    public void urunSil(String ad) {
        urunDAO.urunSil(ad);
    }

    // 💰 Fiyat güncelle
    public void urunFiyatGuncelle(String ad, double yeniFiyat) {
        urunDAO.urunFiyatGuncelle(ad, yeniFiyat);
    }
}
