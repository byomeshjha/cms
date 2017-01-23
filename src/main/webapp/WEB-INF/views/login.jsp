<%@ include file="/WEB-INF/include/taglibs.jspf"%>
<!DOCTYPE html>
<html>

<head>
  <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>BCA Law Manager</title>
    <!-- Core CSS - Include with every page -->
    <link href="<c:url value="/assets/plugins/bootstrap/bootstrap.css"/>" rel="stylesheet" />
    <link href="<c:url value="/assets/font-awesome/css/font-awesome.css"/>" rel="stylesheet" />
    <link href="<c:url value="/assets/plugins/pace/pace-theme-big-counter.css"/>" rel="stylesheet" />
    <link href="<c:url value="/assets/css/style.css"/>" rel="stylesheet" />
    <link href="<c:url value="/assets/css/main-style.css"/>" rel="stylesheet" />
</head>

<body class="body-Login-back">

    <div class="container">

        <div class="row">
            <div class="col-xs-6 col-md-offset-4 text-center login-box-text">
              <!--img src="<c:url value="/assets/img/logo.png"/>" alt=""/-->
              <h4>BHAVYA CHAUDHARY &amp; ASSOCIATES LAW FIRM</h4>
            </div>
            <div class="col-md-4 col-md-offset-4">
                <div class="login-panel panel panel-default">
                    <div class="panel-heading">
                        <h3 class="panel-title">Please Sign In</h3>
                    </div>
                    <div class="panel-body">
                    	<c:url var="actionValue" value="/login"/>
                        <form:form role="form" method="post" action="${actionValue}" commandName="userForm">
                            <fieldset>
                            	<div class="form-group">
                            		<c:if test="${not empty error}">
                            			<label class="errorblock">
                            				<c:forEach items="${error}" var="er">
                            					${er} 
    										</c:forEach>
                            			</label>
                            		</c:if>
                            	</div>
                                <div class="form-group">
                                    <form:input class="form-control" placeholder="E-mail" path="emailAddress" type="email" autofocus="autofocus" />
                                    <form:errors path="emailAddress" cssClass="error" />
                                </div>
                                <div class="form-group">
                                    <form:input class="form-control" placeholder="Password" path="password" type="password" value=""/>
                                    <form:errors path="password" cssClass="error" />
                                </div>
                                <div class="checkbox">
                                    <label>
                                        <input name="remember" type="checkbox" value="Remember Me">Remember Me
                                    </label>
                                </div>
                                <!-- Change this to a button or input when using this as a form -->
                                <input type="submit" value="Login" class="btn btn-lg btn-success btn-block"/>
                            </fieldset>
                        </form:form>
                    </div>
                </div>
            </div>
        </div>
    </div>

     <!-- Core Scripts - Include with every page -->
    <script src="//code.jquery.com/jquery-2.0.3.min.js"></script>
    <script src="<c:url value="/assets/plugins/bootstrap/bootstrap.min.js"/>"></script>
    <script src="<c:url value="/assets/plugins/metisMenu/jquery.metisMenu.js"/>"></script>
</body>

</html>
