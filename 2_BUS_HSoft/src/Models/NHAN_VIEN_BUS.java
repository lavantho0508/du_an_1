/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

/**
 *
 * @author DELL
 */
import java.util.Date;

/**
 *
 * @author BUI_QUANG_HIEU
 */
public class NHAN_VIEN_BUS extends NHAN_VIEN{

    public NHAN_VIEN_BUS() {
    }

    public NHAN_VIEN_BUS(String MANV, String HOTEN, int GIOITINH, String SDT, String EMAIL, String MATKHAU, Date NGAYSINH, String DIACHI, int TRANGTHAI) {
        super(MANV, HOTEN, GIOITINH, SDT, EMAIL, MATKHAU, NGAYSINH, DIACHI, TRANGTHAI);
    }

    @Override
    public void setTRANGTHAI(int TRANGTHAI) {
        super.setTRANGTHAI(TRANGTHAI); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int getTRANGTHAI() {
        return super.getTRANGTHAI(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setDIACHI(String DIACHI) {
        super.setDIACHI(DIACHI); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getDIACHI() {
        return super.getDIACHI(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setNGAYSINH(Date NGAYSINH) {
        super.setNGAYSINH(NGAYSINH); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Date getNGAYSINH() {
        return super.getNGAYSINH(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setMATKHAU(String MATKHAU) {
        super.setMATKHAU(MATKHAU); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getMATKHAU() {
        return super.getMATKHAU(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setEMAIL(String EMAIL) {
        super.setEMAIL(EMAIL); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getEMAIL() {
        return super.getEMAIL(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setSDT(String SDT) {
        super.setSDT(SDT); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getSDT() {
        return super.getSDT(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setGIOITINH(int GIOITINH) {
        super.setGIOITINH(GIOITINH); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int getGIOITINH() {
        return super.getGIOITINH(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setHOTEN(String HOTEN) {
        super.setHOTEN(HOTEN); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getHOTEN() {
        return super.getHOTEN(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setMANV(String MANV) {
        super.setMANV(MANV); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getMANV() {
        return super.getMANV(); //To change body of generated methods, choose Tools | Templates.
    }

   
    
}