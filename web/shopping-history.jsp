<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions"  prefix="fn"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Shopping History</title>
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
                <li>
                    <a href="cart">
                        Cart
                    </a>
                </li>
                <li>
                    <a href="${pageContext.request.contextPath}/">Search</a>
                </li>
                <li>
                    <a href="logout">Log Out</a>
                </li>
            </ul>
        </div>

        <h1 class="title">Shopping History</h1>

        <!--Search-->
        <form class="search" action="shopping-history">
            <span class="form-title">Filter</span>
            <div class="input-search">
                <label>Book Name &nbsp;</label><input type="text" name="s" value="${param.s}"/>
            </div>
            <div class="input-money">
                <div>
                    <label>From &nbsp;</label>
                    <input type="date" name="from" value="${param.from}"/>    
                </div>
                <div>
                    <label>&nbsp;To&nbsp;</label>
                    <input type="date"  name="to" value="${param.to}"/>    
                </div>
            </div>
            <input type="submit" value="Search" />
        </form>
        <br/>

        <hr/>

        <div class="user-container">
            <div class="user-table">
                <table border="1">
                    <thead>
                        <tr>
                            <th>No.</th>
                            <th>Time(Pay at)</th>
                            <th>Photo</th>
                            <th>Book</th>
                            <th>Quantity</th>
                            <th>Price</th>
                            <th>coupon</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${requestScope.CHECKOUTS}" var="checkout" varStatus="counter">
                            <c:forEach items="${checkout.checkoutBookList}" var="item">
                                <c:if test="${fn:containsIgnoreCase(item.book.title, param.s)}">
                                    <tr>
                                        <td>Order ${counter.count}</td>
                                        <td>
                                            <fmt:formatDate pattern="dd-MM-YYYY HH:mm:ss" value="${checkout.createAt}"/>
                                        </td>
                                        <td><img src="images/${item.book.img}" width="80" height="auto"  alt="Book Image"/></td>
                                        <td>${item.book.title}</td>
                                        <td>${item.quantity}</td>
                                        <td>${item.price}</td>
                                        <td>
                                            <c:if test="${not empty checkout.discount.offer}">
                                                ${checkout.discount.offer}%
                                            </c:if>
                                        </td>
                                    </tr>
                                </c:if>

                            </c:forEach>
                        </c:forEach>
                    </tbody>
                </table>

            </div>
        </div>
    </body>
</html>
