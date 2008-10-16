<%@page contentType="text/html" %>
<%@ taglib prefix="json" uri="http://www.atg.com/taglibs/json" %>
<%--@elvariable id="entities" type="java.util.List<org.mvnsearch.snippet.domain.Category>"--%>
<json:array var="category1" items="${entities}">
    <json:object>
        <json:property name="id" value="${category1.id}"/>
        <json:property name="text" value="${category1.name}"/>
        <json:property name="leaf" value="${empty category1.childrenCategories}"/>
        <json:property name="icon" value="/statics/images/category/${category1.icon}"/>
        <json:array var="category2" name="children" items="${category1.childrenCategories}">
            <json:object>
                <json:property name="id" value="${category2.id}"/>
                <json:property name="text" value="${category2.name}"/>
                <json:property name="leaf" value="${empty category2.childrenCategories}"/>
                <json:property name="icon" value="/statics/images/category/${category2.icon}"/>
                <json:array var="category3" name="children" items="${category2.childrenCategories}">
                    <json:object>
                        <json:property name="id" value="${category3.id}"/>
                        <json:property name="text" value="${category3.name}"/>
                        <json:property name="leaf" value="${true}"/>
                        <json:property name="icon" value="/statics/images/category/${category3.icon}"/>
                    </json:object>
                </json:array>
            </json:object>
        </json:array>
    </json:object>
</json:array>