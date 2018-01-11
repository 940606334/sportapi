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
   private Integer id;//优惠券id
    private  String v_cus;//机构代码
    private String amt_noles;//满多少可用
    private String amt_type;//1折扣 2现金
    private String amtdes;//折扣内容
    private String amt;//实际值

}
