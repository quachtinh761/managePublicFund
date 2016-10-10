/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package common.constant;

/**
 *
 * @author Nguyen Van Tinh
 */
public class consumeConstant {
    public final static String KEY_ID = "id",
            KEY_DATE = "dateInput",
            KEY_AMOUNT = "amount",
            KEY_CONTENT = "content",
            KEY_TYPE = "type";
    public final static String KEY_ID_TYPE = "VARCHAR(40) PRIMARY KEY NOT NULL",
            KEY_DATE_TYPE = "VARCHAR(40) NOT NULL",
            KEY_AMOUNT_TYPE = "VARCHAR(40)",
            KEY_CONTENT_TYPE = "CLOB (2K)",
            KEY_TYPE_TYPE = "VARCHAR(20) NOT NULL";
    public final static int AMOUNT_INDEX = 0,
            ID_INDEX = 1,
            DATE_INDEX = 2,
            CONTENT_INDEX = 3,
            TYPE_INDEX = 4;
    public final static String TYPE_CONSUME = "consume",
            TYPE_RAISE = "raise";
}
