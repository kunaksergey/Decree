<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="h" uri="http://java.sun.com/jsf/html" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<spring:message code = "label.message" var = "message"/>
<spring:message code = "label.send" var = "send"/>

<div>
    <div id="inputBox">
        <sec:authorize access="isAuthenticated()">
            <%--<form action="" method="post" >--%>
                <%--<p><b>Введите ваш отзыв:</b></p>--%>
                <%--<p><textarea rows="10" cols="45" name="inputBox"></textarea></p>--%>
                <%--<p><input type="submit" value="Отправить"></p>--%>
                <%--<button type="submit">Войти</button>--%>
            <%--</form>--%>
            <form:form modelAttribute="messageModel" action="/message/add" method="post">
                <table>
                    <tr>
                        <td><form:label path="message">${message}</form:label></td>
                        <td><form:textarea path="message" cols="20" rows="5"/></td>
                    </tr>
                    <tr>
                        <td><input type="submit" value="${send}"/></td>
                    </tr>
                </table>
            </form:form>
        </sec:authorize>
    </div>

</div>
