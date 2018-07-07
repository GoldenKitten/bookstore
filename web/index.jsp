<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<c:choose>
    <c:when test="${empty sessionScope.user}">
        <jsp:forward page="/jsps/user/login.jsp"/>
    </c:when>
    <c:otherwise><jsp:forward page="/jsps/main.jsp" /></c:otherwise>
</c:choose>