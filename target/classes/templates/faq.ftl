<#assign pageType="faq">
<#include "/common/head.ftl">
<#include "/common/macros.ftl">

<#macro faq>
<div class="panel panel-default">
    <#nested>
</div>
</#macro>

<#macro faqq id>
    <div class="panel-heading">
        <h4 class="panel-title">
            <a class="accordion-toggle collapsed" data-toggle="collapse" data-parent="#accordion" href="#${id}">
                <#nested>
            </a>
        </h4>
    </div>
</#macro>

<#macro faqa id>
    <div id="${id}" class="panel-collapse collapse">
        <div class="panel-body">
            <#nested>
        </div>
    </div>
</#macro>

<div class="panel-group" id="accordion">
    <div class="faqHeader">Competitive Programming</div>
    <@faq>
        <@faqq id="cp1">
                What is competitive programming?
        </@faqq>
        <@faqa id="cp1">
                <p> Wikipedia defines competitive programming best: </p> <br>
                <blockquote>
                    <p>
                    Competitive programming is a mind sport usually held over the Internet or a local network, involving participants trying to program according to provided specifications. Contestants are referred to as sport programmers. Competitive programming is recognized and supported by several multinational software and Internet companies, such as Google, and Facebook. There are several organizations who host programming competitions on a regular basis.
                    </p>
                    <p>
