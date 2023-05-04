/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package iservice;

import java.util.List;
import domainmodel.ChucVu;

/**
 *
 * @author Admin
 */
public interface IChucVuService {
    public List<ChucVu> getAll();
    public String insert(ChucVu chucVu);
    public String delete(String ma);
    public String update(String ma , ChucVu chucVu);
}
