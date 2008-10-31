<%@page contentType="text/html" %>
<%@ taglib prefix="json" uri="http://www.atg.com/taglibs/json" %>
<%--@elvariable id="entities" type="java.util.List<org.mvnsearch.snippet.domain.Category>"--%>
<json:array var="category" items="${entities}">
    <json:object>
        <json:property name="id" value="${category.id}"/>
        <json:property name="name" value="${category.name}"/>
        <json:property name="icon" value="${category.icon}"/>
    </json:object>
</json:array>