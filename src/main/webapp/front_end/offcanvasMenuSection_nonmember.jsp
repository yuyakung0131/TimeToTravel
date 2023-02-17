<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    <!-- Offcanvas Menu Section Begin -->
    <div class="offcanvas-menu-overlay"></div>
    <div class="tttlogotitle">
         <a href="<%=request.getContextPath()%>/front_end/member/home.jsp"><img class="tttlogo" src="<%=request.getContextPath()%>/front_end/member/img/ttt-LOGO.png"  alt="" style="cursor: pointer;"></a>
        <div>Time to Travel</div>
    </div>
    <div class="canvas-open">
        <i class="icon_menu"></i>
    </div>
    <div class="offcanvas-menu-wrapper">
        <div class="canvas-close">
            <i class="icon_close"></i>
        </div>

<!--           <div class="search-icon search-switch"> -->
<%--             <span class="icon"><img class="searchicon" src="<%=request.getContextPath()%>/front_end/member/img/magnifying-glass.png" alt=""></span> --%>
<!--             <input type="search" style="border:2px solid black" id="search" placeholder="探索..." /> -->
<!--         </div> -->
        <div class="top-social"> <a href="<%=request.getContextPath()%>/front_end/member/regist.jsp">
                <img class="account" src="<%=request.getContextPath()%>/front_end/member/img/account.png" alt="">
            </a></div>


        <nav class="mainmenu mobile-menu">
            <ul>
                <li><a href="<%=request.getContextPath()%>/front_end/member/home.jsp">首頁</a></li>
                <li><a href="<%=request.getContextPath()%>/front_end/roomtype/listAllRoomFirm.jsp">住宿</a></li>
                <li><a href="<%=request.getContextPath()%>/front_end/ticket/browseTicket.jsp">票券</a></li>
                <li><a href="<%=request.getContextPath()%>/front_end/itinerarytype/itinerary_list.jsp">行程</a></li>
                <li><a href="<%=request.getContextPath()%>/front_end/article/listAllArticle.jsp">文章</a></li>
                <li><a href="<%=request.getContextPath()%>/front_end/firm/addFirm.jsp">廠商</a></li>
                <li><a href="<%=request.getContextPath()%>/front_end/news/news_front_getAll.jsp">最新消息</a></li>
                <li><a href="<%=request.getContextPath()%>/front_end/qa/NewFile.jsp">常見問題</a></li>
            </ul>
        </nav>
        <div id="mobile-menu-wrap"></div>

        <div class="footeritem">
            <a href="<%=request.getContextPath()%>/front_end/member/aboutus.jsp" style="cursor: pointer;">|&nbsp;關於我們&nbsp;|</a>
<!--             <a href="#">聯繫我們</a> -->
        </div>
    </div>
    <!-- Offcanvas Menu Section End -->