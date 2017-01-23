-- Database initialization statements

-- Investment Manager Reports - 13 reports

INSERT INTO "JASPERSERVER".REPORT_MAP (JSON_CALL_NAME, ORG_TYPE, JASPER_REPORT_NAME, REPORT_TYPE) VALUES ('acctView', 'IM', 'IM_AccountView_AccountSearch_FullAccount', 'pdf');

INSERT INTO "JASPERSERVER".REPORT_MAP (JSON_CALL_NAME, ORG_TYPE, JASPER_REPORT_NAME, REPORT_TYPE) VALUES ('genAcct', 'IM', 'IM_GeneralAccount', 'xls');

INSERT INTO "JASPERSERVER".REPORT_MAP (JSON_CALL_NAME, ORG_TYPE, JASPER_REPORT_NAME, REPORT_TYPE) VALUES ('acctSI', 'IM', 'IM_AccountSIDataSearch', 'xls');

INSERT INTO "JASPERSERVER".REPORT_MAP (JSON_CALL_NAME, ORG_TYPE, JASPER_REPORT_NAME, REPORT_TYPE) VALUES ('modelSI', 'IM', 'IM_ModelSIDataSearch', 'xls');

INSERT INTO "JASPERSERVER".REPORT_MAP (JSON_CALL_NAME, ORG_TYPE, JASPER_REPORT_NAME, REPORT_TYPE) VALUES ('confDelivery', 'IM', 'IM_ConfirmDelivery', 'xls');

INSERT INTO "JASPERSERVER".REPORT_MAP (JSON_CALL_NAME, ORG_TYPE, JASPER_REPORT_NAME, REPORT_TYPE) VALUES ('brokAccess', 'IM', 'IM_BrokerAccess', 'xls');

INSERT INTO "JASPERSERVER".REPORT_MAP (JSON_CALL_NAME, ORG_TYPE, JASPER_REPORT_NAME, REPORT_TYPE) VALUES ('acctSIScan', 'IM', 'IM_AccountSIDataSearch-SIScan', 'xls');

INSERT INTO "JASPERSERVER".REPORT_MAP (JSON_CALL_NAME, ORG_TYPE, JASPER_REPORT_NAME, REPORT_TYPE) VALUES ('modelSIScan', 'IM', 'IM_ModelSIDataSearch-SIScan', 'xls');

INSERT INTO "JASPERSERVER".REPORT_MAP (JSON_CALL_NAME, ORG_TYPE, JASPER_REPORT_NAME, REPORT_TYPE) VALUES ('acctGuide', 'IM', 'IM_AccountGuideReport', 'pdf');

INSERT INTO "JASPERSERVER".REPORT_MAP (JSON_CALL_NAME, ORG_TYPE, JASPER_REPORT_NAME, REPORT_TYPE) VALUES ('auditLog', 'IM', 'IM_AuditLog', 'pdf');

INSERT INTO "JASPERSERVER".REPORT_MAP (JSON_CALL_NAME, ORG_TYPE, JASPER_REPORT_NAME, REPORT_TYPE) VALUES ('acctList', 'IM', 'IM_AccountListReport-AccountswModels', 'pdf');

INSERT INTO "JASPERSERVER".REPORT_MAP (JSON_CALL_NAME, ORG_TYPE, JASPER_REPORT_NAME, REPORT_TYPE) VALUES ('alertDtls', 'IM', 'IM_ViewAlertChanges', 'pdf');

INSERT INTO "JASPERSERVER".REPORT_MAP (JSON_CALL_NAME, ORG_TYPE, JASPER_REPORT_NAME, REPORT_TYPE) VALUES ('leDtls', 'IM', 'IM_LegalEntity', 'pdf');

----- IM Report Parameters-------------------------------------------------------------------------------------------------------------------------------------------------------------------------
INSERT INTO "JASPERSERVER".REPORT_PARAMETERS_MAP (JSON_CALL_NAME, ORG_TYPE, JASPER_PARAMETER, JSON_PARAMETER) VALUES ('acctView', 'IM', 'ACCT_VIEW_DATASOURCE', 'none');

INSERT INTO "JASPERSERVER".REPORT_PARAMETERS_MAP (JSON_CALL_NAME, ORG_TYPE, JASPER_PARAMETER, JSON_PARAMETER) VALUES ('acctView', 'IM', 'ACCT_SI_DATASOURCE', 'none');

---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
INSERT INTO "JASPERSERVER".REPORT_PARAMETERS_MAP (JSON_CALL_NAME, ORG_TYPE, JASPER_PARAMETER, JSON_PARAMETER) VALUES ('genAcct', 'IM', 'P_BROKER_ACRONYM', 'ownrAcro');

INSERT INTO "JASPERSERVER".REPORT_PARAMETERS_MAP (JSON_CALL_NAME, ORG_TYPE, JASPER_PARAMETER, JSON_PARAMETER) VALUES ('genAcct', 'IM', 'P_ACCESS_CODE', 'accessCd');

