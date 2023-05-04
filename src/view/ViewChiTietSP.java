/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package view;

import domainmodel.ChiTietSP;
import domainmodel.DongSP;
import domainmodel.MauSac;
import domainmodel.NSX;
import domainmodel.SanPham;
import irepository.IChiTietSPRepository;
import iservice.IChiTietSPService;
import iservice.IChucVuService;
import iservice.IDongSPService;
import iservice.IMauSacService;
import iservice.INSXService;
import iservice.ISanPhamService;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import repositpry.ChiTietSPRepository;
import service.ChiTietSPService;
import service.ChucVuService;
import service.DongSPService;
import service.MauSacService;
import service.NSXService;
import service.SanPhamService;
import viewmodel.ViewModelChiTietSP;

/**
 *
 * @author Admin
 */
public class ViewChiTietSP extends javax.swing.JFrame {

    private INSXService iNSXService = new NSXService();
    private ISanPhamService iSanPhamService = new SanPhamService();
    private IDongSPService iDongSPService = new DongSPService();
    private IMauSacService iMauSacService = new MauSacService();
    private IChiTietSPService iChiTietSPService = new ChiTietSPService();
    private IChiTietSPRepository iChiTietSPRepository = new ChiTietSPRepository();

    /**
     * Creates new form ViewChiTietSP
     */
    public ViewChiTietSP() {
        initComponents();
        loadComBoBoxByTenMauSac();
        loadComBoBoxByTenSanPham();
        loadComBoBoxByTenDongSP();
        loadComBoBoxTenNSX();
        loadTable(iChiTietSPService.getListChiTietSPViewModel());
    }
    // loadTable

    public void loadTable(ArrayList<ViewModelChiTietSP> list) {
        DefaultTableModel defaultTableModel = (DefaultTableModel) tblChiTietSPForm.getModel();
        defaultTableModel.setRowCount(0);
        for (ViewModelChiTietSP viewModelChiTietSP : list) {
            defaultTableModel.addRow(new Object[]{
                viewModelChiTietSP.getTenSanPham(), viewModelChiTietSP.getTenNSX(), viewModelChiTietSP.getTenMauSac(), viewModelChiTietSP.getTenDongSP(), viewModelChiTietSP.getNamBH(), viewModelChiTietSP.getMoTa(), viewModelChiTietSP.getSoLuongTon(), viewModelChiTietSP.getGiaNhap(), viewModelChiTietSP.getGiaBan()
            });
        }
    }

    public ChiTietSP getData() {
        ChiTietSP chiTietSP = new ChiTietSP();

        String namBHString = txtNamBH.getText();
        String moTaString = txtMoTa.getText();
        String soLuongTonString = txtSoLuongTon.getText();
        String giaNhapString = txtGiaNhap.getText();
        String giaBanString = txtGiaBan.getText();

        int namBHInt = 0;
        if (!namBHString.isEmpty()) {
            try {
                namBHInt = Integer.parseInt(namBHString.trim());
                chiTietSP.setNamBH(namBHInt);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Nam BH sai định dạng");
                return null;
            }
        }

        BigDecimal giaNhapBigDecimal = BigDecimal.ZERO;
        if (!giaNhapString.isEmpty()) {  // nếu không rỗng thì vào try catch 
            try {
                giaNhapBigDecimal = new BigDecimal(giaNhapString.trim()); // loại bỏ khoảng trắng  và ép price thành kiểu Bigdecimal

                chiTietSP.setGiaNhap(giaNhapBigDecimal);
                System.out.println(chiTietSP.getGiaNhap());
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Sai định dạng priceGiaNhap");
                return null;
            }
        }

        BigDecimal giaBanBigDecimal = BigDecimal.ZERO;
        if (!giaBanString.isEmpty()) {  // nếu không rỗng thì vào try catch 
            try {
                giaBanBigDecimal = new BigDecimal(giaBanString.trim()); // loại bỏ khoảng trắng  và ép price thành kiểu Bigdecimal
                chiTietSP.setGiaBan(giaBanBigDecimal);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Sai định dạng priceGiaBan");
                return null;
            }
        }

        int soLuongTonInt = 0;
        if (!soLuongTonString.isEmpty()) {
            try {
                soLuongTonInt = Integer.parseInt(soLuongTonString.trim());
                chiTietSP.setNamBH(soLuongTonInt);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "SoLuongTon sai định dạng");
                return null;
            }
        }
        // lấy ra tên khi mình chọn ComBoBox
        String tenSanPham = cbbSanPham.getSelectedItem().toString();
        String tenNSX = cbbNSX.getSelectedItem().toString();
        String tenMauSac = cbbMauSac.getSelectedItem().toString();
        String tenDongSP = cbbDongSP.getSelectedItem().toString();

