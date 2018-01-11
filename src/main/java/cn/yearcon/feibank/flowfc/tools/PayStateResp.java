package cn.yearcon.feibank.flowfc.tools;

import java.util.List;

public class PayStateResp {
	private String transId;
	private String rspCode;
	private String rspMsg;
	private String size;
	private List<PayStateOne> orderList;
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
	public List<PayStateOne> getOrderList() {
		return orderList;
	}
	public void setOrderList(List<PayStateOne> orderList) {
		this.orderList = orderList;
	}
	
}
