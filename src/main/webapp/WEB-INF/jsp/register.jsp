<!doctype html>
<%@ include file="/WEB-INF/jsp/include.jsp" %>

<html>
<head>
    <meta charset="utf-8">
    <title>Crowd game</title>

    <meta content="IE=edge,chrome=1" http-equiv="X-UA-Compatible">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <link href="http://twitter.github.com/bootstrap/assets/css/bootstrap.css" rel="stylesheet">
    <link href="http://twitter.github.com/bootstrap/assets/css/bootstrap-responsive.css" rel="stylesheet">

    <script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.4.2/jquery.js"></script>
    
    <!--
      IMPORTANT:
      This is Heroku specific styling. Remove to customize.
    -->
    <link href="http://heroku.github.com/template-app-bootstrap/heroku.css" rel="stylesheet">
    <!-- /// -->

</head>

<body>
<div class="navbar navbar-fixed-top">
    <div class="navbar-inner">
        <div class="container">
            <a href="/" class="brand">Crowd game</a>
            <a href="/" class="brand" id="heroku">by <strong>Clara</strong></a>
        </div>
    </div>
</div>

<div class="container">
    <div class="row">
        <div class="span8 offset2">

            <div class="page-header">
                <h1>Crowd game</h1>
            </div>

            <h2>Register</h2>

            <spring:url var="registerUrl"
                value="/register" />

            <form:form method="post" action="${authUrl}" modelAttribute="registration">

                <c:set var="errors"><form:errors path="*"/></c:set>
                <c:if test="${not empty errors}">
                    <div class="alert alert-error">
                        <button type="button" class="close" data-dismiss="alert">&times;</button>
                        <h4>Warning!</h4>
                        <p><spring:message code="registration.error" /></p>
                    </div>
                </c:if>

                <fieldset>
                    <table>
                        <tr>
                            <th><label for="username">Username:</label></th>
                            <td>
                                <form:input id="username" path="username" />

                                <div class="control-group error">
                                    <div class="controls">
                                        <form:errors path="username" cssClass="error help-inline" />
                                    </div>
                                </div>
                            </td>
                        </tr>
                        <tr>
                            <th><label for="password">Password:</label></th>
                            <td>
                                <form:input path="password" type="password" id="password" />
                                <small><a href="/account/resend_password">Forgot?</a></small>

                                <div class="control-group error">
                                    <div class="controls">
                                        <form:errors path="password" cssClass="error help-inline" />
                                    </div>
                                </div>
                            </td>
                        </tr>
                        <tr>
                            <th><label for="confirm-password">Confirm password:</label></th>
                            <td>
                                <form:input path="confirmPassword" type="password" id="confirm-password" />

                                <div class="control-group error">
                                    <div class="controls">
                                        <form:errors path="confirmPassword" cssClass="error help-inline" />
                                    </div>
                                </div>
                            </td>
                        </tr>
                        <tr>
                            <th><label for="email">Email:</label></th>
                            <td>
                                <form:input path="email" type="text" id="email" />

                                <div class="control-group error">
                                    <div class="controls">
                                        <form:errors path="email" cssClass="error help-inline" />
                                    </div>
                                </div>
                            </td>
                        </tr>
                        <tr>
                            <th><label for="dyslexic">Dyslexic:</label></th>
                            <td>
                                <form:checkbox path="dyslexic" id="dyslexic" />

                                <div class="control-group error">
                                    <div class="controls">
                                        <form:errors path="dyslexic" cssClass="error help-inline" />
                                    </div>
                                </div>
                            </td>
                        </tr>
                        <tr>
                            <th></th>
                            <td><input name="commit" type="submit" class="btn btn-primary" value="Register"/></td>
                    </div>
                        </tr>
                    </table>
                </fieldset>
                    
            </form:form>

            <script type="text/javascript">
                document.getElementById('username').focus();
            </script>
            
        </div>
    </div>
</div>

</body>
</html>
