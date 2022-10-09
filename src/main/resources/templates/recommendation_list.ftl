<#assign pageType="tools">
<#include "/common/head.ftl">

<div class="row">
    <div class="col-xs-12">
        <h2> ${recommendation.name} </h2>
    </div>
</div>

<div class="row">
    <div class="col-xs-11 col-xs-push-1">
        <h3> ${recommendation.description} </h3>
    </div>
</div>


<div class="row">
    <div class="col-xs-11 col-xs-push-1">
        <h4> Tracking for ${recommendation.handles} </h4>
    </div>
</div>


<div class="row">
    <div class="col-xs-11 col-xs-push-1">
        <h5> Use this url to keep track of your recommendation list. You can also share it with your team for team practice. It may take upto 10 minutes for a solved problem to be updated here. </h5>
    </div>
</div>


<br>

<#include "/common/recommendation.ftl">

<#include "/common/tail.ftl">
