/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Sevices;

import Helper.JdbcHelper;
import IServices.IProduct_Options_Service;
import IServices.IProduct_Variant_Service;
import Models.Product_variant;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author BUI_QUANG_HIEU
 */
public class Product_Variant_Service implements IProduct_Variant_Service {

    @Override
    public Product_variant readFromResultSet(ResultSet rs) throws SQLException {
        Product_variant model = new Product_variant();

        model.setID_Product(rs.getString("ID_Product"));
        model.setID_variant(rs.getString("ID_variant"));
        model.setSKU_ID(rs.getString("SKU_ID"));
        model.setTRANGTHAI(rs.getInt("TRANGTHAI"));
        return model;
    }

    @Override
    public List<Product_variant> select(String sql, Object... args) {
        List<Product_variant> list = new ArrayList<>();
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
    public List<Product_variant> selectAll() {
        String sql = "select * from Product_variant";
        return select(sql);
    }

    @Override
    public List<Product_variant> selectByIDVariant(String IDVariant) {
        String sql = "select * from Product_variant where ID_variant like ?";
        return select(sql, IDVariant);
    }

    @Override
    public Product_variant findById(String IDoptions_values) {
        String sql = "select * from Product_variant where ID_variant like ?";
        List<Product_variant> list = select(sql, IDoptions_values);
        return list.size() > 0 ? list.get(0) : null;
    }

    @Override
    public Product_variant findByNames(String SKUID) {
        String sql = "select * from Product_variant where SKU_ID like ?";
        List<Product_variant> list = select(sql, SKUID);
        return list.size() > 0 ? list.get(0) : null;
    }

    @Override
    public void insert(Product_variant product_variant) {
        String sql = "insert into Product_variant(ID_Product, ID_variant, SKU_ID, TRANGTHAI) values(?, ?, ?, ?)";
        Helper.JdbcHelper.executeUpdate(sql,
                product_variant.getID_Product(),
                product_variant.getID_variant(),
                product_variant.getSKU_ID(),
                product_variant.getTRANGTHAI());
    }

    @Override
    public void update(Product_variant options_values) {
        String sql = "update Product_variant set SKU_ID = ?, TRANGTHAI = ? where ID_Product like ? and ID_variant like ?";
        Helper.JdbcHelper.executeUpdate(sql,
                options_values.getSKU_ID(),
                options_values.getTRANGTHAI(),
                options_values.getID_Product(),
                options_values.getID_variant());
    }

    @Override
    public void delete(String IDProduct, String IDVariant) {
        String sql = "delete from Product_variant where ID_Product like ? and ID_variant like ?";
        Helper.JdbcHelper.executeUpdate(sql, IDProduct, IDVariant);
    }

    @Override
    public List<Product_variant> selectByIDProduct(String IDProduct) {
        String sql = "select * from Product_variant where ID_Product like ?";
        return select(sql, IDProduct);
    }

}
