package ayarlar;

import dao.GarsonDAO;
import model.Garson;

import java.util.List;
import java.util.Scanner;

public class GarsonAyarlar {
    private static final Scanner scanner = new Scanner(System.in);
    private static final GarsonDAO garsonDAO = new GarsonDAO();
    private static int garsonIdSayaci = 1; // Otomatik artan ID için

    public static void menuyuGoster() {
        while (true) {
            System.out.println("\n--- 🧍 GARSON AYARLARI ---");
            System.out.println("1. Garson Ekle");
            System.out.println("2. Garson Sil");
            System.out.println("3. Garson Adını Güncelle");
            System.out.println("4. Garsonları Listele");
            System.out.println("0. Geri Dön");
            System.out.print("Seçiminiz: ");
            int secim = scanner.nextInt();
            scanner.nextLine(); // buffer temizliği

            switch (secim) {
                case 1 -> garsonEkle();
                case 2 -> garsonSil();
                case 3 -> garsonGuncelle();
                case 4 -> garsonListele();
                case 0 -> {
                    System.out.println("⬅️ Ana menüye dönülüyor...");
                    return;
                }
                default -> System.out.println("⚠️ Geçersiz seçim!");
            }
        }
    }

    private static void garsonEkle() {
        System.out.print("Garson adı: ");
        String ad = scanner.nextLine();

        System.out.print("Garson şifresi: ");
        int sifre = scanner.nextInt();


        System.out.println(" Garson maaşı kac tl :");
        double maas = scanner.nextInt();
        scanner.nextLine(); // Enter karakterini temizle


        // Yeni garson oluştur
        Garson garson = new Garson(ad, sifre, garsonIdSayaci,maas );

        // DAO’ya ekle
        garsonDAO.garsonEkle(ad, sifre, garsonIdSayaci,maas);

        System.out.println("✅ Garson eklendi: " + ad);
    }

    private static void garsonSil() {
        garsonListele();
        System.out.print("Silmek istediğiniz garson ID: ");
        int id = scanner.nextInt();

        boolean basarili = garsonDAO.garsonSil(id);
        if (basarili) {
            System.out.println("🗑️ Garson silindi (ID: " + id + ")");
        } else {
            System.out.println("⚠️ Geçersiz garson ID!");
        }
    }

    private static void garsonGuncelle() {
        garsonListele();
        System.out.print("Güncellenecek garson ID: ");
        int id = scanner.nextInt();
        System.out.print("Garson şifresi: ");
        int sifre = scanner.nextInt();
        System.out.print("Yeni ad: ");
        String yeniAd = scanner.nextLine();

        scanner.nextLine();

        boolean basarili = garsonDAO.garsonGuncelle(id, yeniAd, sifre);
        if (basarili) {
            System.out.println("✏️ Garson adı güncellendi (ID: " + id + ")");
        } else {
            System.out.println("⚠️ Geçersiz garson ID!");
        }
    }

    private static void garsonListele() {
        List<Garson> garsonlar = garsonDAO.garsonlariGetir();
        if (garsonlar.isEmpty()) {
            System.out.println("📭 Henüz garson yok.");
            return;
        }
        System.out.println("\n--- 👨‍🍳 GARSON LİSTESİ ---");
        for (Garson g : garsonlar) {
            System.out.println("ID: " + g.getId() + " | Ad: " + g.getAd());
        }
    }
}
