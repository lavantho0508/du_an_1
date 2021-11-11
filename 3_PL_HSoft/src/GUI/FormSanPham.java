/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Helper.DialogHelper;
import Helper.UtilityHelper;
import IServices.IQLySanPhamService;
import Models.Options;
import Models.Options_values;
import Models.Product;
import Models.Product_options;
import Models.Product_variant;
import Models.Variant_Values2;
import Models.Variant_values;
import Sevices.QLySanPham_Service;
import java.awt.Color;
import java.awt.FileDialog;
import java.awt.Frame;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import javax.imageio.ImageIO;
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author LENOVO
 */
public class FormSanPham extends javax.swing.JDialog {

    IQLySanPhamService _IQLySanPhamService = (IQLySanPhamService) new QLySanPham_Service();
    JComboBox _cbx;
    JComboBox _cbx2;
    int _rowProduct = -1;
    int _rowOptions = -1;
    int _rowOptionValues = -1;
    int _rowProductVariant = -1;
    int _thuocTinhs = 0;
    List<String> _cbbTT = new ArrayList<>();
    List<String> _cbbGTTT = new ArrayList<>();
    File _file;
    FileDialog _fd = new FileDialog(new Frame(), "Chọn ảnh cho sản phẩm", FileDialog.LOAD);
    String _images = null;

    public FormSanPham(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        init();
    }

    public void init() {
        setLocationRelativeTo(null);
        ButtonGroup bgProduct = new ButtonGroup();
        ButtonGroup bgOptions = new ButtonGroup();
        ButtonGroup bgOptionValues = new ButtonGroup();
        ButtonGroup bgProductVariant = new ButtonGroup();
        ButtonGroup bgVariantValues = new ButtonGroup();
        bgProduct.add(rdb_0_Product);
        bgProduct.add(rdb_1_Product);
        bgOptions.add(rdb_0_Options);
        bgOptions.add(rdb_1_Options);
        bgOptionValues.add(rdb_0_OptionValues);
        bgOptionValues.add(rdb_1_OptionValues);
        bgProductVariant.add(rdb_0_ProductVariant);
        bgProductVariant.add(rdb_1_ProductVariant);
        bgVariantValues.add(rdb_0_variantValues);
        bgVariantValues.add(rdb_1_variantValues);
        rdb_0_Product.setSelected(true);
        rdb_0_Options.setSelected(true);
        rdb_0_OptionValues.setSelected(true);
        rdb_0_ProductVariant.setSelected(true);
        rdb_0_variantValues.setSelected(true);
//        ChuyenManHinh control = new ChuyenManHinh(pnlbang);
//        control.setView(pnlTrangChu, lblTrangChu);
        this.fillTableDanhMuc();
        this.fillComboBoxProduct();
        this.selectCbbProduct();
        this.selectCbbProduct2();
        this.fillTableOption();
        this.fillComboBoxOption();
        this.selectCbbOptions();
        this.actionOptions();

    }

