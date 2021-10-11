<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Meal</title>
    <link href="./css/meal.css" rel="stylesheet" type="text/css">
</head>
<body>
<h3><a href="index.html">Home</a></h3>
<hr>
<form method="POST" action='mealController' name="frmAddMeal">
    <input type="hidden" name="mealId" value="<c:out value="${meal.getId()}"/>">
    <br/>
    Description : <input
        type="text"
        name="description"
        value="
<c:out value="${meal.getDescription()}" />
"/>
    <br/>
    Calories : <input
        type="text" name="calories"
        value="
            <c:out value="${meal.getCalories()}" />
"/>
    <br/>
    Date :
    <input type="datetime-local" name="dateTime" value="<c:out value="${meal.getDateTime()}"/>">
    <br/>
    <input
            type="submit" value="Submit"/>
</form>
</body>
</html>