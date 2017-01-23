<%@ include file="/WEB-INF/include/taglibs.jspf"%>
	<link href="<c:url value="/assets/plugins/dataTables/dataTables.bootstrap.css"/>" rel="stylesheet" />
	<link href="<c:url value="/assets/plugins/dataTables/jquery.dataTables.css"/>" rel="stylesheet" />

    <!-- Core Scripts - Include with every page -->
    <script src="<c:url value="/assets/plugins/dataTables/dataTables.bootstrap.js"/>"></script>
    <script src="<c:url value="/assets/plugins/dataTables/jquery.dataTables.js"/>"></script>
    
    <script src="<c:url value="/assets/scripts/app/case-list.js"/>"></script>

        <!--  page-wrapper -->
        <div id="page-wrapper">
            <div class="row">
                <div class="col-lg-12">
                	<div class="page-header alert alert-info">
                   		<b>Case List </b> 
 					</div>
                </div>
            </div>
            <div class="row">
                <div class="col-lg-12">
                    <!-- Advanced Tables -->
                    <div class="panel panel-default">
                        <div class="panel-heading">
                             List of all Cases
                        </div>
                        <div class="panel-body">
                            <div class="table-responsive">
                                <table class="table table-striped table-bordered table-hover" id="case-list">
							        <thead>
							            <tr>
							            	<th>ID</th>
							            	<th>Case Name</th>
							            	<th>Type</th>
							            	<th>Status</th>
							                <th>Client</th>
							     			<th>Case Manager</th>
							     			<th>Date Created</th>
							     			<th></th>
							            </tr>
							        </thead>       
							    </table>
							</div>
						</div>
					</div>
	            </div>
            </div>
        </div>
        <!-- Page Wrapper -->