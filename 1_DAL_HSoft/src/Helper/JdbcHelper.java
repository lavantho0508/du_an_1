/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Helper;

import java.lang.System.Logger;
import java.sql.*;

/**
 *
 * @author BUI_QUANG_HIEU
 */
public class JdbcHelper {

    public static String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
    public static String dburl = "jdbc:sqlserver://localhost:1433;databaseName=HSoft";
    public static String username = "admin";
    public static String password = "123";

    //nạp driver
    static {
        try {
            Class.forName(driver);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //nạp truyền giá trị đối số vào prepareStatement
    //có thể statment không có đối số
    //prepareStatement có thể là prepareStatement hoặc CallableStatements
    /*
    @param sql câu lệnh sql statement (có đối số hoặc không)
    @param args mảng đối số của câu lệnh sql (có hoặc không)
    return pstmt là PrepareStatement đã được truyền đối số
     */
    public static PreparedStatement preparedStatement(String sql, Object... args) throws SQLException {
        Connection con = DriverManager.getConnection(dburl, username, password);
        PreparedStatement pstmt = null;
        if (sql.startsWith("{")) {
            pstmt = con.prepareCall(sql);    //có thể gán biến kiểu PreparedStatement là prepareCall (CallableStatement)
        } else {
            pstmt = con.prepareStatement(sql);
        }
        for (int i = 0; i < args.length; i++) {
            pstmt.setObject(i + 1, args[i]);
        }
        return pstmt;
    }

    //thao tác (INSERT, UPDATE, DELETE)
    //thực thi prepareStatement (đã được truyền đối số ở hàm trên)
    //khi làm chỉ cần gọi hàm này, từ trong hàm này nó sẽ gọi ra hàm preparedStatement ở trên
    /*
    @param sql (String) câu lệnh sql statement (có đối số hoặc không)
    @param args mảng đối số của câu lệnh sql (có hoặc không)
     */
    public static void executeUpdate(String sql, Object... args) {
        try {
            PreparedStatement pstmt = preparedStatement(sql, args);
            try {
                pstmt.executeUpdate();
            } finally {
                pstmt.getConnection().close();            //đóng Connection từ statement
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex);
        }
    }

    public static void executeResetPass(String sql, String pass, String maNV) {
        try {
            PreparedStatement pstmt = preparedStatement(sql, pass, maNV);
            try {
                pstmt.executeUpdate();
            } finally {
                pstmt.getConnection().close();            //đóng Connection từ statement
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex);
        }
    }

    //thao tác truy vấn (SELECT)
    //thực thi prepareStatement (đã được truyền đối số ở hàm trên)
    //khi làm chỉ cần gọi hàm này, từ trong hàm này nó sẽ gọi ra hàm preparedStatement ở trên
    /*
    @param sql (String) câu lệnh sql statement (có đối số hoặc không)
    @param args mảng đối số của câu lệnh sql (có hoặc không)
     */
    public static ResultSet executeQuery(String sql, Object... args) {
        try {
            PreparedStatement pstmt = preparedStatement(sql, args);
            try {
                return pstmt.executeQuery();
            } finally {
                //pstmt.getConnection().close();
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex);         //throw các lỗi khi chạy CT, VD không có return khi try bị lỗi
        }
    }
}
