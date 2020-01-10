package basicTypes;
import oracle.jdbc.OracleResultSet;
public class StoredProcParam {
    private String paramName;
    private String paramIOOraType;
    private String paramOraType;
    private Object paramValue;
    public StoredProcParam(String paramName, String paramIOOraType, String paramOraType){
        setParamName(paramName);
        setParamIOOraType(paramIOOraType);
        setParamOraType(paramOraType);
    }
    public String getParamName() {
        return paramName;
    }
    public void setParamName(String paramName) {
        this.paramName = paramName;
    }
    public String getParamIOOraType() {
        return paramIOOraType;
    }
    public void setParamIOOraType(String paramIOOraType) {
        this.paramIOOraType = paramIOOraType;
    }
    public String getParamOraType() {
        return paramOraType;
    }
    public void setParamOraType(String paramOraType){
        this.paramOraType=paramOraType;
    }
    public void setParamValue(Object paramValue) {
        this.paramValue=paramValue;
    }
    public String asString(){
        return getParamValue().toString();
    }
    public Integer asInteger(){
        return new Integer(getParamValue().toString());
    }


    public Float asFloat(){
        return new Float(getParamValue().toString());
    }
    public Object getParamValue() {
        return paramValue;
    }
    public OracleResultSet asResultSet(){
        return (OracleResultSet) paramValue;
    }

//public void setParamValue(Object paramValue) {
//	this.paramValue = paramValue;
//}
}
