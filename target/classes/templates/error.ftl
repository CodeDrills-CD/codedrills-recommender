<#assign pageType="error">
<#include "/common/head.ftl">
<div class="row">
    <div class="col-md-12">
        <h2 class="alert-danger">${errorText!"Oops! Something went wrong while processing your request."}</h2> 
    </div>
</div>
<div class="row">
    <div class="col-md-12">
        <br>
        <img data-toggle='tooltip' title=':(' height="300px" width="400px" class="img-responsive center-block" src="/images/sorry.jpg" alt="Sorry!"/>
        <br>
    </div>
</div>
<div class="row">
    <div class="col-md-12">
        Mark this as a RTE against us :(
    </div>
</div>
<#include "/common/tail.ftl">
