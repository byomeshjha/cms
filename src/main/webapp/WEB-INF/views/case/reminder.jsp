<%@ include file="/WEB-INF/include/taglibs.jspf"%>
	<link href="<c:url value="/assets/css/app.css"/>" rel="stylesheet" />
	<link href="<c:url value="/assets/css/editor.css"/>" rel="stylesheet" />

    <!-- Core Scripts - Include with every page -->
    <script src="<c:url value="/assets/scripts/validator.js"/>"></script>
    <script src="<c:url value="/assets/scripts/editor.js"/>"></script>
    
    <link href="<c:url value="/assets/plugins/dataTables/dataTables.bootstrap.css"/>" rel="stylesheet" />
	<link href="<c:url value="/assets/plugins/dataTables/jquery.dataTables.css"/>" rel="stylesheet" />

    <!-- Core Scripts - Include with every page -->
    <script src="<c:url value="/assets/plugins/dataTables/dataTables.bootstrap.js"/>"></script>
    <script src="<c:url value="/assets/plugins/dataTables/jquery.dataTables.js"/>"></script>
    
    <script src="<c:url value="/assets/scripts/app/case-reg.js"/>"></script>
    
    <style>
    .row {
        margin-right: 0px;
    	margin-left: 0px;
    }
    </style>

        <!--  page-wrapper -->
        <div id="page-wrapper">
            <div class="row">
                <div class="col-lg-12">
                	<div class="page-header alert alert-info">
                   		<b>Reminder</b> 
 					</div>
                </div>
            </div>
            <div class="row">
                <div class="col-lg-12">
                    <!-- Form Elements -->
                    <div class="panel panel-default">
                        <div class="panel-heading">
                            Case Reminder
                        </div>
                        <div class="panel-body">
                            <div class="row">
                            	<c:if test="${not empty error || not empty messages}">
	                            	<div class="col-lg-12">
		                            	<div class="form-group">
		                            		<c:if test="${not empty error}">
		                            			<label class="errorblock">
		                            				<c:forEach items="${error}" var="er">
		                            					${er} 
		    										</c:forEach>
		                            			</label>
		                            		</c:if>
		                            		<c:if test="${not empty messages}">
		                            			<label class="messageblock">
		                            				<c:forEach items="${messages}" var="msg">
		                            					${msg} 
		    										</c:forEach>
		                            			</label>
		                            		</c:if>
		                            	</div>
	                            	</div>
                            	</c:if>
                                <div class="col-lg-6" style="width:100%;">
			                            
										<c:url var="actionValue" value="/registerCase"/>
										<form:form class="form-horizontal" role="form" method="post" action="${actionValue}" commandName="caseRegistrationForm" enctype="multipart/form-data" data-toggle="validator">

		                                	<div class="row">
												<div class="span1 main-center position-left" style="margin-top:0px;">
													<div class="form-group">
                                                   		<div class="panel panel-primary">
									                        <div class="panel-heading">
									                            <i class="fa fa-legal fa-fw"></i>  Date and Case
									                        </div>
									                        <div class="panel-body">
									                            <div class="row">
									                                <div class="cols-sm-6">
																		<div class="input-group">
																			<form:input type="hidden" path="caseDocketId" value="${caseDocketId == null ? null : caseDocketId}"/>
																			<c:set var="now" value="<%=new java.util.Date()%>" />
																			<fmt:formatDate type="date" value="${now}" var="dateString" pattern="MM/dd/yyyy" />
																			<form:input type="text" class="form-control" path="dateTimeCreated" value="${dateString}" placeholder="MM/DD/YYYY" readonly="true"/>
																			<form:input type="text" class="form-control" path="caseName" placeholder="Case Name"/>
																			<form:select class="form-control" path="caseTypeId">
																				<form:option value="">Select Type</form:option>
																				<form:options items="${CASE_TYPE_KV}" />
																		    </form:select>
																			<form:select class="form-control" path="caseStatusId">
																				<form:option value="">Select Status</form:option>
																				<form:options items="${CASE_STATUS_KV}" />
																		    </form:select>
																			
																			<form:textarea class="form-control" path="caseDescription" value="${caseDescription}" placeholder="Case Description" rows="10"></form:textarea>
																			
																			<div class="help-block with-errors"></div>
																		</div>
																	</div>
									                            </div>
									                        </div>
									                    </div>
													</div>
												</div>
												
												<!-- 2nd column -->
												<div class="span1 main-center position-left" style="margin-top:0px;">
													<div class="form-group">
														<div class="panel panel-primary">
									                        <div class="panel-heading">
									                            <i class="fa fa-user fa-fw"></i>  Select Client
									                            <div class="pull-right">
									                                <div class="btn-group">
									                                    <button type="button" id="pickClient" class="btn btn-default btn-xs" data-toggle="modal" data-target="#selectClientModal">
									                                        Select
									                                    </button>
									                                </div>
									                            </div>
									                        </div>
									
									                        <div class="panel-body">
									                            <div class="row">
									                                <div class="cols-sm-6">
																		<div class="input-group">
																			<form:input type="text" class="form-control" path="client.clientId" placeholder="Client ID" maxlength="6" readonly="true"/>
																			<form:input type="text" class="form-control" path="client.firstName" placeholder="Client First Name" maxlength="25" readonly="true"/>
																			<form:input type="text" class="form-control" path="client.lastName" placeholder="Client Last Name" maxlength="25" readonly="true"/>
																			<div class="help-block with-errors"></div>
																		</div>
																	</div>
									                            </div>
									                        </div>
									                    </div>
													</div>
													<div class="form-group">
													
														<div class="panel panel-primary">
									                        <div class="panel-heading">
									                            <i class="fa fa-user fa-fw"></i>  Assign Case Manager
									                            <div class="pull-right">
									                                <div class="btn-group">
									                                    <button type="button" id="pickCaseManager" class="btn btn-default btn-xs" data-toggle="modal" data-target="#selectCaseManagerModal">
									                                        Select
									                                    </button>
									                                </div>
									                            </div>
									                        </div>
									                        <div class="panel-body">
									                            <div class="row">
									                                <div class="cols-sm-6">
																		<div class="input-group">
																			<form:input type="text" class="form-control" path="caseManager.userId" placeholder="Case Manager ID" readonly="true"/>
																			<form:input type="text" class="form-control" path="caseManager.firstName" placeholder="Case Manager First Name" readonly="true"/>
																			<form:input type="text" class="form-control" path="caseManager.lastName" placeholder="Case Manager Last Name" readonly="true"/>
																			<div class="help-block with-errors"></div>
																		</div>
																	</div>
									                            </div>
									                        </div>
									                    </div>
									                    
													</div>
												</div>
												
												<!-- 3rd column -->
												<div class="span1 main-center position-left" style="margin-top:0px;">
													<div class="form-group">
														<div class="panel panel-primary">
									                        <div class="panel-heading">
									                            <i class="fa fa-files-o fa-fw"></i>  Documents
									                            <div class="pull-right">
									                                <div class="btn-group">
									                                    <button type="button" id="addFile" class="btn btn-default btn-xs">
									                                        Add Document
									                                    </button>
									                                </div>
									                            </div>
									                        </div>
									                        <div class="panel-body">
									                            <div class="row">
									                                <div class="cols-sm-6">
																		<div class="input-group">
																			<div id="fileUploader">
																				<input name="file1" type="file"/>
																			</div>
																		</div>
																	</div>
									                            </div>
									                        </div>
									                    </div>
													</div>
													<div class="form-group">
														<div class="panel panel-primary">
									                        <div class="panel-heading">
									                            <i class="fa fa-file-text-o fa-fw"></i> Notes
									                            <div class="pull-right">
									                                <div class="btn-group">
									                                    <button type="button" id="addNote" class="btn btn-default btn-xs">
									                                        Add Note
									                                    </button>
									                                </div>
									                            </div>
									                        </div>
									                        <div class="panel-body">
									                            <div class="row">
									                                <div class="cols-sm-6">
																		<div class="input-group">
																			<div id="notesDiv">
																				<c:forEach items="${caseRegistrationForm.caseNotes}" var="n" varStatus="vs">
																					<form:textarea class="form-control" path="caseNotes[${vs.index}].note" value="${n.note}" placeholder="Case Notes (255 Characters)" rows="3" cols="60"></form:textarea>
																				</c:forEach>
																			</div>
																		</div>
																	</div>
									                            </div>
									                        </div>
									                    </div>
													</div>
												</div>
											</div>
			                                
			                                <div class="row">
				                                <div class="col-lg-6">
											      <div class="span1">
											      	<button type="submit" class="btn btn-primary btn-sm btn-block login-button" style="width:45%;float:left;">Save</button>
											      </div>
											      <div class="span1 offset2">
											      	<button type="button" class="btn btn-primary btn-sm btn-block login-button" style="width:45%;float:right;margin-left:10px;">Cancel</button>
											      </div>
											    </div>
											</div>
											
										</form:form>

                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!-- end page-wrapper -->
        
        
                <!-- Modal Dialog -->
        <div id="selectClientModal" class="modal fade" role="dialog">
		  <div class="modal-dialog">
		
		    <!-- Modal content-->
		    <div class="modal-content">
		      <div class="modal-header">
		        <button type="button" class="close" data-dismiss="modal">&times;</button>
		        <h4 class="modal-title">Select Client</h4>
		      </div>
		      <div class="modal-body">
				  <div class="table-responsive">
                      <table class="table table-bordered table-hover table-striped" id="active-client-list">
                          <thead>
                              <tr>
                                <th>ID</th>
						        <th>First Name</th>
								<th>Last Name</th>
								<th>Primary Phone</th>
								<th>Select</th>
                              </tr>
                          </thead>
                      </table>
	              </div>
		      </div>
		      <div class="modal-footer">
		        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
		      </div>
		    </div>
		
		  </div>
		</div>
        <!-- Modal Dialog -->
        <div id="selectCaseManagerModal" class="modal fade" role="dialog">
		  <div class="modal-dialog">
		
		    <!-- Modal content-->
		    <div class="modal-content">
		      <div class="modal-header">
		        <button type="button" class="close" data-dismiss="modal">&times;</button>
		        <h4 class="modal-title">Select Case Manager</h4>
		      </div>
		      <div class="modal-body">
		        <div class="table-responsive">
                      <table class="table table-bordered table-hover table-striped" id="active-case-manager-list">
                          <thead>
                              <tr>
                                <th>ID</th>
						        <th>First Name</th>
								<th>Last Name</th>
								<th>Select</th>
                              </tr>
                          </thead>
                      </table>
	              </div>
		      </div>
		      <div class="modal-footer">
		        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
		      </div>
		    </div>
		
		  </div>
		</div>
        