---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
INSERT INTO "JASPERSERVER".REPORT_PARAMETERS_MAP (JSON_CALL_NAME, ORG_TYPE, JASPER_PARAMETER, JSON_PARAMETER) VALUES ('acctSI', 'IM', 'P_ACCESS_CODE', 'accessCd');

INSERT INTO "JASPERSERVER".REPORT_PARAMETERS_MAP (JSON_CALL_NAME, ORG_TYPE, JASPER_PARAMETER, JSON_PARAMETER) VALUES ('acctSI', 'IM', 'P_COUNTRY', 'ctry');

INSERT INTO "JASPERSERVER".REPORT_PARAMETERS_MAP (JSON_CALL_NAME, ORG_TYPE, JASPER_PARAMETER, JSON_PARAMETER) VALUES ('acctSI', 'IM', 'P_SECURITY', 'sec');

INSERT INTO "JASPERSERVER".REPORT_PARAMETERS_MAP (JSON_CALL_NAME, ORG_TYPE, JASPER_PARAMETER, JSON_PARAMETER) VALUES ('acctSI', 'IM', 'P_METHOD', 'mthd');

---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
INSERT INTO "JASPERSERVER".REPORT_PARAMETERS_MAP (JSON_CALL_NAME, ORG_TYPE, JASPER_PARAMETER, JSON_PARAMETER) VALUES ('modelSI', 'IM', 'P_MODEL_NAME', 'mdlName');

INSERT INTO "JASPERSERVER".REPORT_PARAMETERS_MAP (JSON_CALL_NAME, ORG_TYPE, JASPER_PARAMETER, JSON_PARAMETER) VALUES ('modelSI', 'IM', 'P_COUNTRY', 'ctry');

INSERT INTO "JASPERSERVER".REPORT_PARAMETERS_MAP (JSON_CALL_NAME, ORG_TYPE, JASPER_PARAMETER, JSON_PARAMETER) VALUES ('modelSI', 'IM', 'P_SECURITY', 'sec');

INSERT INTO "JASPERSERVER".REPORT_PARAMETERS_MAP (JSON_CALL_NAME, ORG_TYPE, JASPER_PARAMETER, JSON_PARAMETER) VALUES ('modelSI', 'IM', 'P_METHOD', 'mthd');

---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
INSERT INTO "JASPERSERVER".REPORT_PARAMETERS_MAP (JSON_CALL_NAME, ORG_TYPE, JASPER_PARAMETER, JSON_PARAMETER) VALUES ('confDelivery', 'IM', 'P_ACRONYM', 'ownrAcro');

INSERT INTO "JASPERSERVER".REPORT_PARAMETERS_MAP (JSON_CALL_NAME, ORG_TYPE, JASPER_PARAMETER, JSON_PARAMETER) VALUES ('confDelivery', 'IM', 'P_ACCESS_CODE', 'accessCd');

---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
INSERT INTO "JASPERSERVER".REPORT_PARAMETERS_MAP (JSON_CALL_NAME, ORG_TYPE, JASPER_PARAMETER, JSON_PARAMETER) VALUES ('brokAccess', 'IM', 'P_ACCOUNT_ACCESS_CODE', 'accessCd');

---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
INSERT INTO "JASPERSERVER".REPORT_PARAMETERS_MAP (JSON_CALL_NAME, ORG_TYPE, JASPER_PARAMETER, JSON_PARAMETER) VALUES ('acctSIScan', 'IM', 'P_ACCESS_CODE', 'accessCd');

INSERT INTO "JASPERSERVER".REPORT_PARAMETERS_MAP (JSON_CALL_NAME, ORG_TYPE, JASPER_PARAMETER, JSON_PARAMETER) VALUES ('acctSIScan', 'IM', 'P_COUNTRY', 'ctry');

INSERT INTO "JASPERSERVER".REPORT_PARAMETERS_MAP (JSON_CALL_NAME, ORG_TYPE, JASPER_PARAMETER, JSON_PARAMETER) VALUES ('acctSIScan', 'IM', 'P_SECURITY', 'sec');

INSERT INTO "JASPERSERVER".REPORT_PARAMETERS_MAP (JSON_CALL_NAME, ORG_TYPE, JASPER_PARAMETER, JSON_PARAMETER) VALUES ('acctSIScan', 'IM', 'P_METHOD', 'mthd');

---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
INSERT INTO "JASPERSERVER".REPORT_PARAMETERS_MAP (JSON_CALL_NAME, ORG_TYPE, JASPER_PARAMETER, JSON_PARAMETER) VALUES ('modelSIScan', 'IM', 'P_MODEL_NAME', 'mdlName');

INSERT INTO "JASPERSERVER".REPORT_PARAMETERS_MAP (JSON_CALL_NAME, ORG_TYPE, JASPER_PARAMETER, JSON_PARAMETER) VALUES ('modelSIScan', 'IM', 'P_COUNTRY', 'ctry');

INSERT INTO "JASPERSERVER".REPORT_PARAMETERS_MAP (JSON_CALL_NAME, ORG_TYPE, JASPER_PARAMETER, JSON_PARAMETER) VALUES ('modelSIScan', 'IM', 'P_SECURITY', 'sec');

