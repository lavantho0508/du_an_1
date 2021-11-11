/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Sevices;

import Helper.JdbcHelper;
import Models.Variant_values;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author BUI_QUANG_HIEU
 */
public class Variant_Values_Service implements IServices.IVariant_Values_Service {

    @Override
    public Variant_values readFromResultSet(ResultSet rs) throws SQLException {
        Variant_values model = new Variant_values();

        model.setID_Options(rs.getString("ID_Product"));
        model.setID_variant(rs.getString("ID_variant"));
        model.setID_Options(rs.getString("ID_Options"));
        model.setID_values(rs.getString("ID_values"));
        model.setQuantity(rs.getInt("Quantity"));
        model.setPrice(rs.getDouble("Price"));
        model.setBarcode(rs.getString("Barcode"));
        model.setImages(rs.getString("Images"));
        model.setTRANGTHAI(rs.getInt("TRANGTHAI"));
        return model;
    }

    @Override
    public List<Variant_values> select(String sql, Object... args) {
        List<Variant_values> list = new ArrayList<>();
        try {
            ResultSet rs = null;
            try {
                rs = JdbcHelper.executeQuery(sql, args);
                while (rs.next()) {
                    list.add(readFromResultSet(rs));
                }
            } finally {
                rs.getStatement().getConnection().close();      //đóng kết nối từ resultSet
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new RuntimeException();
        }
        return list;
    }

    @Override
    public void insert(Variant_values model) {
        String sql = "INSERT INTO Variant_values (ID_Product, ID_variant, ID_Options, ID_values, Quantity, Price, Barcode, Images, TRANGTHAI) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        JdbcHelper.executeUpdate(sql,
                model.getID_Product(),
                model.getID_variant(),
                model.getID_Options(),
                model.getID_values(),
                model.getQuantity(),
                model.getPrice(),
                model.getBarcode(),
                model.getImages(),
                model.getTRANGTHAI());
    }

    @Override
    public List<Variant_values> selectAll() {
//        String sql = "SELECT DISTINCT\n"
//                + "        Product.Names,Product_variant.ID_variant, Product_variant.SKU_ID,\n"
//                + "        (   SELECT Options.Names+ ': '+ Options_values.Names +', ' AS [text()]\n"
//                + "                FROM dbo.Variant_values C1\n"
//                + "        join Product_variant on C1.ID_variant = Product_variant.ID_variant\n"
//                + "        join Product_options on C1.ID_Options = Product_options.ID_Options\n"
//                + "        join Options on Product_options.ID_Options = Options.ID_Options\n"
//                + "        join Options_values on C1.ID_values = Options_values.ID_values\n"
//                + "        join Product on Product_variant.ID_Product = Product.ID_Product\n"
//                + "		where C1.ID_variant = C2.ID_variant\n"
//                + "            ORDER BY C1.ID_variant\n"
//                + "            FOR XML PATH('')), Quantity, Price\n"
//                + "    FROM dbo.Variant_values C2\n"
//                + "	join Product_variant on C2.ID_variant = Product_variant.ID_variant\n"
//                + "        join Product_options on C2.ID_Options = Product_options.ID_Options\n"
//                + "        join Options on Product_options.ID_Options = Options.ID_Options\n"
//                + "        join Options_values on C2.ID_values = Options_values.ID_values\n"
//                + "        join Product on Product_variant.ID_Product = Product.ID_Product";
        String sql = "Select * from Variant_Values"; 
        return select(sql);
    }

//    @Override
//    public List<Variant_values> selectA() {
//        String sql = "Select * from Variant_values";
//        return select(sql);
//    }
    @Override
    public Variant_values findById(String idProduct) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Variant_values findByNames(String Names) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void update(Variant_values variant_values) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(String IDproduct) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
