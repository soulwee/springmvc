<%@page  isELIgnored="false" contentType="text/html; utf-8" language="java"%>
<html>
<body>
<h2>Hello ${username}!</h2>
<h2>Hello <%= request.getAttribute("username")%>!</h2>
<%-- 表单默认提交是get
        如果写了post requestMapping也要改 --%>
<form method="post" action="in.do">
    <input name="userName"/><br/>
    <input name="password"/><br/>
    <input type="submit" value="in.do"/>
</form>
<form  action="index.do">
    <input name="username"/><br/>
    <input name="password"/><br/>
    <input type="submit" value="index.do"/>
</form>
<a href="autoemp">autoemp</a>
</body>
</html>
