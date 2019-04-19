<#assign form=JspTaglibs["http://www.springframework.org/tags/form"] />
<html>
<head>
    <title>Author Form</title>
</head>
<body>

<@form.form method="post" modelAttribute="product">
    <@form.label id="name" path="name">Name</@form.label>
    <@form.textarea path="name"/>
    <@form.errors path="name" /><br>

    <@form.label id="price" path="price">Price</@form.label>
    <@form.textarea path="price"/>
    <@form.errors path="price" /><br>


    <@form.checkbox path="sale"/>

    <@form.label id="manufacturer" path="manufacturer">Manufacturer</@form.label>
    <@form.textarea path="manufacturer"/>
    <@form.errors path="primanufacturerce"/><br>

    <select name="types">
        <#list types as type>
            <option value=${type}>${type}</option>
        </#list>
    </select>

    <input type="submit" value="Submit">
</@form.form>


</body>
</html>