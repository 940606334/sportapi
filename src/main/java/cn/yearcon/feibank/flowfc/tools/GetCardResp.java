package cn.yearcon.feibank.flowfc.tools;

import java.util.List;

public class GetCardResp {

	private String transId;
	private String  rspCode;
	private String  rspMsg;
	private String 	size;
	private List<GetCardRespChild>  cardInfoList;
	
	public GetCardResp(){};
	
	public GetCardResp(String transId, String rspCode, String rspMsg,
			String size, List<GetCardRespChild> cardInfoList) {

		this.transId = transId;
		this.rspCode = rspCode;
		this.rspMsg = rspMsg;
		this.size = size;
		this.cardInfoList = cardInfoList;
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
	public String getSize() {
		return size;
	}
	public void setSize(String size) {
		this.size = size;
	}
	public List<GetCardRespChild> getCardInfoList() {
		return cardInfoList;
	}
	public void setCardInfoList(List<GetCardRespChild> cardInfoList) {
		this.cardInfoList = cardInfoList;
	} 
	
	
	
}
