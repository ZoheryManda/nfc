<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<spring:url value="/resources/css/theme-default.css" var="themeDefault"/>

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
                        </div>
                        <div class="panel-body">
                            <div class="row">

                                <form name="utilsateurForm" id="utilsateurForm" commandName="utilsateurForm" action="/nfc/rest/admin/etudiants/" method="post">
                                    <div class="col-md-6">

                                        <div class="form-group">
                                            <label class="col-md-3 control-label">Nom</label>
                                            <div class="col-md-9">
                                                <div class="input-group">
                                                <span class="input-group-addon"><span
                                                        class="fa fa-pencil"></span></span>
                                                    <input type="text" class="form-control" name="nom" id="nom">
                                                </div>
                                                <span class="help-block"></span>
                                            </div>
                                        </div>

                                        <div class="form-group">
                                            <label class="col-md-3 control-label">Prenom</label>
                                            <div class="col-md-9">
                                                <div class="input-group">
                                                <span class="input-group-addon"><span
                                                        class="fa fa-pencil"></span></span>
                                                    <input type="text" class="form-control" name="prenom" id="prenom">
                                                </div>
                                                <span class="help-block"></span>
                                            </div>
                                        </div>

                                        <div class="form-group">
                                            <label class="col-md-3 control-label">Mot de passe</label>
                                            <div class="col-md-9 col-xs-12">
                                                <div class="input-group">
                                                <span class="input-group-addon"><span
                                                        class="fa fa-unlock-alt"></span></span>
                                                    <input type="password" class="form-control" name="pwd" id="pwd">
                                                </div>
                                                <span class="help-block"></span>
                                            </div>
                                        </div>

                                        <div class="form-group">
                                            <label class="col-md-3 col-xs-12 control-label"></label>
                                            <div class="col-md-6 col-xs-12">
                                                <button class="btn btn-default" type="submit" name="ajout" id="ajout">
                                                    Ajout
                                                </button>
                                                <span class="help-block"></span>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="col-md-6">

                                        <div class="form-group">
                                            <label class="col-md-3 control-label">Date de naissance</label>
                                            <div class="col-md-9">
                                                <div class="input-group">
                                                <span class="input-group-addon"><span
                                                        class="fa fa-calendar"></span></span>
                                                    <input type="text" value="2014-11-01"
                                                           class="form-control datepicker"
                                                           id="datenaissance" name="datenaissance">
                                                </div>
                                                <span class="help-block"></span>
                                            </div>
                                        </div>

                                        <div class="form-group">
                                            <label class="col-md-3 control-label">Telephone</label>
                                            <div class="col-md-9">
                                                <div class="input-group">
                                                <span class="input-group-addon"><span
                                                        class="fa fa-pencil"></span></span>
                                                    <input type="text" class="form-control" name="telephone"
                                                           id="telephone">
                                                </div>
                                                <span class="help-block"></span>
                                            </div>
                                        </div>

                                        <div class="form-group">
                                            <label class="col-md-3 control-label">Adresse</label>
                                            <div class="col-md-9">
                                                <div class="input-group">
                                                <span class="input-group-addon"><span
                                                        class="fa fa-pencil"></span></span>
                                                    <input type="text" class="form-control" name="adresse" id="adresse">
                                                </div>
                                                <span class="help-block"></span>
                                            </div>
                                        </div>

                                        <div class="form-group">
                                            <label class="col-md-3 col-xs-12 control-label"></label>
                                            <div class="col-md-6 col-xs-12">
                                                <button class="btn btn-default" type="button" name="rechercher"
                                                        id="rechercher">
                                                    Rechercher
                                                </button>
                                                <span class="help-block"></span>
                                            </div>
                                        </div>
                                    </div>
                                </form>

                            </div>

                            <table class="table table-hover">
                                <thead>
                                <tr>
                                    <th>#</th>
                                    <th>Nom</th>
                                    <th>Prenom</th>
                                    <th>Date de naissance</th>
                                    <th>Adresse</th>
                                    <th>Telephone</th>
                                    <th>Action</th>
                                </tr>
                                </thead>
                                <tbody>
                                <c:forEach var="etudiant" items="${etudiants}">
                                    <tr>
                                        <td>${etudiant.idutilisateur}</td>
                                        <td>${etudiant.nom}</td>
                                        <td>${etudiant.prenom}</td>
                                        <td>${etudiant.datenaissance}</td>
                                        <td>${etudiant.adresse}</td>
                                        <td>${etudiant.telephone}</td>
                                        <td>
                                            <a class="btn btn-danger"
                                               href="/nfc/rest/admin/etudiants/delete/${etudiant.idutilisateur}">
                                                Supprimer
                                            </a>
                                        </td>
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
<%@include file="../template/script.jsp" %>
<!-- END SCRIPTS -->

</body>
</html>
