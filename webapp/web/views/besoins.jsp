<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="root" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html lang="fr">
    <head>
        <jsp:include page="includes/head.jsp">
            <jsp:param name="title" value="Mes besoins" />
        </jsp:include>
        <link rel="stylesheet" href="${root}/css/pickadate.css">
        <link rel="stylesheet" href="${root}/css/pickadate.date.css">
    </head>
    <body class="mCustomScrollbar" data-mcs-theme="dark">
        <jsp:include page="includes/header.jsp" />
        <div class="ui grid container">
            <jsp:include page="includes/nav.jsp">
                <jsp:param name="besoins" value="active" />
            </jsp:include>
            <section id="main-section">
                <h2 class="ui center aligned icon header need-modal-link">
                    <i class="circular announcement icon"></i>
                    Ajouter un besoin
                </h2>
                <div class="ui middle aligned divided list">
                    <div class="item">
                        <div class="right floated content">
                            <div id="list-elt-1-button" class="ui inverted orange button list-elt-button">Afficher</div>
                            <div class="ui inverted brown button need-modal-link">Modifier</div>
                            <div class="ui inverted red button delete-btn">Supprimer</div>
                        </div>
                        <i class="large announcement middle aligned icon"></i>
                        <div class="content">
                            <span class="bold">Besoin n°1</span>
                            <div class="description">Ajouté il y a 2 minutes</div>
                        </div>
                    </div>
                    <div class="item">
                        <div class="right floated content">
                            <div id="list-elt-2-button" class="ui inverted orange button list-elt-button">Afficher</div>
                            <div class="ui inverted brown button need-modal-link">Modifier</div>
                            <div class="ui inverted red button delete-btn">Supprimer</div>
                        </div>
                        <i class="large announcement middle aligned icon"></i>
                        <div class="content">
                            <span class="bold">Besoin n°2</span>
                            <div class="description">Ajouté il y a 1 heure</div>
                        </div>
                    </div>
                    <div class="item">
                        <div class="right floated content">
                            <div id="list-elt-3-button" class="ui inverted orange button list-elt-button">Afficher</div>
                            <div class="ui inverted brown button need-modal-link">Modifier</div>
                            <div class="ui inverted red button delete-btn">Supprimer</div>
                        </div>
                        <i class="large announcement middle aligned icon"></i>
                        <div class="content">
                            <span class="bold">Besoin n°3</span>
                            <div class="description">Ajouté il y a 1 semaine</div>
                        </div>
                    </div>
                </div>
                <div id="list-elt-1" class="ui segment list-elt" style="display:none;">
                    <h3>Besoin n°1</h3>
                    <p>Description du besoin n°1</p>
                </div>
                <div id="list-elt-2" class="ui segment list-elt" style="display:none;">
                    <h3>Besoin n°2</h3>
                    <p>Description du besoin n°2</p>
                </div>
                <div id="list-elt-3" class="ui segment list-elt" style="display:none;">
                    <h3>Besoin n°3</h3>
                    <p>Description du besoin n°3</p>
                </div>
            </section>
        </div>
        <!-- Pop-ups -->
        <div id="need-modal" class="ui small modal">
            <div class="ui icon header">
                Ajouter/Modifier un besoin
            </div>
            <div class="content">
                <form method="post" action="#" class="ui form">
                    <div class="field">
                        <label>Type <span class="mandatory">*</span></label>
                        <div class="ui search">
                            <div class="ui icon input">
                                <input name="type" class="prompt" placeholder="Type de service que vous souhaitez recevoir" type="text">
                                <i class="search icon"></i>
                            </div>
                        </div>
                    </div>
                    <div class="field">
                        <label>Description <span class="mandatory">*</span></label>
                        <textarea name="description" placeholder="Description du service que vous souhaitez recevoir"></textarea>
                    </div>
                    <div class="field">
                        <label>Date limite</label>
                        <input class="datepicker" name="deadline" placeholder="Date limite du service que vous souhaitez recevoir" type="text">
                    </div>
                    <span class="mandatory">* Champs requis</span>
                </form>
            </div>
            <div class="actions ui grid container">
                <div class="ui black cancel button">
                    <i class="remove icon"></i>
                    Annuler
                </div>
                <div class="ui orange ok button">
                    <i class="checkmark icon"></i>
                    Valider
                </div>
            </div>
        </div>
        <div class="ui small modal delete-list-elt">
            <div class="ui icon header">
                <i class="warning sign icon"></i>
                Voulez-vous vraiment supprimer ce besoin ?
            </div>
            <div class="actions ui grid container">
                <div class="ui black cancel button">
                    <i class="remove icon"></i>
                    Non
                </div>
                <div class="ui orange ok button">
                    <i class="checkmark icon"></i>
                    Oui
                </div>
            </div>
        </div>
        <jsp:include page="includes/logout-modal.jsp" />
        <jsp:include page="includes/scripts.jsp" />
        <script src="${root}/js/formValidation.js"></script>
        <script src="${root}/js/pickadate-legacy.js"></script>
        <script src="${root}/js/pickadate.js"></script>
        <script src="${root}/js/pickadate.date.js"></script>
        <script src="${root}/js/pickadate-fr_FR.js"></script>
        <script>
            $( document ).ready(function() {
                $( ".datepicker" ).pickadate();
            });
        </script>
    </body>
</html>