        // lấy ra id theo tên của ComBoBox
        chiTietSP.setIdSP(iSanPhamService.idSanPhamByTen(tenSanPham));
        chiTietSP.setIdNSX(iNSXService.getIdNSXByTen(tenNSX));
        chiTietSP.setIdMauSac(iMauSacService.getIdMauSacByTenMauSac(tenMauSac));
        chiTietSP.setIdDongSP(iDongSPService.getIdDongSPByTen(tenDongSP));

        // set vào  chiTietSP
        chiTietSP.setMoTa(moTaString);
        chiTietSP.setNamBH(namBHInt);
        chiTietSP.setSoLuongTon(soLuongTonInt);
        chiTietSP.setGiaNhap(giaNhapBigDecimal);
        chiTietSP.setGiaBan(giaBanBigDecimal);
        return chiTietSP;
    }

    // loadComBoMauSacByten
    public void loadComBoBoxByTenMauSac() {
        ArrayList<MauSac> mauSac = iMauSacService.getAll();
        for (MauSac ms : mauSac) {
            cbbMauSac.addItem(ms.getTen());
        }
    }
    // loadComBoBoxByTenSanPham

    public void loadComBoBoxByTenSanPham() {
        List<SanPham> sanPham = iSanPhamService.getAll();
        for (SanPham sp : sanPham) {
            cbbSanPham.addItem(sp.getTen());
        }
    }

    // loadComBoBoxByTenChucVu
    public void loadComBoBoxByTenDongSP() {
        List<DongSP> dongSP = iDongSPService.getAll();
        for (DongSP dongSPs : dongSP) {
            cbbDongSP.addItem(dongSPs.getTen());
        }
    }

    // loadComBoBoxTen
    public void loadComBoBoxTenNSX() {
        List<NSX> nsx = iNSXService.getAll();
        for (NSX nsxs : nsx) {
            cbbNSX.addItem(nsxs.getTen());
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel9 = new javax.swing.JLabel();
        jScrollBar1 = new javax.swing.JScrollBar();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        cbbSanPham = new javax.swing.JComboBox<>();
        cbbNSX = new javax.swing.JComboBox<>();
        cbbMauSac = new javax.swing.JComboBox<>();
        cbbDongSP = new javax.swing.JComboBox<>();
        txtNamBH = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txtMoTa = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        txtSoLuongTon = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        txtGiaNhap = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        txtGiaBan = new javax.swing.JTextField();
        btnThem = new javax.swing.JButton();
        btnSua = new javax.swing.JButton();
        btnXoa = new javax.swing.JButton();
        btnMauSac = new javax.swing.JButton();
        btnNSX = new javax.swing.JButton();
        btnSanPham = new javax.swing.JButton();
        btnDongSP = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblChiTietSPForm = new javax.swing.JTable();
        btnThoat = new javax.swing.JButton();

        jLabel9.setText("jLabel9");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("Chi Tiết Sản Phẩm");

        jLabel2.setText("Sản Phẩm");

        jLabel3.setText("NSX");

        jLabel4.setText("Màu Sắc");

        jLabel5.setText("Dòng SP");

        jLabel6.setText("Năm BH");

        cbbSanPham.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbbSanPhamActionPerformed(evt);
            }
        });

        cbbMauSac.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbbMauSacActionPerformed(evt);
            }
        });

        cbbDongSP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbbDongSPActionPerformed(evt);
            }
        });

        jLabel7.setText("Mô Tả");

        jLabel8.setText("Số Lượng Tồn");

        jLabel10.setText("Giá Nhập");

        jLabel11.setText("Giá Bán");

        btnThem.setText("Thêm");
        btnThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemActionPerformed(evt);
            }
        });

        btnSua.setText("Sửa");
        btnSua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaActionPerformed(evt);
            }
        });

        btnXoa.setText("Xoá");
        btnXoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaActionPerformed(evt);
            }
        });

        btnMauSac.setText("MauSac");
        btnMauSac.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMauSacActionPerformed(evt);
            }
        });

        btnNSX.setText("NSX");
        btnNSX.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNSXActionPerformed(evt);
            }
        });

        btnSanPham.setText("SanPham");
        btnSanPham.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSanPhamActionPerformed(evt);
            }
        });

        btnDongSP.setText("DongSP");
        btnDongSP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDongSPActionPerformed(evt);
            }
        });

        tblChiTietSPForm.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Sản phẩm", "NSX", "Màu Sắc", "Dòng SP", "Năm BH", "Mô tả", "Số lượng tồn", "Giá nhập", "Giá Bán"
            }
        ));
        tblChiTietSPForm.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblChiTietSPFormMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblChiTietSPForm);

        btnThoat.setText("Thoát");
        btnThoat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThoatActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel11)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(txtGiaBan))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel10)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(txtGiaNhap))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel8)
                                        .addGap(18, 18, 18)
                                        .addComponent(txtSoLuongTon, javax.swing.GroupLayout.DEFAULT_SIZE, 87, Short.MAX_VALUE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel7)
                                        .addGap(18, 18, 18)
                                        .addComponent(txtMoTa))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel6)
                                        .addGap(18, 18, 18)
                                        .addComponent(txtNamBH))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel5)
                                        .addGap(18, 18, 18)
                                        .addComponent(cbbDongSP, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel4)
                                        .addGap(18, 18, 18)
                                        .addComponent(cbbMauSac, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel2)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(cbbSanPham, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel3)
                                        .addGap(41, 41, 41)
                                        .addComponent(cbbNSX, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                .addGap(33, 33, 33)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(btnThem)
                                            .addComponent(btnSua)
                                            .addComponent(btnXoa))
                                        .addGap(39, 39, 39)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(btnMauSac, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(btnNSX, javax.swing.GroupLayout.DEFAULT_SIZE, 117, Short.MAX_VALUE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 52, Short.MAX_VALUE)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(btnSanPham, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(btnDongSP, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                    .addComponent(btnThoat)))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(206, 206, 206)
                                .addComponent(jLabel1)))
                        .addGap(74, 74, 74)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(cbbSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnThem)
                    .addComponent(btnMauSac)
                    .addComponent(btnDongSP))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(cbbNSX, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSua)
                    .addComponent(btnNSX)
                    .addComponent(btnSanPham))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(cbbMauSac, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnXoa))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(cbbDongSP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnThoat))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(txtNamBH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(txtMoTa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(txtSoLuongTon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(txtGiaNhap, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(txtGiaBan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnMauSacActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMauSacActionPerformed
        new ViewMauSac().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnMauSacActionPerformed

    private void btnDongSPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDongSPActionPerformed
        new ViewDongSP().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnDongSPActionPerformed

    private void btnNSXActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNSXActionPerformed
        new ViewNSX().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnNSXActionPerformed

    private void btnSanPhamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSanPhamActionPerformed
        new ViewSanPham().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnSanPhamActionPerformed

    private void cbbDongSPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbbDongSPActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbbDongSPActionPerformed

    private void cbbSanPhamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbbSanPhamActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbbSanPhamActionPerformed

    private void btnThoatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThoatActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnThoatActionPerformed

    private void cbbMauSacActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbbMauSacActionPerformed

    }//GEN-LAST:event_cbbMauSacActionPerformed

    private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemActionPerformed
        ChiTietSP chiTietSP = getData();
        if (chiTietSP == null) {
            return;
        }
        JOptionPane.showMessageDialog(this, iChiTietSPService.insert(chiTietSP));
        loadTable(iChiTietSPService.getListChiTietSPViewModel());
    }//GEN-LAST:event_btnThemActionPerformed

    private void btnXoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaActionPerformed
        DefaultTableModel defaultTableModel = (DefaultTableModel) tblChiTietSPForm.getModel();
        int row = tblChiTietSPForm.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn dòng để xoá.");
            return;
        }

        ArrayList<ViewModelChiTietSP> list = iChiTietSPService.getListChiTietSPViewModel();
        ViewModelChiTietSP viewModelChiTietSP = list.get(row);
        int chon = JOptionPane.showConfirmDialog(this, "Bạn có xoá", "Xoá", JOptionPane.YES_NO_OPTION);
        if (chon != JOptionPane.YES_NO_OPTION) {
            return;
        }
        if (iChiTietSPService.delete(viewModelChiTietSP.getId())) {
            defaultTableModel.removeRow(row);
            JOptionPane.showMessageDialog(this, "Xoá thành công.");
        } else {
            JOptionPane.showMessageDialog(this, "Xoá thất bại.");
        }
    }//GEN-LAST:event_btnXoaActionPerformed

    private void btnSuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaActionPerformed
        ChiTietSP chiTietSP1 = getData();

        int row = tblChiTietSPForm.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn dòng để update");
            return;
        }

        int chon = JOptionPane.showConfirmDialog(this, "Xác nhận update", "Update", JOptionPane.YES_NO_OPTION);
        if (chon != JOptionPane.YES_NO_OPTION || chiTietSP1 == null) {
            return;
        }

        ArrayList<ViewModelChiTietSP> listChiTietSP = iChiTietSPService.getListChiTietSPViewModel();
        ViewModelChiTietSP chiTietSP = listChiTietSP.get(row);

        JOptionPane.showMessageDialog(this, iChiTietSPService.update(chiTietSP.getId(), chiTietSP1));
        loadTable(iChiTietSPService.getListChiTietSPViewModel());

    }//GEN-LAST:event_btnSuaActionPerformed

    private void tblChiTietSPFormMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblChiTietSPFormMouseClicked
        int row = tblChiTietSPForm.getSelectedRow();
        String IdSP = tblChiTietSPForm.getValueAt(row, 0).toString();
        String IdNsx = tblChiTietSPForm.getValueAt(row, 1).toString();
        String IdMauSac = tblChiTietSPForm.getValueAt(row, 2).toString();
        String IdDongSP = tblChiTietSPForm.getValueAt(row, 3).toString();
        String NamBH = tblChiTietSPForm.getValueAt(row, 4).toString();
        String MoTa = tblChiTietSPForm.getValueAt(row, 5).toString();
        String SoLuongTon = tblChiTietSPForm.getValueAt(row, 6).toString();
        String GiaNhap = tblChiTietSPForm.getValueAt(row, 7).toString();
        String GiaBan = tblChiTietSPForm.getValueAt(row, 8).toString();

        cbbSanPham.setSelectedItem(IdSP);
        cbbNSX.setSelectedItem(IdNsx);
        cbbDongSP.setSelectedItem(IdDongSP);
        cbbMauSac.setSelectedItem(IdMauSac);
        txtNamBH.setText(NamBH);
        txtMoTa.setText(MoTa);
        txtSoLuongTon.setText(SoLuongTon);
        txtGiaNhap.setText(GiaNhap);
        txtGiaBan.setText(GiaBan);

    }//GEN-LAST:event_tblChiTietSPFormMouseClicked

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ViewChiTietSP.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ViewChiTietSP.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ViewChiTietSP.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ViewChiTietSP.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ViewChiTietSP().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnDongSP;
    private javax.swing.JButton btnMauSac;
    private javax.swing.JButton btnNSX;
    private javax.swing.JButton btnSanPham;
    private javax.swing.JButton btnSua;
    private javax.swing.JButton btnThem;
    private javax.swing.JButton btnThoat;
    private javax.swing.JButton btnXoa;
    private javax.swing.JComboBox<String> cbbDongSP;
    private javax.swing.JComboBox<String> cbbMauSac;
    private javax.swing.JComboBox<String> cbbNSX;
    private javax.swing.JComboBox<String> cbbSanPham;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollBar jScrollBar1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblChiTietSPForm;
    private javax.swing.JTextField txtGiaBan;
    private javax.swing.JTextField txtGiaNhap;
    private javax.swing.JTextField txtMoTa;
    private javax.swing.JTextField txtNamBH;
    private javax.swing.JTextField txtSoLuongTon;
    // End of variables declaration//GEN-END:variables
}
