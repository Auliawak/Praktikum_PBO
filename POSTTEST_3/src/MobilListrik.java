public class MobilListrik extends Mobil {

    private double kapasitasBaterai;
    private int    jangkauanKm;

    public MobilListrik(String id, String nama, String tipe,
                        String warna, int tahun, double harga,
                        double kapasitasBaterai, int jangkauanKm) {
        super(id, nama, tipe, warna, tahun, harga);
        this.kapasitasBaterai = kapasitasBaterai;
        this.jangkauanKm      = jangkauanKm;
    }

    public double getKapasitasBaterai() { return this.kapasitasBaterai; }
    public int    getJangkauanKm()      { return this.jangkauanKm; }

    public void setKapasitasBaterai(double kapasitasBaterai) {
        if (kapasitasBaterai > 0) {
            this.kapasitasBaterai = kapasitasBaterai;
        }
    }

    public void setJangkauanKm(int jangkauanKm) {
        if (jangkauanKm > 0) {
            this.jangkauanKm = jangkauanKm;
        }
    }

    @Override
    public String getKategori() {
        return "Mobil Listrik";
    }

    @Override
    public void tampilInfo() {
        super.tampilInfo();
        System.out.println("  Baterai   : " + this.kapasitasBaterai + " kWh");
        System.out.println("  Jangkauan : " + this.jangkauanKm + " km");
    }
}