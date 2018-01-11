package cn.yearcon.sportapi.testDao;

import cn.yearcon.sportapi.dao.VipInfoDao;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


/**
 * @author itguang
 * @create 2018-01-07 13:51
 **/
@RunWith(SpringRunner.class)
@SpringBootTest
public class TestDao {

    @Autowired
    private VipInfoDao vipInfoDao;

    @Test
    public void test(){
        //List resultList=vipInfoDao.findByVipid(389856);
        //System.out.println(resultList);
        Integer i=vipInfoDao.updateInfo(389856,"ayong","M",null,"123456");
        System.out.println(i);
    }
}