    public void fillComboBoxProduct() {
        DefaultComboBoxModel model = (DefaultComboBoxModel) cbb_Product.getModel();
        DefaultComboBoxModel model2 = (DefaultComboBoxModel) cbb_Product1.getModel();
        model.removeAllElements();
        model2.removeAllElements();
        try {
            List<Product> list = _IQLySanPhamService.selectAllProduct();
            cbb_Product.addItem("Tất cả");
            for (Product models : list) {
                if (models.getTrangThai() == 0) {
                    model.addElement(models);
                    model2.addElement(models);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            DialogHelper.alert(this, "Lỗi truy vấn dữ liệu");
        }
    }

    public void fillComboBoxOption() {
        DefaultComboBoxModel model = (DefaultComboBoxModel) cbb_option.getModel();
        model.removeAllElements();
        try {
            List<Options> list = _IQLySanPhamService.selectAllOptions();
            for (Options models : list) {
                if (models.getTRANGTHAI() == 0) {
                    model.addElement(models);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            DialogHelper.alert(this, "Lỗi truy vấn dữ liệu");
        }
    }

    public void fillComboBoxIDVariant_ProductVarint() {
        DefaultComboBoxModel model = (DefaultComboBoxModel) cbb_IDVariant.getModel();
        model.removeAllElements();
        try {
            Product product = (Product) cbb_Product.getSelectedItem();
            List<Product_variant> list = _IQLySanPhamService.selectByIDProduct_Product_variant(product.getID_Product());
            for (Product_variant models : list) {
                if (models.getTRANGTHAI() == 0) {
                    model.addElement(models);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            DialogHelper.alert(this, "Lỗi truy vấn dữ liệu");
        }
    }

    public void fillComboBoxAllProductVarint() {
        DefaultComboBoxModel model = (DefaultComboBoxModel) cbb_IDVariant.getModel();
        model.removeAllElements();
        try {
//            Product product = (Product) cbb_Product.getSelectedItem();
            List<Product_variant> list = _IQLySanPhamService.selectAllProduct_variant();
            for (Product_variant models : list) {
                if (models.getTRANGTHAI() == 0) {
                    model.addElement(models);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            DialogHelper.alert(this, "Lỗi truy vấn dữ liệu");
        }
    }

    public void selectCbbProduct() {
        if (cbb_Product.getSelectedIndex() == 0) {
            this.fillTableVariantValues();
            this.fillComboBoxAllProductVarint();
        } else {
            Product models = (Product) cbb_Product.getSelectedItem();
            if (models == null) {
                return;
            }
            fillTableVariantValuesByID();
            fillComboBoxIDVariant_ProductVarint();
        }
    }

    public void selectCbbProduct2() {
        Product models = (Product) cbb_Product1.getSelectedItem();
        if (models == null) {
            return;
        }
        fillTableProductVarintByID();

    }

    public void selectCbbOptions() {
        Options models = (Options) cbb_option.getSelectedItem();
        if (models == null) {
            return;
        }
        fillTableOptionValuesByID();
    }

    public void fillTableVariantValues() {
        DefaultTableModel model = (DefaultTableModel) tbl_Variant_values2.getModel();
        model.setRowCount(0);
        try {
//            Product models = (Product) cbb_Product.getSelectedItem();
            List<Variant_Values2> list = _IQLySanPhamService.selectAllVariant_Values2();
            for (Variant_Values2 values2 : list) {
                Object[] row = {
                    values2.getNames(),
                    values2.getID_Variant(),
                    values2.getSKU_ID(),
                    values2.getThuocTinh(),
                    values2.getQuantity(),
                    values2.getPrice(),
                    values2.getTrangThai() == 0 ? "Còn hàng" : "Hết hàng"
                };
                model.addRow(row);
            }
        } catch (Exception e) {
            e.printStackTrace();
            Helper.DialogHelper.alert(this, "Lỗi truy vấn dữ liệu!");
        }

    }

    public void fillTableVariantValuesByID() {
        DefaultTableModel model = (DefaultTableModel) tbl_Variant_values2.getModel();
        model.setRowCount(0);
        try {
            Product models = (Product) cbb_Product.getSelectedItem();
            List<Variant_Values2> list = _IQLySanPhamService.selectByIDVariant_Values2(models.getID_Product());
            for (Variant_Values2 values2 : list) {
                Object[] row = {
                    values2.getNames(),
                    values2.getID_Variant(),
                    values2.getSKU_ID(),
                    values2.getThuocTinh(),
                    values2.getQuantity(),
                    values2.getPrice(),
                    values2.getTrangThai() == 0 ? "Còn hàng" : "Hết hàng"
                };
                model.addRow(row);
            }
        } catch (Exception e) {
            e.printStackTrace();
            Helper.DialogHelper.alert(this, "Lỗi truy vấn dữ liệu!");
        }

    }

    public void fillTableDanhMuc() {
        DefaultTableModel model = (DefaultTableModel) tbl_Product.getModel();
        model.setRowCount(0);
        try {
//            Product models = (Product) cbb_Product.getSelectedItem();
            List<Product> list = _IQLySanPhamService.selectAllProduct();
            for (Product product : list) {
                Object[] row = {
                    product.getID_Product(),
                    product.getNames(),
                    product.getTrangThai() == 0 ? "Đang kinh doanh" : "Ngừng kinh doanh"
                };
                model.addRow(row);
            }
        } catch (Exception e) {
            e.printStackTrace();
            Helper.DialogHelper.alert(this, "Lỗi truy vấn dữ liệu!");
        }

    }

    public void fillTableOption() {
        DefaultTableModel model = (DefaultTableModel) tbl_option.getModel();
        model.setRowCount(0);
        try {
//            Product models = (Product) cbb_Product.getSelectedItem();
            List<Options> list = _IQLySanPhamService.selectAllOptions();
            for (Options options : list) {
                Object[] row = {
                    options.getID_Options(),
                    options.getNames(),
                    options.getTRANGTHAI() == 0 ? "Hoạt động" : "Không hoạt động"
                };
                model.addRow(row);
            }
        } catch (Exception e) {
            e.printStackTrace();
            Helper.DialogHelper.alert(this, "Lỗi truy vấn dữ liệu!");
        }

    }

    public void fillTableOptionValuesByID() {
        DefaultTableModel model = (DefaultTableModel) tbl_OptionValues.getModel();
        model.setRowCount(0);
        try {
            Options models = (Options) cbb_option.getSelectedItem();
            List<Options_values> list = _IQLySanPhamService.selectByIDOptions_values(models.getID_Options());
            for (Options_values options_values : list) {
                Object[] row = {
                    options_values.getID_values(),
                    options_values.getNames(),
                    options_values.getTRANGTHAI() == 0 ? "Hoạt động" : "Không hoạt động"
                };
                model.addRow(row);
            }
        } catch (Exception e) {
            e.printStackTrace();
            Helper.DialogHelper.alert(this, "Lỗi truy vấn dữ liệu!");
        }

    }

    public void fillTableProductVarintByID() {
        DefaultTableModel model = (DefaultTableModel) tbl_ProductVariant.getModel();
        model.setRowCount(0);
        try {
            Product models = (Product) cbb_Product1.getSelectedItem();
            List<Product_variant> list = _IQLySanPhamService.selectByIDProduct_Product_variant(models.getID_Product());
            for (Product_variant product_variant : list) {
                Object[] row = {
                    product_variant.getID_variant(),
                    product_variant.getSKU_ID(),
                    product_variant.getTRANGTHAI() == 0 ? "Còn hàng" : "Hết hàng"
                };
                model.addRow(row);
            }
        } catch (Exception e) {
            e.printStackTrace();
            Helper.DialogHelper.alert(this, "Lỗi truy vấn dữ liệu!");
        }

    }

    public void setFormProduct(Product product) {
        txt_IDDanhMuc.setText(product.getID_Product());
        txt_NameDanhMuc.setText(product.getNames());
        if (product.getTrangThai() == 0) {
            rdb_0_Product.setSelected(true);
        } else {
            rdb_1_Product.setSelected(true);
        }
    }

    public void setFormOptions(Options options) {
        txt_IDOptions.setText(options.getID_Options());
        txt_NamesOptions.setText(options.getNames());
        if (options.getTRANGTHAI() == 0) {
            rdb_0_Options.setSelected(true);
        } else {
            rdb_1_Options.setSelected(true);
        }
    }

    public void setFormOptionValues(Options_values options_values) {
        txt_IDOptionValues.setText(options_values.getID_values());
        txt_NamesOptionValues.setText(options_values.getNames());
        if (options_values.getTRANGTHAI() == 0) {
            rdb_0_OptionValues.setSelected(true);
        } else {
            rdb_1_OptionValues.setSelected(true);
        }
    }

    public void setFormProductVariant(Product_variant product_variant) {
        txt_IDVariant_ProductVariant.setText(product_variant.getID_variant());
        txt_SKUID_ProductVariant.setText(product_variant.getSKU_ID());
        if (product_variant.getTRANGTHAI() == 0) {
            rdb_0_ProductVariant.setSelected(true);
        } else {
            rdb_1_ProductVariant.setSelected(true);
        }
    }

    public Product getFormProduct() {
        Product product = new Product();
        product.setID_Product(txt_IDDanhMuc.getText());
        product.setNames(txt_NameDanhMuc.getText());
        if (rdb_0_Product.isSelected()) {
            product.setTrangThai(0);
        } else {
            product.setTrangThai(1);
        }
        return product;
    }

    public Options getFormOptions() {
        Options options = new Options();
        options.setID_Options(txt_IDOptions.getText());
        options.setNames(txt_NamesOptions.getText());
        if (rdb_0_Options.isSelected()) {
            options.setTRANGTHAI(0);
        } else {
            options.setTRANGTHAI(1);
        }
        return options;
    }

    public Options_values getFormOptionValues() {
        Options options = (Options) cbb_option.getSelectedItem();
        Options_values options_values = new Options_values();
        options_values.setID_Options(options.getID_Options());
        options_values.setID_values(txt_IDOptionValues.getText());
        options_values.setNames(txt_NamesOptionValues.getText());
        if (rdb_0_OptionValues.isSelected()) {
            options_values.setTRANGTHAI(0);
        } else {
            options_values.setTRANGTHAI(1);
        }
        return options_values;
    }

    public Product_variant getFormProductVariant() {
        Product product = (Product) cbb_Product1.getSelectedItem();
        Product_variant product_variant = new Product_variant();
        product_variant.setID_Product(product.getID_Product());
        product_variant.setID_variant(txt_IDVariant_ProductVariant.getText());
        product_variant.setSKU_ID(txt_SKUID_ProductVariant.getText());
        if (rdb_0_ProductVariant.isSelected()) {
            product_variant.setTRANGTHAI(0);
        } else {
            product_variant.setTRANGTHAI(1);
        }
        return product_variant;
    }

    public Product_options getFormProductOptions() {
        Product product = (Product) cbb_Product.getSelectedItem();
        Product_options product_options = new Product_options();
        List<Object> list = new ArrayList<>();
        for (int i = 0; i < _cbbTT.size(); i++) {
            Options out = _IQLySanPhamService.findByNamesOptions(_cbbTT.get(i));
            System.out.println(out.getID_Options());
            product_options.setID_Product(product.getID_Product());
            product_options.setID_Options(out.getID_Options());
            if (rdb_0_variantValues.isSelected()) {
                product_options.setTRANGTHAI(0);
            } else {
                product_options.setTRANGTHAI(1);
            }
            list.add(product_options);
        }

        return product_options;
    }

    public Variant_values getFormVariantValues() {
        Product product = (Product) cbb_Product.getSelectedItem();
        Product_variant product_variant = (Product_variant) cbb_IDVariant.getSelectedItem();
        Variant_values variant_values = new Variant_values();

        for (int i = 0; i < _cbbTT.size(); i++) {
            Options options = _IQLySanPhamService.findByNamesOptions(_cbbTT.get(i));
            Options_values options_values = _IQLySanPhamService.findByNamesOptions_values(_cbbGTTT.get(i));
            variant_values.setID_Product(product.getID_Product());
            variant_values.setID_variant(product_variant.getID_variant());
            variant_values.setID_Options(options.getID_Options());
            variant_values.setID_values(options_values.getID_values());
            variant_values.setQuantity(Integer.parseInt(txt_Quarity.getText()));
            variant_values.setPrice(Double.parseDouble(txt_Price.getText()));
            variant_values.setBarcode(txt_barcode.getText());
            variant_values.setImages(_images);
            if (rdb_0_variantValues.isSelected()) {
                variant_values.setTRANGTHAI(0);
            } else {
                variant_values.setTRANGTHAI(1);
            }
        }

        return variant_values;
    }

    public void editProduct() {
        try {
            String idProduct = String.valueOf(tbl_Product.getValueAt(this._rowProduct, 0));
            Product model = _IQLySanPhamService.findByIdProduct(idProduct);
            if (model != null) {
                this.setFormProduct(model);
//                this.setStatus(false);
            }
        } catch (Exception e) {
            e.printStackTrace();
            DialogHelper.alert(this, "Lỗi truy vấn dữ liệu!");
        }
    }

    public void editOptions() {
        try {
            String idOptions = String.valueOf(tbl_option.getValueAt(this._rowOptions, 0));
            Options model = _IQLySanPhamService.findByIdOptions(idOptions);
            if (model != null) {
                this.setFormOptions(model);
//                this.setStatus(false);
            }
        } catch (Exception e) {
            e.printStackTrace();
            DialogHelper.alert(this, "Lỗi truy vấn dữ liệu!");
        }
    }

    public void editOptionValues() {
        try {
            String idOptionValues = String.valueOf(tbl_OptionValues.getValueAt(this._rowOptionValues, 0));
            Options_values model = _IQLySanPhamService.findByIdOptions_values(idOptionValues);
            if (model != null) {
                this.setFormOptionValues(model);
//                this.setStatus(false);
            }
        } catch (Exception e) {
            e.printStackTrace();
            DialogHelper.alert(this, "Lỗi truy vấn dữ liệu!");
        }
    }

    public void editProductVariant() {
        try {
            String idVariant = String.valueOf(tbl_ProductVariant.getValueAt(this._rowProductVariant, 0));
            Product_variant model = _IQLySanPhamService.findByIdProduct_variant(idVariant);
            if (model != null) {
                this.setFormProductVariant(model);
//                this.setStatus(false);
            }
        } catch (Exception e) {
            e.printStackTrace();
            DialogHelper.alert(this, "Lỗi truy vấn dữ liệu!");
        }
    }

    public void insertProduct() {
        Product product = getFormProduct();
        try {
            _IQLySanPhamService.insertProduct(product);
            this.fillTableDanhMuc();
            this.fillComboBoxProduct();
//            this.viewSoTrang();
            this.clearFormProduct();
//            tabs.setSelectedIndex(1);
            Helper.DialogHelper.alert(this, "Thêm mới thành công!");
        } catch (Exception e) {
            e.printStackTrace();
            Helper.DialogHelper.alert(this, "Thêm mới thất bại!");
        }
    }

    public void insertOptions() {
        Options options = getFormOptions();
        try {
            _IQLySanPhamService.insertOptions(options);
            this.fillTableOption();
            this.fillComboBoxOption();
//            this.viewSoTrang();
            this.clearFormOptions();
//            tabs.setSelectedIndex(1);
            Helper.DialogHelper.alert(this, "Thêm mới thành công!");
        } catch (Exception e) {
            e.printStackTrace();
            Helper.DialogHelper.alert(this, "Thêm mới thất bại!");
        }
    }

    public void insertOptionValues() {
        Options_values options_values = getFormOptionValues();
        try {
            _IQLySanPhamService.insertOptions_values(options_values);
            this.fillTableOptionValuesByID();
//            this.fillComboBoxOption();
//            this.viewSoTrang();
            this.clearFormOptionValues();
//            tabs.setSelectedIndex(1);
            Helper.DialogHelper.alert(this, "Thêm mới thành công!");
        } catch (Exception e) {
            e.printStackTrace();
            Helper.DialogHelper.alert(this, "Thêm mới thất bại!");
        }
    }

    public void insertProductVariant() {
        Product_variant product_variant = getFormProductVariant();
        try {
            _IQLySanPhamService.insertProduct_variant(product_variant);
            this.fillTableProductVarintByID();
            this.fillTableVariantValuesByID();
            this.fillComboBoxIDVariant_ProductVarint();
//            this.viewSoTrang();
            this.clearFormProductVariant();
//            tabs.setSelectedIndex(1);
            Helper.DialogHelper.alert(this, "Thêm mới thành công!");
        } catch (Exception e) {
            e.printStackTrace();
            Helper.DialogHelper.alert(this, "Thêm mới thất bại!");
        }
    }

    public void insertProductOptions() {
//        Product_options product_options = getFormProductOptions();
        Product product = (Product) cbb_Product.getSelectedItem();
        Product_options product_options = new Product_options();
        try {
            for (int i = 0; i < _cbbTT.size(); i++) {
                Options out = _IQLySanPhamService.findByNamesOptions(_cbbTT.get(i));
                System.out.println(out.getID_Options());
                product_options.setID_Product(product.getID_Product());
                product_options.setID_Options(out.getID_Options());
                if (rdb_0_variantValues.isSelected()) {
                    product_options.setTRANGTHAI(0);
                } else {
                    product_options.setTRANGTHAI(1);
                }

//            for (int i = 0; i < _cbbTT.size(); i++) {
                _IQLySanPhamService.insertProduct_Options(product_options);
//            }
//            this.fillTableProductVarintByID();
//            this.fillTableVariantValuesByID();
//            this.fillComboBoxIDVariant_ProductVarint();
////            this.viewSoTrang();
//            this.clearFormProductVariant();
//            tabs.setSelectedIndex(1);

            }
            Helper.DialogHelper.alert(this, "Thêm mới thành công!");
        } catch (Exception e) {
            e.printStackTrace();
            Helper.DialogHelper.alert(this, "Thêm mới thất bại!");
        }
    }

    public void insertVarianValues() {
//        Variant_values variant_values = getFormVariantValues();
        try {
            Product product = (Product) cbb_Product.getSelectedItem();
            Product_variant product_variant = (Product_variant) cbb_IDVariant.getSelectedItem();
            Variant_values variant_values = new Variant_values();

            for (int i = 0; i < _cbbTT.size(); i++) {
                Options options = _IQLySanPhamService.findByNamesOptions(_cbbTT.get(i));
                Options_values options_values = _IQLySanPhamService.findByNamesOptions_values(_cbbGTTT.get(i));
                variant_values.setID_Product(product.getID_Product());
                variant_values.setID_variant(product_variant.getID_variant());
                variant_values.setID_Options(options.getID_Options());
                variant_values.setID_values(options_values.getID_values());
                variant_values.setQuantity(Integer.parseInt(txt_Quarity.getText()));
                variant_values.setPrice(Double.parseDouble(txt_Price.getText()));
                variant_values.setBarcode(txt_barcode.getText());
                variant_values.setImages(_images);
                if (rdb_0_variantValues.isSelected()) {
                    variant_values.setTRANGTHAI(0);
                } else {
                    variant_values.setTRANGTHAI(1);
                }
                _IQLySanPhamService.insertVariantValues(variant_values);
            }

//            for (int i = 0; i < _cbbTT.size(); i++) {
//            }
//            this.fillTableProductVarintByID();
//            this.fillTableVariantValuesByID();
//            this.fillComboBoxIDVariant_ProductVarint();
////            this.viewSoTrang();
//            this.clearFormProductVariant();
//            tabs.setSelectedIndex(1);
            Helper.DialogHelper.alert(this, "Thêm mới thành công!");
        } catch (Exception e) {
            e.printStackTrace();
            Helper.DialogHelper.alert(this, "Thêm mới thất bại!");
        }
    }

    public void updateProduct() {

        Product product = getFormProduct();
        if (Helper.DialogHelper.confirm(this, "Bạn thực sự muốn sửa danh mục này?")) {
            try {
                _IQLySanPhamService.updateProduct(product);
//            this.fillTable(_trang);
//            this.viewSoTrang();
//            tabs.setSelectedIndex(1);
                this.fillTableDanhMuc();
                this.fillComboBoxProduct();
                Helper.DialogHelper.alert(this, "Cập nhật thành công!");
            } catch (Exception e) {
                e.printStackTrace();
                Helper.DialogHelper.alert(this, "Cập nhật thất bại!");
            }
        }
    }

    public void updateOptions() {

        Options options = getFormOptions();
        if (Helper.DialogHelper.confirm(this, "Bạn thực sự muốn sửa thuộc tính này?")) {
            try {
                _IQLySanPhamService.updateOptions(options);
//            this.fillTable(_trang);
//            this.viewSoTrang();
//            tabs.setSelectedIndex(1);
                this.fillTableOption();
                this.fillComboBoxOption();
                Helper.DialogHelper.alert(this, "Cập nhật thành công!");
            } catch (Exception e) {
                e.printStackTrace();
                Helper.DialogHelper.alert(this, "Cập nhật thất bại!");
            }
        }
    }

    public void updateOptionValues() {

        Options_values options_values = getFormOptionValues();
        if (Helper.DialogHelper.confirm(this, "Bạn thực sự muốn sửa giá trị này?")) {
            try {
                _IQLySanPhamService.updateOptions_values(options_values);
//            this.fillTable(_trang);
//            this.viewSoTrang();
//            tabs.setSelectedIndex(1);
                this.fillTableOptionValuesByID();
//                this.fillComboBoxOption();
                Helper.DialogHelper.alert(this, "Cập nhật thành công!");
            } catch (Exception e) {
                e.printStackTrace();
                Helper.DialogHelper.alert(this, "Cập nhật thất bại!");
            }
        }
    }

    public void updateProductVariant() {

        Product_variant product_variant = getFormProductVariant();
        if (Helper.DialogHelper.confirm(this, "Bạn thực sự muốn sửa giá trị này?")) {
            try {
                _IQLySanPhamService.updateProduct_variant(product_variant);
//            this.fillTable(_trang);
//            this.viewSoTrang();
//            tabs.setSelectedIndex(1);
                this.fillTableProductVarintByID();
                this.fillTableVariantValuesByID();
                this.fillComboBoxIDVariant_ProductVarint();
                Helper.DialogHelper.alert(this, "Cập nhật thành công!");
            } catch (Exception e) {
                e.printStackTrace();
                Helper.DialogHelper.alert(this, "Cập nhật thất bại!");
            }
        }
    }

    public void deleteProduct() {
        if (Helper.DialogHelper.confirm(this, "Bạn thực sự muốn xóa danh mục này?")) {
            try {
                _IQLySanPhamService.deleteProduct(txt_IDDanhMuc.getText());   //xóa nhân viên theo maNV
//                this.fillTable(_trang);//điền tt mới vào bảng
//                this.viewSoTrang();
                this.clearFormProduct();//xóa trắng form và chỉnh lại status
                this.fillTableDanhMuc();
                this.fillComboBoxProduct();
                Helper.DialogHelper.alert(this, "Xóa thành công!");
//                tabs.setSelectedIndex(1);
            } catch (Exception e) {
                e.printStackTrace();
                Helper.DialogHelper.alert(this, "Xóa thất bại!");
            }
        }
    }

    public void deleteOptions() {
        if (Helper.DialogHelper.confirm(this, "Bạn thực sự muốn xóa thuộc tính này?")) {
            try {
                _IQLySanPhamService.deleteOptions(txt_IDOptions.getText());   //xóa nhân viên theo maNV
//                this.fillTable(_trang);//điền tt mới vào bảng
//                this.viewSoTrang();
                this.clearFormOptions();//xóa trắng form và chỉnh lại status
                this.fillTableOption();
                this.fillComboBoxOption();
                Helper.DialogHelper.alert(this, "Xóa thành công!");
//                tabs.setSelectedIndex(1);
            } catch (Exception e) {
                e.printStackTrace();
                Helper.DialogHelper.alert(this, "Xóa thất bại!");
            }
        }
    }

    public void deleteOptionValues() {
        Options options = (Options) cbb_option.getSelectedItem();
        if (Helper.DialogHelper.confirm(this, "Bạn thực sự muốn xóa giá trị này?")) {
            try {
                _IQLySanPhamService.deleteOptions_values(options.getID_Options(), txt_IDOptionValues.getText());   //xóa nhân viên theo maNV
//                this.fillTable(_trang);//điền tt mới vào bảng
//                this.viewSoTrang();
                this.clearFormOptionValues();//xóa trắng form và chỉnh lại status
                this.fillTableOptionValuesByID();
//                this.fillComboBoxOption();
                Helper.DialogHelper.alert(this, "Xóa thành công!");
//                tabs.setSelectedIndex(1);
            } catch (Exception e) {
                e.printStackTrace();
                Helper.DialogHelper.alert(this, "Xóa thất bại!");
            }
        }
    }

    public void deleteProductVariant() {
        Product product = (Product) cbb_Product1.getSelectedItem();
        if (Helper.DialogHelper.confirm(this, "Bạn thực sự muốn xóa giá trị này?")) {
            try {
                _IQLySanPhamService.deleteProduct_variant(product.getID_Product(), txt_IDVariant_ProductVariant.getText());
//                this.fillTable(_trang);//điền tt mới vào bảng
//                this.viewSoTrang();
                this.clearFormProductVariant();//xóa trắng form và chỉnh lại status
                this.fillTableProductVarintByID();
                this.fillTableVariantValuesByID();
                this.fillComboBoxIDVariant_ProductVarint();
                Helper.DialogHelper.alert(this, "Xóa thành công!");
//                tabs.setSelectedIndex(1);
            } catch (Exception e) {
                e.printStackTrace();
                Helper.DialogHelper.alert(this, "Xóa thất bại!");
            }
        }
    }

    public void clearFormProduct() {
        Product product = new Product();
        this.setFormProduct(product);
//        this.updateStatus(true);
    }

    public void clearFormOptions() {
        Options options = new Options();
        this.setFormOptions(options);
    }

    public void clearFormOptionValues() {
        Options_values options_values = new Options_values();
        this.setFormOptionValues(options_values);
    }

    public void clearFormProductVariant() {
        Product_variant product_variant = new Product_variant();
        this.setFormProductVariant(product_variant);
    }

    public boolean checkIDProduct(String ID) {
        if (_IQLySanPhamService.findByIdProduct(ID) == null) {
            return true;
        } else {
            Helper.DialogHelper.alert(this, "Mã " + ID + " đã tồn tại !");
            return false;
        }
    }

    public boolean checkIDOptions(String ID) {
        if (_IQLySanPhamService.findByIdOptions(ID) == null) {
            return true;
        } else {
            Helper.DialogHelper.alert(this, "Mã " + ID + " đã tồn tại !");
            return false;
        }
    }

    public boolean checkIDOptionValues(String ID) {
        if (_IQLySanPhamService.findByIdOptions_values(ID) == null) {
            return true;
        } else {
            Helper.DialogHelper.alert(this, "Mã " + ID + " đã tồn tại !");
            return false;
        }
    }

    public boolean checkIDProductVariant(String ID) {
        if (_IQLySanPhamService.findByIdProduct_variant(ID) == null) {
            return true;
        } else {
            Helper.DialogHelper.alert(this, "Mã " + ID + " đã tồn tại !");
            return false;
        }
    }

    public boolean checkNamesProduct(String Names) {
        if (_IQLySanPhamService.findByNamesProduct(Names) == null) {
            return true;
        } else {
            Helper.DialogHelper.alert(this, "Tên " + Names + " đã tồn tại !");
            return false;
        }
    }

    public boolean checkNamesOptions(String Names) {
        if (_IQLySanPhamService.findByNamesOptions(Names) == null) {
            return true;
        } else {
            Helper.DialogHelper.alert(this, "Tên " + Names + " đã tồn tại !");
            return false;
        }
    }

    public boolean checkNamesOptionValues(String Names) {
        if (_IQLySanPhamService.findByNamesOptions_values(Names) == null) {
            return true;
        } else {
            Helper.DialogHelper.alert(this, "Tên " + Names + " đã tồn tại !");
            return false;
        }
    }

    public boolean checkNamesProductVariant(String Names) {
        if (_IQLySanPhamService.findByNamesProduct_variant(Names) == null) {
            return true;
        } else {
            Helper.DialogHelper.alert(this, "Tên " + Names + " đã tồn tại !");
            return false;
        }
    }

    public void loadCBBOptionValues(JComboBox jcb) {
        DefaultComboBoxModel cbbOptionValues = (DefaultComboBoxModel) jcb.getModel();
        cbbOptionValues.removeAllElements();
        try {
            Options optionss = (Options) _cbx.getSelectedItem();
            List<Options_values> list = _IQLySanPhamService.selectByIDOptions_values(optionss.getID_Options());
            for (Options_values options_values : list) {
                if (options_values.getTRANGTHAI() == 0) {
                    cbbOptionValues.addElement(options_values);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            DialogHelper.alert(this, "Lỗi truy vấn dữ liệu");
        }
    }

    public void actionOptions() {
        _thuocTinhs++;
        _cbx = new JComboBox();
        _cbx2 = new JComboBox();
        _cbx.setSize(100, 30);
        _cbx.setLocation(5, 0 + ((_thuocTinhs - 1) * 50));
        _cbx2.setSize(150, 30);
        _cbx2.setLocation(100, 0 + ((_thuocTinhs - 1) * 50));
        Panel_ThuocTinhs.add(_cbx);
        Panel_ThuocTinhs.add(_cbx2);
        DefaultComboBoxModel cbbOptions = (DefaultComboBoxModel) _cbx.getModel();
        cbbOptions.removeAllElements();
        try {
            List<Options> list = _IQLySanPhamService.selectAllOptions();
            for (Options options : list) {
                if (options.getTRANGTHAI() == 0) {
                    cbbOptions.addElement(options);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            DialogHelper.alert(this, "Lỗi truy vấn dữ liệu");
        }
        loadCBBOptionValues(_cbx2);

        _cbx.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loadCBBOptionValues(_cbx2);
            }
        });

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        pnlSide = new javax.swing.JPanel();
        pnlTrangChu = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        lblTrangChu = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        jPanel6 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        lblDanhMuc = new javax.swing.JLabel();
        jSeparator3 = new javax.swing.JSeparator();
        jPanel11 = new javax.swing.JPanel();
        jLabel15 = new javax.swing.JLabel();
        lblThuocTinh = new javax.swing.JLabel();
        jPanel13 = new javax.swing.JPanel();
        jLabel26 = new javax.swing.JLabel();
        lblLoaiSP = new javax.swing.JLabel();
        jSeparator5 = new javax.swing.JSeparator();
        jLabel1 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jSeparator4 = new javax.swing.JSeparator();
        tabs_QLSP = new javax.swing.JTabbedPane();
        jPanel2 = new javax.swing.JPanel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel8 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbl_Variant_values2 = new javax.swing.JTable();
        jPanel9 = new javax.swing.JPanel();
        jLabel16 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        btn_insertVariantValues = new javax.swing.JButton();
        btn_updateVariantValues = new javax.swing.JButton();
        btn_deleteVariantValues = new javax.swing.JButton();
        btn_newVariantValues = new javax.swing.JButton();
        jLabel21 = new javax.swing.JLabel();
        btn_autoBarCode = new javax.swing.JButton();
        jPanel10 = new javax.swing.JPanel();
        btn_addOptions = new javax.swing.JButton();
        SrollPane_Options = new javax.swing.JScrollPane();
        Panel_ThuocTinhs = new javax.swing.JPanel();
        btn_saveOptions = new javax.swing.JButton();
        jLabel27 = new javax.swing.JLabel();
        txt_Quarity = new javax.swing.JTextField();
        txt_Price = new javax.swing.JTextField();
        txt_barcode = new javax.swing.JTextField();
        rdb_0_variantValues = new javax.swing.JRadioButton();
        rdb_1_variantValues = new javax.swing.JRadioButton();
        cbb_IDVariant = new javax.swing.JComboBox<>();
        jPanel17 = new javax.swing.JPanel();
        lbl_images = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        cbb_Product = new javax.swing.JComboBox<>();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbl_Product = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txt_IDDanhMuc = new javax.swing.JTextField();
        txt_NameDanhMuc = new javax.swing.JTextField();
        insertProduct = new javax.swing.JButton();
        btn_updateProduct = new javax.swing.JButton();
        btn_deleteProduct = new javax.swing.JButton();
        btn_newProduct = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        rdb_0_Product = new javax.swing.JRadioButton();
        rdb_1_Product = new javax.swing.JRadioButton();
        jPanel12 = new javax.swing.JPanel();
        jLabel22 = new javax.swing.JLabel();
        cbb_Product1 = new javax.swing.JComboBox<>();
        jScrollPane5 = new javax.swing.JScrollPane();
        tbl_ProductVariant = new javax.swing.JTable();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        txt_IDVariant_ProductVariant = new javax.swing.JTextField();
        txt_SKUID_ProductVariant = new javax.swing.JTextField();
        rdb_0_ProductVariant = new javax.swing.JRadioButton();
        rdb_1_ProductVariant = new javax.swing.JRadioButton();
        btn_insertProductVariant = new javax.swing.JButton();
        btn_updateProductVariant = new javax.swing.JButton();
        btn_deleteProduct1 = new javax.swing.JButton();
        btn_newProduct1 = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tbl_option = new javax.swing.JTable();
        btn_insertOptions = new javax.swing.JButton();
        btn_updateOptions = new javax.swing.JButton();
        btn_deleteOptions = new javax.swing.JButton();
        btn_newOptions = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        txt_IDOptions = new javax.swing.JTextField();
        txt_NamesOptions = new javax.swing.JTextField();
        rdb_0_Options = new javax.swing.JRadioButton();
        rdb_1_Options = new javax.swing.JRadioButton();
        jPanel7 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        cbb_option = new javax.swing.JComboBox<>();
        jScrollPane4 = new javax.swing.JScrollPane();
        tbl_OptionValues = new javax.swing.JTable();
        jLabel12 = new javax.swing.JLabel();
        txt_IDOptionValues = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        txt_NamesOptionValues = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        rdb_0_OptionValues = new javax.swing.JRadioButton();
        rdb_1_OptionValues = new javax.swing.JRadioButton();
        btn_insertOptionValues = new javax.swing.JButton();
        btn_updateOptionValues = new javax.swing.JButton();
        btn_deleteOptionValues = new javax.swing.JButton();
        btn_newOptionValues = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        pnlSide.setBackground(new java.awt.Color(255, 102, 102));

        pnlTrangChu.setBackground(new java.awt.Color(255, 102, 102));
        pnlTrangChu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                pnlTrangChuMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                pnlTrangChuMouseExited(evt);
            }
        });

        lblTrangChu.setBackground(new java.awt.Color(74, 31, 61));
        lblTrangChu.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        lblTrangChu.setForeground(new java.awt.Color(255, 255, 255));
        lblTrangChu.setText("   Danh sách sản phẩm");
        lblTrangChu.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lblTrangChu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblTrangChuMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lblTrangChuMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lblTrangChuMouseExited(evt);
            }
        });

        jSeparator2.setForeground(new java.awt.Color(240, 240, 240));

        jPanel6.setBackground(new java.awt.Color(255, 102, 102));

        lblDanhMuc.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        lblDanhMuc.setForeground(new java.awt.Color(255, 255, 255));
        lblDanhMuc.setText("   Danh mục");
        lblDanhMuc.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lblDanhMuc.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblDanhMucMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lblDanhMucMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lblDanhMucMouseExited(evt);
            }
        });

        jSeparator3.setForeground(new java.awt.Color(240, 240, 240));

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblDanhMuc, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(26, Short.MAX_VALUE))
            .addComponent(jSeparator3)
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblDanhMuc, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 2, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout pnlTrangChuLayout = new javax.swing.GroupLayout(pnlTrangChu);
        pnlTrangChu.setLayout(pnlTrangChuLayout);
        pnlTrangChuLayout.setHorizontalGroup(
            pnlTrangChuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTrangChuLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlTrangChuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jSeparator2)
                    .addGroup(pnlTrangChuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(pnlTrangChuLayout.createSequentialGroup()
                            .addComponent(jLabel3)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(lblTrangChu, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(29, Short.MAX_VALUE))
        );
        pnlTrangChuLayout.setVerticalGroup(
            pnlTrangChuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTrangChuLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlTrangChuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblTrangChu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 4, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel11.setBackground(new java.awt.Color(255, 102, 102));

        lblThuocTinh.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        lblThuocTinh.setForeground(new java.awt.Color(255, 255, 255));
        lblThuocTinh.setText("   Các thuộc tính sản phẩm");
        lblThuocTinh.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lblThuocTinh.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblThuocTinhMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lblThuocTinhMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lblThuocTinhMouseExited(evt);
            }
        });

        jPanel13.setBackground(new java.awt.Color(255, 102, 102));

        lblLoaiSP.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        lblLoaiSP.setForeground(new java.awt.Color(255, 255, 255));
        lblLoaiSP.setText("   Loại sản phẩm");
        lblLoaiSP.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lblLoaiSP.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblLoaiSPMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lblLoaiSPMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lblLoaiSPMouseExited(evt);
            }
        });

        jSeparator5.setForeground(new java.awt.Color(240, 240, 240));

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addComponent(jLabel26)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblLoaiSP, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(26, Short.MAX_VALUE))
            .addComponent(jSeparator5)
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblLoaiSP, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel26, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator5, javax.swing.GroupLayout.PREFERRED_SIZE, 2, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addComponent(jLabel15)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblThuocTinh))
                    .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(17, 17, 17)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblThuocTinh, javax.swing.GroupLayout.DEFAULT_SIZE, 46, Short.MAX_VALUE)
                    .addComponent(jLabel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(5, 5, 5))
        );

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(240, 240, 240));
        jLabel1.setText("Horse Software Team");

        jSeparator1.setForeground(new java.awt.Color(240, 240, 240));

        jSeparator4.setForeground(new java.awt.Color(240, 240, 240));

        javax.swing.GroupLayout pnlSideLayout = new javax.swing.GroupLayout(pnlSide);
        pnlSide.setLayout(pnlSideLayout);
        pnlSideLayout.setHorizontalGroup(
            pnlSideLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlTrangChu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(pnlSideLayout.createSequentialGroup()
                .addGroup(pnlSideLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlSideLayout.createSequentialGroup()
                        .addGap(36, 36, 36)
                        .addGroup(pnlSideLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jSeparator1)
                            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(pnlSideLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, 231, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnlSideLayout.setVerticalGroup(
            pnlSideLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlSideLayout.createSequentialGroup()
                .addGap(73, 73, 73)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(65, 65, 65)
                .addComponent(pnlTrangChu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(64, Short.MAX_VALUE))
        );

        jPanel2.setBackground(new java.awt.Color(255, 204, 204));
        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 204, 204)));

        tbl_Variant_values2.setBackground(new java.awt.Color(255, 204, 204));
        tbl_Variant_values2.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        tbl_Variant_values2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Danh mục", "Loại SP", "Tên SP", "Thông tin SP", "SL", "Giá", "Trạng thái"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbl_Variant_values2.setGridColor(new java.awt.Color(255, 102, 102));
        tbl_Variant_values2.setRowHeight(22);
        tbl_Variant_values2.setSelectionBackground(new java.awt.Color(255, 102, 102));
        jScrollPane1.setViewportView(tbl_Variant_values2);
        if (tbl_Variant_values2.getColumnModel().getColumnCount() > 0) {
            tbl_Variant_values2.getColumnModel().getColumn(0).setMinWidth(70);
            tbl_Variant_values2.getColumnModel().getColumn(0).setMaxWidth(50);
            tbl_Variant_values2.getColumnModel().getColumn(1).setMinWidth(100);
            tbl_Variant_values2.getColumnModel().getColumn(1).setMaxWidth(150);
            tbl_Variant_values2.getColumnModel().getColumn(2).setMinWidth(100);
            tbl_Variant_values2.getColumnModel().getColumn(2).setMaxWidth(150);
            tbl_Variant_values2.getColumnModel().getColumn(3).setMinWidth(270);
            tbl_Variant_values2.getColumnModel().getColumn(4).setMaxWidth(50);
            tbl_Variant_values2.getColumnModel().getColumn(5).setMaxWidth(100);
            tbl_Variant_values2.getColumnModel().getColumn(6).setMaxWidth(70);
        }

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 989, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 364, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Danh sách", jPanel8);

        jPanel9.setBackground(new java.awt.Color(255, 204, 204));

        jLabel16.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jLabel16.setText("Tên SP:");

        jLabel18.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jLabel18.setText("Trạng thái: ");

        jLabel19.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jLabel19.setText("Số lượng: ");

        jLabel20.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jLabel20.setText("Đơn giá: ");

        btn_insertVariantValues.setBackground(new java.awt.Color(11, 181, 217));
        btn_insertVariantValues.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btn_insertVariantValues.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/baseline_add_black_24dp.png"))); // NOI18N
        btn_insertVariantValues.setText("Thêm");
        btn_insertVariantValues.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_insertVariantValuesActionPerformed(evt);
            }
        });

        btn_updateVariantValues.setBackground(new java.awt.Color(11, 181, 217));
        btn_updateVariantValues.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btn_updateVariantValues.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/baseline_sync_alt_black_24dp.png"))); // NOI18N
        btn_updateVariantValues.setText("Sửa");
        btn_updateVariantValues.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_updateVariantValuesActionPerformed(evt);
            }
        });

        btn_deleteVariantValues.setBackground(new java.awt.Color(11, 181, 217));
        btn_deleteVariantValues.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btn_deleteVariantValues.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/baseline_delete_forever_black_24dp.png"))); // NOI18N
        btn_deleteVariantValues.setText("Xóa");
        btn_deleteVariantValues.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_deleteVariantValuesActionPerformed(evt);
            }
        });

        btn_newVariantValues.setBackground(new java.awt.Color(11, 181, 217));
        btn_newVariantValues.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btn_newVariantValues.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/baseline_open_in_new_black_24dp.png"))); // NOI18N
        btn_newVariantValues.setText("Làm mới");
        btn_newVariantValues.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_newVariantValuesActionPerformed(evt);
            }
        });

        jLabel21.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jLabel21.setText("Barcode:");

        btn_autoBarCode.setBackground(new java.awt.Color(11, 181, 217));
        btn_autoBarCode.setText("Zen tự động");
        btn_autoBarCode.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_autoBarCodeActionPerformed(evt);
            }
        });

        jPanel10.setBackground(new java.awt.Color(255, 204, 204));
        jPanel10.setBorder(javax.swing.BorderFactory.createTitledBorder("Thuộc tính & giá trị thuộc tính sản phẩm"));

        btn_addOptions.setBackground(new java.awt.Color(11, 181, 217));
        btn_addOptions.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/baseline_add_black_24dp.png"))); // NOI18N
        btn_addOptions.setText("Thêm thuộc tính");
        btn_addOptions.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_addOptionsActionPerformed(evt);
            }
        });

        Panel_ThuocTinhs.setBackground(new java.awt.Color(255, 153, 153));
        Panel_ThuocTinhs.setPreferredSize(new java.awt.Dimension(32767, 32767));

        javax.swing.GroupLayout Panel_ThuocTinhsLayout = new javax.swing.GroupLayout(Panel_ThuocTinhs);
        Panel_ThuocTinhs.setLayout(Panel_ThuocTinhsLayout);
        Panel_ThuocTinhsLayout.setHorizontalGroup(
            Panel_ThuocTinhsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, Short.MAX_VALUE, Short.MAX_VALUE)
        );
        Panel_ThuocTinhsLayout.setVerticalGroup(
            Panel_ThuocTinhsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, Short.MAX_VALUE, Short.MAX_VALUE)
        );

        SrollPane_Options.setViewportView(Panel_ThuocTinhs);

        btn_saveOptions.setBackground(new java.awt.Color(11, 181, 217));
        btn_saveOptions.setText("Lưu thuộc tính");
        btn_saveOptions.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_saveOptionsActionPerformed(evt);
            }
        });

        jLabel27.setFont(new java.awt.Font("Tahoma", 2, 11)); // NOI18N
        jLabel27.setText("Bấm ngay lưu thuộc tính sau khi chọn được 1 giá trị");

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addComponent(btn_addOptions)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btn_saveOptions))
                    .addComponent(SrollPane_Options, javax.swing.GroupLayout.PREFERRED_SIZE, 347, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel27, javax.swing.GroupLayout.PREFERRED_SIZE, 252, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel10Layout.createSequentialGroup()
                .addComponent(jLabel27)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_addOptions)
                    .addComponent(btn_saveOptions))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(SrollPane_Options, javax.swing.GroupLayout.DEFAULT_SIZE, 252, Short.MAX_VALUE)
                .addContainerGap())
        );

        txt_Quarity.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N

        txt_Price.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N

        txt_barcode.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N

        rdb_0_variantValues.setText("Đang kinh doanh");

        rdb_1_variantValues.setText("Ngừng kinh doanh");

        cbb_IDVariant.setBackground(new java.awt.Color(255, 102, 102));
        cbb_IDVariant.setEditable(true);
        cbb_IDVariant.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        cbb_IDVariant.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jPanel17.setBackground(new java.awt.Color(255, 153, 153));
        jPanel17.setBorder(javax.swing.BorderFactory.createTitledBorder("Hình ảnh"));

        lbl_images.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbl_imagesMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel17Layout = new javax.swing.GroupLayout(jPanel17);
        jPanel17.setLayout(jPanel17Layout);
        jPanel17Layout.setHorizontalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lbl_images, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 143, Short.MAX_VALUE)
        );
        jPanel17Layout.setVerticalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lbl_images, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 207, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addComponent(jLabel18, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(549, 549, 549))
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel9Layout.createSequentialGroup()
                                .addComponent(btn_insertVariantValues)
                                .addGap(13, 13, 13)
                                .addComponent(btn_updateVariantValues)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btn_deleteVariantValues)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btn_newVariantValues))
                            .addGroup(jPanel9Layout.createSequentialGroup()
                                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel9Layout.createSequentialGroup()
                                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel19)
                                            .addComponent(jLabel20)
                                            .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(cbb_IDVariant, 0, 198, Short.MAX_VALUE)
                                            .addComponent(txt_Quarity)
                                            .addComponent(txt_Price)))
                                    .addComponent(jLabel21)
                                    .addGroup(jPanel9Layout.createSequentialGroup()
                                        .addGap(112, 112, 112)
                                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jPanel9Layout.createSequentialGroup()
                                                .addComponent(rdb_0_variantValues)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(rdb_1_variantValues))
                                            .addGroup(jPanel9Layout.createSequentialGroup()
                                                .addComponent(txt_barcode, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(btn_autoBarCode)))))
                                .addGap(30, 30, 30)
                                .addComponent(jPanel17, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, 374, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(8, 8, 8))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel9Layout.createSequentialGroup()
                                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel16)
                                    .addComponent(cbb_IDVariant, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(27, 27, 27)
                                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel19)
                                    .addComponent(txt_Quarity, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(30, 30, 30)
                                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txt_Price, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(30, 30, 30)
                                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(txt_barcode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(btn_autoBarCode))
                                    .addComponent(jLabel21, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(39, 39, 39)
                                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel18)
                                    .addComponent(rdb_0_variantValues)
                                    .addComponent(rdb_1_variantValues))
                                .addGap(41, 41, 41))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel9Layout.createSequentialGroup()
                                .addComponent(jPanel17, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btn_insertVariantValues)
                            .addComponent(btn_updateVariantValues)
                            .addComponent(btn_deleteVariantValues)
                            .addComponent(btn_newVariantValues))))
                .addContainerGap(18, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Cập nhật", jPanel9);

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(51, 51, 51));
        jLabel7.setText("Danh mục:");

        cbb_Product.setBackground(new java.awt.Color(255, 102, 102));
        cbb_Product.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        cbb_Product.setForeground(new java.awt.Color(51, 51, 51));
        cbb_Product.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cbb_Product.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbb_ProductActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTabbedPane1)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addGap(18, 18, 18)
                        .addComponent(cbb_Product, javax.swing.GroupLayout.PREFERRED_SIZE, 216, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(cbb_Product, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 406, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        tabs_QLSP.addTab("Danh sách sản phẩm", jPanel2);

        jPanel4.setBackground(new java.awt.Color(255, 204, 204));

        tbl_Product.setBackground(new java.awt.Color(255, 204, 204));
        tbl_Product.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã Danh mục", "Tên Danh Mục", "Trạng thái"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbl_Product.setGridColor(new java.awt.Color(240, 240, 240));
        tbl_Product.setRowHeight(22);
        tbl_Product.setSelectionBackground(new java.awt.Color(255, 102, 102));
        tbl_Product.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_ProductMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tbl_Product);
        if (tbl_Product.getColumnModel().getColumnCount() > 0) {
            tbl_Product.getColumnModel().getColumn(0).setMinWidth(100);
            tbl_Product.getColumnModel().getColumn(0).setMaxWidth(150);
            tbl_Product.getColumnModel().getColumn(2).setMinWidth(120);
            tbl_Product.getColumnModel().getColumn(2).setMaxWidth(150);
        }

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        jLabel2.setText("Mã Danh mục: ");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        jLabel4.setText("Tên Danh mục: ");

        txt_IDDanhMuc.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        txt_IDDanhMuc.setName("Mã Danh mục"); // NOI18N

        txt_NameDanhMuc.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        txt_NameDanhMuc.setName("Tên Danh mục"); // NOI18N

        insertProduct.setBackground(new java.awt.Color(11, 181, 217));
        insertProduct.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        insertProduct.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/baseline_add_black_24dp.png"))); // NOI18N
        insertProduct.setText("Thêm");
        insertProduct.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                insertProductActionPerformed(evt);
            }
        });

        btn_updateProduct.setBackground(new java.awt.Color(11, 181, 217));
        btn_updateProduct.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btn_updateProduct.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/baseline_sync_alt_black_24dp.png"))); // NOI18N
        btn_updateProduct.setText("Sửa");
        btn_updateProduct.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_updateProductActionPerformed(evt);
            }
        });

        btn_deleteProduct.setBackground(new java.awt.Color(11, 181, 217));
        btn_deleteProduct.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btn_deleteProduct.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/baseline_delete_forever_black_24dp.png"))); // NOI18N
        btn_deleteProduct.setText("Xóa");
        btn_deleteProduct.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_deleteProductActionPerformed(evt);
            }
        });

        btn_newProduct.setBackground(new java.awt.Color(11, 181, 217));
        btn_newProduct.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btn_newProduct.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/baseline_open_in_new_black_24dp.png"))); // NOI18N
        btn_newProduct.setText("Làm mới");
        btn_newProduct.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_newProductActionPerformed(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        jLabel6.setText("Trạng thái:");

        rdb_0_Product.setBackground(new java.awt.Color(255, 204, 204));
        rdb_0_Product.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        rdb_0_Product.setText("Đang kinh doanh");

        rdb_1_Product.setBackground(new java.awt.Color(255, 204, 204));
        rdb_1_Product.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        rdb_1_Product.setText("Ngừng kinh doanh");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 448, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txt_IDDanhMuc, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txt_NameDanhMuc)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addComponent(jLabel2)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(insertProduct)
                                .addGap(18, 18, 18)
                                .addComponent(btn_updateProduct)
                                .addGap(18, 18, 18)
                                .addComponent(btn_deleteProduct)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btn_newProduct))
                            .addComponent(jLabel6)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(rdb_0_Product)
                                .addGap(64, 64, 64)
                                .addComponent(rdb_1_Product)))
                        .addGap(0, 129, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(79, 79, 79)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txt_IDDanhMuc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(23, 23, 23)
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_NameDanhMuc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel6)
                        .addGap(9, 9, 9)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(rdb_0_Product)
                            .addComponent(rdb_1_Product))
                        .addGap(58, 58, 58)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(insertProduct)
                            .addComponent(btn_updateProduct)
                            .addComponent(btn_deleteProduct)
                            .addComponent(btn_newProduct)))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(40, 40, 40)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 373, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(54, Short.MAX_VALUE))
        );

        tabs_QLSP.addTab("Danh mục", jPanel4);

        jLabel22.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel22.setForeground(new java.awt.Color(51, 51, 51));
        jLabel22.setText("Danh mục:");

        cbb_Product1.setBackground(new java.awt.Color(255, 102, 102));
        cbb_Product1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        cbb_Product1.setForeground(new java.awt.Color(51, 51, 51));
        cbb_Product1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cbb_Product1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbb_Product1ActionPerformed(evt);
            }
        });

        tbl_ProductVariant.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Loại SP", "Tên SP", "Trạng thái"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbl_ProductVariant.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_ProductVariantMouseClicked(evt);
            }
        });
        jScrollPane5.setViewportView(tbl_ProductVariant);

        jLabel23.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jLabel23.setText("Loại SP: ");

        jLabel24.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jLabel24.setText("Tên SP: ");

        jLabel25.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jLabel25.setText("Trạng thái: ");

        txt_IDVariant_ProductVariant.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        txt_IDVariant_ProductVariant.setName("Loại sản phẩm"); // NOI18N

        txt_SKUID_ProductVariant.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        txt_SKUID_ProductVariant.setName("Tên sản phẩm"); // NOI18N

        rdb_0_ProductVariant.setText("Còn hàng");

        rdb_1_ProductVariant.setText("Hết hàng");

        btn_insertProductVariant.setBackground(new java.awt.Color(11, 181, 217));
        btn_insertProductVariant.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btn_insertProductVariant.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/baseline_add_black_24dp.png"))); // NOI18N
        btn_insertProductVariant.setText("Thêm");
        btn_insertProductVariant.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_insertProductVariantActionPerformed(evt);
            }
        });

        btn_updateProductVariant.setBackground(new java.awt.Color(11, 181, 217));
        btn_updateProductVariant.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btn_updateProductVariant.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/baseline_sync_alt_black_24dp.png"))); // NOI18N
        btn_updateProductVariant.setText("Sửa");
        btn_updateProductVariant.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_updateProductVariantActionPerformed(evt);
            }
        });

        btn_deleteProduct1.setBackground(new java.awt.Color(11, 181, 217));
        btn_deleteProduct1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btn_deleteProduct1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/baseline_delete_forever_black_24dp.png"))); // NOI18N
        btn_deleteProduct1.setText("Xóa");
        btn_deleteProduct1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_deleteProduct1ActionPerformed(evt);
            }
        });

        btn_newProduct1.setBackground(new java.awt.Color(11, 181, 217));
        btn_newProduct1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btn_newProduct1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/baseline_open_in_new_black_24dp.png"))); // NOI18N
        btn_newProduct1.setText("Làm mới");
        btn_newProduct1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_newProduct1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addComponent(jLabel22)
                        .addGap(18, 18, 18)
                        .addComponent(cbb_Product1, javax.swing.GroupLayout.PREFERRED_SIZE, 216, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel12Layout.createSequentialGroup()
                                .addGap(67, 67, 67)
                                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txt_IDVariant_ProductVariant)
                                    .addComponent(txt_SKUID_ProductVariant)
                                    .addGroup(jPanel12Layout.createSequentialGroup()
                                        .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel23)
                                            .addComponent(jLabel24)
                                            .addComponent(jLabel25)
                                            .addGroup(jPanel12Layout.createSequentialGroup()
                                                .addComponent(rdb_0_ProductVariant)
                                                .addGap(44, 44, 44)
                                                .addComponent(rdb_1_ProductVariant)))
                                        .addGap(0, 0, Short.MAX_VALUE))))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel12Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 143, Short.MAX_VALUE)
                                .addComponent(btn_insertProductVariant)
                                .addGap(18, 18, 18)
                                .addComponent(btn_updateProductVariant)
                                .addGap(18, 18, 18)
                                .addComponent(btn_deleteProduct1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btn_newProduct1)))))
                .addContainerGap())
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel22)
                    .addComponent(cbb_Product1, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 284, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addComponent(jLabel23)
                        .addGap(18, 18, 18)
                        .addComponent(txt_IDVariant_ProductVariant, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(22, 22, 22)
                        .addComponent(jLabel24)
                        .addGap(9, 9, 9)
                        .addComponent(txt_SKUID_ProductVariant, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel25)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(rdb_0_ProductVariant)
                            .addComponent(rdb_1_ProductVariant))
                        .addGap(29, 29, 29)
                        .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btn_insertProductVariant)
                            .addComponent(btn_updateProductVariant)
                            .addComponent(btn_deleteProduct1)
                            .addComponent(btn_newProduct1))))
                .addContainerGap(125, Short.MAX_VALUE))
        );

        tabs_QLSP.addTab("Loại sản phẩm", jPanel12);

        jPanel3.setBackground(new java.awt.Color(255, 204, 204));

        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Thuộc tính", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 13))); // NOI18N

        tbl_option.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã TT", "Tên TT", "Trạng thái"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbl_option.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_optionMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tbl_option);

        btn_insertOptions.setBackground(new java.awt.Color(11, 181, 217));
        btn_insertOptions.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btn_insertOptions.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/baseline_add_black_24dp.png"))); // NOI18N
        btn_insertOptions.setText("Thêm");
        btn_insertOptions.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_insertOptionsActionPerformed(evt);
            }
        });

        btn_updateOptions.setBackground(new java.awt.Color(11, 181, 217));
        btn_updateOptions.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btn_updateOptions.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/baseline_sync_alt_black_24dp.png"))); // NOI18N
        btn_updateOptions.setText("Sửa");
        btn_updateOptions.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_updateOptionsActionPerformed(evt);
            }
        });

        btn_deleteOptions.setBackground(new java.awt.Color(11, 181, 217));
        btn_deleteOptions.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btn_deleteOptions.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/baseline_delete_forever_black_24dp.png"))); // NOI18N
        btn_deleteOptions.setText("Xóa");
        btn_deleteOptions.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_deleteOptionsActionPerformed(evt);
            }
        });

        btn_newOptions.setBackground(new java.awt.Color(11, 181, 217));
        btn_newOptions.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btn_newOptions.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/baseline_open_in_new_black_24dp.png"))); // NOI18N
        btn_newOptions.setText("Làm mới");
        btn_newOptions.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_newOptionsActionPerformed(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel9.setText("Mã thuộc tính:");

        jLabel10.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel10.setText("Tên thuộc tính:");

        jLabel11.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel11.setText("Trạng thái:");

        txt_IDOptions.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txt_IDOptions.setName("Mã thuộc tính"); // NOI18N

        txt_NamesOptions.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txt_NamesOptions.setName("Tên thuộc tính"); // NOI18N

        rdb_0_Options.setText("Hoạt động");

        rdb_1_Options.setText("Không hoạt động");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(jPanel5Layout.createSequentialGroup()
                                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(txt_NamesOptions, javax.swing.GroupLayout.PREFERRED_SIZE, 299, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(jPanel5Layout.createSequentialGroup()
                                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(18, 18, 18)
                                    .addComponent(txt_IDOptions, javax.swing.GroupLayout.PREFERRED_SIZE, 301, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel5Layout.createSequentialGroup()
                                        .addComponent(btn_insertOptions)
                                        .addGap(18, 18, 18)
                                        .addComponent(btn_updateOptions))
                                    .addGroup(jPanel5Layout.createSequentialGroup()
                                        .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(rdb_0_Options)))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel5Layout.createSequentialGroup()
                                        .addComponent(btn_deleteOptions)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(btn_newOptions))
                                    .addComponent(rdb_1_Options))))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(txt_IDOptions, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(txt_NamesOptions, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(rdb_0_Options)
                    .addComponent(rdb_1_Options))
                .addGap(21, 21, 21)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_insertOptions)
                    .addComponent(btn_updateOptions)
                    .addComponent(btn_deleteOptions)
                    .addComponent(btn_newOptions))
                .addGap(24, 24, 24))
        );

        jPanel7.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Giá trị thuộc tính", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 13))); // NOI18N

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jLabel8.setText("Thuộc tính: ");

        cbb_option.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        cbb_option.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cbb_option.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbb_optionActionPerformed(evt);
            }
        });

        tbl_OptionValues.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã Giá trị", "Tên Giá trị", "Trạng thái"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbl_OptionValues.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_OptionValuesMouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(tbl_OptionValues);

        jLabel12.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel12.setText("Mã giá trị:");

        txt_IDOptionValues.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txt_IDOptionValues.setName("Mã giá trị"); // NOI18N

        jLabel13.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel13.setText("Tên giá trị:");

        txt_NamesOptionValues.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txt_NamesOptionValues.setName("Tên giá trị"); // NOI18N

        jLabel14.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel14.setText("Trạng thái:");

        rdb_0_OptionValues.setText("Hoạt động");

        rdb_1_OptionValues.setText("Không hoạt động");

        btn_insertOptionValues.setBackground(new java.awt.Color(11, 181, 217));
        btn_insertOptionValues.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btn_insertOptionValues.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/baseline_add_black_24dp.png"))); // NOI18N
        btn_insertOptionValues.setText("Thêm");
        btn_insertOptionValues.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_insertOptionValuesActionPerformed(evt);
            }
        });

        btn_updateOptionValues.setBackground(new java.awt.Color(11, 181, 217));
        btn_updateOptionValues.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btn_updateOptionValues.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/baseline_sync_alt_black_24dp.png"))); // NOI18N
        btn_updateOptionValues.setText("Sửa");
        btn_updateOptionValues.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_updateOptionValuesActionPerformed(evt);
            }
        });

        btn_deleteOptionValues.setBackground(new java.awt.Color(11, 181, 217));
        btn_deleteOptionValues.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btn_deleteOptionValues.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/baseline_delete_forever_black_24dp.png"))); // NOI18N
        btn_deleteOptionValues.setText("Xóa");
        btn_deleteOptionValues.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_deleteOptionValuesActionPerformed(evt);
            }
        });

        btn_newOptionValues.setBackground(new java.awt.Color(11, 181, 217));
        btn_newOptionValues.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btn_newOptionValues.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/baseline_open_in_new_black_24dp.png"))); // NOI18N
        btn_newOptionValues.setText("Làm mới");
        btn_newOptionValues.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_newOptionValuesActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel7Layout.createSequentialGroup()
                            .addComponent(jLabel8)
                            .addGap(18, 18, 18)
                            .addComponent(cbb_option, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txt_NamesOptionValues, javax.swing.GroupLayout.PREFERRED_SIZE, 299, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(txt_IDOptionValues, javax.swing.GroupLayout.PREFERRED_SIZE, 301, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                            .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel7Layout.createSequentialGroup()
                                    .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(rdb_0_OptionValues))
                                .addGroup(jPanel7Layout.createSequentialGroup()
                                    .addComponent(btn_insertOptionValues)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(btn_updateOptionValues)))
                            .addGap(18, 18, 18)
                            .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(rdb_1_OptionValues)
                                .addGroup(jPanel7Layout.createSequentialGroup()
                                    .addComponent(btn_deleteOptionValues)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(btn_newOptionValues, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                .addContainerGap(50, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(cbb_option, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(txt_IDOptionValues, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(txt_NamesOptionValues, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 31, Short.MAX_VALUE)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14)
                    .addComponent(rdb_0_OptionValues)
                    .addComponent(rdb_1_OptionValues))
                .addGap(21, 21, 21)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_insertOptionValues)
                    .addComponent(btn_updateOptionValues)
                    .addComponent(btn_deleteOptionValues)
                    .addComponent(btn_newOptionValues))
                .addGap(25, 25, 25))
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        tabs_QLSP.addTab("Các thuộc tính sản phẩm", jPanel3);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(pnlSide, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(tabs_QLSP, javax.swing.GroupLayout.PREFERRED_SIZE, 1030, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(408, 408, 408))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlSide, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(tabs_QLSP, javax.swing.GroupLayout.PREFERRED_SIZE, 495, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 1313, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void lblDanhMucMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblDanhMucMouseClicked
        tabs_QLSP.setSelectedIndex(1);
    }//GEN-LAST:event_lblDanhMucMouseClicked

    private void lblTrangChuMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblTrangChuMouseClicked
        tabs_QLSP.setSelectedIndex(0);
    }//GEN-LAST:event_lblTrangChuMouseClicked

    private void lblTrangChuMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblTrangChuMouseEntered
        lblTrangChu.setForeground(Color.black);
    }//GEN-LAST:event_lblTrangChuMouseEntered

    private void lblTrangChuMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblTrangChuMouseExited
        lblTrangChu.setForeground(Color.white);
    }//GEN-LAST:event_lblTrangChuMouseExited

    private void pnlTrangChuMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlTrangChuMouseEntered

    }//GEN-LAST:event_pnlTrangChuMouseEntered

    private void pnlTrangChuMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlTrangChuMouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_pnlTrangChuMouseExited

    private void lblDanhMucMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblDanhMucMouseEntered
        lblDanhMuc.setForeground(Color.black);
    }//GEN-LAST:event_lblDanhMucMouseEntered

    private void lblDanhMucMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblDanhMucMouseExited
        lblDanhMuc.setForeground(Color.white);
    }//GEN-LAST:event_lblDanhMucMouseExited

    private void lblThuocTinhMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblThuocTinhMouseEntered
        lblThuocTinh.setForeground(Color.black);
    }//GEN-LAST:event_lblThuocTinhMouseEntered

    private void lblThuocTinhMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblThuocTinhMouseExited
        lblThuocTinh.setForeground(Color.white);
    }//GEN-LAST:event_lblThuocTinhMouseExited

    private void cbb_ProductActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbb_ProductActionPerformed
        this.selectCbbProduct();
