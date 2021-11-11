/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Sevices;

import Helper.JdbcHelper;
import Models.NHAN_VIEN;
import Models.Product_options;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.sql.*;
import java.util.Random;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author DELL
 */
public class NHAN_VIEN_Service implements IServices.INHAN_VIEN_Service{
  static  List<NHAN_VIEN>list=new ArrayList<>();
    public  List<NHAN_VIEN> selectAll() {
     
       return select("select*from nhan_vien");
    }
public  List<NHAN_VIEN> select(String sql, Object... args) {
        
        try {
            ResultSet rs = null;
            try {
                rs = JdbcHelper.executeQuery(sql, args);
                while (rs.next()) {
                    list.add( new NHAN_VIEN(rs.getString("manv"),rs.getString("hoten"),rs.getInt("gioitinh"),rs.getString("sdt"),
                    rs.getString("email"),rs.getString("matkhau"),rs.getDate("ngaysinh"),rs.getString("diachi"),rs.getInt("trangthai")));
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
    public List<NHAN_VIEN> selectEmailByID(String ma) {
       return select("select*from nhan_vien where manv=?",ma);
       
    }
    
    

    @Override
    public String randomCode() {
          String AlphaNumericString ="0123456789"
                                    + "abcdefghijklmnopqrstuvxyz";
         StringBuilder str=new StringBuilder(6);
          for (int i = 0; i < 6; i++) {
  
            // generate a random number between
            // 0 to AlphaNumericString variable length
            int index
                = (int)(AlphaNumericString.length()
                        * Math.random());
            str.append(AlphaNumericString
                          .charAt(index));
        }
      return str.toString();
    }

    @Override
    public int changePassWord(String ma,String pass) {
      try {
          String query="update nhan_vien set matkhau =? where manv=?";
          
          PreparedStatement pre=DriverManager.getConnection(JdbcHelper.dburl,JdbcHelper.username,JdbcHelper.password).prepareStatement(query);
          pre.setString(1, pass);
          pre.setString(2, ma);
          return pre.executeUpdate();
          // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
      } catch (SQLException ex) {
          Logger.getLogger(NHAN_VIEN_Service.class.getName()).log(Level.SEVERE, null, ex);
      }
     
     return 0;
    }

    
}
