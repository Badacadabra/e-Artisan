<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="root" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html lang="fr">
    <head>
        <jsp:include page="includes/head.jsp">
            <jsp:param name="title" value="${user.firstName}" />
        </jsp:include>
    </head>
    <body class="mCustomScrollbar" data-mcs-theme="dark">
        <jsp:include page="includes/header.jsp" />
        <div class="ui grid container">
            <jsp:include page="includes/nav.jsp">
                <jsp:param name="profil" value="active" />
            </jsp:include>
            <section id="main-section">
                <div id="profile-header">
                    <h2>${user.firstName} ${user.name}</h2>
                    <button id="edit-profile-button" class="ui inverted orange button">
                        <i class="write icon"></i>
                        Modifier le profil
                    </button>
                </div>
                <p class="italic">Inscrit le...</p>
                <img id="user-photo" src="${root}/assets/default_image.png" alt="${user.firstName} ${user.name}">
                <p id="user-description">${user.description}</p>
                <form id="edit-profile-form" class="ui form" method="GET" action="modifProfil" enctype="multipart/form-data" style="display:none;">
                    <div class="two fields">
                        <div class="field">
                            <label>Nom <span class="mandatory">*</span></label>
                            <input name="lastName" type="text" value="${user.name}">
                        </div>
                        <div class="field">
                            <label>Prénom <span class="mandatory">*</span></label>
                            <input name="firstName" type="text" value="${user.firstName}">
                        </div>
                    </div>
                    <div class="two fields">
                        <div class="field">
                            <label>Changer d'e-mail</label>
                            <input name="email" type="text" placeholder="Votre nouvel e-mail (si vous souhaitez le changer)" value="${user.email}">
                        </div>
                        <div class="field">
                            <label>Changer de mot de passe</label>
                            <input name="password" type="password" placeholder="Votre nouveau mot de passe (si vous souhaitez le changer)" value="${user.password}">
                        </div>
                    </div>
                    <div class="field">
                        <label>Photo</label>
                        <div>
                            <label for="file" class="ui icon button">
                                <i class="photo icon"></i>
                                Modifier la photo
                            </label>
                            <input type="file" id="file" name="photo" style="display:none;">
                        </div>
                    </div>
                    <div class="field">
                        <label>Description</label>
                        <textarea name="description" placeholder="Décrivez-vous en quelques lignes...">${user.description}</textarea>
                    </div>
                    <div class="ui large buttons">
                        <button class="ui black button" type="reset">Annuler</button>
                        <button class="ui orange button" type="submit">Valider</button>
                    </div>
                    <c:if test="${! empty error}">
						<p>${error}</p>
					</c:if>
                    <div class="mandatory">* Champs requis</div>
                </form>
            </section>
        </div>
        <jsp:include page="includes/logout-modal.jsp" />
        <jsp:include page="includes/scripts.jsp" />
        <script src="${root}/js/formValidation.js"></script>
    </body>
</html>
