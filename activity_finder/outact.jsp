<%@ page import="org.apache.commons.text.StringEscapeUtils" %>
<tr>
    <td><%=StringEscapeUtils.escapeHtml4(request.getParameter("id"))%></td><td><%=StringEscapeUtils.escapeHtml4(request.getParameter("name"))%></td>
    <td><%= StringEscapeUtils.escapeHtml4(request.getParameter("description"))%></td> <td> <%=StringEscapeUtils.escapeHtml4(request.getParameter("initial"))%></td>
    <td><%= StringEscapeUtils.escapeHtml4(request.getParameter("cost"))%></td><td><%= StringEscapeUtils.escapeHtml4(request.getParameter("pavname"))%></td>
    <td><%= StringEscapeUtils.escapeHtml4(request.getParameter("total"))%></td><td><%= StringEscapeUtils.escapeHtml4(request.getParameter("occupied"))%></td>
</tr>
