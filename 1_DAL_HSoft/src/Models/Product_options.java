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
public class Product_options {

    private String ID_Product, ID_Options;
    private int TRANGTHAI;

    public Product_options() {
    }

    public Product_options(String ID_Product, String ID_Options, int TRANGTHAI) {
        this.ID_Product = ID_Product;
        this.ID_Options = ID_Options;
        this.TRANGTHAI = TRANGTHAI;
    }

    public String getID_Product() {
        return ID_Product;
    }

    public void setID_Product(String ID_Product) {
        this.ID_Product = ID_Product;
    }

    public String getID_Options() {
        return ID_Options;
    }

    public void setID_Options(String ID_Options) {
        this.ID_Options = ID_Options;
    }

    public int getTRANGTHAI() {
        return TRANGTHAI;
    }

    public void setTRANGTHAI(int TRANGTHAI) {
        this.TRANGTHAI = TRANGTHAI;
    }
    
    
}
