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
    <div class="side-ribbon left-ribbon">
        <a href="/game"><fmt:message key="play"/></a>
    </div>
    <div class="side-ribbon right-ribbon">
        <a href="/user">user</a>
    </div>
    <div class="title-container grid_5 prefix_2">
        <a href="/home">
            <img src="/resources/img/mapuche.png" alt="Mapuche" />
        </a>
    </div>
    
    <div class="central">
        <div class="central-container">
            <div class="central-container2">
                <section class="contents-area">

                    <div class="clear"></div>

                    <form:form method="post" action="/admin/save" modelAttribute="platformData" class="center-box">
                        <fieldset>
                            <table>
                                <tr>
                                    <th><label for="username">Project ID:</label></th>
                                    <td>
                                        <form:input id="username" path="projectId" />
                                    </td>
                                </tr>
                                <tr>
                                    <th><label for="password">UID:</label></th>
                                    <td>
                                        <form:input path="UID" id="password" />
                                    </td>
                                </tr>
                            </table>
                        </fieldset>
                        <div >
                            <input name="commit" type="submit" class="beige-ribbon right" value="Save"/>
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
