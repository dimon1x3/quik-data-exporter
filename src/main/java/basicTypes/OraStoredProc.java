package basicTypes;
import java.sql.Connection;
import oracle.jdbc.OracleCallableStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import basicTypes.StoredProcParam;
import basicTypes.OraToTypesConverter;
import java.sql.DatabaseMetaData;
import oracle.jdbc.OracleResultSet;
import java.sql.Types;

public class OraStoredProc {
    private String procName;
    private String packageName;
    private String schemaName;
    private String callStatement;
    private OracleCallableStatement cstmt;
    private Connection procConnection;;
    private ArrayList<StoredProcParam> params;
    private DatabaseMetaData dbMetaData;
    public void addNewParam(StoredProcParam param){
     //   params.add(param);
    }
    public void close(){
        try {
            cstmt.close();
        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }
    public OraStoredProc(String schemaName,String packageName,String procName,OraConnection procConnection){
        this.procConnection=procConnection.getConnection();
        this.procName=procName;
        this.packageName=packageName;
        this.schemaName=schemaName;
        try {
            dbMetaData = this.procConnection.getMetaData();
            params=new ArrayList<StoredProcParam>();
            OracleResultSet rs=(OracleResultSet)dbMetaData.getProcedureColumns(packageName,schemaName,procName, "%");
            while (rs.next()) {
                params.add(parseMetadataToStoredProcParam(rs.getString(4) + " " + rs.getString(5) + " " + rs.getString(7)));
            }
            createCallStatement();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
    public void fetchParams() throws SQLException {

    }
    public StoredProcParam parseMetadataToStoredProcParam(String mdString) throws SQLException {
        String tmpStr="";
        String paramName;
        Integer parIOType;
        String paramIOOraType;
        String paramValue;
        ArrayList<String> al = new ArrayList<String>();
        for (int i=0;i<mdString.length();i++) {

            if (mdString.charAt(i)==' ') {
                al.add(tmpStr);
                tmpStr="";
            }

            else {
                tmpStr=tmpStr+(mdString.substring(i,i+1));
            }
        }
        al.add(tmpStr);
        parIOType=new Integer(al.get(1));
        paramName=al.get(0);
        paramValue=al.get(2);
        OracleCallableStatement cstmt = (OracleCallableStatement)procConnection.prepareCall("begin dimo.jc_pack.getJCStringParam(?,?,?);  end;");
        cstmt.setString(1, new String("OracleDatabaseMetaData"));
        cstmt.setInt(2, parIOType.intValue());
        cstmt.registerOutParameter(3,Types.VARCHAR);
        cstmt.execute( );
        paramIOOraType=cstmt.getString(3);
        cstmt.close();
        return new StoredProcParam(paramName, paramIOOraType, paramValue);
    }
    public StoredProcParam getParamByName(String param_name){
        for (int i=0; i<params.size(); i++){
            if (params.get(i).getParamName().equals(param_name)){
                return params.get(i);
            }
        }
        return new StoredProcParam("NOT_FOUND", "0", "NOT_FOUND");
    }
    public void createCallStatement(){
        String tmpStr;
        tmpStr="begin "+schemaName+"."+packageName+"."+procName+"(";
        for (int i=0; i<params.size(); i++){
            if (i!=(params.size()-1)) {
                tmpStr=tmpStr+"?,";
            }
            else {tmpStr=tmpStr+"?); end;";};
        }
        callStatement=tmpStr;
    }
    public void prepareCallStatement()throws SQLException {
        cstmt=(OracleCallableStatement)procConnection.prepareCall(callStatement);
        for (int i=0;i<params.size();i++){
           if (params.get(i).getParamIOOraType().equals(new String("RETURN"))){
                cstmt.registerOutParameter(i+1, OraToTypesConverter.convert(params.get(i).getParamOraType()));
                System.out.println("RETURN");
            }
            if (params.get(i).getParamIOOraType().equals(new String("OUT"))){
                cstmt.registerOutParameter(i, OraToTypesConverter.convert(params.get(i).getParamOraType()));
                System.out.println("OUT");
            }
            if (params.get(i).getParamIOOraType().equals(new String("IN"))){
			/*if (params.get(i).getParamValue().getClass().toString().equals("class java.sql.Date")){
				System.out.println("PREPARED");
				cstmt.setDate(i+1, java.sql.Date.valueOf("2011-06-13"));
			}
			else {*/
                cstmt.setObject(i+1, params.get(i).getParamValue());//}


            }
        }
    }
    public void execute() throws SQLException{
        System.out.println(callStatement);
        prepareCallStatement();
        cstmt.execute();

        for (int i=0;i<params.size();i++){
            if (params.get(i).getParamIOOraType().equals("RETURN")){
                params.get(i).setParamValue(cstmt.getObject(i+1));
            }
        }
      //  cstmt.close();
    }
    public void clearParams(){
        //params.clear();
    }
}
