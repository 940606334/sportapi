package cn.yearcon.sportapi.Service;

import cn.yearcon.sportapi.dao.VipInfoDao;
import cn.yearcon.sportapi.entity.Coupon;
import cn.yearcon.sportapi.entity.Integral;
import cn.yearcon.sportapi.json.JsonResult;
import cn.yearcon.sportapi.mapper.VipMapper;
import com.alibaba.druid.sql.ast.expr.SQLCaseExpr;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @author itguang
 * @create 2018-01-07 14:04
 **/
@Service
public class VipInfoService {
    @Autowired
    private VipInfoDao vipInfoDao;
    @Autowired
    private RedisTemplate redisTemplate;
    /**
     * 根据id查询会员信息
     * @param id
     * @return
     */
    public JsonResult findById(Integer id){

        Map item=vipInfoDao.findByVipid(id);
        if(item.isEmpty()){
            return  new JsonResult(0,"无会员信息");
        }else{
            return  new JsonResult(1,item);
        }
    }

    /**
     * 修改会员信息
     * @param id
     * @param name
     * @param sex
     * @param building
     * @param password
     * @return
     */
    public JsonResult update(Integer id,String name,String sex,String building,String password){
        if(id==null){

            return new JsonResult(0,"请输入vipid");
        }
        if(name==null||"".equals(name)){

            return new JsonResult(0,"请输入姓名");
        }
        if(sex==null||"".equals(sex)){

            return new JsonResult(0,"请输入性别");
        }
        if(password==null||"".equals(password)){

            return new JsonResult(0,"请输入密码");
        }
        Integer status=vipInfoDao.updateInfo(id,name,sex,building,password);
        if (status==1){
            return new JsonResult(status,"修改成功");
        }else{
            return new JsonResult(status,"修改失败");
        }
    }
    @Autowired
    VipMapper vipMapper;

    /**
     * 根据手机号查询vipid
     * @param mobile
     * @return
     */
    public JsonResult findByMobile(String mobile){
        if (mobile==null||"".equals(mobile)){
            return  new JsonResult(0,"请输入手机号");
        }
        String vipid=vipMapper.findByMobile(mobile);
        if(vipid==null||"".equals(vipid)){
            return new JsonResult(0,"该手机号未注册");
        }else{
            return new JsonResult(1,vipid);
        }
    }

    /**
     * 获取六个月内的积分详情
     * @param id
     * @return
     */
    public JsonResult findIntegralList(Integer id){
        if(id==null){
            return new JsonResult(0,"请输入vipid");
        }
        //获取当前时间,和六个月前的时间
        Date date =new Date();
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyyMMdd");
        String end=simpleDateFormat.format(date);
        Calendar calendar=Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.MONTH,-6);
        date=calendar.getTime();
        String begin=simpleDateFormat.format(date);
        List<Integral> integralList=(List<Integral>) redisTemplate.opsForValue().get("integralListById="+id);
        if(integralList!=null){
            System.out.println("读取缓存中的积分列表");
            return  new JsonResult(1,integralList);
        }
        List<Integral> list=vipMapper.findIntegerList(id,begin,end);
        if (list.isEmpty()){
            return new JsonResult(0,"六个月内无账单记录");
        }else{
            redisTemplate.opsForValue().set("integralListById="+id,list,60,TimeUnit.SECONDS);
            return new JsonResult(1,list);
        }
    }

    /**
     * 根据vipid获取积分余额
     * @param vipid
     * @return
     */
    public JsonResult findIntegralByVipid(Integer vipid){
        if(vipid==null){
            return new JsonResult(0,"请输入vipid");
        }
        String integral=vipMapper.findIntegralByVipid(vipid);
        return new JsonResult(1,integral);
    }

    public JsonResult insertVip(String mobile,String name,String sex,Integer birthday,Integer storeid,String password,String checkcode){

        if(mobile==null||"".equals(mobile)){
            return new JsonResult(0,"手机号不能为空");
        }
        if(name==null ||"".equals(name)){
            return new JsonResult(0,"姓名不能为空");
        }
        if(birthday==null ||"".equals(birthday)){
            return new JsonResult(0,"生日不能为空");
        }
        if(storeid==null){
            return new JsonResult(0,"门店id不能为空");
        }
        if(password==null){
            return new JsonResult(0,"密码不能为空");
        }
        String code=(Integer) redisTemplate.opsForValue().get("smscodeMobile="+mobile)+"";
        if(code==null||"".equals(code)){
            return new JsonResult(0,"验证码已失效");
        }
        if(!code.equals(checkcode)){
            return new JsonResult(0,"验证码不正确");
        }
        try {
            int[] arr=vipInfoDao.insertInfo(mobile,name,sex,birthday,storeid,password);
            String msg=null;
            switch (arr[0]){
                case -1:msg="您所输入的手机号码不满11位";
                    break;
                case -2:msg="系统手机号已存在";
                    break;
                case -3:msg="店铺不存在";
                    break;
                case -4:msg="系统卡号已经存在";
                    break;
                case 1:msg=arr[1]+"";//vipMapper.findByMobile(mobile);
                    break;
            }
            return new JsonResult(arr[0],msg);
        }catch (Exception e){
            e.printStackTrace();
            return new JsonResult(0,"门店信息错误");
        }

    }

    /**
     * 获取优惠券
     * @param vipid
     * @return
     */
    public JsonResult findCoupon(Integer vipid){
        if(vipid==null){
            return new JsonResult(0,"会员id为空");
        }
        List<Coupon> coupons=(List<Coupon>) redisTemplate.opsForValue().get("couponByVipid="+vipid);
        if(coupons!=null&&coupons.size()>0){
            System.out.println("读取缓存中的优惠券");
            return new JsonResult(1,coupons);
        }
        List<Coupon> list=vipMapper.findCoupon(vipid);
        if (list.size()==0){
            return new JsonResult(0,"无可用优惠券");
        }else{
            System.out.println("优惠券缓存1分钟");
            redisTemplate.opsForValue().set("couponByVipid="+vipid,list,60, TimeUnit.SECONDS);
            return new JsonResult(1,list);
        }
    }

    public JsonResult updateAuthorize(Integer vipid){
        if(vipid==null){
            return new JsonResult(0,"请输入vipid");
        }
        try {
            int i=vipMapper.updateAuthorize(vipid);
            return new JsonResult(i,"修改成功");
        }catch (Exception e){
            return new JsonResult(0,e.getMessage());
        }

    }

}
