<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--@elvariable id="tags" type="java.util.Map<java.lang.String,java.lang.Integer>"--%>
<c:forEach var="entry" items="${tags}">
    <c:if test="${entry.value>=5}">
        <span class="tag s5"><a href="javascript:Layout.getListPanel().reloadByWord('${entry.key}');">${entry.key}</a></span>
    </c:if>
    <c:if test="${entry.value<5}">
        <span class="tag ${entry.value}"> <a href="javascript:Layout.getListPanel().reloadByWord('${entry.key}');">${entry.key}</a></span>
    </c:if>
</c:forEach>