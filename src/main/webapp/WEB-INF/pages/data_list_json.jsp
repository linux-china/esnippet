<%@page contentType="text/html" %>
<%@ taglib prefix="json" uri="http://www.atg.com/taglibs/json" %>
<%--@elvariable id="dataSet" type="java.util.Set<Map.Entry<Integer, String>>"--%>
<json:array var="entry" items="${dataSet}">
    <json:object>
        <json:property name="id" value="${entry.key}"/>
        <json:property name="value" value="${entry.value}"/>
    </json:object>
</json:array>