INSERT INTO "JASPERSERVER".REPORT_PARAMETERS_MAP (JSON_CALL_NAME, ORG_TYPE, JASPER_PARAMETER, JSON_PARAMETER) VALUES ('modelSIScan', 'IM', 'P_METHOD', 'mthd');

---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
INSERT INTO "JASPERSERVER".REPORT_PARAMETERS_MAP (JSON_CALL_NAME, ORG_TYPE, JASPER_PARAMETER, JSON_PARAMETER) VALUES ('acctGuide', 'IM', 'P_REPORT_NAME', 'reportName');

INSERT INTO "JASPERSERVER".REPORT_PARAMETERS_MAP (JSON_CALL_NAME, ORG_TYPE, JASPER_PARAMETER, JSON_PARAMETER) VALUES ('acctGuide', 'IM', 'P_COUNTRY', 'ctry');

INSERT INTO "JASPERSERVER".REPORT_PARAMETERS_MAP (JSON_CALL_NAME, ORG_TYPE, JASPER_PARAMETER, JSON_PARAMETER) VALUES ('acctGuide', 'IM', 'P_SECURITY', 'sec');

INSERT INTO "JASPERSERVER".REPORT_PARAMETERS_MAP (JSON_CALL_NAME, ORG_TYPE, JASPER_PARAMETER, JSON_PARAMETER) VALUES ('acctGuide', 'IM', 'P_BROKER_ACRONYM', 'brokAcro');

---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
INSERT INTO "JASPERSERVER".REPORT_PARAMETERS_MAP (JSON_CALL_NAME, ORG_TYPE, JASPER_PARAMETER, JSON_PARAMETER) VALUES ('acctList', 'IM', 'P_ACCESS_CODE', 'accessCd');

INSERT INTO "JASPERSERVER".REPORT_PARAMETERS_MAP (JSON_CALL_NAME, ORG_TYPE, JASPER_PARAMETER, JSON_PARAMETER) VALUES ('acctList', 'IM', 'P_ACRONYM', 'ownrAcro');


-- Broker / Dealer Reports - 12 Reports -----------------------------------------------------------------------------------------------------------------------------------------------------------

INSERT INTO "JASPERSERVER".REPORT_MAP (JSON_CALL_NAME, ORG_TYPE, JASPER_REPORT_NAME, REPORT_TYPE) VALUES ('genAcct', 'BD', 'BD_GeneralAccount', 'xls');

INSERT INTO "JASPERSERVER".REPORT_MAP (JSON_CALL_NAME, ORG_TYPE, JASPER_REPORT_NAME, REPORT_TYPE) VALUES ('acctSI', 'BD', 'BD_AccountSIDataSearch', 'xls');

INSERT INTO "JASPERSERVER".REPORT_MAP (JSON_CALL_NAME, ORG_TYPE, JASPER_REPORT_NAME, REPORT_TYPE) VALUES ('modelSI', 'BD', 'BD_ModelSIDataSearch', 'xls');

INSERT INTO "JASPERSERVER".REPORT_MAP (JSON_CALL_NAME, ORG_TYPE, JASPER_REPORT_NAME, REPORT_TYPE) VALUES ('confDelivery', 'BD', 'BD_ConfirmDelivery', 'xls');

INSERT INTO "JASPERSERVER".REPORT_MAP (JSON_CALL_NAME, ORG_TYPE, JASPER_REPORT_NAME, REPORT_TYPE) VALUES ('modelSIScan', 'BD', 'BD_ModelSIDataSearch-SIScan', 'xls');

INSERT INTO "JASPERSERVER".REPORT_MAP (JSON_CALL_NAME, ORG_TYPE, JASPER_REPORT_NAME, REPORT_TYPE) VALUES ('acctGuide', 'BD', 'BD_AccountGuideReport', 'pdf');

INSERT INTO "JASPERSERVER".REPORT_MAP (JSON_CALL_NAME, ORG_TYPE, JASPER_REPORT_NAME, REPORT_TYPE) VALUES ('instSummary', 'BD', 'BD_InstitutionSummaryReport', 'pdf');

INSERT INTO "JASPERSERVER".REPORT_MAP (JSON_CALL_NAME, ORG_TYPE, JASPER_REPORT_NAME, REPORT_TYPE) VALUES ('auditLog', 'BD', 'BD_AuditLog', 'pdf');

INSERT INTO "JASPERSERVER".REPORT_MAP (JSON_CALL_NAME, ORG_TYPE, JASPER_REPORT_NAME, REPORT_TYPE) VALUES ('acctList', 'BD', 'BD_AccountListReport', 'pdf');

INSERT INTO "JASPERSERVER".REPORT_MAP (JSON_CALL_NAME, ORG_TYPE, JASPER_REPORT_NAME, REPORT_TYPE) VALUES ('biaDtls', 'BD', 'BD_AccountSearchByBIA', 'pdf');

INSERT INTO "JASPERSERVER".REPORT_MAP (JSON_CALL_NAME, ORG_TYPE, JASPER_REPORT_NAME, REPORT_TYPE) VALUES ('alertDtls', 'BD', 'BD_ViewAlertChanges', 'pdf');

