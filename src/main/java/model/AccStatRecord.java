package model;

public class AccStatRecord {
    private String firm;
    private String l_account;
    private String ticker;
    private String client_code;
    private String lim_type;
    private double position;
    private double bal_price;
    private double curr_price;
    private double curr_volume;
    private double proc_act;
    private double npl;
    private double lock_buy;
    private double lock_sell;
    private double buy_lim;
    private double buy_sell;
    public AccStatRecord(String firm, String l_account, String ticker, String client_code,
                         String lim_type, double position, double bal_price, double curr_price,
                         double curr_volume, double proc_act, double npl, double lock_buy,
                         double lock_sell, double buy_lim, double buy_sell){
        this.setFirm(firm);
        this.setL_account(l_account);
        this.setTicker(ticker);
        this.setClient_code(client_code);
        this.setLim_type(lim_type);
        this.setPosition(position);
        this.setBal_price(bal_price);
        this.setCurr_price(curr_price);
        this.setCurr_volume(curr_volume);
        this.setProc_act(proc_act);
        this.setNpl(npl);
        this.setLock_buy(lock_buy);
        this.setLock_sell(lock_sell);
        this.setBuy_lim(buy_lim);
        this.setBuy_sell(buy_sell);
    }

    public String getFirm() {
        return firm;
    }

    public void setFirm(String firm) {
        this.firm = firm;
    }

    public String getL_account() {
        return l_account;
    }

    public void setL_account(String l_account) {
        this.l_account = l_account;
    }

    public String getTicker() {
        return ticker;
    }

    public void setTicker(String ticker) {
        this.ticker = ticker;
    }

    public String getClient_code() {
        return client_code;
    }

    public void setClient_code(String client_code) {
        this.client_code = client_code;
    }

    public String getLim_type() {
        return lim_type;
    }

    public void setLim_type(String lim_type) {
        this.lim_type = lim_type;
    }

    public double getPosition() {
        return position;
    }

    public void setPosition(double position) {
        this.position = position;
    }

    public double getBal_price() {
        return bal_price;
    }

    public void setBal_price(double bal_price) {
        this.bal_price = bal_price;
    }

    public double getCurr_price() {
        return curr_price;
    }

    public void setCurr_price(double curr_price) {
        this.curr_price = curr_price;
    }

    public double getCurr_volume() {
        return curr_volume;
    }

    public void setCurr_volume(double curr_volume) {
        this.curr_volume = curr_volume;
    }

    public double getProc_act() {
        return proc_act;
    }

    public void setProc_act(double proc_act) {
        this.proc_act = proc_act;
    }

    public double getNpl() {
        return npl;
    }

    public void setNpl(double npl) {
        this.npl = npl;
    }

    public double getLock_buy() {
        return lock_buy;
    }

    public void setLock_buy(double lock_buy) {
        this.lock_buy = lock_buy;
    }

    public double getLock_sell() {
        return lock_sell;
    }

    public void setLock_sell(double lock_sell) {
        this.lock_sell = lock_sell;
    }

    public double getBuy_lim() {
        return buy_lim;
    }

    public void setBuy_lim(double buy_lim) {
        this.buy_lim = buy_lim;
    }

    public double getBuy_sell() {
        return buy_sell;
    }

    public void setBuy_sell(double buy_sell) {
        this.buy_sell = buy_sell;
    }
}
