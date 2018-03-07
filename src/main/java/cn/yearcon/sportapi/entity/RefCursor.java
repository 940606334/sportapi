package cn.yearcon.sportapi.entity;


import lombok.Data;

import javax.persistence.*;

/**
 * @author itguang
 * @create 2017-12-31 10:08
 **/
@NamedStoredProcedureQuery(name="yek_vip_retailftp", procedureName = "yek_vip_retailftp",parameters= {
        @StoredProcedureParameter(mode= ParameterMode.IN, type=Integer.class, name="v_vip_id"),
        @StoredProcedureParameter(mode=ParameterMode.OUT, type=RefCursor.class, name="ref_cursor")
})
@Data
@Entity
@Table(name="ref_cursor")
public class RefCursor {
   @Id
   private Integer c_vip_id;//vipid
   private String name;//	店仓名称
   private String no;	//营业员
   private String  salesid;	//营业员id
   private String  docno;	//单据编号
   private String billdate;	//单据日期
   private String tot_intergral;//	新增积分
   private String  integral;	//抵扣积分
   private String tot_amt_actual;	//交易金额

   @Override
   public String toString() {
      return "refCursor{" +
              "c_vip_id=" + c_vip_id +
              ", name='" + name + '\'' +
              ", no='" + no + '\'' +
              ", salesid='" + salesid + '\'' +
              ", docno='" + docno + '\'' +
              ", billdate='" + billdate + '\'' +
              ", tot_intergral='" + tot_intergral + '\'' +
              ", integral='" + integral + '\'' +
              ", tot_amt_actual='" + tot_amt_actual + '\'' +
              '}';
   }
}
