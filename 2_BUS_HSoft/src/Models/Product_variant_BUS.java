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
public class Product_variant_BUS {

    private String ID_Product, ID_variant, SKU_ID;
    private int TRANGTHAI;

    public Product_variant_BUS() {
    }

    public Product_variant_BUS(String ID_Product, String ID_variant, String SKU_ID, int TRANGTHAI) {
        this.ID_Product = ID_Product;
        this.ID_variant = ID_variant;
        this.SKU_ID = SKU_ID;
        this.TRANGTHAI = TRANGTHAI;
    }

    public String getID_Product() {
        return ID_Product;
    }

    public void setID_Product(String ID_Product) {
        this.ID_Product = ID_Product;
    }

    public String getID_variant() {
        return ID_variant;
    }

    public void setID_variant(String ID_variant) {
        this.ID_variant = ID_variant;
    }

    public String getSKU_ID() {
        return SKU_ID;
    }

    public void setSKU_ID(String SKU_ID) {
        this.SKU_ID = SKU_ID;
    }

    public int getTRANGTHAI() {
        return TRANGTHAI;
    }

    public void setTRANGTHAI(int TRANGTHAI) {
        this.TRANGTHAI = TRANGTHAI;
    }

    @Override
    public String toString() {
        return SKU_ID;
    }

}
