<%@page contentType="text/html;charset=ISO-8859-1" %>
<%@ taglib prefix="json" uri="http://www.atg.com/taglibs/json" %>
<%--@elvariable id="snippet" type="org.mvnsearch.snippet.domain.Snippet"--%>
<json:object>
   <json:property name="success">true</json:property>
   <json:object name="data">
    <json:property name="id" value="${snippet.id}"/>
    <json:property name="categoryId" value="${snippet.categoryId}"/>
    <json:property name="name" value="${snippet.name}"/>
    <json:property name="mnemonic" value="${snippet.mnemonic}"/>
    <json:property name="language" value="${snippet.language}"/>
    <json:property name="icon" value="${snippet.icon}"/>
    <json:property name="author" value="${snippet.author}"/>
    <json:property name="keywords" value="${snippet.keywords}"/>
    <json:property name="url" value="${snippet.url}"/>
    <json:property name="type" value="${snippet.type}"/>
    <json:property name="description" value="${snippet.description}" escapeXml="false"/>
    <json:property name="code" value="${snippet.code}" escapeXml="false"/>
    <json:property name="example" value="${snippet.example}" escapeXml="false"/>
</json:object>
</json:object>
