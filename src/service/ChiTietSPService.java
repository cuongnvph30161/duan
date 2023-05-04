/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import domainmodel.ChiTietSP;
import domainmodel.MauSac;
import irepository.IChiTietSPRepository;
import irepository.IDongSPRepository;
import irepository.IMauSacRepository;
import irepository.INSXRepository;
import irepository.ISanPhamRepository;
import java.util.ArrayList;
import repositpry.ChiTietSPRepository;
import repositpry.DongSPRepository;
import repositpry.MauSacRepository;
import repositpry.NSXRepository;
import repositpry.SanPhamRepository;
import viewmodel.ViewModelChiTietSP;
import iservice.IChiTietSPService;
import java.math.BigDecimal;
import javax.swing.JOptionPane;

/**
 *
 * @author Admin
 */
public class ChiTietSPService implements IChiTietSPService {

    private IChiTietSPRepository iChiTietSPRepository = new ChiTietSPRepository();
    private IMauSacRepository iMauSacRepository = new MauSacRepository();
    private ISanPhamRepository iSanPhamRepository = new SanPhamRepository();
    private INSXRepository iNSXRepository = new NSXRepository();
    private IDongSPRepository iDongSPRepository = new DongSPRepository();

    @Override
    public ArrayList<ViewModelChiTietSP> getListChiTietSPViewModel() {
    return iChiTietSPRepository.getListChiTietSP();
    }

    @Override
    public String insert(ChiTietSP chiTietSP) {
        if (chiTietSP.getIdMauSac().trim().equals("") || chiTietSP.getIdDongSP().trim().equals("") || chiTietSP.getIdNSX().trim().equals("") || chiTietSP.getIdSP().trim().equals("") || chiTietSP.getMoTa().trim().equals("")) {
            return "Không được rỗng";
        }

        if (iChiTietSPRepository.insert(chiTietSP)) {
            return "Thêm thành công";
        } else {
            return "Thêm thất bại";
        }
    }

    @Override
    public boolean delete(String id) {
       return iChiTietSPRepository.delete(id);
    }

 

    @Override
    public String update(String id, ChiTietSP chiTietSP) {
        
        if(iChiTietSPRepository.update(id, chiTietSP)){
            return "Đã update thành công";
        }else{
            return "Update thất bại";
        }
                
    }

}
