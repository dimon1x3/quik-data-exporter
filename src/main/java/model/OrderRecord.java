package model;

/**
 * Created by dimo on 16.07.2019.
 */
public class OrderRecord {
    private long order_number;
    private String tp_code;
    private String placed_at;
    private String period;
    private String paper_name;
    private String ticker;
    private String operation;
    private String l_account;
    private double price;
    private double num_lots;
    private double vis_num_lots;
    private double ostatok;
    private double volume;
    private String status;
    private String comm;
    private String class_code;
    public OrderRecord(long order_number, String tp_code, String placed_at, String period, String paper_name, String ticker,
                       String operation, String l_account, double price, double num_lots, double vis_num_lots, double ostatok,
                       double volume, String status, String comm, String class_code
                  ) {
        this.setOrder_number(order_number);
        this.setTp_code(tp_code);
        this.setPlaced_at(placed_at);
        this.setPeriod(period);
        this.setPaper_name(paper_name);
        this.setTicker(ticker);
        this.setOperation(operation);
        this.setL_account(l_account);
        this.setPrice(price);
        this.setNum_lots(num_lots);
        this.setVis_num_lots(vis_num_lots);
        this.setOstatok(ostatok);
        this.setVolume(volume);
        this.setStatus(status);
        this.setComm(comm);
        this.setClass_code(class_code);
    }

    public long getOrder_number() {
        return order_number;
    }

    public void setOrder_number(long order_number) {
        this.order_number = order_number;
    }

    public String getTp_code() {
        return tp_code;
    }

    public void setTp_code(String tp_code) {
        this.tp_code = tp_code;
    }

    public String getPlaced_at() {
        return placed_at;
    }

    public void setPlaced_at(String placed_at) {
        this.placed_at = placed_at;
    }

    public String getPeriod() {
        return period;
    }

    public void setPeriod(String period) {
        this.period = period;
    }

    public String getPaper_name() {
        return paper_name;
    }

    public void setPaper_name(String paper_name) {
        this.paper_name = paper_name;
    }

    public String getTicker() {
        return ticker;
    }

    public void setTicker(String ticker) {
        this.ticker = ticker;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public String getL_account() {
        return l_account;
    }

    public void setL_account(String l_account) {
        this.l_account = l_account;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getNum_lots() {
        return num_lots;
    }

    public void setNum_lots(double num_lots) {
        this.num_lots = num_lots;
    }

    public double getVis_num_lots() {
        return vis_num_lots;
    }

    public void setVis_num_lots(double vis_num_lots) {
        this.vis_num_lots = vis_num_lots;
    }

    public double getOstatok() {
        return ostatok;
    }

    public void setOstatok(double ostatok) {
        this.ostatok = ostatok;
    }

    public double getVolume() {
        return volume;
    }

    public void setVolume(double volume) {
        this.volume = volume;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getComm() {
        return comm;
    }

    public void setComm(String comm) {
        this.comm = comm;
    }

    public String getClass_code() {
        return class_code;
    }

    public void setClass_code(String class_code) {
        this.class_code = class_code;
    }
}
