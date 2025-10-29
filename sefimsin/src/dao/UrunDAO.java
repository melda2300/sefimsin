package dao;

import model.Urun;
import java.util.ArrayList;
import java.util.List;

public class UrunDAO {

    private static final List<Urun> urunList = new ArrayList<>();

    public void urunEkle(Urun urun) {
        urunList.add(urun);
    }

    public boolean urunSil(String id) {
        return urunList.removeIf(u -> u.getName() == id);
    }

    public boolean urunFiyatGuncelle(String id, double yeniFiyat) {
        for (Urun u : urunList) {
            if (u.getName() == id) {
                u.setFiyat(yeniFiyat);
                return true;
            }
        }
        return false;
    }

    public List<Urun> urunleriGetir() {
        return urunList;
    }

    public Urun urunBul(String ad) {
        for (Urun u : urunList) {
            if (u.getName() == ad) {
                return u;
            }
        }
        return null;
    }
}
