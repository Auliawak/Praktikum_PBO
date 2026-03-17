import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    private Scanner      scanner;
    private InputManager input;
    private DealerManager dealer;

    public Main() {
        this.scanner = new Scanner(System.in);
        this.input   = new InputManager(scanner);
        this.dealer  = new DealerManager();
    }

    public static void main(String[] args) {
        new Main().jalankan();
    }

    private void jalankan() {
        tampilHeader();

        boolean jalan = true;
        while (jalan) {
            tampilMenu();
            int pilihan = input.bacaInt("  Pilih menu [0-4]: ");
            System.out.println();

            switch (pilihan) {
                case 1 -> menuTambah();
                case 2 -> menuLihat();
                case 3 -> menuUbah();
                case 4 -> menuHapus();
                case 0 -> {
                    System.out.println("  Terima kasih telah menggunakan Sistem Dealer Honda!");
                    System.out.println("  Program selesai. Sampai jumpa!");
                    System.out.println();
                    jalan = false;
                }
                default -> System.out.println("  [!] Pilihan tidak valid. Masukkan angka 0-4.\n");
            }
        }

        scanner.close();
    }

    private void tampilHeader() {
        System.out.println("==========================================================");
        System.out.println("      SISTEM MANAJEMEN DEALER MOBIL HONDA");
        System.out.println("      Pemrograman Berorientasi Objek - Posttest 2");
        System.out.println("      Encapsulation & Access Modifier");
        System.out.println("==========================================================");
        System.out.println();
    }

    private void tampilMenu() {
        System.out.println("----------------------------------------------------------");
        System.out.println("  MENU UTAMA");
        System.out.println("----------------------------------------------------------");
        System.out.println("  1. Tambah Data Mobil");
        System.out.println("  2. Lihat Semua Mobil");
        System.out.println("  3. Ubah Data Mobil");
        System.out.println("  4. Hapus Data Mobil");
        System.out.println("  0. Keluar");
        System.out.println("----------------------------------------------------------");
    }

    private void garis() {
        System.out.println("----------------------------------------------------------");
    }

    private void menuTambah() {
        System.out.println("  [TAMBAH DATA MOBIL]");
        garis();

        String id = dealer.generateIdBaru();
        System.out.println("  ID Otomatis : " + id);
        System.out.println();

        System.out.print("  Nama Model  : ");
        String nama = input.bacaString();

        System.out.print("  Tipe Mobil  : ");
        String tipe = input.bacaString();

        System.out.print("  Warna       : ");
        String warna = input.bacaString();

        int    tahun = input.bacaInt("  Tahun       : ");
        double harga = input.bacaDouble("  Harga (Rp)  : ");

        Mobil mobilBaru = new Mobil(id, nama, tipe, warna, tahun, harga);
        dealer.tambahMobil(mobilBaru);

        System.out.println();
        System.out.println("  [OK] Mobil " + nama + " berhasil ditambahkan! ID: " + id);
        garis();
        System.out.println();
    }

    private void menuLihat() {
        System.out.println("  [DAFTAR SEMUA MOBIL]");
        garis();

        ArrayList<Mobil> list = dealer.getDaftarMobil();

        if (list.isEmpty()) {
            System.out.println("  Belum ada data mobil.");
            garis();
            System.out.println();
            return;
        }

        System.out.printf("  %-11s  %-18s  %-11s  %-8s  %-6s  %-20s  %-14s%n",
                "ID", "Nama", "Tipe", "Warna", "Tahun", "Harga", "Status");
        garis();

        for (Mobil m : list) {
            String hargaStr  = "Rp " + String.format("%,.0f", m.getHarga());
            String statusStr = m.isTersedia() ? "Tersedia" : "Tidak Tersedia";
            System.out.printf("  %-11s  %-18s  %-11s  %-8s  %-6d  %-20s  %-14s%n",
                    m.getId(), m.getNama(), m.getTipe(), m.getWarna(),
                    m.getTahun(), hargaStr, statusStr);
        }

        garis();
        System.out.println("  Total: " + list.size() + " unit");
        System.out.println();
    }

    private void menuUbah() {
        System.out.println("  [UBAH DATA MOBIL]");
        garis();

        ArrayList<Mobil> list = dealer.getDaftarMobil();
        if (list.isEmpty()) {
            System.out.println("  Belum ada data mobil untuk diubah.");
            garis();
            System.out.println();
            return;
        }

        System.out.printf("  %-11s  %-18s%n", "ID", "Nama");
        garis();
        for (Mobil m : list) {
            System.out.printf("  %-11s  %-18s%n", m.getId(), m.getNama());
        }
        garis();

        System.out.print("  Masukkan ID yang akan diubah: ");
        String id = input.bacaString();

        Mobil target = dealer.cariMobilById(id);
        if (target == null) {
            System.out.println("  [!] Mobil dengan ID " + id + " tidak ditemukan.");
            garis();
            System.out.println();
            return;
        }

        System.out.println();
        System.out.println("  Data saat ini:");
        target.tampilInfo();
        System.out.println();
        System.out.println("  Masukkan data baru (Enter = tidak diubah):");
        System.out.println();

        System.out.print("  Nama Model  [" + target.getNama() + "]: ");
        String nama = input.bacaRaw();
        if (nama.isEmpty()) nama = target.getNama();

        System.out.print("  Tipe Mobil  [" + target.getTipe() + "]: ");
        String tipe = input.bacaRaw();
        if (tipe.isEmpty()) tipe = target.getTipe();

        System.out.print("  Warna       [" + target.getWarna() + "]: ");
        String warna = input.bacaRaw();
        if (warna.isEmpty()) warna = target.getWarna();

        int    tahun = input.bacaIntOpsional("  Tahun       [" + target.getTahun() + "]: ", target.getTahun());
        double harga = input.bacaDoubleOpsional("  Harga (Rp)  [" + (int) target.getHarga() + "]: ", target.getHarga());

        System.out.print("  Status (y=Tersedia, n=Tidak) [" + (target.isTersedia() ? "y" : "n") + "]: ");
        String inputStatus = input.bacaRaw().toLowerCase();
        boolean tersedia = inputStatus.isEmpty() ? target.isTersedia() : inputStatus.equals("y");

        boolean sukses = dealer.updateMobil(id, nama, tipe, warna, tahun, harga, tersedia);
        System.out.println();
        System.out.println(sukses
                ? "  [OK] Data mobil " + id + " berhasil diperbarui!"
                : "  [!] Gagal memperbarui data.");
        garis();
        System.out.println();
    }

    private void menuHapus() {
        System.out.println("  [HAPUS DATA MOBIL]");
        garis();

        ArrayList<Mobil> list = dealer.getDaftarMobil();
        if (list.isEmpty()) {
            System.out.println("  Belum ada data mobil untuk dihapus.");
            garis();
            System.out.println();
            return;
        }

        System.out.printf("  %-11s  %-18s%n", "ID", "Nama");
        garis();
        for (Mobil m : list) {
            System.out.printf("  %-11s  %-18s%n", m.getId(), m.getNama());
        }
        garis();

        System.out.print("  Masukkan ID yang akan dihapus: ");
        String id = input.bacaString();

        Mobil target = dealer.cariMobilById(id);
        if (target == null) {
            System.out.println("  [!] Mobil dengan ID " + id + " tidak ditemukan.");
            garis();
            System.out.println();
            return;
        }

        System.out.println();
        System.out.println("  Data yang akan dihapus:");
        target.tampilInfo();
        System.out.println();
        System.out.print("  Yakin ingin menghapus? (y/n): ");
        String konfirmasi = input.bacaRaw().toLowerCase();

        if (konfirmasi.equals("y")) {
            dealer.hapusMobil(id);
            System.out.println("  [OK] Mobil " + id + " berhasil dihapus!");
        } else {
            System.out.println("  [!] Penghapusan dibatalkan.");
        }
        garis();
        System.out.println();
    }
}