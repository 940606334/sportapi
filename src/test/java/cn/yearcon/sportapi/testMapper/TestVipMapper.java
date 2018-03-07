package cn.yearcon.sportapi.testMapper;

import cn.yearcon.sportapi.dao.IntegralDao;
import cn.yearcon.sportapi.entity.Integral;
import cn.yearcon.sportapi.mapper.VipMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.criteria.CriteriaBuilder;
import javax.xml.stream.events.EndDocument;
import java.util.Date;
import java.util.List;

/**
 * @author itguang
 * @create 2018-01-07 15:44
 **/
@RunWith(SpringRunner.class)
@SpringBootTest
public class TestVipMapper {
    @Autowired
    private VipMapper vipMapper;

    @Test
    public void test(){
        String begin="20170606";
        String end="20180808";
        List<Integral> list=vipMapper.findIntegerList(376957,begin,end);
        for (Integral integer:list){
            System.out.println(integer);
        }
    }
    @Test
    public void testFindIntegral(){
        String integral = vipMapper.findIntegralByVipid(389856);
        System.out.println(integral);
    }
    @Autowired
    private IntegralDao integralDao;
    @Test
    public void testChangeIntegral(){
        /*String msg=integralDao.costIntegral(389856,"测试",-10,"RE1710190005654");
        System.out.println(msg);*/
        Integer code=integralDao.addIntegral(389856,10,"测试");
        System.out.println(code);
    }
}
