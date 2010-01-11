<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<br>
This is menu
<br>
Hi ${sessionScope.userSession.user.username}

<br>
<a href="/registration.do">Registration</a>
<a href="/myProfile.do">My Profile</a>
<a href="/login.do">Login</a>
<a href="/json.do">Ajax Json return</a>

</br>