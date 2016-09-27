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
    public static String KEY_ID = "id",
            KEY_DATE = "dateInput",
            KEY_AMOUNT = "amount",
            KEY_CONTENT = "content";
    public static String KEY_ID_TYPE = "VARCHAR(30) PRIMARY KEY NOT NULL",
            KEY_DATE_TYPE = "VARCHAR(10) NOT NULL",
            KEY_AMOUNT_TYPE = "VACHAR(10)",
            KEY_CONTENT_TYPE = "BLOB(64000)";
    public static int ID = 0,
            DATE = 1,
            AMOUNT = 2,
            CONTENT = 3;
}
