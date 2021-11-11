/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IServices;

import Models.Product_variant;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author BUI_QUANG_HIEU
 */
public interface IProduct_Variant_Service {

    public Product_variant readFromResultSet(ResultSet rs) throws SQLException;

    public List<Product_variant> select(String sql, Object... args);

    public List<Product_variant> selectAll();

    public List<Product_variant> selectByIDVariant(String IDVariant);

    public List<Product_variant> selectByIDProduct(String IDProduct);

    public Product_variant findById(String IDoptions_values);

    public Product_variant findByNames(String SKUID);

    public void insert(Product_variant options_values);

    public void update(Product_variant options_values);

    public void delete(String IDProduct, String IDVariant);
}
