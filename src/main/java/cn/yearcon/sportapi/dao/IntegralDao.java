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
import java.sql.SQLException;
import java.util.List;

/**
 * @author ayong
 * @create 2018-03-07 9:01
 **/
@Repository
public class IntegralDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    /**
     * 扣除积分
     * @param v_vipid 会员id
     * @param v_description 说明
     * @param v_integral 扣除积分
     * @param v_docno 订单号
     * @return
     */
    public String costIntegral(Integer v_vipid,String v_description,Integer v_integral,String v_docno){
       String msg=(String) jdbcTemplate.execute(new CallableStatementCreator() {
            public CallableStatement createCallableStatement(Connection con) throws SQLException {
                String storedProc = "{call yek_vip_ftp_jfsc(?,?,?,?,?)}";// 调用的sql
                CallableStatement cs = con.prepareCall(storedProc);
                cs.setInt(1, v_vipid);// 设置输入参数的值 vipid
                cs.setString(2,v_description); //说明
                cs.setInt(3,v_integral); //变动积分
                cs.setString(4,v_docno); //订单号
                cs.registerOutParameter(5, OracleTypes.VARCHAR);// 注册输出参数的类型
                return cs;
            }
        }, new CallableStatementCallback() {
            public Object doInCallableStatement(CallableStatement cs) throws SQLException,DataAccessException {
                cs.execute();
                String message=cs.getString(5);
                cs.close();
                return message;
            }
        });
        return msg;
    }

    /**
     * 增加积分
     * @param v_vipid 会员id
     * @param v_integral 增加积分
     * @param v_demo  说明
     * @return
     */
    public Integer addIntegral(Integer v_vipid,Integer v_integral,String v_demo){
        Integer code=(Integer) jdbcTemplate.execute(new CallableStatementCreator() {
            public CallableStatement createCallableStatement(Connection con) throws SQLException {
                String storedProc = "{call yek_integral_get(?,?,?,?)}";// 调用的sql
                CallableStatement cs = con.prepareCall(storedProc);
                cs.setInt(1, v_vipid);// 设置输入参数的值 vipid
                cs.setInt(2,v_integral); //变动积分
                cs.setString(3,v_demo); //订单号
                cs.registerOutParameter(4, OracleTypes.NUMBER);// 注册输出参数的类型
                return cs;
            }
        }, new CallableStatementCallback() {
            public Object doInCallableStatement(CallableStatement cs) throws SQLException,DataAccessException {
                cs.execute();
                Integer message=cs.getInt(4);
                cs.close();
                return message;
            }
        });
        return code;
    }
}
