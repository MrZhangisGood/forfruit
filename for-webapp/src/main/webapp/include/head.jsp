<%@ page contentType="text/html; charset=UTF-8"%>

<div class="col-xs-12 visible-sm visible-xs visible-md" id="templatemo_mobile_menu_wap">
    <p id="mobile_menu_btn">
        <a href="#"><span class="glyphicon glyphicon-align-justify"></span></a>
    </p>
    <div id="mobile_menu">
        <ul class="nav nav-pills nav-stacked">
            <li><a href="#" onclick="window.location.href='<%=request.getContextPath()%>/shoppingCart.jsp';">收藏夹</a></li>
            <li><a href="#" onclick="window.location.href='<%=request.getContextPath()%>/shoppingCart.jsp';">购物车</a></li>
            <li><a href="#" onclick="window.location.href='<%=request.getContextPath()%>/order.jsp';">订单</a></li>
        </ul>
    </div>
</div>

<div id="templatemo_banner_top" class="container_wapper">
    <div class="container">
        <div class="row">
            <div class="col-md-4">
                <p><a href="#">User Login</a> <span class="glyphicon glyphicon-user"></span></p>
            </div>
            <div class="col-md-8">
                <p class="right"><a href="<%=request.getContextPath()%>/index.jsp">首页</a> | <a href="<%=request.getContextPath()%>/groupBuy.jsp">我要团</a></p>
            </div>
        </div>
    </div>
</div>