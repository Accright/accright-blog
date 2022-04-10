<#include "include/macros.ftl">
<@header title="留言板 | ${config.siteName}"
    keywords="${config.siteName},留言板"
    description="我的留言板,欢迎给我的个人留言 - ${config.siteName}"
    canonical="/guestbook">
    <style>
        ul {
            list-style: none;
            margin-left: 0;
            padding-left: 0;
        }
    </style>
</@header>

<div class="container custome-container">
    <nav class="breadcrumb">
        <a class="crumbs" title="返回首页" href="${config.siteUrl}" data-toggle="tooltip" data-placement="bottom"><i class="fa fa-home"></i>首页</a>
        <i class="fa fa-angle-right"></i>留言板
    </nav>
    <div class="row">
        <div class="col-sm-12 blog-main">
            <div class="blog-body expansion">
                <div class="alert alert-default alert-dismissible" role="alert" style="padding: 0px;">
                    <ul>
                        <li><i class="fa fa-heartbeat fa-fw"></i> <strong>闲来无事，淡淡来书</strong></li>
                    </ul>
                </div>
            </div>
        </div>
        <div class="col-sm-12 blog-main">
            <div class="blog-body expansion">
                <div id="comment-box" data-id="-1"></div>
            </div>
        </div>
    </div>
</div>

<@footer>
    <#--<script src="https://v1.hitokoto.cn/?encode=js&c=d&select=%23hitokoto" defer></script>-->
</@footer>