//        fillComboBoxIDVariant_ProductVarint();
    }//GEN-LAST:event_cbb_ProductActionPerformed

    private void tbl_ProductMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_ProductMouseClicked
        this._rowProduct = tbl_Product.rowAtPoint(evt.getPoint());
        editProduct();
    }//GEN-LAST:event_tbl_ProductMouseClicked

    private void insertProductActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_insertProductActionPerformed
        if (UtilityHelper.checkNullText(txt_IDDanhMuc) && UtilityHelper.checkNullText(txt_NameDanhMuc)) {
            if (UtilityHelper.checkIdProduct(txt_IDDanhMuc) && UtilityHelper.checkTenDanhMuc(txt_NameDanhMuc)) {
                if (checkIDProduct(txt_IDDanhMuc.getText()) && checkNamesProduct(txt_NameDanhMuc.getText())) {
                    insertProduct();
                }
            }
        }
    }//GEN-LAST:event_insertProductActionPerformed

    private void lblThuocTinhMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblThuocTinhMouseClicked
        tabs_QLSP.setSelectedIndex(3);
    }//GEN-LAST:event_lblThuocTinhMouseClicked

    private void btn_deleteProductActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_deleteProductActionPerformed

        if (_rowProduct < 0) {
            DialogHelper.alert(this, "Mời bạn chọn danh mục cần xóa");
            return;
        }
        deleteProduct();
    }//GEN-LAST:event_btn_deleteProductActionPerformed

    private void btn_updateProductActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_updateProductActionPerformed

        if (_rowProduct < 0) {
            DialogHelper.alert(this, "Mời bạn chọn danh mục cần sửa");
            return;
        }
        if (UtilityHelper.checkNullText(txt_IDDanhMuc) && UtilityHelper.checkNullText(txt_NameDanhMuc)) {
            if (UtilityHelper.checkIdProduct(txt_IDDanhMuc) && UtilityHelper.checkTenDanhMuc(txt_NameDanhMuc)) {
                if (!txt_IDDanhMuc.getText().equalsIgnoreCase(String.valueOf(tbl_Product.getValueAt(_rowProduct, 0)))) {
                    DialogHelper.alert(this, "Bạn không được thay đổi Mã danh mục");
                    return;
                } else {
                    updateProduct();
                }
            }
        }
    }//GEN-LAST:event_btn_updateProductActionPerformed

    private void btn_newProductActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_newProductActionPerformed
        if (Helper.DialogHelper.confirm(this, "Bạn thực sự muốn làm mới?")) {
            clearFormProduct();
        }
    }//GEN-LAST:event_btn_newProductActionPerformed

    private void btn_insertOptionsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_insertOptionsActionPerformed
        if (UtilityHelper.checkNullText(txt_IDOptions) && UtilityHelper.checkNullText(txt_NamesOptions)) {
            if (UtilityHelper.checkIdProduct(txt_IDOptions) && UtilityHelper.checkTenDanhMuc(txt_NamesOptions)) {
                if (checkIDOptions(txt_IDOptions.getText()) && checkNamesOptions(txt_NamesOptions.getText())) {
                    insertOptions();
                }
            }
        }
    }//GEN-LAST:event_btn_insertOptionsActionPerformed

    private void btn_updateOptionsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_updateOptionsActionPerformed
        if (_rowOptions < 0) {
            DialogHelper.alert(this, "Mời bạn chọn thuộc tính cần sửa");
            return;
        }
        if (UtilityHelper.checkNullText(txt_IDOptions) && UtilityHelper.checkNullText(txt_NamesOptions)) {
            if (UtilityHelper.checkIdProduct(txt_IDOptions) && UtilityHelper.checkTenDanhMuc(txt_NamesOptions)) {
                if (!txt_IDOptions.getText().equalsIgnoreCase(String.valueOf(tbl_option.getValueAt(_rowOptions, 0)))) {
                    DialogHelper.alert(this, "Bạn không được thay đổi Mã thuộc tính");
                    return;
                } else {
                    updateOptions();
                }
            }
        }
    }//GEN-LAST:event_btn_updateOptionsActionPerformed

    private void btn_deleteOptionsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_deleteOptionsActionPerformed
        if (_rowOptions < 0) {
            DialogHelper.alert(this, "Mời bạn chọn thuộc tính cần xóa");
            return;
        }
        deleteOptions();
    }//GEN-LAST:event_btn_deleteOptionsActionPerformed

    private void btn_newOptionsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_newOptionsActionPerformed
        if (Helper.DialogHelper.confirm(this, "Bạn thực sự muốn làm mới?")) {
            clearFormOptions();
        }
    }//GEN-LAST:event_btn_newOptionsActionPerformed

    private void tbl_optionMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_optionMouseClicked
        this._rowOptions = tbl_option.rowAtPoint(evt.getPoint());
        editOptions();
    }//GEN-LAST:event_tbl_optionMouseClicked

    private void tbl_OptionValuesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_OptionValuesMouseClicked
        this._rowOptionValues = tbl_OptionValues.rowAtPoint(evt.getPoint());
        editOptionValues();
    }//GEN-LAST:event_tbl_OptionValuesMouseClicked

    private void btn_insertOptionValuesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_insertOptionValuesActionPerformed
        if (UtilityHelper.checkNullText(txt_IDOptionValues) && UtilityHelper.checkNullText(txt_NamesOptionValues)) {
            if (UtilityHelper.checkIdProduct(txt_IDOptionValues) && UtilityHelper.checkTenDanhMuc(txt_NamesOptionValues)) {
                if (checkIDOptionValues(txt_IDOptionValues.getText()) && checkNamesOptionValues(txt_NamesOptionValues.getText())) {
                    insertOptionValues();
                }
            }
        }
    }//GEN-LAST:event_btn_insertOptionValuesActionPerformed

    private void btn_updateOptionValuesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_updateOptionValuesActionPerformed
        if (_rowOptionValues < 0) {
            DialogHelper.alert(this, "Mời bạn chọn giá trị cần sửa");
            return;
        }
        if (UtilityHelper.checkNullText(txt_IDOptionValues) && UtilityHelper.checkNullText(txt_NamesOptionValues)) {
            if (UtilityHelper.checkIdProduct(txt_IDOptionValues) && UtilityHelper.checkTenDanhMuc(txt_NamesOptionValues)) {
                if (!txt_IDOptionValues.getText().equalsIgnoreCase(String.valueOf(tbl_OptionValues.getValueAt(_rowOptionValues, 0)))) {
                    DialogHelper.alert(this, "Bạn không được thay đổi Mã giá trị");
                    return;
                } else {
                    updateOptionValues();
                }
            }
        }
    }//GEN-LAST:event_btn_updateOptionValuesActionPerformed

    private void btn_deleteOptionValuesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_deleteOptionValuesActionPerformed
        if (_rowOptionValues < 0) {
            DialogHelper.alert(this, "Mời bạn chọn giá trị cần xóa");
            return;
        }
        deleteOptionValues();
    }//GEN-LAST:event_btn_deleteOptionValuesActionPerformed

    private void btn_newOptionValuesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_newOptionValuesActionPerformed
        if (Helper.DialogHelper.confirm(this, "Bạn thực sự muốn làm mới?")) {
            clearFormOptionValues();
        }
    }//GEN-LAST:event_btn_newOptionValuesActionPerformed

    private void cbb_optionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbb_optionActionPerformed
        this.selectCbbOptions();
    }//GEN-LAST:event_cbb_optionActionPerformed

    private void btn_insertVariantValuesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_insertVariantValuesActionPerformed
        insertProductOptions();
        insertVarianValues();
