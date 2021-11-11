/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Sevices;

import Helper.JdbcHelper;
import Models.Product;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import IServices.IProduct_Service;

/**
 *
 * @author BUI_QUANG_HIEU
 */
public class Product_Service implements IProduct_Service {

    @Override
    public List<Product> selectAll() {
        String sql = "select * from Product";
        return select(sql);
    }

    @Override
    public Product readFromResultSet(ResultSet rs) throws SQLException {
        Product model = new Product();

        model.setID_Product(rs.getString("ID_Product"));
        model.setNames(rs.getString("Names"));
        model.setTrangThai(rs.getInt("TRANGTHAI"));
        return model;
    }

    @Override
    public List<Product> select(String sql, Object... args) {
        List<Product> list = new ArrayList<>();
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
    public Product findById(String idProduct) {
        String sql = "select * from Product where ID_Product like ?";
        List<Product> list = select(sql, idProduct);
        return list.size() > 0 ? list.get(0) : null;
    }

    @Override
    public void insert(Product product) {
        String sql = "insert into Product(ID_Product, Names, TRANGTHAI) values(?, ? ,?)";
        Helper.JdbcHelper.executeUpdate(sql,
                product.getID_Product(),
                product.getNames(),
                product.getTrangThai());
    }

    @Override
    public Product findByNames(String Names) {
        String sql = "select * from Product where Names like ?";
        List<Product> list = select(sql, Names);
        return list.size() > 0 ? list.get(0) : null;
    }

    @Override
    public void delete(String IDproduct) {
        String sql = "delete from Product where ID_Product like ?";
        Helper.JdbcHelper.executeUpdate(sql, IDproduct);
    }

    @Override
    public void update(Product product) {
        String sql = "update Product set Names = ? , TRANGTHAI = ? where ID_Product like ?";
        Helper.JdbcHelper.executeUpdate(sql,
                product.getNames(),
                product.getTrangThai(),
                product.getID_Product());
    }

}
