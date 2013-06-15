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

    <script src="http://ajax.googleapis.com/ajax/libs/jquery/2.0.0/jquery.min.js"></script>

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
        <a href="/user">${user.username}</a>
    </div>
    <div class="side-ribbon right-ribbon">
        <div id="score">${user.score} puntos</div>
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
                    
                    <div id="game-container">
                        <canvas id="game-canvas" width="800" height="500"><fmt:message key="canvas.html5"/></canvas>
                    </div>

                    <div class="side-ribbon right-red-ribbon push-out-ribbon">
                        <a href="/static/j_spring_security_logout"><fmt:message key="logout"/></a>
                    </div>

                    <div class="clear"></div>
                </section>
                
            </div>
        </div>
    </div>
    <div class="fb-like" data-href="http://mapuche.clarabayarri.com" data-send="false" data-layout="button_count" data-width="450" data-show-faces="true" data-font="arial"></div>
    <%@ include file="/WEB-INF/jsp/footer.jsp" %>
            
    <%@ include file="/WEB-INF/js/gameJS.jsp" %>

</body>
</html>
