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
    <div class="side-ribbon left-ribbon">
        <a href="/game">jugar</a>
    </div>
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
                        <span class="left" id="register-title-text">Platform data</span>
                        <div class="green-ribbon-bent"></div>
                    </div>

                    <div class="clear"></div>

                    <form:form method="post" action="/admin/save" modelAttribute="platformData" class="register-form">
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
