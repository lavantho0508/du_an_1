/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Sevices;

import IServices.INHAN_VIEN_Service;
import IServices.IQLyNHAN_VIENService;
import Models.NHAN_VIEN;
import Models.NHAN_VIEN_BUS;
import java.util.List;

/**
 *
 * @author DELL
 */
public class QLNHAN_VIEN_Service implements IQLyNHAN_VIENService{
 static NHAN_VIEN_Service _INHANVIEN_Service1=new NHAN_VIEN_Service();
   
 @Override
    public  List<NHAN_VIEN> selectAll() {
       return _INHANVIEN_Service1.selectAll();
    }   

   

    @Override
    public String randomCode() {
       return _INHANVIEN_Service1.randomCode();
    }

    @Override
    public List<NHAN_VIEN> selecEmailByID(String ma) {
      return _INHANVIEN_Service1.selectEmailByID(ma);
    }

    @Override
    public int changePassWord(String ma, String pass) {
       return _INHANVIEN_Service1.changePassWord(ma, pass);
    }
    
}
