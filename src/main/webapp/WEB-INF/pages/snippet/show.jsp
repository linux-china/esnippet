<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="joda" uri="http://www.joda.org/joda/time/tags" %>
<%--@elvariable id="snippet" type="org.mvnsearch.snippet.domain.Snippet"--%>
<h1>snippet detail </h1>
<hr/>
<ul>
    <li>ID: ${snippet.id}</li>
    <li>Name: ${snippet.name}</li>
    <li>Mnemonic: ${snippet.mnemonic}</li>
    <li>Language: ${languages[snippet.language]}</li>
	<li>Added Date: <joda:format value="${snippet.createdAt}" pattern="yyyy-MM-dd"/> </li>
</ul>
<hr/>
<a href="index.action">return to list</a>
&nbsp;&nbsp; <a href="edit.action?id=${snippet.id}">edit</a>