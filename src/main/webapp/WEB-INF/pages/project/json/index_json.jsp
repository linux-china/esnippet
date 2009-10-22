<%@page contentType="text/html" %>
<%@ taglib prefix="json" uri="http://www.atg.com/taglibs/json" %>
<%--@elvariable id="projects" type="java.util.List<org.mvnsearch.snippet.domain.Project>"--%>
<json:array var="project" items="${projects}">
    <json:object>
        <json:property name="id" value="${project.id}"/>
        <json:property name="name" value="${project.name}"/>
        <json:property name="shortName" value="${project.shortName}"/>
    </json:object>
</json:array>