<#assign pageType="about">
<#include "/common/head.ftl">
<#macro profile link site>
    <span>
        <a target="_blank" href="${link}" data-toggle='tooltip' title='${site?cap_first} profile'">
            <#nested>
        </a>
    </span>
</#macro>
<h2>About</h2>

<p>We built code-drills primarily for problem recommendations. Whenever we sat down for practicing, we faced a big problem: <em>what</em> problem to solve next? So we decided to build code-drills to recommend practice problems to solve next based on your past submissions.</p>

<h2>Contact Us</h2>
If you have any bug reports/feature requests or just want to praise us 😁,
email us at <a href="mailto:codedrills@gmail.com " target="_top">codedrills@gmail.com</a>. Connect with us at:
<br>
<h3>Balajiganapathi S</h3>
<p>
    <@profile link="https://github.com/Balajiganapathi" site="github">
        <span class="fa-stack fa-lg">
          <i class="fa fa-square-o fa-stack-2x"></i>
          <i class="fa fa-github fa-stack-1x"></i>
        </span>
    </@profile>
    <@profile link="https://www.quora.com/profile/Balajiganapathi-Senthilnathan" site="quora">
        <span class="fa-stack fa-lg">
          <i class="fa fa-square-o fa-stack-2x"></i>
          <i class="fa fa-quora fa-stack-1x"></i>
        </span>
    </@profile>
    <@profile link="https://www.linkedin.com/in/balajiganapathi-senthilnathan-7436562a" site="linkedin">
        <span class="fa-stack fa-lg">
          <i class="fa fa-square-o fa-stack-2x"></i>
          <i class="fa fa-linkedin fa-stack-1x"></i>
        </span>
    </@profile>
    <@profile link="https://www.facebook.com/balajiganapathi" site="facebook">
        <span class="fa-stack fa-lg">
          <i class="fa fa-square-o fa-stack-2x"></i>
          <i class="fa fa-facebook fa-stack-1x"></i>
        </span>
    </@profile>
</p>
<h3>Deepa Panwar</h3>
<p>
    <@profile link="https://github.com/deepapanwar" site="github">
        <span class="fa-stack fa-lg">
          <i class="fa fa-square-o fa-stack-2x"></i>
          <i class="fa fa-github fa-stack-1x"></i>
        </span>
    </@profile>
    <@profile link="https://www.quora.com/profile/Deepa-Panwar" site="quora">
        <span class="fa-stack fa-lg">
          <i class="fa fa-square-o fa-stack-2x"></i>
          <i class="fa fa-quora fa-stack-1x"></i>
        </span>
    </@profile>
    <@profile link="https://www.linkedin.com/in/deepa-panwar-46008121" site="linkedin">
        <span class="fa-stack fa-lg">
          <i class="fa fa-square-o fa-stack-2x"></i>
          <i class="fa fa-linkedin fa-stack-1x"></i>
        </span>
    </@profile>
    <@profile link="https://www.facebook.com/deepa.panwar.397" site="facebook">
        <span class="fa-stack fa-lg">
          <i class="fa fa-square-o fa-stack-2x"></i>
          <i class="fa fa-facebook fa-stack-1x"></i>
        </span>
    </@profile>
</p>


<#include "/common/tail.ftl">
