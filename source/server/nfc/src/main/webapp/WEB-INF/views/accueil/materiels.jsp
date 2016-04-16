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

                                <form name="materielForm" id="materielForm" commandName="materielForm"
                                      action="/nfc/rest/admin/materiels/" method="post">
                                    <div class="col-md-6">

                                        <div class="form-group">
                                            <label class="col-md-3 control-label">Libelle</label>
                                            <div class="col-md-9">
                                                <div class="input-group">
                                                <span class="input-group-addon"><span
                                                        class="fa fa-pencil"></span></span>
                                                    <input type="text" class="form-control" name="libelle" id="libelle">
                                                </div>
                                                <span class="help-block"></span>
                                            </div>
                                        </div>

                                        <div class="form-group">
                                            <label class="col-md-3 control-label">Code</label>
                                            <div class="col-md-9">
                                                <div class="input-group">
                                                <span class="input-group-addon"><span
                                                        class="fa fa-pencil"></span></span>
                                                    <input type="text" class="form-control" name="codemateriel"
                                                           id="codemateriel">
                                                </div>
                                                <span class="help-block"></span>
                                            </div>
                                        </div>

                                        <div class="form-group">
                                            <label class="col-md-3 col-xs-12 control-label"></label>
                                            <div class="col-md-6 col-xs-12">
                                                <button class="btn btn-default" type="submit" name="ajouter"
                                                        id="ajouter">
                                                    Ajouter
                                                </button>
                                                <span class="help-block"></span>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="col-md-6">

                                        <div class="form-group">
                                            <label class="col-md-3 control-label">UIID</label>
                                            <div class="col-md-9 col-xs-12">
                                                <textarea class="form-control" rows="3" name="uiid"
                                                          id="uiid"></textarea>
                                                <span class="help-block"></span>
                                            </div>
                                        </div>

                                        <div class="form-group">
                                            <label class="col-md-3 control-label">Type</label>
                                            <div class="col-md-9">
                                                <select class="form-control select" id="idtype" name="idtype">
                                                    <c:forEach var="typemateriel" items="${typemateriels}">
                                                        <option value="${typemateriel.idtype}">${typemateriel.valeur}</option>
                                                    </c:forEach>
                                                </select>
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
                                    <th>Libelle</th>
                                    <th>Code</th>
                                    <th>UIID</th>
                                    <th>Type</th>
                                    <th>Action</th>
                                </tr>
                                </thead>
                                <tbody>
                                <c:forEach var="materiel" items="${materiels}">
                                    <tr>
                                        <td>${materiel.idmateriel}</td>
                                        <td>${materiel.libelle}</td>
                                        <td>${materiel.codemateriel}</td>
                                        <td>${materiel.uiid}</td>
                                        <td>${materiel.idtype}</td>
                                        <td>
                                            <a class="btn btn-danger"
                                               href="/nfc/rest/admin/materiels/delete/${materiel.idmateriel}">
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
