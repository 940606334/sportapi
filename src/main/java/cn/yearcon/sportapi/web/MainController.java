package cn.yearcon.sportapi.web;

import cn.yearcon.sportapi.Service.BillService;
import cn.yearcon.sportapi.Service.SmsCodeService;
import cn.yearcon.sportapi.Service.StoreService;
import cn.yearcon.sportapi.Service.VipInfoService;
import cn.yearcon.sportapi.entity.CStore;
import cn.yearcon.sportapi.json.JsonResult;
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
public class MainController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private BillService billService;


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
    @RequestMapping("/user.edit")
    public JsonResult editInfo(Integer vipid,String name,String sex,String building,String password){
        JsonResult jsonResult=vipInfoService.update(vipid,name,sex,building,password);
        logger.info(jsonResult.toString());
        return jsonResult;
    }
    @Autowired
    private StoreService storeService;
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
    @RequestMapping(value = "user.id")
    public JsonResult findVipidByMobile(String mobile){
        JsonResult jsonResult=vipInfoService.findByMobile(mobile);
        return  jsonResult;
    }
    @RequestMapping(value="integral.list")
    public JsonResult findIntegralList(Integer vipid){

        JsonResult jsonResult=vipInfoService.findIntegralList(vipid);
        logger.info(jsonResult.toString());
        return jsonResult;
    }
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
    @RequestMapping(value="user.reg")
    public JsonResult insertVipInfo(String checkcode,String mobile,String name,String sex,Integer birthday,Integer storeid,String password){
        JsonResult jsonResult=vipInfoService.insertVip(mobile,name,sex,birthday,storeid,password,checkcode);
        logger.info(jsonResult.toString());
        return jsonResult;
    }

    @RequestMapping(value="voucher.list")
    public JsonResult findCupon(Integer vipid){
        JsonResult jsonResult=vipInfoService.findCoupon(vipid);
        logger.info(jsonResult.toString());
        return jsonResult;
    }
    @Autowired
    private SmsCodeService smsCodeService;

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
    @RequestMapping(value = "vip.authorize")
    public JsonResult authorizeVip(Integer vipid){
       return vipInfoService.updateAuthorize(vipid);
    }
}
