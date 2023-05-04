/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repositpry;

import domainmodel.ChiTietSP;
import irepository.IChiTietSPRepository;
import irepository.IDongSPRepository;
import irepository.IMauSacRepository;
import irepository.INSXRepository;
import irepository.ISanPhamRepository;
import java.math.BigDecimal;
import java.sql.Connection;
import java.util.ArrayList;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import utilities.DbConnector;
import viewmodel.ViewModelChiTietSP;

/**
 *
 * @author Admin
 */
public class ChiTietSPRepository implements IChiTietSPRepository {

    private static Connection connection = null;

    static {
        try {
            connection = DbConnector.getConnection();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public ArrayList<ViewModelChiTietSP> getListChiTietSP() {
        try {
            ArrayList<ViewModelChiTietSP> listViewModelChiTietSP = new ArrayList<>();
            String query = "select chitietsp.Id, SanPham.MA as 'masp' , SanPham.Ten as'TenSp',NSX.ten as'Tennsx', MauSac.ten as 'tenmausac', DongSP.Ten as'tenDongSP', ChiTietSP.NamBH, ChiTietSP.SoLuongTon, ChiTietSP.MoTa, ChiTietSP.GiaNhap, ChiTietSP.GiaBan"
                    + "                     FROM ChiTietSP JOIN SanPham ON SanPham.Id = ChiTietSP.IdSP"
                    + "                  				JOIN DongSP ON ChiTietSP.IdDongSP = DongSP.Id\n"
                    + "                 				JOIN MauSac ON ChiTietSP.IdMauSac = MauSac.Id\n"
                    + "                 				JOIN NSX ON ChiTietSP.IdNsx = NSX.Id";
            PreparedStatement ps = connection.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String id = rs.getString("id");
                String masp = rs.getString("masp");
                String tensp = rs.getString("tensp");
                String tennsx = rs.getString("tennsx");
                String tenmausac = rs.getString("tenmausac");
                String tendongsp = rs.getString("tendongsp");
                int namBh = rs.getInt("namBh");
                int soLuongTon = rs.getInt("soLuongTon");
                BigDecimal giaNhap = rs.getBigDecimal("giaNhap");
                BigDecimal giaBan = rs.getBigDecimal("giaBan");
                String moTa = rs.getString("moTa");
                ViewModelChiTietSP viewModelChiTietSP = new ViewModelChiTietSP(id, masp, tenmausac, tennsx, tenmausac, tendongsp, namBh, moTa, soLuongTon, giaNhap, giaBan);
                listViewModelChiTietSP.add(viewModelChiTietSP);
            }
            return listViewModelChiTietSP;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }

    }

    @Override
    public boolean insert(ChiTietSP chiTietSP) {
        try {
            String query = "insert into ChiTietSP(IdSP,IdNsx,IdMauSac,IdDongSP,NamBH,MoTa,SoLuongTon,GiaNhap,GiaBan) values (?,?,?,?,?,?,?,?,?)";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, chiTietSP.getIdSP());
            ps.setString(2, chiTietSP.getIdNSX());
            ps.setString(3, chiTietSP.getIdMauSac());
            ps.setString(4, chiTietSP.getIdDongSP());
            ps.setInt(5, chiTietSP.getNamBH());
            ps.setString(6, chiTietSP.getMoTa());
            ps.setInt(7, chiTietSP.getSoLuongTon());
            ps.setBigDecimal(8, chiTietSP.getGiaNhap());
            ps.setBigDecimal(9, chiTietSP.getGiaBan());
            ps.executeUpdate();
            return true;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Chua insert thanh cong");
            return false;
        }
    }

    @Override
    public boolean delete(String id) {
        try {
            String query = "delete from ChiTietSP where id = ?";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, id);
            ps.executeUpdate();
            return true;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }

    }

    @Override
    public boolean update(String id, ChiTietSP chiTietSP) {
        try {
            String query = "update ChiTietSP set IdSP =? , IdNsx=? ,IdMauSac=? ,IdDongSP=? ,NamBH=? ,MoTa=? ,SoLuongTon=? ,GiaNhap=? ,GiaBan=? where id = ?";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, chiTietSP.getIdSP());
            ps.setString(2, chiTietSP.getIdNSX());
            ps.setString(3, chiTietSP.getIdMauSac());
            ps.setString(4, chiTietSP.getIdDongSP());
            ps.setInt(5, chiTietSP.getNamBH());
            ps.setString(6, chiTietSP.getMoTa());
            ps.setInt(7, chiTietSP.getSoLuongTon());
            ps.setBigDecimal(8, chiTietSP.getGiaNhap());
            ps.setBigDecimal(9, chiTietSP.getGiaBan());
            ps.setString(10, id);
            ps.executeUpdate();
            return true;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

}
