<%@page contentType="text/html" %>
<%@ taglib prefix="json" uri="http://www.atg.com/taglibs/json" %>
<%--@elvariable id="comments" type="java.util.List<org.mvnsearch.snippet.domain.extra.Comment>"--%>
<json:array var="comment" items="${comments}">
    <json:object>
        <json:property name="id" value="${comment.id}"/>
        <json:property name="commentator" value="${comment.commentator}" escapeXml="true"/>
        <json:property name="commentatorEmail" value="${comment.commentatorEmail}" escapeXml=""/>
        <json:property name="subject" value="${comment.subject}" escapeXml="true"/>
        <json:property name="content" value="${comment.content}" escapeXml="true"/>
        <json:property name="createdAt" value="${comment.createdAt}"/>
    </json:object>
</json:array>