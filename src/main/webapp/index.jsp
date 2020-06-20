<%@page contentType="text/html; utf-8" language="java"%>
<html>
<body>
<h2>Hello ${username}!</h2>
<%-- 表单默认提交是get
        如果写了post requestMapping也要改 --%>
<form method="post" action="in.do">
    <input name="userName"/><br/>
    <input name="password"/><br/>
    <input type="submit"/>
</form>

</body>
</html>
