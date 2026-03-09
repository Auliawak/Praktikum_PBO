import java.util.ArrayList;
import java.util.Scanner;

    Scanner scanner = new Scanner(System.in);
    DealerManager dealer = new DealerManager();

    void main() {
        tampilHeader();

        boolean jalan = true;
        while (jalan) {
            tampilMenu();
            int pilihan = bacaInt("  Pilih menu [0-4]: ");
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


    void tampilHeader() {
        System.out.println("==========================================================");
        System.out.println("      SISTEM MANAJEMEN DEALER MOBIL HONDA");
        System.out.println("      Pemrograman Berorientasi Objek - Modul 2");
        System.out.println("      OpenJDK 25.0.2 | Informatika UNMUL");
        System.out.println("==========================================================");
        System.out.println();
    }

    void tampilMenu() {
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

    void garis() {
        System.out.println("----------------------------------------------------------");
    }


    void menuTambah() {
        System.out.println("  [TAMBAH DATA MOBIL]");
        garis();

        String id = dealer.generateIdBaru();
        System.out.println("  ID Otomatis : " + id);
        System.out.println();

        System.out.print("  Nama Model  : ");
        String nama = bacaString();

        System.out.print("  Tipe Mobil  : ");
        String tipe = bacaString();

        System.out.print("  Warna       : ");
        String warna = bacaString();

        int tahun = bacaInt("  Tahun       : ");
        double harga = bacaDouble("  Harga (Rp)  : ");

        Mobil mobilBaru = new Mobil(id, nama, tipe, warna, tahun, harga);
        dealer.tambahMobil(mobilBaru);

        System.out.println();
        System.out.println("  [OK] Mobil " + nama + " berhasil ditambahkan! ID: " + id);
        garis();
        System.out.println();
    }


    void menuLihat() {
        System.out.println("  [DAFTAR SEMUA MOBIL]");
        garis();

        ArrayList<Mobil> list = dealer.getDaftarMobil();

        if (list.isEmpty()) {
            System.out.println("  Belum ada data mobil.");
            garis();
            System.out.println();
            return;
        }

        System.out.printf("  %-11s  %-18s  %-11s  %-8s  %-6s  %-18s  %-14s%n",
                "ID", "Nama", "Tipe", "Warna", "Tahun", "Harga", "Status");
        garis();

        for (Mobil m : list) {
            String hargaStr = "Rp " + String.format("%,.0f", m.getHarga());
            String statusStr = m.isTersedia() ? "Tersedia" : "Tidak Tersedia";
            System.out.printf("  %-11s  %-18s  %-11s  %-8s  %-6d  %-20s  %-14s%n",
                    m.getId(),
                    m.getNama(),
                    m.getTipe(),
                    m.getWarna(),
                    m.getTahun(),
                    hargaStr,
                    statusStr);
        }

        garis();
        System.out.println("  Total: " + list.size() + " unit");
        System.out.println();
    }


    void menuUbah() {
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
        String id = bacaString();

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
        String nama = scanner.nextLine().trim();
        if (nama.isEmpty()) nama = target.getNama();

        System.out.print("  Tipe Mobil  [" + target.getTipe() + "]: ");
        String tipe = scanner.nextLine().trim();
        if (tipe.isEmpty()) tipe = target.getTipe();

        System.out.print("  Warna       [" + target.getWarna() + "]: ");
        String warna = scanner.nextLine().trim();
        if (warna.isEmpty()) warna = target.getWarna();

        int tahun = bacaIntOpsional("  Tahun       [" + target.getTahun() + "]: ", target.getTahun());
        double harga = bacaDoubleOpsional("  Harga (Rp)  [" + (int) target.getHarga() + "]: ", target.getHarga());

        System.out.print("  Status (y=Tersedia, n=Tidak) [" + (target.isTersedia() ? "y" : "n") + "]: ");
        String inputStatus = scanner.nextLine().trim().toLowerCase();
        boolean tersedia;
        if (inputStatus.isEmpty()) {
            tersedia = target.isTersedia();
        } else {
            tersedia = inputStatus.equals("y");
        }

        boolean sukses = dealer.updateMobil(id, nama, tipe, warna, tahun, harga, tersedia);
        System.out.println();
        if (sukses) {
            System.out.println("  [OK] Data mobil " + id + " berhasil diperbarui!");
        } else {
            System.out.println("  [!] Gagal memperbarui data.");
        }
        garis();
        System.out.println();
    }


    void menuHapus() {
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
        String id = bacaString();

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
        String konfirmasi = scanner.nextLine().trim().toLowerCase();

        if (konfirmasi.equals("y")) {
            dealer.hapusMobil(id);
            System.out.println("  [OK] Mobil " + id + " berhasil dihapus!");
        } else {
            System.out.println("  [!] Penghapusan dibatalkan.");
        }
        garis();
        System.out.println();
    }


    String bacaString() {
        String hasil;
        while (true) {
            hasil = scanner.nextLine().trim();
            if (!hasil.isEmpty()) {
                return hasil;
            }
            System.out.print("  Tidak boleh kosong, coba lagi: ");
        }
    }

    int bacaInt(String prompt) {
        while (true) {
            System.out.print(prompt);
            String masukan = scanner.nextLine().trim();
            try {
                return Integer.parseInt(masukan);
            } catch (NumberFormatException e) {
                System.out.println("  Harus angka bulat. Coba lagi.");
            }
        }
    }

    int bacaIntOpsional(String prompt, int defaultNilai) {
        while (true) {
            System.out.print(prompt);
            String masukan = scanner.nextLine().trim();
            if (masukan.isEmpty()) return defaultNilai;
            try {
                return Integer.parseInt(masukan);
            } catch (NumberFormatException e) {
                System.out.println("  Harus angka bulat. Coba lagi.");
            }
        }
    }

    double bacaDouble(String prompt) {
        while (true) {
            System.out.print(prompt);
            String masukan = scanner.nextLine().trim();
            try {
                return Double.parseDouble(masukan);
            } catch (NumberFormatException e) {
                System.out.println("  Harus angka. Coba lagi.");
            }
        }
    }

    double bacaDoubleOpsional(String prompt, double defaultNilai) {
        while (true) {
            System.out.print(prompt);
            String masukan = scanner.nextLine().trim();
            if (masukan.isEmpty()) return defaultNilai;
            try {
                return Double.parseDouble(masukan);
            } catch (NumberFormatException e) {
                System.out.println("  Harus angka. Coba lagi.");
            }
        }
    }
}