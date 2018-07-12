<#include "include/macros.ftl">
<@header title="${config.siteName?if_exists} | 闲来无事，淡淡来书"
    keywords="${config.homeKeywords?if_exists}"
    description="${config.homeDesc?if_exists}"
    canonical="/${url?if_exists}">
</@header>

<div class="container custome-container">
    <!--[if lt IE 9]><div class="alert alert-danger topframe" role="alert">Oh My God！你的浏览器实在<strong>太太太太太太旧了</strong>，赶紧升级浏览器 <a target="_blank" class="alert-link" href="http://browsehappy.com">立即升级</a></div><![endif]-->
    <#if config.maintenance?if_exists && config.maintenance>
    <div class="alert alert-warning fade-in" role="alert">
        <a href="#" class="close" data-dismiss="alert">&times;</a>
        系统预计将在<strong>${config.maintenanceData?string('yyyy年MM月dd日 HH点mm分')}</strong>进行更新，届时网站将无法使用，更新时间大约 5-10分钟，敬请悉知。
    </div>
    </#if>
    <div class="row">
        <div class="col-sm-8 blog-main">
            <#if page.list?exists && (page.list?size > 0)>
                <#list page.list as item>
                    <article class="fade-in">
                        <figure class="thumbnail">
                            <a href="${config.siteUrl}/article/${item.id?c}">
                                <#if item.coverImage?exists && (item.coverImage?length > 7)>
                                    <img width="150" height="150" data-original="${config.qiuniuBasePath}${item.coverImage}" class="img-responsive lazy-img" alt="${item.title?if_exists}">
                                <#else>
                                    <img width="150" height="150" data-original="${config.staticWebSite}/img/user/11.jpg" class="img-responsive lazy-img" alt="${item.title?if_exists}">
                                </#if>
                            </a>
                            <#if item.original?string('true','false') == 'true'>
                                <span class="cat"><a href="${config.staticWebSite}/original/${item.original?c}">原创</a></span>
                            <#else>
                                <span class="cat"><a href="${config.staticWebSite}/original/${item.original?c}">转载</a></span>
                            </#if>
                        </figure>
                        <header class="entry-header">
                            <h2 class="entry-title">
                                <a href="${config.siteUrl}/article/${item.id?c}" rel="bookmark" title="点击查看文章详情" data-toggle="tooltip" data-placement="bottom">${item.title}</a>
                            </h2>
                        </header>
                        <div class="entry-content">
                            <div class="archive-content">
                                ${item.description?if_exists}
                            </div>
                            <span class="title-l"></span>
                            <span class="entry-meta">
                                <span class="date" title="文章发表日期" data-toggle="tooltip" data-placement="bottom"><i class="fa fa-calendar-o fa-fw"></i>${item.createTime?string('yyyy-MM-dd')}</span>
                                <span class="views" title="文章阅读次数" data-toggle="tooltip" data-placement="bottom"><i class="fa fa-eye fa-fw"></i>浏览(${item.lookCount!(0)})</span>
                                <span class="comment" title="文章评论次数" data-toggle="tooltip" data-placement="bottom">
                                    <a href="${config.siteUrl}/article/${item.id?c}#comment-box" rel="external nofollow">
                                        <i class="fa fa-comments-o fa-fw"></i>评论(${item.commentCount!(0)})
                                    </a>
                                </span>
                            </span>
                            <div class="clear"></div>
                            <span class="entry-more">
                                <a href="${config.siteUrl}/type/${item.typeId?c}" rel="bookmark" title="点击查看${item.type.name}分类下所有文章" data-toggle="tooltip" data-placement="bottom">${item.type.name}</a>
                            </span>
                        </div>
                    </article>
                </#list>
                <@pageBar></@pageBar>
            <#else >
                <article class="fade-in" style="height: auto">
                    <div class="rows">
                        <div class="col-md-10 col-sm-12">
                            <div class="h3 text-center">亲，啥也没找到啊~~<img src="http://img.t.sinajs.cn/t35/style/images/common/face/ext/normal/15/j_thumb.gif" alt="[囧]"></div>
                            <div class="h5 text-center">换个姿势，再来一次~~</div>
                        </div>
                        <div class="col-md-2 col-sm-12">
                            <img src="${config.staticWebSite}/img/wrong.png" class="center-block" alt="没有结果" style="width: 110px;margin-top: -10px;">
                        </div>
                    </div>
                    <form action="/" method="post" class="form-horizontal searchForm">
                        <input type="hidden" name="pageNumber" value="1">
                        <div class="input-group">
                            <input type="text" class="form-control br-none" name="keywords" placeholder="重新查找~~">
                            <span class="input-group-btn">
                                <button class="btn btn-default br-none nav-search-btn pointer" type="submit"><i class="fa fa-search"></i> 搜索</button>
                            </span>
                        </div>
                        <div class="clear" style="margin-bottom: 10px"></div>
                        <ul class="list-unstyled list-inline search-hot">
                            <li><strong style="position: relative;top: 2px;color: #999999;">热门搜索：</strong></li>
                            <li><a class="pointer" rel="external nofollow"><span class="label label-default">Java</span></a></li>
                            <li><a class="pointer" rel="external nofollow"><span class="label label-primary">Springboot</span></a></li>
                            <li><a class="pointer" rel="external nofollow"><span class="label label-success">Linux</span></a></li>
                            <li><a class="pointer" rel="external nofollow"><span class="label label-info">Maven</span></a></li>
                            <li><a class="pointer" rel="external nofollow"><span class="label label-warning">Bootstrap</span></a></li>
                        </ul>
                    </form>
                </article>
            </#if>
        </div>
        <#include "layout/sidebar.ftl"/>
    </div>
</div>

<@footer></@footer>
