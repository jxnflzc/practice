package per.jxnflzc.practice.handler;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import per.jxnflzc.practice.model.enums.CodeEnum;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

public class String2ListTypeHandler extends BaseTypeHandler<List<String>> {
    private static final String SPLIT_CHARACTER = "、";
    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, List<String> parameter, JdbcType jdbcType)
            throws SQLException {
        StringBuffer sb = new StringBuffer();
        for (String s : parameter) {
            sb.append(s).append(SPLIT_CHARACTER);
        }
        ps.setString(i, sb.toString().substring(0, sb.toString().length() - 1));
    }

    @Override
    public List<String> getNullableResult(ResultSet rs, String columnName) throws SQLException {
        String[] arr = rs.getString(columnName).split(SPLIT_CHARACTER);
        return Arrays.asList(arr);
    }

    @Override
    public List<String> getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        String[] arr = rs.getString(columnIndex).split(SPLIT_CHARACTER);
        return Arrays.asList(arr);
    }

    @Override
    public List<String> getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        String[] arr = cs.getString(columnIndex).split(SPLIT_CHARACTER);
        return Arrays.asList(arr);
    }
}
