<#assign pageType="tools">
<#include "/common/head.ftl">

<div class="row">
    <div class="col-xs-12">
        <h2> Competitive programming comparator </h2>
    </div>
</div>

<div class="row">
    <div class="col-xs-12">
        Compare competitive programming profiles between 2 different sets of handles
    </div>
</div>

<br>

<form method="GET" action="/tools/compare">
    <div class="row">
        <div class="col-xs-11">
            <div class="form-group">
                <label for="handlesA">
                    <i class="fa fa-users"></i>
                    (A) Enter handle(s) with site prefix
                </label>
                <input required pattern = "${handlesRegex}" type="text" class="form-control input-lg" id="handlesA" name="handlesA" placeholder="Single handle or space separated handles">
            </div>
        </div>
    </div>

    <div class="row">
        <div class="col-xs-1 col-xs-push-5">
            <br> vs <br> <br>
        </div>
    </div>

    <div class="row">
        <div class="col-xs-11">
            <div class="form-group">
                <label for="handlesB">
                    <i class="fa fa-users"></i>
                    (B) Enter handle(s) with site prefix
                </label>
                <input required pattern = "${handlesRegex}" type="text" class="form-control" id="handlesB" name="handlesB" placeholder="Single handle or space separated handles">
            </div>
        </div>
    </div>
    <br>
    <div class="row">
        <div class="col-xs-2">
            <button data-toggle='tooltip' title='Compare A and B' type="submit" class="btn btn-primary" id="handles-submit">
                <i id="compare-icon"></i>
                Compare
            </button>
        </div>
    </div>
</form>

<div class="row">
    <div class="col-md-10">
        <h3>
            <small><i class="fa fa-terminal"></i></small>
            Input format
        </h3>
    </div>
</div>
<div id="input-format">
    <#include "/partial/input.ftl">
</div>

<script>
    $(document).ready(function() {
        $("#handles-submit").closest("form").on("submit", function(e) {
            var icon = $("#compare-icon");
            icon.addClass("fa fa-spinner fa-spin");
            var button = $("#handles-submit");
            button.prop("disabled", true);
            var html = button.html();
            button.html(html.replace("Compare", "Comparing..."));
        });
    });
</script>



<#include "/common/tail.ftl">
