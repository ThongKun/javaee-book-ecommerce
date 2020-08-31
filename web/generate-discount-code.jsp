<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Generate Discount Code</title>     
        <style>
            <%@ include file="/css/search.css" %>
        </style>
    </head>
    <body>
        <div class="custom-shape-divider-top-1597926621">
            <svg data-name="Layer 1" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 1200 120" preserveAspectRatio="none">
            <path d="M321.39,56.44c58-10.79,114.16-30.13,172-41.86,82.39-16.72,168.19-17.73,250.45-.39C823.78,31,906.67,72,985.66,92.83c70.05,18.48,146.53,26.09,214.34,3V0H0V27.35A600.21,600.21,0,0,0,321.39,56.44Z" class="shape-fill"></path>
            </svg>
        </div>
        <div class="nav-bar">
            <span>${sessionScope.userinfo.name} > Role: ${sessionScope.userinfo.role.name} </span>
            <ul class="menu">
                <li><a href="${pageContext.request.contextPath}/">Search Page</a></li>
                <li><a href="logout">Log Out</a></li>
            </ul>
        </div>

        <h1 align="center">Generate Discount Code   </h1>

        <c:if test="${requestScope.success != null}">
            <font color="green" align="center" style="display: block;">
            *${requestScope.success}
            </font>
        </c:if>

        <form class="user-form" action="generate-discount-code" method="POST">

            <c:if test="${not empty requestScope.offerError}">
                <p style="color:red">*${requestScope.offerError}</p>
            </c:if>
            Offer 
            <div>
                <input type="range" name="offer" id="ageInputId" min="1" max="100" oninput="ageOutputId.value = ageInputId.value" value="10"/><output name="ageOutputName" id="ageOutputId">10</output>%<br/>
            </div>

            <c:if test="${not empty requestScope.codeError}">
                <p style="color:red">${requestScope.codeError}</p>
            </c:if>
                Code <input name="code" placeholder="generate code!"/> <br/>
            <input type="submit" value="Submit" />
        </form>
    </body>
</html>
