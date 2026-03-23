package com.ddd.app.user.service;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Properties;
import java.util.Random;

import org.json.simple.JSONObject;

import net.nurigo.java_sdk.api.Message;
import net.nurigo.java_sdk.exceptions.CoolsmsException;

public class SmsService {

	private static String API_KEY;
	private static String API_SECRET;
	private static String FROM_NUMBER;

	static {
		try {
			Properties prop = new Properties();
			InputStream input = SmsService.class.getClassLoader().getResourceAsStream("context.properties");

			if (input == null) {
				throw new RuntimeException("context.properties 못 찾음");
			}

			prop.load(input);

			API_KEY = prop.getProperty("api.apiKey");
			API_SECRET = prop.getProperty("api.apiSecret");
			FROM_NUMBER = prop.getProperty("api.fromNumber");

			System.out.println("API_KEY: " + API_KEY);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String sendVerificationSms(String to) throws CoolsmsException {
		Message coolsms = new Message(API_KEY, API_SECRET);
		String verificationCode = generateVerificationCode();

		System.out.println("인증번호 :" + verificationCode);

		HashMap<String, String> params = new HashMap<String, String>();
		params.put("to", to);
		params.put("from", FROM_NUMBER);
		params.put("type", "SMS");
		params.put("text", "인증번호는 [" + verificationCode + "] 입니다.");

		System.out.println(coolsms + "확인!!!!!!!!!");
		JSONObject obj = (JSONObject) coolsms.send(params);
		System.out.println(obj.toString());

		System.out.println("sendVerificationSms 종료");

		return verificationCode;
	}

	private String generateVerificationCode() {
		Random random = new Random();
		StringBuilder code = new StringBuilder();
		for (int i = 0; i < 6; i++) {
			code.append(random.nextInt(10));
		}
		return code.toString();
	}

}
