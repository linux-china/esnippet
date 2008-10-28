<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--@elvariable id="languages" type="java.util.Map<Integer, String>"--%>
<%--@elvariable id="snippet" type="org.mvnsearch.snippet.domain.Snippet"--%>
<h2>Basic Information:</h2>

<ul id="snippetDetailSpecification">
    <li><span class="title">ID:</span> ${snippet.id}</li>
    <li><span class="title">Name:</span> ${snippet.name}</li>
    <li><span class="title">Mnemonic:</span> ${snippet.mnemonic}</li>
    <li><span class="title">Author:</span> ${snippet.author}</li>
    <li><span class="title">Keywords:</span> ${snippet.keywords}</li>
</ul>
<p/>

<h2>Description:</h2>

<div id="code_desc">
    ${snippet.description}
</div>
<p/>

<h2>Code: </h2>
<pre name="code" class="${languages[snippet.language]}">
<c:out value="${snippet.code}" escapeXml="true"/>
</pre>