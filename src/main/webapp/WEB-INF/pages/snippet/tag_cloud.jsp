<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--@elvariable id="tags" type="java.util.Map<java.lang.String,java.lang.Integer>"--%>
<c:forEach var="entry" items="${tags}">
    <c:if test="${entry.value>10}">
        <span class="tag s10"><a href="javascript:Layout.getListPanel().reloadByWord('${entry.key}');">${entry.key}</a></span>
    </c:if>
    <c:if test="${entry.value<=10}">
        <span class="tag s${entry.value}"> <a href="javascript:Layout.getListPanel().reloadByWord('${entry.key}');">${entry.key}</a></span>
    </c:if>
</c:forEach>