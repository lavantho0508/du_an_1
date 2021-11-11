/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IServices;

import Models.NHAN_VIEN;
import Models.NHAN_VIEN_BUS;
import java.util.List;

/**
 *
 * @author DELL
 */
public interface IQLyNHAN_VIENService {
    public List<NHAN_VIEN> selectAll();
    public List<NHAN_VIEN>selecEmailByID(String ma);
    public String randomCode();
    public int changePassWord(String ma,String pass);
}
