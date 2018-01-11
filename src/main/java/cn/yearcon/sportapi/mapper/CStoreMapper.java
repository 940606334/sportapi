package cn.yearcon.sportapi.mapper;

import cn.yearcon.sportapi.entity.CStore;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;

public interface CStoreMapper {
    @Select("SELECT a.id,\n" +
            "  a.name,\n" +
            "  a.address,\n" +
            "  a.license,\n" +
            "  a.phone,\n" +
            "  a.coordinate\n" +
            "FROM c_store a\n" +
            "WHERE  a.isretail = 'Y'\n" +
            "       AND a.isactive = 'Y'\n" +
            "       AND (a.c_customer_id =${webid} OR a.c_customerup_id =${webid})\n" +
            "       AND a.c_storeattrib1_id IS NULL"+
            "       AND a.coordinate IS NOT NULL")
    List<CStore> findByWebid(@Param("webid") String webid);

}
