<%@page contentType="text/plain;charset=utf-8" %>
<%@ taglib prefix="json" uri="http://www.atg.com/taglibs/json" %>
<%--@elvariable id="snippets" type="java.util.List<org.mvnsearch.snippet.domain.Snippet>"--%>
<json:array var="snippet" items="${snippets}">
    <json:object>
        <json:property name="id" value="${snippet.id}"/>
        <json:property name="icon" value="${snippet.icon}"/>
        <json:property name="name" value="${snippet.name}"/>
        <json:property name="mnemonic" value="${snippet.mnemonic}"/>
        <json:property name="author" value="${snippet.author}"/>
        <json:property name="languageText" value="${snippet.languageText}"/>
    </json:object>
</json:array>