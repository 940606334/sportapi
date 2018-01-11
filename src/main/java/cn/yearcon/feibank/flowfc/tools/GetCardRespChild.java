package cn.yearcon.feibank.flowfc.tools;

public class GetCardRespChild {
	private String saledProvinceCode;
	private String  cardCode;
	private String  cardName;
	private String  flowNumber;
	private String  cardValue;
	private String  operatorsType;
	private String  cardStatus;
	private String  backup1;
	private String  backup2;
	private String  backup3;
	private String  backup4;
	private String  patnerChannel;
	
	public GetCardRespChild(){}
	


	public GetCardRespChild(String saledProvinceCode, String cardCode,
			String cardName, String flowNumber, String cardValue,
			String operatorsType, String cardStatus, String backup1,
			String backup2, String backup3, String backup4, String patnerChannel) {
		super();
		this.saledProvinceCode = saledProvinceCode;
		this.cardCode = cardCode;
		this.cardName = cardName;
		this.flowNumber = flowNumber;
		this.cardValue = cardValue;
		this.operatorsType = operatorsType;
		this.cardStatus = cardStatus;
		this.backup1 = backup1;
		this.backup2 = backup2;
		this.backup3 = backup3;
		this.backup4 = backup4;
		this.patnerChannel = patnerChannel;
	}
	
	public String getBackup3() {
		return backup3;
	}


	public void setBackup3(String backup3) {
		this.backup3 = backup3;
	}

	public String getBackup4() {
		return backup4;
	}

	public void setBackup4(String backup4) {
		this.backup4 = backup4;
	}

	public String getPatnerChannel() {
		return patnerChannel;
	}

	public void setPatnerChannel(String patnerChannel) {
		this.patnerChannel = patnerChannel;
	}

	public String getSaledProvinceCode() {
		return saledProvinceCode;
	}
	public void setSaledProvinceCode(String saledProvinceCode) {
		this.saledProvinceCode = saledProvinceCode;
	}
	public String getCardCode() {
		return cardCode;
	}
	public void setCardCode(String cardCode) {
		this.cardCode = cardCode;
	}
	public String getCardName() {
		return cardName;
	}
	public void setCardName(String cardName) {
		this.cardName = cardName;
	}
	public String getFlowNumber() {
		return flowNumber;
	}
	public void setFlowNumber(String flowNumber) {
		this.flowNumber = flowNumber;
	}
	public String getCardValue() {
		return cardValue;
	}
	public void setCardValue(String cardValue) {
		this.cardValue = cardValue;
	}
	public String getOperatorsType() {
		return operatorsType;
	}
	public void setOperatorsType(String operatorsType) {
		this.operatorsType = operatorsType;
	}
	public String getCardStatus() {
		return cardStatus;
	}
	public void setCardStatus(String cardStatus) {
		this.cardStatus = cardStatus;
	}
	public String getBackup1() {
		return backup1;
	}
	public void setBackup1(String backup1) {
		this.backup1 = backup1;
	}
	public String getBackup2() {
		return backup2;
	}
	public void setBackup2(String backup2) {
		this.backup2 = backup2;
	}
	
	
}
