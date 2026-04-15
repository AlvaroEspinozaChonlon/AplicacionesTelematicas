<%@ page import="org.apache.commons.text.StringEscapeUtils" %>

<tr>
    <td><%=StringEscapeUtils.escapeHtml4(request.getParameter("name"))%></td><td><%=StringEscapeUtils.escapeHtml4(request.getParameter("location"))%></td>
</tr>
