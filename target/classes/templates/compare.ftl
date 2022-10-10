<#assign pageType="tools">
<#include "/common/head.ftl">
<#include "/common/macros.ftl">
<#assign ha="${result.handlesA?html}">
<#assign hb="${result.handlesB?html}">

<div class="row">
    <div class="col-xs-12">
        <h1> Comparision Result </h1>
    </div>
</div>

<div class="row">
    <div class="col-xs-12 col-md-push-1">
        <h2> A = ${ha} </h2>
        Vs
        <h2> B = ${hb} </h2>
    </div>
</div>

<#if result.areSame()>
    <div class="row">
        <div class="col-xs-12">
            <h4>Both handles are same</h4>
        </div>
    </div>
    <div class="row">
        <div class="col-xs-12">
            <small>The only person you should try to be better than is who you were yesterday</small>
        </div>
    </div>
<#else>

    <div class="row">
        <div class="col-xs-6">
            <h3 data-toggle='tooltip' title='All ratings for ${ha}' data-placement='left'> 
                <small><i class="fa fa-line-chart"></i></small>
                A's ratings 
            </h3>
            <table>
                <#list result.ratingsA as handle, rating>
                    <tr><td>
                        <@displayRating handle=handle rating=rating></@displayRating>
                    </td></tr>
                </#list>
            </table>
        </div>

        <div class="col-xs-6">
            <h3 data-toggle='tooltip' title='All ratings for ${hb}' data-placement='left'> 
                <small><i class="fa fa-line-chart"></i></small>
                B's ratings 
            </h3>
            <table>
                <#list result.ratingsB as handle, rating>
                    <tr><td>
                        <@displayRating handle=handle rating=rating></@displayRating>
                    </td></tr>
                </#list>
            </table>
        </div>

    </div>

    <hr/>

    <div class="row">
        <div class="col-xs-6">
            <h3 data-toggle='tooltip' title='Based on strong area analysis of ${ha}' data-placement='left'> 
                <small><i class="fa fa-tags"></i></small>
                A is stronger than B in 
            </h3>
            <#list result.strongerA as t>
                <b class="strong-tag">
                <small><i class="fa fa-tag"></i></small>
                    ${t}
                </b>
                <#sep> | </#sep>
            <#else>
                None
            </#list>
        </div>
        <div class="col-xs-6">
            <h3 data-toggle='tooltip' title='Based on strong area analysis of ${hb}' data-placement='left'> 
                <small><i class="fa fa-tags"></i></small>
                B is stronger than A in 
            </h3>
            <#list result.strongerB as t>
                <b class="weak-tag">
                    <small><i class="fa fa-tag"></i></small>
                    ${t}
                </b>
                <#sep> | </#sep>
            <#else>
                None
            </#list>
        </div>
    </div>

    <hr/>

    <div class="row">
        <div class="col-xs-6">
            <h3 data-toggle='tooltip' title='Problems solved by ${ha} but not by ${hb}' data-placement='left'> 
                <small><i class="fa fa-list"></i></small>
                Problems only solved by A 
            </h3>
            <#list result.onlySolvedByA as p>
                <a href='${p.url}' target='_blank'>${p.name}</a>
                <#sep> | </#sep>
            <#else>
                None
            </#list>
        </div>
        <div class="col-xs-6">
            <h3 data-toggle='tooltip' title='Problems solved by ${hb} but not by ${ha}' data-placement='left'> 
                <small><i class="fa fa-list"></i></small>
                Problems only solved by B 
            </h3>
            <#list result.onlySolvedByB as p>
                <a href='${p.url}' target='_blank'>${p.name}</a>
                <#sep> | </#sep>
            <#else>
                None
            </#list>
        </div>
    </div>
</#if>

<#include "/common/tail.ftl">
