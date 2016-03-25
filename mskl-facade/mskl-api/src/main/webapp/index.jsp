<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="core" uri="http://java.sun.com/jsp/jstl/core" %>
<core:set var="ctx" value="${pageContext.request.contextPath}"/>

<html>
<body>
<script type="text/javascript" src="${ctx}/js/jquery.js"></script>
<h2>服务测试</h2>


<input type="button" onclick="get()" name="button" id="button" value="获取信息">
<input type="button" onclick="register()" name="button" id="register" value="注册">
<input type="button" onclick="login()" name="button" id="login" value="登录">

<script type="text/javascript">

    function get() {
        var pe = {"username": "ddd", "sex": "222"};
        $.ajax({
            type: "POST",
            contentType: "application/json",
            dataType: "json",
            url: "api/get",
            data: JSON.stringify(pe),
            success: function (data) {
                alert("Success!!!");
            },
            error: function (jqXHR, textStatus, errorThrown) {
                alert(jqXHR + " : " + textStatus + " : " + errorThrown);
            }
        });
    }

    function register() {

        var registerDto = {
            "mobile": "15024480545",
            "verificationCode": "818399",
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
                alert("Success!!!");
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
            url: "api/mskluser/login",
            data: JSON.stringify(loginDto),
            success: function (data) {
                alert("Success!!!");
            },
            error: function (jqXHR, textStatus, errorThrown) {
                alert(jqXHR + " : " + textStatus + " : " + errorThrown);
            }
        });
    }

</script>
</body>
</html>
