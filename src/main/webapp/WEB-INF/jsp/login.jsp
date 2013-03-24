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

            <h2>Sign in</h2>

            <form method="post" action="/static/j_spring_security_check">
                <fieldset>
                    <table>
                        <tr>
                            <th><label for="username_or_email">Username or email:</label></th>
                            <td>
                                <input id="username_or_email" name="j_username" type="text" />
                            </td>
                        </tr>
                        <tr>
                            <th><label for="password">Password:</label></th>
                            <td>
                                <input name="j_password" type="password" id="password" />
                                <small><a href="/account/resend_password">Forgot?</a></small>
                            </td>
                        </tr>
                        <tr>
                            <th><label for="remember_me">Remember me</label></th>
                            <td>
                                <input name="_spring_security_remember_me" type="checkbox" id="remember_me" />
                            </td>
                        </tr>
                        <tr>
                            <th></th>
                            <td><input name="commit" type="submit" class="btn btn-primary" value="Sign In"/></td>
                    </div>
                        </tr>
                    </table>
                </fieldset>
                    
            </form>

            <p>Don't have an account yet? <a href="/register">Sign up now!</a></p>

            <script type="text/javascript">
                document.getElementById('username_or_email').focus();
            </script>
            
        </div>
    </div>
</div>

</body>
</html>
