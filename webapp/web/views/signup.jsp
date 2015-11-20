<%@ page pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
  </head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>S'inscrire</title>
  </head>
  <body>
      <h1>Vous êtes sur le point de vous inscrire</h1>
    <form action="register" method="post" id="myForm">
        <input type="text" name="nom" id="nom" placeholder="Entrez votre nom"/>
        <input type="text" name="prenom" id="prenom" placeholder="Entrez votre prénom"/>
        <input type="text" name="email" id="email" placeholder="Entrez votre email"/>
        <input type="password" name="password" id="password" placeholder="Entrez votre mot de passe"/>
        <input type="submit" name="submit" value="Envoyer"/>
    </form>
	<c:if test="${! empty error}">
		<p><c:out value="${error}"/></p>
	</c:if>
  </body>
</html>
