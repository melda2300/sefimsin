package service;

import model.Masa;

import java.util.ArrayList;
import java.util.List;

public class MasaService {
    private List<Masa> masaList = new ArrayList<>();
    private int idSayac = 1;

    // Masa ekle
    public void masaEkle(String kapasite) {
        Masa masa = new Masa(idSayac++, kapasite);
        masaList.add(masa);
        System.out.println("✅ Masa eklendi: ID " + masa.getMasaId() + " | Kapasite: " + kapasite);
    }

    // Masa sil
    public void masaSil(int id) {
        Masa silinecek = null;
        for (Masa m : masaList) {
            if (m.getMasaId() == id) {
                silinecek = m;
                break;
            }
        }
        if (silinecek != null) {
            masaList.remove(silinecek);
            System.out.println("🗑️ Masa silindi: ID " + id);
        } else {
            System.out.println("⚠️ Silinecek masa bulunamadı: " + id);
        }
    }

    // Masa listesi
    public void masaListesi() {
        if (masaList.isEmpty()) {
            System.out.println("Henüz kayıtlı masa yok.");
            return;
        }
        System.out.println("\n--- MASA LİSTESİ ---");
        for (Masa m : masaList) {
            System.out.println("ID: " + m.getMasaId() + " | Kapasite: " + m.getKapasite() + " | Durum: " + m.getDurum());
        }
    }

    // Masa durumunu değiştir
    public void masaDurumGuncelle(int id, String durum) {
        for (Masa m : masaList) {
            if (m.getMasaId() == id) {
                m.setDurum(durum);
                System.out.println("✅ Masa durumu güncellendi: ID " + id + " → " + durum);
                return;
            }
        }
        System.out.println("⚠️ Masa bulunamadı: " + id);
    }

    // Getter
    public List<Masa> getMasaList() { return masaList; }
}
