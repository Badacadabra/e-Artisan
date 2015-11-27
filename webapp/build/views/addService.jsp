<%@ page pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
   <head>
    <meta charset="utf-8" />
    <title>Ajouter un service</title>
  </head>
  <body>
    <h1>Vous êtes sur le point d'ajouter un service</h1>
    <form action="addService" method="post" id="serviceForm">
        <input type="text" name="name" id="name" placeholder="Nom du service">
        <textarea name="description" id="description" placeholder="Description du service"></textarea>
        <input type="text" name="publicationDate" id="publicationDate">
        <input type="text" name="deadline" id="deadline">
        <input type="submit" name="submit" value="Créer le service">
    </form>
  </body>
</html>
