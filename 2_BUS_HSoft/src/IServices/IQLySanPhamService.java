
package IServices;

import Models.Options;
import Models.Options_values;
import Models.Product;
import Models.Product_options;
import Models.Product_variant;
import Models.Variant_Values2;
import Models.Variant_values;
import java.util.List;
//Hello HTML

public interface IQLySanPhamService {

    public List<Product> selectAllProduct();

    public List<Variant_Values2> selectAllVariant_Values2();

    public List<Variant_Values2> selectByIDVariant_Values2(String ID);

    public Product findByIdProduct(String idProduct);

    public Product findByNamesProduct(String Names);

    public void insertProduct(Product product);

    public void updateProduct(Product product);

    public void deleteProduct(String IDproduct);

    public List<Options> selectAllOptions();

    public Options findByIdOptions(String idOptions);

    public Options findByNamesOptions(String Names);

    public void insertOptions(Options options);

    public void updateOptions(Options options);

    public void deleteOptions(String IDOptions);

    public List<Options_values> selectAllOptions_values();

    public List<Options_values> selectByIDOptions_values(String ID);

    public Options_values findByIdValues_Options_values(String IDValues);
    
    public Options_values findByIdOptions_Options_values(String IDOptions);

    public Options_values findByNamesOptions_values(String Names);

    public void insertOptions_values(Options_values options_values);

    public void updateOptions_values(Options_values options_values);

    public void deleteOptions_values(String IDOptions, String IDValues);

    public List<Product_variant> selectAllProduct_variant();

    public List<Product_variant> selectByIDVariant_Product_variant(String IDVariant);

    public List<Product_variant> selectByIDProduct_Product_variant(String IDProduct);

    public Product_variant findByIdProduct_variant(String IDVariant);

    public Product_variant findByNamesProduct_variant(String SKUID);

    public void insertProduct_variant(Product_variant product_variant);

    public void updateProduct_variant(Product_variant product_variant);

    public void deleteProduct_variant(String IDProduct, String IDVariant);

    public List<Product_options> selectAllProduct_Options();

    public List<Product_options> selectByIDProduct_Options(String IDProduct);

    public Product_options findByIdProduct_Options(String IDProduct);

    public void insertProduct_Options(Product_options product_options);

    public void updateProduct_Options(Product_options product_options);

    public void deleteProduct_Options(String IDProduct);

    public List<Variant_values> selectAllVariantValues();
    
    public List<Variant_values> selectByIDVariant_VariantValues(String IDVarint);

    public List<Variant_values> selectByIDVariantValues(String IDProduct);

    public Variant_values findByIdVariantValues(String IDProduct);

    public Variant_values findByIdVariant_VariantValues(String IDVariant);

    public void insertVariantValues(Variant_values variant_values);

    public void updateVariantValues(Variant_values variant_values);

    public void deleteVariantValues(Variant_values variant_values);

    public int countOptions_VariantValues(String IDVariant);
    
}
