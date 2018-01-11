package cn.yearcon.sportapi.testMapper;

import cn.yearcon.sportapi.entity.CStore;
import cn.yearcon.sportapi.mapper.CStoreMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * 测试mapper
 *
 * @author itguang
 * @create 2018-01-05 16:26
 **/
@RunWith(SpringRunner.class)
@SpringBootTest
public class testCStoreMapper {
    @Autowired
    private CStoreMapper cStoreMapper;

    @Test
    public void testList() {
        List<CStore> list=cStoreMapper.findByWebid("191");
        for (CStore cStore:list){
            System.out.println(cStore);
        }
    }


}
