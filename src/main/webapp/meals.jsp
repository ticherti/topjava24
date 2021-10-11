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
        <th>День</th>
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
                    <%--                TODO change date and time for formatted dateTime--%>
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
            <td><a href="mealController?action=edit&id=<c:out value="${meal.getId()}"/>">Update</a></td>
            <td><a href="mealController?action=delete&id=<c:out value="${meal.getId()}"/>">Delete</a></td>
        </tr>
    </c:forEach>
</table>
<%--TODO for some reasons there is no insert in the example case. BUT. There is strange else{} where the action is add--%>
<p><a href="mealController?action=insert">Add meal</a></p>
</body>
</html>
