-- Membuat database dan menggunakannya
CREATE DATABASE IF NOT EXISTS toko_indah;
USE toko_indah;

-- Tabel user (tidak memiliki foreign key)
CREATE TABLE IF NOT EXISTS user (
  id INT NOT NULL AUTO_INCREMENT,
  username VARCHAR(50) DEFAULT NULL,
  nama VARCHAR(100) DEFAULT NULL,
  password VARCHAR(100) DEFAULT NULL,
  role VARCHAR(50) DEFAULT NULL,
  PRIMARY KEY (id)
);

-- Tabel pelanggan (tidak memiliki foreign key)
CREATE TABLE IF NOT EXISTS pelanggan (
  kode_pelanggan VARCHAR(20) NOT NULL,
  nama VARCHAR(100) DEFAULT NULL,
  telepon VARCHAR(20) DEFAULT NULL,
  alamat TEXT,
  PRIMARY KEY (kode_pelanggan),
  UNIQUE KEY telepon (telepon)
);

-- Tabel produk (tidak memiliki foreign key)
CREATE TABLE IF NOT EXISTS produk (
  kode_produk VARCHAR(20) NOT NULL,
  nama_produk VARCHAR(100) DEFAULT NULL,
  kategori_produk VARCHAR(50) DEFAULT NULL,
  stock INT DEFAULT NULL,
  harga_modal FLOAT DEFAULT NULL,
  harga_jual FLOAT DEFAULT NULL,
  tanggal_input DATE DEFAULT NULL,
  PRIMARY KEY (kode_produk)
);

-- Tabel transaksi (memiliki foreign key ke pelanggan)
CREATE TABLE IF NOT EXISTS transaksi (
  nomor_transaksi VARCHAR(20) NOT NULL,
  tanggal_transaksi DATE DEFAULT NULL,
  catatan_transaksi TEXT,
  total FLOAT DEFAULT NULL,
  pembayaran FLOAT DEFAULT NULL,
  kembalian FLOAT DEFAULT NULL,
  kode_pelanggan VARCHAR(20) DEFAULT NULL,
  deleted TINYINT DEFAULT 0,
  PRIMARY KEY (nomor_transaksi),
  KEY kode_pelanggan (kode_pelanggan),
  CONSTRAINT transaksi_ibfk_1 FOREIGN KEY (kode_pelanggan) REFERENCES pelanggan (kode_pelanggan)
);

-- Tabel keranjang (memiliki foreign key ke transaksi)
CREATE TABLE IF NOT EXISTS keranjang (
  id_keranjang INT NOT NULL AUTO_INCREMENT,
  nomor_transaksi VARCHAR(20) DEFAULT NULL,
  nama_produk VARCHAR(100) DEFAULT NULL,
  harga INT DEFAULT NULL,
  qty INT DEFAULT NULL,
  subtotal INT DEFAULT NULL,
  kode_produk VARCHAR(100) DEFAULT NULL,
  PRIMARY KEY (id_keranjang),
  KEY nomor_transaksi (nomor_transaksi),
  CONSTRAINT keranjang_ibfk_2 FOREIGN KEY (nomor_transaksi) REFERENCES transaksi (nomor_transaksi)
);