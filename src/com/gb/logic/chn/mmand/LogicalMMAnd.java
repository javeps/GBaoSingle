package com.gb.logic.chn.mmand;

import java.io.Serializable;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.bowlong.lang.StrEx;
import com.bowlong.security.MD5;
import com.bowlong.third.xml.jaxb.JaxbReadXml;
import com.gb.content.Svc;
import com.gb.logic.opt.model.LogicalRecordOrders;

/**
 * 咪咕游戏(和游戏[移动MM])
 * @author Canyon
 * @version createtime：2016年8月16日 下午9:34:57
 */
public class LogicalMMAnd implements Serializable {
	static Log log = LogFactory.getLog(LogicalMMAnd.class);

	private static final long serialVersionUID = 1L;

	static public String handler(String xml) {
		BillingResp resp = new BillingResp();
		resp.sethRet(0);
		resp.setMessage("Successful");
		boolean isState = recordMMAnd(xml);
		if (isState) {
			try {
				return JaxbReadXml.getString(resp, "files/mmand.xml");
			} catch (Exception e) {
				log.error(Svc.e2s(e));
			}
		} else {
			log.debug(xml);
		}
		return "";
	}

	static public boolean recordMMAnd(String xml) {
		try {
			BillingReq req = JaxbReadXml.readXmlContext(BillingReq.class, xml);
			String orderid = req.getCpparam();
			if (StrEx.isEmptyTrim(orderid)) {
				return false;
			}

			int state = LogicalRecordOrders.recordOrder(orderid, xml,"mmand");
			if (state == 1) {
				return true;
			}
			return false;

		} catch (Exception e) {
			log.error(Svc.e2s(e));
			return false;
		}
	}
	
	public static void main(String[] args) {
		String xml =
				"<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
					"<request>\n" +
					"<userId>1234567890</userId>\n" +
					"<contentId>000000000000</contentId>\n" +
					"<consumeCode>000000000000</consumeCode>\n" +
					"<cpid>701010</cpid>\n" +
					"<hRet>0</hRet>\n" +
					"<status>1800</status>\n" +
					"<versionId>2_0_0</versionId>\n" +
					"<cpparam>" + MD5.MD5UUIDStimeF16() + "</cpparam>\n" +
					"<packageID></packageID>\n" +
					"</request>\n";
		String ret = handler(xml);
		System.out.println(ret);
	}
}
