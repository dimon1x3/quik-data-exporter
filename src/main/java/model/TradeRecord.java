package model;

import basicTypes.DateTime;

public class TradeRecord {
    private String ttime;
    private String direction;
    private String v_account;
    private String ticker;
    private double price;
    private double lots;
    private double volume;
    private long trade_id;
    private String trade_date;
    private String class_code;
    private String client_code;
    public TradeRecord (String ttime, String direction, String v_account, String ticker,
                        double price, double lots, double volume, long trade_id, String trade_date,
                        String class_code, String client_code) {
        this.setTtime(ttime);
        this.setDirection(direction);
        this.setV_account(v_account);
        this.setTicker(ticker);
        this.setPrice(price);
        this.setLots(lots);
        this.setVolume(volume);
        this.setTrade_id(trade_id);
        this.setTrade_date(trade_date);
        this.setClass_code(class_code);
        this.setClient_code(client_code);
    }

    public String getTtime() {
        return ttime;
    }

    public void setTtime(String ttime) {
        this.ttime = ttime;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public String getV_account() {
        return v_account;
    }

    public void setV_account(String v_account) {
        this.v_account = v_account;
    }

    public String getTicker() {
        return ticker;
    }

    public void setTicker(String ticker) {
        this.ticker = ticker;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getLots() {
        return lots;
    }

    public void setLots(double lots) {
        this.lots = lots;
    }

    public double getVolume() {
        return volume;
    }

    public void setVolume(double volume) {
        this.volume = volume;
    }

    public long getTrade_id() {
        return trade_id;
    }

    public void setTrade_id(long trade_id) {
        this.trade_id = trade_id;
    }

    public String getTrade_date() {
        return trade_date;
    }

    public void setTrade_date(String trade_date) {
        this.trade_date = trade_date;
    }

    public String getClass_code() {
        return class_code;
    }

    public void setClass_code(String class_code) {
        this.class_code = class_code;
    }

    public String getClient_code() {
        return client_code;
    }

    public void setClient_code(String client_code) {
        this.client_code = client_code;
    }
}
