<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="root" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html lang="fr">
    <head>
        <jsp:include page="includes/head.jsp">
            <jsp:param name="title" value="Toto" />
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
                    <h2>Prénom Nom</h2>
                    <button id="edit-profile-button" class="ui inverted orange button">
                        <i class="write icon"></i>
                        Modifier le profil
                    </button>
                </div>
                <p class="italic">Inscrit le...</p>
                <img id="user-photo" src="${root}/assets/default_image.png" alt="Nom du membre">
                <p id="user-description">Lorem ipsum dolor sit amet, consectetur adipiscing elit. Vivamus pellentesque faucibus est, sed finibus velit lacinia gravida. Donec eros libero, aliquet a elit vitae, commodo dictum ligula. Cras urna sem, auctor ut massa nec, cursus dignissim lorem. Integer auctor tortor et justo ullamcorper, et placerat elit pretium. Praesent vel euismod dolor. Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Quisque luctus urna metus, in faucibus diam scelerisque condimentum. Praesent a lacinia ex. Nunc eu finibus arcu, ac bibendum turpis. Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Cras tempor faucibus augue in mollis. Proin vitae sem id felis pharetra vehicula ut quis neque. In sodales vel odio sed interdum.</p>
                <form id="edit-profile-form" class="ui form" method="post" action="#" enctype="multipart/form-data" style="display:none;">
                    <div class="two fields">
                        <div class="field">
                            <label>Nom <span class="mandatory">*</span></label>
                            <input name="lastName" type="text" value="Toto">
                        </div>
                        <div class="field">
                            <label>Prénom <span class="mandatory">*</span></label>
                            <input name="firstName" type="text" value="Toto">
                        </div>
                    </div>
                    <div class="two fields">
                        <div class="field">
                            <label>Changer d'e-mail</label>
                            <input name="email" type="text" placeholder="Votre nouvel e-mail (si vous souhaitez le changer)">
                        </div>
                        <div class="field">
                            <label>Changer de mot de passe</label>
                            <input name="password" type="password" placeholder="Votre nouveau mot de passe (si vous souhaitez le changer)">
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
                        <textarea name="description" placeholder="Décrivez-vous en quelques lignes..."></textarea>
                    </div>
                    <div class="ui large buttons">
                        <button class="ui black button" type="reset">Annuler</button>
                        <button class="ui orange button" type="submit">Valider</button>
                    </div>
                    <div class="mandatory">* Champs requis</div>
                </form>
            </section>
        </div>
        <jsp:include page="includes/logout-modal.jsp" />
        <jsp:include page="includes/scripts.jsp" />
        <script src="${root}/js/formValidation.js"></script>
    </body>
</html>
