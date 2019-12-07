<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" isELIgnored="false"%>

<title>购物商城</title>

<div class="homepageDiv">
    <%@include file="categoryAndcarousel.jsp"%>
    <%@include file="homepageCategoryProducts.jsp"%>
</div>

<s:debug/>