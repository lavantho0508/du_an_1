/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

import java.util.Date;

/**
 *
 * @author BUI_QUANG_HIEU
 */
public class NHAN_VIEN {

    private String MANV, HOTEN;
    private int GIOITINH;
    private String SDT, EMAIL, MATKHAU;
    private Date NGAYSINH;
    private String DIACHI;
    private int TRANGTHAI;

    public NHAN_VIEN() {
    }

    public NHAN_VIEN(String MANV, String HOTEN, int GIOITINH, String SDT, String EMAIL, String MATKHAU, Date NGAYSINH, String DIACHI, int TRANGTHAI) {
        this.MANV = MANV;
        this.HOTEN = HOTEN;
        this.GIOITINH = GIOITINH;
        this.SDT = SDT;
        this.EMAIL = EMAIL;
        this.MATKHAU = MATKHAU;
        this.NGAYSINH = NGAYSINH;
        this.DIACHI = DIACHI;
        this.TRANGTHAI = TRANGTHAI;
    }

    public String getMANV() {
        return MANV;
    }

    public void setMANV(String MANV) {
        this.MANV = MANV;
    }

    public String getHOTEN() {
        return HOTEN;
    }

    public void setHOTEN(String HOTEN) {
        this.HOTEN = HOTEN;
    }

    public int getGIOITINH() {
        return GIOITINH;
    }

    public void setGIOITINH(int GIOITINH) {
        this.GIOITINH = GIOITINH;
    }

    public String getSDT() {
        return SDT;
    }

    public void setSDT(String SDT) {
        this.SDT = SDT;
    }

    public String getEMAIL() {
        return EMAIL;
    }

    public void setEMAIL(String EMAIL) {
        this.EMAIL = EMAIL;
    }

    public String getMATKHAU() {
        return MATKHAU;
    }

    public void setMATKHAU(String MATKHAU) {
        this.MATKHAU = MATKHAU;
    }

    public Date getNGAYSINH() {
        return NGAYSINH;
    }

    public void setNGAYSINH(Date NGAYSINH) {
        this.NGAYSINH = NGAYSINH;
    }

    public String getDIACHI() {
        return DIACHI;
    }

    public void setDIACHI(String DIACHI) {
        this.DIACHI = DIACHI;
    }

    public int getTRANGTHAI() {
        return TRANGTHAI;
    }

    public void setTRANGTHAI(int TRANGTHAI) {
        this.TRANGTHAI = TRANGTHAI;
    }
    
    
}
