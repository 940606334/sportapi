package cn.yearcon.sportapi.web;

import cn.yearcon.sportapi.Service.BillService;
import cn.yearcon.sportapi.Service.SmsCodeService;
import cn.yearcon.sportapi.Service.StoreService;
import cn.yearcon.sportapi.Service.VipInfoService;
import cn.yearcon.sportapi.entity.CStore;
import cn.yearcon.sportapi.json.JsonResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


/**
 * ERP接口
 *
 * @author itguang
 * @create 2018-01-02 15:10
 **/
@RestController
@Api(description = "ERP接口")
public class MainController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private BillService billService;

    @ApiOperation(value = "获取账单信息列表", notes = "根据vipid获取")
    @RequestMapping("/retail.list")
    public JsonResult billList(Integer vipid){
        if(vipid==null){
            logger.debug("请输入vipid");
            return new JsonResult(0,"请输入vipid");
        }
        JsonResult jsonResult=billService.findById(vipid);
        logger.info(jsonResult.toString());
        return jsonResult;
    }
    @Autowired
    private VipInfoService vipInfoService;
    @ApiOperation(value = "获取用户信息", notes = "根据vipid获取")
    @RequestMapping("/user.detail")
    public JsonResult vipInfo(Integer vipid){
        if(vipid==null){
            logger.debug("请输入vipid");
            return new JsonResult(0,"请输入vipid");
        }
        JsonResult jsonResult=vipInfoService.findById(vipid);
        logger.info(jsonResult.toString());
        return jsonResult;
    }
    @ApiOperation(value = "修改会员信息", notes = "根据vipid修改")
    @RequestMapping("/user.edit")
    public JsonResult editInfo(Integer vipid,String name,String sex,String building,String password){
        JsonResult jsonResult=vipInfoService.update(vipid,name,sex,building,password);
        logger.info(jsonResult.toString());
        return jsonResult;
    }
    @Autowired
    private StoreService storeService;

    @ApiOperation(value = "获取店铺列表", notes = "根据机构id和坐标信息")
    @RequestMapping("store.list")
    public JsonResult storeList(String webid,String coordinate){
        if(webid==null||"".equals(webid)){
            logger.debug("请输入机构id");
            return new JsonResult(0,"请输入机构id");
        }
        /*if(coordinate==null||"".equals(coordinate)){
            logger.debug("请输入坐标");
            return new JsonResult(0,"请输入坐标");
        }*/
        List<CStore> list=storeService.getStoreList(webid,coordinate);
        logger.info(list.toString());
        JsonResult jsonResult=null;
        if (list==null){
            jsonResult=new JsonResult(0,"无相关店铺");
        }else{
            jsonResult=new JsonResult(1,list);
        }
        return jsonResult;
    }
    @ApiOperation(value = "获取vipid", notes = "根据手机号获取")
    @RequestMapping(value = "user.id")
    public JsonResult findVipidByMobile(String mobile){
        JsonResult jsonResult=vipInfoService.findByMobile(mobile);
        return  jsonResult;
    }
    @ApiOperation(value = "获取积分想详情", notes = "根据vipid获取")
    @RequestMapping(value="integral.list")
    public JsonResult findIntegralList(Integer vipid){

        JsonResult jsonResult=vipInfoService.findIntegralList(vipid);
        logger.info(jsonResult.toString());
        return jsonResult;
    }
    @ApiOperation(value = "获取积分余额", notes = "根据vipid获取余额")
    @RequestMapping(value="integral.num")
    public JsonResult findIntegralByVipid(Integer vipid){

        JsonResult jsonResult=vipInfoService.findIntegralByVipid(vipid);
        logger.info(jsonResult.toString());
        return jsonResult;
    }

    /**
     * 注册会员
     * @param mobile
     * @param name
     * @param sex
     * @param birthday
     * @param storeid
     * @param password
     * @return
     */
    @ApiOperation(value = "注册会员", notes = "注册会员")
    @RequestMapping(value="user.reg")
    public JsonResult insertVipInfo(String checkcode,String mobile,String name,String sex,Integer birthday,Integer storeid,String password){
        JsonResult jsonResult=vipInfoService.insertVip(mobile,name,sex,birthday,storeid,password,checkcode);
        logger.info(jsonResult.toString());
        return jsonResult;
    }
    @ApiOperation(value = "获取优惠券列表", notes = "根据vipid获取优惠券")
    @RequestMapping(value="voucher.list")
    public JsonResult findCupon(Integer vipid){
        JsonResult jsonResult=vipInfoService.findCoupon(vipid);
        logger.info(jsonResult.toString());
        return jsonResult;
    }
    @Autowired
    private SmsCodeService smsCodeService;
    @ApiOperation(value = "获取短信验证码", notes = "输入手机号获取短信验证码")
    @RequestMapping(value="code.get")
    public JsonResult getCheckCode(String mobile){
        JsonResult jsonResult=smsCodeService.getCheckCode(mobile);
        logger.info(jsonResult.toString());
        return jsonResult;
    }

    /**
     * 会员认证
     * @return
     */
    @ApiOperation(value = "会员认证", notes = "根据vipid修改会员认证状态")
    @RequestMapping(value = "vip.authorize")
    public JsonResult authorizeVip(Integer vipid){
       return vipInfoService.updateAuthorize(vipid);
    }
}
