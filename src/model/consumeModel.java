/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import common.constant.consumeConstant;
import common.function.dateTimeHandle;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.table.DefaultTableModel;
import object.consumeObject;

/**
 *
 * @author Nguyen Van Tinh
 */
public final class consumeModel {
    private final String tblName = "consumeTable";
    DBTemplate model = new DBTemplate();
    
    public consumeModel(){
        //this.getConnect();
        //this.createTable();
    }
    
    public void getConnect(){
        model.getConnect();
    }
    
    public void closeConnect(){
        model.closeConnect();
    }
    
    public boolean createTable(){
        List < String[] > params = new ArrayList();
        params.add(new String[]{consumeConstant.KEY_ID, consumeConstant.KEY_ID_TYPE});
        params.add(new String[]{consumeConstant.KEY_DATE, consumeConstant.KEY_DATE_TYPE});
        params.add(new String[]{consumeConstant.KEY_AMOUNT, consumeConstant.KEY_AMOUNT_TYPE});
        params.add(new String[]{consumeConstant.KEY_CONTENT, consumeConstant.KEY_CONTENT_TYPE});
        return model.createTable(tblName, params);
        
    }
    
    public boolean addNew(consumeObject consume){
        Map<String, String> params= new HashMap();
        params.put(consumeConstant.KEY_ID, dateTimeHandle.getDateFormat(new Date(), "yyyyMMddHHmm"));
        params.put(consumeConstant.KEY_DATE, dateTimeHandle.getDateFormat(consume.getDate(), "yyyy-MM-dd"));
        params.put(consumeConstant.KEY_AMOUNT, Integer.toString(consume.getAmount()));
        params.put(consumeConstant.KEY_CONTENT, consume.getContent());
        
        if (!model.isTableExist(tblName)){
            this.createTable();
        }
        return model.insertTable(tblName, params);
    }
    
    public DefaultTableModel getAllTableConsumeInDB(){
        return model.searchAllTable(tblName);
    }
}
