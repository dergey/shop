<#-- @ftlvariable name="frontendContext" type="by.sergey.zhuravlev.shop.model.MailFrontendContext" -->
<#-- @ftlvariable name="root" type="by.sergey.zhuravlev.shop.domain.Confirmation" -->

<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Email confirmation</title>
</head>
<body>
<div>Welcome to Social Application</div>
<div>Your code is <b>${root.manualCode}</b></div>
<div>You can also activate your account by following <a href="${frontendContext.getFrontendRegistrationConfirmationLink(root.manualCode)}"><b>this link</b></a></div>
</body>
</html>