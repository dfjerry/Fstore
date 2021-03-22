<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 3/22/21
  Time: 9:02 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Product</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">

</head>
<body>
<style>
    body {
        background: linear-gradient(to right, #c04848, #480048);
        min-height: 100vh
    }

    .text-gray {
        color: #aaa
    }

    img {
        height: 170px;
        width: 140px
    }
</style>
<c:if test="${sessionScope.user==null}">
    <jsp:forward page="login.jsp"></jsp:forward>
</c:if>
<div class="container py-5">
    <a href="<%= request.getContextPath()%>/logout" class="nav-link">Logout</a>
    <div class="row text-center text-white mb-5">
        <div class="col-lg-7 mx-auto">
            <h1 class="display-4">Product List</h1>
        </div>
    </div>
    <div class="row">
        <div class="col-lg-8 mx-auto">
            <ul class="list-group shadow">
<c:forEach var="product" items="${listProduct}">
                <li class="list-group-item">
                    <div class="media align-items-lg-center flex-column flex-lg-row p-3">
                        <div class="media-body order-2 order-lg-1">
                            <h5 class="mt-0 font-weight-bold mb-2"><c:out value="${product.productname}"/> </h5>
                            <p class="font-italic text-muted mb-0 small"><c:out value="${product.desc}"/></p>
                            <div class="d-flex align-items-center justify-content-between mt-1">
                                <h6 class="font-weight-bold my-2">$ <c:out value="${product.price}"/></h6>
                                <h6 class="font-weight-bold my-2">Amount: <c:out value="${product.amount}"/></h6>
                                <ul class="list-inline small">
                                    <li class="list-inline-item m-0"><i class="fa fa-star text-success"></i></li>
                                    <li class="list-inline-item m-0"><i class="fa fa-star text-success"></i></li>
                                    <li class="list-inline-item m-0"><i class="fa fa-star text-success"></i></li>
                                    <li class="list-inline-item m-0"><i class="fa fa-star text-success"></i></li>
                                    <li class="list-inline-item m-0"><i class="fa fa-star-o text-gray"></i></li>
                                </ul>
                            </div>
                        </div><img src="https://i.imgur.com/KFojDGa.jpg" alt="Generic placeholder image" width="200" class="ml-lg-5 order-1 order-lg-2">
                    </div>
                </li>
</c:forEach>
            </ul>
        </div>
    </div>
</div>
</body>
</html>
