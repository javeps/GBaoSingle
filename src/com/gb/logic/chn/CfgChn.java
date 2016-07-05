package com.gb.logic.chn;

import com.gb.toolkits.NameRandom;

public class CfgChn {

	// ========= 渠道常量
	static public final String Chn_Str_None = "none";
	static public final int Chn_Int_None = 1;
	
	// ========= 渠道函数
	static public final String getChnStr(int chnInt) {
		switch (chnInt) {
		case Chn_Int_None:
			return Chn_Str_None;
		default:
			return "";
		}
	}

	static public final int getChnInt(final String chnStr) {
		switch (chnStr) {
		case Chn_Str_None:
			return Chn_Int_None;
		default:
			return 0;
		}
	}
	
	/**
	 * 取得用户的语言 [1:繁体,2:en,默认:简体]
	 * @param chnStr
	 * @return
	 */
	static public final int getLanguageType(final String chnStr){
		switch (chnStr) {
		case "ft":
			return NameRandom.Laguage_Type_CNFT;//中文繁体
		case "en":
			return NameRandom.Laguage_Type_EN;//英文版本
		default:
			return NameRandom.Laguage_Type_CN;//中文简体
		}
	}
}
