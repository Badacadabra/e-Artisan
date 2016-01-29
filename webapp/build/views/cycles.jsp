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
                <jsp:param name="userId" value="${currentUser.id}" />
            </jsp:include>
            <section id="main-section">
                <h2 id="show-cycles" class="ui center aligned icon header">
                    <i class="circular refresh icon"></i>
                    Générer les cycles de services
                </h2>
                <table class="ui black celled table" style="display:none;">
                    <thead class="full-width">
                        <tr>
							<th>Bénéficiaire</th>
                            <th>Service</th>
							<th>Prestataire</th>
                        </tr>
                    </thead>
                    <tbody>
						<c:forEach items="${cycles2}" var="item">
							<tr>
								<td>${item.sender.firstName} ${item.sender.name}</td>
								<td>${item.service.name}</td>
								<td>${item.receiver.firstName} ${item.receiver.name}</td>
							</tr>
						</c:forEach>
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