A programming competition generally involves the host presenting a set of logical or mathematical practiceProblems to the contestants (who can vary in number from tens to several thousands), and contestants are required to write computer programs capable of solving each problem. Judging is based mostly upon number of practiceProblems solved and time spent for writing successful solutions, but may also include other factors (quality of output produced, execution time, program size, etc.)
                    </p>
                    <footer> <a target="_blank" href="https://en.wikipedia.org/wiki/Competitive_programming"> Wikipedia </a> </footer>
        
                </blockquote>
        </@faqa>
    </@faq>

    <@faq>
        <@faqq id="cp2">
                How to get started with competitive programming?
        </@faqq>
        <@faqa id="cp2">
                <p>
                    The best way is to just dive in and start coding. Pick a programming language you are comfortable with and go to any one of the various competitive programming websites and start solving the easier practiceProblems. If you have limited experience in programming in general too, aim to solve hundreds of simple practiceProblems. This will acquaint you with the basic patterns of coding.
                </p>
                <p>
                    Initially focus on learning the language - its syntax and library. That can be done by first solving practiceProblems and then looking at others' solution to the problem. While looking at others' code, attention should be paid to how they use the language features and library to write concise code.
                </p>
                <@refs name="More Info">
                    <@ref name="Codechef Getting Started page" link="https://www.codechef.com/getting-started"></@ref>
                    <@ref name="Spoj simple practiceProblems" link="http://www.spoj.com/practiceProblems/basics/"></@ref>
                </@refs>
        </@faqa>
    </@faq>
    <div class="panel panel-default">
        <@faqq id="cp3">
                Which programming language is the best one for competitive programming?
        </@faqq>
        <@faqa id="cp3">
                <p>
                    Any programming language that you are already familiar with is the best. Most sites support a huge range of languages. However if you are preparing for ICPC, C++ or Java are the best ones as they are the allowed languages at ICPC. Note that most sites usually have a time multiplier for Java as it is usually slower than C++.
                </p>
                <p>
                    Between C++ and Java any is fine. While C++ generally leads to shorter code, that should not be the main factor for choosing it. If you are using Java, make sure to use proper fast I/O so that your code does not take a long time to read the input. If you are still unsure, just try both of them and choose whichever you like better.
                </p>
                <p>
                    Whatever language you choose, first learn its basic syntax. Then focus on getting familiar with its standard library. Knowing the standard library really well is the key to fast and accurate implementation. Knowing that there is a library function for doing a particular task will help avoid wasting time by coding your own implementation.
                <@refs name="Useful C++ Links">
                    <@ref name="Basic C++ tutorial" link="https://www.tutorialspoint.com/cplusplus/"></@ref>
                    <@ref name="C++ Reference" link="http://www.cplusplus.com/reference/"></@ref>
                    <@ref name="C++ tricks" link="http://codeforces.com/blog/entry/15643"></@ref>
                    <@ref name="STL tutorial" link="https://www.topcoder.com/community/data-science/data-science-tutorials/power-up-c-with-the-standard-template-library-part-1/"></@ref>
                    <@ref name="C++ I/O Optimizations" link="http://codeforces.com/blog/entry/5217"></@ref>
                </@refs>
                <@refs name="Useful Java Links">
                    <@ref name="Basic Java tutorial" link="https://www.tutorialspoint.com/java/"></@ref>
                    <@ref name="Java Reference" link="https://docs.oracle.com/javase/7/docs/api/overview-summary.html"></@ref>
                    <@ref name="Java I/O" link="http://codeforces.com/blog/entry/7018"></@ref>
                </@refs>
        </@faqa>
    </div>
    <div class="panel panel-default">
        <@faqq id="cp4">
                What are the best sites to practice in?
        </@faqq>
        <@faqa id="cp4">
                There are too many sites to list all of them here. Instead we will list out the more popular ones. Besides practice practiceProblems, some of these sites hold regular long and short contests. If you want to see the upcoming ones, do visit our contests page.
                <table class="table table-condensed table-striped">
                    <thead>
                        <tr><th>Site</th><th>Comment</th></tr>
                    </thead>
                    <tbody>
                        <tr>
                            <td><a target="_blank" href="https://arena.topcoder.com/">Topcoder</a></td>
                            <td>It is one of the oldest site to host regular short contests. The quality of problem is quite high and consitent. Topcoder conducts a 1 hr 35 min contest called "Single Round Match" (SRM) frequently</td>
                        </tr>
                        <tr>
                            <td><a target="_blank" href="http://codeforces.com/">Codeforces</a></td>
                            <td>Probably the most popular site right now, codeforces has a variety of practiceProblems and regular Div 1 and Div 2 rounds</td>
                        </tr>
                        <tr>
                            <td><a target="_blank" href="https://www.codechef.com/">Codechef</a></td>
                            <td>Codechef holds monthly long contests (with big prize money!). It also holds regular short contest named cookoff. Apart from the 2, there is also a monthly contest named lunchtime, targetted specifically at school and IOI students. It has quality editorials for most practiceProblems</td>
                        </tr>
                        <tr>
                            <td><a target="_blank" href="http://www.spoj.com/">Spoj</a></td>
                            <td>Spoj has a huge collection of practiceProblems on almost every area of competitive programming</td>
                        </tr>
                    </tbody>
                </table>
        </@faqa>
    </div>
  
    <div class="panel panel-default">
        <@faqq id="cp6">
                How do I learn a particular topic?
        </@faqq>
        <@faqa id="cp6">
                <p> First find some good tutorials/articles on that topic. It helps to find multiple articles so that you get different perspectives on the same topic. Try going through them and understanding them. The next step depends on if you are learning some specific algorithm (like, say, DFS/BFS, KMP etc.) or a technique (like Dynamic Programming, Greedy etc.).</p>
                <p>If you are learning a specific algorithm, walk through the pseudo-code/code on an actual example to get the feel of the algorithm. Also, some prefer to  first use it as a black box. Learn where and how it is used and just use it without knowing how exactly it works. Then try implementing the algorithm on your own in your favourite language.</p>
                <p>If you are learning a general technique, after reading the basics, it is best to go through a lot of classical algorithms that uses the technique. For example, if you are learning dynamic programming, go through classic DP algorithms like edit distance, knapsack dp etc. </p>
                <p>Finally whether you are learning an algorithm or technique, practice a <strong> LOT </strong> of practiceProblems on that topic. </p>
        </@faqa>
    </div>
    <div class="panel panel-default">
        <@faqq id="cp7">
                I know the basics, what next?
        </@faqq>
        <@faqa id="cp7">
                <p>So, you know all the basic algorithms, their usage and can probably solve the first 2 practiceProblems of a Div 2 round? That's great. Now improving gets slightly harder than before. First off, we would recommend solving lots of adhoc/implementation problem so that your implementation and general understanding skill is strengthened. This will help you as you learn more advanced algorithms and techniques.</p>
                <p>After that it is just a matter of smart practicing. The trick is to always try practiceProblems which are just beyond your current ability. This will ensure you are always improving. Solving one single good medium problem is way better than solving a ton of easy practiceProblems at this stage.</p>
        </@faqa>
    </div>
    <div class="panel panel-default">
        <@faqq id="cp8">
                What is the single best tip to improve?
        </@faqq>
        <@faqa id="cp8">
                <strong>Upsolving</strong>
                <p>What does that mean? After a contest, try solving the easiest problem that you could <em>not</em> solve. If you are consistently unable to solve a problem of a particular type, then you should learn it in depth.</p>
        </@faqa>
    </div>
    <div class="panel panel-default">
        <@faqq id="cp9">
                What are some famous competitive programming contests?
        </@faqq>
        <@faqa id="cp9">
                Besides the regular contests listed above, there are some annual contests run regularly by various organizations. Here are some of the popular ones:
                <@refs name="Contests">
                    <@ref name="International Olympiad in Informatics (IOI)" link="https://en.wikipedia.org/wiki/International_Olympiad_in_Informatics"></@ref>
                    <@ref name="International Collegiate Programming Contest (ICPC)" link="https://icpc.baylor.edu/"></@ref>
                    <@ref name="Google Code Jam" link="https://code.google.com/codejam/"></@ref>
                    <@ref name="Topcoder Open" link="https://tco17.topcoder.com/"></@ref>
                    <@ref name="Facebook Hackercup" link="https://www.facebook.com/hackercup/"></@ref>
                    <@ref name="Codechef Snackdown" link="https://www.codechef.com/snackdown/"></@ref>
                    <@ref name="Internet Problem Solving Contest" link="https://ipsc.ksp.sk/"></@ref>
                </@refs>
        </@faqa>
    </div>
    <div class="panel panel-default">
        <@faqq id="cp10">
                How to prepare for ICPC?
        </@faqq>
        <@faqa id="cp10">
                <p>ICPC is a team contest, so it is very important to work on it as a team. Of course every team member must know the basics of all topics, so all the individual practice tips given above apply here too. Specifically for ICPC, analyze each of the team member's weak and strong areas. Then make sure that each topic is at least 1 member's strong area. </p>
                <p>Another important thing is to understand the penalty system intuitively. For example, if you solve the first problem just 5 min late, and then go on to solve 8 practiceProblems, then the first problem's penalty actually becomes 40 (5 * 8 - since the penalties are added for each problem). So it is vitally important to solve the easiest problem <em>asap</em>.</p>
                <p>To achieve the above, make sure there is a team plan. Maybe 2 of the team will go through the practiceProblems (one from front and one from back) while the 3rd starts typing the basic code template. Basically try to get the first few practiceProblems quickly. </p>
                <p>Also, practice a lot together so that you become good at solving the harder practiceProblems together.</p>
        </@faqa>
    </div>
    <div class="panel panel-default">
        <@faqq id="cp11">
                How much does competitive programming help in actual software jobs?
        </@faqq>
        <@faqa id="cp11">
                <p>Competitive programming helps a lot in clearing the interview for the said software job. If you are good at competitive programming, then the algorithm round of the interview will be a breeze. Some companies like Google have only algo rounds , so it will be easy to get into those companies if you are good at competitive programming</p>
                <p>Coming back to the question, while you will rarely implement a segment tree or dynamic programming in a software job, the computational thinking skils develped in competitive programming will always help. We have found that debugging and (especially) thinking about corner cases are things that are very useful in both competitive programming and actual software jobs.</p>
        </@faqa>
    </div>


    <div class="faqHeader">code-drills</div>
    <div class="panel panel-default">
        <@faqq id="cd1">
                What is code-drills?
        </@faqq>
        <@faqa id="cd1">
                <p>We built code-drills primarily for problem recommendations. Whenever we sat down for practicing we faced a big problem: <em>what</em> problem to solve next? So we decided to build code-drills to recommend practiceProblems to solve next based on your past submissions.</p>
        </@faqa>
    </div>
    <div class="panel panel-default">
        <@faqq id="cd2">
                Which sites are included in the recommendations?
        </@faqq>
        <@faqa id="cd2">
                Right now we analyze and recommend from 3 sites: Codeforces, Codechef and Spoj.
        </@faqa>
    </div>
    <div class="panel panel-default">
        <@faqq id="cd3">
                How to enter the handles?
        </@faqq>
        <@faqa id="cd3">
                <#include "/partial/input.ftl">
        </@faqa>
    </div>
    <div class="panel panel-default">
        <@faqq id="cd4">
                What are the various recommendations and how to use them?
        </@faqq>
        <@faqa id="cd4">
                <table class="table table-condensed table-striped">
                    <thead>
                        <tr><th>Recommendation</th><th>Comment</th></tr>
                    </thead>
                    <tbody>
                        <tr><td>Warmup</td><td>This just recommends very easy problem and is ideal for solving before start of contest or a long practice session</td></tr>
                        <tr><td>Daily Practice</td><td>It has one easy problem and one medium problem. It is best for individual practice if you don't have the time to do a big practice session. It should ideally just take an hour or so</td></tr>
                        <tr><td>Easy/Medium/Hard</td><td>We especially recommend the medium recommendations. These are the ones which are just beyond your solved practiceProblems level</td></tr>
                        <tr><td>ICPC</td><td>Recommended for a full fledged team practice. Enter all the handles of the entire team for best results</td></tr>
                        <tr><td>Strong/weak areas</td><td>Recommendation in your strong/weak areas. If you are practicing the weak areas, we recommend soving the middle ones as those are the medium ones</td></tr>
                        <tr><td>Topic recommendation</td><td>Just as above, the middle ones in the list are the medium ones</td></tr>
                    </tbody>
                </table>
        </@faqa>
    </div>
    <div class="panel panel-default">
        <@faqq id="cd5">
                What is the best way to use code-drills for individual practice?
        </@faqq>
        <@faqa id="cd5">
                <p>That depends on how you want to practice and how much time you have. If you are in a hurry, the <em>Daily Practice</em> is what will be most useful. It has 1 easy problem and 1 medium problem. If you have enough time, the best one is either the <em>Medium</em> or <em>Weak Area</em> recommendations. </p>
                <p>Of course if you want to practice a particular topic, the recommendation for that topic is the best. Keep in mind that the recommendations for each tag start with easy practiceProblems. So sometimes, you may want to skip the first 3-4 practiceProblems and directly jump to the middle of the list </p>
        </@faqa>
    </div>
    <div class="panel panel-default">
        <@faqq id="cd6">
                What is the best way to use code-drills for team practice?
        </@faqq>
        <@faqa id="cd6">
                <p>For team practice, <em>ICPC</em> recommendations is the best. We would also recommend taking a look at the team's weak areas and focussing on those topics individually or using the <em>Weak Area</em> recommendation</p>
        </@faqa>
    </div>
    <div class="panel panel-default">
        <@faqq id="cd7">
                How is the verdict chart calculated?
        </@faqq>
        <@faqa id="cd7">
                <p>We just aggregate the information from all the handles of sites you have enterred. There are a few things to keep in mind while looking at the verdicts chart:
                    <ol>
                        <li>We count codechef's partial accepted as AC</li>
                        <li>We map every non standard verdict of codeforces to the Other verdict tag</li>
                    </ol>
                </p>
        </@faqa>
    </div>
    <div class="panel panel-default">
        <@faqq id="cd8">
                How is the tag stats chart calculated?
        </@faqq>
        <@faqa id="cd8">
                <p> We look at tags of all practiceProblems you have solved and aggregate them from the handles enterred. There are a few things to keep in mind while looking at the tags chart:
                <ol>
                    <li>The count shown in title may not match the sum of the counts shown in the chart. This is because tag stats is actually a count of (problem, tag) pair. i.e. if a problem has 2 tags then both the tags' count are incremented</li>
                    <li>Untagged practiceProblems are those which have no tag in their corresponding sites</li>
                    <li>The tags are mapped to Adhoc or Implementation when none of the other tags are a good fit</li>
        </@faqa>
    </div>
    <div class="panel panel-default">
        <@faqq id="cd9">
                How are the strong/weak areas computed?
        </@faqq>
        <@faqa id="cd9">
                <p>Strong/weak areas are relative to your own submissions. We look at some of the hardest practiceProblems you have solved in a particular topic and compare it with your performance on other topics to compute your strong and weak areas</p>
        </@faqa>
    </div>
    <div class="panel panel-default">
        <@faqq id="cd10">
                How are the recommendations computed?
        </@faqq>
        <@faqa id="cd10">
                <p>We use the number of users who have solved the problem to estimate its difficulty. The recommendations are relative to the difficulty of your solved practiceProblems so far. So, for example, the easy recommendations for a red coder will be harder than the easy recommendations for a grey coder</p>
        </@faqa>
    </div>
    
</div>
<#include "/common/tail.ftl">
