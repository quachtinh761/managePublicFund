/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;
import common.constant.consumeConstant;
import common.constant.getErrMsg;
import common.function.dateTimeHandle;
import java.util.Date;
import javax.swing.table.DefaultTableModel;
import model.consumeModel;
import object.consumeObject;

/**
 *
 * @author Nguyen Van Tinh
 */
public class consumeController {
    private String type = consumeConstant.TYPE_CONSUME;
    private consumeObject conObject;
    private consumeModel conModel = new consumeModel(this.type);
    
    public consumeController(String type){
        switch (type){
            case consumeConstant.TYPE_RAISE:
                this.type = consumeConstant.TYPE_RAISE;
                break;
            default:
                this.type = consumeConstant.TYPE_CONSUME;
                break;
        }
        conModel = new consumeModel(this.type);
    }
    
    public void getConnect(){
        conModel.getConnect();
    }
    
    public void closeConnect(){
        conModel.closeConnect();
    }
    
    public String addConsume(Date date, String amount, String content, String type){
        String result = "";
        result += checkDate(date);
        result += checkContent(amount);
        result += checkAmount(amount);
        if (result.equals("")){
            this.conObject = new consumeObject(date, Integer.parseInt(amount), content, type);
            conModel.addNew(conObject);
        }
        return result;
    }
    
    public String updateConsume(String id, String date, String amount, String content, String type){
        String result = "";
        result += checkContent(amount);
        result += checkAmount(amount);
        if (result.equals("")){
            this.conObject = new consumeObject(new Date(), Integer.parseInt(amount), content, type);
            result += conModel.updateTable(conObject, id);
        }
        return result;
    }
    
    private String checkDate(Date date){
        //date input
        int da = Integer.parseInt(dateTimeHandle.getDateFormat(date, "yyyyMMdd"));
        //date current
        int daNow = Integer.parseInt(dateTimeHandle.getDateFormat(new Date(), "yyyyMMdd"));
        //after 1 minute
        if (da > daNow + 60){
            System.out.println(da + "|" + daNow);
            return getErrMsg.errMsgInvalidDate;
        }else{
            return "";
        }
    }
    
    private String checkAmount(String amount){
        try{
            Integer.parseInt(amount);
        }catch (Exception e){
            return getErrMsg.errMsgNullAmount;
        }
        return "";
    }
    
    public String updateConsume(String date, String amount, String content){
        return "";
    }
    
    private String checkContent(String content){
        return (content.equals("")) ? getErrMsg.errMsgNullContent : "";
    }
    
    public DefaultTableModel getAllTableConsumeInDB(){
        int id = 0, date = 1, amount = 2, content = 3;
        DefaultTableModel temp = conModel.getAllTableConsumeInDB();
        DefaultTableModel result = new DefaultTableModel();
        result.setColumnCount(temp.getColumnCount());
        result.setRowCount(temp.getRowCount());
        for (int i = 0; i < temp.getRowCount(); i++){
            result.setValueAt(temp, i, id);
            result.setValueAt("", i, date);
            result.setValueAt("", i, amount);
            result.setValueAt("", i, content);
        }
        return conModel.getAllTableConsumeInDB();
    }
}
