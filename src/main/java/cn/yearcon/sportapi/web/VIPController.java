package cn.yearcon.sportapi.web;

import cn.yearcon.sportapi.Service.VipInfoService;
import cn.yearcon.sportapi.json.JsonResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * 会员信息
 *
 * @author ayong
 * @create 2018-03-06 16:46
 **/
@Api(description = "ERP会员信息接口")
@RestController
public class VIPController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private VipInfoService vipInfoService;
    @ApiOperation(value = "获取会员信息", notes = "根据vipid获取")
    @RequestMapping(value = "/user.detail",method = {RequestMethod.GET,RequestMethod.POST})
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
    @RequestMapping(value = "/user.edit",method = {RequestMethod.GET,RequestMethod.POST})
    public JsonResult editInfo(Integer vipid,String name,String sex,String building,String password){
        JsonResult jsonResult=vipInfoService.update(vipid,name,sex,building,password);
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
    @RequestMapping(value="user.reg",method = {RequestMethod.GET,RequestMethod.POST})
    public JsonResult insertVipInfo(String checkcode,String mobile,String name,String sex,Integer birthday,Integer storeid,String password){
        JsonResult jsonResult=vipInfoService.insertVip(mobile,name,sex,birthday,storeid,password,checkcode);
        logger.info(jsonResult.toString());
        return jsonResult;
    }
    @ApiOperation(value = "获取vipid", notes = "根据手机号获取")
    @RequestMapping(value = "user.id",method = {RequestMethod.GET,RequestMethod.POST})
    public JsonResult findVipidByMobile(String mobile){
        JsonResult jsonResult=vipInfoService.findByMobile(mobile);
        return  jsonResult;
    }
    /**
     * 会员认证
     * @return
     */
    @ApiOperation(value = "会员认证", notes = "根据vipid修改会员认证状态")
    @RequestMapping(value = "vip.authorize", method={RequestMethod.GET,RequestMethod.POST})
    public JsonResult authorizeVip(Integer vipid){
        return vipInfoService.updateAuthorize(vipid);
    }
}
