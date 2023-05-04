/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package iservice;

import java.util.ArrayList;
import domainmodel.MauSac;

/**
 *
 * @author Admin
 */
public interface IMauSacService {
   public String insert(MauSac mauSac);
   public ArrayList<MauSac> getAll(); // loadTable
   public String delete(String ma);
   public String update(String ma , MauSac mauSac);
   public String getIdMauSacByTenMauSac(String tenMauSac);
}
