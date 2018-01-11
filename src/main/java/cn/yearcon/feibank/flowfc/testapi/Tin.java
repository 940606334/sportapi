package cn.yearcon.feibank.flowfc.testapi;

import cn.yearcon.feibank.flowfc.tools.AESUtil;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.cxf.jaxrs.client.WebClient;

import javax.ws.rs.core.Response;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

public class Tin extends Thread{
private static ObjectMapper objectMapper = new ObjectMapper();
	
	public void run() {
    	
    	try{
    
    	 WebClient client = WebClient.create("http://111.1.6.4:8080/zhixun/voice/openCard");
         // 非常重要
         client.type("application/json;charset=UTF-8");
    	 
         String tradeNo =  AESUtil.getTradeNo();
         Map<String, Object> parameters = new HashMap<String, Object>();
         parameters.put("tradeNo", "20150605101116323169");//必填参数
         parameters.put("userName", "wolunTest");//必填参数
         parameters.put("userPassword","test0000");//必填参数
         parameters.put("money","1");
         parameters.put("phone", "13567148561");
         String sign=AESUtil.encrypt(objectMapper.writeValueAsString(parameters),"9dmq2613fIXVvCpf");
         parameters.put("sign", sign);
         parameters.put("userPassword", AESUtil.MD5("test0000"));//必填参数
         String body = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(parameters);
         printJsonString("开卡请求", body);
         Response response = client.post(body);
         printResult("开卡完成", response);
    	}catch (Exception e) {
			e.printStackTrace();
		}
    }
    private static JsonNode printResult(String phase, Response response) {
        System.out.println("\n=== " + phase + " ===");

        try {
            InputStream stream = (InputStream) response.getEntity();
            int available = 0;
            available = stream.available();

            if (available == 0) {
                System.out.println("nothing returned, response code: " + response.getStatus());
                return null;
            }
            JsonNode responseNode = objectMapper.readTree(stream);
            if (true) {
                System.out.println(objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(responseNode));
            } else {
                System.out.println(objectMapper.writeValueAsString(responseNode));
            }
            return responseNode;
        } catch (IOException e) {
            System.err.println("catch an exception: " + e.getMessage());
            e.printStackTrace();
        }
        return null;
    }
	   private static void printJsonString(String phase, String json) {
	        System.out.println("\n+++ 发送请求[" + phase + "] +++");
	        System.out.println(json);
	    }
	    
}
