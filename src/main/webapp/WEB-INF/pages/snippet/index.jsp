<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="display" uri="http://displaytag.sf.net" %>
<%--@elvariable id="entities" type="java.util.List<org.mvnsearch.snippet.domain.null>"--%>
<h1>snippet List</h1>
<hr/>
<display:table name="entities">
    <display:column property="id" title="ID" href="edit.action" paramId="id"/>
    <display:column property="name" title="Name" href="show.action" paramId="id" paramProperty="id"/>
    <display:column property="mnemonic" title="Mnemonic"/>
</display:table>
<hr/>
<a href="editNew.action">create</a>