<!DOCTYPE html>
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
        <sec:authorize access="isAuthenticated()">
            <a href="/game"><fmt:message key="play"/></a>
        </sec:authorize>
        <sec:authorize access="!isAuthenticated()">
            <a href="/register"><fmt:message key="back"/></a>
        </sec:authorize>
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

                    <h1><fmt:message key="policy0"/></h1>

                    <div class="center-box">
                        <h2><fmt:message key="policy1"/></h2>

                        <p><fmt:message key="policy2"/></p>
                        <p><fmt:message key="policy3"/></p>

                        <h2><fmt:message key="policy4"/></h2>

                        <p><fmt:message key="policy5"/></p>
                        <p><fmt:message key="policy6"/></p>
                        <p><fmt:message key="policy7"/></p>

                        <h2><fmt:message key="policy8"/></h2>

                        <p><fmt:message key="policy9"/></p>
                        <p><fmt:message key="policy10"/></p>

                        <h2><fmt:message key="policy11"/></h2>

                        <p><fmt:message key="policy12"/></p>

                        <h2><fmt:message key="policy13"/></h2>

                        <p><fmt:message key="policy14"/></p>
                        <p><fmt:message key="policy15"/></p>

                        <h2><fmt:message key="policy16"/></h2>

                        <p><fmt:message key="policy17"/></p>

                        <h2><fmt:message key="policy18"/></h2>

                        <p><fmt:message key="policy19"/></p>

                        <h2><fmt:message key="policy20"/></h2>

                        <p><fmt:message key="policy21"/></p>
                    </div>

                    <sec:authorize access="isAuthenticated()">
                        <div class="side-ribbon right-red-ribbon push-out-ribbon">
                            <a href="/static/j_spring_security_logout"><fmt:message key="logout"/></a>
                        </div>
                        <div class="clear"></div>
                    </sec:authorize>
                </section>
                
            </div>
        </div>
    </div>
    <%@ include file="/WEB-INF/jsp/footer.jsp" %>

</body>
</html>
