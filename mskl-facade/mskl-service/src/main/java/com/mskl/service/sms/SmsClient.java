package com.mskl.service.sms;


public interface SmsClient {
     boolean sendSMS(String mobile,String msg);
}
