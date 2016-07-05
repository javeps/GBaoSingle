package com.gb.logic.opt;

import gen_b2g.web_disp.bean.NEmail;
import gen_b2g.web_disp.bean.NEmails;
import gen_b2g.web_disp.bean.ReturnStatus;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;

import com.bowlong.lang.StrEx;
import com.bowlong.net.TcpChannel;
import com.bowlong.util.DateEx;
import com.bowlong.util.ListEx;
import com.gb.db.bean.Email;
import com.gb.db.bean.Player;
import com.gb.db.entity.EmailEntity;
import com.gb.logic.opt.server.OptSvEmail4Rnk;

public class LogicalEmail extends Logical {

	static Log log = getLog(LogicalEmail.class);

	// 判断最后一次取得邮件的时间
	static Map<String, Long> maptime = new HashMap<String, Long>();

	static NEmail transform2NetEn(NEmail to, Email fm) {
		if (fm == null)
			return to;
		if (to == null)
			to = new NEmail();
		to.awardJson = fm.getAwardJson();
		to.cont = fm.getContent();
		to.creattime = fm.getCreattime();
		to.isRead = fm.getIsRead();
		to.isReceive = fm.getIsReceive();
		to.title = fm.getTitle();
		to.validtime = fm.getValidtime();
		return to;
	}

	static List<NEmail> transform2NetEnList(List<NEmail> to, List<Email> fm) {
		if (ListEx.isEmpty(fm))
			return to;
		if (to == null)
			to = new ArrayList<NEmail>();
		int len = fm.size();
		for (int i = 0; i < len; i++) {
			Email enFm = fm.get(i);
			if (enFm == null)
				continue;
			NEmail enTo = null;
			enTo = transform2NetEn(enTo, enFm);
			to.add(enTo);
		}
		return to;
	}

	static void transform2NetEns(NEmails to, List<Email> fm) {
		List<NEmail> toList = null;
		toList = transform2NetEnList(toList, fm);
		if (ListEx.isEmpty(toList))
			return;
		to.list = toList;
	}

	/*** 取得邮件 **/
	static public void onGetNEmals(TcpChannel chn, String unqid, long lasttime,
			NEmails nemails, ReturnStatus ret) throws Exception {
		if (StrEx.isEmptyTrim(unqid))
			return;

		Player pl = getPl(unqid);
		if (pl == null)
			return;

		long last = 0l;
		if (maptime.containsKey(unqid)) {
			last = maptime.get(unqid);
		}

		List<Email> list = EmailEntity.getEmails(unqid, last);

		// 删除邮件
		EmailEntity.deleteBy(unqid);

		Email emailRnk = OptSvEmail4Rnk.getEmailBy(pl);
		if (emailRnk != null) {
			if (list == null) {
				list = new ArrayList<Email>();
			}
			list.add(emailRnk);
		}

		transform2NetEns(nemails, list);

		last = DateEx.now();
		maptime.put(unqid, last);
	}
}
