<#ftl output_format="HTML" auto_esc=true>
<#setting url_escaping_charset="UTF-8">
<#assign title="code-drills - Competitive programming resources and problem recommender">
<!DOCTYPE html>
<html lang="en">
<head>
	<script async src="//pagead2.googlesyndication.com/pagead/js/adsbygoogle.js"></script>
	<script>
		(adsbygoogle = window.adsbygoogle || []).push({
			google_ad_client: "ca-pub-4051566829091536",
			enable_page_level_ads: true
		});
	</script>
	<meta charset="UTF-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge" />
	<meta name="viewport" content="width=device-width, initial-scale=1">

	<meta property="og:url"           content="https://code-drills.com/" />
	<meta property="og:type"          content="website" />
	<meta property="og:title"         content="${title}" />
	<meta property="og:description"   content="Competitive programming resources, lectures, problem recommender, faq" />
	<meta property="og:image"         content="https://code-drills.com/images/code-drills.png" />

	<meta name="google-site-verification" content="0qse_VZJZMoYVDbtROS5LId7nqmM2NtE3aBt6L7Ymb0" />

	<link rel="icon" href="favicon.ico?v=2" />
	<title>${title}</title>
	<link href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
    <link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet">
	<link href="/css/common.css?v=2" rel="stylesheet">
    <script src="https://code.jquery.com/jquery-3.1.1.slim.min.js" integrity="sha256-/SIrNqv8h6QGKDuNoLGA4iret+kyesCkHGzVUUV0shc=" crossorigin="anonymous"></script>
    <#if isProd!true>
        <script>
              (function(i,s,o,g,r,a,m){i['GoogleAnalyticsObject']=r;i[r]=i[r]||function(){
              (i[r].q=i[r].q||[]).push(arguments)},i[r].l=1*new Date();a=s.createElement(o),
              m=s.getElementsByTagName(o)[0];a.async=1;a.src=g;m.parentNode.insertBefore(a,m)
              })(window,document,'script','https://www.google-analytics.com/analytics.js','ga');

              ga('create', 'UA-88808977-1', 'auto');
              ga('send', 'pageview');
        </script>
    </#if>
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
