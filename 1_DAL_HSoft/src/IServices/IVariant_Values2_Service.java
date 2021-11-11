/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IServices;

import Models.Variant_Values2;
import Models.Variant_values;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author BUI_QUANG_HIEU
 */
public interface IVariant_Values2_Service {
    public Variant_Values2 readFromResultSet(ResultSet rs) throws SQLException;
    public List<Variant_Values2> select(String sql, Object... args);
    public void insert(Variant_Values2 model);
    public List<Variant_Values2> selectAll();
    public List<Variant_Values2> selectByID(String ID);
}
