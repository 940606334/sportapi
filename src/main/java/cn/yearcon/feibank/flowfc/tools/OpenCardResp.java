package cn.yearcon.feibank.flowfc.tools;

public class OpenCardResp {

	private String transId;
	private String rspCode;
	private String rspMsg;
	
	private OpenCardRespChild data;
	public OpenCardRespChild getData() {
		return data;
	}
	public void setData(OpenCardRespChild data) {
		this.data = data;
	}
	public String getTransId() {
		return transId;
	}
	public void setTransId(String transId) {
		this.transId = transId;
	}
	public String getRspCode() {
		return rspCode;
	}
	public void setRspCode(String rspCode) {
		this.rspCode = rspCode;
	}
	public String getRspMsg() {
		return rspMsg;
	}
	public void setRspMsg(String rspMsg) {
		this.rspMsg = rspMsg;
	}
	
	
}
