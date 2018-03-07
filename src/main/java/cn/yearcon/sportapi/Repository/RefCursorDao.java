package cn.yearcon.sportapi.Repository;


import cn.yearcon.sportapi.entity.RefCursor;
import org.springframework.data.jpa.repository.query.Procedure;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RefCursorDao  extends CrudRepository<RefCursor, Integer> {
    @Procedure(name="yek_vip_retailftp")
    List<RefCursor> findById(@Param("v_vip_id") Integer id);
}
