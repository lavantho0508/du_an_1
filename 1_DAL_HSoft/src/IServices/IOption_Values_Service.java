/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IServices;

import Models.Options;
import Models.Options_values;
import Models.Product;
import Models.Variant_values;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author BUI_QUANG_HIEU
 */
public interface IOption_Values_Service {

    public Options_values readFromResultSet(ResultSet rs) throws SQLException;

    public List<Options_values> select(String sql, Object... args);

    public List<Options_values> selectAll();

    public List<Options_values> selectByID(String ID);

    public Options_values findById(String IDoptions_values);

    public Options_values findByNames(String Names);

    public void insert(Options_values options_values);

    public void update(Options_values options_values);

    public void delete(String IDOptions, String IDValues);
}
