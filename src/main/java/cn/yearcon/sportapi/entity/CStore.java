package cn.yearcon.sportapi.entity;

import lombok.Data;

import javax.persistence.*;
import java.sql.Time;
import java.util.Objects;

/**
 * @author itguang
 * @create 2018-01-05 15:21
 **/
@Entity
@Data
@Table(name = "C_STORE", schema = "NEANDS3", catalog = "")
public class CStore {
    @Id
    private int id; //店铺id

    private String name;  //店铺名称

    private String address; //店铺地址

    private String license; //营业时间

    private String phone; //电话

    private String coordinate; //店铺坐标

    private Double distance; //距离 （km）

    //private String c_customer_id;

    @Override
    public String toString() {
        return "CStore{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", license='" + license + '\'' +
                ", phone='" + phone + '\'' +
                ", coordinate='" + coordinate + '\'' +
                ", distance=" + distance +
                '}';
    }
}
