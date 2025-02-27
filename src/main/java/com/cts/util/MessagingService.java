package com.cts.util;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

public class MessagingService {
	
	private static final String ACCOUNT_SID="";
	private static final String AUTH_TOKEN="";
	
	public static void sendMessage(String contactNumber,String name,String slot) {
		String messageBody = name+",your appointment has been scheduled at:"+slot+" slot";
		String toPhoneNumber = "+91"+contactNumber;
		Twilio.init(ACCOUNT_SID,AUTH_TOKEN);
		Message message = Message.creator(
				new PhoneNumber(toPhoneNumber),
				new PhoneNumber("+19032015564"),
				messageBody).create();
		System.out.println("Status:"+message.getStatus().toString());
	}
	
}
