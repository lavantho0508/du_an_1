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
public class Variant_values {

    private String ID_Product, ID_variant, ID_Options, ID_values;
    private int Quantity;
    private double Price;
    private String Barcode, Images;
    private int TRANGTHAI;

    public Variant_values() {
    }

    public Variant_values(String ID_Product, String ID_variant, String ID_Options, String ID_values, int Quantity, double Price, String Barcode, String Images, int TRANGTHAI) {
        this.ID_Product = ID_Product;
        this.ID_variant = ID_variant;
        this.ID_Options = ID_Options;
        this.ID_values = ID_values;
        this.Quantity = Quantity;
        this.Price = Price;
        this.Barcode = Barcode;
        this.Images = Images;
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

    public int getQuantity() {
        return Quantity;
    }

    public void setQuantity(int Quantity) {
        this.Quantity = Quantity;
    }

    public double getPrice() {
        return Price;
    }

    public void setPrice(double Price) {
        this.Price = Price;
    }

    public String getBarcode() {
        return Barcode;
    }

    public void setBarcode(String Barcode) {
        this.Barcode = Barcode;
    }

    public String getImages() {
        return Images;
    }

    public void setImages(String Images) {
        this.Images = Images;
    }

    public int getTRANGTHAI() {
        return TRANGTHAI;
    }

    public void setTRANGTHAI(int TRANGTHAI) {
        this.TRANGTHAI = TRANGTHAI;
    }

    @Override
    public String toString() {
        return "Variant_values{" + "ID_Product=" + ID_Product + ", ID_variant=" + ID_variant + ", ID_Options=" + ID_Options + ", ID_values=" + ID_values + ", Quantity=" + Quantity + ", Price=" + Price + ", Barcode=" + Barcode + ", Images=" + Images + ", TRANGTHAI=" + TRANGTHAI + '}';
    }

}
