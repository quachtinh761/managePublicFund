/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package layout;

import common.constant.consumeConstant;
import common.function.encryptMd5;
import java.util.HashMap;
import java.util.Map;
import model.DBTemplate;
import static model.DBTemplate.searchAllTable;

/**
 *
 * @author Nguyen Van Tinh
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        //HomeLayout.run();
        String tableName = "consumeTable";
        DBTemplate db = new DBTemplate(tableName);
        System.out.println(db.getConnect());
        /*Map<String, String> params= new HashMap();
        params.put(consumeConstant.KEY_ID, consumeConstant.KEY_ID_TYPE);
        params.put(consumeConstant.KEY_DATE, consumeConstant.KEY_DATE_TYPE);
        params.put(consumeConstant.KEY_AMOUNT, consumeConstant.KEY_AMOUNT_TYPE);
        params.put(consumeConstant.KEY_CONTENT, consumeConstant.KEY_CONTENT_TYPE);
        System.out.println(db.createTable("data", params));
        String sql = "INSERT INTO @" + encryptMd5.getStringMd5(tableName) + 
                "(id, dateInput, amount, content) VALUES ('123445','140625','14','abc');";
        System.out.println(sql);
        db.runQuery(sql);*/
    }
    
    
}
