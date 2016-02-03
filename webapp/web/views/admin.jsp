<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="root" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html lang="fr">
    <head>
        <jsp:include page="includes/head.jsp">
            <jsp:param name="title" value="Zone d'administration" />
        </jsp:include>
        <link rel="stylesheet" href="${root}/css/pickadate.css">
        <link rel="stylesheet" href="${root}/css/pickadate.date.css">
    </head>
    <body class="mCustomScrollbar" data-mcs-theme="dark">
        <jsp:include page="includes/header.jsp" />
        <div class="ui grid container">
            <jsp:include page="includes/nav.jsp">
                <jsp:param name="admin" value="active" />
                <jsp:param name="userId" value="${currentUser.id}" />
            </jsp:include>
            <section id="main-section">
                <h2 class="ui center aligned icon header user-modal-link" id="add-user">
                    <i class="circular user icon"></i>
                    Ajouter un utilisateur
                </h2>
                <div class="ui middle aligned divided list">
                    <c:forEach items="${users}" var="user" varStatus="loop">
                        <div class="item">
                            <div class="right floated content">
                                <div class="ui inverted orange button list-elt-link">Afficher</div>
                                <div class="ui inverted brown button user-modal-link">Modifier</div>
                                <div class="ui inverted red button delete-btn">Supprimer</div>
                                <input type="hidden" name="tmpId_${user.id}" id="tmpId_${user.id}" value="${user.id}">
                                <input type="hidden" name="tmpName_${user.id}" id="tmpName_${user.id}" value="${user.name}">
                                <input type="hidden" name="tmpFirstName_${user.id}" id="tmpFirstName_${user.id}" value="${user.firstName}">
                                <input type="hidden" name="tmpEmail_${user.id}" id="tmpEmail_${user.id}" value="${user.email}">
                                <input type="hidden" name="tmpPassword_${user.id}" id="tmpPassword_${user.id}" value="${user.password}">
                                <input type="hidden" name="needOrOffer" value="admin">
                            </div>
                            <i class="large user middle aligned icon"></i>
                            <div class="content">
                                <span class="bold">${user.firstName} ${user.name}</span>
                                <!-- <div class="description">Membre depuis 2 minutes</div> -->
                            </div>
                        </div>
                    </c:forEach>
                </div>
            </section>
        </div>
        <!-- Pop-ups -->
        <div id="user-modal" class="ui small modal">
            <div class="ui icon header">
                Ajouter/Modifier un utilisateur
            </div>
            <div class="content">
                <form class="ui form" method="post" action="register">
                    <div class="two fields">
                        <div class="field">
                            <label>Nom <span class="mandatory">*</span></label>
                            <input name="lastName" type="text" placeholder="Nom de l'utilisateur" value="">
                        </div>
                        <div class="field">
                            <label>Prénom <span class="mandatory">*</span></label>
                            <input name="firstName" type="text" placeholder="Prénom de l'utilisateur" value="">
                        </div>
                    </div>
                    <div class="two fields">
                        <div class="field">
                            <label>E-mail <span class="mandatory">*</span></label>
                            <input name="email" type="text" placeholder="E-mail de l'utilisateur" value="">
                        </div>
                        <div class="field">
                            <label>Mot de passe <span class="mandatory">*</span></label>
                            <input name="password" type="password" placeholder="Votre mot de passe">
                        </div>
                    </div>
                    <div class="mandatory">* Champs requis</div>
                    <input type="hidden" name="currentUserId" id="currentUserId"/>
                    <input type="hidden" name="mode" value="insert"/>
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
                Voulez-vous vraiment supprimer cet utilisateur ?
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
        <jsp:include page="includes/modals.jsp" />
        <jsp:include page="includes/scripts.jsp" />
        <script src="${root}/js/formValidation.js"></script>
    </body>
</html>
