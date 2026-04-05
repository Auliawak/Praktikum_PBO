public class MobilBensin extends Mobil {

    private double kapasitasMesin;
    private String jenisBahanBakar;

    public MobilBensin(String id, String nama, String tipe,
                       String warna, int tahun, double harga,
                       double kapasitasMesin, String jenisBahanBakar) {
        super(id, nama, tipe, warna, tahun, harga);
        this.kapasitasMesin  = kapasitasMesin;
        this.jenisBahanBakar = jenisBahanBakar;
    }

    public double getKapasitasMesin()  { return this.kapasitasMesin; }
    public String getJenisBahanBakar() { return this.jenisBahanBakar; }

    public void setKapasitasMesin(double kapasitasMesin) {
        if (kapasitasMesin > 0) {
            this.kapasitasMesin = kapasitasMesin;
        }
    }

    public void setJenisBahanBakar(String jenisBahanBakar) {
        if (jenisBahanBakar != null && !jenisBahanBakar.trim().isEmpty()) {
            this.jenisBahanBakar = jenisBahanBakar;
        }
    }

    @Override
    public String getKategori() {
        return "Mobil Bensin";
    }

    @Override
    public void tampilInfo() {
        super.tampilInfo();
        System.out.println("  Mesin     : " + this.kapasitasMesin + " cc");
        System.out.println("  BBM       : " + this.jenisBahanBakar);
    }
}