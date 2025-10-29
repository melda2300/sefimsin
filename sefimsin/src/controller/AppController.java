package controller;

import ayarlar.GarsonAyarlar;
import ayarlar.UrunAyarlar;
import dao.GarsonDAO;
import dao.MasaDAO;
import dao.SiparisDAO;
import dao.UrunDAO;
import model.Garson;
import model.Masa;
import model.Siparis;
import model.Urun;

import java.util.*;

import static dao.GarsonDAO.garsonListesi;

public class AppController {
    private static final Scanner scanner = new Scanner(System.in);
    private static final MasaDAO masaDAO = new MasaDAO();
    private static final GarsonDAO garsonDAO = new GarsonDAO();
    private static final UrunDAO urunDAO = new UrunDAO();
    private static final SiparisDAO siparisDAO = new SiparisDAO();
    private static int siparisId = 1; // 🔹 sınıf genelinde sayaç
    private static int garsonId = 1;

    public static void menuGoster() {
        while (true) {
            System.out.println("\n===== RESTORAN YÖNETİM SİSTEMİ =====");
            System.out.println("1. Masaları Listele");
            System.out.println("2. Garsonları ayarları");
            System.out.println("3. Ürünleri ayarları");
            System.out.println("4. Sipariş Oluştur");
            System.out.println("5. Siparişleri Listele");
            System.out.println("6. ödeme al ");
            System.out.println("7. Çıkış");
            System.out.print("Seçiminiz: ");
            int secim = scanner.nextInt();

            switch (secim) {
                case 1 -> masaListele();
                case 2 -> GarsonAyarlar.menuyuGoster();
                case 3 -> UrunAyarlar.menuyuGoster();
                case 4 -> siparisOlustur();
                case 5 -> siparisListele();
                case 6 -> odemeAl();
                case 7 -> System.exit(0);
                default -> System.out.println("Geçersiz seçim!");
            }
        }
    }

    private static void masaListele() {
        System.out.println("\n--- MASA LİSTESİ ---");
        masaDAO.masalariGetir().forEach(m ->
                System.out.println("Masa No: " + m.getMasaId() + " | Durum: " + m.getDurum()));
    }

    private static void garsonListele() {
        System.out.println("\n--- GARSON LİSTESİ ---");
        garsonDAO.garsonlariGetir().forEach(g ->
                System.out.println("Garson ID: " + g.getId() + " | Ad: " + g.getAd()));
    }

    private static void urunListele() {
        System.out.println("\n--- ÜRÜN LİSTESİ ---");
        for (Urun u : urunDAO.urunleriGetir()) {
            System.out.println(u.getId() + ". " + u.getName() + " - " + u.getFiyat() + "₺");
        }
    }

    private static void siparisOlustur() {
        System.out.println("\n--- 🧾 SİPARİŞ OLUŞTUR ---");

        masaListele();
        System.out.print("Masa numarasını girin: ");
        int masaId = scanner.nextInt();

        garsonListele();
        System.out.print("Garson ID girin: ");
        int garsonId = scanner.nextInt();

        List<Urun> urunler = new ArrayList<>();
        double toplam = 0;

        while (true) {
            urunListele();
            System.out.print("Ürün ID girin (bitirmek için 0): ");
            int urunId = scanner.nextInt();
            if (urunId == 0) break;

            Urun urun = urunDAO.urunleriGetir().stream()
                    .filter(u -> u.getId() == urunId)
                    .findFirst()
                    .orElse(null);

            if (urun != null) {
                urunler.add(urun);
                toplam += urun.getFiyat();
            } else {
                System.out.println("⚠️ Geçersiz ürün ID’si!");
            }
        }

        // 🔹 Otomatik artan ID ile sipariş oluşturuluyor

        Siparis siparis = new Siparis(siparisId++, masaId, garsonId, urunler, toplam, "Hazırlanıyor", false);

        siparisDAO.siparisEkle(siparis);
        System.out.println("✅ Sipariş başarıyla oluşturuldu!");
        System.out.println("📋 Sipariş ID: " + siparis.getSiparisId());
        System.out.println("💰 Toplam Tutar: " + toplam + "₺");
    }

