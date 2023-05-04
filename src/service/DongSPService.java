/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import iservice.IDongSPService;
import java.util.List;
import domainmodel.DongSP;
import irepository.IDongSPRepository;
import javax.swing.JOptionPane;
import repositpry.DongSPRepository;

/**
 *
 * @author Admin
 */
public class DongSPService implements IDongSPService {

    private IDongSPRepository iDongSPRepository = new DongSPRepository();

    @Override
    public List<DongSP> getAll() {
        return iDongSPRepository.getListDongSP();
    }

    @Override
    public String insert(DongSP dongSP) {
        
        if (dongSP.getMa().trim().equals("") || dongSP.getTen().trim().equals("")) {
            return "Mã và tên không được rỗng";
        }

        List<DongSP> dongSPs = iDongSPRepository.getListDongSP();
        for (DongSP dongSP1 : dongSPs) {
            if (dongSP1.getMa().equals(dongSP.getMa())) {
                return "Mã DongSP không được trùng";

            }
        }
        if (iDongSPRepository.insert(dongSP)) {
            return "Đã thêm thành công";
        } else {
            return "Thêm thất bại";
        }
    }

    @Override
    public String update(String ma, DongSP dongSP) {
        if (dongSP.getMa().trim().equals("") || dongSP.getTen().trim().equals("")) {
            return "Mã và tên không được rỗng";
        }

        if (!ma.equals(dongSP.getMa())) {
            return "Mã DongSP không được thay đổi";
        }
        if (iDongSPRepository.update(ma, dongSP)) {
            return "Update thành công";
        } else {
            return "Update thất bại";
        }

    }

    @Override
    public String delete(String ma) {
        if (iDongSPRepository.delete(ma)) {
            return "Đã xoá";
        } else {
            return "Xoá thất bại";
        }
    }

    @Override
    public String getIdDongSPByTen(String tenDongSP) {
        return iDongSPRepository.getIdDongSPByTen(tenDongSP);
    }

}
