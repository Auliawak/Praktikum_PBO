Nama: Aulia Nur Rachman
Kelas: B1
NIM: 2409106069

---

## Penerapan Access Modifier

Program ini menerapkan keempat jenis access modifier yang ada di Java.

### 1. private

Digunakan pada semua field di setiap class sehingga data tidak bisa diakses atau diubah langsung dari luar. Perubahan hanya boleh terjadi melalui setter yang sudah dikontrol.

Contoh di Mobil.java:
```java
private String  id;
private String  nama;
private double  harga;
private boolean tersedia;
```

Contoh di DealerManager.java:
```java
private ArrayList<Mobil> daftarMobil;
private int counterIdOtomatis;

private void initDataAwal() { ... }
private String buatId() { ... }
```

Contoh di InputManager.java:
```java
private Scanner scanner;
private Helper  helper;

private static class Helper { ... }
```

### 2. public

Digunakan pada constructor, semua getter dan setter, method tampilInfo(), dan semua method CRUD di DealerManager. Artinya bisa diakses dari class manapun.

Contoh di Mobil.java:
```java
public String getId()    { return this.id; }
public void setNama(String nama) { ... }
public void tampilInfo() { ... }
```

### 3. protected

Digunakan pada method validasiHarga() di class Mobil. Method ini bisa diakses oleh class Mobil sendiri dan subclass-nya, tetapi tidak dari class lain yang tidak memiliki hubungan pewarisan.

Contoh di Mobil.java:
```java
protected boolean validasiHarga(double harga) {
    return harga >= 0;
}
```

Method ini dipanggil oleh setter setHarga() agar nilai yang disimpan selalu valid.

### 4. default (package-private)

Digunakan pada method formatHarga() di class Mobil. Tidak ada modifier yang ditulis, artinya hanya bisa diakses oleh class dalam package yang sama.

Contoh di Mobil.java:
```java
String formatHarga() {
    return String.format("Rp %,.0f", this.harga);
}
```

---

## Penerapan Encapsulation

Encapsulation diterapkan dengan menyembunyikan data di balik modifier private, lalu menyediakan akses terkontrol melalui getter dan setter.

Contoh pertama: field harga bersifat private. Perubahan hanya bisa dilakukan lewat setHarga(), yang di dalamnya memanggil validasiHarga() untuk memastikan nilai tidak negatif.

```java
public void setHarga(double harga) {
    if (validasiHarga(harga)) {
        this.harga = harga;
    }
}

protected boolean validasiHarga(double harga) {
    return harga >= 0;
}
```

Contoh kedua: field daftarMobil di DealerManager bersifat private. Kode luar tidak bisa menambah atau menghapus data secara langsung. Semua perubahan harus melalui method tambahMobil(), updateMobil(), atau hapusMobil().

Contoh ketiga: class Helper di dalam InputManager dibuat sebagai private static inner class. Detail implementasi pembacaan input tersembunyi dari class lain, yang hanya bisa menggunakan method public yang ada di InputManager.

---

## Ringkasan Access Modifier

| Modifier  | Diterapkan Pada                                                                          | Tujuan                                              |
|-----------|------------------------------------------------------------------------------------------|-----------------------------------------------------|
| private   | Semua field di Mobil, DealerManager, Main, InputManager; buatId(); initDataAwal(); class Helper | Data tidak bisa diakses langsung dari luar class |
| public    | Constructor, getter, setter, tampilInfo(), method CRUD, method input                    | Bisa diakses dari class manapun                     |
| protected | validasiHarga() di Mobil                                                                 | Bisa dipakai subclass, tidak terlihat dari luar     |
| default   | formatHarga() di Mobil                                                                   | Hanya bisa diakses dalam package yang sama          |

---

## Data Awal

Program dimulai dengan lima data mobil:

| ID        | Nama        | Tipe      | Warna  | Tahun | Harga          |
|-----------|-------------|-----------|--------|-------|----------------|
| HONDA-001 | Honda Brio  | Hatchback | Merah  | 2023  | Rp 175.000.000 |
| HONDA-002 | Honda Jazz  | Hatchback | Putih  | 2022  | Rp 270.000.000 |
| HONDA-003 | Honda City  | Sedan     | Hitam  | 2023  | Rp 355.000.000 |
| HONDA-004 | Honda Civic | Sedan     | Silver | 2024  | Rp 570.000.000 |
| HONDA-005 | Honda CR-V  | SUV       | Biru   | 2024  | Rp 620.000.000 |
