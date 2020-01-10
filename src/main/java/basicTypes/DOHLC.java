//класс Дата-Опен-Хай-Лоу-Клоуз
package basicTypes;
import java.sql.Date;
public class DOHLC {
    public DOHLC(DOHLC dohlc){
        this.priceDate=dohlc.getPriceDate();
        this.priceOpen=dohlc.getPriceOpen();
        this.priceHigh=dohlc.getPriceHigh();
        this.priceLow=dohlc.getPriceLow();
        this.priceClose=dohlc.getPriceClose();
    }
    public DOHLC(Date priceDate, Float priceOpen, Float priceHigh, Float priceLow, Float priceClose){
        this.priceDate=priceDate;
        this.priceOpen=priceOpen;
        this.priceHigh=priceHigh;
        this.priceLow=priceLow;
        this.priceClose=priceClose;
    }

    private Date priceDate;
    private Float priceOpen;
    private Float priceHigh;
    private Float priceLow;
    private Float priceClose;
    public void setPriceDate(Date newPriceDate) {
        priceDate=newPriceDate;
    }
    public Date getPriceDate(){
        return priceDate;
    }
    public void setPriceOpen(Float newPriceOpen){
        priceOpen=newPriceOpen;
    }
    public void setPriceHigh(Float newPriceHigh){
        priceHigh=newPriceHigh;
    }
    public float getPriceHigh(){
        return priceHigh;
    }
    public Float getPriceOpen(){
        return priceOpen;
    }
    public void setPriceLow(Float newPriceLow){
        priceLow=newPriceLow;
    }
    public Float getPriceLow(){
        return priceLow;
    }
    public void setPriceClose(Float newPriceClose){
        priceClose=newPriceClose;
    }
    public Float getPriceClose(){
        return priceClose;
    }
}
