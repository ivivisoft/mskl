<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="core" uri="http://java.sun.com/jsp/jstl/core" %>
<core:set var="ctx" value="${pageContext.request.contextPath}"/>

<html>
<body>
<script type="text/javascript" src="${ctx}/js/jquery.js"></script>
<h2>服务测试</h2>


<input type="button" onclick="getVerificationCode()" name="button" id="button" value="获取注册码">
<input type="button" onclick="register()" name="button" id="register" value="注册">
<input type="button" onclick="login()" name="button" id="login" value="登录">
<input type="button" onclick="getLoginPswVerificationCode()" name="button" id="getLoginPswVerificationCode" value="获取找回密码注册码">
<input type="button" onclick="getLoginPassword()" name="button" id="getLoginPassword" value="找回密码">


<input type="button" onclick="promotionInfo()" name="button" id="promotion" value="推广活动">
<input type="button" onclick="insertFeedback()" name="button" id="feedback" value="添加反馈意见">
<input type="button" onclick="insertOverseer()" name="button" id="saveOverseer" value="添加监督人">
<input type="button" onclick="selectOverseer()" name="button" id="selectOverseer" value="查询监督人">
<input type="button" onclick="getMsklMedicineByBarCode()" name="button" id="getMsklMedicineByBarCode" value="根据药品二维码获取药品信息">

<input type="button" onclick="apllyAmount()" name="button" id="apllyAmount" value="用户提现申请">
<input type="button" onclick="takeMedicine()" name="button" id="takeMedicine" value="服药">
<input type="button" onclick="insertTreatPlan()" name="button" id="insertTreatPlan" value="添加服药计划">
<input type="button" onclick="selectTreatPlan()" name="button" id="selectTreatPlan" value="查询服药计划">
<input type="button" onclick="insertBankCard()" name="button" id="insertBankCard" value="添加银行卡">
<input type="button" onclick="selectBankCard()" name="button" id="selectBankCard" value="查询银行卡">
<input type="button" onclick="insertTradePassword()" name="button" id="insertTradePassword" value="添加提现密码">
<input type="button" onclick="updateTradePassword()" name="button" id="updateTradePassword" value="修改提现密码">
<input type="button" onclick="getMsklMedicineByName()" name="button" id="getMsklMedicineByName" value="根据药品通用名查询药品">
<input type="button" onclick="userInfo()" name="button" id="userInfo" value="获取用户信息">
<input type="button" onclick="selectTreatLog()" name="button" id="selectTreatLog" value="查询服药记录">
<input type="button" onclick="selectPushMsg()" name="button" id="selectPushMsg" value="查询推送消息">
<input type="button" onclick="selectAllMedicine()" name="button" id="selectAllMedicine" value="查询所有药品信息">
<input type="button" onclick="selectTreatInfo()" name="button" id="selectTreatInfo" value="查询统计信息">


