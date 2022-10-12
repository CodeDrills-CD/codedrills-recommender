<#macro displayRating handle rating>
    <img data-toggle='tooltip' title='${handle.site?lower_case?cap_first}' class='site-icon' src='/images/icons/${handle.site?lower_case}.png'/>
    <a href='${handle.getProfileUrl()}' target='_blank'>
        ${handle.handle}
    </a>
    <b>${rating.current!"NA"}</b>
    <small>(Highest ${rating.highest!"NA"})</small>
</#macro>

<#macro refs name="References">
    <table class="refs table table-striped table-condensed">
        <thead>
            <tr><th>
                <small><i class="fa fa-university"></i></small>
                ${name}
            </th></tr>
        </thead>
        <tbody>
            <#nested>
        </tbody>
    </table>
</#macro>

<#macro ref name link>
    <tr><td><a href="${link}" target="_blank">${name}</a></td></tr>
</#macro>

<#macro code gist file>
    <div class="code">
        <script src="${gist}?file=${file}" ></script>
    </div>
</#macro>

<#macro info>
    <div class="alert alert-info">
        <i class="glyphicon glyphicon-info-sign"></i>
        <#nested>
    </div>
</#macro>

<#macro warning>
    <div class="alert alert-warning">
        <i class="glyphicon glyphicon-warning-sign"></i>
        <#nested>
    </div>
</#macro>

<#macro remind text>
    <br>
    <i> ${text} </i>
    </br>
</#macro>

