<%@taglib prefix="s" uri="/struts-tags" %>

<h1>edit category</h1>
<hr/>
<s:form namespace="/category" action="save" method="post" id="categoryForm">
    <s:hidden name="id"/>
    <s:textfield name="name" label="Name"/>
    <s:textfield name="icon" label="Icon"/>
    <s:textfield name="parentId" label="Parrent ID"/>
    <s:submit name="Save"/>
</s:form>
<hr/>
<a href="showAll.action">return to list</a>