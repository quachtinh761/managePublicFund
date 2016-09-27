/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package common.function;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author Nguyen Van Tinh
 */
public class dateTimeHandle {
    public static String getDateFormat(Date date, String format){
        Date curDate = date;
        SimpleDateFormat formatDate = new SimpleDateFormat(format);
        return formatDate.format(curDate);
    }
    
}
