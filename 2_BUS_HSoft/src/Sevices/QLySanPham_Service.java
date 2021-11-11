/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Sevices;

import IServices.IQLySanPhamService;
import IServices.IVariant_Values2_Service;
import Models.Options;
import Models.Product;
import Models.Variant_Values2;
import java.util.List;
import IServices.IOption_Service;
import Models.Options_values;
import IServices.IProduct_Service;
import IServices.IOption_Values_Service;
import IServices.IProduct_Options_Service;
import IServices.IProduct_Variant_Service;
import IServices.IVariant_Values_Service;
import Models.Product_options;
import Models.Product_variant;
import Models.Variant_values;

/**
 *
 * @author BUI_QUANG_HIEU
 */
public class QLySanPham_Service implements IQLySanPhamService {

    static IProduct_Service _IProductService = (IProduct_Service) new Product_Service();
    static IVariant_Values2_Service _IVariant_Values2_Service = (IVariant_Values2_Service) new Variant_Values2_Service();
    static IOption_Service _IOptionService = (IOption_Service) new Options_Service();
    static IOption_Values_Service _IOptionValues_Service = (IOption_Values_Service) new Option_Values_Service();
    static IProduct_Variant_Service _IProduct_Variant_Service = (IProduct_Variant_Service) new Product_Variant_Service();
    static IVariant_Values_Service _IVariant_Values_Service = (IVariant_Values_Service) new Variant_Values_Service();
    static IProduct_Options_Service _IProduct_Options_Service = (IProduct_Options_Service) new Product_Options_Service();

    @Override
    public List<Product> selectAllProduct() {
        return _IProductService.selectAll();
    }

    @Override
    public List<Variant_Values2> selectAllVariant_Values2() {
        return _IVariant_Values2_Service.selectAll();
    }

    @Override
    public List<Variant_Values2> selectByIDVariant_Values2(String ID) {
        return _IVariant_Values2_Service.selectByID(ID);
    }

    @Override
    public Product findByIdProduct(String idProduct) {
        return _IProductService.findById(idProduct);
    }

    @Override
    public void insertProduct(Product product) {
        _IProductService.insert(product);
    }

    @Override
    public Product findByNamesProduct(String Names) {
        return _IProductService.findByNames(Names);
    }

    @Override
    public void deleteProduct(String IDproduct) {
        _IProductService.delete(IDproduct);
    }

    @Override
    public void updateProduct(Product product) {
        _IProductService.update(product);
    }

    @Override
    public List<Options> selectAllOptions() {
        return _IOptionService.selectAll();
    }

    @Override
    public Options findByIdOptions(String idOptions) {
        return _IOptionService.findById(idOptions);
    }

    @Override
    public Options findByNamesOptions(String Names) {
        return _IOptionService.findByNames(Names);
    }

    @Override
    public void insertOptions(Options options) {
        _IOptionService.insert(options);
    }

    @Override
    public void updateOptions(Options options) {
        _IOptionService.update(options);
    }

    @Override
    public void deleteOptions(String IDOptions) {
        _IOptionService.delete(IDOptions);
    }

    @Override
    public List<Options_values> selectAllOptions_values() {
        return _IOptionValues_Service.selectAll();
    }

    @Override
    public Options_values findByIdOptions_values(String IDoptions_values) {
        return _IOptionValues_Service.findById(IDoptions_values);
    }

    @Override
    public Options_values findByNamesOptions_values(String Names) {
        return _IOptionValues_Service.findByNames(Names);
    }

    @Override
    public void insertOptions_values(Options_values options_values) {
        _IOptionValues_Service.insert(options_values);
    }

    @Override
    public void updateOptions_values(Options_values options_values) {
        _IOptionValues_Service.update(options_values);
    }

    @Override
    public void deleteOptions_values(String IDOptions, String IDValues) {
        _IOptionValues_Service.delete(IDOptions, IDValues);
    }

    @Override
    public List<Options_values> selectByIDOptions_values(String ID) {
        return _IOptionValues_Service.selectByID(ID);
    }

    @Override
    public List<Product_variant> selectAllProduct_variant() {
        return _IProduct_Variant_Service.selectAll();
    }

    @Override
    public List<Product_variant> selectByIDVariant_Product_variant(String IDVariant) {
        return _IProduct_Variant_Service.selectByIDVariant(IDVariant);
    }

    @Override
    public Product_variant findByIdProduct_variant(String IDVariant) {
        return _IProduct_Variant_Service.findById(IDVariant);
    }

    @Override
    public Product_variant findByNamesProduct_variant(String SKUID) {
        return _IProduct_Variant_Service.findByNames(SKUID);
    }

    @Override
    public void insertProduct_variant(Product_variant product_variant) {
        _IProduct_Variant_Service.insert(product_variant);
    }

    @Override
    public void updateProduct_variant(Product_variant product_variant) {
        _IProduct_Variant_Service.update(product_variant);
    }

    @Override
    public void deleteProduct_variant(String IDProduct, String IDVariant) {
        _IProduct_Variant_Service.delete(IDProduct, IDVariant);
    }

    @Override
    public List<Product_variant> selectByIDProduct_Product_variant(String IDProduct) {
        return _IProduct_Variant_Service.selectByIDProduct(IDProduct);
    }

    @Override
    public List<Product_options> selectAllProduct_Options() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Product_options> selectByIDProduct_Options(String IDProduct) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Product_options findByIdProduct_Options(String IDProduct) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void insertProduct_Options(Product_options product_options) {
        _IProduct_Options_Service.insert(product_options);
    }

    @Override
    public void updateProduct_Options(Product_options product_options) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void deleteProduct_Options(String IDProduct) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Variant_values> selectAllVariantValues() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Variant_values> selectByIDVariantValues(String IDProduct) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Variant_values findByIdVariantValues(String IDProduct) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void insertVariantValues(Variant_values variant_values) {
        _IVariant_Values_Service.insert(variant_values);
    }

    @Override
    public void updateVariantValues(Variant_values variant_values) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void deleteVariantValues(Variant_values variant_values) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
