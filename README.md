Nama: Aulia Nur Rachman
Kelas: B1
NIM: 2409106069

---
 
## Penerapan Polymorphism
 
### Method Overriding
 
Overriding adalah ketika subclass mendefinisikan ulang method yang sudah ada di parent class dengan tanda @Override.
 
#### 1. getKategori()
 
Didefinisikan di Mobil, di-override di MobilBensin dan MobilListrik. Dipakai untuk menampilkan label kategori yang tepat pada setiap objek.
 
```java
public String getKategori() { return "Mobil"; }
 
@Override
public String getKategori() { return "Mobil Bensin"; }
 
@Override
public String getKategori() { return "Mobil Listrik"; }
```
 
#### 2. hitungPajak()
 
Didefinisikan di Mobil dengan tarif 10%. Di-override di MobilBensin dengan tarif yang berbeda tergantung kapasitas mesin (10% untuk mesin di bawah 1500 cc, 12.5% untuk di atasnya), dan di MobilListrik dengan tarif 1% sebagai bentuk insentif pemerintah untuk kendaraan listrik.
 
```java
public double hitungPajak() { return this.harga * 0.10; }
 
@Override
public double hitungPajak() {
    if (this.kapasitasMesin <= 1500) return getHarga() * 0.10;
    else return getHarga() * 0.125;
}
 
@Override
public double hitungPajak() { return getHarga() * 0.01; }
```
 
#### 3. tampilInfo()
 
Didefinisikan di Mobil untuk menampilkan data dasar. Di-override di MobilBensin untuk menambah baris mesin dan BBM, dan di MobilListrik untuk menambah baris kapasitas baterai dan jangkauan.
 
```java
public void tampilInfo() { ... }
 
@Override
public void tampilInfo() {
    super.tampilInfo();
    System.out.println("  Mesin     : " + this.kapasitasMesin + " cc");
    System.out.println("  BBM       : " + this.jenisBahanBakar);
}
 
@Override
public void tampilInfo() {
    super.tampilInfo();
    System.out.println("  Baterai   : " + this.kapasitasBaterai + " kWh");
    System.out.println("  Jangkauan : " + this.jangkauanKm + " km");
}
```
 
---
 
### Method Overloading
 
Overloading adalah ketika satu class memiliki beberapa method dengan nama yang sama tetapi parameter yang berbeda.
 
#### 1. hitungDiskon() di class Mobil (3 versi)
 
Logis karena dealer bisa memberikan diskon dalam berbagai cara: diskon standar tanpa argumen, diskon dengan persentase tertentu, atau diskon dengan persentase sekaligus alasan yang tercatat.
 
```java
public double hitungDiskon() {
    return this.harga * 0.05;
}
 
public double hitungDiskon(double persentase) {
    return this.harga * (persentase / 100);
}
 
public double hitungDiskon(double persentase, String alasan) {
    double diskon = this.harga * (persentase / 100);
    System.out.println("  Alasan diskon : " + alasan);
    return diskon;
}
```
 
#### 2. cariMobil() di class DealerManager (3 versi)
 
Logis karena pencarian mobil di dealer bisa dilakukan dengan berbagai kriteria: berdasarkan ID, berdasarkan kata kunci nama atau tipe, atau berdasarkan rentang tahun produksi.
 
```java
public Mobil cariMobil(String id) { ... }
 
public ArrayList<Mobil> cariMobil(String kata, boolean cariByNama) { ... }
 
public ArrayList<Mobil> cariMobil(int tahunDari, int tahunSampai) { ... }
```
 
---
 
## Ringkasan Polymorphism
 
| Jenis       | Method            | Lokasi            | Keterangan                                      |
|-------------|-------------------|-------------------|-------------------------------------------------|
| Overriding  | getKategori()     | MobilBensin, MobilListrik | Mengembalikan label kategori yang sesuai  |
| Overriding  | hitungPajak()     | MobilBensin, MobilListrik | Tarif pajak berbeda per jenis kendaraan   |
| Overriding  | tampilInfo()      | MobilBensin, MobilListrik | Menambah baris info khusus per subclass   |
| Overloading | hitungDiskon()    | Mobil             | 3 versi: tanpa param, dengan persen, dengan alasan |
| Overloading | cariMobil()       | DealerManager     | 3 versi: by ID, by nama/tipe, by rentang tahun |
 
---
 
## Data Awal
 
| ID        | Kategori      | Nama        | Tipe      | Tahun | Harga          |
|-----------|---------------|-------------|-----------|-------|----------------|
| HONDA-001 | Mobil Bensin  | Honda Brio  | Hatchback | 2023  | Rp 175.000.000 |
| HONDA-002 | Mobil Bensin  | Honda Jazz  | Hatchback | 2022  | Rp 270.000.000 |
| HONDA-003 | Mobil Bensin  | Honda City  | Sedan     | 2023  | Rp 355.000.000 |
| HONDA-004 | Mobil Bensin  | Honda Civic | Sedan     | 2024  | Rp 570.000.000 |
| HONDA-005 | Mobil Bensin  | Honda CR-V  | SUV       | 2024  | Rp 620.000.000 |
| HONDA-006 | Mobil Listrik | Honda e     | Hatchback | 2024  | Rp 750.000.000 |
| HONDA-007 | Mobil Listrik | Honda e:Ny1 | SUV       | 2024  | Rp 850.000.000 |
 
