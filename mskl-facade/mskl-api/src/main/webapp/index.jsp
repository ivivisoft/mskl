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

<script type="text/javascript">
    function getVerificationCode() {
        $.ajax({
            type: "POST",
            contentType: "application/json",
            dataType: "json",
            url: "api/verificationCode/register/15024480545",
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
            url: "api/verificationCode/getLoginPsw/15024480545/349358c613254a0aa62108a8fccf43e2|10006",
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
            url: "api/mskluser/getLoginPassword/454c4c1033bc46608df4838479b9c1c2|10006",
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
            "mobile": "15024480545",
            "verificationCode": "679615",
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
        var loginDto = {"username": "15024480545", "password": "123456"};
        $.ajax({
            type: "POST",
            contentType: "application/json",
            dataType: "json",
            url: "api/mskluser/login/122341514514/4a73541284d6666624cf5cabba295201",
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
        var feedbackDto = {"userId": "0001", "userName": "yangxu", "userMobile": "13545454567", "feedbackMsg": "吃药"};

        $.ajax({
            type: "post",
            contentType: "application/json",
            dataType: "json",
            url: "api/feedback/insert",
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
            "overseerMobile": "13422325533",
            "userId": "001"
        }

        $.ajax({
            type: "post",
            contentType: "application/json",
            dataType: "json",
            url: "api/overseer/insert",
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
            url: "api/overseer/1",
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
