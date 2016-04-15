<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.text.DecimalFormat" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>JVM memory</title>
</head>
<body>
    <%
        DecimalFormat df = new DecimalFormat("0.00");
        Double total = (Runtime.getRuntime().totalMemory()) / (1024.0 * 1024);
        Double max = (Runtime.getRuntime().maxMemory()) / (1024.0 * 1024);
        Double free = (Runtime.getRuntime().freeMemory()) / (1024.0 * 1024);
    %>
    最大: <%=df.format(max)%>MB<br/>
    可用: <%=df.format(max - total + free)%>MB<br/>
    总数: <%=df.format(total)%>MB<br/>
    空闲: <%=df.format(free)%>MB<br/>
    <script type="text/javascript">
        setInterval("myInterval()",1000);
        function myInterval(){
            location.reload();
        }
    </script>
</body>
</html>