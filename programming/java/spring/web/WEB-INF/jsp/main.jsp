<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html>
<head>
    <jsp:include page="/WEB-INF/jsp/decorator/_header.jsp"/>
</head>
<body>
<jsp:include page="/WEB-INF/jsp/decorator/_top.jsp"/>
<jsp:include page="/WEB-INF/jsp/decorator/_menu.jsp"/>

<h1>Welcome</h1>
    <c:if test="${users != null}">
        <c:forEach var="user" items="${users}">
            <c:out value="${user}"/> <br>
        </c:forEach>
    </c:if>

<jsp:include page="/WEB-INF/jsp/decorator/_footer.jsp"/>
</body>
</html>