<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="core" uri="http://java.sun.com/jsp/jstl/core"%>
<core:set var="ctx" value="${pageContext.request.contextPath}"/>

<html>
<body>
<script type="text/javascript" src="${ctx}/js/jquery.js"></script>
<h2>Hello World!</h2>



<input type="button" onclick="get()" name="button" id="button" value="获取信息">

<script type="text/javascript">
    $(function(){
       
    });

    function get() {
        var pe = {"username":"ddd","sex":"222"};
        $.ajax({
            type: "POST",
            contentType: "application/json",
            dataType: "json",
            url: "api/get",
            data : JSON.stringify(pe),
            success: function(data) {
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
