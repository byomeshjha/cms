<%@ include file="/WEB-INF/include/taglibs.jspf"%>
<link href="<c:url value="/assets/css/app.css"/>" rel="stylesheet" />
<link href="<c:url value="/assets/css/editor.css"/>" rel="stylesheet" />


<link href="<c:url value="/assets/plugins/dataTables/dataTables.bootstrap.css"/>" rel="stylesheet" />
<link href="<c:url value="/assets/plugins/dataTables/jquery.dataTables.css"/>" rel="stylesheet" />

<!-- Core Scripts - Include with every page -->
<script src="<c:url value="/assets/scripts/validator.js"/>"></script>
<script src="<c:url value="/assets/scripts/editor.js"/>"></script>

<script src="<c:url value="/assets/plugins/dataTables/dataTables.bootstrap.js"/>"></script>
<script src="<c:url value="/assets/plugins/dataTables/jquery.dataTables.js"/>"></script>
<script src="<c:url value="/assets/scripts/app/client-reg.js"/>"></script>

<!--  page-wrapper -->
<div id="page-wrapper">
	<div class="row">
		<div class="col-lg-12">
			<div class="page-header alert alert-info">
				<b>Client Registration Form </b>
			</div>
		</div>
	</div>
	<div class="row">
		<div class="col-lg-12">
			<!-- Form Elements -->
			<div class="panel panel-default">
				<div class="panel-heading">Biographic Information</div>
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
						<div class="col-lg-6" style="width: 100%;">
							<!--Basic Tabs   -->
							<ul class="nav nav-tabs">
								<li class="active"><a href="#primary" data-toggle="tab">*Primary</a></li>
								<li><a href="#questionnaire" data-toggle="tab">Questionnaire</a></li>
								<li><a href="#dependents" data-toggle="tab">Dependents</a></li>
							</ul>
							<input type="hidden" name="selectedTab" id="selected-tab" />
							<c:url var="actionValue" value="/registerClient" />
							<form:form class="form-horizontal" role="form" method="post" id="client-registration-form"
								action="${actionValue}" commandName="clientRegistrationForm"
								data-toggle="validator">
								<div class="tab-content">
									<div class="tab-pane fade in active" id="primary">
										<div class="row">
											<div class="span1 main-center position-left" style="margin-top: 0px;">
												<div class="form-group">
													<label for="dateOfClientRegistration"
														class="cols-sm-2 control-label">Date and Referral</label>
													<div class="cols-sm-6">
														<div class="input-group">
															<span class="input-group-addon"><i
																class="fa fa-calendar fa" aria-hidden="true"></i></span>
															<form:input type="hidden" path="clientId"
																value="${clientId == null ? null : clientId}" />
															<c:set var="now" value="<%=new java.util.Date()%>" />
															<fmt:formatDate type="date" value="${now}"
																var="dateString" pattern="MM/dd/yyyy" />
															<form:input type="text" class="form-control"
																path="dateTimeCreated" value="${dateString}"
																placeholder="MM/DD/YYYY" readonly="true" />
															<form:input type="text" class="form-control"
																path="referredBy" placeholder="Referred By" />
														</div>
													</div>
												</div>
												<div class="form-group">
													<div class="checkbox">
														<label for="existingClient"
															class="cols-sm-2 control-label">Are you an
															existing client?</label>
														<form:checkbox path="existingClient" id="existingClient" />
													</div>
												</div>
												<div class="form-group">
													<label for="name" class="cols-sm-2 control-label">Name</label>
													<div class="cols-sm-6">
														<div class="input-group">
															<span class="input-group-addon"><i
																class="fa fa-user fa" aria-hidden="true"></i></span>
															<form:select class="form-control" path="salutation"
																value="${salutation}">
																<option value="">Select Salutation</option>
																<option value="Mr.">Mr.</option>
																<option value="Ms.">Ms.</option>
															</form:select>
															<form:input type="text" class="form-control"
																path="firstName" placeholder="*First Name"
																required="required" pattern="[A-Za-z]+" maxlength="25"
																data-error="First name is invalid." />
															<form:input type="text" class="form-control"
																path="middleName" placeholder="Middle Name"
																pattern="[A-Za-z]+" maxlength="25"
																data-error="Middle name is invalid." />
															<form:input type="text" class="form-control"
																path="lastName" placeholder="*Last Name"
																required="required" pattern="[A-Za-z]+" maxlength="25"
																data-error="Last name is invalid." />
															<div class="help-block with-errors"></div>
														</div>
													</div>
												</div>
												<div class="form-group">
													<label for="email" class="cols-sm-2 control-label">Email
														Address</label>
													<div class="cols-sm-6">
														<div class="input-group">
															<span class="input-group-addon"><i
																class="fa fa-envelope fa" aria-hidden="true"></i></span>
															<form:input type="email" class="form-control"
																path="emailAddress" id="emailAddress"
																placeholder="Email Address"
																pattern="[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,4}$"
																data-error="Please provide valid email address." />
															<div class="help-block with-errors"></div>
														</div>
													</div>
												</div>
												<div class="form-group">
													<label for="phone1" class="cols-sm-2 control-label">Phone</label>
													<div class="cols-sm-6">
														<div class="input-group">
															<span class="input-group-addon"><i
																class="fa fa-phone fa" aria-hidden="true"></i></span>
															<form:input type="text" class="form-control"
																path="phone1" id="phone1" placeholder="Cell Phone" />
															<form:input type="text" class="form-control"
																path="phone2" id="phone2" placeholder="Home Phone" />
														</div>
													</div>
												</div>
												<div class="form-group">
													<label for="address" class="cols-sm-2 control-label">US
														Address</label>
													<div class="cols-sm-6">
														<div class="input-group">
															<span class="input-group-addon"><i
																class="fa fa-map-marker fa" aria-hidden="true"></i></span>
															<c:forEach items="${clientRegistrationForm.addresses}" var="address" varStatus="status">
															<form:input type="hidden" path="addresses[${status.index}].addressType"
																value="Primary" />
															<form:input type="text" class="form-control"
																path="addresses[${status.index}].line1" placeholder="Address Line 1" />
															<form:input type="text" class="form-control"
																path="addresses[${status.index}].line2" placeholder="Address Line 2" />
															<form:input type="text" class="form-control"
																path="addresses[${status.index}].city" placeholder="City" />
															<form:input type="text" class="form-control"
																path="addresses[${status.index}].state" placeholder="State" />
															<form:input type="text" class="form-control"
																path="addresses[${status.index}].zip" placeholder="Zip" />
															<form:input type="text" class="form-control"
																path="addresses[${status.index}].country" placeholder="Country" />
															</c:forEach>
														</div>
													</div>
												</div>
											</div>
											<div class="span1 main-center main-center position-left"
												style="margin-top: 0px;">
												<div class="form-group">
													<label for="passport" class="cols-sm-2 control-label">Passport</label>
													<div class="cols-sm-6">
														<div class="input-group">
															<span class="input-group-addon"><i
																class="fa fa-book fa" aria-hidden="true"></i></span>
															<form:input type="text" class="form-control"
																path="passportIssuedByCountry"
																placeholder="Passport Issued By (Country)" />
															<form:input type="text" class="form-control"
																path="passportNumber" placeholder="Passport Number" />
															<form:input type="text" class="form-control"
																path="passportExpirationDate"
																placeholder="Expiration Date (MM/DD/YYYY)" />
															<form:input type="text" class="form-control"
																path="nameOnPassport"
																placeholder="Name as Appears on Passport" />
														</div>
													</div>
												</div>
												<div class="form-group">
													<label for="dateOfBirth" class="cols-sm-2 control-label">Birth
														&amp; Nationality</label>
													<div class="cols-sm-6">
														<div class="input-group">
															<span class="input-group-addon"><i
																class="fa fa-flag fa" aria-hidden="true"></i></span>
															<form:input type="text" class="form-control"
																path="dateOfBirth"
																placeholder="Date of Birth (MM/DD/YYYY)" />
															<form:input type="text" class="form-control"
																path="placeOfBirth" placeholder="Place of Birth" />
															<form:input type="text" class="form-control"
																path="nationality" placeholder="Nationality" />
														</div>
													</div>
												</div>
												<div class="form-group">
													<label for="visaStatus" class="cols-sm-2 control-label">Visa
														Status</label>
													<div class="cols-sm-6">
														<div class="input-group">
															<span class="input-group-addon"><i
																class="fa fa-plane fa" aria-hidden="true"></i></span>
															<form:input type="text" class="form-control"
																path="dateOfLastEntry"
																placeholder="Date of Last Entry (MM/DD/YYYY)" />
															<form:input type="text" class="form-control"
																path="mannerOfLastEntry"
																placeholder="Manner of Last Entry" />
															<form:input type="text" class="form-control"
																path="visaExpiryDate"
																placeholder="Visa Expiry Date (MM/DD/YYYY)" />
														</div>
													</div>
												</div>
												<div class="form-group">
													<label for="email" class="cols-sm-2 control-label">Spouse
														or closest family member in US</label>
													<div class="cols-sm-6">
														<div class="input-group">
															<span class="input-group-addon"><i
																class="fa fa-info-circle fa" aria-hidden="true"></i></span>
															<form:input type="text" class="form-control"
																path="closestFamilyMemberName" placeholder="Name" />
															<!--form:input type="text" class="form-control" path="closestFamilyMemberRelation" placeholder="Relation"/-->
															<form:select class="form-control"
																path="closestFamilyMemberRelation"
																value="${closestFamilyMemberRelation}">
																<option value="">Select Relation</option>
																<option value="Wife">Wife</option>
																<option value="Husband">Husband</option>
																<option value="Spouse">Spouse</option>
																<option value="Daughter">Daughter</option>
																<option value="Son">Son</option>
																<option value="Mother">Mother</option>
																<option value="Father">Father</option>
																<option value="Sister">Sister</option>
																<option value="Brother">Brother</option>
															</form:select>
															<div class="help-block with-errors"></div>
														</div>
													</div>
												</div>
												<div class="form-group">
													<label for="email" class="cols-sm-2 control-label">Current
														Immigration Status</label>
													<div class="cols-sm-6">
														<div class="input-group">
															<span class="input-group-addon"><i
																class="fa fa-legal fa" aria-hidden="true"></i></span>
															<form:input type="text" class="form-control"
																path="currentImmigrationStatus"
																placeholder="Current Immigration Status" />
															<div class="help-block with-errors"></div>
														</div>
													</div>
												</div>
											</div>
											<div class="span1 main-center position-left">
												<div class="form-group">
													<label for="emergencyContactPerson"
														class="cols-sm-2 control-label">Emergency Contact
														Person</label>
													<div class="cols-sm-6">
														<div class="input-group">
															<span class="input-group-addon"><i
																class="fa fa-info-circle fa" aria-hidden="true"></i></span>
															<form:input type="text" class="form-control"
																path="emergencyContactPersonName" placeholder="Name" />
															<form:input type="text" class="form-control"
																path="emergencyContactPersonPhone1" placeholder="Phone" />
															<form:input type="text" class="form-control"
																path="emergencyContactPersonPhone2"
																placeholder="Alternate Phone" />
														</div>
													</div>
												</div>
												<div class="form-group">
													<label for="factSheet" class="cols-sm-2 control-label">Fact
														Sheet</label>
													<div class="cols-sm-6">
														<div class="input-group">
															<span class="input-group-addon"><i
																class="fa fa-file-text-o fa" aria-hidden="true"></i></span>
															<form:textarea class="form-control" path="factSheet"
																value="${factSheet}" placeholder="Fact Sheet" rows="28"></form:textarea>
														</div>
													</div>
												</div>
											</div>
										</div>
									</div>
									<!-- End of primary tab -->
									<!-- Begin Questionnaire tab -->
									<div class="tab-pane fade" id="questionnaire">
										<div class="row">
											<div class="span1 main-center position-left"
												style="margin-top: 0px;">
												<div class="form-group">
													<label for="questionnaire" class="cols-sm-2 control-label"><form:checkbox
															path="visaExtensionApplied" />&nbsp;Have you ever
														applied for an extension on your visa?</label>
													<div class="cols-sm-6">
														<div class="input-group">
															<span class="input-group-addon"><i
																class="fa fa-question-circle fa" aria-hidden="true"></i></span>
															<form:input type="text" class="form-control"
																path="placeOfVisaExtension"
																placeholder="Where Applied for Visa Extension?" />
															<form:input type="text" class="form-control"
																path="dateOfVisaExtension"
																placeholder="When Applied for Visa Extension (MM/DD/YYYY)" />
														</div>
													</div>
												</div>
												<div class="form-group">
													<label for="questionnaire" class="cols-sm-2 control-label"><form:checkbox
															path="appliedForPermanentResidency" />&nbsp;Have you
														ever applied for permanent residency?</label>
													<div class="cols-sm-6">
														<div class="input-group">
															<span class="input-group-addon"><i
																class="fa fa-question-circle fa" aria-hidden="true"></i></span>
															<form:input type="text" class="form-control"
																path="placeOfPermanentResidencyApplied"
																placeholder="Where Applied for Permanent Residency?" />
															<form:input type="text" class="form-control"
																path="dateOfPermanentResidencyApplied"
																placeholder="When Applied for Permanent Residency (MM/DD/YYYY)" />
														</div>
													</div>
												</div>
												<div class="form-group">
													<label for="questionnaire" class="cols-sm-2 control-label"><form:checkbox
															path="divorced" />&nbsp;Are you divorced?</label>
													<div class="cols-sm-6">
														<div class="input-group">
															<span class="input-group-addon"><i
																class="fa fa-question-circle fa" aria-hidden="true"></i></span>
															<form:input type="text" class="form-control"
																path="placeOfDivorce"
																placeholder="When were you divorced? (MM/DD/YYYY)" />
															<form:input type="text" class="form-control"
																path="dateOfDivorce"
																placeholder="Where were you divorced?" />
															<form:input type="text" class="form-control"
																path="divorcedSpouseName"
																placeholder="Name of Previous Spouse" />
														</div>
													</div>
												</div>
											</div>
											<div class="span1 main-center position-left"
												style="margin-top: 0px;">
												<div class="form-group">
													<label for="questionnaire" class="cols-sm-2 control-label"><form:checkbox
															path="employed" />&nbsp;Are you employed?</label> &nbsp; <label
														for="questionnaire" class="cols-sm-2 control-label"><form:checkbox
															path="everEmployedInUS" />&nbsp;Have you ever worked in
														the US?</label>
													<div class="cols-sm-6">
														<div class="input-group">
															<span class="input-group-addon"><i
																class="fa fa-question-circle fa" aria-hidden="true"></i></span>
															<form:input type="text" class="form-control"
																path="employerName" placeholder="Name of Employer" />
															<form:input type="hidden"
																path="employerAddress.addressType" value="Primary" />
															<form:input type="text" class="form-control"
																path="employerAddress.line1"
																placeholder="Employer Address Line 1" />
															<form:input type="text" class="form-control"
																path="employerAddress.line2"
																placeholder="Employer Address Line 2" />
															<form:input type="text" class="form-control"
																path="employerAddress.city" placeholder="City" />
															<form:input type="text" class="form-control"
																path="employerAddress.state" placeholder="State" />
															<form:input type="text" class="form-control"
																path="employerAddress.zip" placeholder="Zip" />
															<form:input type="text" class="form-control"
																path="employerAddress.country" placeholder="Country" />
														</div>
													</div>
												</div>
												<div class="form-group">
													<label for="questionnaire" class="cols-sm-2 control-label"><form:checkbox
															path="arrested" />&nbsp;Have you ever been arrested?</label>
													<div class="cols-sm-6">
														<div class="input-group">
															<span class="input-group-addon"><i
																class="fa fa-question-circle fa" aria-hidden="true"></i></span>
															<form:textarea class="form-control"
																path="reasonForArrest"
																placeholder="Reason(s) for Arrest" rows="6"></form:textarea>
															<form:input type="text" class="form-control"
																path="placeOfArrest" placeholder="Where Arrested?" />
															<form:input type="text" class="form-control"
																path="dateOfArrest"
																placeholder="When Arrested (MM/DD/YYYY)" />
														</div>
													</div>
												</div>
											</div>
											<div class="span1 main-center position-left"
												style="margin-top: 0px;">
												<div class="form-group">
													<label for="questionnaire" class="cols-sm-2 control-label"><form:checkbox
															path="deported" id="deported" />&nbsp;Have you ever been
														deported?</label>
													<div class="cols-sm-6">
														<div class="input-group">
															<span class="input-group-addon"><i
																class="fa fa-question-circle fa" aria-hidden="true"></i></span>
															<form:input type="text" class="form-control"
																path="placeOfDeportation"
																placeholder="Where were you deported?" />
															<form:input type="text" class="form-control"
																path="dateOfDeportation"
																placeholder="When were you deported (MM/DD/YYYY)" />
														</div>
													</div>
												</div>
											</div>
										</div>
									</div>
									<!-- End of questionnaire tab -->

									<!-- Begin Dependents Tab -->
									<div class="tab-pane fade" id="dependents">

										<input type="hidden" id="dependents-json"
											name="dependentsJson" />

										<div class="panel panel-primary">
											<div class="panel-heading">
												<i class="fa fa-files-o fa-fw"></i> Dependents
												<div class="pull-right">
													<div class="btn-group">
														<button type="button" id="addDependent"
															class="btn btn-default btn-xs">
															<i class="fa fa-user-plus fa"></i> Add Dependent
														</button>
													</div>
												</div>
											</div>
											<div class="panel-body">
												<div class="row">
													<div
														class="span1 main-center-max position-left margin-left-25">
														<div id="docList">
															<div class="table-responsive">
																<c:set var="member_last_index"
																	value="${fn:length(clientRegistrationForm.members) - 1}" />
																<table
																	class="table table-bordered table-hover table-striped"
																	id="dependent-list">
																	<thead>
																		<tr>
																			<th>ID - <c:out value="${member_last_index}" /></th>
																			<th>First Name</th>
																			<th>Last Name</th>
																			<th>Relation</th>
																			<th>Date Of Birth</th>
																			<th>Phone #</th>
																			<th>Email</th>
																			<th>View</th>
																		</tr>
																	</thead>
																	<tbody>
																		<c:forEach items="${clientRegistrationForm.members}"
																			var="n" varStatus="vs">
																			<tr>
																				<td>${n.memberId}</td>
																				<td>${n.firstName}</td>
																				<td>${n.lastName}</td>
																				<td>${n.applicantRelationToPrimary}</td>
																				<td><fmt:formatDate pattern="MM/dd/yyyy"
																						type="date" value="${n.dateOfBirth}" /></td>
																				<td>${n.phone1}</td>
																				<td>${n.emailAddress}</td>
																				<c:choose>
																					<c:when
																						test="${n.firstName == '' or n.firstName == null}">
																						<td></td>
																					</c:when>
																					<c:otherwise>
																						<td>
																							<button type="button" 
																							onclick="javascript:viewMember('<c:url value="/updateMember/${n.memberId}"/>')"
																							class="btn btn-info btn-sm">View</button>
																						</td>
																					</c:otherwise>
																				</c:choose>
																			</tr>
																		</c:forEach>
																	</tbody>
																</table>
															</div>
														</div>
													</div>
												</div>
											</div>
										</div>
									</div>
									<!-- end of dependents tab -->
								</div>
								<!--End Basic Tabs - Tab content -->
								<div class="row">
									<!-- div class="col-lg-12" -->
									<div style="width: 30%; float: right;">
										<div class="span1">
											<button type="submit" class="btn btn-primary btn-sm"
												style="width: 120px;">Save</button>
											<button type="button" class="btn btn-primary btn-sm"
												style="width: 120px;">Cancel</button>
										</div>
									</div>
									<!-- /div -->
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

