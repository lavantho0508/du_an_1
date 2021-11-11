/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IServices;

import Models.Options;
import Models.Product_options;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author BUI_QUANG_HIEU
 */
public interface IProduct_Options_Service {

    public Product_options readFromResultSet(ResultSet rs) throws SQLException;

    public List<Product_options> select(String sql, Object... args);

    public List<Product_options> selectAll();
    
    public List<Product_options> selectByID(String IDProduct);

    public Product_options findById(String IDProduct);

    public void insert(Product_options product_options);

    public void update(Product_options product_options);

    public void delete(String IDProduct);
}
