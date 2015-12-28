<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="root" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html lang="fr">
    <head>
        <jsp:include page="includes/head.jsp">
            <jsp:param name="title" value="Espace personnel" />
        </jsp:include>
    </head>
    <body class="mCustomScrollbar" data-mcs-theme="dark">
        <jsp:include page="includes/header.jsp" />
        <div class="ui grid container">
            <jsp:include page="includes/nav.jsp">
                <jsp:param name="accueil" value="active" />
            </jsp:include>
            <section id="main-section" class="reset">
                <div class="ui orange message">
                    <div class="content">
                        <h3 class="header">Bonjour, Toto ! Comment pouvons-nous vous aider ?</h3>
                        <p>Vous êtes ici dans votre espace personnel.</p>
                        <p>Pour profiter au maximum des fonctionnalités offertes par le site, vous pouvez suivre les étapes listées ci-dessous.</p>
                        <p>À l'issue de la procédure, vous pourrez générer automatiquement des cycles de services car nous serons alors en mesure de voir les échanges possibles au sein du réseau, en fonction de ce que vous souhaitez recevoir et de ce que vous êtes capable d'offrir.</p>
                        <p>Découvrez le vrai sens du mot « service » sur e-Artisan !</p>
                    </div>
                </div>
                <div class="ui fluid vertical steps">
                    <a href="profil" class="link step">
                        <i class="user icon"></i>
                        <div class="content">
                            <div class="title">Profil</div>
                            <div class="description">Complétez votre profil</div>
                        </div>
                    </a>
                    <a href="besoins" class="link step">
                        <i class="announcement icon"></i>
                        <div class="content">
                            <div class="title">Besoins</div>
                            <div class="description">Dites-nous quels services vous souhaitez recevoir</div>
                        </div>
                    </a>
                    <a href="offres" class="link step">
                        <i class="configure icon"></i>
                        <div class="content">
                            <div class="title">Offres</div>
                            <div class="description">Dites-nous quels services vous pouvez rendre</div>
                        </div>
                    </a>
                    <a href="cycles" class="link step">
                        <i class="refresh icon"></i>
                        <div class="content">
                            <div class="title">Cycles</div>
                            <div class="description">Générez vos cycles de services</div>
                        </div>
                    </a>
                </div>
            </section>
        </div>
        <jsp:include page="includes/logout-modal.jsp" />
        <jsp:include page="includes/scripts.jsp" />
    </body>
</html>
