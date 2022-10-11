<#assign pageType="contests">
<#include "/common/head.ftl">

<#macro timeUrl time str msg>
    <a href="https://www.timeanddate.com/worldclock/fixedtime.html?day=${time.getDayOfMonth()}&month=${time.getMonthValue()}&year=${time.getYear()?c}&hour=${time.getHour()}&min=${time.getMinute()}&sec=0&msg=${msg?url}" target="_blank">
        ${str}
    </a>
</#macro>

<#if timezone?has_content>
    <div class="row">
        <div class="col-xs-12">
            <br>
            All times are in <i>${timezone}</i> time zone. <a href="/contests?utc=1"> Switch to UTC </a>.
        </div>
    </div>
</#if>

<#list contests as type,clists>
    <div class="row">
        <div class="col-md-12">
            <h3>${type?cap_first}</h3>
        </div>
    </div>
    <#if type == "live">
        <#assign startText="Started">
        <#assign panelType="panel-success">
    <#else>
        <#assign startText="Starts">
        <#assign panelType="panel-info">
    </#if>
    <#list clists?chunk(3) as crow>
        <div class="row">
            <#list crow as contest>
                <div class="col-sm-4 col-xs-10 col-xs-push-1 col-md-push-0 col-sm-push-0">
                    <div class="panel ${panelType}">
                        <div class="panel-heading">
                            <a href="${contest.url}" target="_blank">
                                <strong> ${contest.title}</strong>
                            </a>
                        </div>
                        <div class="panel-body banner">
                            <div class="thumbnail site-img">
                                <a href="${contest.url}" target="_blank">
                                    <img src="/images/banners/${contest.site}.png" alt="${contest.site}"/>
                                </a>
                            </div>
                        </div>
                        <div class="panel-footer">
                            <table class="table table-condensed table-borderless">
                                <tr>
                                    <td>
                                        ${startText}
                                    </td>
                                    <td>
                                        <@timeUrl time=contest.getStartTimeUTC() str=contest.getStartTimeString() msg=contest.title>
                                        </@timeUrl>
                                    </td>
                                </tr>
                                <tr>
                                    <td>
                                        Ends
                                    </td>
                                    <td>
                                        <@timeUrl time=contest.getEndTimeUTC() str=contest.getEndTimeString() msg=contest.title>
                                        </@timeUrl>
                                    </td>
                                </tr>
                            </table>
                        </div>
                    </div>
                </div>
            </#list>
        </div>
        <br/>
    </#list>
    <br>
</#list>
<#include "/common/tail.ftl">
