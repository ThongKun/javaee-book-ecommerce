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
        <div class="nav-bar" <c:if test="${empty sessionScope.userinfo}">style="justify-content: flex-end;"</c:if>>
            <c:if test="${not empty sessionScope.userinfo}">
                <span>${sessionScope.userinfo.name} > Role: ${sessionScope.userinfo.role.name} </span>
            </c:if>

            <ul class="menu"  >
                <c:if test="${not empty sessionScope.userinfo && sessionScope.userinfo.role.name != 'admin'}">
                    <li>
                        <a href="shopping-history">Shopping History</a>
                    </li>
                    <li>
                        <a href="cart">
                            Cart
                        </a>
                    </li>
                </c:if>
                <c:if test="${empty sessionScope.userinfo}">
                    <li>
                        <a href="login">Log In</a>
                    </li>
                </c:if>
                <c:if test="${not empty sessionScope.userinfo}">
                    <li>
                        <a href="logout">Log Out</a>
                    </li>
                </c:if>

            </ul>
        </div>

        <h1 class="title">Mini Book Store</h1>

        <!--Search-->
        <form class="search" action="search-book">
            <span class="form-title">Filter</span>
            <div class="input-search">
                <label>Book Name &nbsp;</label><input type="text" name="s" value="${param.s}"/>
            </div>
            <div class="input-money">
                <label>Select Money &nbsp; </label><input type="range" name="minimumMoney" id="ageInputId" value="${param.minimumMoney lt 1 || empty param.minimumMoney ? 1 : param.minimumMoney}" min="1" max="100" oninput="ageOutputId.value = ageInputId.value">
                >=<output name="ageOutputName" id="ageOutputId">${param.minimumMoney lt 1 || empty param.minimumMoney ? 1 : param.minimumMoney}</output>$
            </div>
            <div class="input-category">
                <label>Select Category &nbsp;</label>
                <select name="categoryId">
                    <option value=""></option>
                    <c:forEach items="${requestScope.CATEGORIES}" var="item">
                        <option value="${item.id}" <c:if test="${param.categoryId == item.id}">selected</c:if>>${item.name}</option>
                    </c:forEach>
                </select>
            </div>
            <input type="submit" value="Search" />
        </form>
        <br/>

        <hr/>

        <c:if test="${sessionScope.userinfo.role.name == 'admin'}">
            <div class="admin-right-btn">
                <button style="border: none;    border-bottom: 1px inset;    border-radius: unset">
                    <a href="add-new-book" style="color:#8BC34A;font-size: 15px;">Add New Book</a>
                </button>
                <button style="border: none;    border-bottom: 1px inset;    border-radius: unset">
                    <a href="generate-discount-code" style="color:#8BC34A;font-size: 15px;">Generate Discount Code</a>
                </button>
            </div>
        </c:if>
        <br/><br/>

        <div class="user-container">
            <div class="user-table">
                <c:choose>
                    <c:when test="${not empty requestScope.books}">
                        <table border="1" class="search">
                            <thead>
                                <tr>
                                    <th>No.</th>
                                    <th>Photo</th>
                                    <th>Title</th>
                                    <th>Author</th>
                                    <th>Quantity</th>
                                    <th>Price($)</th>
                                    <th>Category</th>
                                    <th>is Active</th>
                                    <th></th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach items="${requestScope.books}" var="item" varStatus="counter">
                                    <tr>
                                        <td>${counter.count}</td>
                                        <td><img src="images/${item.img}" width="80" height="auto"  alt="Book Image"/></td>
                                        <td>${item.title}</td>
                                        <td><span>${item.author}</span></td>
                                        <td>
                                            ${item.quantity}
                                        </td>
                                        <td>${item.price}</td>
                                        <td>${item.category.name}</td>
                                        <td>${item.status}</td>
                                        <td>
                                            <c:if test="${sessionScope.userinfo.id eq 1}">
                                                <button type="submit">
                                                    <a href="update-book?id=${item.id}" style="color:blue">Update</a>
                                                </button>
                                                <c:choose>
                                                    <c:when test="${item.status}">
                                                        <button>
                                                            <a href="change-book-status?id=${item.id}" style="color:#6c6a6c"onclick="return confirmBan(${item.id})">Ban</a>
                                                        </button> 
                                                    </c:when>
                                                    <c:otherwise>
                                                        <button>
                                                            <a href="change-book-status?id=${item.id}" style="color:#3cb027" onclick="return confirmActivate(${item.id})">Activate</a>
                                                        </button> 
                                                    </c:otherwise>
                                                </c:choose>
                                            </c:if>
                                            <c:if test="${sessionScope.userinfo.id != 1}">
                                                <button class="btn--cart" title="add to cart">
                                                    <c:if test="${not empty sessionScope.userinfo}">
                                                        <a href="add-to-cart?bookId=${item.id}" style="color:#3cb027">🛒</a>
                                                    </c:if>        
                                                    <c:if test="${empty sessionScope.userinfo}">
                                                        <a style="color:#3cb027" <c:if test="${empty sessionScope.userinfo}">onclick="return haveToLogin()"</c:if>>🛒</a>
                                                    </c:if>        
                                                </button> 

                                            </c:if>
                                        </td>
                                    </tr>
                                </c:forEach>

                            </tbody>
                        </table>
                    </c:when>
                    <c:otherwise>
                        Not Found Any Results
                    </c:otherwise>
                </c:choose>
            </div>
        </div>

        <script>
                    function confirmBan(id) {
                    let pass = false;
                            if (id) {
                    pass = confirm('Do you want to ban this book ?');
                    }
                    if (pass) {
                    window.location.href = "change-book-status?id=" + id;
                    }
                    return pass;
                    };
                    function confirmActivate(id) {
                    let pass = false;
                            if (id) {
                    pass = confirm('Do you want to activate this book ?');
                    }
                    if (pass) {
                    window.location.href = "change-book-status?id=" + id;
                    }
                    return pass;
                    }

            function haveToLogin() {
            let pass = false;
                    pass = confirm('You have to login, do you want that ?');
                    if (pass) {
            window.location.href = "login";
            }
            return pass;
            }
        </script>
    </body>
</html>
