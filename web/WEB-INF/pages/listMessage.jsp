<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:forEach items="${messages}" var="message">
    <div>
        <table>
            <tbody>
            <tr>
                <td><c:out value="${message.user.firstName}"/></td>
                <td><c:out value="${message.user.lastName}"/></td>
            </tr>
            <tr>

                <td>
                    <fmt:parseDate value="${message.messageDateTime}" pattern="yyyy-MM-dd'T'HH:mm:ss"
                                   var="parsedDate" type="date"/>
                    <fmt:formatDate value="${parsedDate}" var="formatedDate"
                                    type="date" pattern="dd.MM.yyyy HH:mm:ss"/>
                    <c:out value="${formatedDate}"/>
                </td>
                <td><c:out value="${message.message}"/></td>
            </tr>
            </tbody>
        </table>
    </div>
</c:forEach>


