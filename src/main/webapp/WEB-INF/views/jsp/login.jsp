<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>Login Page</title>
  </head><body onload='document.f.username.focus();'>
<h3>Login with Username and Password</h3>
    <c:if test="${not empty error}">
      <div class="error">${error}</div>
    </c:if>
    <c:if test="${not empty msg}">
      <div class="msg">${msg}</div>
    </c:if>
<form name='f' action='/client/login' method='GET'>
    <table>
      <tr><td>Логин:</td><td><input type='text' name='username' value=''></td></tr>
      <tr><td>Пароль:</td><td><input type='password' name='password'/></td></tr>
      <tr><td colspan='2'><input name="submit" type="submit" value="Login"/></td></tr>
      <input name="_csrf" type="hidden" value="a7f3f541-f210-4a52-bc6f-482cac42c17c" />
    </table>
</form>
</body>
</html>
