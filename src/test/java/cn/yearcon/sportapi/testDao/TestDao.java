package cn.yearcon.sportapi.testDao;

import cn.yearcon.sportapi.dao.BillDao;
import cn.yearcon.sportapi.dao.VipInfoDao;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Map;


/**
 * @author itguang
 * @create 2018-01-07 13:51
 **/
@RunWith(SpringRunner.class)
@SpringBootTest
public class TestDao {

    @Autowired
    private VipInfoDao vipInfoDao;

    @Autowired
    private BillDao billDao;
    @Test
    public void test(){
        Map resultList=vipInfoDao.findByVipid(389856);
        System.out.println(resultList);
        /*Integer i=vipInfoDao.updateInfo(389856,"ayong","M",null,"123456");
        System.out.println(i);*/
    }

    @Test
    public void testBill(){
        List list=billDao.findByVipid(150);
        System.out.println(list);
    }

    @Test
    public void testReg(){
        String mobile="13058720637";//"18878008227";
        String name="测试";
        String sex="M";
        Integer birthday=19951111;
        Integer storeid=2378;
        String password="";
        try {
            int[] arr=vipInfoDao.insertInfo(mobile,name,sex,birthday,storeid,password);
            System.out.println(arr);
        }catch (Exception e){
            e.printStackTrace();
        }


    }
}
