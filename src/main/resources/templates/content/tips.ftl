<#assign pageType="resources">
<#include "/common/head.ftl">
<#include "/common/macros.ftl">

<div class="row">
    <div class="col-xs-12">
        <h1> Tips and tricks </h1>
    </div>
</div>

<div class="row">
    <div class="col-xs-12">
        <p>These articles explain the small tricks and tips that are useful for competitive programmers</p>
    </div>
</div>

<@refs name="Tips and tricks">
    <@ref link="/resources/tips/include_all_libraries_cpp" name="Including all libraries in C++"></@ref>
    <@ref link="/resources/tips/fast_cin_cout_cpp" name="Fast I/O optimization for <code>cin</code>/<code>cout</code> in C++"></@ref>
</@refs>

<#include "/common/tail.ftl">
