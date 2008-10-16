<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--@elvariable id="category" type="org.mvnsearch.snippet.domain.Category"--%>
<h1>category detail </h1>
<hr/>
<ul>
    <li>ID: ${category.id}</li>
    <li>Name: ${category.name}</li>
    <li>Icon: <img src="/statics/images/category/${category.icon}"/></li>
    <li>Parent ID: ${category.id}</li>
</ul>
<hr/>
<a href="showAll.action">return to list</a>
&nbsp;&nbsp; <a href="edit.action?id=${category.id}">edit</a>