INSERT INTO "JASPERSERVER".REPORT_MAP (JSON_CALL_NAME, ORG_TYPE, JASPER_REPORT_NAME, REPORT_TYPE) VALUES ('leDtls', 'BD', 'BD_LegalEntity', 'pdf');


----- BD Report Parameters-------------------------------------------------------------------------------------------------------------------------------------------------------------------------
INSERT INTO "JASPERSERVER".REPORT_PARAMETERS_MAP (JSON_CALL_NAME, ORG_TYPE, JASPER_PARAMETER, JSON_PARAMETER) VALUES ('genAcct', 'BD', 'P_ACRONYM', 'ownrAcro');

INSERT INTO "JASPERSERVER".REPORT_PARAMETERS_MAP (JSON_CALL_NAME, ORG_TYPE, JASPER_PARAMETER, JSON_PARAMETER) VALUES ('genAcct', 'BD', 'P_ACCESS_CODE', 'accessCd');

---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
INSERT INTO "JASPERSERVER".REPORT_PARAMETERS_MAP (JSON_CALL_NAME, ORG_TYPE, JASPER_PARAMETER, JSON_PARAMETER) VALUES ('acctSI', 'BD', 'P_ACCESS_CODE', 'accessCd');

INSERT INTO "JASPERSERVER".REPORT_PARAMETERS_MAP (JSON_CALL_NAME, ORG_TYPE, JASPER_PARAMETER, JSON_PARAMETER) VALUES ('acctSI', 'BD', 'P_COUNTRY', 'ctry');

INSERT INTO "JASPERSERVER".REPORT_PARAMETERS_MAP (JSON_CALL_NAME, ORG_TYPE, JASPER_PARAMETER, JSON_PARAMETER) VALUES ('acctSI', 'BD', 'P_SECURITY', 'sec');

INSERT INTO "JASPERSERVER".REPORT_PARAMETERS_MAP (JSON_CALL_NAME, ORG_TYPE, JASPER_PARAMETER, JSON_PARAMETER) VALUES ('acctSI', 'BD', 'P_METHOD', 'mthd');

---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
INSERT INTO "JASPERSERVER".REPORT_PARAMETERS_MAP (JSON_CALL_NAME, ORG_TYPE, JASPER_PARAMETER, JSON_PARAMETER) VALUES ('modelSI', 'BD', 'P_MODEL_NAME', 'mdlName');

INSERT INTO "JASPERSERVER".REPORT_PARAMETERS_MAP (JSON_CALL_NAME, ORG_TYPE, JASPER_PARAMETER, JSON_PARAMETER) VALUES ('modelSI', 'BD', 'P_COUNTRY', 'ctry');

INSERT INTO "JASPERSERVER".REPORT_PARAMETERS_MAP (JSON_CALL_NAME, ORG_TYPE, JASPER_PARAMETER, JSON_PARAMETER) VALUES ('modelSI', 'BD', 'P_SECURITY', 'sec');

INSERT INTO "JASPERSERVER".REPORT_PARAMETERS_MAP (JSON_CALL_NAME, ORG_TYPE, JASPER_PARAMETER, JSON_PARAMETER) VALUES ('modelSI', 'BD', 'P_METHOD', 'mthd');

---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
INSERT INTO "JASPERSERVER".REPORT_PARAMETERS_MAP (JSON_CALL_NAME, ORG_TYPE, JASPER_PARAMETER, JSON_PARAMETER) VALUES ('confDelivery', 'BD', 'P_ACCESS_CODE', 'accessCd');

---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
INSERT INTO "JASPERSERVER".REPORT_PARAMETERS_MAP (JSON_CALL_NAME, ORG_TYPE, JASPER_PARAMETER, JSON_PARAMETER) VALUES ('modelSIScan', 'BD', 'P_MODEL_NAME', 'mdlName');

INSERT INTO "JASPERSERVER".REPORT_PARAMETERS_MAP (JSON_CALL_NAME, ORG_TYPE, JASPER_PARAMETER, JSON_PARAMETER) VALUES ('modelSIScan', 'BD', 'P_COUNTRY', 'ctry');

INSERT INTO "JASPERSERVER".REPORT_PARAMETERS_MAP (JSON_CALL_NAME, ORG_TYPE, JASPER_PARAMETER, JSON_PARAMETER) VALUES ('modelSIScan', 'BD', 'P_SECURITY', 'sec');

INSERT INTO "JASPERSERVER".REPORT_PARAMETERS_MAP (JSON_CALL_NAME, ORG_TYPE, JASPER_PARAMETER, JSON_PARAMETER) VALUES ('modelSIScan', 'BD', 'P_METHOD', 'mthd');

---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
INSERT INTO "JASPERSERVER".REPORT_PARAMETERS_MAP (JSON_CALL_NAME, ORG_TYPE, JASPER_PARAMETER, JSON_PARAMETER) VALUES ('acctGuide', 'BD', 'P_ACRONYM', 'instAcro');

