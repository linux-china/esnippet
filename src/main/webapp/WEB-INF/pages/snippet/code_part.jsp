<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--@elvariable id="languages" type="java.util.Map<Integer, String>"--%>
<%--@elvariable id="snippet" type="org.mvnsearch.snippet.domain.Snippet"--%>
<ul>Basic Information:
    <li>ID: ${snippet.id}</li>
    <li>Name: ${snippet.name}</li>
    <li>Mnemonic: ${snippet.mnemonic}</li>
    <li>Author: ${snippet.author}</li>
    <li>Keywords: ${snippet.keywords}</li>
</ul>
<br/><br/>

<div id="code_desc">
    ${snippet.description}
</div>
<br/>
<pre name="code" class="${languages[snippet.language]}:nocontrols">
<c:out value="${snippet.code}" escapeXml="true"/>
</pre>