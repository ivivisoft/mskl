package com.mskl.service.sms.impl;

import com.mskl.common.util.HttpClientUtil;
import com.mskl.service.sms.SmsClient;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import static com.mskl.service.constant.ServiceConstant.SMS_TEMPLATE;


@Service(value = "smsClient")
public class SmsClientImpl implements SmsClient {

    private Log logger = LogFactory.getLog(SmsClientImpl.class);

    private static final HttpClientUtil httpClientUtil = HttpClientUtil.getInstance();

    @Value("${sms.url}")
    private String smsUrl;

    @Value("${sms.account}")
    private String account;

    @Value("${sms.pswd}")
    private String password;


    public boolean sendSMS(String mobile, String msg) {
        String url = handleParam(mobile, msg);
        String result = httpClientUtil.doGetRequest(url);
        if (logger.isInfoEnabled()) {
            logger.info(result);
        }
        return false;
    }

    private String handleParam(String mobile, String msg) {
        StringBuilder url = new StringBuilder(smsUrl);
        url.append("?").append("account=").append(account)
                .append("&pswd=").append(password)
                .append("&mobile=").append(mobile);
        String template = String.format(SMS_TEMPLATE.replaceAll("\\s", ""), msg);
        try {
            template = URLEncoder.encode(template, "utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        url.append("&msg=").append(template)
                .append("&needstatus=true").append("&product=");
        return url.toString();
    }
}
