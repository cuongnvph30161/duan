/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package iservice;

import java.util.List;
import domainmodel.DongSP;

/**
 *
 * @author Admin
 */
public interface IDongSPService {
    public List<DongSP> getAll();
    public String insert(DongSP dongSP);
    public String update(String ma , DongSP dongSP);
    public String delete(String ma);
    public String  getIdDongSPByTen(String tenDongSP);
   
}
