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
import object.consumeObject;

/**
 *
 * @author Nguyen Van Tinh
 */
public class consumeModel {
    private Connection conn;
    private final String tblName = "consumeTable";
    DBTemplate model = new DBTemplate(tblName);
    
    private boolean createTable(){
        Map<String, String> params= new HashMap();
        params.put(consumeConstant.KEY_ID, consumeConstant.KEY_ID_TYPE);
        params.put(consumeConstant.KEY_DATE, consumeConstant.KEY_DATE_TYPE);
        params.put(consumeConstant.KEY_AMOUNT, consumeConstant.KEY_AMOUNT_TYPE);
        params.put(consumeConstant.KEY_CONTENT, consumeConstant.KEY_CONTENT_TYPE);
        return model.createTable(tblName, params);
    }
    
    public boolean addNew(consumeObject consume){
        boolean result =  model.getConnect();
        this.createTable();
        
        Map<String, String> params= new HashMap();
        params.put(consumeConstant.KEY_ID, dateTimeHandle.getDateFormat(consume.getDate(), "hhmmddMMyyyy"));
        params.put(consumeConstant.KEY_DATE, dateTimeHandle.getDateFormat(consume.getDate(), "hhmmddMMyyyy"));
        params.put(consumeConstant.KEY_AMOUNT, Integer.toString(consume.getAmount()));
        params.put(consumeConstant.KEY_CONTENT, consume.getContent());
        model.insertTable(tblName, params);
        return result && model.closeConnect();
    }
}
