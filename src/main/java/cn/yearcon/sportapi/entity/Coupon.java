package cn.yearcon.sportapi.entity;

import lombok.Data;

/**
 * 优惠券
 *
 * @author itguang
 * @create 2018-01-08 9:48
 **/
@Data
public class Coupon {
    private String vouchers_no;//优惠券号
    private String v_cus;//机构代码
    private String amt_noles;//满多少可用
    private Double amt_type;//<1折扣 >1现金
    private Double vou_dis;//折扣内容
    private String valid_date;//有效期


}
