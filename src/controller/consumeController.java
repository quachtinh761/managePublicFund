/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;
import common.constant.getErrMsg;
import common.function.dateTimeHandle;
import java.text.ParseException;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import model.consumeModel;
import object.consumeObject;

/**
 *
 * @author Nguyen Van Tinh
 */
public class consumeController {
    private consumeObject conObject;
    private final consumeModel conModel = new consumeModel();
    
    public void getConnect(){
        conModel.getConnect();
    }
    
    public void closeConnect(){
        conModel.closeConnect();
    }
    
    public String addConsume(Date date, String amount, String content){
        String result = "";
        result += checkDate(date);
        result += checkContent(amount);
        result += checkAmount(amount);
        if (result.equals("")){
            this.conObject = new consumeObject(date, Integer.parseInt(amount), content);
            conModel.addNew(conObject);
        }
        return result;
    }
    
    private String checkDate(Date date){
        //date input
        int da = Integer.parseInt(dateTimeHandle.getDateFormat(date, "yyyyMMdd"));
        //date current
        int daNo = Integer.parseInt(dateTimeHandle.getDateFormat(new Date(), "yyyyMMdd"));
        if (da > daNo){
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
    
    private String checkContent(String content){
        return (content.equals("")) ? getErrMsg.errMsgNullContent : "";
    }
    
    public DefaultTableModel getAllTableConsumeInDB(){
        return conModel.getAllTableConsumeInDB();
    }
}
