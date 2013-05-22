<!doctype html>
<%@ include file="/WEB-INF/jsp/include.jsp" %>

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>Mapuche</title>
    <link rel="stylesheet" href="/resources/css/reset.css" />
    <link rel="stylesheet" href="/resources/css/960.css" />
    <link rel="stylesheet" href="/resources/css/style.css" type="text/css" media="all">
    <link href='http://fonts.googleapis.com/css?family=Didact+Gothic' rel='stylesheet' type='text/css'>

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
                        <span class="left" id="register-title-text"><fmt:message key="passwordreset.title"/></span>
                        <div class="green-ribbon-bent"></div>
                    </div>

                    <div class="clear"></div>
            
                    <form:form method="post" action="/reset" modelAttribute="passwordResetData">

                        <c:set var="errors"><form:errors path="*"/></c:set>
                        <c:if test="${not empty errors}">
                            <div class="alert alert-error">
                                <h4><fmt:message key="warning"/></h4>
                                <p><spring:message code="password.change.error" /></p>
                            </div>
                        </c:if>

                        <fieldset class="center-box">
                            <form:hidden path="uid" value="${uid}"/>
                            <table>
                                <tr>
                                    <th><label for="username"><fmt:message key="login.usernameoremail"/></label></th>
                                    <td>
                                        <form:input path="username" id="username" />
                                        <div class="control-group error">
                                            <div class="controls">
                                                <form:errors path="username" cssClass="error help-inline" />
                                            </div>
                                        </div>
                                    </td>
                                </tr>
                                <tr>
                                    <th><label for="password"><fmt:message key="user.password"/></label></th>
                                    <td>
                                        <form:input path="password" type="password" id="password" />
                                        <div class="control-group error">
                                            <div class="controls">
                                                <form:errors path="password" cssClass="error help-inline" />
                                            </div>
                                        </div>
                                    </td>
                                </tr>
                                <tr>
                                    <th><label for="confirm-password"><fmt:message key="user.confirmpassword"/></label></th>
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
                                    <th></th>
                                    <td><input name="commit" type="submit" class="beige-ribbon" value="<fmt:message key="send"/>"/></td>
                                </tr>
                            </table>
                        </fieldset>
                            
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
