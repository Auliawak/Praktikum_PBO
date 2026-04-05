Nama: Aulia Nur Rachman
Kelas: B1
NIM: 2409106069

---
 
## Rancangan Inheritance
 
Program ini menggunakan Single Inheritance, yaitu setiap subclass hanya mewarisi dari satu parent class.
 
```
Mobil  (parent class)
  |
  +-- MobilBensin  (subclass 1)
  |
  +-- MobilListrik (subclass 2)
```
 
Pemilihan ini logis karena di dunia nyata Honda memang memiliki dua lini produk utama: kendaraan bermesin bensin dan kendaraan bertenaga listrik. Keduanya adalah mobil, sehingga memiliki data dasar yang sama, namun masing-masing punya spesifikasi teknis yang berbeda.
 
---
 
## Penjelasan Setiap Class
 
### Mobil (Parent Class)
 
Berisi data yang dimiliki oleh semua jenis mobil tanpa terkecuali:
id, nama, tipe, warna, tahun, harga, dan status tersedia.
 
Juga memiliki method `getKategori()` dan `tampilInfo()` yang dapat di-override oleh subclass.
 
### MobilBensin (Subclass 1)
 
Mewarisi semua field dan method dari Mobil, lalu menambahkan:
- `kapasitasMesin` (double) - kapasitas mesin dalam satuan cc
- `jenisBahanBakar` (String) - jenis BBM yang digunakan
 
Method yang di-override:
- `getKategori()` - mengembalikan "Mobil Bensin"
- `tampilInfo()` - memanggil `super.tampilInfo()` lalu menambah baris mesin dan BBM
 
### MobilListrik (Subclass 2)
 
Mewarisi semua field dan method dari Mobil, lalu menambahkan:
- `kapasitasBaterai` (double) - kapasitas baterai dalam satuan kWh
- `jangkauanKm` (int) - jarak tempuh maksimal dalam km
 
Method yang di-override:
- `getKategori()` - mengembalikan "Mobil Listrik"
- `tampilInfo()` - memanggil `super.tampilInfo()` lalu menambah baris baterai dan jangkauan
 
---
 
## Konsep Inheritance yang Diterapkan
 
### 1. Kata kunci extends
 
Digunakan untuk mendeklarasikan bahwa suatu class mewarisi class lain.
 
```java
public class MobilBensin extends Mobil { ... }
public class MobilListrik extends Mobil { ... }
```
 
### 2. Kata kunci super
 
Digunakan di constructor subclass untuk memanggil constructor parent class, sehingga field yang ada di parent tidak perlu diinisialisasi ulang.
 
```java
public MobilBensin(String id, String nama, String tipe,
                   String warna, int tahun, double harga,
                   double kapasitasMesin, String jenisBahanBakar) {
    super(id, nama, tipe, warna, tahun, harga);
    this.kapasitasMesin  = kapasitasMesin;
    this.jenisBahanBakar = jenisBahanBakar;
}
```
 
Juga digunakan di `tampilInfo()` untuk memanggil versi method milik parent sebelum menambahkan baris khusus subclass.
 
```java
@Override
public void tampilInfo() {
    super.tampilInfo();
    System.out.println("  Mesin     : " + this.kapasitasMesin + " cc");
    System.out.println("  BBM       : " + this.jenisBahanBakar);
}
```
 
### 3. Method Overriding (@Override)
 
Subclass menimpa method `getKategori()` dan `tampilInfo()` yang sudah ada di parent class. Dengan cara ini, ketika `tampilInfo()` dipanggil pada objek `MobilBensin`, yang dieksekusi adalah versi `MobilBensin`, bukan versi `Mobil`.
 
### 4. Polymorphism melalui ArrayList
 
`DealerManager` menyimpan semua mobil dalam satu `ArrayList<Mobil>`. Meskipun isinya bisa berupa `MobilBensin` maupun `MobilListrik`, keduanya bisa diperlakukan sebagai `Mobil` karena keduanya adalah turunan dari `Mobil`.
 
```java
private ArrayList<Mobil> daftarMobil;
```
 
### 5. instanceof untuk pengecekan tipe
 
Digunakan di menu ubah untuk menampilkan dan mengubah field spesifik sesuai jenis mobilnya.
 
```java
if (target instanceof MobilBensin mb) {
    mb.setKapasitasMesin(...);
}
 
if (target instanceof MobilListrik ml) {
    ml.setKapasitasBaterai(...);
}
```
 
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
 
