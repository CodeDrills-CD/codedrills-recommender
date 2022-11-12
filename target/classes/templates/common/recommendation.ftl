<div class="row">
    <div class="col-xs-12">
        <table class="table table-striped">
            <tbody>
                <#list recommendation.practiceProblems as pr>
                    <tr>
                        <td>
                            <h4><a  class='practice-${pr.status?lower_case}' href='${pr.problem.url}' target='_blank' class='problem'>${pr.problem.name}</a></h4>
                        </td>
                        <td class='problem-attributes'>
                            <img data-toggle='tooltip' title='${pr.problem.difficulty?lower_case?cap_first}' src='/images/difficulty/${pr.problem.difficulty?lower_case}.png'/>
                            <img data-toggle='tooltip' title='${pr.problem.site?lower_case?cap_first}' src='/images/icons/${pr.problem.site?lower_case}.png'/>
                        </td>
                    </tr>
                </#list>
            </tbody>
        </table>
    </div>
</div>

