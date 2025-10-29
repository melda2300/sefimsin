package dao;

import model.Masa;
import java.util.ArrayList;
import java.util.List;

public class MasaDAO {
    private static final List<Masa> masaListesi = new ArrayList<>();

    public void masaEkle(Masa masa) {
        masaListesi.add(masa);
        System.out.println("Masa eklendi: " + masa.getMasaId());
    }

    public List<Masa> masalariGetir() {
        return masaListesi;
    }

    public Masa masaBul(int masaNo) {
        for (Masa m : masaListesi) {
            if (m.getMasaId() == masaNo) {
                return m;
            }
        }
        return null;
    }

    public void masaSil(int masaNo) {
        Masa masa = masaBul(masaNo);
        if (masa != null) {
            masaListesi.remove(masa);
            System.out.println("Masa silindi: " + masaNo);
        } else {
            System.out.println("Masa bulunamadı: " + masaNo);
        }
    }
}
