<#assign pageType="fast_cin_cout_cpp">
<#include "/common/head.ftl">
<#include "/common/tip_macros.ftl">

<@tip_title>
Fast I/O optimization for <code>cin</code>/<code>cout</code> in C++
</@tip_title>

<@tip_content>
Some problems have a huge amount of input and output data. Here are some tips for optimizing I/O through <code>cin</code>/<code>cout</code>. Without these optimizations <code>cin</code> and <code>cout</code> maybe too slow.

<@info>
All code snippets provided assume that you have included the required headers and are <code>using namespace std;</code>
</@info>

<h3> set sync with stdio to false </h3>
C++ allows both C and C++ styled I/O in order to remain backward compatible. So each I/O operation performed on standard C++ streams (such as <code>cin</code>/<code>cout</code>/<code>cerr</code>) are also performed on the standard C streams. We can save a lot of time by not requiring this synchronization. We can do that by:

<@code gist="https://gist.github.com/code-drills/9e8af843266e58a2d935b6499471d287.js" file="sync_with_stdio.cpp"></@code>

<@warning>
When you set synchronization to false you cannot intermix C++ styled I/O and C styled I/O (<code>scanf</code>/<code>printf</code> etc.)
</@warning>

<h3> untie <code>cin</code> and <code>cout</code> </h3>
Usually when you output someting to <code>cout</code> it is not output immediately to stdandard output but stored in a buffer. It has to be <i>flushed</i> for the content to be actually output. Flushing usually happens when the buffer is full or the program is being terminated. To facilitate interaction with the user via <code>cin</code>/<code>cout</code> whenever <code>cin</code> is used <code>cout</code> is flushed. This is to help with user prompt. Consider the following snippet:

<@code gist="https://gist.github.com/code-drills/9e8af843266e58a2d935b6499471d287.js" file="tied.cpp"></@code>

Since <code>cin</code> and <code>cout</code> are tied, the <code>cout</code>'s buffer is flushed before <code>cin</code> executes allowing the user to see the prompt. However in coding contests we don't need this. So we can untie <code>cout</code> from <code>cin</code> by doing this:

<@code gist="https://gist.github.com/code-drills/9e8af843266e58a2d935b6499471d287.js" file="untied.cpp"></@code>

<h3>Avoid using <code>endl</code> </h3>
We usually use <code>endl</code> for ending a line of output in <code>cout</code>. But it also has the side effect of flushing <code>cout</code>. So avoid using <code>endl</code> and use '\n' instead to take full advantage of bufferring. This is especially useful if you have to output a large number of lines.

<h3>Caveats</h3>
Before using these optimizations do try to understand the what and why of each optimization. Otherwise they may cause strange, hard-to-debug bugs. Note that with above optimization you will have to manually flush <code>cout</code> for interactive problems. You can do that using:

<@code gist="https://gist.github.com/code-drills/9e8af843266e58a2d935b6499471d287.js" file="flush.cpp"></@code>

<h3>Where to put those statements</h3>
Those statements should be at start of the program. You can put them in main as the first few lines or if you don't like cluterring main you can use this trick:

<@code gist="https://gist.github.com/code-drills/9e8af843266e58a2d935b6499471d287.js" file="fastio_class.cpp"></@code>

<h3>More optimizations</h3>
There are ofcourse more I/O optimizations that can be performed. But these should be enough for the vast majority of problems. See the references for links to articles on more advanced optimizations.

<@refs>
<@ref name="sync_with_stdio" link="http://en.cppreference.com/w/cpp/io/ios_base/sync_with_stdio"></@ref>
<@ref name="endl" link="http://www.cplusplus.com/reference/ostream/endl/"></@ref>
<@ref name="tie" link="http://www.cplusplus.com/reference/ios/ios/tie/"></@ref>
<@ref name="C++ I/O benchmark" link="http://codeforces.com/blog/entry/5217"></@ref>
<@ref name="Fast I/O using getchar" link="http://abhisharlives.blogspot.in/2012/06/really-fast-io-methods-for-programming.html"></@ref>
<@ref name="Discussion on advanced fast I/O" link="http://discuss.spoj.com/t/fast-io-in-c-c/3902/8"></@ref>
<@ref name="Fast I/O using getc" link="https://discuss.codechef.com/questions/11364/fast-io-discussion"></@ref>

</@refs>


</@tip_content>

<#include "/common/tail.ftl">
