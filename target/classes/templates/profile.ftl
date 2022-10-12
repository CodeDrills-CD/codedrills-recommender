<#assign pageType="tools">
<#include "/common/head.ftl">

<div class="row">
    <div class="col-xs-12">
        <h2>
            ${result.handles?html}
        </h2>
    </div>
</div>
<#include "/partial/analysis.ftl">
<#include "/partial/recommendations.ftl">

<#include "/common/tail.ftl">
