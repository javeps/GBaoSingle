package com.gb.logic.chn.egame;

import java.io.Serializable;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.dom4j.Element;

import com.bowlong.lang.StrEx;
import com.bowlong.security.MD5;
import com.bowlong.third.xml.XMLEx;
import com.bowlong.third.xml.jaxb.JaxbReadXml;
import com.gb.content.Svc;
import com.gb.logic.chn.egame.IF1.IF1BillingReq;
import com.gb.logic.chn.egame.IF2.IF2BillingReq;
import com.gb.logic.opt.model.LogicalRecordOrders;
/**
 * egame(爱游戏[电信])
 * @author Canyon
 * @version createtime：2016年8月16日 下午9:32:13
 */
public class LogicalEgame implements Serializable {
	
	static Log log = LogFactory.getLog(LogicalEgame.class);

	private static final long serialVersionUID = 1L;
	
	static public String AppID = "5116754";
	static public String AppCode = "110256018367";
	static public String AppKey = "70435b96d0ed409885513be1d6a72296";

	static public String handler(String xml) throws Exception {
		Element root = XMLEx.getRoot(xml);
		Element method = root.element("method");
		String valMethod = method.getTextTrim();

		Element cp_order_id = root.element("cp_order_id");
		String valCp_order_id = cp_order_id.getTextTrim();

		boolean isState = false;

		if ("callback".equals(valMethod)) {
			isState = recordIF2(xml);
		} else if ("check".equals(valMethod)) {
			isState = recordIF1(xml);
		}

		int h_ret = -1;
		if (isState) {
			h_ret = 1;
		}

		EgameBillingResp resp = new EgameBillingResp();
		resp.setH_ret(h_ret);
		resp.setCp_order_id(valCp_order_id);

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

	static boolean record4DB(String orderid, String content, String chn) {
		int state = LogicalRecordOrders.recordOrder(orderid, content, chn);
		if (state == 1) {
			return true;
		}
		return false;
	}

	static public boolean recordIF1(String xml) {
		try {
			IF1BillingReq req = JaxbReadXml.readXmlContext(IF1BillingReq.class,
					xml);
			String orderid = req.getCp_order_id();
			if (StrEx.isEmptyTrim(orderid)) {
				return false;
			}

			if (req.isSign()) {
				return record4DB(orderid, xml, "egame");
			}

			return false;

		} catch (Exception e) {
			log.error(Svc.e2s(e));
			return false;
		}
	}

	static public boolean recordIF2(String xml) {
		try {
			IF2BillingReq req = JaxbReadXml.readXmlContext(IF2BillingReq.class,
					xml);
			String orderid = req.getCp_order_id();
			if (StrEx.isEmptyTrim(orderid)) {
				return false;
			}

			if (req.isSign()) {
				return record4DB(orderid, xml, "egame");
			}

			return false;

		} catch (Exception e) {
			log.error(Svc.e2s(e));
			return false;
		}
	}

	public static void main(String[] args) throws Exception {
		String xml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n"
				+ "<request>\n" + "<userId>1234567890</userId>\n"
				+ "<contentId>000000000000</contentId>\n"
				+ "<consumeCode>000000000000</consumeCode>\n"
				+ "<cpid>701010</cpid>\n" + "<hRet>0</hRet>\n"
				+ "<status>1800</status>\n" + "<versionId>2_0_0</versionId>\n"
				+ "<cpparam>" + MD5.MD5UUIDStimeF16() + "</cpparam>\n"
				+ "<packageID></packageID>\n" + "</request>\n";
		String ret = handler(xml);
		System.out.println(ret);
	}
}
