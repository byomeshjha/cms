CMS - Case Management System

1. Download and unzip wildfly-10.0.0.Final.zip from http://wildfly.org/downloads/
2. Download and install postgrsql from https://www.postgresql.org/download/windows/
3. Download and install eclipse (MARS preferred) from eclipse.org
4. Download and install JDK8 from Oracle
5. Download and install Maven (apache-maven-3.3.3) or higher from Apache
6. Execute ">mvn clean install" from the command line
7. Create eclipse project by executing command ">mvn eclipse:eclipse"
8. Make sure you have the correct "Facet" selected in eclipse. (Should be faceted project)
9. Make sure "Dynamic Web Module" version 3 is selected facet
10. Make sure java version 1.8 is selected in facet
11. Copy the war file from target folder and put it in standalone folder of wildfly for execution outside eclipse.
12. Create the wildfly server in eclipse
