/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import iservice.ISanPhamService;
import java.util.List;
import domainmodel.SanPham;
import irepository.ISanPhamRepository;
import javax.swing.JOptionPane;
import repositpry.SanPhamRepository;

/**
 *
 * @author Admin
 */
public class SanPhamService implements ISanPhamService {

    private ISanPhamRepository iSanPhamRepository = new SanPhamRepository();

    @Override
    public List<SanPham> getAll() {
        return iSanPhamRepository.getListSanPham();
    }

    @Override
    public String insert(SanPham sanPham) {
        if (sanPham.getMa().trim().equals("") || sanPham.getTen().trim().equals("")) {
            return "Mã và tên không được rỗng";
        }

        // kiểm tra mã trùng nhau không 
        List<SanPham> sanPhams = iSanPhamRepository.getListSanPham();
        for (SanPham sp : sanPhams) {
            if (sp.getMa().equals(sanPham.getMa())) {
                return "Mã sản phẩm đã tồn tại";

            }
        }

        if (iSanPhamRepository.insert(sanPham)) {
            return "Thêm thành công";
        } else {
            return "Thêm thất bại";
        }

    }

    @Override
    public String update(String ma, SanPham sanPham) {

        if (sanPham.getMa().trim().equals("") || sanPham.getTen().trim().equals("")) {
            return "Mã và tên không được rỗng";
        }

        if (!ma.equals(sanPham.getMa())) {
            return "Mã sản phẩm không được thay đổi";
        }

        if (iSanPhamRepository.update(ma, sanPham)) {
            return "Update thành công";
        } else {
            return "Update thất bại";
        }

    }

    @Override
    public String delete(String ma) {
        if (iSanPhamRepository.delete(ma)) {
            return "Xoá thành công";
        } else {
            return "Xoá thất bại";
        }

    }

//    @Override
//    public String getByMa(String ma) {
//      return   sanPhamRepository.getByMa(ma);
//    }

    @Override
    public String idSanPhamByTen(String tenSP) {
        return iSanPhamRepository.idSanPhamByTen(tenSP);
    }
}
