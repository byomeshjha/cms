 <%@ include file="/WEB-INF/include/taglibs.jspf"%>
        <!-- navbar side -->
        <nav class="navbar-default navbar-static-side" role="navigation">
            <!-- sidebar-collapse -->
            <div class="sidebar-collapse">
                <!-- side-menu -->
                <ul class="nav" id="side-menu">
                    <li>
                        <!-- user image section-->
                        <div class="user-section">
                            <div class="user-section-inner">
                                <img src="<c:url value="/assets/img/user.jpg"/>" alt="">
                            </div>
                            <div class="user-info">
                                <div><h8>${LOGGEDIN_USER.firstName} ${LOGGEDIN_USER.lastName}</h8></div>
                                <div class="user-text-online">
                                    <span class="user-circle-online btn btn-success btn-circle "></span>&nbsp;Online
                                </div>
                            </div>
                        </div>
                        <!--end user image section-->
                    </li>
                    <li class="sidebar-search">
                        <!-- search section-->
                        <div class="input-group custom-search-form">
                            <input type="text" class="form-control" placeholder="Search...">
                            <span class="input-group-btn">
                                <button class="btn btn-default" type="button">
                                    <i class="fa fa-search"></i>
                                </button>
                            </span>
                        </div>
                        <!--end search section-->
                    </li>
                    <li <c:out value="${NAV_SELECTED == 'home'?' class=selected':''}" />>
                        <a href="<c:url value="/"/>"><i class="fa fa-dashboard fa-fw"></i>Home</a>
                    </li>
                    <li <c:out value="${(NAV_SELECTED=='case/list' || NAV_SELECTED=='case/register')?' class=active':''}" />>
                        <a href="#"><i class="fa fa-folder-o fa-fw"></i> Case<span class="fa arrow"></span></a>
                        <ul class="nav nav-second-level">
                            <li <c:out value="${NAV_SELECTED=='case/list'?' class=selected':''}" />>
                                <a href="<c:url value="/case/list"/>"><i class="fa fa-list-ol fa-fw" style="margin-left:30px;padding-right:10px;"></i>List</a>
                            </li>
                            <li <c:out value="${NAV_SELECTED=='case/register'?' class=selected':''}" />>
                                <a href="<c:url value="/case/register"/>"><i class="fa fa-plus-square-o fa-fw" style="margin-left:30px;padding-right:10px;"></i>Add New</a>
                            </li>
                            <li <c:out value="${NAV_SELECTED=='case/reminder'?' class=selected':''}" />>
                                <a href="<c:url value="/case/reminder"/>"><i class="fa fa-calendar fa-fw" style="margin-left:30px;padding-right:10px;"></i>Add Reminder</a>
                            </li>
                        </ul>
                        <!-- second-level-items -->
                    </li>
                    <li <c:out value="${(NAV_SELECTED=='client/list' || NAV_SELECTED=='client/register')?' class=active':''}" />>
                        <a href="#"><i class="fa fa-group fa-fw"></i> Client<span class="fa arrow"></span></a>
                        <ul class="nav nav-second-level">
                            <li <c:out value="${NAV_SELECTED=='client/list'?' class=selected':''}" />>
                                <a href="<c:url value="/client/list"/>" ><i class="fa fa-list-ol fa-fw" style="margin-left:30px;padding-right:10px;"></i>List</a>
                            </li>
                            <li <c:out value="${NAV_SELECTED=='client/register'?' class=selected':''}" />>
                                <a href="<c:url value="/client/register"/>"><i class="fa fa-plus-square-o fa-fw" style="margin-left:30px;padding-right:10px;"></i>Add New Client</a>
                            </li>
                        </ul>
                        <!-- second-level-items -->
                    </li>
                    <li <c:out value="${(NAV_SELECTED=='calendar/schedules')?' class=active':''}" />>
                        <a href="#"><i class="fa fa-calendar-o fa-fw"></i> Scheduler<span class="fa calendar"></span></a>
                        <ul class="nav nav-second-level">
                            <li <c:out value="${NAV_SELECTED=='calendar/schedules'?' class=selected':''}" />>
                                <a href="<c:url value="/calendar/schedules"/>" ><i class="fa fa-calendar fa-fw" style="margin-left:30px;padding-right:10px;"></i>Calendar </a>
                            </li>
                        </ul>
                        <!-- second-level-items -->
                    </li>
                </ul>
                <!-- end side-menu -->
            </div>
            <!-- end sidebar-collapse -->
        </nav>
        <!-- end navbar side -->