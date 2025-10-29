package service;

import model.Siparis;

import java.util.ArrayList;
import java.util.List;

import model.Siparis;
import model.Urun;

public class SiparisService {
    private final List<Siparis> siparisList = new ArrayList<>();
    private int idSayac = 1;

    // 🔹 Yeni sipariş oluşturma
    public Siparis siparisOlustur(int masaId, int garsonId, List<Urun> urunler) {
        double toplamTutar = 0;
        for (Urun u : urunler) {
            toplamTutar += u.getFiyat();
        }

        Siparis siparis = new Siparis(idSayac++, masaId, garsonId, urunler, toplamTutar, "Hazırlanıyor", false);
        siparisList.add(siparis);

        System.out.println("✅ Sipariş oluşturuldu: ID " + siparis.toString() +
                " | Masa: " + masaId +
                " | Garson: " + garsonId +
                " | Toplam: " + toplamTutar + "₺");
        return siparis;
    }

    // 🔹 Günlük toplam ciro
    public void gunlukCiro() {
        double toplam = 0;
        for (Siparis s : siparisList) {
            toplam += s.getToplam();
        }
        System.out.println("💰 Günlük toplam ciro: " + toplam + "₺");
    }

    // 🔹 Tüm siparişleri listele
    public void siparisListesi() {
        if (siparisList.isEmpty()) {
            System.out.println("⚠️ Henüz sipariş bulunmuyor.");
            return;
        }

        System.out.println("\n--- 📋 SİPARİŞ LİSTESİ ---");
        for (Siparis s : siparisList) {
            System.out.println("Sipariş ID: " + s.getSiparisId() +
                    " | Masa: " + s.getMasaId() +
                    " | Garson ID: " + s.getGarsonId() +
                    " | Toplam: " + s.getToplam() + "₺" +
                    " | Durum: " + s.getDurum());
        }
    }

    // 🔹 Siparişleri dışarıdan almak için
    public List<Siparis> getSiparisListesi() {
        return siparisList;
    }
}
