/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Sevices;

import Helper.JdbcHelper;
import IServices.IVariant_Values2_Service;
import Models.Variant_Values2;
import Models.Variant_values;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author BUI_QUANG_HIEU
 */
public class Variant_Values2_Service implements IVariant_Values2_Service {

    @Override
    public Variant_Values2 readFromResultSet(ResultSet rs) throws SQLException {
        Variant_Values2 model = new Variant_Values2();

        model.setNames(rs.getString("Names"));
        model.setID_Variant(rs.getString("ID_variant"));
        model.setSKU_ID(rs.getString("SKU_ID"));
        model.setThuocTinh(rs.getString("TT"));
        model.setQuantity(rs.getInt("Quantity"));
        model.setPrice(rs.getDouble("Price"));
        model.setTrangThai(rs.getInt("TRANGTHAI"));
        return model;
    }

    @Override
    public List<Variant_Values2> select(String sql, Object... args) {
        List<Variant_Values2> list = new ArrayList<>();
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
    public void insert(Variant_Values2 model) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Variant_Values2> selectAll() {
        String sql = "SELECT DISTINCT\n"
                + "        Product.Names,Product_variant.ID_variant, Product_variant.SKU_ID,\n"
                + "        (   SELECT Options.Names+ ': '+ Options_values.Names +', ' AS [text()]\n"
                + "                FROM dbo.Variant_values C1\n"
                + "        join Product_variant on C1.ID_variant = Product_variant.ID_variant\n"
                + "        join Product_options on C1.ID_Options = Product_options.ID_Options\n"
                + "        join Options on Product_options.ID_Options = Options.ID_Options\n"
                + "        join Options_values on C1.ID_values = Options_values.ID_values\n"
                + "        join Product on Product_variant.ID_Product = Product.ID_Product\n"
                + "		where C1.ID_variant = C2.ID_variant\n"
                + "            ORDER BY C1.ID_variant\n"
                + "            FOR XML PATH(''))[TT], Quantity, Price, C2.TRANGTHAI\n"
                + "    FROM dbo.Variant_values C2\n"
                + "	join Product_variant on C2.ID_variant = Product_variant.ID_variant\n"
                + "        join Product_options on C2.ID_Options = Product_options.ID_Options\n"
                + "        join Options on Product_options.ID_Options = Options.ID_Options\n"
                + "        join Options_values on C2.ID_values = Options_values.ID_values\n"
                + "        join Product on Product_variant.ID_Product = Product.ID_Product";
        return select(sql);
    }

    @Override
    public List<Variant_Values2> selectByID(String ID) {
        String sql = "			SELECT DISTINCT \n"
                + "        Product.Names,Product_variant.ID_variant, Product_variant.SKU_ID,\n"
                + "        (   SELECT Options.Names+ ': '+ Options_values.Names +', ' AS [text()]\n"
                + "                FROM dbo.Variant_values C1\n"
                + "        join Product_variant on C1.ID_variant = Product_variant.ID_variant\n"
                + "        join Product_options on C1.ID_Options = Product_options.ID_Options\n"
                + "        join Options on Product_options.ID_Options = Options.ID_Options\n"
                + "        join Options_values on C1.ID_values = Options_values.ID_values\n"
                + "        join Product on Product_variant.ID_Product = Product.ID_Product\n"
                + "		where C1.ID_variant = C2.ID_variant\n"
                + "            ORDER BY C1.ID_variant\n"
                + "            FOR XML PATH(''))[TT], Quantity, Price, C2.TRANGTHAI\n"
                + "    FROM dbo.Variant_values C2\n"
                + "	join Product_variant on C2.ID_variant = Product_variant.ID_variant\n"
                + "        join Product_options on C2.ID_Options = Product_options.ID_Options\n"
                + "        join Options on Product_options.ID_Options = Options.ID_Options\n"
                + "        join Options_values on C2.ID_values = Options_values.ID_values\n"
                + "        join Product on Product_variant.ID_Product = Product.ID_Product\n"
                + "		where  Product.ID_Product like ?";
        return select(sql, ID);
    }

}
