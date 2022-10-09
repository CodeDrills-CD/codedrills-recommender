<#assign pageType="resources">
<#include "/common/head.ftl">

<div class="row">
    <div class="col-xs-12">
        <h1 class="bfscp_title">bfs(competitive_programming)</h1>
    </div>
</div>

<div class="row">
    <div class="col-xs-12">
        <span class='bfscp_title'> bfs(competitive_programming) </span> is a series of lectures related to competitive programming. We plan to start with the very basics and slowly climb up to advanced topics. Each module page contains references and problems list for practice. If you have any comments/suggestions or find any typo/bug in the slides/pages please mail <a href="mailto:codedrills@gmail.com"> codedrills@gmail.com </a>.
    </div>
</div>

<#list levels as level>
    <div class="row">
        <div class="col-xs-4">
            <h2 class="level${level.val}-title"> Level ${level.val} </h2>
        </div>
    </div>
    <div class="row">
    <#list level.modules as module>
        <div class="col-xs-12 col-sm-6 col-md-4">
            <div class="panel level level${level.val}">
                <div class="panel-heading">
                    <a href="/bfscp/modules/${module.id}" target="_blank">
                        <strong> ${module.title} </strong>
                    </a>
                </div>
                <div class="panel-footer">
                    <#if module.completed!false>
                    <#else>
                        <img data-toggle="tooltip" title="In progress; not yet completed" src="/images/inprogress.svg" class="progress-img" alt="In progress">
                    </#if>
                    ${module.lectures?size} lectures
                </div>
            </div>
        </div>
    </#list>
    </div>
</#list>

<#include "/common/tail.ftl">
