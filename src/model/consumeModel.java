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
    private String type = consumeConstant.TYPE_CONSUME;
    private String tblName = this.type + "Table";
    DBTemplate model = new DBTemplate();
    
    public consumeModel(String type){
        //this.getConnect();
        //this.createTable();
        switch (type){
            case consumeConstant.TYPE_RAISE:
                this.type = consumeConstant.TYPE_RAISE;
                break;
            default:
                this.type = consumeConstant.TYPE_CONSUME;
                break;
        }
        tblName = this.type + "Table";
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
        params.add(new String[]{consumeConstant.KEY_TYPE, consumeConstant.KEY_TYPE_TYPE});
        return model.createTable(tblName, params);
        
    }
    
    public boolean addNew(consumeObject consume){
        Map<String, String> params= new HashMap();
        params.put(consumeConstant.KEY_ID, dateTimeHandle.getDateFormat(new Date(), "yyyyMMddHHmm"));
        params.put(consumeConstant.KEY_DATE, dateTimeHandle.getDateFormat(consume.getDate(), "yyyy-MM-dd"));
        params.put(consumeConstant.KEY_AMOUNT, Integer.toString(consume.getAmount()));
        params.put(consumeConstant.KEY_CONTENT, consume.getContent());
        params.put(consumeConstant.KEY_TYPE, consume.getType());
        if (!model.isTableExist(tblName)){
            this.createTable();
        }
        return model.insertTable(tblName, params);
    }
    
    public boolean updateTable(consumeObject consume, String id){
        Map<String, String> params= new HashMap();
        params.put(consumeConstant.KEY_AMOUNT, Integer.toString(consume.getAmount()));
        params.put(consumeConstant.KEY_CONTENT, consume.getContent());
        String[] where = new String[]{consumeConstant.KEY_ID, id};
        return model.updateTable(tblName, params, where);
    }
    
    public DefaultTableModel getAllTableConsumeInDB(){
        return model.searchAllTable(tblName);
    }
}
