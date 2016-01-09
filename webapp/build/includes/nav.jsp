<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<nav>
    <div class="ui secondary vertical pointing menu">
        <a class="${param.accueil} item" href="accueil">Accueil</a>
        <a class="${param.actus} item" href="actus">Mon fil d'actualité</a>
        <a class="${param.profil} item" href="profil">Mon profil</a>
        <a class="${param.besoins} item" href="besoins">Mes besoins</a>
        <a class="${param.offres} item" href="offres">Mes offres</a>
        <a class="${param.cycles} item" href="cycles">Cycles de services</a>
        <a id="logout" class="item" href="logout">Déconnexion</a>
        <a id="admin" class="${param.admin} item" href="admin">Administration</a>
    </div>
</nav>
