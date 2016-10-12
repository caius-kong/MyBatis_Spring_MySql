<%--
  Created by IntelliJ IDEA.
  User: kongyunhui
  Date: 16/9/18
  Time: 下午12:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>userInfo</title>
</head>
<body>
<div>
    <c:if test="${user!=null}">
        EL(jstl1.0以后支持): ${user.name} <br/>
        JSTL: <c:out value="${user.name}"/>
    </c:if>
</div>

<div>
    <c:if test="${uploadSuccess==true}">
        文件上传成功!
    </c:if>
</div>

<table>
    <c:if test="${users!=null}">
        <c:forEach var="user"  items="${users}">
            <tr>
                <td>${user.name}</td>
                <c:if test="${user.emails!=null}">
                    <c:forEach var="email" items="${user.emails}">
                        <td>${email.emailName}</td>
                    </c:forEach>
                </c:if>
            </tr>
        </c:forEach>
    </c:if>
</table>

<table>
    <c:if test="${emails!=null}">
        <c:forEach var="email"  items="${emails}">
            <tr>
                <td>${email.emailName}</td>
                <c:if test="${email.user!=null}">
                    <td>${email.user.name}</td>
                </c:if>
            </tr>
        </c:forEach>
    </c:if>
</table>

<form action="/user/deleteUsers.do" method="post">
<table>
    <c:if test="${users!=null}">
        <c:forEach var="user" items="${users}">
            <tr>
                <td><input type="checkbox" value="${user.id}" name="user_id"></td>
                <td>${user.name}</td>
            </tr>
        </c:forEach>
        <tr><td colspan="2"><input type="submit" value="批量删除"></td></tr>
    </c:if>
</table>
</form>

<form action="/user/deleteUsers1.do" method="post">
    <table>
        <c:if test="${users!=null}">
            <c:forEach var="user" items="${users}" varStatus="status">
                <tr>
                    <td><input type="checkbox" value="${user.id}" name="ids[${status.index}]"></td>
                    <td>${user.name}</td>
                </tr>
            </c:forEach>
            <tr><td colspan="2"><input type="submit" value="批量删除"></td></tr>
        </c:if>
    </table>
</form>

<form action="/user/deleteUsers2.do" method="post">
    <table>
        <c:if test="${users!=null}">
            <c:forEach var="user" items="${users}">
                <tr>
                    <td><input type="checkbox" value="${user.id}" name="user.id"></td>
                    <td>${user.name}</td>
                </tr>
            </c:forEach>
            <tr><td colspan="2"><input type="submit" value="批量删除"></td></tr>
        </c:if>
    </table>
</form>
</body>
</html>
