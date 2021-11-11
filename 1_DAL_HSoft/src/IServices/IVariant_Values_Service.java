/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IServices;

import Models.Variant_values;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author BUI_QUANG_HIEU
 */
public interface IVariant_Values_Service {

    public Variant_values readFromResultSet(ResultSet rs) throws SQLException;

    public List<Variant_values> select(String sql, Object... args);

    public List<Variant_values> selectAll();

    public List<Variant_values> selectByIDVariant(String IDVariant);

    public Variant_values findById(String idProduct);

    public Variant_values findByIdVariant(String IDVariant);

    public Variant_values findByNames(String Names);

    public void insert(Variant_values variant_values);

    public void update(Variant_values variant_values);

    public void delete(String IDproduct);

    public int countOptions(String IDVariant);
}
