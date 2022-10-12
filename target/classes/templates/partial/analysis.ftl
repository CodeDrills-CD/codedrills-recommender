<#include "/common/macros.ftl">
<div class="row">
    <h2 class="col-xs-12">Analysis</h2>
</div>

<#assign userStats=result.getUserStats()/>
<#assign total_submitted=userStats.totalSubmissions()/>
<#assign subs=userStats.getVerdictCount()/>

<#assign total_solved=userStats.totalSolved()/>
<#assign tags=userStats.getTagCount()/>

<#assign strong=userStats.getStrong()/>
<#assign weak=userStats.getWeak()/>

<script src="https://code.highcharts.com/highcharts.js"></script>
<script type="text/javascript">
function radialize(color) {
    return {
        radialGradient: {
            cx: 0.5,
            cy: 0.3,
            r: 0.7
        },
        stops: [
            [0, color],
            [1, Highcharts.Color(color).brighten(-0.3).get('rgb')] // darken
        ]
    };
}

Highcharts.setOptions({
    colors: ['#8c696e', '#49394d', '#7c8da6', '#00d6e6', '#ffa640', '#d93636', '#8c233f', '#220033', '#234d8c', '#269973', '#2e7300', '#733d00', '#f20081', '#4d00bf', '#0d2633', '#7fffa1', '#ccc233', '#e55c00', '#c566cc', '#bfbfff', '#00aaff', '#00330e', '#66611a', '#330700', '#ee00ff', '#3967e6', '#165559', '#263328', '#998773', '#ffbfbf']
});
// Radialize the colors
Highcharts.getOptions().colors = Highcharts.map(Highcharts.getOptions().colors, radialize);
</script>

<#if total_submitted != 0>
<script type="text/javascript">
    $(function () {
        Highcharts.chart('subs_chart', {
            credits: {
                enabled: false
            },
            chart: {
                plotBackgroundColor: null,
                plotBorderWidth: null,
                plotShadow: false,
                type: 'pie'
            },
            title: {
                text: 'Verdicts (total: ${total_submitted})'
            },
            tooltip: {
                pointFormat: '<b>{point.y}</b> submissions'
            },
            plotOptions: {
                pie: {
                    allowPointSelect: true,
                    cursor: 'pointer',
                    dataLabels: {
                        enabled: true,
                        format: '<b>{point.name}</b>: {point.y}',
                        style: {
                            color: (Highcharts.theme && Highcharts.theme.contrastTextColor) || 'black'
                        }
                    }
                }
            },
            series: [{
                name: 'Result',
                colorByPoint: true,
                data: [
                    <#list subs as name, count>
                        {
                            name: '${name}',
                            y: ${count?c},
                            <#if name == 'AC'>
                                sliced: true,
                                selected: true,
                                color: radialize('#00ff00'),
                            <#elseif name == 'WA'>
                                color: radialize('#ff0000'),
                            <#elseif name == 'TLE'>
                                color: radialize('#ffff00'),
                            </#if>

                        }
                        <#sep>,
                    </#list>
                ]
            }]
        });
    }); 
    </script>
    <script type="text/javascript">
    $(function () {
        Highcharts.chart('tags_chart', {
            credits: {
                enabled: false
            },
            chart: {
                plotBackgroundColor: null,
                plotBorderWidth: null,
                plotShadow: false,
                type: 'pie'
            },
            title: {
                text: 'Categories (total solved: ${total_solved})'
            },
            tooltip: {
                pointFormat: '<b>{point.y}</b> solved'
            },
            plotOptions: {
                pie: {
                    allowPointSelect: true,
                    cursor: 'pointer',
                    dataLabels: {
                        enabled: true,
                        format: '<b>{point.name}</b>: {point.y}',
                        style: {
                            color: (Highcharts.theme && Highcharts.theme.contrastTextColor) || 'black'
                        }
                    }
                }
            },
            series: [{
                name: 'Result',
                colorByPoint: true,
                data: [
                    <#list tags as name, count>
                        {
                            name: '${name}',
                            y: ${count?c},
                        }
                        <#sep>,
                    </#list>
                ]
            }]
        });
    }); 
    </script>
</#if>

<div class="row">
	<h3 class="col-xs-12">
        <small><i class="fa fa-line-chart"></i></small>
        Ratings
    </h3>
</div>

<br>

<div class="row">
	<#list userStats.ratings as handle, rating>
		<div class="col-xs-12 col-sm-6 col-md-4">
            <@displayRating handle=handle rating=rating></@displayRating>
		</div>
	</#list>
</div>

<hr>
<div class="row">
    <h3 class="col-xs-12">
        <small><i class="fa fa-pie-chart"></i></small>
        Submissions
    </h3>
</div>

<div class="row">
    <div class="col xs-12 col-md-6">
        <div id="subs_chart">
            <#if total_submitted == 0>
                No submissions found
            </#if>
        </div>
    </div>
    <div class="col xs-12 col-md-6">
        <div id="tags_chart">
            <#if total_submitted == 0>
                No submissions found
            </#if>
        </div>
    </div>
</div>

<hr>
<div class="row">
    <h3 class="col-xs-12">
        <small><i class="fa fa-level-up"></i></small>
        <small><i class="fa fa-tags"></i></small>
        Strong Areas
    </h3>
</div>
<ul class="list-inline row">
    <#list strong as s>
        <#if s?index < 5>
            <li class="col-xs-12 col-sm-6 col-md-2 col-md-push-1 text-success"> <b>
                <small><i class="fa fa-tag"></i></small>
                ${s.tag}
            </b> </li>
        </#if>
    <#else>
        <li class="col-xs-12">Need more submissions for this analysis</li>
    </#list>
</ul>
<hr>
<div class="row">
    <h3 class="col-xs-12">
        <small><i class="fa fa-level-down"></i></small>
        <small><i class="fa fa-tags"></i></small>
        Weak Areas
    </h3>
</div>
<ul class="list-inline row">
    <#list weak as w>
        <#if w?index < 5>
            <li class="col-xs-12 col-sm-6 col-md-2 col-md-push-1 text-danger"> <b>
                <small><i class="fa fa-tag"></i></small>
                ${w.tag}
            </b> </li>
        </#if>
    <#else>
        <li class="col-xs-12">Need more submissions for this analysis</li>
    </#list>
</ul>
<hr>
