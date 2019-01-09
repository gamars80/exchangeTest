package com.test.controller;

import java.util.Map;
import java.util.Properties;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.test.common.util.StringUtil;

@Controller
@RequestMapping("/")
public class MainController {
	private static final Logger logger = LoggerFactory.getLogger(MainController.class);
	@Resource
	Properties systemProp;

	
	@RequestMapping(value="/main")
	public String main(
			HttpServletRequest request,
			Map<String, Object> map) throws Exception {	
		
		return "/main";
	
	}
	

	@SuppressWarnings("finally")
	@RequestMapping(value="/exchange")
	@ResponseBody
	public String exchange(
			@RequestParam(required=true) String country,
			HttpServletRequest req) throws Exception{
		String result	  = "99";
	
		String url  = systemProp.getProperty("exchange_url");
		String key  = systemProp.getProperty("exchange_apikey");
		try{		
			String resultTxt = "";
			resultTxt = Jsoup.connect(url+"?access_key="+key)
					.timeout(4000)
					.userAgent("Mozilla")
					.ignoreContentType(true)
					.execute().body();

					  
			JSONObject obj = (JSONObject)JSONValue.parse(resultTxt);
			String success = obj.get("success").toString();
			
			//api 성공일경우
			if(StringUtil.replaceNull(success,"").equals("true")){
				JSONObject tmp = (JSONObject)obj.get("quotes");
				if(tmp!=null){
					String quote = tmp.get(country).toString();
					result = String.format("%.2f", Double.parseDouble(quote));
					logger.debug("result:::"+result);
				}
		
			}
				
		}catch(Exception e){
			return result;
		}finally{
			return result;
		}
		
	}
}
