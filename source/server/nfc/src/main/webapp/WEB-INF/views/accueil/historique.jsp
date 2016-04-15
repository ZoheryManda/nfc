<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<spring:url value="/resources/css/theme-default.css" var="themeDefault"/>

<spring:url value="/resources/js/plugins/jquery/jquery.min.js" var="jquery"/>
<spring:url value="/resources/js/plugins/jquery/jquery-ui.min.js" var="jqueryUI"/>
<spring:url value="/resources/js/plugins/bootstrap/bootstrap.min.js" var="bootstrap"/>
<spring:url value="/resources/js/plugins.js" var="plugins"/>
<spring:url value="/resources/js/actions.js" var="actions"/>

<!DOCTYPE html>
<html lang="en">
<head>
    <!-- META SECTION -->
    <title>${title}</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
    <meta name="viewport" content="width=device-width, initial-scale=1"/>

    <link rel="icon" href="favicon.ico" type="image/x-icon"/>
    <!-- END META SECTION -->

    <!-- CSS INCLUDE -->
    <!--<link rel="stylesheet" type="text/css" id="theme" href="css/theme-default.css"/>-->
    <link href="${themeDefault}" rel="stylesheet"/>
    <!-- EOF CSS INCLUDE -->
</head>
<body>
<!-- START PAGE CONTAINER -->
<div class="page-container">

    <!-- START PAGE SIDEBAR -->
    <%@include file="../template/sideabar.jsp" %>
    <!-- END PAGE SIDEBAR -->

    <!-- PAGE CONTENT -->
    <div class="page-content">

        <!-- START X-NAVIGATION VERTICAL -->
        <%@include file="../template/nav_vertical.jsp" %>
        <!-- END X-NAVIGATION VERTICAL -->

        <!-- START BREADCRUMB -->
        <%@include file="../template/breadcrumb.jsp" %>
        <!-- END BREADCRUMB -->

        <div class="page-title">
            <h2><span class="fa fa-arrow-circle-o-left"></span> ${title}</h2>
        </div>

        <!-- PAGE CONTENT WRAPPER -->
        <div class="page-content-wrap">

            <div class="row">
                <div class="col-md-12">

                    <div class="panel panel-default">
                        <div class="panel-heading">
                            <h3 class="panel-title">${subtitle}</h3>
                            <a class="btn btn-default pull-right" href="/nfc/rest/admin/historique/">Tous</a>
                            <a class="btn btn-default pull-right" href="/nfc/rest/admin/historique/rendu">Rendu</a>
                            <a class="btn btn-default pull-right" href="/nfc/rest/admin/historique/emprunte">Emprunt</a>
                        </div>
                        <div class="panel-body">
                            <table class="table table-hover">
                                <thead>
                                <tr>
                                    <th>#</th>
                                    <th>ID/Matricule Etudiant</th>
                                    <th>ID/SN Materiel</th>
                                    <th>Date d'emprunt</th>
                                    <th>Date de remise</th>
                                    <th>Action</th>
                                </tr>
                                </thead>
                                <tbody>
                                <c:forEach var="mouvement" items="${mouvements}">
                                    <tr>
                                        <td>${mouvement.idmvt}</td>
                                        <td>${mouvement.idutilisateur}</td>
                                        <td>${mouvement.idmateriel}</td>
                                        <td>${mouvement.dateemprunt}</td>
                                        <td>${mouvement.dateremise}</td>
                                        <td></td>
                                    </tr>
                                </c:forEach>

                                </tbody>
                            </table>

                        </div>
                    </div>

                </div>
            </div>

        </div>
        <!-- END PAGE CONTENT WRAPPER -->
    </div>
    <!-- END PAGE CONTENT -->
</div>
<!-- END PAGE CONTAINER -->

<!-- MESSAGE BOX-->
<%@include file="../template/message_box.jsp" %>
<!-- END MESSAGE BOX-->

<!-- START SCRIPTS -->
<!-- START PLUGINS -->
<script type="text/javascript" src="${jquery}"></script>
<script type="text/javascript" src="${jqueryUI}"></script>
<script type="text/javascript" src="${bootstrap}"></script>
<!-- END PLUGINS -->

<!-- THIS PAGE PLUGINS -->

<!-- END PAGE PLUGINS -->

<!-- START TEMPLATE -->
<script type="text/javascript" src="${plugins}"></script>
<script type="text/javascript" src="${actions}"></script>
<!-- END TEMPLATE -->
<!-- END SCRIPTS -->
</body>
</html>