INSERT INTO "JASPERSERVER".REPORT_PARAMETERS_MAP (JSON_CALL_NAME, ORG_TYPE, JASPER_PARAMETER, JSON_PARAMETER) VALUES ('acctGuide', 'BD', 'P_ACRONYM_LIST', 'brokAcro');

INSERT INTO "JASPERSERVER".REPORT_PARAMETERS_MAP (JSON_CALL_NAME, ORG_TYPE, JASPER_PARAMETER, JSON_PARAMETER) VALUES ('acctGuide', 'BD', 'P_ACCESS_CODE', 'accessCd');

INSERT INTO "JASPERSERVER".REPORT_PARAMETERS_MAP (JSON_CALL_NAME, ORG_TYPE, JASPER_PARAMETER, JSON_PARAMETER) VALUES ('acctGuide', 'BD', 'P_COUNTRY', 'ctry');

INSERT INTO "JASPERSERVER".REPORT_PARAMETERS_MAP (JSON_CALL_NAME, ORG_TYPE, JASPER_PARAMETER, JSON_PARAMETER) VALUES ('acctGuide', 'BD', 'P_SECURITY', 'sec');

---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
INSERT INTO "JASPERSERVER".REPORT_PARAMETERS_MAP (JSON_CALL_NAME, ORG_TYPE, JASPER_PARAMETER, JSON_PARAMETER) VALUES ('biaDtls', 'BD', 'P_BIA', 'bia');

INSERT INTO "JASPERSERVER".REPORT_PARAMETERS_MAP (JSON_CALL_NAME, ORG_TYPE, JASPER_PARAMETER, JSON_PARAMETER) VALUES ('biaDtls', 'BD', 'P_BIA_LIST', 'bia');

INSERT INTO "JASPERSERVER".REPORT_PARAMETERS_MAP (JSON_CALL_NAME, ORG_TYPE, JASPER_PARAMETER, JSON_PARAMETER) VALUES ('biaDtls', 'BD', 'P_COUNTRY', 'ctry');

INSERT INTO "JASPERSERVER".REPORT_PARAMETERS_MAP (JSON_CALL_NAME, ORG_TYPE, JASPER_PARAMETER, JSON_PARAMETER) VALUES ('biaDtls', 'BD', 'P_SECURITY', 'sec');

---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------


-- Global Custodian Reports - 9 Reports

INSERT INTO "JASPERSERVER".REPORT_MAP (JSON_CALL_NAME, ORG_TYPE, JASPER_REPORT_NAME, REPORT_TYPE) VALUES ('acctView', 'GC', 'GC_AccountView_AccountSearch_FullAccount', 'pdf');

INSERT INTO "JASPERSERVER".REPORT_MAP (JSON_CALL_NAME, ORG_TYPE, JASPER_REPORT_NAME, REPORT_TYPE) VALUES ('genAcct', 'GC', 'GC_GeneralAccount', 'xls');

INSERT INTO "JASPERSERVER".REPORT_MAP (JSON_CALL_NAME, ORG_TYPE, JASPER_REPORT_NAME, REPORT_TYPE) VALUES ('acctSI', 'GC', 'GC_AccountSIDataSearch', 'xls');

INSERT INTO "JASPERSERVER".REPORT_MAP (JSON_CALL_NAME, ORG_TYPE, JASPER_REPORT_NAME, REPORT_TYPE) VALUES ('modelSI', 'GC', 'GC_ModelSIDataSearch', 'xls');

INSERT INTO "JASPERSERVER".REPORT_MAP (JSON_CALL_NAME, ORG_TYPE, JASPER_REPORT_NAME, REPORT_TYPE) VALUES ('acctSIScan', 'GC', 'GC_AccountSIDataSearch-SIScan', 'xls');

INSERT INTO "JASPERSERVER".REPORT_MAP (JSON_CALL_NAME, ORG_TYPE, JASPER_REPORT_NAME, REPORT_TYPE) VALUES ('modelSIScan', 'GC', 'GC_ModelSIDataSearch-SIScan', 'xls');

INSERT INTO "JASPERSERVER".REPORT_MAP (JSON_CALL_NAME, ORG_TYPE, JASPER_REPORT_NAME, REPORT_TYPE) VALUES ('clientData', 'GC', 'GC_ClientDataReport', '');

INSERT INTO "JASPERSERVER".REPORT_MAP (JSON_CALL_NAME, ORG_TYPE, JASPER_REPORT_NAME, REPORT_TYPE) VALUES ('auditLog', 'GC', 'GC_AuditLog', 'pdf');

INSERT INTO "JASPERSERVER".REPORT_MAP (JSON_CALL_NAME, ORG_TYPE, JASPER_REPORT_NAME, REPORT_TYPE) VALUES ('alertDtls', 'GC', 'GC_ViewAlertChanges', 'pdf');

----- GC Report Parameters-------------------------------------------------------------------------------------------------------------------------------------------------------------------------
INSERT INTO "JASPERSERVER".REPORT_PARAMETERS_MAP (JSON_CALL_NAME, ORG_TYPE, JASPER_PARAMETER, JSON_PARAMETER) VALUES ('acctView', 'GC', 'ACCT_VIEW_DATASOURCE', 'none');

