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
import model.consumeModel;

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
        HomeLayout.run();
        //consumeModel con = new consumeModel();
        //con.getConnect();
        //con.createTable();
        //System.out.println(con.getAllTableConsumeInDB().getRowCount());
        //con.closeConnect();
        //
    }
    
    
}
