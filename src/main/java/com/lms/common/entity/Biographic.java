package com.lms.common.entity;

import java.sql.Timestamp;
import java.util.Arrays;
import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Embedded;
import javax.persistence.Lob;
import javax.persistence.MappedSuperclass;


@MappedSuperclass
public class Biographic extends BaseEntity {
	
	@Column(name = "First_Name", length = 30, nullable = false)
	private String firstName;
	
	@Column(name = "Middle_Name", length = 30)
	private String middleName;
	
	@Column(name = "Last_Name", length = 30)
	private String lastName;
	
	@Column(name = "Salutation", length = 4)
	private String salutation;
	
	@Column(name = "Gender", length = 35)
	private String gender;
	
	@Column(name = "Applicant_Relation_To_Primary", length = 15)
	private String applicantRelationToPrimary;
	
	@ElementCollection 
	private Collection<Address> addresses;
	
	@Column(name = "Primary_Phone", length = 15)
	private String phone1;
	
	@Column(name = "Secondary_Phone", length = 15)
	private String phone2;
	
	@Column(name = "Email_Address", length = 70)
	private String emailAddress;
	
	@Column(name = "Date_Of_Birth")
	private Timestamp dateOfBirth;
	
	@Column(name = "Plave_Of_Birth", length = 50)
	private String placeOfBirth;
	
	@Column(name = "Nationality", length = 30)
	private String nationality;
	
	@Column(name = "Passport_Issued_By_Country", length = 30)
	private String passportIssuedByCountry;
	
	@Column(name = "Passport_Number", length = 15)
	private String passportNumber;
	
	@Column(name = "Passport_Expiration_Date", length = 10)
	private String passportExpirationDate;
	
	@Column(name = "Name_On_Passport", length = 55)
	private String nameOnPassport;
	
	@Column(name = "Date_Of_Last_Entry")
	private Timestamp dateOfLastEntry;
	
	@Column(name = "Place_Of_Last_Entry", length = 25)
	private String placeOfLastEntry;
	
	@Column(name = "Manner_Of_Last_Entry", length = 25)
	private String mannerOfLastEntry;
	
	@Column(name = "Visa_Expiry_Date")
	private Timestamp visaExpiryDate;
	
	@Column(name = "Visa_Extension_Applied")
	private Boolean visaExtensionApplied;
	
	@Column(name = "Place_Of_Visa_Extension", length = 25)
	private String placeOfVisaExtension;
	
	@Column(name = "Date_Of_Visa_Extension")
	private Timestamp dateOfVisaExtension;
	
	@Column(name = "Closest_Family_Member_Name", length = 50)
	private String closestFamilyMemberName;
	
	@Column(name = "Closest_Family_Member_Relation", length = 15)
	private String closestFamilyMemberRelation;
	
	@Column(name = "Current_Immigration_Status", length = 50)
	private String currentImmigrationStatus;
	
	@Column(name = "Applied_For_Permanent_Residency")
	private Boolean appliedForPermanentResidency;
	
	@Column(name = "Place_Of_Permanent_Residency_Applied", length = 40)
	private String placeOfPermanentResidencyApplied;
	
	@Column(name = "Date_Of_Permanent_Residency_Applied")
	private Timestamp dateOfPermanentResidencyApplied;
	
	@Column(name = "Divorced")
	private Boolean divorced;
	
	@Column(name = "Place_Of_Divorce", length = 40)
	private String placeOfDivorce;
	
	@Column(name = "Date_Of_Divorce")
	private Timestamp dateOfDivorce;
	
	@Column(name = "Divorced_Spouse_Name", length = 40)
	private String divorcedSpouseName;
	
	@Column(name = "Date_Of_Birth_Of_Divorced_Spouse")
	private Timestamp dateOfBirthOfDivorcedSpouse;
	
	@Column(name = "Employed")
	private Boolean employed;
	
	@Column(name = "Ever_Employed_In_US")
	private Boolean everEmployedInUS;
	
	@Column(name = "Employer_Name", length = 40)
	private String employerName;
	
	@Embedded
	@Column(name = "Employer_Address")
	private Address employerAddress;
	
	@Column(name = "Arrested")
	private Boolean arrested;
	
	@Column(name = "Reason_For_Arrest", length = 500)
	private String reasonForArrest;
	
	@Column(name = "Place_Of_Arrest", length = 40)
	private String placeOfArrest;
	
