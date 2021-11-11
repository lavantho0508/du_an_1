/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IServices;

import Models.Product;
import Models.Variant_values;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author BUI_QUANG_HIEU
 */
public interface IProduct_Service {

    public Product readFromResultSet(ResultSet rs) throws SQLException;

    public List<Product> select(String sql, Object... args);

    public List<Product> selectAll();

    public Product findById(String idProduct);

    public Product findByNames(String Names);

    public void insert(Product product);

    public void update(Product product);

    public void delete(String IDproduct);
}
