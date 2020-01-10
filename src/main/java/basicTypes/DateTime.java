package basicTypes;
import java.util.Calendar;;
public class DateTime {
    private int YEAR;
    private int MONTH;
    private int DAY;
    private int HOURS;
    private int MINUTES;
    private int SECONDS;
    @SuppressWarnings("unused")
    private int MSECONDS;
    public java.sql.Date toSQLDATE(){
        Calendar c = Calendar.getInstance();
        c.set(YEAR, MONTH-1, DAY, HOURS, MINUTES, SECONDS);
        return new java.sql.Date(c.getTime().getTime());
    }

    public static java.sql.Date now(){
        java.sql.Date d=new java.sql.Date(Calendar.getInstance().getTime().getTime());
        //d.setMonth(d.getMonth());
        return d;
    }
    public static DateTime stringToDateTime(String dateSep, String dateTimeSep, String timeSep, String stringDT ){
        System.out.println(stringDT);
        String [] dateTimeArr=stringDT.split(dateTimeSep);
        String [] dateArr = dateTimeArr[0].split("\\.");
        String [] timeArr = dateTimeArr[1].split(timeSep);
        String [] msecArr = timeArr[2].split("\\.");
        if (msecArr.length>0) {
            return new DateTime(new Integer(dateArr[0]).intValue(), new Integer(dateArr[1]).intValue(),
                    new Integer(dateArr[2]).intValue(), new Integer(timeArr[0]).intValue(),
                    new Integer(timeArr[1]).intValue(), new Integer(msecArr[0]).intValue(),
                    0);
        }
        else {
            return new DateTime(new Integer(dateArr[0]).intValue(), new Integer(dateArr[1]).intValue(),
                    new Integer(dateArr[2]).intValue(), new Integer(timeArr[0]).intValue(),
                    new Integer(timeArr[1]).intValue(), new Integer(0).intValue(),
                    0);
        }
    }
    public DateTime(int YEAR, int MONTH, int DAY, int HOURS, int MINUTES, int SECONDS, int MSECONDS){
        this.YEAR=YEAR;
        this.MONTH=MONTH;
        this.DAY=DAY;
        this.HOURS=HOURS;
        this.MINUTES=MINUTES;
        this.SECONDS=SECONDS;
    }
    public static DateTime sqlDateToDateTime(java.sql.Date sqlDate){
        Calendar c=Calendar.getInstance();
        c.setTime(sqlDate);
        return new DateTime(c.get(Calendar.YEAR),c.get(Calendar.MONTH)+1,c.get(Calendar.DATE),
                c.get(Calendar.HOUR_OF_DAY),c.get(Calendar.MINUTE), c.get(Calendar.SECOND),0);
    }
    public String dateTimeToString(String dateSep, String dateTimeSep, String timeSep){
        return new String(new Integer(this.YEAR).toString()).
                concat(dateSep).concat(new Integer(this.MONTH).toString()).
                concat(dateSep).concat(new Integer(this.DAY).toString()).
                concat(dateTimeSep).concat(new Integer(this.HOURS).toString()).
                concat(timeSep).
                concat(new Integer(this.MINUTES).toString()).
                concat(timeSep).concat(new Integer(this.SECONDS).toString()) ;
    }
    public String dateTimeToStringORARUS(String dateSep, String dateTimeSep, String timeSep){
        System.out.println("dateTimeToStringORARUS entering");
        System.out.println(this.YEAR);
        System.out.println(new String(new Integer(this.DAY).toString().
                concat(dateSep).concat(new Integer(this.MONTH).toString()).
                concat(dateSep).concat(new Integer(this.YEAR).toString()).
                concat(dateTimeSep).concat(new Integer(this.HOURS).toString()).
                concat(timeSep).
                concat(new Integer(this.MINUTES).toString()).
                concat(timeSep).concat(new Integer(this.SECONDS).toString())) );
        return new String(new Integer(this.DAY).toString().
                concat(dateSep).concat(new Integer(this.MONTH).toString()).
                concat(dateSep).concat(new Integer(this.YEAR).toString()).
                concat(dateTimeSep).concat(new Integer(this.HOURS).toString()).
                concat(timeSep).
                concat(new Integer(this.MINUTES).toString()).
                concat(timeSep).concat(new Integer(this.SECONDS).toString())) ;
    }
    public void add(int days){
        Calendar c = Calendar.getInstance();
        c.set(this.YEAR, this.MONTH-1, this.DAY, this.HOURS, this.MINUTES, this.SECONDS);
        c.add(Calendar.DATE , days);

        this.YEAR=c.get(Calendar.YEAR);
        this.MONTH=c.get(Calendar.MONTH)+1;
        this.DAY=c.get(Calendar.DATE);
        this.HOURS=c.get(Calendar.HOUR_OF_DAY);
        this.MINUTES=c.get(Calendar.MINUTE);
        this.SECONDS=c.get(Calendar.SECOND);
        this.MSECONDS=c.get(Calendar.MILLISECOND);
    }
    public java.util.Date toUtilDate(){
        Calendar c = Calendar.getInstance();
        c.set(this.YEAR, this.MONTH-1, this.DAY, this.HOURS, this.MINUTES, this.SECONDS);
        return c.getTime();
    }
}
