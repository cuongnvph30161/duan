﻿CREATE DATABASE FINALASS_FPOLYSHOP_FA22_SOF205__SOF2041
USE FINALASS_FPOLYSHOP_FA22_SOF205__SOF2041
GO
-- ChucVu
CREATE TABLE ChucVu(
Id UNIQUEIDENTIFIER PRIMARY KEY DEFAULT NEWID(),
Ma VARCHAR(20) UNIQUE,
Ten NVARCHAR(50) DEFAULT NULL
)
GO
-- CuaHang
CREATE TABLE CuaHang(
Id UNIQUEIDENTIFIER PRIMARY KEY DEFAULT NEWID(),
Ma VARCHAR(20) UNIQUE,
Ten NVARCHAR(50) DEFAULT NULL,
DiaChi NVARCHAR(100) DEFAULT NULL,
ThanhPho NVARCHAR(50) DEFAULT NULL,
QuocGia NVARCHAR(50) DEFAULT NULL
)
GO
-- NhanVien
CREATE TABLE NhanVien(
Id UNIQUEIDENTIFIER PRIMARY KEY DEFAULT NEWID(),
Ma VARCHAR(20) UNIQUE,
Ten NVARCHAR(30) DEFAULT NULL,
TenDem NVARCHAR(30) DEFAULT NULL,
Ho NVARCHAR(30) DEFAULT NULL,
GioiTinh NVARCHAR(10) DEFAULT NULL,
NgaySinh DATE DEFAULT NULL,
DiaChi NVARCHAR(100) DEFAULT NULL,
Sdt VARCHAR(30) DEFAULT NULL,
MatKhau VARCHAR(MAX) DEFAULT NULL,
IdCH UNIQUEIDENTIFIER,
IdCV UNIQUEIDENTIFIER,
IdGuiBC UNIQUEIDENTIFIER,
TrangThai INT DEFAULT 0
)
GO
-- KhachHang
CREATE TABLE KhachHang(
Id UNIQUEIDENTIFIER PRIMARY KEY DEFAULT NEWID(),
Ma VARCHAR(20) UNIQUE,
Ten NVARCHAR(30),
TenDem NVARCHAR(30) DEFAULT NULL,
Ho NVARCHAR(30) DEFAULT NULL,
NgaySinh DATE DEFAULT NULL,
Sdt VARCHAR(30) DEFAULT NULL,
DiaChi NVARCHAR(100) DEFAULT NULL,
ThanhPho NVARCHAR(50) DEFAULT NULL,
QuocGia NVARCHAR(50) DEFAULT NULL,
MatKhau VARCHAR(MAX) DEFAULT NULL
)
GO
--HoaDon
CREATE TABLE HoaDon(
Id UNIQUEIDENTIFIER PRIMARY KEY DEFAULT NEWID(),
IdKH UNIQUEIDENTIFIER,
IdNV UNIQUEIDENTIFIER,
Ma VARCHAR(20) UNIQUE,
NgayTao DATE DEFAULT NULL,
NgayThanhToan DATE DEFAULT NULL,
NgayShip DATE DEFAULT NULL,
NgayNhan DATE DEFAULT NULL,
TinhTrang INT DEFAULT 0,
TenNguoiNhan NVARCHAR(50) DEFAULT NULL,
DiaChi NVARCHAR(100) DEFAULT NULL,
Sdt VARCHAR(30) DEFAULT NULL,
)






GO
CREATE TABLE GioHang(
Id UNIQUEIDENTIFIER PRIMARY KEY DEFAULT NEWID(),
IdKH UNIQUEIDENTIFIER,
IdNV UNIQUEIDENTIFIER,
Ma VARCHAR(20) UNIQUE,
NgayTao DATE DEFAULT NULL,
NgayThanhToan DATE DEFAULT NULL,
TenNguoiNhan NVARCHAR(50) DEFAULT NULL,
DiaChi NVARCHAR(100) DEFAULT NULL,
Sdt VARCHAR(30) DEFAULT NULL,
TinhTrang INT DEFAULT 0
)
GO
-- SanPham


CREATE TABLE SanPham(
Id UNIQUEIDENTIFIER PRIMARY KEY DEFAULT NEWID(),
Ma VARCHAR(20) UNIQUE,
Ten NVARCHAR(30)
)
GO
select * from sanpham

-- NSX
CREATE TABLE NSX(
Id UNIQUEIDENTIFIER PRIMARY KEY DEFAULT NEWID(),
Ma VARCHAR(20) UNIQUE,
Ten NVARCHAR(30)
)
GO

select * from NSX
-- MauSac

