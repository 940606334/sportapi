package cn.yearcon.sportapi.dao;

import oracle.jdbc.internal.OracleTypes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.CallableStatementCallback;
import org.springframework.jdbc.core.CallableStatementCreator;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 获取账单信息
 *  调用存储过程
 * @author itguang
 * @create 2018-01-02 11:03
 **/
@Repository
public class BillDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List findByVipid(Integer id){
        List resultList = (List) jdbcTemplate.execute(
                new CallableStatementCreator() {
                    public CallableStatement createCallableStatement(Connection con) throws SQLException {
                        String storedProc = "{call yek_vip_retailftp(?,?)}";// 调用的sql
                        CallableStatement cs = con.prepareCall(storedProc);
                        cs.setInt(1, id);// 设置输入参数的值
                        cs.registerOutParameter(2, OracleTypes.CURSOR);// 注册输出参数的类型
                        return cs;
                    }
                }, new CallableStatementCallback() {
                    public Object doInCallableStatement(CallableStatement cs) throws SQLException,DataAccessException {
                        List resultsMap = new ArrayList();
                        cs.execute();
                        ResultSet rs = (ResultSet) cs.getObject(2);// 获取游标一行的值
                        while (rs.next()) {// 转换每行的返回值到Map中
                            Map rowMap = new HashMap();
                            rowMap.put("name", rs.getString("name"));//店仓名称
                            rowMap.put("no",rs.getString("no"));//营业员
                            rowMap.put("salesid",rs.getString("salesid"));
                            rowMap.put("docno",rs.getString("docno"));
                            rowMap.put("billdate",rs.getString("billdate"));
                            rowMap.put("tot_intergral",rs.getString("tot_intergral"));
                            rowMap.put("integral",rs.getString("integral"));
                            rowMap.put("tot_amt_actual",rs.getString("tot_amt_actual"));
                            rowMap.put("mptno",rs.getString("mptno"));
                            resultsMap.add(rowMap);
                        }
                        rs.close();
                        return resultsMap;
                    }
                });
        return resultList;
    }
}
