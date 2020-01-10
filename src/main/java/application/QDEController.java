package application;

import basicTypes.DateTime;
import basicTypes.OraConnection;
import basicTypes.OraStoredProc;
import basicTypes.StoredProcParam;
import model.AccStatRecord;
import model.OrderRecord;
import model.TradeRecord;
import oracle.jdbc.OracleResultSet;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by dimo on 16.07.2019.
 */
@Controller
public class QDEController {
    private static OraConnection conn = new OraConnection("DIMO","ilovedroid","127.0.0.1","1521","ORABET");
    private static OraStoredProc getOrders = new OraStoredProc("DIMO","PACK200", "GETORDERS", conn);
    private static OraStoredProc getTrades = new OraStoredProc("DIMO","PACK200", "GETTRADES", conn);
    private static OraStoredProc getAccStat = new OraStoredProc("DIMO","PACK200", "GETACCSTAT", conn);
    static {
        getOrders.addNewParam(new StoredProcParam("RES","OUT","REF"));
        getTrades.addNewParam(new StoredProcParam("RES","OUT","REF"));
        getAccStat.addNewParam(new StoredProcParam("RES","OUT","REF"));
    }

        @GetMapping("/getorders")
        @ResponseBody
        public ArrayList<OrderRecord> getOrders(@RequestParam(value="key", defaultValue="null") String key) {
            ArrayList<OrderRecord> al=new ArrayList<>();
            System.out.println("key is "+key);
            if (key.equals("jkfdgkdsauiryiq3243487ufhdjvbxb88y3y4rgfsb9098ijhfsbdvidf7yf7iyshf"))
            {
                System.out.println(key);
                try {
                    getOrders.fetchParams();
                    getOrders.execute();
                    OracleResultSet deptResultSet = getOrders.getParamByName("RES").asResultSet();
                    while (deptResultSet.next()) {
                        OrderRecord order = new OrderRecord(
                                deptResultSet.getLong(1),   //order_number
                                deptResultSet.getString(2),//tp_code
                                deptResultSet.getString(3),//placed_at
                                deptResultSet.getString(4),//period
                                deptResultSet.getString(5),//paper_name
                                deptResultSet.getString(6),//ticker
                                deptResultSet.getString(7),//operation
                                deptResultSet.getString(8),//l_account
                                deptResultSet.getDouble(9), //price
                                deptResultSet.getDouble(10),//num_lots
                                deptResultSet.getDouble(11),//vis_num_lots
                                deptResultSet.getDouble(12),//ostatok
                                deptResultSet.getDouble(13),//volume
                                deptResultSet.getString(14),//status
                                deptResultSet.getString(15),//comm
                                deptResultSet.getString(16)//class_code
                        );
                        al.add(order);
                    }
                    deptResultSet.close();
                }
                catch (SQLException e){
                    e.printStackTrace();
                  return null;
                }
                getOrders.close();
                return al;
            }
            else {
                return null;
            }
        }
    @GetMapping("/gettrades")
    @ResponseBody
    public ArrayList<TradeRecord> getTrades(@RequestParam(value="key", defaultValue="null") String key){
        ArrayList<TradeRecord> al=new ArrayList<>();
        if (key.equals("jkfdgkdsauiryiq3243487ufhdjvbxb88y3y4rgfsb9098ijhfsbdvidf7yf7iyshf"))
        {
            System.out.println(key);
            try {
                getTrades.fetchParams();
                getTrades.execute();
                OracleResultSet deptResultSet = getTrades.getParamByName("RES").asResultSet();
                while (deptResultSet.next()) {
                    TradeRecord trade = new TradeRecord(
                            deptResultSet.getString(1),   //ttime
                            deptResultSet.getString(2),//direction
                            deptResultSet.getString(3),//v_account
                            deptResultSet.getString(4),//ticker
                            deptResultSet.getDouble(5),//price
                            deptResultSet.getDouble(6),//lots
                            deptResultSet.getDouble(7),//volume
                            deptResultSet.getLong(8),//trade_id
                            deptResultSet.getString (9)+" "+deptResultSet.getString(1),//l_account
                            deptResultSet.getString(10), //class_code
                            deptResultSet.getString(11)//client_code
                    );
                    al.add(trade);
                }
                deptResultSet.close();
            }
            catch (SQLException e){
                e.printStackTrace();
                return null;
            }
            getTrades.close();
            return al;
        }
        return null;
    }
    @GetMapping("/getaccstat")
    @ResponseBody
    public ArrayList<AccStatRecord> getAccStat(@RequestParam(value="key", defaultValue="null") String key){
        ArrayList<AccStatRecord> al=new ArrayList<>();
        if (key.equals("jkfdgkdsauiryiq3243487ufhdjvbxb88y3y4rgfsb9098ijhfsbdvidf7yf7iyshf"))
        {
            System.out.println(key);
            try {
                getAccStat.fetchParams();
                getAccStat.execute();
                OracleResultSet deptResultSet = getAccStat.getParamByName("RES").asResultSet();
                while (deptResultSet.next()) {
                    AccStatRecord accStatRecord = new AccStatRecord(
                            deptResultSet.getString(1),//firm
                            deptResultSet.getString(2),//l_account
                            deptResultSet.getString(3),//ticker
                            deptResultSet.getString(4),//client_code
                            deptResultSet.getString(5),//lim_type
                            deptResultSet.getDouble(6),//position
                            deptResultSet.getDouble(7),//bal_price
                            deptResultSet.getDouble(8),//curr_price
                            deptResultSet.getDouble(9),//curr_volume
                            deptResultSet.getDouble(10),//proc_act
                            deptResultSet.getDouble(11),//npl
                            deptResultSet.getDouble(12),//lock_buy
                            deptResultSet.getDouble(13),//lock_sell
                            deptResultSet.getDouble(14),//buy_lim
                            deptResultSet.getDouble(15)//buy_sell
                    );
                    al.add(accStatRecord);
                }
                deptResultSet.close();
            }
            catch (SQLException e){
                e.printStackTrace();
                return null;
            }
            getAccStat.close();
            return al;
        }
        return null;
    }
    @PostMapping("/postorder")
    @ResponseBody
   // ?ACTION=NEW_ORDER&ACCOUNT=L01-00000F00&TRANS_ID=38990311&CLASSCODE=TQBR&SECCODE=CBOM&OPERATION=S&QUANTITY=9&CLIENT_CODE=35868&PRICE=5,475
   public String postOrder(@RequestParam(value="ACTION", defaultValue="null") String action,
                           @RequestParam(value="ACCOUNT", defaultValue="null") String account,
                           @RequestParam(value="TRANS_ID", defaultValue="null") String trans_id,
                           @RequestParam(value="CLASSCODE", defaultValue="null") String classcode,
                           @RequestParam(value="SECCODE", defaultValue="null") String seccode,
                           @RequestParam(value="OPERATION", defaultValue="null") String operation,
                           @RequestParam(value="QUANTITY", defaultValue="null") String quantity,
                           @RequestParam(value="CLIENT_CODE", defaultValue="null") String client_code,
                           @RequestParam(value="PRICE", defaultValue="null") String price
                           ){
        String s = "ACTION=".concat(action.toUpperCase()).concat(";")
                .concat("ACCOUNT=").concat(account.toUpperCase()).concat(";")
                .concat("TRANS_ID=").concat(trans_id.toUpperCase()).concat(";")
                .concat("CLASSCODE=").concat(classcode.toUpperCase()).concat(";")
                .concat("SECCODE=").concat(seccode.toUpperCase()).concat(";")
                .concat("OPERATION=").concat(operation.toUpperCase()).concat(";")
                .concat("QUANTITY=").concat(quantity).concat(";")
                .concat("CLIENT_CODE=").concat(client_code.toUpperCase()).concat(";")
                .concat("PRICE=").concat(price.replace(',','.')).concat(";")
                .concat("MARKET_MAKER_ORDER=YES")
                .concat("\n");
        Writer output;
        try {
            output = new BufferedWriter(new FileWriter("C:/orders/orders.tri", true));
            output.append(s);
            output.close();
        }
        catch (IOException e){
            e.printStackTrace();
            return "exception during appending line to file "+e.getMessage();
        }
        return "success";
    }
    @PostMapping("/cancelorder")
    @ResponseBody
// ?ACTION=NEW_ORDER&ACCOUNT=L01-00000F00&TRANS_ID=38990311&CLASSCODE=TQBR&SECCODE=CBOM&OPERATION=S&QUANTITY=9&CLIENT_CODE=35868&PRICE=5,475
    public String cancelOrder(@RequestParam(value="TRANS_ID", defaultValue="null") String trans_id,
                              @RequestParam(value="CLASSCODE", defaultValue="null") String classcode,
                              @RequestParam(value="SECCODE", defaultValue="null") String seccode,
                              @RequestParam(value="ORDER_KEY", defaultValue="null") String order_key
    ){
        String s = "ACTION=KILL_ORDER".concat(";")
                .concat("TRANS_ID=").concat(trans_id.toUpperCase()).concat(";")
                .concat("CLASSCODE=").concat(classcode.toUpperCase()).concat(";")
                .concat("SECCODE=").concat(seccode.toUpperCase()).concat(";")
                .concat("ORDER_KEY=").concat(order_key.toUpperCase()).concat(";")
                .concat("\n");
        Writer output;
        try {
            output = new BufferedWriter(new FileWriter("C:/orders/orders.tri", true));
            output.append(s);
            output.close();
        }
        catch (IOException e){
            e.printStackTrace();
            return "exception during appending line to file "+e.getMessage();
        }
        return "success";
    }
}
