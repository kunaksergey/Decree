<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<spring:message code = "label.welcome" var = "welcome"/>
<spring:message code = "label.logout" var = "logout"/>
<spring:message code = "label.editProfile" var = "editProfile"/>

<div>
    <div id="userinfo">
        <sec:authorize access="isAuthenticated()">${welcome}
         <sec:authentication property="principal.username"/>
         <a href="/editUser.xhtml">${editProfile}</a>
         <br/>
            <a href="<c:url value="/logout" />">${logout}</a>

        </sec:authorize>
    </div>
</div>