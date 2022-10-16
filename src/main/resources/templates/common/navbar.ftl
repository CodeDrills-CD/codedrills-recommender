<#macro isactive type>
    <#if type == pageType>
        <#assign active="active"/>
    <#else>
        <#assign active=""/>
    </#if>
</#macro>
<nav class="navbar navbar-default">
    <div class="container-fluid">
        <!-- Brand and toggle get grouped for better mobile display -->
        <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar-content">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="/">
                <span> <img alt="Logo" src="/images/code-drills.png" height="15" width="15"/></span>
                code-drills
            </a>
        </div>

        <!-- Collect the nav links, forms, and other content for toggling -->
        <div class="collapse navbar-collapse" id="navbar-content">
            <ul class="nav navbar-nav">
                <@isactive type="tools"/>
                <li class="${active} dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">
                        <i class="fa fa-wrench"></i>
                        Tools <span class="caret"> </span>
                    </a>
                    <ul class="dropdown-menu">
                        <li><a href="/recommender">Recommender</a></li>
                        <li><a href="/tools/comparator">Comparator</a></li>
                    </ul>
                </li>
                <@isactive type="resources"/>
                <li class="${active} dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">
                        <i class="fa fa-book"></i>
                        Resources <span class="caret"> </span>
                    </a>
                    <ul class="dropdown-menu">
                        <li><a href="/resources/tips">Tips and Tricks</a></li>

                    </ul>
                </li>
                <@isactive type="faq"/>
                <li class="${active}"><a href="/faq"> 
                    <i class="fa fa-question"></i>
                    FAQ 
                </a></li>
            </ul>

            <ul class="nav navbar-nav navbar-right">
                <li>
                    <a class="navbar-brand" href="https://github.com/CodeDrills-CD/codedrills-recommender" target="_blank">
                        <span> <img alt="github-link" src="/images/github-logo.png" height="25" width="25"/></span>
                    </a>
                </li>
                <li> <div class="fb-like" data-href="https://code-drills.com" data-layout="button_count" data-action="recommend" data-size="large" data-show-faces="false" data-share="true"></div></li>
            </ul>
        </div><!-- /.navbar-collapse -->
    </div><!-- /.container-fluid -->
</nav>
