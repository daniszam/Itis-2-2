<#assign form=JspTaglibs["http://www.springframework.org/tags/form"] />
<html>
<head>
    <title>Author Form</title>
</head>
<body>

<@form.form method="post" modelAttribute="user">
    <@form.label id="userName" path="userName">User Name</@form.label>
    <@form.textarea path="userName"/>
    <@form.errors path="userName" /><br>

    <@form.label id="email" path="email">Email</@form.label>
    <@form.textarea path="email"/>
    <@form.errors path="email" /><br>

    <@form.label id="password" path="password">Password</@form.label>
    <@form.textarea path="password"/>
    <@form.errors path="password" /><br>


<#---->
<#--<select multiple name="book_id">-->
<#--<#list books as book>-->
<#--<option value=${book.id}>${book.name}</option>-->
<#--</#list>-->
<#--</select>-->
    <input type="submit" value="Submit">
</@form.form>


</body>
</html>