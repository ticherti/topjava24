<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Meals</title>
    <link href="./css/meal.css" rel="stylesheet" type="text/css">
</head>
<body>
<h3><a href="index.html">Home</a></h3>
<hr>
<h2>Meals </h2>
<table>
    <tr>
        <th>
            День
        </th>
        <th>
            Время
        </th>
        <th>
            Описание
        </th>
        <th>
            Калории
        </th>
    </tr>


    <c:forEach var="meal" items="${list}">
        <tr class=
        <c:if test="${meal.isExcess() == true}">
            "exceeded"
        </c:if>
        <c:if test="${meal.isExcess() == false}">
            "not-exceeded"
        </c:if>
        >
        <td>
            <p><c:out value="${meal.getDate()}"/></p>
        </td>
        <td>
            <p><c:out value="${meal.getTime()}"/></p>
        </td>
        <td>
            <p><c:out value="${meal.getDescription()}"/></p>
        </td>
        <td>
            <p><c:out value="${meal.getCalories()}"/></p>
        </td>
        </tr>

    </c:forEach>
</table>
</body>
</html>
