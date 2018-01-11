package cn.yearcon.feibank.flowfc.tools;


/**
 * <strong>OpenType</strong><br>
 * 开通类型<br> 
 */
public enum OpenType
{
	ADD("直充",1),SEND("卡密下发",2);
	
	private String desc;
	private int code;
	
	private OpenType(String desc,int code)
	{
		this.desc = desc;
		this.code = code;
	}

	public Integer getCode() {
		return this.code;
	}

	public String getDesc() {
		// TODO Auto-generated method stub
		return this.desc;
	}	
}