	@Column(name = "Date_Of_Arrest")
	private Timestamp dateOfArrest;
	
	@Column(name = "Deported")
	private Boolean deported;
	
	@Column(name = "Place_Of_Deportation", length = 40)
	private String placeOfDeportation;
	
	@Column(name = "Date_Of_Deportation")
	private Timestamp dateOfDeportation;
	
	@Column(name = "Emergency_Contact_Person_Name", length = 50)
	private String emergencyContactPersonName;
	
	@Column(name = "Emergency_Contact_Person_Primary_Phone", length = 15)
	private String emergencyContactPersonPhone1;
	
	@Column(name = "Emergency_Contact_Person_Secondary_Phone", length = 15)
	private String emergencyContactPersonPhone2;
	
	@Lob
	private byte[] signature;
	
	@Column(name = "Date_Of_Signature")
	private Timestamp dateOfSignature;

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getMiddleName() {
		return middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getSalutation() {
		return salutation;
	}

	public void setSalutation(String salutation) {
		this.salutation = salutation;
	}

	public Collection<Address> getAddresses() {
		return addresses;
	}

	public void setAddresses(Collection<Address> addresses) {
		this.addresses = addresses;
	}

	public String getPhone1() {
		return phone1;
	}

	public void setPhone1(String phone1) {
		this.phone1 = phone1;
	}

	public String getPhone2() {
		return phone2;
	}

	public void setPhone2(String phone2) {
		this.phone2 = phone2;
	}

	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getApplicantRelationToPrimary() {
		return applicantRelationToPrimary;
	}

	public void setApplicantRelationToPrimary(String applicantRelationToPrimary) {
		this.applicantRelationToPrimary = applicantRelationToPrimary;
	}

	public Timestamp getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Timestamp dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getPlaceOfBirth() {
		return placeOfBirth;
	}

	public void setPlaceOfBirth(String placeOfBirth) {
		this.placeOfBirth = placeOfBirth;
	}

	public String getNationality() {
		return nationality;
	}

	public void setNationality(String nationality) {
		this.nationality = nationality;
	}

	public Timestamp getDateOfLastEntry() {
		return dateOfLastEntry;
	}

	public void setDateOfLastEntry(Timestamp dateOfLastEntry) {
		this.dateOfLastEntry = dateOfLastEntry;
	}

	public String getPlaceOfLastEntry() {
		return placeOfLastEntry;
	}

	public void setPlaceOfLastEntry(String placeOfLastEntry) {
		this.placeOfLastEntry = placeOfLastEntry;
	}

	public String getMannerOfLastEntry() {
		return mannerOfLastEntry;
	}

	public void setMannerOfLastEntry(String mannerOfLastEntry) {
		this.mannerOfLastEntry = mannerOfLastEntry;
	}

	public Timestamp getVisaExpiryDate() {
		return visaExpiryDate;
	}

	public void setVisaExpiryDate(Timestamp visaExpiryDate) {
		this.visaExpiryDate = visaExpiryDate;
	}

	public Boolean getVisaExtensionApplied() {
		return visaExtensionApplied;
	}

	public void setVisaExtensionApplied(Boolean visaExtensionApplied) {
		this.visaExtensionApplied = visaExtensionApplied;
	}

	public String getPlaceOfVisaExtension() {
		return placeOfVisaExtension;
	}

	public void setPlaceOfVisaExtension(String placeOfVisaExtension) {
		this.placeOfVisaExtension = placeOfVisaExtension;
	}

	public Timestamp getDateOfVisaExtension() {
		return dateOfVisaExtension;
	}

	public void setDateOfVisaExtension(Timestamp dateOfVisaExtension) {
		this.dateOfVisaExtension = dateOfVisaExtension;
	}

	public String getClosestFamilyMemberName() {
		return closestFamilyMemberName;
	}

	public void setClosestFamilyMemberName(String closestFamilyMemberName) {
		this.closestFamilyMemberName = closestFamilyMemberName;
	}

	public String getClosestFamilyMemberRelation() {
		return closestFamilyMemberRelation;
	}

	public void setClosestFamilyMemberRelation(String closestFamilyMemberRelation) {
		this.closestFamilyMemberRelation = closestFamilyMemberRelation;
	}

	public String getCurrentImmigrationStatus() {
		return currentImmigrationStatus;
	}

	public void setCurrentImmigrationStatus(String currentImmigrationStatus) {
		this.currentImmigrationStatus = currentImmigrationStatus;
	}

	public Boolean getAppliedForPermanentResidency() {
		return appliedForPermanentResidency;
	}

	public void setAppliedForPermanentResidency(Boolean appliedForPermanentResidency) {
		this.appliedForPermanentResidency = appliedForPermanentResidency;
	}

	public String getPlaceOfPermanentResidencyApplied() {
		return placeOfPermanentResidencyApplied;
	}

	public void setPlaceOfPermanentResidencyApplied(String placeOfPermanentResidencyApplied) {
		this.placeOfPermanentResidencyApplied = placeOfPermanentResidencyApplied;
	}

	public Timestamp getDateOfPermanentResidencyApplied() {
		return dateOfPermanentResidencyApplied;
	}

	public void setDateOfPermanentResidencyApplied(Timestamp dateOfPermanentResidencyApplied) {
		this.dateOfPermanentResidencyApplied = dateOfPermanentResidencyApplied;
	}

	public Boolean getDivorced() {
		return divorced;
	}

	public void setDivorced(Boolean divorced) {
		this.divorced = divorced;
	}

	public String getPlaceOfDivorce() {
		return placeOfDivorce;
	}

	public void setPlaceOfDivorce(String placeOfDivorce) {
		this.placeOfDivorce = placeOfDivorce;
	}

	public Timestamp getDateOfDivorce() {
		return dateOfDivorce;
	}

	public void setDateOfDivorce(Timestamp dateOfDivorce) {
		this.dateOfDivorce = dateOfDivorce;
	}

	public String getDivorcedSpouseName() {
		return divorcedSpouseName;
	}

	public void setDivorcedSpouseName(String divorcedSpouseName) {
		this.divorcedSpouseName = divorcedSpouseName;
	}

	public Timestamp getDateOfBirthOfDivorcedSpouse() {
		return dateOfBirthOfDivorcedSpouse;
	}

	public void setDateOfBirthOfDivorcedSpouse(Timestamp dateOfBirthOfDivorcedSpouse) {
		this.dateOfBirthOfDivorcedSpouse = dateOfBirthOfDivorcedSpouse;
	}

	public Boolean getEmployed() {
		return employed;
	}

	public void setEmployed(Boolean employed) {
		this.employed = employed;
	}

	public Boolean getEverEmployedInUS() {
		return everEmployedInUS;
	}

	public void setEverEmployedInUS(Boolean everEmployedInUS) {
		this.everEmployedInUS = everEmployedInUS;
	}

	public String getEmployerName() {
		return employerName;
	}

	public void setEmployerName(String employerName) {
		this.employerName = employerName;
	}

	public Address getEmployerAddress() {
		return employerAddress;
	}

	public void setEmployerAddress(Address employerAddress) {
		this.employerAddress = employerAddress;
	}

	public Boolean getArrested() {
		return arrested;
	}

	public void setArrested(Boolean arrested) {
		this.arrested = arrested;
	}

	public String getReasonForArrest() {
		return reasonForArrest;
	}

	public void setReasonForArrest(String reasonForArrest) {
		this.reasonForArrest = reasonForArrest;
	}

	public String getPlaceOfArrest() {
		return placeOfArrest;
	}

	public void setPlaceOfArrest(String placeOfArrest) {
		this.placeOfArrest = placeOfArrest;
	}

	public Timestamp getDateOfArrest() {
		return dateOfArrest;
	}

	public void setDateOfArrest(Timestamp dateOfArrest) {
		this.dateOfArrest = dateOfArrest;
	}

	public Boolean getDeported() {
		return deported;
	}

	public void setDeported(Boolean deported) {
		this.deported = deported;
	}

	public String getPlaceOfDeportation() {
		return placeOfDeportation;
	}

	public void setPlaceOfDeportation(String placeOfDeportation) {
		this.placeOfDeportation = placeOfDeportation;
	}

	public Timestamp getDateOfDeportation() {
		return dateOfDeportation;
	}

	public void setDateOfDeportation(Timestamp dateOfDeportation) {
		this.dateOfDeportation = dateOfDeportation;
	}

	public String getEmergencyContactPersonName() {
		return emergencyContactPersonName;
	}

	public void setEmergencyContactPersonName(String emergencyContactPersonName) {
		this.emergencyContactPersonName = emergencyContactPersonName;
	}

	public String getEmergencyContactPersonPhone1() {
		return emergencyContactPersonPhone1;
	}

	public void setEmergencyContactPersonPhone1(String emergencyContactPersonPhone1) {
		this.emergencyContactPersonPhone1 = emergencyContactPersonPhone1;
	}

	public String getEmergencyContactPersonPhone2() {
		return emergencyContactPersonPhone2;
	}

	public void setEmergencyContactPersonPhone2(String emergencyContactPersonPhone2) {
		this.emergencyContactPersonPhone2 = emergencyContactPersonPhone2;
	}

	public byte[] getSignature() {
		return signature;
	}

	public void setSignature(byte[] signature) {
		this.signature = signature;
	}

	public Timestamp getDateOfSignature() {
		return dateOfSignature;
	}

	public void setDateOfSignature(Timestamp dateOfSignature) {
		this.dateOfSignature = dateOfSignature;
	}

	public String getPassportIssuedByCountry() {
		return passportIssuedByCountry;
	}

	public void setPassportIssuedByCountry(String passportIssuedByCountry) {
		this.passportIssuedByCountry = passportIssuedByCountry;
	}

	public String getPassportNumber() {
		return passportNumber;
	}

	public void setPassportNumber(String passportNumber) {
		this.passportNumber = passportNumber;
	}

	public String getPassportExpirationDate() {
		return passportExpirationDate;
	}

	public void setPassportExpirationDate(String passportExpirationDate) {
		this.passportExpirationDate = passportExpirationDate;
	}

	public String getNameOnPassport() {
		return nameOnPassport;
	}

	public void setNameOnPassport(String nameOnPassport) {
		this.nameOnPassport = nameOnPassport;
	}


	@Override
	public String toString() {
		return "Biographic [firstName=" + firstName + ", middleName=" + middleName + ", lastName=" + lastName
				+ ", salutation=" + salutation + ", gender=" + gender + ", applicantRelationToPrimary="
				+ applicantRelationToPrimary + ", addresses=" + addresses + ", phone1="
				+ phone1 + ", phone2=" + phone2 + ", emailAddress=" + emailAddress + ", dateOfBirth=" + dateOfBirth
				+ ", placeOfBirth=" + placeOfBirth + ", nationality=" + nationality + ", passportIssuedByCountry="
				+ passportIssuedByCountry + ", passportNumber=" + passportNumber + ", passportExpirationDate="
				+ passportExpirationDate + ", nameOnPassport=" + nameOnPassport + ", dateOfLastEntry=" + dateOfLastEntry
				+ ", placeOfLastEntry=" + placeOfLastEntry + ", mannerOfLastEntry=" + mannerOfLastEntry + ", visaExpiryDate="
				+ visaExpiryDate + ", visaExtensionApplied=" + visaExtensionApplied + ", placeOfVisaExtension="
				+ placeOfVisaExtension + ", dateOfVisaExtension=" + dateOfVisaExtension + ", closestFamilyMemberName="
				+ closestFamilyMemberName + ", closestFamilyMemberRelation=" + closestFamilyMemberRelation
				+ ", currentImmigrationStatus=" + currentImmigrationStatus + ", appliedForPermanentResidency="
				+ appliedForPermanentResidency + ", placeOfPermanentResidencyApplied="
				+ placeOfPermanentResidencyApplied + ", dateOfPermanentResidencyApplied="
				+ dateOfPermanentResidencyApplied + ", divorced=" + divorced + ", placeOfDivorce=" + placeOfDivorce
				+ ", dateOfDivorce=" + dateOfDivorce + ", divorcedSpouseName=" + divorcedSpouseName
				+ ", dateOfBirthOfDivorcedSpouse=" + dateOfBirthOfDivorcedSpouse + ", employed=" + employed
				+ ", everEmployedInUS=" + everEmployedInUS + ", employerName=" + employerName + ", employerAddress="
				+ employerAddress + ", arrested=" + arrested + ", reasonForArrest=" + reasonForArrest
				+ ", placeOfArrest=" + placeOfArrest + ", dateOfArrest=" + dateOfArrest + ", deported=" + deported
				+ ", placeOfDeportation=" + placeOfDeportation + ", dateOfDeportation=" + dateOfDeportation
				+ ", emergencyContactPersonName=" + emergencyContactPersonName + ", emergencyContactPersonPhone1="
				+ emergencyContactPersonPhone1 + ", emergencyContactPersonPhone2=" + emergencyContactPersonPhone2
				+ ", signature=" + Arrays.toString(signature) + ", dateOfSignature=" + dateOfSignature + "]";
	}
}
