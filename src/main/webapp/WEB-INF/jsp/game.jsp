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

    <script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.4.2/jquery.js"></script>

</head>

<body class="container_12">
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
                        <canvas id="game-canvas" width="800" height="500" style="background-color: #fff888;"><fmt:message key="canvas.html5"/></canvas>
                    </div>

                    <div class="side-ribbon right-red-ribbon push-out-ribbon">
                        <a href="/static/j_spring_security_logout"><fmt:message key="logout"/></a>
                    </div>

                    <div class="clear"></div>
                </section>
                
            </div>
        </div>
    </div>

    <%@ include file="/WEB-INF/jsp/footer.jsp" %>
            
    <%@ include file="/WEB-INF/js/gameJS.jsp" %>

</body>
</html>
