package application;

import basicTypes.OraConnection;
import basicTypes.OraStoredProc;
import basicTypes.StoredProcParam;
import model.OrderRecord;
import oracle.jdbc.OracleResultSet;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class BigLadderBot {
    private static OraConnection conn = new OraConnection("DIMO","ilovedroid","127.0.0.1","1521","ORABET");
    private static OraStoredProc getOrders = new OraStoredProc("DIMO","PACK200", "GETORDERS", conn);
    private static OraStoredProc getTrades = new OraStoredProc("DIMO","PACK200", "GETTRADES", conn);
    private static OraStoredProc getAccStat = new OraStoredProc("DIMO","PACK200", "GETACCSTAT", conn);
    private static OraStoredProc getCBOMLastPrice = new OraStoredProc("DIMO","PACK200","GETCBOMLASTPRICE",conn);
    private static OraStoredProc getTransId = new OraStoredProc("DIMO","PACK200","GETTRANSID",conn);
    static {
        getOrders.addNewParam(new StoredProcParam("RES","OUT","REF"));
        getTrades.addNewParam(new StoredProcParam("RES","OUT","REF"));
        getAccStat.addNewParam(new StoredProcParam("RES","OUT","REF"));
        getCBOMLastPrice.addNewParam(new StoredProcParam("LAST_PRICE","OUT","NUMBER"));
        getTransId.addNewParam(new StoredProcParam("TRANS_ID","OUT","INTEGER"));
    }
    public static void main(String[] args) {
        Timer timer = new Timer();
        Timer timer2 = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                try {
                    getCBOMLastPrice.execute();
                    float last_price = getCBOMLastPrice.getParamByName("LAST_PRICE").asFloat();
                    System.out.println(last_price);
                    doTheLadder(last_price);
                }
                catch (SQLException e){
                    e.printStackTrace();
                }
            }
        }, 5000, 5000);
        timer2.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                // Your database code here
            }
        }, 1000, 1000);
    }
    public static void doTheLadder (float initPrice){
        Random random = new Random();
        String quantity = String.valueOf(random.nextInt());
        String action="NEW_ORDER";
        String account="L00+00000218";
        long trans_id=0;
        String classcode="TQBR";
        String client_code="PROGRESS_01";
        String seccode = "CBOM";
        String operation;
        String price;
        double bidPrice=initPrice;
        double offerPrice=initPrice+0.001;
        for (int i=0; i<BigLadderBotConf.numStairs; i++) {
            Writer output;
            operation="B";
            quantity=String.valueOf(getRandomNumberInRange(1,3));
            price=String.valueOf(bidPrice);
            bidPrice=bidPrice-0.001;
            offerPrice=offerPrice+0.001;
            try {
                getTransId.execute();
                trans_id=getTransId.getParamByName("TRANS_ID").asFloat().longValue();
                getTransId.clearParams();
            }
            catch (SQLException e){
                e.printStackTrace();
            }
            String s = "ACTION=".concat(action.toUpperCase()).concat(";")
                    .concat("ACCOUNT=").concat(account.toUpperCase()).concat(";")
                    .concat("TRANS_ID=").concat(Long.toString(trans_id)).concat(";")
                    .concat("CLASSCODE=").concat(classcode.toUpperCase()).concat(";")
                    .concat("SECCODE=").concat(seccode.toUpperCase()).concat(";")
                    .concat("OPERATION=").concat(operation.toUpperCase()).concat(";")
                    .concat("QUANTITY=").concat(quantity).concat(";")
                    .concat("CLIENT_CODE=").concat(client_code.toUpperCase()).concat(";")
                    .concat("PRICE=").concat(price.replace(',','.')).concat(";")
                    .concat("\n");
            try {
                output = new BufferedWriter(new FileWriter("C:/orders/orders.tri", true));
                output.append(s);
                output.close();
            }
            catch (IOException e){
                e.printStackTrace();
                // return "exception during appending line to file "+e.getMessage();
            }
            operation="S";
            quantity=String.valueOf(getRandomNumberInRange(1,4));
            price=String.valueOf(offerPrice);
            try {
                getTransId.execute();
                trans_id=getTransId.getParamByName("TRANS_ID").asFloat().longValue();
                getTransId.clearParams();
            }
            catch (SQLException e){
                e.printStackTrace();
            }
            s = "ACTION=".concat(action.toUpperCase()).concat(";")
                    .concat("ACCOUNT=").concat(account.toUpperCase()).concat(";")
                    .concat("TRANS_ID=").concat(Long.toString(trans_id)).concat(";")
                    .concat("CLASSCODE=").concat(classcode.toUpperCase()).concat(";")
                    .concat("SECCODE=").concat(seccode.toUpperCase()).concat(";")
                    .concat("OPERATION=").concat(operation.toUpperCase()).concat(";")
                    .concat("QUANTITY=").concat(quantity).concat(";")
                    .concat("CLIENT_CODE=").concat(client_code.toUpperCase()).concat(";")
                    .concat("PRICE=").concat(price.replace(',','.')).concat(";")
                    .concat("\n");
            try {
                output = new BufferedWriter(new FileWriter("C:/orders/orders.tri", true));
                output.append(s);
                output.close();
            }
            catch (IOException e){
                e.printStackTrace();
                // return "exception during appending line to file "+e.getMessage();
            }
        }

    }
    private static void randomCancel() {
        ArrayList<OrderRecord> al = new ArrayList<>();
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
                if (al.size()<5) return;
                else {
                    cancelOrder(String.valueOf(al.get(getRandomNumberInRange(0,al.size()-1))));
                }

            }
            deptResultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    private static int getRandomNumberInRange(int min, int max) {

        if (min >= max) {
            throw new IllegalArgumentException("max must be greater than min");
        }

        Random r = new Random();
        return r.nextInt((max - min) + 1) + min;
    }
    private static void cancelOrder(String ordernumber){
        String account="L00+00000218";
        long trans_id=0;
        String classcode="TQBR";
        String client_code="PROGRESS_01";
        String seccode = "CBOM";
        try {
            getTransId.execute();
            trans_id=getTransId.getParamByName("TRANS_ID").asFloat().longValue();
            getTransId.clearParams();;
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        String s = "ACTION=KILL_ORDER".concat(";")
                .concat("TRANS_ID=").concat(String.valueOf(trans_id).toUpperCase()).concat(";")
                .concat("CLASSCODE=").concat(classcode.toUpperCase()).concat(";")
                .concat("SECCODE=").concat(seccode.toUpperCase()).concat(";")
                .concat("ORDER_KEY=").concat(ordernumber.toUpperCase()).concat(";")
                .concat("\n");
        Writer output;
        try {
            output = new BufferedWriter(new FileWriter("C:/orders/orders.tri", true));
            output.append(s);
            output.close();
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }

}
