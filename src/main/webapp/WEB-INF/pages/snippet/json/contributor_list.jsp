<%@page contentType="text/plain;charset=utf-8" %>
<%@ taglib prefix="json" uri="http://www.atg.com/taglibs/json" %>
<%--@elvariable id="contributors" type="java.util.List<java.util.Map>"--%>
<json:array var="contributor" items="${contributors}">
    <json:object>
        <json:property name="id" value="${contributor['author']}"/>
        <json:property name="author" value="${contributor['author']}"/>
        <json:property name="count" value="${contributor['count']}"/>
    </json:object>
</json:array>