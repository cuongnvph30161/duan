/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import iservice.IMauSacService;
import java.util.ArrayList;
import domainmodel.MauSac;
import irepository.IMauSacRepository;
import java.util.List;
import javax.swing.JOptionPane;
import repositpry.MauSacRepository;

/**
 *
 * @author Admin
 */
public class MauSacService implements IMauSacService {

    private IMauSacRepository iMauSacRepository = new MauSacRepository();

    @Override
    public String insert(MauSac mauSac) {
        // check rỗng
        if (mauSac.getMa().trim().equals("") || mauSac.getTen().trim().equals("")) {
            return " Không được rỗng ";

        }
        // check trùng mã
        List<MauSac> mauSacs = iMauSacRepository.getListMauSac();
        for (MauSac ms : mauSacs) {
            if (ms.getMa().equals(mauSac.getMa())) {
                return "Mã màu đã tồn tại";
            }
        }

        if (iMauSacRepository.insert(mauSac) == true) {
            return "Đã thêm";
        } else {
            return "Thất bại";
        }
    }

    @Override
    public ArrayList<MauSac> getAll() {
        return iMauSacRepository.getListMauSac();
    }

    @Override
    public String delete(String ma) {
        if (iMauSacRepository.delete(ma)) {
            return "Đã xoá";
        } else {
            return "Xoá thất bại";
        }
    }

    @Override
    public String update(String ma, MauSac mauSac) {
        // check trùng 
        if (mauSac.getMa().trim().equals("") || mauSac.getTen().trim().equals("")) {
            return " Không được rỗng ";
        }

        // mã dạng unique không thay đổi được 
        if (!ma.equals(mauSac.getMa())) {
            return "Mã màu sắc không được thay đổi ";
        }
        if (iMauSacRepository.update(ma, mauSac)) {
            return "Update thành công";
        } else {
            return "Update thất bại";
        }
    }

    @Override
    public String getIdMauSacByTenMauSac(String tenMauSac) {
        return iMauSacRepository.getIdMauSacByTenMauSac(tenMauSac);
    }

  
}
