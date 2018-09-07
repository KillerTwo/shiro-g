package org.lwt.typehandler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.TypeHandler;
import org.apache.shiro.crypto.hash.Md5Hash;

public class PasswordTypeHandler implements TypeHandler<String> {

    @Override
    public void setParameter(PreparedStatement ps, int i, String parameter, JdbcType jdbcType) throws SQLException {
        String encryPassword = new Md5Hash(parameter).toBase64();
        ps.setString(i, encryPassword);
    }

    @Override
    public String getResult(ResultSet rs, String columnName) throws SQLException {
        
        return rs.getString(columnName);
    }

    @Override
    public String getResult(ResultSet rs, int columnIndex) throws SQLException {
        
        return rs.getString(columnIndex);
    }

    @Override
    public String getResult(CallableStatement cs, int columnIndex) throws SQLException {
        
        return cs.getString(columnIndex);
    }

}
