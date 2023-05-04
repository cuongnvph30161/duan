/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import iservice.IChucVuService;
import java.util.List;
import domainmodel.ChucVu;
import irepository.IChucVuRepository;
import javax.swing.JOptionPane;
import repositpry.ChucVuRepository;

/**
 *
 * @author Admin
 */
public class ChucVuService implements IChucVuService {

    private IChucVuRepository iChucVuRepository = new ChucVuRepository();

    @Override
    public List<ChucVu> getAll() {
        return iChucVuRepository.getListChucVu();
    }

    @Override
    public String insert(ChucVu chucVu) {
        if (chucVu.getMa().trim().equals("") || chucVu.getTen().trim().equals("")) {
            return "Mã và tên không được rỗng";

        }

        List<ChucVu> chucVus = iChucVuRepository.getListChucVu();
        for (ChucVu chucVu1 : chucVus) {
            if (chucVu1.getMa().equals(chucVu.getMa())) {
                return "Mã ChucVu không được trùng";
            }
        }
        if (iChucVuRepository.insert(chucVu)) {
            return "Đã thêm";
        } else {
            return "Thêm thất bại";
        }
    }

    @Override
    public String delete(String ma) {
        if (iChucVuRepository.delete(ma)) {
            return "Đã xoá";
        } else {
            return "Xoá thất bại";
        }

    }

    @Override
    public String update(String ma, ChucVu chucVu) {
        if (chucVu.getMa().trim().equals("") || chucVu.getTen().trim().equals("")) {
            return "Mã và tên không được rỗng";
        }
        if (!ma.equals(chucVu.getMa())) {
            return "Mã ChucVu không được thay đổi ";
        }
        if (iChucVuRepository.update(ma, chucVu)) {
            return "Update thành công";
        } else {
            return "Update thất bại";
        }

    }

}