INSERT INTO "JASPERSERVER".REPORT_PARAMETERS_MAP (JSON_CALL_NAME, ORG_TYPE, JASPER_PARAMETER, JSON_PARAMETER) VALUES ('acctView', 'GC', 'ACCT_SI_DATASOURCE', 'none');

---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
INSERT INTO "JASPERSERVER".REPORT_PARAMETERS_MAP (JSON_CALL_NAME, ORG_TYPE, JASPER_PARAMETER, JSON_PARAMETER) VALUES ('genAcct', 'GC', 'P_ACRONYM', 'ownrAcro');

INSERT INTO "JASPERSERVER".REPORT_PARAMETERS_MAP (JSON_CALL_NAME, ORG_TYPE, JASPER_PARAMETER, JSON_PARAMETER) VALUES ('genAcct', 'GC', 'P_ACCESS_CODE', 'accessCd');

---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
INSERT INTO "JASPERSERVER".REPORT_PARAMETERS_MAP (JSON_CALL_NAME, ORG_TYPE, JASPER_PARAMETER, JSON_PARAMETER) VALUES ('acctSI', 'GC', 'P_ACCESS_CODE', 'accessCd');

INSERT INTO "JASPERSERVER".REPORT_PARAMETERS_MAP (JSON_CALL_NAME, ORG_TYPE, JASPER_PARAMETER, JSON_PARAMETER) VALUES ('acctSI', 'GC', 'P_COUNTRY', 'ctry');

INSERT INTO "JASPERSERVER".REPORT_PARAMETERS_MAP (JSON_CALL_NAME, ORG_TYPE, JASPER_PARAMETER, JSON_PARAMETER) VALUES ('acctSI', 'GC', 'P_SECURITY', 'sec');

INSERT INTO "JASPERSERVER".REPORT_PARAMETERS_MAP (JSON_CALL_NAME, ORG_TYPE, JASPER_PARAMETER, JSON_PARAMETER) VALUES ('acctSI', 'GC', 'P_METHOD', 'mthd');

---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
INSERT INTO "JASPERSERVER".REPORT_PARAMETERS_MAP (JSON_CALL_NAME, ORG_TYPE, JASPER_PARAMETER, JSON_PARAMETER) VALUES ('modelSI', 'GC', 'P_MODEL_NAME', 'mdlName');

INSERT INTO "JASPERSERVER".REPORT_PARAMETERS_MAP (JSON_CALL_NAME, ORG_TYPE, JASPER_PARAMETER, JSON_PARAMETER) VALUES ('modelSI', 'GC', 'P_COUNTRY', 'ctry');

INSERT INTO "JASPERSERVER".REPORT_PARAMETERS_MAP (JSON_CALL_NAME, ORG_TYPE, JASPER_PARAMETER, JSON_PARAMETER) VALUES ('modelSI', 'GC', 'P_SECURITY', 'sec');

INSERT INTO "JASPERSERVER".REPORT_PARAMETERS_MAP (JSON_CALL_NAME, ORG_TYPE, JASPER_PARAMETER, JSON_PARAMETER) VALUES ('modelSI', 'GC', 'P_METHOD', 'mthd');

---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
INSERT INTO "JASPERSERVER".REPORT_PARAMETERS_MAP (JSON_CALL_NAME, ORG_TYPE, JASPER_PARAMETER, JSON_PARAMETER) VALUES ('acctSIScan', 'GC', 'P_ACCESS_CODE', 'accessCd');

INSERT INTO "JASPERSERVER".REPORT_PARAMETERS_MAP (JSON_CALL_NAME, ORG_TYPE, JASPER_PARAMETER, JSON_PARAMETER) VALUES ('acctSIScan', 'GC', 'P_COUNTRY', 'ctry');

INSERT INTO "JASPERSERVER".REPORT_PARAMETERS_MAP (JSON_CALL_NAME, ORG_TYPE, JASPER_PARAMETER, JSON_PARAMETER) VALUES ('acctSIScan', 'GC', 'P_SECURITY', 'sec');

INSERT INTO "JASPERSERVER".REPORT_PARAMETERS_MAP (JSON_CALL_NAME, ORG_TYPE, JASPER_PARAMETER, JSON_PARAMETER) VALUES ('acctSIScan', 'GC', 'P_METHOD', 'mthd');

---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
INSERT INTO "JASPERSERVER".REPORT_PARAMETERS_MAP (JSON_CALL_NAME, ORG_TYPE, JASPER_PARAMETER, JSON_PARAMETER) VALUES ('modelSIScan', 'GC', 'P_MODEL_NAME', 'mdlName');

INSERT INTO "JASPERSERVER".REPORT_PARAMETERS_MAP (JSON_CALL_NAME, ORG_TYPE, JASPER_PARAMETER, JSON_PARAMETER) VALUES ('modelSIScan', 'GC', 'P_COUNTRY', 'ctry');

