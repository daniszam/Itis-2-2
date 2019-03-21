<#macro signForm emailInput rememberCheck genderRadio countrySelect>
<#assign form=JspTaglibs["http://www.springframework.org/tags/form"] />
<#if emailInput>
    <@form.label id="email" path="email">${email}</@form.label>
    <@form.textarea path="email"/>
    <@form.errors path="email" /><br>
</#if>

    <@form.label id="password" path="password">${password}</@form.label>
    <@form.textarea path="password"/>
    <@form.errors path="password" /><br>

<#if rememberCheck>
    <div style="position: absolute; bottom: 5%;left: 5%">
        <@form.checkbox path="emailSubscription" />
        <@form.label for="emailSubscription" path="emailSubscription">Remember</@form.label>
        <@form.errors path="emailSubscription" cssClass="error" />
    </div>
</#if>
<div class="logo" style="background: white; position: absolute; left: 70%; right: 3%; top: 80%">
    <button id="submit" type="submit" value="Sign Up" accesskey="enter">
        <img src="/resources/visa.png" style="width: 100%">
    </button>
</div>

<#if genderRadio>
    <@form.radiobutton path="gender" value="${male}"/>
    <@form.label path="gender" for="${male}">Male</@form.label>
    <@form.radiobutton path="gender" value="${female}"/>
    <@form.label path="gender" for="${female}">Female</@form.label>
    <@form.errors path="gender" cssClass="error" />
</#if>

<#if countrySelect>
    <@form.select path="country">
        <#list countryItems?keys as k>
            <@form.option value='${countryItems[k]}'></@form.option>
        </#list>
    </@form.select>
    <@form.errors path="country" cssClass="error" />
</#if>
</#macro>
