<%@ include file="/WEB-INF/include/taglibs.jspf"%>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>BCA Law</title>
    <!-- Core CSS - Include with every page -->
    <link href="<c:url value="/assets/plugins/bootstrap/3.3.6/bootstrap.css"/>" rel="stylesheet" />
    <link href="<c:url value="/assets/font-awesome/css/font-awesome.css"/>" rel="stylesheet" />
    <link href="<c:url value="/assets/plugins/pace/pace-theme-big-counter.css"/>" rel="stylesheet" />
    <link href="<c:url value="/assets/css/style.css"/>" rel="stylesheet" />
    <link href="<c:url value="/assets/css/main-style.css"/>" rel="stylesheet" />
    
    <!-- Core Scripts - Include with every page -->
    <script src="<c:url value="/assets/plugins/jquery-1.10.2.js"/>"></script>
    <!--script src="<c:url value="/assets/plugins/jquery.min.js"/>"></script-->
    <script src="<c:url value="/assets/plugins/bootstrap/3.3.6/bootstrap.min.js"/>"></script>
    <script src="<c:url value="/assets/plugins/metisMenu/jquery.metisMenu.js"/>"></script>
    <script src="<c:url value="/assets/plugins/pace/pace.js"/>"></script>
    <script src="<c:url value="/assets/scripts/siminta.js"/>"></script>
    <!-- Page-Level Plugin Scripts-->
    
    <script>
    	'use strict';
    	var base_url = '<c:url value="/"/>';
    </script>
</head>
<body>
    <!--  wrapper -->
    <div id="wrapper">
		<div id="header">
			<tiles:insertAttribute name="header" />
		</div>
		<div class="clear"></div>
		<div id="nav">
			<tiles:insertAttribute name="nav"/>
		</div>
		<div id="content">
			<tiles:insertAttribute name="content"/>
		</div>
		<div class="clear"></div>
		<div id="footer">
			<tiles:insertAttribute name="footer" />
		</div>
	</div>
</body>

</html>
