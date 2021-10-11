<%@ page import="ru.javawebinar.topjava.util.DateUtil" %>
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
        <th>Время</th>
        <th>Описание</th>
        <th>Калории</th>
        <th colspan=2></th>
    </tr>
    <c:forEach var="meal" items="${list}">
        <tr class=
                <c:out value="${meal.isExcess() ? 'exceeded' : 'not-exceeded'}"/>
        >
            <td>
                <p><c:out value="${DateUtil.format(meal.getDateTime())}"/></p>
            </td>
            <td>
                <p><c:out value="${meal.getDescription()}"/></p>
            </td>
            <td>
                <p><c:out value="${meal.getCalories()}"/></p>
            </td>
            <td><a href="mealController?action=edit&id=<c:out value="${meal.getId()}"/>">Update</a></td>
            <td><a href="mealController?action=delete&id=<c:out value="${meal.getId()}"/>">Delete</a></td>
        </tr>
    </c:forEach>
</table>

<p><a href="mealController?action=insert">Add meal</a></p>
</body>
</html>
