/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import iservice.INSXService;
import java.util.List;
import domainmodel.NSX;
import irepository.INSXRepository;
import javax.swing.JOptionPane;
import repositpry.NSXRepository;

/**
 *
 * @author Admin
 */
public class NSXService implements INSXService {

    private INSXRepository iNSXRepository = new NSXRepository();

    @Override
    public List<NSX> getAll() {
        return iNSXRepository.getListNSX();
    }

    @Override
    public String insert(NSX nsx) {

        // check rỗng 
        if (nsx.getMa().trim().equals("") || nsx.getTen().trim().equals("")) {
            return "Không được rỗng ";
        }

        // check trùng mã 
        List<NSX> nsxs = iNSXRepository.getListNSX();
        for (NSX nsx1 : nsxs) {
            if (nsx1.getMa().equals(nsx.getMa())) {
                return "Mã NSX đã tồn tại";
            }
        }
        if (iNSXRepository.insert(nsx)) {
            return "Đã thêm thành công";
        } else {
            return "Thêm thất bại";
        }

    }

    @Override
    public String delete(String ma) {
        if (iNSXRepository.delete(ma)) {
            return "Đã xoá";
        } else {
            return "Xoá thất bại";
        }
    }

    @Override
    public String update(String ma, NSX nsx) {

        // check rỗng 
        if (nsx.getMa().trim().equals("") || nsx.getTen().trim().equals("")) {
            return "Không được rỗng ";
        }
        
        if (!ma.equals(nsx.getMa())) {
            return "Mã NSX không được thay đổi";
        }
        if(iNSXRepository.update(ma, nsx)){
            return "Update thành công";
        }else{
            return "Update thất bại";
        }
        
    }

    @Override
    public String getIdNSXByTen(String ten) {
        return iNSXRepository.getIdNSXByTen(ten);
    }

}
