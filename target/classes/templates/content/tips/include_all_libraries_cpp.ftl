<#assign pageType="include_all_libraries_cpp">
<#include "/common/head.ftl">
<#include "/common/tip_macros.ftl">

<@tip_title>
Including all libraries in C++
</@tip_title>

<@warning>
This tip only applies for gcc and mingw compiler
</@warning>


<@tip_content>
Suppose you have a lot of includes in your code:

<@code gist="https://gist.github.com/code-drills/02aa2637040d84081ba4478172cd3282.js" file="lots_of_includes.cpp"></@code>

If you use gcc compiler (or minGW) you can replace all of above with a single line:

<@code gist="https://gist.github.com/code-drills/02aa2637040d84081ba4478172cd3282.js" file="single_include.cpp"></@code>

This header has almost all of the usual libraries. This is especially useful if you don't have template with you or you want to shortern your template. Keep in mind that this is not a standard header file and hence not available in all C++ compilers.

<@refs>
<@ref name="GCC's stdc++.h header file" link="https://github.com/gcc-mirror/gcc/blob/master/libstdc++-v3/include/precompiled/stdc++.h"> </@ref>
</@refs>

</@tip_content>

<#include "/common/tail.ftl">
