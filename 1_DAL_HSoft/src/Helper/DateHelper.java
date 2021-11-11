/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Helper;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author BUI_QUANG_HIEU
 */
public class DateHelper {
public static final SimpleDateFormat DATE_FORMATER = new SimpleDateFormat("dd/MM/yyyy");
    static SimpleDateFormat format = new SimpleDateFormat();
   
    public static Date toDate(String date,String...pattern){
        try {
            if(pattern.length>0)DATE_FORMATER.applyPattern(pattern[0]);
            if(date==null)return DateHelper.now();
            return DATE_FORMATER.parse(date);
        } catch (ParseException ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex); 
        }
    }
    
   

    public static String toString(Date date, String...pattern){
        if(pattern.length>0)DATE_FORMATER.applyPattern(pattern[0]);
        if(date==null)date=DateHelper.now();
        return DATE_FORMATER.format(date);
    }
    
    public static String DateToDate(Date date){
//        if(pattern.length>0)DATE_FORMATER.applyPattern(pattern[0]);
        if(date==null)date=DateHelper.now();
        return DATE_FORMATER.format(date);
    }

    public static Date adDays(Date date, long days) {
        date.setTime(date.getTime()+days*24*60*60*1000);
        return date;
    }
    
    public static Date now() {
        return new Date();   //new Date lấy giờ hiện tại
    }
    
     public static Date add(int days){
        Calendar cal=Calendar.getInstance();
        cal.add(Calendar.DAY_OF_MONTH,days);
        return cal.getTime();
    }

    
}
