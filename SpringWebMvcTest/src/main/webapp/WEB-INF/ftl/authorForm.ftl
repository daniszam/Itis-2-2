<#import "macros/signForm.ftl" as sign>
<#assign form=JspTaglibs["http://www.springframework.org/tags/form"] />
<html>
<head>
    <title>Author Form</title>
</head>
<body>

    <@form.form method="post" modelAttribute="author">
        <@form.label id="name" path="name">Name</@form.label>
        <@form.textarea path="name"/>
        <@form.errors path="name" /><br>

        <@form.label id="avatarUrl" path="avatarUrl">AvatarUrl</@form.label>
        <@form.textarea path="avatarUrl"/>
        <@form.errors path="avatarUrl" /><br>



        <select multiple name="book_id">
            <#list books as book>
                <option value=${book.id}>${book.name}</option>
            </#list>
        </select>
        <input type="submit" value="Submit">
    </@form.form>


</body>
</html>