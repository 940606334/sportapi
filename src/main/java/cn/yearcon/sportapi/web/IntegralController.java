package cn.yearcon.sportapi.web;

import cn.yearcon.sportapi.Service.IntegralService;
import cn.yearcon.sportapi.Service.VipInfoService;
import cn.yearcon.sportapi.entity.Integral;
import cn.yearcon.sportapi.json.JsonResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ayong
 * @create 2018-03-07 10:52
 **/
@RestController
@Api(description = "ERP积分变动接口")
public class IntegralController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private VipInfoService vipInfoService;
    @Autowired
    private IntegralService integralService;

    @ApiOperation(value = "获取积分想详情", notes = "根据vipid获取")
    @RequestMapping(value="integral.list",method = {RequestMethod.GET,RequestMethod.POST})
    public JsonResult findIntegralList(Integer vipid){
        JsonResult jsonResult=vipInfoService.findIntegralList(vipid);
        logger.info(jsonResult.toString());
        return jsonResult;
    }
    @ApiOperation(value = "获取积分余额", notes = "根据vipid获取余额")
    @RequestMapping(value="integral.num",method = {RequestMethod.GET,RequestMethod.POST})
    public JsonResult findIntegralByVipid(Integer vipid){
        JsonResult jsonResult=vipInfoService.findIntegralByVipid(vipid);
        logger.info(jsonResult.toString());
        return jsonResult;
    }
    @ApiOperation(value = "增加积分", notes = "根据vipid增加积分")
    @RequestMapping(value="integral.add",method = {RequestMethod.GET,RequestMethod.POST})
    public JsonResult addIntegral(Integer vipid, Integer integral,String remark){
        JsonResult jsonResult=integralService.addIntegral(vipid,integral,remark);
        logger.info(jsonResult.toString());
        return jsonResult;
    }
    @ApiOperation(value = "扣除积分", notes = "根据vipid扣除积分")
    @RequestMapping(value="integral.cost",method = {RequestMethod.GET,RequestMethod.POST})
    public JsonResult costIntegral(Integer vipid, Integer integral,String remark,String docno){
        JsonResult jsonResult=integralService.costIntegral(vipid,remark,integral,docno);
        logger.info(jsonResult.toString());
        return jsonResult;
    }
}
