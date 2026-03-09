Nama     : Aulia Nur Rachman
NIM      : 2409106069
Kelas    : B1

---

## Deskripsi Program

Program ini adalah aplikasi berbasis teks (console) untuk mengelola data mobil pada sebuah dealer Honda. Data disimpan sementara di dalam ArrayList selama program berjalan. Pengguna dapat melakukan operasi tambah, lihat, ubah, dan hapus data melalui menu yang berjalan secara berulang hingga pengguna memilih keluar.

---


## Struktur File

```
src/
  Mobil.java           Class yang merepresentasikan data satu unit mobil
  DealerManager.java   Class yang mengelola kumpulan data mobil (ArrayList)
  Main.java            Class utama berisi menu dan alur program
```

---

## Penjelasan Setiap Class

**Mobil.java**

Class ini adalah blueprint dari sebuah objek mobil. Setiap objek Mobil memiliki tujuh properti yaitu id, nama, tipe, warna, tahun, harga, dan tersedia. Class ini memiliki tiga constructor: satu tanpa argumen yang mengisi nilai default, satu dengan enam argumen, dan satu dengan tujuh argumen yang menggunakan constructor chaining lewat kata kunci this(). Kata kunci this juga dipakai di seluruh constructor dan setter untuk membedakan properti class dari parameter yang namanya sama.

**DealerManager.java**

Class ini mengelola ArrayList bertipe Mobil. Di dalamnya terdapat method untuk setiap operasi CRUD: tambahMobil() untuk menambah data, getDaftarMobil() dan cariMobilById() untuk membaca data, updateMobil() untuk mengubah data, dan hapusMobil() untuk menghapus data. Saat pertama kali dibuat, constructor DealerManager langsung mengisi lima data awal sebagai contoh.

**Main.java**

Class ini adalah titik masuk program. Menggunakan sintaks JDK 25 yaitu void main() sebagai instance method, bukan public static void main(String[] args). Program berjalan dalam loop while yang terus menampilkan menu sampai pengguna memilih 0 untuk keluar. Setiap menu ditangani oleh method tersendiri. Terdapat juga method-method helper untuk membaca input dari pengguna dengan validasi, sehingga program tidak crash jika pengguna memasukkan input yang salah.

---

## Menu Program

```
1. Tambah Data Mobil
2. Lihat Semua Mobil
3. Ubah Data Mobil
4. Hapus Data Mobil
0. Keluar
```

---


## Data Awal

Program dimulai dengan lima data mobil yang sudah tersedia:

| ID        | Nama        | Tipe      | Warna  | Tahun | Harga           |
|-----------|-------------|-----------|--------|-------|-----------------|
| HONDA-001 | Honda Brio  | Hatchback | Merah  | 2023  | Rp 175.000.000  |
| HONDA-002 | Honda Jazz  | Hatchback | Putih  | 2022  | Rp 270.000.000  |
| HONDA-003 | Honda City  | Sedan     | Hitam  | 2023  | Rp 355.000.000  |
| HONDA-004 | Honda Civic | Sedan     | Silver | 2024  | Rp 570.000.000  |
| HONDA-005 | Honda CR-V  | SUV       | Biru   | 2024  | Rp 620.000.000  |
