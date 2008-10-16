<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="display" uri="http://displaytag.sf.net" %>
<%--@elvariable id="entities" type="java.util.List<org.mvnsearch.snippet.domain.Category>"--%>
<h1>category List</h1>
<hr/>
<display:table id="category" name="entities">
    <display:column property="id" title="ID" href="edit.action" paramId="id"/>
    <display:column property="name" title="ID" href="show.action" paramProperty="id" paramId="id"/>
    <display:column title="Icon">
        <img src="/statics/images/category/${category.icon}"/>
    </display:column>
    <display:column property="parentId" title="Parent"/>
</display:table>
<hr/>
<a href="editNew.action">create</a>