<%@ page import="ru.javawebinar.topjava.util.DateUtil" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:useBean id="list" scope="request" type="java.util.List<ru.javawebinar.topjava.model.MealTo>"/>
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
                <c:out value="${meal.excess ? 'exceeded' : 'not-exceeded'}"/>
        >
            <td>
                <p>${DateUtil.format(meal.dateTime)}</p>
            </td>
            <td>
                <p>${meal.description}</p>
            </td>
            <td>
                <p>${meal.calories}</p>
            </td>
            <td><a href="mealController?action=edit&id=${meal.id}">Update</a></td>
            <td><a href="mealController?action=delete&id=${meal.id}">Delete</a></td>
        </tr>
    </c:forEach>
</table>

<p><a href="mealController?action=insert">Add meal</a></p>
</body>
</html>
