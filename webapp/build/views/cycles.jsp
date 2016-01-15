<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="root" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html lang="fr">
    <head>
        <jsp:include page="includes/head.jsp">
            <jsp:param name="title" value="Cycles de services" />
        </jsp:include>
    </head>
    <body class="mCustomScrollbar" data-mcs-theme="dark">
        <jsp:include page="includes/header.jsp" />
        <div class="ui grid container">
            <jsp:include page="includes/nav.jsp">
                <jsp:param name="cycles" value="active" />
            </jsp:include>
            <section id="main-section">
                <h2 id="show-cycles" class="ui center aligned icon header">
                    <i class="circular refresh icon"></i>
                    Générer les cycles de services
                </h2>
                <c:forEach items="${cycles2}" var="item">
                    <div>${item}</div>
                </c:forEach>
                <table class="ui black celled table" style="display:none;">
                    <thead class="full-width">
                        <tr>
                            <th>Prestataire</th>
                            <th>Service</th>
                            <th>Bénéficiaire</th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr>
                            <td>Toto</td>
                            <td>Charpenterie</td>
                            <td>Tutu</td>
                        </tr>
                        <tr>
                            <td>Tutu</td>
                            <td>Maçonnerie</td>
                            <td>Tata</td>
                        </tr>
                        <tr>
                            <td>Tata</td>
                            <td>Jardin/Paysage</td>
                            <td>Toto</td>
                        </tr>
                    </tbody>
                    <tfoot class="full-width">
                        <tr>
                            <th colspan="3">
                            <div class="ui right fluid small labeled icon button">
                                <i class="checkmark icon"></i>
                                Accepter ce cycle
                            </div>
                        </tr>
                    </tfoot>
                </table>
            </section>
        </div>
        <jsp:include page="includes/logout-modal.jsp" />
        <jsp:include page="includes/scripts.jsp" />
    </body>
</html>
