package cn.yearcon.sportapi.mapper;

import cn.yearcon.sportapi.entity.Coupon;
import cn.yearcon.sportapi.entity.Integral;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.Date;
import java.util.List;

public interface VipMapper {
    @Select("SELECT t.id\n" +
            "FROM c_vip t\n" +
            "WHERE t.mobil = '${mobile}'")
    String findByMobile(@Param("mobile")String mobile);

    @Select("SELECT a.changdate,a.description,a.integral,\n" +
            "  (SELECT SUM(b.integral) from  fa_vipintegral_ftp b\n" +
            "  where b.c_vip_id=a.c_vip_id and  b.changdate<=a.changdate) as leaveintegral\n" +
            "FROM fa_vipintegral_ftp a\n" +
            "\n" +
            "WHERE a.changdate BETWEEN  ${begin}\n" +
            "      AND  ${end}\n" +
            "      AND a.c_vip_id = ${id}\n")
    List<Integral> findIntegerList(@Param("id") Integer id,
                                   @Param("begin") String begin,@Param("end") String end);

    @Select("SELECT\n" +
            "  nvl(t.integral, 0) as integral\n" +
            "FROM c_vip t\n" +
            "WHERE t.id = ${id}")
    String findIntegralByVipid(@Param("id")Integer id);



    @Select("SELECT a.vouchers_no,\n" +
            "  a.valid_date,\n" +
            "  a.c_vip_id,\n" +
            "  a.vou_dis,\n" +
            "  (CASE\n" +
            "   WHEN a.amt_acount >0 THEN\n" +
            "     a.amt_acount\n" +
            "   ELSE\n" +
            "     a.vou_dis\n" +
            "   END) AS amt_type,\n" +
            "  a.id,\n" +
            "  a.amt_noles,\n" +
            "  (CASE\n" +
            "   WHEN c.c_cusrank_id = 2 THEN\n" +
            "     c.id\n" +
            "   ELSE\n" +
            "     c.c_customerup_id\n" +
            "   END) AS v_cus\n" +
            "FROM c_vouchers a\n" +
            "  LEFT JOIN c_client_vip b\n" +
            "    ON (a.c_vip_id = b.id)\n" +
            "  LEFT JOIN c_customer c\n" +
            "    ON (a.c_customer_id = c.id)\n" +
            "WHERE b.id = ${vipid} and  a.is_verifyed ='N' AND    a.is_valid='Y' AND  a.isactive='Y'")
    List<Coupon> findCoupon(@Param("vipid")Integer vipid);
    @Update("update c_client_vip set IS_VERIFY =2,VERIFYDATE =sysdate\n" +
            "WHERE id=${vipid}")
    int updateAuthorize(@Param("vipid")Integer vipid);
}
