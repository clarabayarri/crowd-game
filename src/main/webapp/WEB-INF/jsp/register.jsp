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
    <link href="/resources/css/bootstrap.min.css" rel="stylesheet">

    <script>
      (function(i,s,o,g,r,a,m){i['GoogleAnalyticsObject']=r;i[r]=i[r]||function(){
      (i[r].q=i[r].q||[]).push(arguments)},i[r].l=1*new Date();a=s.createElement(o),
      m=s.getElementsByTagName(o)[0];a.async=1;a.src=g;m.parentNode.insertBefore(a,m)
      })(window,document,'script','//www.google-analytics.com/analytics.js','ga');

      ga('create', 'UA-41125384-1', 'clarabayarri.com');
      ga('send', 'pageview');

    </script>
</head>

<body class="container_12">
    <div id="fb-root"></div>
<script>(function(d, s, id) {
  var js, fjs = d.getElementsByTagName(s)[0];
  if (d.getElementById(id)) return;
  js = d.createElement(s); js.id = id;
  js.src = "http://connect.facebook.net/en_US/all.js#xfbml=1&appId=522557827811561";
  fjs.parentNode.insertBefore(js, fjs);
}(document, 'script', 'facebook-jssdk'));</script>


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
                        <span class="left" id="register-title-text"><fmt:message key="register.title"/></span>
                        <div class="green-ribbon-bent"></div>
                    </div>

                    <div class="clear"></div>

                    <form:form method="post" action="/register" modelAttribute="registration" class="center-box">
                        <c:set var="errors"><form:errors path="*"/></c:set>
                        <c:if test="${not empty errors}">
                            <div class="alert alert-error">
                                <h4><fmt:message key="warning"/></h4>
                                <p><spring:message code="registration.error" /></p>
                            </div>
                        </c:if>

                        <fieldset>
                            <table>
                                <tr>
                                    <th><label for="username"><fmt:message key="user.username"/>: </label></th>
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
                                    <th><label for="password"><fmt:message key="user.password"/>: </label></th>
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
                                    <th><label for="confirm-password"><fmt:message key="user.confirmpassword"/>: </label></th>
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
                                    <th><label for="email"><fmt:message key="user.email"/>: </label></th>
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
                                    <th><label for="dyslexic"><fmt:message key="user.dyslexic"/> </label></th>
                                    <td>
                                        <form:checkbox path="dyslexic" id="dyslexic" /> <fmt:message key="yes"/>

                                        <div class="control-group error">
                                            <div class="controls">
                                                <form:errors path="dyslexic" cssClass="error help-inline" />
                                            </div>
                                        </div>
                                    </td>
                                </tr>
                                <tr>
                                    <th><label for="age"><fmt:message key="user.age"/>: </label></th>
                                    <td>
                                        <form:input path="age" id="age" />

                                        <div class="control-group error">
                                            <div class="controls">
                                                <form:errors path="age" cssClass="error help-inline" />
                                            </div>
                                        </div>
                                    </td>
                                </tr>
                                <tr>
                                    <th><label for="spanishSpeaker"><fmt:message key="user.spanishspeaker"/> </label></th>
                                    <td>
                                        <form:checkbox path="spanishSpeaker" id="spanishSpeaker" />  <fmt:message key="yes"/>

                                        <div class="control-group error">
                                            <div class="controls">
                                                <form:errors path="spanishSpeaker" cssClass="error help-inline" />
                                            </div>
                                        </div>
                                    </td>
                                </tr>
                            </table>
                        </fieldset>

                        <p><fmt:message key="policy.accept"/> <a href="/policy" target="_blank"><fmt:message key="policy0"/></a>.</p>

                        <div >
                            <input name="commit" type="submit" class="beige-ribbon right" value="<fmt:message key="register.action"/>"/>
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