INSERT INTO "JASPERSERVER".REPORT_PARAMETERS_MAP (JSON_CALL_NAME, ORG_TYPE, JASPER_PARAMETER, JSON_PARAMETER) VALUES ('modelSIScan', 'GC', 'P_SECURITY', 'sec');

INSERT INTO "JASPERSERVER".REPORT_PARAMETERS_MAP (JSON_CALL_NAME, ORG_TYPE, JASPER_PARAMETER, JSON_PARAMETER) VALUES ('modelSIScan', 'GC', 'P_METHOD', 'mthd');

---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------



-- QDI / Dashboard Reports - 9 Reports

INSERT INTO "JASPERSERVER".REPORT_MAP (JSON_CALL_NAME, ORG_TYPE, JASPER_REPORT_NAME, REPORT_TYPE) VALUES ('genAcct', 'QDI', 'QDI_GeneralAccount', 'xls');

INSERT INTO "JASPERSERVER".REPORT_MAP (JSON_CALL_NAME, ORG_TYPE, JASPER_REPORT_NAME, REPORT_TYPE) VALUES ('acctSI', 'QDI', 'QDI_AccountSIDataSearch', 'xls');

INSERT INTO "JASPERSERVER".REPORT_MAP (JSON_CALL_NAME, ORG_TYPE, JASPER_REPORT_NAME, REPORT_TYPE) VALUES ('modelSI', 'QDI', 'QDI_ModelSIDataSearch', 'xls');

INSERT INTO "JASPERSERVER".REPORT_MAP (JSON_CALL_NAME, ORG_TYPE, JASPER_REPORT_NAME, REPORT_TYPE) VALUES ('confDelivery', 'QDI', 'QDI_ConfirmDelivery', 'xls');

INSERT INTO "JASPERSERVER".REPORT_MAP (JSON_CALL_NAME, ORG_TYPE, JASPER_REPORT_NAME, REPORT_TYPE) VALUES ('brokAccess', 'QDI', 'QDI_BrokerAccess', 'xls');

INSERT INTO "JASPERSERVER".REPORT_MAP (JSON_CALL_NAME, ORG_TYPE, JASPER_REPORT_NAME, REPORT_TYPE) VALUES ('acctSIScan', 'QDI', 'QDI_AccountSIDataSearch-SIScan', 'xls');

INSERT INTO "JASPERSERVER".REPORT_MAP (JSON_CALL_NAME, ORG_TYPE, JASPER_REPORT_NAME, REPORT_TYPE) VALUES ('modelSIScan', 'QDI', 'QDI_ModelSIDataSearch-SIScan', 'xls');

INSERT INTO "JASPERSERVER".REPORT_MAP (JSON_CALL_NAME, ORG_TYPE, JASPER_REPORT_NAME, REPORT_TYPE) VALUES ('biaDtls', 'QDI', 'QDI_AccountSearchByBIA', 'xls');

INSERT INTO "JASPERSERVER".REPORT_MAP (JSON_CALL_NAME, ORG_TYPE, JASPER_REPORT_NAME, REPORT_TYPE) VALUES ('auditLog', 'QDI', 'QDI_AuditLog', 'pdf');


----- QDI Report Parameters-------------------------------------------------------------------------------------------------------------------------------------------------------------------------
INSERT INTO "JASPERSERVER".REPORT_PARAMETERS_MAP (JSON_CALL_NAME, ORG_TYPE, JASPER_PARAMETER, JSON_PARAMETER) VALUES ('genAcct', 'QDI', 'P_ACRONYM', 'ownrAcro');

INSERT INTO "JASPERSERVER".REPORT_PARAMETERS_MAP (JSON_CALL_NAME, ORG_TYPE, JASPER_PARAMETER, JSON_PARAMETER) VALUES ('genAcct', 'QDI', 'P_ACCESS_CODE', 'accessCd');

---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
INSERT INTO "JASPERSERVER".REPORT_PARAMETERS_MAP (JSON_CALL_NAME, ORG_TYPE, JASPER_PARAMETER, JSON_PARAMETER) VALUES ('acctSI', 'QDI', 'P_ACCESS_CODE', 'accessCd');

INSERT INTO "JASPERSERVER".REPORT_PARAMETERS_MAP (JSON_CALL_NAME, ORG_TYPE, JASPER_PARAMETER, JSON_PARAMETER) VALUES ('acctSI', 'QDI', 'P_COUNTRY', 'ctry');

INSERT INTO "JASPERSERVER".REPORT_PARAMETERS_MAP (JSON_CALL_NAME, ORG_TYPE, JASPER_PARAMETER, JSON_PARAMETER) VALUES ('acctSI', 'QDI', 'P_SECURITY', 'sec');

INSERT INTO "JASPERSERVER".REPORT_PARAMETERS_MAP (JSON_CALL_NAME, ORG_TYPE, JASPER_PARAMETER, JSON_PARAMETER) VALUES ('acctSI', 'QDI', 'P_METHOD', 'mthd');

