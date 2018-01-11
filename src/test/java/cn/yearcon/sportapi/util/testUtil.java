package cn.yearcon.sportapi.util;

import lombok.experimental.var;
import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @author itguang
 * @create 2018-01-07 16:07
 **/
public class testUtil {

    @Test
    public void test1(){
        Date date =new Date();
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyyMMdd");
        String begin=simpleDateFormat.format(date);
        Calendar calendar=Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.MONTH,6);
        date=calendar.getTime();
        String end=simpleDateFormat.format(date);
        System.out.println("begin="+begin+",end="+end);
    }
    @Test
    public void test2(){
        int a=0;
        StringBuffer str=new StringBuffer();
        for(int i=0;i<4;i++){
            a=(int) (Math.random()*10);
            str.append(a);
        }
        String code=str.toString();
        System.out.println(code);
    }
}
