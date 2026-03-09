public class Mobil {

    private String id;
    private String nama;
    private String tipe;
    private String warna;
    private int tahun;
    private double harga;
    private boolean tersedia;

    public Mobil() {
        this.id       = "HONDA-000";
        this.nama     = "Unknown";
        this.tipe     = "Unknown";
        this.warna    = "Unknown";
        this.tahun    = 2024;
        this.harga    = 0.0;
        this.tersedia = true;
    }

    public Mobil(String id, String nama, String tipe,
                 String warna, int tahun, double harga) {
        this.id       = id;
        this.nama     = nama;
        this.tipe     = tipe;
        this.warna    = warna;
        this.tahun    = tahun;
        this.harga    = harga;
        this.tersedia = true;
    }

    public Mobil(String id, String nama, String tipe,
                 String warna, int tahun, double harga, boolean tersedia) {
        this(id, nama, tipe, warna, tahun, harga);
        this.tersedia = tersedia;
    }

    public String  getId()      { return this.id; }
    public String  getNama()    { return this.nama; }
    public String  getTipe()    { return this.tipe; }
    public String  getWarna()   { return this.warna; }
    public int     getTahun()   { return this.tahun; }
    public double  getHarga()   { return this.harga; }
    public boolean isTersedia() { return this.tersedia; }

    public void setNama(String nama)          { this.nama     = nama; }
    public void setTipe(String tipe)          { this.tipe     = tipe; }
    public void setWarna(String warna)        { this.warna    = warna; }
    public void setTahun(int tahun)           { this.tahun    = tahun; }
    public void setHarga(double harga)        { this.harga    = harga; }
    public void setTersedia(boolean tersedia) { this.tersedia = tersedia; }

    public void tampilInfo() {
        System.out.println("  ID        : " + this.id);
        System.out.println("  Nama      : " + this.nama);
        System.out.println("  Tipe      : " + this.tipe);
        System.out.println("  Warna     : " + this.warna);
        System.out.println("  Tahun     : " + this.tahun);
        System.out.printf ("  Harga     : Rp %,.0f%n", this.harga);
        System.out.println("  Status    : " + (this.tersedia ? "Tersedia" : "Tidak Tersedia"));
    }
}