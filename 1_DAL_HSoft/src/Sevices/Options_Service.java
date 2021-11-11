/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Sevices;

import Helper.JdbcHelper;
import Models.Options;
import Models.Product;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import IServices.IOption_Service;
import IServices.IProduct_Service;

/**
 *
 * @author BUI_QUANG_HIEU
 */
public class Options_Service implements IOption_Service {

    @Override
    public Options readFromResultSet(ResultSet rs) throws SQLException {
        Options model = new Options();

        model.setID_Options(rs.getString("ID_Options"));
        model.setNames(rs.getString("Names"));
        model.setTRANGTHAI(rs.getInt("TRANGTHAI"));
        return model;
    }

    @Override
    public List<Options> select(String sql, Object... args) {
        List<Options> list = new ArrayList<>();
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
    public List<Options> selectAll() {
        String sql = "select * from Options";
        return select(sql);
    }

    @Override
    public Options findById(String IDoptions) {
        String sql = "select * from Options where ID_Options like ?";
        List<Options> list = select(sql, IDoptions);
        return list.size() > 0 ? list.get(0) : null;
    }

    @Override
    public Options findByNames(String Names) {
        String sql = "select * from Options where Names like ?";
        List<Options> list = select(sql, Names);
        return list.size() > 0 ? list.get(0) : null;
    }

    @Override
    public void insert(Options options) {
        String sql = "insert into Options(ID_Options, Names, TRANGTHAI) values(?, ? ,?)";
        Helper.JdbcHelper.executeUpdate(sql,
                options.getID_Options(),
                options.getNames(),
                options.getTRANGTHAI());
    }

    @Override
    public void update(Options options) {
        String sql = "update Options set Names = ?, TRANGTHAI = ? where ID_Options like ?";
        Helper.JdbcHelper.executeUpdate(sql,
                options.getNames(),
                options.getTRANGTHAI(),
                options.getID_Options());
    }

    @Override
    public void delete(String IDoptions) {
        String sql = "delete from Options where ID_Options like ?";
        Helper.JdbcHelper.executeUpdate(sql, IDoptions);
    }

}
