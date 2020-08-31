<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Update Book</title>     
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

        <h1 align="center">Update Book</h1>

        <c:if test="${requestScope.success != null}">
            <font color="green" align="center" style="display: block;">
            ${requestScope.success}
            </font>
        </c:if>

        <form class="user-form" action="update-book?id=${param.id}" method="POST" enctype="multipart/form-data">
            <img src="images/${requestScope.selected_book.img}" height="100px" width="auto"/><br/><br/>
            <c:if test="${not empty requestScope.errors.titleError}">
                <p style="color:red">${requestScope.errors.titleError}</p>
            </c:if>
                Imported Date: <input type="text" disabled value=" <fmt:formatDate pattern="dd-MM-YYYY HH:mm:ss" value="${selected_book.updateAt}"/>"/><br/>
            
            Title <input type="text" name="title" value="${selected_book.title}" /><br/>

            <c:if test="${not empty requestScope.errors.authorError}">
                <p style="color:red">${requestScope.errors.authorError}</p>
            </c:if>
            Author <input type="text" name="author" value="${selected_book.author}" /><br/>

            <c:if test="${not empty requestScope.errors.descriptionError}">
                <p style="color:red">${requestScope.errors.descriptionError}</p>
            </c:if>
            Description <textarea type="text" name="description">${selected_book.description}</textarea><br/>

            <c:if test="${not empty requestScope.errors.priceError}">
                <p style="color:red">${requestScope.errors.priceError}</p>
            </c:if>
            Price($) <input type="text" name="price" value="${selected_book.price}" /><br/>

            Category<select name="categoryId">
                <c:forEach items="${requestScope.CATEGORIES}" var="item">
                    <option value="${item.id}" <c:if test="${item.id eq selected_book.category.id}">selected</c:if> >${item.name}</option>
                </c:forEach>
            </select><br/>

            <c:if test="${not empty requestScope.errors.quantityError}">
                <p style="color:red">${requestScope.errors.quantityError}</p>
            </c:if>
            Quantity <input type="number" name="quantity" value="${selected_book.quantity > 1 ? selected_book.quantity : 1}" /><br/>
            <input name="uploadfile" type="file" size="50" accept=".jpg, .jpeg, .png, .webp"/><br/><br/>
            <input type="submit" value="Submit" />
        </form>
    </body>
</html>
