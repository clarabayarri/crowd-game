<!doctype html>
<%@ include file="/WEB-INF/jsp/include.jsp" %>

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>Mapuche</title>
    <link rel="stylesheet" href="/resources/css/reset.css" />
    <link rel="stylesheet" href="/resources/css/960.css" />
    <link rel="stylesheet" href="/resources/css/style.css" type="text/css" media="all">

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
                        <span class="left" id="register-title-text"><fmt:message key="login"/></span>
                        <div class="green-ribbon-bent"></div>
                    </div>

                    <div class="clear"></div>

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
                                        <small><a href="/account/resend_password"><fmt:message key="user.password.forgot"/></a></small>
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

</body>
</html>
