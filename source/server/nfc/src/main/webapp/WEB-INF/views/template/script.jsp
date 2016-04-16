<spring:url value="/resources/js/plugins/jquery/jquery.min.js" var="jquery"/>
<spring:url value="/resources/js/plugins/jquery/jquery-ui.min.js" var="jqueryUI"/>
<spring:url value="/resources/js/plugins/bootstrap/bootstrap.min.js" var="bootstrap"/>
<spring:url value="/resources/js/plugins.js" var="plugins"/>
<spring:url value="/resources/js/actions.js" var="actions"/>

<spring:url value="/resources/js/plugins/icheck/icheck.min.js" var="icheck"/>
<spring:url value="/resources/js/plugins/mcustomscrollbar/jquery.mCustomScrollbar.min.js" var="mcustomscrollbar"/>
<spring:url value="/resources/js/plugins/bootstrap/bootstrap-datepicker.js" var="bsdatepicker"/>
<spring:url value="/resources/js/plugins/bootstrap/bootstrap-file-input.js" var="bsfi"/>
<spring:url value="/resources/js/plugins/bootstrap/bootstrap-select.js" var="bsselect"/>
<spring:url value="/resources/js/plugins/tagsinput/jquery.tagsinput.min.js" var="tagsinput"/>


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

<!-- THIS PAGE PLUGINS -->
<script type='text/javascript' src='${icheck}'></script>
<script type="text/javascript" src="${mcustomscrollbar}"></script>

<script type="text/javascript" src="${bsdatepicker}"></script>
<script type="text/javascript" src="${bsfi}"></script>
<script type="text/javascript" src="${bsselect}"></script>
<script type="text/javascript" src="${tagsinput}"></script>
<!-- END THIS PAGE PLUGINS -->

<!-- END SCRIPTS -->

