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

    <script src="http://code.jquery.com/jquery-1.7.1.js"></script>
    <link href="/resources/css/bootstrap.min.css" rel="stylesheet">
    <script type="text/javascript" src="/resources/js/bootstrap.min.js"></script>

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

    <div class="title-container grid_4 prefix_4 suffix_4">
        <a href="/home">
            <img src="/resources/img/mapuche.png" alt="Mapuche" />
        </a>
    </div>

    <div id="forgot" class="modal hide fade">
        <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
            <h3><fmt:message key="login.forgot.title"/></h3>
        </div>
        <form action="/forgot" id="forgot-form" method="post">
            <div class="modal-body">
                <table>
                    <tr>
                        <th><label for="forgot-username"><fmt:message key="login.usernameoremail"/> </label></th>
                        <td><input id="forgot-username" name="username" type="text" /></td>
                    </tr>
                </table>
            </div>
            <div class="modal-footer">
                <button data-dismiss="modal" aria-hidden="true" class="btn"><fmt:message key="cancel"/></button>
                <button data-dismiss="modal" data-toggle="alert" class="btn btn-primary" id="forgot-send"><fmt:message key="send"/></button>
            </div>
        </form>
    </div>

    <div class="central">
        <div class="central-container">
            <div class="central-container2">
                <section class="contents-area">
                    
                    <div id="register-title">
                        <span class="left" id="register-title-text"><fmt:message key="login"/></span>
                        <div class="green-ribbon-bent"></div>
                    </div>

                    <div class="clear"></div>

                    <div class="alert alert-success fade in hide" id="forgot-confirm" data-alert="alert">
                        <button type="button" class="close" data-dismiss="alert">&times;</button>
                        <strong><fmt:message key="login.forgot.success"/></strong> <fmt:message key="login.forgot.successmessage"/>
                    </div>

                    <c:if test="${error}">
                        <p><strong><fmt:message key="login.error"/></strong> <fmt:message key="login.error.message"/></p>
                    </c:if>
            
                    <form method="post" class="center-box" action="/static/j_spring_security_check">
                        <fieldset>
                            <table>
                                <tr>
                                    <th><label for="username_or_email"><fmt:message key="user.username"/> </label></th>
                                    <td>
                                        <input id="username_or_email" name="j_username" type="text" />
                                    </td>
                                </tr>
                                <tr>
                                    <th><label for="password"><fmt:message key="user.password"/> </label></th>
                                    <td>
                                        <input name="j_password" type="password" id="password" />
                                        <small><a href="#forgot" data-toggle="modal"><fmt:message key="user.password.forgot"/></a></small>
                                    </td>
                                </tr>
                                <tr>
                                    <th><label for="remember_me"><fmt:message key="user.rememberme"/></label></th>
                                    <td>
                                        <input name="_spring_security_remember_me" type="checkbox" id="remember_me" />
                                    </td>
                                </tr>
                            </table>
                        </fieldset>
                        <div >
                            <input name="commit" type="submit" class="beige-ribbon right" value="<fmt:message key="login"/>"/>
                        </div>
                        
                        <div class="clear"></div>

                        <p><fmt:message key="register.text"/> <a href="/register"><fmt:message key="register.link"/></a></p>
                    </form>
                </section>
            </div>
        </div>
    </div>

    <%@ include file="/WEB-INF/jsp/footer.jsp" %>
            
    <script type="text/javascript">
        document.getElementById('username_or_email').focus();
    </script>
    <script type="text/javascript">
        document.getElementById('username_or_email').focus();
        $("#forgot-send").click(function() {
            $.post("/forgot", $("#forgot-form").serialize());
            $("#forgot-confirm").removeClass("hide");
        });
        $(".alert").alert();
    </script>
</body>
</html>
