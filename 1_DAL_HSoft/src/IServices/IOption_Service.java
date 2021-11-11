/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IServices;

import Models.Options;
import Models.Product;
import Models.Variant_values;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author BUI_QUANG_HIEU
 */
public interface IOption_Service {

    public Options readFromResultSet(ResultSet rs) throws SQLException;

    public List<Options> select(String sql, Object... args);

    public List<Options> selectAll();

    public Options findById(String IDoptions);

    public Options findByNames(String Names);

    public void insert(Options options);

    public void update(Options options);

    public void delete(String IDoptions);
}
