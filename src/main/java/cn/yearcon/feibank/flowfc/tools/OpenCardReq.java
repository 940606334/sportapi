package cn.yearcon.feibank.flowfc.tools;

public class OpenCardReq extends RestBaseRequest  {

	private static final long serialVersionUID = -6030387197505838459L;
	
		
	/**
	 * 渠道交易订单号，由渠道系统自己保持在本渠道内唯一
	 */
	private String channelOrderId;
	
	/**
	 * 11位用户手机号码
	 */
	private String phoneNum;
	
	/**
	 * 需要充值的卡类型code，该code从卡品查询接口中获取
	 */
	private String cardCode;
	
	/**
	 *开通类型：1-直充；2-卡密下发，目前都是直充备用
	 */
	private int openType;

	
	public String getChannelOrderId() {
		return channelOrderId;
	}

	public void setChannelOrderId(String channelOrderId) {
		this.channelOrderId = channelOrderId;
	}

	public String getPhoneNum() {
		return phoneNum;
	}

	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}

	public String getCardCode() {
		return cardCode;
	}

	public void setCardCode(String cardCode) {
		this.cardCode = cardCode;
	}

	public int getOpenType() {
		return openType;
	}

	public void setOpenType(int openType) {
		this.openType = openType;
	}

}
