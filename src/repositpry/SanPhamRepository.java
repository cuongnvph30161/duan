/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repositpry;

import utilities.DbConnector;
import java.util.List;
import domainmodel.SanPham;
import irepository.ISanPhamRepository;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Admin
 */
public class SanPhamRepository implements ISanPhamRepository {

    public static Connection connection = null;

    static {
        try {
            connection = DbConnector.getConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public ArrayList<SanPham> getListSanPham() {
        ArrayList<SanPham> list = new ArrayList<>();
        try {
            String query = "select * from sanpham";
            PreparedStatement ps = connection.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String id = rs.getString("id");
                String ma = rs.getString("ma");
                String ten = rs.getString("ten");
                SanPham sanPham = new SanPham(id, ma, ten);
                list.add(sanPham);
            }
            return list;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "chua select thanh cong o repository");
            return null;
        }
    }

    @Override
    public boolean insert(SanPham sanPham) {
        try {
            String query = " insert into SanPham(ma , ten)  values(?,?)";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, sanPham.getMa());
            ps.setString(2, sanPham.getTen());
            ps.executeUpdate();
            return true;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "chua insert thanh cong o repository");
            return false;
        }

    }

    @Override
    public boolean delete(String ma) {
        try {
            String query = "DELETE ChiTietSP WHERE IdSP IN ( SELECT id FROM SanPham WHERE SanPham.Ma = ?)\n"
                    + "		DELETE SanPham WHERE SanPham.ma= ?";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, ma);
            ps.setString(2, ma);

            ps.executeUpdate();
            return true;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "chua delete  thanh cong o repository");
            return false;
        }
    }

    @Override
    public boolean update(String ma, SanPham sanPham) {
        try {
            String query = " update SanPham set  ten = ? where ma =?";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, sanPham.getTen());
            ps.setString(2, ma);
            ps.executeUpdate();
            return true;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "chua update thanh cong o repository");
            return false;

        }
    }

    // tìm mã 
//    public String getByMa(String ma){
//        try {
//            String query ="select ma from SanPham where ma =?";
//            PreparedStatement ps = connection.prepareStatement(query);
//            ResultSet rs = ps.executeQuery();
//            ps.setString(1, ma);
//           
//            while (rs.next()) {                
//                return rs.getString("ma");
//            }
//        } catch (SQLException ex) {
//            JOptionPane.showMessageDialog(null,"Chua tim duoc ma");
//           
//        }
//         return null ;
//    }
    @Override
    public String getTenSanPhamById(String idSanPham) {
        try {
            String ten = null;
            String query = "select ten from SanPham where Id = ?";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, idSanPham);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                ten = rs.getString("Ten");
            }
            return ten;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    @Override
    public String idSanPhamByTen(String tenSP) {
        String id = null;
        try {
            String query = " select id from SanPham where ten = ?";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, tenSP);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                id = rs.getString("id");
            }
            return id;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Chua lay duoc id cua SanPham");
            return null;
        }
    }

    @Override
    public String getMaSPByIdSp(String idSP) {
        String ma = null;
        try {
            String query = "select ma from SanPham where id =?";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, idSP);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                ma = rs.getString("ma");
            }
            return ma;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }
    }
}
