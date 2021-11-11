/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IServices;

import Models.NHAN_VIEN;
import java.util.List;

/**
 *
 * @author DELL
 */
public interface INHAN_VIEN_Service {
    public List<NHAN_VIEN>selectAll();
    public List<NHAN_VIEN>selectEmailByID(String ma);
    public String randomCode();
    public int changePassWord(String ma,String pass);
}
