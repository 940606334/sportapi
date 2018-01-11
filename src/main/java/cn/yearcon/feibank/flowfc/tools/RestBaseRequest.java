/* Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package cn.yearcon.feibank.flowfc.tools;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * 请求报文基础数据类
 * 
 * @author Johnny.lu
 */
public class RestBaseRequest implements java.io.Serializable {

	private static final long serialVersionUID = -6411118253459078274L;

	/**
	 * 交易流水号，用于确保唯一性，由渠道生成，生成规则为：7位渠道号+2位订单类型号（目前为00）+
	 * yyyymmddHH24MISS+3位随机序号组成，渠道保证流水的唯一性。
	 * 每次交易请求必须要不能重复
	 */
	private String transId;
	
	/**
	 * 渠道代码
	 */
	private String userCode;
	
	/**
	 * 数字签名
	 */
	private String sign;
	
	//备用字段1
	private String backup1;
	
	//备用字段2
	private String backup2;

	public RestBaseRequest(){
		
	}
	
	public RestBaseRequest(String transId,String userCode,String sign){
		this.transId = transId;
		this.userCode = userCode;
		this.sign = sign;
	}
	
	public String getTransId() {
		return transId;
	}

	public void setTransId(String transId) {
		this.transId = transId;
	}
	
	public String getUserCode() {
		return userCode;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}

	public String getSign() {
		return sign;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}
	
	@JsonInclude(Include.NON_NULL)
	public String getBackup1() {
		return backup1;
	}

	public void setBackup1(String backup1) {
		this.backup1 = backup1;
	}

	@JsonInclude(Include.NON_NULL)
	public String getBackup2() {
		return backup2;
	}

	public void setBackup2(String backup2) {
		this.backup2 = backup2;
	}
}
