package cn.yearcon.feibank.flowfc.tools;

import java.util.HashMap;
import java.util.Map;

/** 
* Pub工具类
* @author johnny 
*/ 
public class FlowConstants {
	
	public FlowConstants() {}
		 
	public static String DEFAULT_ENCODE="UTF-8";
		
	public static final String OPERATORS_CM = "CM";//中国移动
	
	public static final String OPERATORS_CU = "CU";//中国联通
	
	public static final String OPERATORS_CT = "CT";//中国电信
	
	public static Map<String,String> operatorsMap = null;
	static{
		operatorsMap = new HashMap<String,String>();
		operatorsMap.put(FlowConstants.OPERATORS_CM,"移动");
		operatorsMap.put(FlowConstants.OPERATORS_CU,"联通");
		operatorsMap.put(FlowConstants.OPERATORS_CT,"电信");
	}
	
	public static final String CARD_CHINA_CODE = "00";//可全国销售的区域代码
	
	public static final String DEFAULT_RESP_SUCC_CODE="P00000";//请求成功的返回代码rspCode
	
	public static final String default_user_name="test";
	public static final String default_user_pwd="11";
	
	public static final String default_md5key="nIWzr1wk12dfdsvb";
    
	public static final String BASE_REST_URI = "";
	
	public static final String GET_ALLCARD_URI = "/getAllCard";

	public static final String OPEN_CARD_URI = "/openCard";
	
	public static final String GET_BATCH_ORDER_STATUS_URI = "/getCardStatus";

	
}
