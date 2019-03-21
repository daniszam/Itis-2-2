<#import "/spring.ftl" as spring/>
<#import "macros/signForm.ftl" as sign>
<html>
<head>
    <title>Sign In</title>

    <link href="/resources/css/signCard.css" rel="stylesheet">
    <link href="/resources/css/bankassistant.css" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css?family=Comfortaa" rel="stylesheet">
</head>
<body>
<a class="title-name" style="text-decoration: none; position: absolute; z-index: 1; left:1%;" href="/home"><h2>YOUR
        FINANCE ASSISTANT</h2></a>
<div class="signUp">
    <div class="signUp card" style="width: 860px; height: 540px; border-radius: 60px">
        <div class="background"></div>
        <div class="title-name" style="position: absolute; left: 5%;top: 5%">
            <h2>YOUR FINANCE ASSISTANT</h2>
        </div>
        <div class="logo" style="position: absolute; left: 70%; right: 3%; top: 5%">
            <img src="/resources/payway.png" style="width: 100%">
        </div>

        <form id="singUp" name="user" style="width: 100%; height: 100%">
            <#--<@sign.signForm data=false email=true remember=true></@sign.signForm>-->
            User Name: <@spring.formInput "user.userName" "" "text"></@spring.formInput>
        </form>

    </div>
</div>
<div class="auth link">
    <form style="width: 100%" action="">
        <button type="submit">
            <input type="hidden" name="vkauth" id="vkauth" value="vkauth">
            <img src="/resources/vk-logo.png" style="width: 100%">
        </button>
    </form>
</div>
<div id="header">
      <h2>FreeMarker Spring MVC Hello World</h2>
</div>

</body>
</html>