<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html>
<head>
    <jsp:include page="/WEB-INF/jsp/decorator/_header.jsp"/>
    <script type="text/javascript" src="/script/jquery.validate.min.js"></script>
    <style type="text/css">
        * {
            font-family: Verdana;
            font-size: 96%;
        }

        label {
            width: 10em;
            float: left;
        }

        label.error {
            float: none;
            color: red;
            padding-left: .5em;
            vertical-align: top;
        }

        p {
            clear: both;
        }

        .submit {
            margin-left: 12em;
        }

        em {
            font-weight: bold;
            padding-right: 1em;
            vertical-align: top;
        }
    </style>
    <script>
        $(document).ready(function() {
            $("#registrationForm").validate();
        });
        function validateConfirmPassword() {
            if ($("#pwd").val() == $("#pwc").val()) {
                setDOB();
                return true;
            } else {
                alert("Please enter confirm password again!");
                return false;
            }
        }
    </script>
</head>
<body>
<jsp:include page="/WEB-INF/jsp/decorator/_top.jsp"/>

<h1>Registration</h1>
<spring:bind path="registrationForm.*">
    <c:forEach var="error" items="${status.errorMessages}">
        <B><FONT color=RED>
            <BR><c:out value="${error}"/>
        </FONT></B>
    </c:forEach>
</spring:bind>
<form name="registrationForm" id="registrationForm" action="/registration.do" method="post"
      onSubmit="return validateConfirmPassword()">

    <label>Choose a username:</label>
    <div>
        <spring:bind path="registrationForm.username">
            <input type="text" name="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}"/>"
                   size="25" class="required" minlength="3" maxlength="25"/>
        </spring:bind>
    </div>
    <br>
    <label>Password: </label>
    <div>
        <spring:bind path="registrationForm.password">
            <input type="password" name="<c:out value="${status.expression}"/>" id="pwd" value="" size="16"
                   class="required" minlength="6" maxlength="16"/>
        </spring:bind>
    </div>

    <br>
    <label>Confirm Password: </label>
    <div>
        <input type="password" name="pwc" id="pwc" value="" size="16" class="required" minlength="6" maxlength="16"/>
    </div>
    <br>
    <label>age: </label>
    <div>
        <spring:bind path="registrationForm.age">
            <input type="text" name="<c:out value="${status.expression}"/>" id="pwd" value="" size="16"
                   class="required" minlength="1" maxlength="3"/>
        </spring:bind>
    </div>
    <br>

    <div class="terms">
        <div>
            <input class="checkbox" type="checkbox" name="termsagree" id="termsagree"/>I have read and agree
        </div>
    </div>
    <div class="button"><input id="submit" type="submit" value="SUBMIT"/></div>
</form>

<jsp:include page="/WEB-INF/jsp/decorator/_footer.jsp"/>
</body>
</html>