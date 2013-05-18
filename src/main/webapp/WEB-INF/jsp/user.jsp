<!DOCTYPE html>
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
    <div class="side-ribbon left-ribbon">
        <a href="/game">jugar</a>
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

                    <div class="center-box">
                        <table>
                            <tr>
                                <th><label for="username">Username:</label></th>
                                <td>${user.username}</td>
                            </tr>
                            <tr>
                                <th><label for="email">Email:</label></th>
                                <td>${user.email}</td>
                            </tr>
                            <tr>
                                <th><label for="dyslexic">Dyslexic:</label></th>
                                <td>
                                    <c:choose>
                                        <c:when test="${user.dyslexic}">yes</c:when>
                                        <c:otherwise>no</c:otherwise>
                                    </c:choose>
                                </td>
                            </tr>
                            <tr>
                                <th><label for="age">Age:</label></th>
                                <td>${user.age}</td>
                            </tr>
                            <tr>
                                <th><label for="spanishSpeaker">Spanish speaker:</label></th>
                                <td>
                                    <c:choose>
                                        <c:when test="${user.spanishSpeaker}">yes</c:when>
                                        <c:otherwise>no</c:otherwise>
                                    </c:choose>
                                </td>
                            </tr>
                        </table>

                        <div class="clear"></div>
                    </div>

                    <div class="side-ribbon right-red-ribbon push-out-ribbon">
                        <a href="/static/j_spring_security_logout">salir</a>
                    </div>

                    <div class="clear"></div>
                </section>
                
            </div>
        </div>
    </div>
    <%@ include file="/WEB-INF/jsp/footer.jsp" %>

</body>
</html>
