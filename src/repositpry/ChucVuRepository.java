/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repositpry;

import utilities.DbConnector;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import domainmodel.ChucVu;
import irepository.IChucVuRepository;

/**
 *
 * @author Admin
 */
public class ChucVuRepository implements IChucVuRepository {

    private static Connection connection = null;

    static {
        try {
            connection = DbConnector.getConnection();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public ArrayList<ChucVu> getListChucVu() {
        try {
            ArrayList<ChucVu> list = new ArrayList<>();
            String query = "select * from ChucVu";
            PreparedStatement ps = connection.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String id = rs.getString("id");
                String ma = rs.getString("ma");
                String ten = rs.getString("ten");
                ChucVu chucVu = new ChucVu(id, ma, ten);
                list.add(chucVu);
            }
            return list;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Chưa select thành công ở repository");
            return null;
        }
    }

    @Override
    // insert 
    public boolean insert(ChucVu chucVu) {
        try {
            String query = "insert into ChucVu (ma , ten) values(?,?)";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, chucVu.getMa());
            ps.setString(2, chucVu.getTen());
            ps.executeUpdate();
            return true;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Chưa insert thành công ở repository");
            return false;
        }
    }

    @Override
    // delete 
    public boolean delete(String ma) {
        try {
            String query = "delete from ChucVu where ma =?";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, ma);
            ps.executeUpdate();
            return true;

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Chưa delete thành công ở repository");
            return false;

        }

    }

    @Override
    // update 
    public boolean update(String ma, ChucVu chucVu) {
        try {
            String query = "update ChucVu set ten =? where ma =?";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, chucVu.getTen());
            ps.setString(2, ma);
            ps.executeUpdate();
            return true;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "chua update duoc o repository");
            return false;
        }

    }

}
