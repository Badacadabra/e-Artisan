<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="root" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html lang="fr">
    <head>
        <jsp:include page="includes/head.jsp">
            <jsp:param name="title" value="Mon fil d'actualité" />
        </jsp:include>
    </head>
    <body class="mCustomScrollbar" data-mcs-theme="dark">
        <jsp:include page="includes/header.jsp" />
        <div class="ui grid container">
            <jsp:include page="includes/nav.jsp">
                <jsp:param name="actus" value="active" />
                <jsp:param name="userId" value="${currentUser.id}" />
            </jsp:include>
            <section id="main-section" class="reset">
                <div class="ui feed">
                    <div class="event">
                        <div class="label">
                            <i class="pin icon"></i>
                        </div>
                        <div class="content">
                            <div class="summary">
                                <a href="#">Tutu</a> a refusé un cycle qui vous concerne.
                                <div class="date">
                                    Il y a 1 jour
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="event">
                        <div class="label">
                            <i class="pin icon"></i>
                        </div>
                        <div class="content">
                            <div class="summary">
                                <a href="#">Tata</a> a accepté un cycle qui vous concerne.
                                <div class="date">
                                    Il y a 3 jours
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="event">
                        <div class="label">
                            <i class="pin icon"></i>
                        </div>
                        <div class="content">
                            <div class="summary">
                                Vous avez accepté le cycle n°1.
                                <div class="date">
                                    Il y a 1 semaine
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </section>
        </div>
        <jsp:include page="includes/modals.jsp" />
        <jsp:include page="includes/scripts.jsp" />
    </body>
</html>
