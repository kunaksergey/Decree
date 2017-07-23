<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<spring:message code = "label.login" var = "labelLogin"/>
<spring:message code = "label.signIn" var = "labelSignIn"/>
<spring:message code = "message.loginOrPassIsWrong" var = "loginOrPassIsWrong"/>
<spring:message code = "label.regestration" var = "regestration"/>
<div>
    <c:if test="${not empty sessionScope.SPRING_SECURITY_LAST_EXCEPTION}">
        <font size="2" color="red"><b>${loginOrPassIsWrong}</b></font>
    </c:if>
</div>
<div>
    <sec:authorize access="isAnonymous()">
        <form action="/login" method="post">
            <h2>${labelSignIn}</h2>
            <input type="text" name="username" placeholder="User"/>
            <input type="password" name="password" placeholder="Password"/>
            <button type="submit">${labelLogin}</button>
        </form>
        <a href="/register.xhtml">${regestration}</a>
     </sec:authorize>
</div>
