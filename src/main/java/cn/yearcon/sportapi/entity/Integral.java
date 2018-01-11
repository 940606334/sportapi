package cn.yearcon.sportapi.entity;

import lombok.Data;

import javax.persistence.Table;
import java.util.Date;

/**
 * 积分详情
 *
 * @author itguang
 * @create 2018-01-07 15:34
 **/
@Data
@Table(name = "fa_vipintegral_ftp", schema = "NEANDS3", catalog = "")
public class Integral {
    private String changdate;//时间
    private Integer integral;//积分变动
    private String description;//变动说明
    private Integer leaveintegral;//积分余额

    @Override
    public String toString() {
        return "Integral{" +
                "changdate=" + changdate +
                ", integral=" + integral +
                ", description='" + description + '\'' +
                ", leaveintegral=" + leaveintegral +
                '}';
    }
}
