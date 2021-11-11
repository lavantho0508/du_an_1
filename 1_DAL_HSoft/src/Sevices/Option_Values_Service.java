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
import Models.Options_values;
import IServices.IProduct_Service;
import IServices.IOption_Values_Service;

/**
 *
 * @author BUI_QUANG_HIEU
 */
public class Option_Values_Service implements IOption_Values_Service {

    @Override
    public Options_values readFromResultSet(ResultSet rs) throws SQLException {
        Options_values model = new Options_values();

        model.setID_Options(rs.getString("ID_Options"));
        model.setID_values(rs.getString("ID_values"));
        model.setNames(rs.getString("Names"));
        model.setTRANGTHAI(rs.getInt("TRANGTHAI"));
        return model;
    }

    @Override
    public List<Options_values> select(String sql, Object... args) {
        List<Options_values> list = new ArrayList<>();
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
    public List<Options_values> selectAll() {
        String sql = "select * from Options_values";
        return select(sql);
    }

    @Override
    public Options_values findById(String IDoptions_values) {
        String sql = "select * from Options_values where ID_values like ?";
        List<Options_values> list = select(sql, IDoptions_values);
        return list.size() > 0 ? list.get(0) : null;
    }

    @Override
    public Options_values findByNames(String Names) {
        String sql = "select * from Options_values where Names like ?";
        List<Options_values> list = select(sql, Names);
        return list.size() > 0 ? list.get(0) : null;
    }

    @Override
    public void insert(Options_values options_values) {
        String sql = "insert into Options_values(ID_Options, ID_values, Names, TRANGTHAI) values(?, ?, ?, ?)";
        Helper.JdbcHelper.executeUpdate(sql,
                options_values.getID_Options(),
                options_values.getID_values(),
                options_values.getNames(),
                options_values.getTRANGTHAI());
    }

    @Override
    public void update(Options_values options_values) {
        String sql = "update Options_values set Names = ?, TRANGTHAI = ? where ID_Options like ? and ID_values like ?";
        Helper.JdbcHelper.executeUpdate(sql,
                options_values.getNames(),
                options_values.getTRANGTHAI(),
                options_values.getID_Options(),
                options_values.getID_values());
    }

    @Override
    public void delete(String IDOptions, String IDValues) {
        String sql = "delete from Options_values where ID_Options like ? and ID_values like ?";
        Helper.JdbcHelper.executeUpdate(sql, IDOptions, IDValues);
    }

    @Override
    public List<Options_values> selectByID(String ID) {
        String sql = "select * from Options_values where ID_Options like ?";
        return select(sql, ID);
    }

}
