package me.imniu.po;

import java.util.HashMap;

/**
 * Description：
 * 
 * @author  Darcy
 * @date    2017年11月1日下午10:21:56
 * @version 1.0
 */
public class ResultMap extends HashMap{
	
	public ResultMap(int code, Object data, String message) {
		super();
		this.put("code", code);
		this.put("data", data);
		this.put("message", message);
	}
}
