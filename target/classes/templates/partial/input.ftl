<#macro example>
    <code><#nested></code>
</#macro>
<div class="row">
    <div class="col-xs-12">
        <ul>
            <li> Each handle should be prefixed with the site prefix (in lowercase) of that handle </li>
            <li> Sites supported <br>
                <table class="table table-bordered">
                    <thead>
                        <tr>
                            <th>Site</th>
                            <th>Prefix</th>
                            <th>Example</th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr>
                            <td>
                                <span> <img class="input-site-icon" src="/images/icons/codeforces.png" /> </span>
                                Codeforces
                            </td>
                            <td>
                                cf/<br>
                                <i>no prefix</i>
                            </td>
                            <td>
                                <@example>cf/tourist</@example><br>
                                <@example>petr</@example>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <span> <img class="input-site-icon" src="/images/icons/codechef.png" /> </span>
                                Codechef
                            </td>
                            <td> cc/ </td>
                            <td><@example>cc/mugurelionut</@example></td>
                        </tr>
                        <tr>
                            <td>
                                <span> <img class="input-site-icon" src="/images/icons/spoj.png" /> </span>
                                Spoj
                            </td>
                            <td> sp/ </td>
                            <td><@example>sp/xilinx</@example></td>
                        </tr>
                    </tbody>
                </table>
            </li>
            <li>
                You can apply multiple prefixes to same handle. This is useful if the same handle is used across multiple sites<br>
                e.g. <@example>cf/sp/cc/acrush</@example>
            </li>
            <li>
                Different handle groups should be space separated<br>
                e.g. <@example>cc/sp/balajiganapath cf/balajiganapathi</@example>
            </li>
        </ul>
    </div>
</div>
