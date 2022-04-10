<nav id="topmenu" class="navbar navbar-default navbar-fixed-top">
    <div class="menu-box">
        <div class="pull-left">
            <ul class="list-unstyled list-inline">
                <li><span id="currentTime"></span></li>
            </ul>
            <div class="clear"></div>
        </div>
        <!--天气预报界面-->
        <div class="menu-topmenu-container pull-right">
            <iframe allowtransparency="true" frameborder="0" width="650" height="28" scrolling="no" src="//tianqi.2345.com/plugin/widget/index.htm?s=3&z=1&t=0&v=0&d=3&bd=0&k=&f=&ltf=009944&htf=cc0000&q=1&e=1&a=1&c=54511&w=731&h=28&align=right"></iframe>
        </div>
    </div>
</nav>
<nav id="mainmenu" class="navbar navbar-default navbar-fixed-top" role="navigation">
    <div class="menu-box">
        <div class="navbar-header">
            <span class="pull-right nav-search toggle-search" data-toggle="modal" data-target=".nav-search-box"><i class="fa fa-search"></i></span>
            <a href="#" type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </a>
            <a class="navbar-brand logo" href="#"></a>
        </div>
        <div id="navbar" class="navbar-collapse collapse">
            <div class="pull-left site-desc" style="line-height: 0.9;">
                <h1 style="font-size: 20px;font-weight: 700;" class="auto-shake"><a href="${config.siteUrl}" data-original-title="闲来无事，淡淡来书" data-toggle="tooltip" data-placement="bottom">${config.siteName}</a></h1>
                <p class="site-description">闲来无事，淡淡来书</p>
            </div>
            <ul class="nav navbar-nav ">
                <li>
                    <a href="/" class="menu_a"><i class="fa fa-home"></i>首页</a>
                </li>
                <@zhydTag method="types">
                    <#if types?? && types?size gt 0>
                        <#list types as item>
                            <#if item.nodes?exists && item.nodes?size gt 0>
                                <li class="dropdown">
                                    <a href="#" class="dropdown-toggle menu_a" data-toggle="dropdown" aria-expanded="false">
                                        <i class="${item.icon?if_exists}"></i>${item.name?if_exists} <span class="caret"></span>
                                    </a>
                                    <ul class="dropdown-menu" role="menu">
                                        <#list item.nodes as node>
                                        <li><a href="/type/${node.id?c}" title="点击查看《${node.name?if_exists}》">${node.name?if_exists}</a></li>
                                        </#list>
                                    </ul>
                                </li>
                            <#else>
                                <li><a href="/type/${item.id?c}" class="menu_a"><i class="${item.icon?if_exists}"></i>${item.name?if_exists}</a></li>
                            </#if>
                        </#list>
                    </#if>
                </@zhydTag>
                <li><a href="/guestbook" class="menu_a"><i class="fa fa-comments-o"></i>留言板</a></li>
                <li><a href="/about" class="menu_a"><i class="fa fa-info fa-fw"></i>关于</a></li>
                <#--   可以修改mine用来展示个人简历
                 <li><a href="/mine" class="menu_a"><i class="fa fa-ellipsis-v"></i>关于博主</a></li>
                -->
                <li><span class="pull-right nav-search main-search" data-toggle="modal" data-target=".nav-search-box"><i class="fa fa-search"></i></span></li>
            </ul>
        </div>
    </div>
</nav>