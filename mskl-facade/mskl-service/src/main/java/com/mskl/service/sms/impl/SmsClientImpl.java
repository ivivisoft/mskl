package com.mskl.service.sms.impl;

import com.mskl.common.util.HttpClientUtil;
import com.mskl.common.constant.CheckcodeType;
import com.mskl.service.constant.ServiceConstant;
import com.mskl.service.otherserviceresult.ServiceResult;
import com.mskl.service.sms.SmsClient;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;


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


    public ServiceResult sendVerificationCode(String mobile, String code,CheckcodeType checkcodeType) {
        String url = handleParam(mobile, code, checkcodeType);
        String result = httpClientUtil.doGetRequest(url);
        if (logger.isInfoEnabled()) {
            logger.info(result);
        }
        return handleResult(result);
    }

    private ServiceResult handleResult(String result) {
        ServiceResult serviceResult = new ServiceResult(false);
        String code = StringUtils.substringAfter(StringUtils.substringBefore(result, "\n"), ",");
        serviceResult.setSuccess(StringUtils.equals("0", code));
        return serviceResult;
    }

    private String handleParam(String mobile, String code, CheckcodeType checkcodeType) {
        StringBuilder url = new StringBuilder(smsUrl);
        url.append("?").append("account=").append(account)
                .append("&pswd=").append(password)
                .append("&mobile=").append(mobile);
        String template = getTemplate(checkcodeType, code);
        url.append("&msg=").append(template)
                .append("&needstatus=true").append("&product=");
        return url.toString();
    }


    private String getTemplate(CheckcodeType checkcodeType, String code) {
        String template = StringUtils.EMPTY;
        String templateConstant = StringUtils.EMPTY;
        switch (checkcodeType) {
            case REGISTER:
                templateConstant = ServiceConstant.REGISTER_SMS_TEMPLATE;
                break;
            case GETLOGINPSW:
                templateConstant = ServiceConstant.GETLOGINPSW_SMS_TEMPLATE;
                break;
        }
        if (StringUtils.isNotBlank(templateConstant)) {
            template = String.format(templateConstant.replaceAll("\\s", ""), code);
            try {
                template = URLEncoder.encode(template, "utf-8");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        return template;
    }
}
