Nama: Aulia Nur Rachman
Kelas: B1
NIM: 2409106069

---
 
## Hierarki Class
 
```
<<interface>>
ITerhitung
  + hitungDiskon(double) : double
  + hitungHargaAkhir(double) : double
        |
        | implements
        |
<<abstract>>
Mobil
  - id, nama, tipe, warna, tahun, harga, tersedia
  + getKategori() : String      [abstract]
  + hitungPajak() : double      [abstract]
  + hitungDiskon() : double
  + hitungDiskon(double) : double       [implements ITerhitung]
  + hitungDiskon(double, String) : double
  + hitungHargaAkhir(double) : double   [implements ITerhitung]
  + tampilInfo() : void
        |
        +-------------------+
        |                   |
MobilBensin             MobilListrik
  - kapasitasMesin        - kapasitasBaterai
  - jenisBahanBakar       - jangkauanKm
```
 
---
 
## Penerapan Abstract Class
 
Class `Mobil` diubah menjadi abstract class karena dalam konteks dealer Honda yang nyata, tidak ada objek yang hanya berstatus "Mobil" tanpa jenis yang jelas. Setiap kendaraan pasti merupakan mobil bensin atau mobil listrik. Oleh karena itu, class `Mobil` tidak boleh diinstansiasi langsung.
 
```java
public abstract class Mobil implements ITerhitung {
    ...
}
```
 
Pernyataan `new Mobil(...)` akan menyebabkan error kompilasi, yang memastikan programmer tidak bisa membuat objek Mobil generik secara tidak sengaja.
 
---
 
## Penerapan Abstract Method
 
Dua method dideklarasikan abstract di class `Mobil` karena implementasinya berbeda-beda tergantung jenis mobil dan tidak ada nilai default yang masuk akal untuk class induknya.
 
```java
public abstract String getKategori();
 
public abstract double hitungPajak();
```
 
Karena kedua method ini abstract, setiap subclass wajib mengimplementasikannya, jika tidak maka subclass tersebut juga harus dideklarasikan abstract.
 
Implementasi di `MobilBensin`:
 
```java
@Override
public String getKategori() { return "Mobil Bensin"; }
 
@Override
public double hitungPajak() {
    if (this.kapasitasMesin <= 1500) return getHarga() * 0.10;
    else return getHarga() * 0.125;
}
```
 
Implementasi di `MobilListrik`:
 
```java
@Override
public String getKategori() { return "Mobil Listrik"; }
 
@Override
public double hitungPajak() { return getHarga() * 0.01; }
```
 
---
 
## Penerapan Interface
 
Interface `ITerhitung` mendefinisikan kontrak perhitungan harga yang harus dipenuhi oleh semua kendaraan di sistem dealer. Ini logis karena sistem kasir atau sistem harga dealer membutuhkan jaminan bahwa semua objek kendaraan pasti bisa menghitung diskon dan harga akhirnya.
 
```java
public interface ITerhitung {
    double hitungDiskon(double persentase);
    double hitungHargaAkhir(double persentaseDiskon);
}
```
 
`Mobil` mengimplementasikan interface ini dan menyediakan implementasinya:
 
```java
@Override
public double hitungDiskon(double persentase) {
    return this.harga * (persentase / 100);
}
 
@Override
public double hitungHargaAkhir(double persentaseDiskon) {
    double diskon = hitungDiskon(persentaseDiskon);
    double pajak  = hitungPajak();
    return this.harga - diskon + pajak;
}
```
 
Karena `Mobil` adalah abstract class yang sudah mengimplementasikan interface, subclass `MobilBensin` dan `MobilListrik` mewarisi implementasi tersebut secara otomatis tanpa perlu menulisnya ulang.
 
---
 
## Ringkasan Konsep yang Diterapkan
 
| Konsep | Lokasi | Keterangan |
|---|---|---|
| Abstract Class | `Mobil` | Tidak bisa diinstansiasi langsung |
| Abstract Method | `getKategori()`, `hitungPajak()` di `Mobil` | Wajib diimplementasi oleh subclass |
| Interface | `ITerhitung` | Kontrak 2 method perhitungan harga |
| implements | `Mobil implements ITerhitung` | Mobil memenuhi kontrak ITerhitung |
| Overriding abstract | `MobilBensin`, `MobilListrik` | Implementasi getKategori dan hitungPajak |
| Overloading | `hitungDiskon()` di `Mobil` | 3 versi dengan parameter berbeda |
| Overloading | `cariMobil()` di `DealerManager` | 3 versi pencarian |
 
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
 