//        Product product = (Product) cbb_Product.getSelectedItem();
//        System.out.println(product.getID_Product());

    }//GEN-LAST:event_btn_insertVariantValuesActionPerformed

    private void btn_updateVariantValuesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_updateVariantValuesActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_updateVariantValuesActionPerformed

    private void btn_deleteVariantValuesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_deleteVariantValuesActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_deleteVariantValuesActionPerformed

    private void btn_newVariantValuesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_newVariantValuesActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_newVariantValuesActionPerformed

    private void btn_addOptionsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_addOptionsActionPerformed

        actionOptions();

    }//GEN-LAST:event_btn_addOptionsActionPerformed

    private void cbb_Product1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbb_Product1ActionPerformed
        this.selectCbbProduct2();
    }//GEN-LAST:event_cbb_Product1ActionPerformed

    private void btn_insertProductVariantActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_insertProductVariantActionPerformed
        if (UtilityHelper.checkNullText(txt_IDVariant_ProductVariant) && UtilityHelper.checkNullText(txt_SKUID_ProductVariant)) {
            if (UtilityHelper.checkIdProduct(txt_IDVariant_ProductVariant) && UtilityHelper.checkTenDanhMuc(txt_SKUID_ProductVariant)) {
//                if (checkIDProductVariant(txt_IDVariant_ProductVariant.getText()) && checkNamesProductVariant(txt_SKUID_ProductVariant.getText())) {
                insertProductVariant();
//                }
            }
        }
    }//GEN-LAST:event_btn_insertProductVariantActionPerformed

    private void btn_updateProductVariantActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_updateProductVariantActionPerformed
        if (_rowProductVariant < 0) {
            DialogHelper.alert(this, "Mời bạn chọn Loại SP cần sửa");
            return;
        }
        if (UtilityHelper.checkNullText(txt_IDVariant_ProductVariant) && UtilityHelper.checkNullText(txt_SKUID_ProductVariant)) {
            if (UtilityHelper.checkTenDanhMuc(txt_SKUID_ProductVariant)) {
                if (!txt_IDVariant_ProductVariant.getText().equalsIgnoreCase(String.valueOf(tbl_ProductVariant.getValueAt(_rowProductVariant, 0)))) {
                    DialogHelper.alert(this, "Bạn không được thay đổi Loại SP");
                    return;
                } else {
                    updateProductVariant();
                }
            }
        }
    }//GEN-LAST:event_btn_updateProductVariantActionPerformed

    private void btn_deleteProduct1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_deleteProduct1ActionPerformed
        if (_rowProductVariant < 0) {
            DialogHelper.alert(this, "Mời bạn chọn Loại SP cần xóa");
            return;
        }
        deleteProductVariant();
    }//GEN-LAST:event_btn_deleteProduct1ActionPerformed

    private void btn_newProduct1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_newProduct1ActionPerformed
        if (Helper.DialogHelper.confirm(this, "Bạn thực sự muốn làm mới?")) {
            clearFormProductVariant();
        }
    }//GEN-LAST:event_btn_newProduct1ActionPerformed

    private void lblLoaiSPMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblLoaiSPMouseClicked
        tabs_QLSP.setSelectedIndex(2);
    }//GEN-LAST:event_lblLoaiSPMouseClicked

    private void lblLoaiSPMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblLoaiSPMouseEntered
        lblLoaiSP.setForeground(Color.black);
    }//GEN-LAST:event_lblLoaiSPMouseEntered

    private void lblLoaiSPMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblLoaiSPMouseExited
        lblLoaiSP.setForeground(Color.white);
    }//GEN-LAST:event_lblLoaiSPMouseExited

    private void tbl_ProductVariantMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_ProductVariantMouseClicked
        this._rowProductVariant = tbl_ProductVariant.rowAtPoint(evt.getPoint());
        this.editProductVariant();
    }//GEN-LAST:event_tbl_ProductVariantMouseClicked

    private void btn_saveOptionsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_saveOptionsActionPerformed
        _cbbTT.add(String.valueOf(_cbx.getSelectedItem()));
        _cbbGTTT.add(String.valueOf(_cbx2.getSelectedItem()));
    }//GEN-LAST:event_btn_saveOptionsActionPerformed

    private void lbl_imagesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbl_imagesMouseClicked
        try {
            _fd.setVisible(true); //mở hộp thoại chọn file
            _file = new File(_fd.getDirectory() + _fd.getFile());
            if (!String.valueOf(_file).equals("nullnull")) {
                Helper.Image.save(_file);
                Image image = ImageIO.read(_file);
                _images = _file.getName();
                lbl_images.setIcon(new ImageIcon(image.getScaledInstance(140, 200, 0)));
                return;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_lbl_imagesMouseClicked

    private void btn_autoBarCodeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_autoBarCodeActionPerformed
        String uuid = UUID.randomUUID().toString().replaceAll("-", "");
        String random = uuid.substring(0, 13);
        txt_barcode.setText(random);
    }//GEN-LAST:event_btn_autoBarCodeActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;

                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(FormSanPham.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FormSanPham.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FormSanPham.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FormSanPham.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                FormSanPham dialog = new FormSanPham(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel Panel_ThuocTinhs;
    private javax.swing.JScrollPane SrollPane_Options;
    private javax.swing.JButton btn_addOptions;
    private javax.swing.JButton btn_autoBarCode;
    private javax.swing.JButton btn_deleteOptionValues;
    private javax.swing.JButton btn_deleteOptions;
    private javax.swing.JButton btn_deleteProduct;
    private javax.swing.JButton btn_deleteProduct1;
    private javax.swing.JButton btn_deleteVariantValues;
    private javax.swing.JButton btn_insertOptionValues;
    private javax.swing.JButton btn_insertOptions;
    private javax.swing.JButton btn_insertProductVariant;
    private javax.swing.JButton btn_insertVariantValues;
    private javax.swing.JButton btn_newOptionValues;
    private javax.swing.JButton btn_newOptions;
    private javax.swing.JButton btn_newProduct;
    private javax.swing.JButton btn_newProduct1;
    private javax.swing.JButton btn_newVariantValues;
    private javax.swing.JButton btn_saveOptions;
    private javax.swing.JButton btn_updateOptionValues;
    private javax.swing.JButton btn_updateOptions;
    private javax.swing.JButton btn_updateProduct;
    private javax.swing.JButton btn_updateProductVariant;
    private javax.swing.JButton btn_updateVariantValues;
    private javax.swing.JComboBox<String> cbb_IDVariant;
    private javax.swing.JComboBox<String> cbb_Product;
    private javax.swing.JComboBox<String> cbb_Product1;
    private javax.swing.JComboBox<String> cbb_option;
    private javax.swing.JButton insertProduct;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel17;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JLabel lblDanhMuc;
    private javax.swing.JLabel lblLoaiSP;
    private javax.swing.JLabel lblThuocTinh;
    private javax.swing.JLabel lblTrangChu;
    private javax.swing.JLabel lbl_images;
    private javax.swing.JPanel pnlSide;
    private javax.swing.JPanel pnlTrangChu;
    private javax.swing.JRadioButton rdb_0_OptionValues;
    private javax.swing.JRadioButton rdb_0_Options;
    private javax.swing.JRadioButton rdb_0_Product;
    private javax.swing.JRadioButton rdb_0_ProductVariant;
    private javax.swing.JRadioButton rdb_0_variantValues;
    private javax.swing.JRadioButton rdb_1_OptionValues;
    private javax.swing.JRadioButton rdb_1_Options;
    private javax.swing.JRadioButton rdb_1_Product;
    private javax.swing.JRadioButton rdb_1_ProductVariant;
    private javax.swing.JRadioButton rdb_1_variantValues;
    private javax.swing.JTabbedPane tabs_QLSP;
    private javax.swing.JTable tbl_OptionValues;
    private javax.swing.JTable tbl_Product;
    private javax.swing.JTable tbl_ProductVariant;
    private javax.swing.JTable tbl_Variant_values2;
    private javax.swing.JTable tbl_option;
    private javax.swing.JTextField txt_IDDanhMuc;
    private javax.swing.JTextField txt_IDOptionValues;
    private javax.swing.JTextField txt_IDOptions;
    private javax.swing.JTextField txt_IDVariant_ProductVariant;
    private javax.swing.JTextField txt_NameDanhMuc;
    private javax.swing.JTextField txt_NamesOptionValues;
    private javax.swing.JTextField txt_NamesOptions;
    private javax.swing.JTextField txt_Price;
    private javax.swing.JTextField txt_Quarity;
    private javax.swing.JTextField txt_SKUID_ProductVariant;
    private javax.swing.JTextField txt_barcode;
    // End of variables declaration//GEN-END:variables
}
