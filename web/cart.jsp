<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Search</title>
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
                    <a href="shopping-history">Shopping History</a>
                </li>
                <li>
                    <a href="${pageContext.request.contextPath}/">
                        Search
                    </a>
                </li>
                <li><a href="logout">Log Out</a></li>
            </ul>
        </div>

        <h1 class="title">Your Cart</h1>
        <hr/>
        
        <c:if test="${not emptyrequestScope.IMPORTANT_ERROR}">
            <h3 style="color: red; text-align: center">${requestScope.IMPORTANT_ERROR}</h3>
        </c:if>

        <!--Table Cart-->
        <div class="user-container">
            <c:choose>
                <c:when test="${not empty requestScope.SHOPPING.shoppingBookList}">
                    <div class="user-table">
                        <table border="1">
                            <thead>
                                <tr>
                                    <th>No.</th>
                                    <th>Photo</th>
                                    <th>Book Name</th>
                                    <th>Quantity</th>
                                    <th>Price</th>
                                    <th></th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:set var="total" value="${0}"/>
                                <c:forEach items="${requestScope.SHOPPING.shoppingBookList}" var="item" varStatus="counter">
                                    <tr>
                                        <td>${counter.count}</td>
                                        <td><img src="images/${item.book.img}" width="80" height="auto"  alt="${item.book.title}"/></td>
                                        <td>${item.book.title}</td>
                                        <td>
                                            <c:if test="${item.book.quantity > item.quantity}">
                                                <a href="increase-cart-item?shoppingBookId=${item.id}" title="Increase">
                                                    ⬆️
                                                </a>
                                            </c:if>
                                            ${item.quantity}
                                            <a href="decrease-cart-item?shoppingBookId=${item.id}" title="Decrease">
                                                ⬇️
                                            </a>
                                        </td>
                                        <td>
                                            ${item.quantity * item.book.price}$
                                            <c:set var="total" value="${total + item.quantity * item.book.price}" />
                                        </td>
                                        <td>
                                            <a title="remove item" onclick="return confirmToRemove(${item.id})">❌</a>
                                        </td>
                                    </tr>          
                                </c:forEach>

                                <tr style="font-weight: bold;color: #e15018;">
                                    <td colspan="4">
                                        Total
                                    </td>
                                    <td> 
                                        ${total}$
                                    </td>
                                </tr>
                            </tbody>
                        </table>

                    </div>
                    <div class="checkout">
                        <table border="1">
                            <tbody>
                                <tr>
                                    <td colspan="2" style="background: pink;" class="td--total">
                                        Total:  ${total * (100 - sessionScope.DISCOUNT.offer)/100}$
                                        <c:if test="${not empty sessionScope.DISCOUNT}"> 
                                            <span>(-${sessionScope.DISCOUNT.offer}%)</span>
                                        </c:if>
                                    </td>
                                </tr>
                                <tr>
                                    <td>
                                        <form action="cart" method="post">
                                            <input style="height:2em" type="text" placeholder="Enter Discount" name="coupon-code" value="${sessionScope.DISCOUNT.code}"/>
                                            <button style="height:2em">Enter</button>
                                        </form>
                                    </td>
                                </tr>
                                <c:if test="${empty sessionScope.DISCOUNT}">
                                    <tr>
                                        <td style="color: red">Not Found Code</td>
                                    </tr>    
                                </c:if>
                                <tr>
                                    <td colspan="2">
                                        <button class="btn-checkout" onclick="return confirmToPay()">
                                            Submit To Pay
                                        </button>
                                    </td>
                                </tr>
                            </tbody>
                        </table>
                    </div>
                </c:when>
                <c:otherwise>
                    <h3 style="margin:auto">
                        Your Cart Is Empty
                    </h3>
                </c:otherwise>
            </c:choose>  
        </div>
        <script>
                    function confirmToRemove(id) {
                    let pass = false;
                            if (id) {
                    pass = confirm("Do you really want to remove this item ? ")
                    }
                    if (pass) {
                    window.location.href = "remove-cart-item?shoppingBookId=" + id;
                    }
                    return pass;
                    }

            function confirmToPay() {
            let pass = false;
                    pass = confirm("Are you sure to pay the item(s)?");
                    if (pass) {
            window.location.href = "checkout";
            }
            return pass;
            }
        </script>
    </body>
</html>
