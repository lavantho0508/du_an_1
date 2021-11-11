/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

/**
 *
 * @author BUI_QUANG_HIEU
 */
public class NV_HAS_CV {

    private String MANV, MACV;
    private int TRANGTHAI;

    public NV_HAS_CV() {
    }

    public NV_HAS_CV(String MANV, String MACV, int TRANGTHAI) {
        this.MANV = MANV;
        this.MACV = MACV;
        this.TRANGTHAI = TRANGTHAI;
    }

    public String getMANV() {
        return MANV;
    }

    public void setMANV(String MANV) {
        this.MANV = MANV;
    }

    public String getMACV() {
        return MACV;
    }

    public void setMACV(String MACV) {
        this.MACV = MACV;
    }

    public int getTRANGTHAI() {
        return TRANGTHAI;
    }

    public void setTRANGTHAI(int TRANGTHAI) {
        this.TRANGTHAI = TRANGTHAI;
    }
    
    
}
