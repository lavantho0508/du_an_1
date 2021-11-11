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
public class CHUC_VU {
    private String MACV, TENCV;
    private int TRANGTHAI;

    public CHUC_VU() {
    }

    public CHUC_VU(String MACV, String TENCV, int TRANGTHAI) {
        this.MACV = MACV;
        this.TENCV = TENCV;
        this.TRANGTHAI = TRANGTHAI;
    }

    public String getMACV() {
        return MACV;
    }

    public void setMACV(String MACV) {
        this.MACV = MACV;
    }

    public String getTENCV() {
        return TENCV;
    }

    public void setTENCV(String TENCV) {
        this.TENCV = TENCV;
    }

    public int getTRANGTHAI() {
        return TRANGTHAI;
    }

    public void setTRANGTHAI(int TRANGTHAI) {
        this.TRANGTHAI = TRANGTHAI;
    }
    
    
}
