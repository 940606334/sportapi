package cn.yearcon.sportapi.Service;


import cn.yearcon.sportapi.dao.BillDao;
import cn.yearcon.sportapi.json.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * 账单信息业务层
 *
 * @author itguang
 * @create 2018-01-02 14:53
 **/
@Service
public class BillService {
    @Autowired
    private BillDao billDao;

    @Autowired
    private RedisTemplate redisTemplate;


    public JsonResult findById(Integer vipid){
        List list=(List)redisTemplate.opsForValue().get("BillVipid="+vipid);
        if(list!=null){
            System.out.println("有缓存,直接查询缓存");
            return new JsonResult(1,list);
        }
        List lists=billDao.findByVipid(vipid);
        if(lists.isEmpty()){
            return new JsonResult(0,"无消费记录");
        }
        System.out.println("无缓存,设置缓存60秒");
        redisTemplate.opsForValue().set("BillVipid="+vipid,lists,60, TimeUnit.SECONDS);
        return  new JsonResult(1,lists);
    }
}
