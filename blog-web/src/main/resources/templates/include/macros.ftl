<#-- 公共顶部 -->
<#macro header title="稻dao草re人n" keywords="默认文字" description="默认文字" canonical="">
<#include "/common/annotation.ftl">
<!DOCTYPE HTML>
<html lang="zh-CN">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
    <title>${title}</title>
    <meta name="author" content="${config.authorName}(${config.authorEmail})">
    <meta name="keywords" content="${keywords}"/>
    <meta name="description" content="${description}" id="meta_description">
    <link rel="canonical" href="${config.siteUrl}${canonical}" />
    <#include "/layout/quote.ftl">
    <#nested>
</head>
<body>
    <#include "/layout/header.ftl"/>
</#macro>

<#-- 公共底部 -->
<#macro footer>
    <#include "/layout/footer.ftl"/>

    <#nested>

    </body>
</html>
</#macro>

<#-- 分页组件 -->
<#macro pageBar>
    <#if page?exists && (page.pages > 1)>
    <nav>
        <ul class="pager page-btn" data-url="${config.siteUrl}/${url?if_exists}" data-search="${(model.keywords == null || model.keywords == '')?string('false', 'true')}">
            <#if page.hasPreviousPage>
            <li><a class="pointer" data-page="${page.prePage}"><i class="fa fa-angle-double-left"></i></a></li>
            </#if>
            <#list 1..page.pages as item>
            <li><a ${(page.pageNum == item)?string('class="pointer active"','class="pointer" data-page="${item?c}"')}>${item?c}</a></li>
            </#list>
            <#if page.hasNextPage>
            <li><a class="pointer" data-page="${page.nextPage}"><i class="fa fa-angle-double-right"></i></a></li>
            </#if>
        </ul>
    </nav>
    </#if>
</#macro>