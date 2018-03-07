package cn.yearcon.sportapi.Service;

import cn.yearcon.sportapi.dao.IntegralDao;
import cn.yearcon.sportapi.json.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author ayong
 * @create 2018-03-07 10:37
 **/
@Service
public class IntegralService {
    @Autowired
    private IntegralDao integralDao;

    /**
     * 添加积分
     * @param vipid 会员id
     * @param integral 增加积分
     * @param remark 备注
     * @return
     */
    public  JsonResult addIntegral(Integer vipid,Integer integral,String remark){
        Integer code=integralDao.addIntegral(vipid,integral,remark);
        if(code==1){
            return new JsonResult(code,"成功添加"+integral+"积分");
        }else{
            return new JsonResult(code,"添加积分失败");
        }
    }

    /**
     * 消费积分
     * @param vipid 会员id
     * @param remark 备注说明
     * @param integral 花费积分
     * @param docno 订单号
     * @return
     */
    public JsonResult costIntegral(Integer vipid,String remark,Integer integral,String docno){
        String msg=integralDao.costIntegral(vipid,remark,integral,docno);
        if("TRUE".equals(msg.toUpperCase())){
            return new JsonResult(1,"成功消费"+integral+"积分");
        }else{
            return new JsonResult(0,"花费积分失败");
        }
    }

}
