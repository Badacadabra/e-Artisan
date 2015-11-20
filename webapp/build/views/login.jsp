<%@ page pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
   <head>
    <meta charset="utf-8" />
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>S'inscrire</title>
  </head>
  <body>
      <h1>Vous êtes sur le point de vous inscrire</h1>
    <form action="auth" method="post" id="myForm">
        <input type="text" name="email" id="email" placeholder="Identifiant" value="${email}"/>
        <input type="password" name="password" id="password" placeholder="Mot de passe"/>
        <input type="submit" name="submit" value="Se connecter"/>
    </form>
  </body>
</html>
