package dao;

import model.Garson;
import model.Siparis;
import model.Masa;

import java.util.ArrayList;
import java.util.List;

public class SiparisDAO {
    private static final List<Siparis> siparisListesi = new ArrayList<>();

    // 📌 Sipariş ekleme
    public void siparisEkle(Siparis siparis) {
        siparisListesi.add(siparis);
        System.out.println("Sipariş eklendi: " + siparis.getMasaId() + " numaralı masa");
    }

    // 📋 Tüm siparişleri getir
    public List<Siparis> siparisleriGetir() {
        return siparisListesi;
    }

    // 🔍 Belirli bir masanın siparişlerini bul
    public List<Siparis> masaSiparisleriGetir(int masaNo) {
        List<Siparis> masaSiparisleri = new ArrayList<>();
        for (Siparis s : siparisListesi) {
            if (s.getMasaId() == masaNo) {
                masaSiparisleri.add(s);
            }
        }
        return masaSiparisleri;
    }

    // ❌ Sipariş sil
    public void siparisSil(int siparisId) {
        Siparis silinecek = null;
        for (Siparis s : siparisListesi) {
            if (s.getSiparisId() == siparisId) {
                silinecek = s;
                break;
            }
        }

        if (silinecek != null) {
            siparisListesi.remove(silinecek);
            System.out.println("Sipariş silindi: " + siparisId);
        } else {
            System.out.println("Sipariş bulunamadı: " + siparisId);
        }
    }

    // 🔄 Sipariş güncelle (örnek: durum değişikliği)
    public void siparisGuncelle(int siparisId, String yeniDurum) {
        for (Siparis s : siparisListesi) {
            if (s.getSiparisId() == siparisId) {
                s.setDurum(yeniDurum);
                System.out.println("Sipariş durumu güncellendi: " + yeniDurum);
                return;
            }
        }
        System.out.println("Sipariş bulunamadı: " + siparisId);
    }

    // 💰 MASA TOPLAM TUTARINI HESAPLA
    public double masaToplamTutar(int masaNo) {
        double toplam = 0;

        for (Siparis s : siparisListesi) {
            if (s.getMasaId() == masaNo && !s.isOdendi()) {
                toplam += s.getToplam();
            }
        }
        return toplam;
    }

    // ✅ SİPARİŞİ KAPAT (ödendi olarak işaretle)
    public void siparisKapat(int masaNo) {
        for (Siparis s : siparisListesi) {
            if (s.getMasaId() == masaNo && !s.isOdendi()) {
                s.setOdendi(true);
            }
        }
        System.out.println("✅ Masa " + masaNo + " siparişi kapatıldı.");
    }
}