    private static void garsonEkle() {
        System.out.println("Yeni garsonun adını giriniz:");
        String ad = scanner.nextLine();


        System.out.println("Garson şifre giriniz:");
        int sifre = scanner.nextInt();

        System.out.println("Garson ID giriniz:");
        int garsonId = scanner.nextInt();

        System.out.println("Garsonun maaşını giriniz:");
        double maas = scanner.nextDouble();
        scanner.nextLine(); // buffer temizleme

        Garson yeniGarson = new Garson(ad, sifre, garsonId, maas);
        garsonListesi.add(yeniGarson);

        System.out.println("✅ Garson başarıyla eklendi: " + yeniGarson);
    }

    private static void siparisListele() {
        System.out.println("\n--- TÜM SİPARİŞLER ---");

        List<Siparis> siparisListesi = siparisDAO.siparisleriGetir();

        if (siparisListesi == null || siparisListesi.isEmpty()) {
            System.out.println("⚠️ Henüz sipariş bulunmuyor.");
            return;
        }

        for (Siparis s : siparisListesi) {
            Garson garson = garsonDAO.garsonBul(s.getGarsonId());
            String garsonAdi = (garson != null) ? garson.getAd() : "Bilinmiyor";

            System.out.println("🪑 Masa No: " + s.getMasaId() + " | Garson: " + garsonAdi);
            System.out.println("🍽️ Ürünler:");
            for (Urun u : s.getUrunler()) {
                System.out.println(" - " + u.getName() + " (" + u.getFiyat() + "₺)");
            }
            System.out.println("💰 Toplam: " + s.getToplam() + "₺\n");
        }
    }

    // 💰 Ödeme al metodu tam bu kısma eklenir:
    private static void odemeAl() {
        System.out.print("Masa numarasını girin: ");
        int masaNo = scanner.nextInt();
        double toplamTutar = siparisDAO.masaToplamTutar(masaNo);

        System.out.println("Masa " + masaNo + " toplam: " + toplamTutar + " TL");
        System.out.print("Alınan ödeme miktarı: ");
        double odeme = scanner.nextDouble();

        if (odeme >= toplamTutar) {
            System.out.println("✅ Ödeme alındı. Para üstü: " + (odeme - toplamTutar) + " TL");
            siparisDAO.siparisKapat(masaNo);
        } else {
            System.out.println("⚠️ Yetersiz ödeme! Eksik: " + (toplamTutar - odeme) + " TL");
        }
    }

    public static void seedData() {
        // Masalar
        masaDAO.masaEkle(new Masa(1, "Boş"));
        masaDAO.masaEkle(new Masa(2, "Dolu"));
        masaDAO.masaEkle(new Masa(3, "Boş"));
        masaDAO.masaEkle(new Masa(4, "Boş"));

        // Ürünler
        urunDAO.urunEkle(new Urun(1, "Kahve", 45));
        urunDAO.urunEkle(new Urun(2, "Çay", 25));
        urunDAO.urunEkle(new Urun(3, "Tost", 60));
        urunDAO.urunEkle(new Urun(4, "Hamburger", 120));
        urunDAO.urunEkle(new Urun(5, "Pizza", 180));
        urunDAO.urunEkle(new Urun(6, "Pasta", 80));
        urunDAO.urunEkle(new Urun(7, "Salata", 70));
        urunDAO.urunEkle(new Urun(8, "Limonata", 35));
        urunDAO.urunEkle(new Urun(9, "Tatlı", 90));
        urunDAO.urunEkle(new Urun(10, "Kola", 40));
        urunDAO.urunEkle(new Urun(11, "Makarna", 95));
    }

}
