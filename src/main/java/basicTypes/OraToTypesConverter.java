package basicTypes;
import java.sql.Types;

import oracle.jdbc.OracleTypes;
public class OraToTypesConverter {
    public static Integer convert(String oraType){
        if (oraType.equals("VARCHAR")){
            return Types.VARCHAR;
        } else
        if (oraType.equals("VARCHAR2")){
            return Types.VARCHAR;
        }else
        if (oraType.equals("NUMBER")){
            return Types.FLOAT;
        } else
        if (oraType.equals("REF CURSOR")){
            return OracleTypes.CURSOR;
        } else
        if (oraType.equals("REF")) {
            return OracleTypes.CURSOR;
        }
        if (oraType.equals("INTEGER")) {
            return OracleTypes.INTEGER;
        }
        return new Integer(-1);
    }
}
