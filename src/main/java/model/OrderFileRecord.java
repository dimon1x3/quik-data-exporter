package model;

public class OrderFileRecord {
    private String action;
    private String account;
    private String trans_id;
    private String classcode;
    private String seccode;
    private String operation;
    private String quantity;
    private String client_code;
    private String price;

    public OrderFileRecord(String action, String account, String trans_id,
                           String classcode, String seccode, String operation,
                           String quantity, String client_code, String price){
        this.setAction(action);
        this.setAccount(account);
        this.setTrans_id(trans_id);
        this.setClasscode(classcode);
        this.setSeccode(seccode);
        this.setOperation(operation);
        this.setQuantity(quantity);
        this.setClient_code(client_code);
        this.setPrice(price);
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getTrans_id() {
        return trans_id;
    }

    public void setTrans_id(String trans_id) {
        this.trans_id = trans_id;
    }

    public String getClasscode() {
        return classcode;
    }

    public void setClasscode(String classcode) {
        this.classcode = classcode;
    }

    public String getSeccode() {
        return seccode;
    }

    public void setSeccode(String seccode) {
        this.seccode = seccode;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getClient_code() {
        return client_code;
    }

    public void setClient_code(String client_code) {
        this.client_code = client_code;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
