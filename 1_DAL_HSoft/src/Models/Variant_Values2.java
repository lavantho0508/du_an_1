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
public class Variant_Values2 {

    private String Names, ID_Variant, SKU_ID, ThuocTinh;
    private int Quantity;
    private double Price;
    private int TrangThai;

    public Variant_Values2() {
    }

    public Variant_Values2(String Names, String ID_Variant, String SKU_ID, String ThuocTinh, int Quantity, double Price, int TrangThai) {
        this.Names = Names;
        this.ID_Variant = ID_Variant;
        this.SKU_ID = SKU_ID;
        this.ThuocTinh = ThuocTinh;
        this.Quantity = Quantity;
        this.Price = Price;
        this.TrangThai = TrangThai;
    }

    public String getNames() {
        return Names;
    }

    public void setNames(String Names) {
        this.Names = Names;
    }

    public String getID_Variant() {
        return ID_Variant;
    }

    public void setID_Variant(String ID_Variant) {
        this.ID_Variant = ID_Variant;
    }

    public String getSKU_ID() {
        return SKU_ID;
    }

    public void setSKU_ID(String SKU_ID) {
        this.SKU_ID = SKU_ID;
    }

    public String getThuocTinh() {
        return ThuocTinh;
    }

    public void setThuocTinh(String ThuocTinh) {
        this.ThuocTinh = ThuocTinh;
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

    public int getTrangThai() {
        return TrangThai;
    }

    public void setTrangThai(int TrangThai) {
        this.TrangThai = TrangThai;
    }

}
