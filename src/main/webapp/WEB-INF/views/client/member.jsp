<%@ include file="/WEB-INF/include/taglibs.jspf"%>
<link href="<c:url value="/assets/css/app.css"/>" rel="stylesheet" />
<link href="<c:url value="/assets/css/editor.css"/>" rel="stylesheet" />

<!-- Core Scripts - Include with every page -->
<script src="<c:url value="/assets/scripts/validator.js"/>"></script>

<script src="<c:url value="/assets/scripts/app/client-reg.js"/>"></script>


<!--  page-wrapper -->
<div id="page-wrapper">
	<div class="row">
		<div class="col-lg-12">
			<div class="page-header alert alert-info">
				<b>Dependent Registration Form </b>
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
										<label class="errorblock"> <c:forEach items="${error}"
												var="er">
												${er} 
											</c:forEach>
										</label>
									</c:if>
									<c:if test="${not empty messages}">
										<label class="messageblock"> <c:forEach
												items="${messages}" var="msg">
												${msg} 
											</c:forEach>
										</label>
									</c:if>
								</div>
							</div>
						</c:if>
						<div class="col-lg-6" style="width: 100%;">
							<c:url var="actionValue" value="/registerMember" />
							<form:form class="form-horizontal" role="form" method="post"
								action="${actionValue}" commandName="memberRegistrationForm"
								data-toggle="validator">
								<div class="row">
									<div class="span1 main-center position-left"
										style="margin-top: 0px;">
										<div class="form-group">
											<label for="name" class="cols-sm-2 control-label">Name</label>
											<div class="cols-sm-6">
												<div class="input-group">
													<span class="input-group-addon"><i
														class="fa fa-user fa" aria-hidden="true"></i></span>
													<form:input type="hidden" path="memberId" />
													<form:input type="hidden" path="clientId" />
													<form:input type="text" class="form-control"
														path="firstName" placeholder="*First Name"
														pattern="[A-Za-z]+" maxlength="25" required="required"
														data-error="First name is invalid." />
													<form:input type="text" class="form-control"
														path="middleName" placeholder="Middle Name"
														pattern="[A-Za-z]+" maxlength="25"
														data-error="Middle name is invalid." />
													<form:input type="text" class="form-control"
														path="lastName" placeholder="*Last Name" required="required"
														pattern="[A-Za-z]+" maxlength="25"
														data-error="Last name is invalid." />
													<div class="help-block with-errors"></div>
												</div>
											</div>
										</div>
										<div class="form-group">
											<label for="relationshipWithPrimary"
												class="cols-sm-2 control-label">Relationship With
												Primary</label>
											<div class="cols-sm-6">
												<div class="input-group">
													<span class="input-group-addon"><i
														class="fa fa-flag fa" aria-hidden="true"></i></span>
													<form:select class="form-control"
														path="applicantRelationToPrimary">
														<option value="">-- Select One --</option>
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
									</div>
									<div class="span1 main-center position-left"
										style="margin-top: 0px;">
										<div class="form-group">
											<label for="email" class="cols-sm-2 control-label">Email
												Address</label>
											<div class="cols-sm-6">
												<div class="input-group">
													<span class="input-group-addon"><i
														class="fa fa-envelope fa" aria-hidden="true"></i></span>
													<form:input type="email" class="form-control"
														path="emailAddress" placeholder="Email Address"
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
													<form:input type="text" class="form-control" path="phone1"
														placeholder="Cell Phone" />
													<form:input type="text" class="form-control" path="phone2"
														placeholder="Home Phone" />
												</div>
											</div>
										</div>
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
									</div>
									<div class="span1 main-center position-left">
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
								</div>
								<div class="row">
									<!-- div class="col-lg-12" -->
									<div style="width: 30%; float: right;">
										<div class="span1">
											<button type="submit" class="btn btn-primary btn-sm">
												<i class="fa fa-save fa"></i> Save
											</button>
											<button type="button" class="btn btn-primary btn-sm">
												<i class="fa fa-close fa"></i> Cancel
											</button>
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








