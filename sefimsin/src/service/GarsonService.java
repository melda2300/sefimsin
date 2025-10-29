package service;

import model.Garson;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class GarsonService {
    private List<Garson> GarsonList = new ArrayList<>();
    private static final String DOSYA_ADI = "garsonlar.txt";
    private Scanner scanner = new Scanner(System.in);
    private int idSayac = 1; // Artan ID sayacı


    // 🔸 Yeni garson ekle
    public void garsonEkle(String ad, int sifre, int id, double maas) {
        Garson yeniGarson = new Garson(ad, sifre, id, maas); // 🔹 Şifre parametresi eklendi
        getGarsonList().add(yeniGarson);
        dosyayaYaz(); // Dosyaya kaydet
        System.out.println("✅ Yeni garson eklendi: " + yeniGarson.getAd() + " (ID: " + yeniGarson.getId() + ")");
    }

    public void girisKontrol(String ad, int sifre) {
        for (Garson g : getGarsonList()) {
            if (g.getAd().equals(ad) && g.getSifre() == sifre) {
                System.out.println("✅ Giriş başarılı! Hoş geldin " + g.getAd() + " 😊");
                return;
            }
        }
        System.out.println("❌ Giriş başarısız! Kullanıcı adı veya şifre hatalı.");
    }

    // 🔸 Garson listesi
    public void garsonListesi() {
        if (GarsonList.isEmpty()) {
            System.out.println("Henüz kayıtlı garson yok.");
            return;
        }
        System.out.println("\n--- GARSON LİSTESİ ---");
        for (Garson g : GarsonList) {
            System.out.println("ID: " + g.getId() + " | Ad: " + g.getAd());
        }
    }

    // 🔸 Garsonları dosyadan yükle
    private void garsonlariYukle(Double maas) {
        File dosya = new File(DOSYA_ADI);
        if (!dosya.exists()) return;

        try (BufferedReader reader = new BufferedReader(new FileReader(dosya))) {
            String satir;
            int maxId = 0;
            while ((satir = reader.readLine()) != null) {
                String[] parca = satir.split(",");
                if (parca.length == 3) {
                    int id = Integer.parseInt(parca[0]);
                    String ad = parca[1];
                    int sifre = Integer.parseInt(parca[2]);
                    GarsonList.add(new Garson(ad, sifre, id, maas));
                    if (id > maxId) maxId = id;
                }
            }
            idSayac = maxId + 1; // sonraki eklemeler için
        } catch (IOException e) {
            System.out.println("⚠️ Garsonları yüklerken hata: " + e.getMessage());
        }
    }

    // 🔸 Garsonları dosyaya kaydet
    private void dosyayaYaz() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(DOSYA_ADI))) {
            for (Garson g : GarsonList) {
                writer.write(g.getId() + "," + g.getAd() + "," + g.getSifre());
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("⚠️ Kaydederken hata: " + e.getMessage());
        }
    }

    // 🔸 Garson silme
    public void garsonSil(int id) {
        Garson silinecek = null;
        for (Garson g : GarsonList) {
            if (g.getId() == id) {
                silinecek = g;
                break;
            }
        }
        if (silinecek != null) {
            GarsonList.remove(silinecek);
            dosyayaYaz();
            System.out.println("🗑️ Garson silindi: " + silinecek.getAd());
        } else {
            System.out.println("⚠️ Silinecek garson bulunamadı: " + id);
        }
    }

    // 🔸 Garson bilgilerini düzenleme (ad veya şifre)
    public void garsonDuzenle(int id) {
        for (Garson g : GarsonList) {
            if (g.getId() == id) {
                System.out.print("Yeni ad gir (boş bırakmak için Enter): ");
                String yeniAd = scanner.nextLine();
                if (!yeniAd.isBlank()) {
                    g.setAd(yeniAd);
                }

                System.out.print("Yeni şifre gir (boş bırakmak için Enter): ");
                String sifreInput = scanner.nextLine();
                if (!sifreInput.isBlank()) {
                    try {
                        int yeniSifre = Integer.parseInt(sifreInput);
                        g.setSifre(yeniSifre);
                    } catch (NumberFormatException e) {
                        System.out.println("⚠️ Hatalı şifre girdiniz, şifre değişmedi.");
                    }
                }

                dosyayaYaz();
                System.out.println("✏️ Garson bilgileri güncellendi: " + g.getAd());
                return;
            }
        }
        System.out.println("⚠️ Düzenlenecek garson bulunamadı: " + id);
    }

    // 🔸 Getter
    public List<Garson> getGarsonList() {
        return GarsonList;
    }
}

