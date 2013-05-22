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
                    <div class="center-area">
                        <h1><fmt:message key="home.welcome"/></h1>

                        <br />

                        <p><fmt:message key="home.text1"/></p>

                        <img src="/resources/img/screenshot.png" alt="Game screenshot"/>

                        <p><fmt:message key="home.text2"/></p>
                        
                        <div class="green-ribbon">
                            <a href="/game"><fmt:message key="enter"/></a>
                        </div>
                    </div>
                    
                    
                </section>
            </div>
        </div>
    </div>
    <%@ include file="/WEB-INF/jsp/footer.jsp" %>

</body>
</html>
