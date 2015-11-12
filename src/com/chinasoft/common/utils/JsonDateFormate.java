package com.chinasoft.common.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

import net.sf.json.JsonConfig;
import net.sf.json.processors.JsonValueProcessor;

public class JsonDateFormate implements JsonValueProcessor{

	public Object processArrayValue(Object ob, JsonConfig arg1) {
		return process(ob);
	}

	public Object processObjectValue(String arg0, Object ob, JsonConfig arg2) {
		return process(ob);
	}
	public Object process(Object ob) {
		if (ob instanceof Date) {
			return new SimpleDateFormat("yyyy-MM-dd").format(ob);
		}
		return ob==null?"":ob.toString();
	}

}
