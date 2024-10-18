# API Documentation for Stock Management

## Introduction

Budi membutuhkan bantuan untuk membuat API yang dapat mengelola data stok barang. API ini akan mencakup berbagai fungsi untuk membuat, membaca, memperbarui, dan menghapus data stok.

## API Endpoints

API yang perlu dibuat terdiri dari lima endpoint utama:

1. **Create Stock**
   - **Endpoint**: `/stock/addItem`
   - **Method**: `POST`
   - **Description**: Menambahkan stok barang baru.
   - **Request Body**: Menggunakan DTO (Data Transfer Object).

2. **List Stock**
   - **Endpoint**: `/stock/getAll`
   - **Method**: `POST`
   - **Description**: Mengambil daftar semua stok barang.

3. **Detail Stock**
   - **Endpoint**: `/stock/getItem`
   - **Method**: `POST`
   - **Description**: Mengambil detail informasi tentang stok barang berdasarkan ID.

4. **Update Stock**
   - **Endpoint**: `/stock/stocks/{id}`
   - **Method**: `POST`
   - **Description**: Memperbarui informasi stok barang yang sudah ada.
   - **Request Body**: Menggunakan DTO.

5. **Delete Stock**
   - **Endpoint**: `/stock/stocks/{id}`
   - **Method**: `POST`
   - **Description**: Menghapus stok barang berdasarkan ID.

## Data Structure

Data yang perlu disimpan dalam API terdiri dari:

- **IdBarang** (sequence)
- **NamaBarang**
- **JumlahStokBarang**
- **NomorSeriBarang**
- **AdditionalInfo** (type JSONB)
- **Gambar Barang**
- **Created At**
- **Created By**
- **Updated At**
- **Updated By**

### Additional Info

- Field `additional_info` harus menyimpan data dengan struktur dinamis dalam format JSON.

## Validasi

- **MIME Type**: Hanya file gambar dengan format JPG dan PNG yang diizinkan.
- Gambar dapat disimpan sebagai blob atau hanya menyimpan path di database.

## Database

- Menggunakan library **LOMBOK** untuk mempermudah pengelolaan data.
- Implementasikan pembuatan database dengan menggunakan native query atau query builder dari JPA.

## Logging

- Setiap request dan response harus dicatat ke dalam sebuah file log.
- Tampilkan log di stdout menggunakan library **log4j**.

## Installation

1. Clone repository ini.
2. Pastikan Anda memiliki JDK dan Maven terinstal (maven 399 & java 17)
3. Konfigurasikan terlebih dahulu database anda pada profile properties yang ada
4. Jalankan perintah berikut untuk menginstal dependensi:
   ```bash
   mvn clean install
   ```
5. Jalankan aplikasi dengan perintah:
    ```bash
   mvn spring-boot:run
   ```
# Usage
Setelah aplikasi berjalan, saya sudah menyediakan postman pada link berikut:
https://drive.google.com/file/d/1BNtW7LmAjDPhJi3MVJK566GVh_i7jZSU/view
