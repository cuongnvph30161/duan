/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package iservice;

import java.util.List;
import domainmodel.NSX;

/**
 *
 * @author Admin
 */
public interface INSXService {
    public List<NSX> getAll();
    public String insert(NSX nsx);
    public String delete(String ma);
    public String update(String ma ,NSX nsx);
    public String getIdNSXByTen(String ten );
}
