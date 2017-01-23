<%@ include file="/WEB-INF/include/taglibs.jspf"%>

<link href="<c:url value="/assets/css/fullcalendar/2.6.1/fullcalendar.css"/>" type="text/css" rel="stylesheet" />
<link href="<c:url value="/assets/css/fullcalendar/2.6.1/fullcalendar.print.css"/>" type="text/css" rel="stylesheet" />
<link href="<c:url value="/assets/css/schedules.css"/>" type="text/css" rel="stylesheet" />


<!--  page-wrapper -->
<div id="page-wrapper">
	<div class="row">
		<div class="col-lg-12">
			<div class="page-header alert alert-info">
				<b>Calendar Events </b>
			</div>
		</div>
	</div>
	<div class="row">
		<div class="col-lg-12">
			<div class="panel panel-default">
				<div class="panel-heading">My Calendar</div>
				<div class="panel-body">
					<div class="row">
						<div class="col-lg-12">
							<div id="schedulerCalendar"></div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>

    <div id="fullCalModal" class="modal fade">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">×</span> <span class="sr-only">close</span></button>
                    <h4 id="modalTitle" class="modal-title"></h4>
                </div>
                <div id="modalBody" class="modal-body"></div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                    <a class="btn btn-primary" id="eventUrl" target="_blank">Event Page</a>
                </div>
            </div>
        </div>
    </div>
</div>

<script src="<c:url value="/assets/plugins/moment/2.8.2/moment.min.js"/>"></script>
<script src="<c:url value="/assets/scripts/fullcalendar/2.6.1/fullcalendar.min.js"/>"></script>
<script src="<c:url value="/assets/scripts/app/app-schedules.js"/>"></script>
