/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Sevices;

import Helper.JdbcHelper;
import IServices.IProduct_Options_Service;
import Models.Product_options;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author BUI_QUANG_HIEU
 */
public class Product_Options_Service implements IProduct_Options_Service {

    @Override
    public Product_options readFromResultSet(ResultSet rs) throws SQLException {
        Product_options model = new Product_options();
        model.setID_Product(rs.getString("ID_Product"));
        model.setID_Options(rs.getString("ID_Options"));
        model.setTRANGTHAI(rs.getInt("TRANGTHAI"));
        return model;
    }

    @Override
    public List<Product_options> select(String sql, Object... args) {
        List<Product_options> list = new ArrayList<>();
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
    public List<Product_options> selectAll() {
        String sql = "select * from Product_options";
        return select(sql);
    }

    @Override
    public List<Product_options> selectByID(String IDProduct) {
        String sql = "select * from Product_options where ID_Product like ?";
        return select(sql, IDProduct);
    }

    @Override
    public Product_options findById(String IDProduct) {
        String sql = "select * from Product_options where ID_Product like ?";
        List<Product_options> list = select(sql, IDProduct);
        return list.size() > 0 ? list.get(0) : null;
    }

    @Override
    public void insert(Product_options product_options) {
        String sql = "insert into Product_Options(ID_Product, ID_Options, TRANGTHAI) values(?, ? ,?)";
        Helper.JdbcHelper.executeUpdate(sql,
                product_options.getID_Product(),
                product_options.getID_Options(),
                product_options.getTRANGTHAI());
    }

    @Override
    public void update(Product_options product_options) {
        String sql = "update Product_Options set ID_Options = ?, TRANGTHAI = ? where ID_Product like ?";
        Helper.JdbcHelper.executeUpdate(sql,
                product_options.getID_Options(),
                product_options.getTRANGTHAI(),
                product_options.getID_Product());
    }

    @Override
    public void delete(String IDProduct) {
        String sql = "delete from Product_Options where ID_Product like ?";
        Helper.JdbcHelper.executeUpdate(sql, IDProduct);
    }

}
