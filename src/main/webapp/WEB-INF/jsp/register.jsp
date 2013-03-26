<!doctype html>
<%@ include file="/WEB-INF/jsp/include.jsp" %>

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>Mapuche</title>
    <link rel="stylesheet" href="/resources/css/reset.css" />
    <link rel="stylesheet" href="/resources/css/960.css" />
    <link rel="stylesheet" href="/resources/css/style.css" type="text/css" media="all">

    <script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.4.2/jquery.js"></script>

</head>

<body class="container_12">

    <div class="title-container grid_4 prefix_4 suffix_4">
        <a href="/home">
            <img src="/resources/img/mapuche.png" alt="Mapuche" />
        </a>
    </div>
    <div class="central">
        <div class="central-container">
            <div class="central-container2">
                <section class="contents-area">
                    
                    <div id="register-title">
                        <span class="left" id="register-title-text">Register</span>
                        <div class="green-ribbon-bent"></div>
                    </div>

                    <div class="clear"></div>

                    <form:form method="post" action="/register" modelAttribute="registration" class="register-form">
                        <c:set var="errors"><form:errors path="*"/></c:set>
                        <c:if test="${not empty errors}">
                            <div class="alert alert-error">
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
                            </table>
                        </fieldset>
                        <div >
                            <input name="commit" type="submit" class="beige-ribbon right" value="Register"/>
                        </div>

                        <div class="clear"></div>
                    </form:form>
                </section>
            </div>
        </div>
    </div>

    <%@ include file="/WEB-INF/jsp/footer.jsp" %>
            
    <script type="text/javascript">
        document.getElementById('username').focus();
    </script>

</body>
</html>
