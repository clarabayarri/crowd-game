<!DOCTYPE html>
<%@ include file="/WEB-INF/jsp/include.jsp" %>

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>Mapuche</title>
    <link rel="stylesheet" href="/resources/css/reset.css" />
    <link rel="stylesheet" href="/resources/css/960.css" />
    <link rel="stylesheet" href="/resources/css/style.css" type="text/css" media="all">

    <link href="/resources/css/bootstrap.min.css" rel="stylesheet">
    <script src="http://code.jquery.com/jquery-1.7.1.js"></script>
    <script type="text/javascript" src="/resources/js/bootstrap.min.js"></script>

</head>

<body class="container_12">
    <div class="side-ribbon left-ribbon">
        <a href="/game"><fmt:message key="play"/></a>
    </div>
    <div class="title-container grid_5 prefix_2">
        <a href="/home">
            <img src="/resources/img/mapuche.png" alt="Mapuche" />
        </a>
    </div>

    <div id="delete-confirm" class="modal hide fade">
        <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
            <h3><fmt:message key="delete.confirm.title"/></h3>
        </div>
        <div class="modal-body">
            <p><fmt:message key="delete.confirm.body"/></p>
        </div>
        <div class="modal-footer">
            <button data-dismiss="modal" aria-hidden="true" class="btn"><fmt:message key="cancel"/></button>
            <a href="/deleteUser" class="btn btn-danger" id="delete-send"><fmt:message key="delete"/></a>
        </div>
    </div>

    <div class="central">
        <div class="central-container">
            <div class="central-container2">
                <section class="contents-area">
                    
                    <div class="clear"></div>

                    <div class="center-box">
                        <table>
                            <tr>
                                <th><label for="username"><fmt:message key="user.username"/>: </label></th>
                                <td>${user.username}</td>
                            </tr>
                            <tr>
                                <th><label for="email"><fmt:message key="user.email"/>: </label></th>
                                <td>${user.email}</td>
                            </tr>
                            <tr>
                                <th><label for="dyslexic"><fmt:message key="user.dyslexic"/>: </label></th>
                                <td>
                                    <c:choose>
                                        <c:when test="${user.dyslexic}"><fmt:message key="yes"/></c:when>
                                        <c:otherwise><fmt:message key="no"/></c:otherwise>
                                    </c:choose>
                                </td>
                            </tr>
                            <tr>
                                <th><label for="age"><fmt:message key="user.age"/>:</label></th>
                                <td>${user.age}</td>
                            </tr>
                            <tr>
                                <th><label for="spanishSpeaker"><fmt:message key="user.spanishspeaker"/>: </label></th>
                                <td>
                                    <c:choose>
                                        <c:when test="${user.spanishSpeaker}"><fmt:message key="yes"/></c:when>
                                        <c:otherwise><fmt:message key="no"/></c:otherwise>
                                    </c:choose>
                                </td>
                            </tr>
                        </table>                        

                        <a href="#delete-confirm" data-toggle="modal" role="button" class="right"><fmt:message key="user.delete"/></a>
                        <div class="clear"></div>
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

</body>
</html>
