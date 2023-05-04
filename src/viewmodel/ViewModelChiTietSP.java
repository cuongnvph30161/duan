/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package viewmodel;

import domainmodel.MauSac;
import java.math.BigDecimal;

/**
 *
 * @author Admin
 */
public class ViewModelChiTietSP {
    private String id ;
    private String maSP ;
    private String tenSanPham;
    private String tenNSX;
    private String tenMauSac;
    private String tenDongSP;
    
    private Integer namBH;
    private String moTa;
    private Integer soLuongTon;
    private BigDecimal giaNhap;
    private BigDecimal giaBan;

    public ViewModelChiTietSP(String id, String maSP, String tenSanPham, String tenNSX, String tenMauSac, String tenDongSP, Integer namBH, String moTa, Integer soLuongTon, BigDecimal giaNhap, BigDecimal giaBan) {
        this.id = id;
        this.maSP = maSP;
        this.tenSanPham = tenSanPham;
        this.tenNSX = tenNSX;
        this.tenMauSac = tenMauSac;
        this.tenDongSP = tenDongSP;
        this.namBH = namBH;
        this.moTa = moTa;
        this.soLuongTon = soLuongTon;
        this.giaNhap = giaNhap;
        this.giaBan = giaBan;
    }

    public ViewModelChiTietSP() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMaSP() {
        return maSP;
    }

    public void setMaSP(String maSP) {
        this.maSP = maSP;
    }

    public String getTenSanPham() {
        return tenSanPham;
    }

    public void setTenSanPham(String tenSanPham) {
        this.tenSanPham = tenSanPham;
    }

    public String getTenNSX() {
        return tenNSX;
    }

    public void setTenNSX(String tenNSX) {
        this.tenNSX = tenNSX;
    }

    public String getTenMauSac() {
        return tenMauSac;
    }

    public void setTenMauSac(String tenMauSac) {
        this.tenMauSac = tenMauSac;
    }

    public String getTenDongSP() {
        return tenDongSP;
    }

    public void setTenDongSP(String tenDongSP) {
        this.tenDongSP = tenDongSP;
    }

    public Integer getNamBH() {
        return namBH;
    }

    public void setNamBH(Integer namBH) {
        this.namBH = namBH;
    }

    public String getMoTa() {
        return moTa;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }

    public Integer getSoLuongTon() {
        return soLuongTon;
    }

    public void setSoLuongTon(Integer soLuongTon) {
        this.soLuongTon = soLuongTon;
    }

    public BigDecimal getGiaNhap() {
        return giaNhap;
    }

    public void setGiaNhap(BigDecimal giaNhap) {
        this.giaNhap = giaNhap;
    }

    public BigDecimal getGiaBan() {
        return giaBan;
    }

    public void setGiaBan(BigDecimal giaBan) {
        this.giaBan = giaBan;
    }

    @Override
    public String toString() {
        return "ViewModelChiTietSP{" + "id=" + id + ", maSP=" + maSP + ", tenSanPham=" + tenSanPham + ", tenNSX=" + tenNSX + ", tenMauSac=" + tenMauSac + ", tenDongSP=" + tenDongSP + ", namBH=" + namBH + ", moTa=" + moTa + ", soLuongTon=" + soLuongTon + ", giaNhap=" + giaNhap + ", giaBan=" + giaBan + '}';
    }

    


    

}