<script type="text/javascript">
    function getVerificationCode() {
        $.ajax({
            type: "POST",
            contentType: "application/json",
            dataType: "json",
            url: "api/verificationCode/register/18514208469",
            data: "",
            success: function (data) {
                alert(JSON.stringify(data));
            },
            error: function (jqXHR, textStatus, errorThrown) {
                alert(jqXHR + " : " + textStatus + " : " + errorThrown);
            }
        });
    }

    function getMsklMedicineByBarCode() {
        $.ajax({
            type: "POST",
            contentType: "application/json",
            dataType: "json",
            url: "api/msklmedicine/1234567890",
            data: "",
            success: function (data) {
                alert(JSON.stringify(data));
            },
            error: function (jqXHR, textStatus, errorThrown) {
                alert(jqXHR + " : " + textStatus + " : " + errorThrown);
            }
        });
    }

    function getLoginPswVerificationCode() {
        $.ajax({
            type: "POST",
            contentType: "application/json",
            dataType: "json",
            url: "api/verificationCode/getLoginPsw/15024480545/18b04227c42748498c2227e20c66e3b4|999999",
            data: "",
            success: function (data) {
                alert(JSON.stringify(data));
            },
            error: function (jqXHR, textStatus, errorThrown) {
                alert(jqXHR + " : " + textStatus + " : " + errorThrown);
            }
        });
    }

    function getLoginPassword() {

        var findLoginPswDto = {
            "mobile": "15024480545",
            "verificationCode": "972544",
            "newPassword": "123456",
        };
        $.ajax({
            type: "POST",
            contentType: "application/json",
            dataType: "json",
            url: "api/mskluser/getLoginPassword/18b04227c42748498c2227e20c66e3b4|999999",
            data: JSON.stringify(findLoginPswDto),
            success: function (data) {
                alert(JSON.stringify(data));
            },
            error: function (jqXHR, textStatus, errorThrown) {
                alert(jqXHR + " : " + textStatus + " : " + errorThrown);
            }
        });
    }

    function register() {

        var registerDto = {
            "mobile": "18514208469",
            "verificationCode": "929333",
            "password": "123456",
            "invitationCode": ""
        };
        $.ajax({
            type: "POST",
            contentType: "application/json",
            dataType: "json",
            url: "api/mskluser/register",
            data: JSON.stringify(registerDto),
            success: function (data) {
                alert(JSON.stringify(data));
            },
            error: function (jqXHR, textStatus, errorThrown) {
                alert(jqXHR + " : " + textStatus + " : " + errorThrown);
            }
        });
    }


    function login() {
        var loginDto = {"username": "18514208469", "password": "123456"};
        $.ajax({
            type: "POST",
            contentType: "application/json",
            dataType: "json",
            url: "api/mskluser/login/122341514514/7d71362ffcf8f1dd45c52fab29e58ecf",
            data: JSON.stringify(loginDto),
            success: function (data) {
                alert(JSON.stringify(data));
            },
            error: function (jqXHR, textStatus, errorThrown) {
                alert(jqXHR + " : " + textStatus + " : " + errorThrown);
            }
        });
    }

    function promotionInfo() {
        $.ajax({
            type: "post",
            contentType: "application/json",
            dataType: "json",
            url: "api/promotionInfo/all",
            success: function (data) {
                var data = JSON.stringify(data)
                alert(data)
            },
            error: function (jqXHR, textStatus, errorThrown) {
                alert(jqXHR + " : " + textStatus + " : " + errorThrown);
            }
        })
    }

    function insertFeedback() {
        var feedbackDto = {"userName": "yangxu", "userMobile": "13545454567", "feedbackMsg": "吃药"};

        $.ajax({
            type: "post",
            contentType: "application/json",
            dataType: "json",
            url: "api/feedback/insert/122341514514/f9d8a2c3db7e3a5db324bfea29df5efe/18b04227c42748498c2227e20c66e3b4|999999",
            data: JSON.stringify(feedbackDto),
            success: function (data) {
                var data = JSON.stringify(data);
                alert(data)
            },
            error: function (jqXHR, textStatus, errorThrown) {
                alert(jqXHR + " : " + textStatus + " : " + errorThrown);
            }
        })
    }

    function insertOverseer() {
        var overseerDto = {
            "userMobile": "13512122323",
            "overseer": "yangxu",
            "overseerMobile": "13422325533"
        }

        $.ajax({
            type: "post",
            contentType: "application/json",
            dataType: "json",
            url: "api/overseer/insert/122341514514/f9d8a2c3db7e3a5db324bfea29df5efe/18b04227c42748498c2227e20c66e3b4|999999",
            data: JSON.stringify(overseerDto),
            success: function (data) {
                var data = JSON.stringify(data);
                alert(data)
            },
            error: function (jqXHR, textStatus, errorThrown) {
                alert(jqXHR + " : " + textStatus + " : " + errorThrown);
            }
        })
    }


    function selectOverseer() {

        $.ajax({
            type: "post",
            contentType: "application/json",
            dataType: "json",
            url: "api/overseer/select/18b04227c42748498c2227e20c66e3b4|999999",
            success: function (data) {
                var data = JSON.stringify(data);
                alert(data)
            },
            error: function (jqXHR, textStatus, errorThrown) {
                alert(jqXHR + " : " + textStatus + " : " + errorThrown);
            }
        })

    }

    function apllyAmount(){
        var userCashoutDto = {
            "amount": "100"
        }

        $.ajax({
            type: "post",
            contentType: "application/json",
            dataType: "json",
            url: "api/userCashout/apply/122341514514/0f7d375f9201371fea1e21e59b6986e9/18b04227c42748498c2227e20c66e3b4|999999",
            data: JSON.stringify(userCashoutDto),
            success: function (data) {
                var data = JSON.stringify(data);
                alert(data)
            },
            error: function (jqXHR, textStatus, errorThrown) {
                alert(jqXHR + " : " + textStatus + " : " + errorThrown);
            }
        })
    }

    function takeMedicine(){
        var takeMedicineDto = {
            "msklTreatlogId": "2",
            "takenMood":"1",
            "takenWords":"2222"

        }

        $.ajax({
            type: "post",
            contentType: "application/json",
            dataType: "json",
            url: "api/treat/takeMedicine/122341514514/c1a1b8eabc85f35765954ebd4890dff6/18b04227c42748498c2227e20c66e3b4|999999",
            data: JSON.stringify(takeMedicineDto),
            success: function (data) {
                var data = JSON.stringify(data);
                alert(data)
            },
            error: function (jqXHR, textStatus, errorThrown) {
                alert(jqXHR + " : " + textStatus + " : " + errorThrown);
            }
        })
    }
    function insertTreatPlan(){

        var treatPlanDto = {
            "msklMedicineId": "2",
            "dailyTimes": "3",
            "dose": "2",

            "morningAlarm": "09:23:32",
            "nightAlarm": "22:23:32",
            "noonAlarm": "12:23:32",

            "takenAmount": "2",
            "packageAmount": "3"
        }

        $.ajax({
            type: "post",
            contentType: "application/json",
            dataType: "json",
            url: "api/treatPlan/insertOrUpdate/122341514514/1ce13538d0c6268835fcda9cbfcca129/18b04227c42748498c2227e20c66e3b4|999999",
            data: JSON.stringify(treatPlanDto),
            success: function (data) {
                var data = JSON.stringify(data);
                alert(data)
            },
            error: function (jqXHR, textStatus, errorThrown) {
                alert(jqXHR + " : " + textStatus + " : " + errorThrown);
            }
        })
    }

    function selectTreatPlan(){
        $.ajax({
            type: "post",
            contentType: "application/json",
            dataType: "json",
            url: "api/treatPlan/all/18b04227c42748498c2227e20c66e3b4|999999",
            success: function (data) {
                var data = JSON.stringify(data);
                alert(data)
            },
            error: function (jqXHR, textStatus, errorThrown) {
                alert(jqXHR + " : " + textStatus + " : " + errorThrown);
            }
        })
    }

    function insertBankCard(){
        var userBankcardDto = {
            "bankNo": "2",
            "bankName": "杨旭速度",
            "isDefault": "2",
            "bankAddrNo": "2",
            "cardNo": "2"
        }

        $.ajax({
            type: "post",
            contentType: "application/json",
            dataType: "json",
            url: "api/userBankcard/insert/122341514514/b28cd78249e11c2f73d22f6423ae2bc9/18b04227c42748498c2227e20c66e3b4|999999",
            data: JSON.stringify(userBankcardDto),
            success: function (data) {
                var data = JSON.stringify(data);
                alert(data)
            },
            error: function (jqXHR, textStatus, errorThrown) {
                alert(jqXHR + " : " + textStatus + " : " + errorThrown);
            }
        })
    }

    function selectBankCard(){
        $.ajax({
            type: "post",
            contentType: "application/json",
            dataType: "json",
            url: "api/userBankcard/select/18b04227c42748498c2227e20c66e3b4|999999",
            success: function (data) {
                var data = JSON.stringify(data);
                alert(data)
            },
            error: function (jqXHR, textStatus, errorThrown) {
                alert(jqXHR + " : " + textStatus + " : " + errorThrown);
            }
        })
    }

    function insertTradePassword(){
        var userTradeDto = {
            "userTradePwd": "2",
            "newUserTradePwd": "222",
            "userTradePwdStrength": "2"
        }

        $.ajax({
            type: "post",
            contentType: "application/json",
            dataType: "json",
            url: "api/userTrade/tradePassword/insert/122341514514/dd0997b2ee086a0352b6fc7b67e26743/18b04227c42748498c2227e20c66e3b4|999999",
            data: JSON.stringify(userTradeDto),
            success: function (data) {
                var data = JSON.stringify(data);
                alert(data)
            },
            error: function (jqXHR, textStatus, errorThrown) {
                alert(jqXHR + " : " + textStatus + " : " + errorThrown);
            }
        })
    }

    function updateTradePassword(){
        var userTradeDto = {
            "userTradePwd": "2",
            "newUserTradePwd": "222",
            "userTradePwdStrength": "2"
        }

        $.ajax({
            type: "post",
            contentType: "application/json",
            dataType: "json",
            url: "api/userTrade/tradePassword/update/122341514514/dd0997b2ee086a0352b6fc7b67e26743/18b04227c42748498c2227e20c66e3b4|999999",
            data: JSON.stringify(userTradeDto),
            success: function (data) {
                var data = JSON.stringify(data);
                alert(data)
            },
            error: function (jqXHR, textStatus, errorThrown) {
                alert(jqXHR + " : " + textStatus + " : " + errorThrown);
            }
        })
    }

    function getMsklMedicineByName(){
        $.ajax({
            type: "POST",
            contentType: "application/json",
            dataType: "json",
            url: "api/msklmedicine/name/qinliugan",
            data: "",
            success: function (data) {
                alert(JSON.stringify(data));
            },
            error: function (jqXHR, textStatus, errorThrown) {
                alert(jqXHR + " : " + textStatus + " : " + errorThrown);
            }
        });
    }

    function userInfo(){
        $.ajax({
            type: "POST",
            contentType: "application/json",
            dataType: "json",
            url: "api/mskluser/18b04227c42748498c2227e20c66e3b4|999999",
            success: function (data) {
                alert(JSON.stringify(data));
            },
            error: function (jqXHR, textStatus, errorThrown) {
                alert(jqXHR + " : " + textStatus + " : " + errorThrown);
            }
        });
    }

    function selectTreatLog(){
        var treatLogDto = {
            "msklMedicineId": "2",
            "date": "2016-04-02"
        }

        $.ajax({
            type: "post",
            contentType: "application/json",
            dataType: "json",
            url: "api/treatLog/select/122341514514/9dc462162b323bd5146c549828e5f467/18b04227c42748498c2227e20c66e3b4|999999",
            data: JSON.stringify(treatLogDto),
            success: function (data) {
                var data = JSON.stringify(data);
                alert(data)
            },
            error: function (jqXHR, textStatus, errorThrown) {
                alert(jqXHR + " : " + textStatus + " : " + errorThrown);
            }
        })
    }

    function selectPushMsg(){
        var treatLogDto = {
            "date": "2016-04-02"
        }

        $.ajax({
            type: "post",
            contentType: "application/json",
            dataType: "json",
            url: "api/pushMsg/select/122341514514/a19533ad0d33b664993007b0f450fe9d/18b04227c42748498c2227e20c66e3b4|999999",
            data: JSON.stringify(treatLogDto),
            success: function (data) {
                var data = JSON.stringify(data);
                alert(data)
            },
            error: function (jqXHR, textStatus, errorThrown) {
                alert(jqXHR + " : " + textStatus + " : " + errorThrown);
            }
        })
    }

    function selectAllMedicine(){
        $.ajax({
            type: "post",
            contentType: "application/json",
            dataType: "json",
            url: "api/msklmedicine/all",
            success: function (data) {
                var data = JSON.stringify(data);
                alert(data)
            },
            error: function (jqXHR, textStatus, errorThrown) {
                alert(jqXHR + " : " + textStatus + " : " + errorThrown);
            }
        })
    }

    function selectTreatInfo(){
        var treatInfoDto = {
            "beginDate": "2016-04-04",
            "endDate":"2016-04-06",
            "medicineId":"2"
        }

        $.ajax({
            type: "post",
            contentType: "application/json",
            dataType: "json",
            url: "api/treatInfo/select/122341514514/76440dfa5be7b3878ce2a87fb14e3486/18b04227c42748498c2227e20c66e3b4|999999",
            data: JSON.stringify(treatInfoDto),
            success: function (data) {
                var data = JSON.stringify(data);
                alert(data)
            },
            error: function (jqXHR, textStatus, errorThrown) {
                alert(jqXHR + " : " + textStatus + " : " + errorThrown);
            }
        })
    }
</script>
</body>
</html>
