/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package object;

import java.util.Date;

/**
 *
 * @author Nguyen Van Tinh
 */
public class consumeObject {
    private Date date;
    private int amount;
    private String content;
    private String type;

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public consumeObject(Date date, int amount, String content, String type) {
        if (date != null){
            this.date = date;
        }else{
            this.date = new Date();
        }
        this.amount = amount;
        this.content = content;
        this.type = type;
    }
    
    public consumeObject(String date, int amount, String content, String type) {
        if (!date.equals("")){
            this.date = new Date(date);
        }else{
            this.date = new Date();
        }
        this.amount = amount;
        this.content = content;
        this.type = type;
    }
    
    public consumeObject(Date date, String amount, String content, String type) {
        if (date != null){
            this.date = date;
        }else{
            this.date = new Date();
        }
        if (!amount.equals("")){
            this.amount = Integer.parseInt(amount);
        }else{
            this.amount = 0;
        }
        this.content = content;
        this.type = type;
    }
    
    public consumeObject(String date, String amount, String content, String type) {
        if (!date.equals("")){
            this.date = new Date(date);
        }else{
            this.date = new Date();
        }
        if (!amount.equals("")){
            this.amount = Integer.parseInt(amount);
        }else{
            this.amount = 0;
        }
        this.content = content;
        this.type = type;
    }
    
    public consumeObject(int amount, String content, String type) {
        this.date = new Date();
        this.amount = amount;
        this.content = content;
        this.type = type;
    }
}
