<#ftl output_format="HTML" auto_esc=true>
<#setting url_escaping_charset="UTF-8">
<#assign title="code-drills - Competitive programming resources and problem recommender">
<!DOCTYPE html>
<html lang="en">
<head>

	<meta charset="UTF-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge" />
	<meta name="viewport" content="width=device-width, initial-scale=1">

	<meta property="og:url"           content="https://code-drills.com/" />
	<meta property="og:type"          content="website" />
	<meta property="og:title"         content="${title}" />
	<meta property="og:description"   content="Competitive programming resources, lectures, problem recommender, faq" />
	<meta property="og:image"         content="https://code-drills.com/images/code-drills.png" />


	<link rel="icon" href="favicon.ico?v=2" />
	<title>${title}</title>
	<link href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
    <link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet">
	<link href="/css/common.css?v=2" rel="stylesheet">
    <script src="https://code.jquery.com/jquery-3.1.1.slim.min.js" integrity="sha256-/SIrNqv8h6QGKDuNoLGA4iret+kyesCkHGzVUUV0shc=" crossorigin="anonymous"></script>

</head>

<body>
<div id="fb-root"></div>
<script>
    (function(d, s, id) {
        var js, fjs = d.getElementsByTagName(s)[0];
        if (d.getElementById(id)) return;
        js = d.createElement(s); js.id = id;
        js.src = "//connect.facebook.net/en_GB/sdk.js#xfbml=1&version=v2.8&appId=1238981726198127";
        fjs.parentNode.insertBefore(js, fjs);
    }(document, 'script', 'facebook-jssdk'));
</script>

<#include "/common/navbar.ftl">

<div class="container">
