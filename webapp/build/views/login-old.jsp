<%@ page pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
   <head>
    <meta charset="utf-8" />
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>Se connecter</title>
  </head>
  <body>
      <h1>Vous êtes sur le point de vous inscrire</h1>
      <c:if test="${! empty error}">
        <p style="color:red;">${error}</p>
      </c:if>
    <form action="auth" method="post" id="myForm">
        <input type="text" name="email" id="email" placeholder="Identifiant" value="${email}"/>
        <input type="password" name="password" id="password" placeholder="Mot de passe"/>
        <input type="submit" name="submit" value="Se connecter"/>
    </form>
    
  </body>
</html>
