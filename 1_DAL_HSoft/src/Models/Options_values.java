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
public class Options_values {

    private String ID_Options, ID_values, Names;
    private int TRANGTHAI;

    public Options_values() {
    }

    public Options_values(String ID_Options, String ID_values, String Names, int TRANGTHAI) {
        this.ID_Options = ID_Options;
        this.ID_values = ID_values;
        this.Names = Names;
        this.TRANGTHAI = TRANGTHAI;
    }

    public String getID_Options() {
        return ID_Options;
    }

    public void setID_Options(String ID_Options) {
        this.ID_Options = ID_Options;
    }

    public String getID_values() {
        return ID_values;
    }

    public void setID_values(String ID_values) {
        this.ID_values = ID_values;
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
