/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package irepository;

import domainmodel.ChiTietSP;
import java.util.ArrayList;
import viewmodel.ViewModelChiTietSP;

/**
 *
 * @author Admin
 */
public interface IChiTietSPRepository {
    public ArrayList<ViewModelChiTietSP> getListChiTietSP();
    public boolean insert(ChiTietSP chiTietSP);
    public boolean delete(String id);
    public boolean update(String id ,ChiTietSP chiTietSP);
}
