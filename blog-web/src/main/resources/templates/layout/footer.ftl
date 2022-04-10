<#-- 搜索弹窗 -->
<div class="modal fade nav-search-box" tabindex="-1" role="dialog" aria-labelledby="navSearchModalLabel">
    <div class="modal-dialog modal-lg" role="document">
        <div class="modal-content">
            <div class="modal-header" style="padding: 5px 15px;">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close" style="margin-top: 10px;">
                    <span aria-hidden="true">&times;</span></button>
                <h4><i class="fa fa-search"></i> 搜索文章</h4>
            </div>
            <div class="modal-body">
                <form action="/" method="post" class="form-horizontal searchForm" id="searchForm">
                    <input type="hidden" name="pageNumber" value="1">
                    <div class="input-group bottom-line">
                        <input type="text" class="form-control br-none" name="keywords" value="${model.keywords}"
                               required="required" placeholder="输入搜索内容">
                        <span class="input-group-btn">
                        <button class="btn btn-default br-none nav-search-btn pointer" type="submit"><i
                                    class="fa fa-search"></i> 搜索</button>
                    </span>
                    </div>
                    <div class="clear"></div>
                </form>
            </div>
        </div>
    </div>
</div>
<!--评论弹框-->
<div class="modal fade bs-example-modal-sm" id="comment-detail-modal" tabindex="-1" role="dialog"
     aria-labelledby="comment-detail-modal-label">
    <div class="modal-dialog modal-sm" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title" id="comment-detail-modal-label">评论信息框</h4>
                <small><i class="fa fa-lightbulb-o fa-fw"></i>可以通过QQ号实时获取昵称和头像</small>
            </div>
            <div class="modal-body">
                <form id="detail-form">
                    <input type="hidden" name="avatar">
                    <div class="form-group input-logo">
                        <input type="text" class="form-control" name="qq" placeholder="选填" value="">
                        <img class="pull-left hide" alt="">
                        <span class="fa fa-qq pull-left" aria-hidden="true">QQ</span>
                    </div>
                    <div class="form-group input-logo">
                        <input type="text" class="form-control" name="nickname" placeholder="必填" value="匿名">
                        <span class="fa fa-user pull-left" aria-hidden="true">昵称</span>
                    </div>
                    <div class="form-group input-logo">
                        <input type="text" class="form-control" name="email" placeholder="选填">
                        <span class="fa fa-envelope pull-left" aria-hidden="true">邮箱</span>
                    </div>
                    <div class="form-group input-logo">
                        <input type="text" class="form-control" name="url" placeholder="选填">
                        <span class="fa fa-globe pull-left" aria-hidden="true">网址</span>
                    </div>
                    <div class="form-group">
                        <button type="button" class="btn btn-default btn-sm" id="detail-form-btn"><i
                                    class="fa fa-smile-o"></i>提交评论
                        </button>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>
<div class="modal fade" id="reward" tabindex="-1" role="dialog">
    <small class="font-bold"> </small>
</div>
<div id="loading">
    <div class="filter"></div>
    <div class="loader">
        <div class="loading-1"></div>
        <div class="loading-2">Loading...</div>
    </div>
</div>
<!--屏蔽部分不需要的链接-->
<div class="clear blog-footer">
    <div class="container">
        <div>
            <h4>关于本站</h4>
            <h5>稻dao草re人n</h5>
            <div>
                <p style="white-space: initial;">闲来无事，淡淡来书</p>
                <p>
                    <a href="http://beian.miit.gov.cn" target="_blank" title="" data-toggle="tooltip"
                       data-placement="bottom" rel="external nofollow" data-original-title="查看备案信息">
                        鲁ICP备18030331号-1</a>
                </p>
                <a href="${config.siteUrl}/disclaimer" title="免责声明" data-toggle="tooltip" data-placement="bottom"><i
                            class="fa fa-file-o fa-fw"></i>免责声明</a>
            </div>
        </div>
    </div>
</div>
<footer class="footer">
    <div class="clear">
        <p>Copyright&copy;2016-${.now?string("yyyy")} ${config.siteName} · Powered by <a
                    href="https://github.com/Accright/AccRightBlog" title="DBlog是一款简洁美观、自适应的Java系统..."
                    data-toggle="tooltip" data-placement="right" target="_blank"><strong>DBlog</strong></a> · <a
                    href="http://tongji.baidu.com/web/welcome/ico?s=590226d2f4ffbf051442eb75f37333c9" target="_blank"
                    title="点击查看${config.siteName}网站统计详情" data-toggle="tooltip" data-placement="right"
                    rel="external nofollow"><i class="fa fa-bar-chart-o fa-fw fa-spin"></i>网站统计</a></p>
    </div>
</footer>
<a class="to-top" title="点击返回顶部" data-toggle="tooltip" data-placement="bottom"></a>
<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
<script type="text/javascript"
        src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.0/js/bootstrap.min.js"></script>
<script type="text/javascript"
        src="https://cdnjs.cloudflare.com/ajax/libs/jquery_lazyload/1.9.7/jquery.lazyload.min.js"></script>
<script type="text/javascript"
        src="https://cdnjs.cloudflare.com/ajax/libs/jquery-confirm/2.5.1/jquery-confirm.min.js"></script>
<script type="text/javascript"
        src="https://cdnjs.cloudflare.com/ajax/libs/fancybox/2.1.5/jquery.fancybox.min.js"></script>
<script type="text/javascript"
        src="https://cdnjs.cloudflare.com/ajax/libs/jquery.bootstrapvalidator/0.5.1/js/bootstrapValidator.min.js"></script>
<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/js-xss/0.3.3/xss.min.js"></script>
<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/nprogress/0.2.0/nprogress.min.js"></script>
<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/mustache.js/2.3.0/mustache.min.js"></script>
<script type="text/javascript">
    var appConfig = {
        wwwPath: '${config.siteUrl}',
        staticPath: '${config.staticWebSite}',
        qiuniuBasePath: '${config.qiuniuBasePath}',
        wxPraiseCode: '${config.wxPraiseCode}',
        zfbPraiseCode: '${config.zfbPraiseCode}',
    }
</script>
<script type="text/javascript" src="${config.staticWebSite}/js/wangEditor.min.js"></script>
<script type="text/javascript" src="${config.staticWebSite}/js/blog.core.js"></script>
<script type="text/javascript" src="${config.staticWebSite}/js/blog.js"></script>
<script type="text/javascript" src="${config.staticWebSite}/js/blog.comment.js"></script>