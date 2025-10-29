package ayarlar;

import dao.UrunDAO;
import model.Urun;

import java.util.List;
import java.util.Scanner;

public class UrunAyarlar {
    private static final Scanner scanner = new Scanner(System.in);
    private static final UrunDAO urunDAO = new UrunDAO();

    public static void menuyuGoster() {
        while (true) {
            System.out.println("\n--- 🍽️ ÜRÜN AYARLARI ---");
            System.out.println("1. Ürün Ekle");
            System.out.println("2. Ürün Sil");
            System.out.println("3. Fiyat Güncelle");
            System.out.println("4. Ürünleri Listele");
            System.out.println("0. Geri Dön");
            System.out.print("Seçiminiz: ");
            int secim = scanner.nextInt();
            scanner.nextLine(); // satır sonunu temizle

            switch (secim) {
                case 1 -> urunEkle();
                case 2 -> urunSil();
                case 3 -> fiyatGuncelle();
                case 4 -> urunListele();
                case 0 -> {
                    System.out.println("⬅️ Ana menüye dönülüyor...");
                    return;
                }
                default -> System.out.println("⚠️ Geçersiz seçim!");
            }
        }
    }

    private static void urunEkle() {
        System.out.print("Ürün adı: ");
        String ad = scanner.nextLine();
        System.out.print("Fiyat: ");
        double fiyat = scanner.nextDouble();

        Urun yeniUrun = new Urun(urunDAO.urunleriGetir().size() + 1, ad, fiyat);
        urunDAO.urunEkle(yeniUrun);
        System.out.println("✅ Ürün eklendi: " + ad + " (" + fiyat + "₺)");
    }

    private static void urunSil() {
        urunListele();
        System.out.print("Silmek istediğiniz ürün ID: ");
        String id = scanner.nextLine();

        boolean basarili = urunDAO.urunSil(id);
        if (basarili) {
            System.out.println("🗑️ Ürün silindi (ID: " + id + ")");
        } else {
            System.out.println("⚠️ Geçersiz ürün ID!");
        }
    }

    private static void fiyatGuncelle() {
        urunListele();
        System.out.print("Fiyatı güncellenecek ürün ID: ");
        String id = scanner.nextLine();
        System.out.print("Yeni fiyat: ");
        double yeniFiyat = scanner.nextDouble();

        boolean basarili = urunDAO.urunFiyatGuncelle(id, yeniFiyat);
        if (basarili) {
            System.out.println("💰 Ürün fiyatı güncellendi (ID: " + id + ")");
        } else {
            System.out.println("⚠️ Geçersiz ürün ID!");
        }
    }

    private static void urunListele() {
        List<Urun> urunler = urunDAO.urunleriGetir();
        if (urunler.isEmpty()) {
            System.out.println("📭 Henüz ürün yok.");
            return;
        }
        System.out.println("\n--- 📋 TÜM ÜRÜNLER ---");
        for (Urun u : urunler) {
            System.out.println("ID: " + u.getId() + " | " + u.getName() + " - " + u.getFiyat() + "₺");
        }
    }
}
