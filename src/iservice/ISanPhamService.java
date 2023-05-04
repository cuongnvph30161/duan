/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package iservice;

import java.util.List;
import domainmodel.SanPham;

/**
 *
 * @author Admin
 */
public interface ISanPhamService {

    public List<SanPham> getAll(); // loadd 

    public String insert(SanPham sanPham);

    public String update(String ma, SanPham sanPham);

    public String delete(String ma);
//    public String  getByMa(String ma);

    public String idSanPhamByTen(String tenSP);
}
