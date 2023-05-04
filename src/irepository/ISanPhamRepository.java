/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package irepository;

import domainmodel.SanPham;
import java.util.ArrayList;

/**
 *
 * @author Admin
 */
public interface ISanPhamRepository {
    public ArrayList<SanPham> getListSanPham();
    public boolean insert(SanPham sanPham);
    public boolean delete(String ma);
    public boolean update(String ma, SanPham sanPham);
    
    public String getTenSanPhamById(String idSP);
    public String idSanPhamByTen(String tenSP);
    public String getMaSPByIdSp(String idSP);
}
