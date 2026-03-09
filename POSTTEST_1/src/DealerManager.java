import java.util.ArrayList;

public class DealerManager {

    // ===== PROPERTI =====
    private ArrayList<Mobil> daftarMobil;
    private int counterIdOtomatis;

    // ===== CONSTRUCTOR =====
    public DealerManager() {
        this.daftarMobil       = new ArrayList<Mobil>();
        this.counterIdOtomatis = 1;
        initDataAwal();
    }

    // ===== PRIVATE METHOD =====

    private void initDataAwal() {
        this.daftarMobil.add(new Mobil(buatId(), "Honda Brio",  "Hatchback", "Merah",  2023, 175000000));
        this.daftarMobil.add(new Mobil(buatId(), "Honda Jazz",  "Hatchback", "Putih",  2022, 270000000));
        this.daftarMobil.add(new Mobil(buatId(), "Honda City",  "Sedan",     "Hitam",  2023, 355000000));
        this.daftarMobil.add(new Mobil(buatId(), "Honda Civic", "Sedan",     "Silver", 2024, 570000000));
        this.daftarMobil.add(new Mobil(buatId(), "Honda CR-V",  "SUV",       "Biru",   2024, 620000000));
    }

    private String buatId() {
        String id = String.format("HONDA-%03d", this.counterIdOtomatis);
        this.counterIdOtomatis++;
        return id;
    }

    // ===== PUBLIC METHOD =====

    // Buat ID baru untuk input user (Create)
    public String generateIdBaru() {
        String id = String.format("HONDA-%03d", this.counterIdOtomatis);
        this.counterIdOtomatis++;
        return id;
    }

    // ==================== CREATE ====================
    public void tambahMobil(Mobil mobil) {
        this.daftarMobil.add(mobil);
    }

    // ==================== READ ====================
    public ArrayList<Mobil> getDaftarMobil() {
        return this.daftarMobil;
    }

    public Mobil cariMobilById(String id) {
        for (Mobil m : this.daftarMobil) {
            if (m.getId().equalsIgnoreCase(id)) {
                return m;
            }
        }
        return null;
    }

    // ==================== UPDATE ====================
    public boolean updateMobil(String id, String nama, String tipe,
                               String warna, int tahun, double harga, boolean tersedia) {
        Mobil target = cariMobilById(id);
        if (target == null) {
            return false;
        }
        target.setNama(nama);
        target.setTipe(tipe);
        target.setWarna(warna);
        target.setTahun(tahun);
        target.setHarga(harga);
        target.setTersedia(tersedia);
        return true;
    }

    // ==================== DELETE ====================
    public boolean hapusMobil(String id) {
        Mobil target = cariMobilById(id);
        if (target == null) {
            return false;
        }
        this.daftarMobil.remove(target);
        return true;
    }
}