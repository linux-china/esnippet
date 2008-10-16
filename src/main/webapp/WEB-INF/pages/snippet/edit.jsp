<%@taglib prefix="s" uri="/struts-tags" %>

<h1>edit snippet</h1>
<hr/>
<s:form namespace="/snippet" action="save" method="post" id="snippetForm">
    <s:hidden name="id"/>
    <s:textfield name="name" label="Name" size="64"/>
    <s:textfield name="mnemonic" label="Mnemonic" size="64"/>
    <s:textfield name="categoryId" label="Category ID" size="64"/>
    <s:select label="Language" name="language"
              list="languages" listKey="key" listValue="value"/>
    <s:select label="Icon" name="icon"
              list="icons" listKey="key" listValue="value"/>
    <s:textfield name="author" label="Author" size="64"/>
    <s:textfield name="keywords" label="Key Words" size="64"/>
    <s:textfield name="url" label="URL" size="64"/>
    <s:textarea name="description" cols="80" rows="3" label="Description"/>
    <s:textarea name="code" cols="80" rows="5" label="Code"/>
    <s:textarea id="example" name="example" cols="80" rows="5" label="Example"/>
    <s:submit/>
</s:form>
<hr/>
<a href="index.action">return to list</a>
<script type="text/javascript" src="/statics/javascript/fckeditor/fckeditor.js"></script>
<script type="text/javascript">
    window.onload = function()
    {
        var oFCKeditor = new FCKeditor('example') ;
        oFCKeditor.BasePath = "/statics/javascript/fckeditor/";
        oFCKeditor.ReplaceTextarea();
    }
</script>
