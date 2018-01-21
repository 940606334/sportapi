package cn.yearcon.sportapi.Service;

import cn.yearcon.sportapi.entity.CStore;
import cn.yearcon.sportapi.mapper.CStoreMapper;
import cn.yearcon.sportapi.util.DistanceUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author itguang
 * @create 2018-01-03 9:49
 **/
@Service
public class StoreService {
    private Logger logger= LoggerFactory.getLogger(this.getClass());
    @Autowired
    private CStoreMapper cStoreMapper;
    @Autowired
    private RedisTemplate redisTemplate;

    public List<CStore> getStoreList(String webid,String coordinate){
        List<CStore> list=(List)redisTemplate.opsForValue().get("getStoreByWebid="+webid);
        if(list!=null){
            logger.info("读取缓存中的商店列表");
            try {
                logger.info("获取地理坐标为:"+coordinate);
                setCStoreList(list,coordinate);
            }catch (Exception e){
                logger.info(e.getMessage());
            }
            return list;
        }
        list=cStoreMapper.findByWebid(webid);
        logger.info("商店列表设置缓存10分钟");
        redisTemplate.opsForValue().set("getStoreByWebid="+webid,list,60*10, TimeUnit.SECONDS);
        logger.info("获取地理坐标为:"+coordinate);
       try {
           setCStoreList(list,coordinate);
       }catch (Exception e){
           logger.info(e.getMessage());
       }
       return list;
    }
    public void setCStoreList(List<CStore> list,String coordinate){
        String[] arr=coordinate.split(",");
        double lng1=Double.parseDouble(arr[1]);
        double lat1=Double.parseDouble(arr[0]);
        String[] arr2=null;
        for(CStore cStore:list){
            String coordinate2=cStore.getCoordinate();
            arr2=coordinate2.split(",");
            double lng2=Double.parseDouble(arr2[1]);
            double lat2=Double.parseDouble(arr2[0]);
            cStore.setDistance(DistanceUtil.getDistance(lng1,lat1,lng2,lat2));
        }
        Collections.sort(list,new Comparator<CStore>(){
            @Override
            public int compare(CStore o1, CStore o2) {
                int i= (int) (o1.getDistance()*1000-o2.getDistance()*1000);
                return i;
            }
        });

    }

}
