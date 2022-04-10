<#include "include/macros.ftl">
<@header title="关于 | ${config.siteName}"
    keywords="${config.siteName},关于"
    description="闲来无事，淡淡来书 - ${config.siteName}"
    canonical="/about">
</@header>

<div class="container custome-container">
    <nav class="breadcrumb">
        <a class="crumbs" title="返回首页" href="${config.siteUrl}" data-toggle="tooltip" data-placement="bottom"><i class="fa fa-home"></i>首页</a>
        <i class="fa fa-angle-right"></i>关于
    </nav>
    <div class="row">
        <div class="col-sm-12 blog-main">
            <div class="blog-body expansion">
                <h5 class="legend-title">博主简介</h5>
                <div class="info">
                    <p>
                        博主并不是大牛，本站大部分代码都建立在开源<a href="https://gitee.com/yadong.zhang/DBlog" title="DBlog是一款简洁美观、自适应的Java系统..." data-toggle="tooltip" data-placement="right" target="_blank"><strong>DBlog</strong></a>的基础上，需要开源代码的，直接点击参考。但是本文的内容皆是博主原创，希望能给找到这些文章的人一定的帮助。
                    </p>
                </div>
                <h5 class="legend-title">关于</h5>
                <div class="info">
                    <p><kbd>稻dao草re人n -Accright</kbd></p>
                </div>
                <h5 class="legend-title">关于版权</h5>
                <div class="info">
                    本站大多数文章为站长原创，可能会有少部分文章系从互联网上转载过来的。一般能确认原创出处的，都会在文章中注明。但若因此造成侵权行为，烦请原作者<a target="_blank" href="mailto:my_romantic_story@163.com" title="点击给我发邮件" data-toggle="tooltip" data-placement="bottom" rel="external nofollow"><strong>告知</strong></a>，我会立刻删除相关内容。
                </div>
            </div>
        </div>
        <div class="col-sm-12 blog-main">
            <div class="blog-body expansion">
                <div id="comment-box" data-id="-3"></div>
            </div>
        </div>
    </div>
</div>

<@footer>
    <script src="https://v1.hitokoto.cn/?encode=js&c=d&select=%23hitokoto" defer></script>
</@footer>
