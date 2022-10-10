<#assign pageType="resources">
<#include "/common/head.ftl">

<div class = "row">
    <div class = "col-xs-12">
        <h1>
            <#if module.completed!false>
            <#else>
                <img data-toggle="tooltip" title="In progress; not yet completed" src="/images/inprogress.svg" class="progress-img" alt="In progress">
            </#if>
            ${module.title}
        </h1>
    </div>
</div>

<div class="row">
    <div class="col-xs-12">
        <h3 class="level${module.level.val}-title"><small>Level ${module.level.val}</small></h3>
    </div>
</div>


<div class = "row">
    <div class = "col-xs-12">
        <#if module.description?has_content>
            ${module.description}
        <#else>
            This module is a work in progress
        </#if>
    </div>
</div>

<#if module.prereqs?has_content>
<div class = "row">
    <div class = "col-xs-12">
        <h2>
            <small><i class="fa fa-tasks"></i></small>
            Prerequisites
        </h2>
    </div>
</div>

<div class = "row">
    <div class = "col-xs-12">
        <table class="refs table table-striped table-condensed">
            <tbody>
                <#list module.prereqs as pre>
                    <#if pre.link?has_content>
                        <tr><td><a href="${pre.link}" target="_blank">${pre.title}</a></td></tr>
                    <#else>
                        <tr><td>${pre.title}</td></tr>
                    </#if>
                </#list>
            </tbody>
        </table>
    </div>
</div>
</#if>

<div class = "row">
    <div class = "col-xs-12">
        <h2>
            <small><i class="fa fa-tv"></i></small>
            Lectures
        </h2>
    </div>
</div>

<#if module.lectures?has_content>
<div class = "row">
    <div class = "col-xs-12">
        <#list module.lectures as lec>
            <#assign id=lec.id>
            <div class="panel panel-default">
                <div class="panel-heading">
                    <h4 class="panel-title">
                        <a class="accordion-toggle collapsed" data-toggle="collapse" data-parent="#accordion" href="#${id}">
                            ${lec.name}
                        </a>
                    </h4>
                </div>
                <div id="${id}" class="panel-collapse collapse">
                    <div class="panel-body">
                        <#if lec.releaseExpected?has_content>
                            <p><i class="fa fa-calendar-plus-o"></i> Expected release: <b>${lec.releaseExpected}</b></p>
                        </#if>
                        <p>${lec.description}</p>
                        <table class="table">
                            <tbody>
                                <tr>
                                    <td><b>
                                        <i class="fa fa-video-camera"></i>
                                        Link to video
                                    </b></td> 
                                    <td>
                                        <#if lec.url?has_content>
                                            <a href="${lec.url}" target="_blank">${lec.url}</a>
                                        <#else>
                                            In progress
                                        </#if>
                                    </td>
                                </tr>
                                <tr>
                                    <td><b>
                                        <small><i class="fa fa-newspaper-o"></i></small>
                                        Slides
                                    </b></td> 
                                    <td>
                                        <#if lec.slides!false>
                                            <a href="/slides/${id}.pdf" target="_blank">
                                                <small><i class="fa fa-file-pdf-o"></i></small>
                                                pdf
                                            </a>
                                        <#else>
                                            N/A
                                        </#if>
                                    </td>
                                </tr>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </#list>
    </div>
</div>

<#else>
<div class="row">
    <div class="col-xs-12">
        Coming soon...
    </div>
</div>
</#if>

<#if module.references?has_content>

<div class = "row">
    <div class = "col-xs-12">
        <h2>
            <small><i class="fa fa-university"></i></small>
            References
        </h2>
    </div>
</div>

<div class = "row">
    <div class = "col-xs-12">
        <table class="refs table table-striped table-condensed">
            <tbody>
                <#list module.references as ref>
                    <tr><td><a href="${ref.url}" target="_blank">${ref.title}</a></td></tr>
                </#list>
            </tbody>
        </table>
    </div>
</div>

</#if>

<#if module.problems?has_content>
<div class = "row">
    <div class = "col-xs-12">
        <h2>
            <small><i class="fa fa-magic"></i></small>
            Related Problems
        </h2>
    </div>
</div>

<div class = "row">
    <div class = "col-xs-12">
        <table class="refs table table-striped table-condensed">
            <tbody>
                <#list module.problems as problem>
                    <tr><td><a href="${problem}" target="_blank">${problem}</a></td></tr>
                </#list>
            </tbody>
        </table>
    </div>
</div>
</#if>

<#include "/common/tail.ftl">
