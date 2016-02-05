<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="root" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html lang="fr" class="no-js">
    <head>
        <meta charset="UTF-8">
        <title>e-Artisan : Le site qui va vous rendre service !</title>
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta name="author" content="Macky Dieng, Baptiste Vannesson">
        <link rel="stylesheet" href="css/style.css">
        <link rel="stylesheet" href="css/hero-slider-reset.css">
        <link rel="stylesheet" href="css/hero-slider-style.css">
        <link rel="stylesheet" href="css/jquery.mCustomScrollbar.min.css">
        <link rel="stylesheet" href="semantic/dist/semantic.min.css">
    </head>
    <body class="mCustomScrollbar" data-mcs-theme="dark">
        <header class="cd-header">
            <div id="cd-logo">
                <a href="${root}"><h1>e-Artisan</h1></a>
            </div>

            <nav class="cd-primary-nav">
                 <c:choose>
                    <c:when test="${currentUser!=null}">
                        <a id="logout" href="logout" class="sign-in-link">Déconnexion</a>
                        <a href="accueil" class="sign-up-link">Mon espace</a>
                    </c:when>
                    <c:otherwise>
                        <a href="#0" class="sign-in-link">Connexion</a>
                        <a href="#0" class="sign-up-link">Inscription</a>
                    </c:otherwise>
                </c:choose>

            </nav> <!-- .cd-primary-nav -->
        </header>

        <section class="cd-hero">
            <ul class="cd-hero-slider autoplay">
                <li class="selected">
                    <div class="cd-full-width">
                        <div class="desc">
                            <h2>Solidarité</h2>
                            <p>Parce qu'entre artisans, il faut se serrer les coudes !</p>
                        </div>
                    </div> <!-- .cd-full-width -->
                </li>
                <li>
                    <div class="cd-full-width">
                        <div class="desc">
                            <h2>Simplicité</h2>
                            <p>Demandez un service à vos confrères en 5 minutes !</p>
                        </div>
                    </div> <!-- .cd-full-width -->
                </li>
                <li>
                    <div class="cd-full-width">
                        <div class="desc">
                            <h2>Utilité</h2>
                            <p>On vous rend service, et vous ne payez rien !</p>
                        </div>
                    </div> <!-- .cd-full-width -->
                </li>
                <li>
                    <div class="cd-full-width">
                        <div class="desc">
                            <h2>Réciprocité</h2>
                            <p>Donner pour recevoir, telle est votre devise !</p>
                        </div>
                    </div> <!-- .cd-full-width -->
                </li>
                <li>
                    <div class="cd-full-width">
                        <div class="desc">
                            <h2>Efficacité</h2>
                            <p>Notre algorithme de matching calcule pour vous les cycles de services optimaux au sein du réseau !</p>
                        </div>
                    </div> <!-- .cd-full-width -->
                </li>
            </ul> <!-- .cd-hero-slider -->

            <div class="cd-slider-nav">
                <nav>
                    <span class="cd-marker item-1"></span>

                    <ul>
                        <li class="selected"><a href="#0">1</a></li>
                        <li><a href="#0">2</a></li>
                        <li><a href="#0">3</a></li>
                        <li><a href="#0">4</a></li>
                        <li><a href="#0">5</a></li>
                    </ul>
                </nav>
            </div> <!-- .cd-slider-nav -->
        </section> <!-- .cd-hero -->

        <main class="cd-main-content">
            <p>
                e-Artisan est un mini-réseau social développé par Macky Dieng et Baptiste Vannesson, dans le cadre d'un projet du M2-DNR2I à l'université de Caen Normandie. Il s'agit, plus précisément, d'un site de mise en relation entre artisans dans une optique d'échange de services.
            </p>
            <p>
                Sur e-Artisan, tout utilisateur possédant un compte peut créer une liste de besoins, autrement dit la liste des services qu'il souhaite recevoir. En contrepartie, il doit constituer une liste d'offres, autrement dit la liste des services qu'il peut rendre.
                À partir de là, l'application calcule automatiquement, selon l'algorithme BFS (Breadth First Search), les cycles de services réalisables au sein du réseau.
            </p>
            <div class="ui statistics">
                <div class="statistic">
                    <div class="value">
                        <i class="refresh icon"></i> 19
                    </div>
                    <div class="label">
                        Cycle le plus long
                    </div>
                </div>
            </div>
        </main> <!-- .cd-main-content -->

        <!-- Pop-ups -->
        <div id="sign-in" class="ui small modal" style="display:none;">
            <div class="header">Connexion</div>
            <div class="content">
                <form method="post" action="auth" class="ui form">
                    <div class="two fields">
                        <div class="field">
                            <label>Identifiant</label>
                            <input name="login" placeholder="Votre e-mail" type="text">
                        </div>
                        <div class="field">
                            <label>Mot de passe</label>
                            <input name="password" placeholder="Votre mot de passe" type="password">
                        </div>
                    </div>
                    <c:if test="${!empty errorLogin}">
                        <span class="errors">${errorLogin}</span>
                    </c:if>
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
        <div id="sign-up" class="ui small modal" style="display:none;">
            <div class="header">Inscription</div>
            <div class="content">
                <form method="post" action="register" class="ui form">
                    <div class="two fields">
                        <div class="field">
                            <label>Nom <span class="mandatory">*</span></label>
                            <input name="lastName" placeholder="Votre nom" type="text">
                        </div>
                        <div class="field">
                            <label>Prénom <span class="mandatory">*</span></label>
                            <input name="firstName" placeholder="Votre prénom" type="text">
                        </div>
                    </div>
                    <div class="two fields">
                        <div class="field">
                            <label>E-mail <span class="mandatory">*</span></label>
                            <input name="email" placeholder="Votre e-mail" type="text">
                        </div>
                        <div class="field">
                            <label>Mot de passe <span class="mandatory">*</span></label>
                            <input name="password" type="password" placeholder="Votre mot de passe">
                        </div>
                    </div>
                    <span class="mandatory">* Champs requis</span>
                    <c:if test="${!empty errorRegister}">
                        <span class="errors">${errorRegister}</span>
                    </c:if>
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
        <jsp:include page="includes/logout-modal.jsp" />
        <script src="js/jquery-2.1.1.js"></script>
        <script src="js/modernizr.js"></script>
        <script src="js/hero-slider-main.js"></script>
        <script src="js/jquery.mCustomScrollbar.concat.min.js"></script>
        <script src="semantic/dist/semantic.min.js"></script>
        <script src="js/gui.js"></script>
        <script src="js/formValidation.js"></script>
        <c:if test="${!empty errorLogin}">
            <script>
                $(function() {
                    $( ".sign-in-link" ).trigger( "click" );
                });
            </script>
        </c:if>
        <c:if test="${!empty errorRegister}">
            <script>
                $(function() {
                    $( ".sign-up-link" ).trigger( "click" );
                });
            </script>
        </c:if>
    </body>
</html>
