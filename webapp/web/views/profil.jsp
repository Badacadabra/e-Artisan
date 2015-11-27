<%@ page pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>Macky Dieng</title>
  </head>
  <body>
    <c:if test="${! empty info}">
        <h1>${info}</h1>
    </c:if>
    <c:if test="${! empty error}">
        <p>${error} <a href="login">Veuillez vous connecter</a></p>
    </c:if>
  </body>
</html>
