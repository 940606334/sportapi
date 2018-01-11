package cn.yearcon.sportapi.testService;

import cn.yearcon.sportapi.Service.StoreService;
import cn.yearcon.sportapi.entity.CStore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * @author itguang
 * @create 2018-01-07 10:55
 **/
@RunWith(SpringRunner.class)
@SpringBootTest
public class testStore {
    @Autowired
    private StoreService storeService;

    @Test
    public void test(){
        List<CStore> list=storeService.getStoreList("191","28.165407,120.395994");
        for (CStore cStore:list){
            System.out.println(cStore);
        }
    }
}
