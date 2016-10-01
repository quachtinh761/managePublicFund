/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import common.constant.consumeConstant;
import common.function.dateTimeHandle;
import java.sql.Connection;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.swing.table.DefaultTableModel;
import object.consumeObject;
import model.DBTemplate;

/**
 *
 * @author Nguyen Van Tinh
 */
public class consumeModel {
    private final String tblName = "vothidaiguong";
    DBTemplate model = new DBTemplate(tblName);
    
    public consumeModel(){
        model.getConnect();
        //this.createTable();
    }
    
    public void getConnect(){
        model.getConnect();
    }
    
    public void closeConnect(){
        model.closeConnect();
    }
    
    public boolean checkExistTable(){
        if(model.isTableExist(tblName)==true){
            return createTable();
        }else{
            return false;
        }
    }
    
    public boolean createTable(){
        Map<String, String> params= new HashMap();
        params.put(consumeConstant.KEY_ID, consumeConstant.KEY_ID_TYPE);
        params.put(consumeConstant.KEY_DATE, consumeConstant.KEY_DATE_TYPE);
        params.put(consumeConstant.KEY_AMOUNT, consumeConstant.KEY_AMOUNT_TYPE);
        params.put(consumeConstant.KEY_CONTENT, consumeConstant.KEY_CONTENT_TYPE);
        return model.createTable(tblName, params);
        
    }
    
    public boolean addNew(consumeObject consume){
        Map<String, String> params= new HashMap();
        params.put(consumeConstant.KEY_ID, dateTimeHandle.getDateFormat(new Date(), "yyyyMMddHHmmssa"));
        params.put(consumeConstant.KEY_DATE, dateTimeHandle.getDateFormat(consume.getDate(), "yyyyMMddHHmmssa"));
        params.put(consumeConstant.KEY_AMOUNT, Integer.toString(consume.getAmount()));
        params.put(consumeConstant.KEY_CONTENT, consume.getContent());
        
        System.out.println(getAllTableConsumeInDB().getRowCount());
        if (getAllTableConsumeInDB().getRowCount() == 0){
            System.out.println(this.createTable());
        }
        System.out.println(model.insertTable(tblName, params));
        return model.closeConnect();
    }
    
    public DefaultTableModel getAllTableConsumeInDB(){
        return model.searchAllTable(tblName);
    }
}