CREATE TABLE MauSac(
Id UNIQUEIDENTIFIER PRIMARY KEY DEFAULT NEWID(),
Ma VARCHAR(20) UNIQUE,
Ten NVARCHAR(30)
)
GO
select * from mausac




-- DongSP
CREATE TABLE DongSP(
Id UNIQUEIDENTIFIER PRIMARY KEY DEFAULT NEWID(),
Ma VARCHAR(20) UNIQUE,
Ten NVARCHAR(30)
)
GO
select * from ChiTietSP

GO-- ChiTietSP
CREATE TABLE ChiTietSP(
Id UNIQUEIDENTIFIER PRIMARY KEY DEFAULT NEWID(),
IdSP UNIQUEIDENTIFIER,
IdNsx UNIQUEIDENTIFIER,
IdMauSac UNIQUEIDENTIFIER,
IdDongSP UNIQUEIDENTIFIER,
NamBH INT DEFAULT NULL,
MoTa NVARCHAR(50) DEFAULT NULL,
SoLuongTon INT,
GiaNhap DECIMAL(20,0) DEFAULT 0,-- làm tròn số gần nhất 
GiaBan DECIMAL(20,0) DEFAULT 0,
)

GO
-- HoaDonChiTiet
CREATE TABLE HoaDonChiTiet(
IdHoaDon UNIQUEIDENTIFIER,
IdChiTietSP UNIQUEIDENTIFIER,
SoLuong INT,
DonGia DECIMAL(20,0) DEFAULT 0,
CONSTRAINT PK_HoaDonCT PRIMARY KEY (IdHoaDon,IdChiTietSP),
CONSTRAINT FK1 FOREIGN KEY(IdHoaDon) REFERENCES HoaDon(Id),
CONSTRAINT FK2 FOREIGN KEY(IdChiTietSP) REFERENCES ChiTietSP(Id),
)

CREATE TABLE GioHangChiTiet(
IdGioHang UNIQUEIDENTIFIER,
IdChiTietSP UNIQUEIDENTIFIER,
SoLuong INT,
DonGia DECIMAL(20,0) DEFAULT 0,
DonGiaKhiGiam DECIMAL(20,0) DEFAULT 0,
CONSTRAINT PK_GioHangCT PRIMARY KEY (IdGioHang,IdChiTietSP),
CONSTRAINT FK1_IdGioHang FOREIGN KEY(IdGioHang) REFERENCES GioHang(Id),
CONSTRAINT FK2_IdChiTietSP FOREIGN KEY(IdChiTietSP) REFERENCES ChiTietSP(Id),
)
GO
--TẠO QUAN HỆ GIỮA CÁC BẢNG
--NhanVien - CuaHang
ALTER TABLE NhanVien ADD FOREIGN KEY (IdCH) REFERENCES CuaHang(Id)
--NhanVien - ChucVu
ALTER TABLE NhanVien ADD FOREIGN KEY (IdCV) REFERENCES ChucVu(Id)
--NhanVien - GuiBaoCao
ALTER TABLE NhanVien ADD FOREIGN KEY (IdGuiBC) REFERENCES NhanVien(Id)
-- HoaDon - KhachHang
ALTER TABLE HoaDon ADD FOREIGN KEY (IdKH) REFERENCES KhachHang(Id)
-- GioHang - KhachHang
ALTER TABLE GioHang ADD FOREIGN KEY (IdKH) REFERENCES KhachHang(Id)
-- HoaDon - NhanVien
ALTER TABLE HoaDon ADD FOREIGN KEY (IdNV) REFERENCES NhanVien(Id)
-- ChiTietSP - SanPham
ALTER TABLE ChiTietSP ADD FOREIGN KEY(IdSP) REFERENCES SanPham(Id)
-- ChiTietSP - NSX
ALTER TABLE ChiTietSP ADD FOREIGN KEY(IdNsx) REFERENCES Nsx(Id)
-- ChiTietSP - MauSac
ALTER TABLE ChiTietSP ADD FOREIGN KEY(IdMauSac) REFERENCES MauSac(Id)
-- ChiTietSP - DongSP
ALTER TABLE ChiTietSP ADD FOREIGN KEY(IdDongSP) REFERENCES DongSP(Id)

select SanPham.Ma ,SanPham.Ten , NSX.Ten ,MauSac.Ten ,  DongSP.Ten  , NamBH,MoTa,SoLuongTon,GiaNhap,GiaBan
from ChiTietSP 
join SanPham on ChiTietSP.IdSP = SanPham.Id
join DongSP on ChiTietSP.IdDongSP = DongSP.Id
join NSX on ChiTietSP.IdNsx = NSX.Id
join MauSac on ChiTietSP.IdMauSac = MauSac.Id

-- CƯỜNG TEST GIT 