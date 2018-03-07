package cn.yearcon.sportapi.dao;

import oracle.jdbc.internal.OracleTypes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.CallableStatementCallback;
import org.springframework.jdbc.core.CallableStatementCreator;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.w3c.dom.CDATASection;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 获取会员详情
 *  调用存储过程
 * @author itguang
 * @create 2018-01-07 13:37
 **/
@Repository
public class VipInfoDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public Map findByVipid(Integer id){
        Map map = (Map) jdbcTemplate.execute(
                new CallableStatementCreator() {
                    public CallableStatement createCallableStatement(Connection con) throws SQLException {
                        String storedProc = "{call yek_vip_mall_filter(?,?)}";// 调用的sql
                        CallableStatement cs = con.prepareCall(storedProc);
                        cs.setInt(1, id);// 设置输入参数的值
                        cs.registerOutParameter(2, OracleTypes.CURSOR);// 注册输出参数的类型
                        return cs;
                    }
                }, new CallableStatementCallback() {
                    public Object doInCallableStatement(CallableStatement cs) throws SQLException,DataAccessException {
                        //List resultsMap = new ArrayList();
                        cs.execute();
                        ResultSet rs = (ResultSet) cs.getObject(2);// 获取游标一行的值
                        Map rowMap = new HashMap();
                        while (rs.next()) {// 转换每行的返回值到Map中
                            rowMap.put("id", rs.getString("id"));//会员id
                            rowMap.put("cardno", rs.getString("cardno"));//卡号
                            rowMap.put("opencarddate",rs.getString("opencarddate"));//开卡时间
                            rowMap.put("mobil",rs.getString("mobil"));//手机
                            rowMap.put("vipname",rs.getString("vipname"));//姓名
                            rowMap.put("sex",rs.getString("sex"));//性别
                            rowMap.put("age",rs.getString("age"));//年龄
                            rowMap.put("building",rs.getString("building"));//寄送地址
                            rowMap.put("viptype",rs.getString("viptype"));//卡类型
                            rowMap.put("lastdate",rs.getString("lastdate"));//最后消费时间
                            rowMap.put("birthday",rs.getString("birthday"));//生日
                            rowMap.put("vipstate",rs.getString("vipstate"));//状态Y –可用  N-不可用
                            rowMap.put("integral",rs.getString("integral"));//积分余额
                            rowMap.put("c_store_id",rs.getString("c_store_id"));//店铺id
                            rowMap.put("c_customer_id",rs.getString("c_customer_id"));//机构id
                            rowMap.put("pass_word",rs.getString("pass_word"));//支付密码
                            rowMap.put("storename",rs.getString("storename"));//店铺名称
                            //resultsMap.add(rowMap);
                        }
                        rs.close();
                        return rowMap;
                    }
                });
        return map;
    }
    public Integer updateInfo(Integer id,String name,String sex,String building,String password){
        Integer i=(Integer) jdbcTemplate.execute(
                new CallableStatementCreator() {
                    public CallableStatement createCallableStatement(Connection con) throws SQLException {
                        String storedProc = "{call yek_vip_mall_upde(?,?,?,?,?,?,?,?,?,?,?)}";// 调用的sql
                        CallableStatement cs = con.prepareCall(storedProc);
                        cs.setInt(1, id);// 设置输入参数的值
                        cs.setString(2,name);
                        cs.setString(3,sex);
                        cs.setInt(4,0);
                        cs.setString(5,building);
                        cs.setInt(6,0);
                        cs.setInt(7,0);
                        cs.setInt(8,0);
                        cs.setInt(9,0);
                        cs.setString(10,password);
                        cs.registerOutParameter(11, OracleTypes.NUMBER);// 注册输出参数的类型
                        return cs;
                    }
                }, new CallableStatementCallback() {
                    public Object doInCallableStatement(CallableStatement cs) throws SQLException,DataAccessException {
                        cs.execute();
                        Integer rs=cs.getInt(11);
                        cs.close();
                        return rs;
                    }
                });
        return i;
    }
    public int[] insertInfo(String mobile,String name,String sex,Integer birthday,Integer storeid,String password){
        int[] arr= (int[]) jdbcTemplate.execute(
                new CallableStatementCreator() {
                    public CallableStatement createCallableStatement(Connection con) throws SQLException {
                        String storedProc = "{call yek_vipmall_register(?,?,?,?,?,?,?,?,?,?,?,?)}";// 调用的sql
                        CallableStatement cs = con.prepareCall(storedProc);
                        cs.setString(1, mobile);// 设置输入参数的值
                        cs.setString(2,mobile);
                        cs.setString(3,name);
                        cs.setString(4,sex);
                        cs.setInt(5,birthday);
                        cs.setString(6,null);
                        cs.setString(7,null);
                        cs.setInt(8,0);
                        cs.setInt(9,storeid);
                        cs.setString(10,password);
                        cs.registerOutParameter(11, OracleTypes.NUMBER);// 注册输出参数的类型
                        cs.registerOutParameter(12, OracleTypes.NUMBER);// 注册输出参数的类型
                        return cs;
                    }
                }, new CallableStatementCallback() {
                    public Object doInCallableStatement(CallableStatement cs) throws SQLException,DataAccessException {
                         cs.execute();
                         int[] rs=new int[2];
                         rs[0]=cs.getInt(11);
                         rs[1]=cs.getInt(12);
                         cs.close();
                        return rs;
                    }
                });
        return arr;
    }
}