---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
INSERT INTO "JASPERSERVER".REPORT_PARAMETERS_MAP (JSON_CALL_NAME, ORG_TYPE, JASPER_PARAMETER, JSON_PARAMETER) VALUES ('modelSI', 'QDI', 'P_MODEL_NAME', 'mdlName');

INSERT INTO "JASPERSERVER".REPORT_PARAMETERS_MAP (JSON_CALL_NAME, ORG_TYPE, JASPER_PARAMETER, JSON_PARAMETER) VALUES ('modelSI', 'QDI', 'P_COUNTRY', 'ctry');

INSERT INTO "JASPERSERVER".REPORT_PARAMETERS_MAP (JSON_CALL_NAME, ORG_TYPE, JASPER_PARAMETER, JSON_PARAMETER) VALUES ('modelSI', 'QDI', 'P_SECURITY', 'sec');

INSERT INTO "JASPERSERVER".REPORT_PARAMETERS_MAP (JSON_CALL_NAME, ORG_TYPE, JASPER_PARAMETER, JSON_PARAMETER) VALUES ('modelSI', 'QDI', 'P_METHOD', 'mthd');

---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
INSERT INTO "JASPERSERVER".REPORT_PARAMETERS_MAP (JSON_CALL_NAME, ORG_TYPE, JASPER_PARAMETER, JSON_PARAMETER) VALUES ('confDelivery', 'QDI', 'P_ACCESS_CODE', 'accessCd');

---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
INSERT INTO "JASPERSERVER".REPORT_PARAMETERS_MAP (JSON_CALL_NAME, ORG_TYPE, JASPER_PARAMETER, JSON_PARAMETER) VALUES ('brokAccess', 'QDI', 'P_ACCESS_CODE', 'accessCd');

---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
INSERT INTO "JASPERSERVER".REPORT_PARAMETERS_MAP (JSON_CALL_NAME, ORG_TYPE, JASPER_PARAMETER, JSON_PARAMETER) VALUES ('acctSIScan', 'QDI', 'P_ACCESS_CODE', 'accessCd');

INSERT INTO "JASPERSERVER".REPORT_PARAMETERS_MAP (JSON_CALL_NAME, ORG_TYPE, JASPER_PARAMETER, JSON_PARAMETER) VALUES ('acctSIScan', 'QDI', 'P_COUNTRY', 'ctry');

INSERT INTO "JASPERSERVER".REPORT_PARAMETERS_MAP (JSON_CALL_NAME, ORG_TYPE, JASPER_PARAMETER, JSON_PARAMETER) VALUES ('acctSIScan', 'QDI', 'P_SECURITY', 'sec');

INSERT INTO "JASPERSERVER".REPORT_PARAMETERS_MAP (JSON_CALL_NAME, ORG_TYPE, JASPER_PARAMETER, JSON_PARAMETER) VALUES ('acctSIScan', 'QDI', 'P_METHOD', 'mthd');

---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
INSERT INTO "JASPERSERVER".REPORT_PARAMETERS_MAP (JSON_CALL_NAME, ORG_TYPE, JASPER_PARAMETER, JSON_PARAMETER) VALUES ('modelSIScan', 'QDI', 'P_MODEL_NAME', 'mdlName');

INSERT INTO "JASPERSERVER".REPORT_PARAMETERS_MAP (JSON_CALL_NAME, ORG_TYPE, JASPER_PARAMETER, JSON_PARAMETER) VALUES ('modelSIScan', 'QDI', 'P_COUNTRY', 'ctry');

INSERT INTO "JASPERSERVER".REPORT_PARAMETERS_MAP (JSON_CALL_NAME, ORG_TYPE, JASPER_PARAMETER, JSON_PARAMETER) VALUES ('modelSIScan', 'QDI', 'P_SECURITY', 'sec');

INSERT INTO "JASPERSERVER".REPORT_PARAMETERS_MAP (JSON_CALL_NAME, ORG_TYPE, JASPER_PARAMETER, JSON_PARAMETER) VALUES ('modelSIScan', 'QDI', 'P_METHOD', 'mthd');

---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
INSERT INTO "JASPERSERVER".REPORT_PARAMETERS_MAP (JSON_CALL_NAME, ORG_TYPE, JASPER_PARAMETER, JSON_PARAMETER) VALUES ('biaDtls', 'BD', 'P_BIA', 'bia');

INSERT INTO "JASPERSERVER".REPORT_PARAMETERS_MAP (JSON_CALL_NAME, ORG_TYPE, JASPER_PARAMETER, JSON_PARAMETER) VALUES ('biaDtls', 'BD', 'P_ACRONYM', 'ownrAcro');

INSERT INTO "JASPERSERVER".REPORT_PARAMETERS_MAP (JSON_CALL_NAME, ORG_TYPE, JASPER_PARAMETER, JSON_PARAMETER) VALUES ('biaDtls', 'BD', 'P_SECURITY', 'sec');

INSERT INTO "JASPERSERVER".REPORT_PARAMETERS_MAP (JSON_CALL_NAME, ORG_TYPE, JASPER_PARAMETER, JSON_PARAMETER) VALUES ('biaDtls', 'BD', 'P_METHOD', 'mthd');
---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

