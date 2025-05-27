CREATE TABLE USER (
  id INT PRIMARY KEY,
  username VARCHAR(50),
  nama VARCHAR(100),
  password VARCHAR(100),
  role VARCHAR(50)
);

CREATE TABLE pelanggan (
  kode_pelanggan VARCHAR(20) PRIMARY KEY,
  nama VARCHAR(100),
  telepon VARCHAR(20),
  alamat TEXT
);

CREATE TABLE produk (
  kode_produk VARCHAR(20) PRIMARY KEY,
  nama_produk VARCHAR(100),
  kategori_produk VARCHAR(50),
  stock INT,
  harga_modal FLOAT,
  harga_jual FLOAT,
  tanggal_input DATE
);

CREATE TABLE transaksi (
  nomor_transaksi VARCHAR(20) PRIMARY KEY,
  tanggal_transaksi DATE,
  catatan_transaksi TEXT,
  total INT,
  pembayaran INT,
  kembalian INT,
  kode_pelanggan VARCHAR(20),
  FOREIGN KEY (kode_pelanggan) REFERENCES pelanggan(kode_pelanggan)
);

CREATE TABLE keranjang (
  id_keranjang INT AUTO_INCREMENT PRIMARY KEY,
  nomor_transaksi VARCHAR(20),
  kode_produk VARCHAR(20),
  nama_produk VARCHAR(100),
  harga INT,
  qty INT,
  subtotal INT,
  FOREIGN KEY (kode_produk) REFERENCES produk(kode_produk),
  FOREIGN KEY (nomor_transaksi) REFERENCES transaksi(nomor_transaksi)
);
