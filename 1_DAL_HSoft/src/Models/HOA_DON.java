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
public class HOA_DON {

    private String MAHD, MANV, MAKH;
    private Date NGAYLAP;
    private int TRANGTHAI;

    public HOA_DON() {
    }

    public HOA_DON(String MAHD, String MANV, String MAKH, Date NGAYLAP, int TRANGTHAI) {
        this.MAHD = MAHD;
        this.MANV = MANV;
        this.MAKH = MAKH;
        this.NGAYLAP = NGAYLAP;
        this.TRANGTHAI = TRANGTHAI;
    }

    public String getMAHD() {
        return MAHD;
    }

    public void setMAHD(String MAHD) {
        this.MAHD = MAHD;
    }

    public String getMANV() {
        return MANV;
    }

    public void setMANV(String MANV) {
        this.MANV = MANV;
    }

    public String getMAKH() {
        return MAKH;
    }

    public void setMAKH(String MAKH) {
        this.MAKH = MAKH;
    }

    public Date getNGAYLAP() {
        return NGAYLAP;
    }

    public void setNGAYLAP(Date NGAYLAP) {
        this.NGAYLAP = NGAYLAP;
    }

    public int getTRANGTHAI() {
        return TRANGTHAI;
    }

    public void setTRANGTHAI(int TRANGTHAI) {
        this.TRANGTHAI = TRANGTHAI;
    }
    
    
}
