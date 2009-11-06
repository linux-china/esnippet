<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib prefix="joda" uri="http://www.joda.org/joda/time/tags" %>
<%--@elvariable id="languages" type="java.util.Map<Integer, String>"--%>
<%--@elvariable id="snippet" type="org.mvnsearch.snippet.domain.Snippet"--%>
<h2>Basic Information:</h2>

<ul id="snippetDetailSpecification">
    <li><span class="title">ID:</span> ${snippet.id}</li>
    <li><span class="title">Language:</span> ${languages[snippet.language]}</li>
    <li><span class="title">Name:</span> ${snippet.name}</li>
    <li><span class="title">Mnemonic:</span> ${snippet.mnemonic}</li>
    <li><span class="title">Author:</span> ${snippet.author}</li>
    <li><span class="title">Keywords:</span> ${snippet.keywords}</li>
    <li><span class="title">Added Date:</span> <joda:format value="${snippet.createdAt}" pattern="yyyy-MM-dd"/></li>
</ul>
<p></p>

<h2>Description:</h2>

<div id="code_desc">
    ${snippet.description}
</div>
<p></p>

<h2>Code: </h2>
<pre name="code" class="brush: ${fn:toLowerCase(languages[snippet.language])}">
<c:out value="${snippet.code}" escapeXml="true"/>
</pre>
