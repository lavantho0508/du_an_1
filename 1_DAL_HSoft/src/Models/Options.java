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
public class Options {

    private String ID_Options, Names;
    private int TRANGTHAI;

    public Options() {
    }

    public Options(String ID_Options, String Names, int TRANGTHAI) {
        this.ID_Options = ID_Options;
        this.Names = Names;
        this.TRANGTHAI = TRANGTHAI;
    }

    public String getID_Options() {
        return ID_Options;
    }

    public void setID_Options(String ID_Options) {
        this.ID_Options = ID_Options;
    }

    public String getNames() {
        return Names;
    }

    public void setNames(String Names) {
        this.Names = Names;
    }

    public int getTRANGTHAI() {
        return TRANGTHAI;
    }

    public void setTRANGTHAI(int TRANGTHAI) {
        this.TRANGTHAI = TRANGTHAI;
    }

    @Override
    public String toString() {
        return Names;
    }

}
