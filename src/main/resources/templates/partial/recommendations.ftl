<#assign practice=result.getPracticeRecommendations().getRecommendations()/>
<#assign defaultRec="Warmup">
<div class="row">
	<div class="col-xs-12">
		<h2>
            <small><i class="fa fa-magic"></i></small>
            Practice Recommendations
        </h2>
	</div>
</div>

<div id="practice_tab" >	
    <div class="row">
        <div class="col-xs-12">
            <ul  class="nav nav-pills">
                <#list practice as p>
                    <li <#if p.name = defaultRec> class="active" </#if> >
                        <a href="#${p.name?replace(' ', '_')?replace('&', '_')}" data-toggle="tab"  >${p.name}</a>
                    </li>
                </#list>
            </ul>
        </div>
    </div>
    <div class="tab-content clearfix">
        <#list practice as recommendation>
            <div class="tab-pane <#if recommendation.name = defaultRec> active </#if>" id="${recommendation.name?replace(' ', '_')?replace('&','_')}">
                <br>
                <div class="row">
                    <h5 class="col-xs-1">  
                        <a data-toggle='tooltip' title='An url for sharing and keeping track of solved problems for this recommendation list' href="/recommendations/${recommendation.id}" target="_blank"> 
                            <i class="fa fa-link" aria-hidden="true"></i> Track
                        </a>
                    </h5>
                    <h5 class="col-xs-10 col-xs-push-1"> ${recommendation.description} </h5>
                </div>
                <#include "/common/recommendation.ftl">
            </div>
        </#list>
    </div>
</div>
