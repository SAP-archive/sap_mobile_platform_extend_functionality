![](https://img.shields.io/badge/STATUS-NOT%20CURRENTLY%20MAINTAINED-red.svg?longCache=true&style=flat)

# Important Notice
This public repository is read-only and no longer maintained. For the latest sample code repositories, visit the [SAP Samples](https://github.com/SAP-samples) organization.

Readme of How to use SMP3 sample code
SCN: http://scn.sap.com/docs/DOC-61703

The developer from the customer may want to be able to build server extensions based on sample code receive from SAP. This code must run out of the box w/o any dependencies on SAP internal build infrastructure.The code include 4 samples, Dispatcher Integration (Servlet / Controller / Filter),Persistence Integration, Properties/ConfigAdmin Integration, and P2 artifacts.

## Steps:
1. Download sample source code 
			https://github.com/SAP/sap_mobile_platform_extend_functionality        
2. Prerequisites
            2.1 maven/maven repository, customer should have their own repositories. 
            For example in my test, I mapped central Repository http://central.maven.org/maven2/, spring bundles are required too. 
            Spring OSGI Bundle Repository, e.g. http://repository.opencastproject.org/nexus/content/repositories/public/
            P2 unzip repository e.g. http://download.eclipse.org/virgo/updatesite/3.6.2.RELEASE/
            
2.2 SMP3 installed(tested in SMP 3.0.5)
            Customer can uploaded dependencies to their repository incase missing maven artifacts.
            
            Usage: java -jar bundles/com.sybase365.mobiliser.dist.tools.jar
            <DEPENDENCY META INFORMATION FILE>, e.g. ./dependencies.properties
            <DIST DIRECTORY>, e.g. ../
            <NEXUS URL>, e.g. http://localhost/nexus
            <REPOSITORY NAME>, e.g. repo
            <USERNAME>, e.g. admin
            <PASSWORD>, e.g. password
            <put|rest> for using the PUT or REST Nexus interface, defaults to REST (which auto-generates POM files too)
            e.g. open command line, go to C:\smp3\extras\dependencies, run below command
            java -jar com.sybase365.mobiliser.dist.tools-5.4.1.RC1.jar c:\sample\dependencies.properties C:\smp3\plugins http://usphlwssc247.amer.global.corp.sap:8081/nexus smp3_customer admin admin123
            
3. Customize and build Samples Source Code
            3.1 Build each samples separately
			Build sample.parent first, customer can customize the code as need.
            3.2 Build p2 repository
            Open command line, go to update-site folder, build features and repository, the current samples include 4 sample modules(servlet/filter/properties/jpa), if some of them are not needed to customize, customer can comment the submodule in file update-site\repository\pom.xml, and comment the features in update-site\repository\category.xml
 
4. Install
            4.1 go to SMP ADMIN UI->SETTINGS -> REPOSITORIES, add new repository.
            e.g. Unzip the code to c:/sample, then generate p2 repository to c:/sample/update-site/repository/target/repository, input file:/c:/sample/update-site/repository/target/repository to textbox in adminUI, save it. 
            4.2 go to SMP ADMIN UI->SETTINGS -> FEATURES&COMPONENTS, enable features added in previous step.
 
5. How to verify if it is take effect or not
            5.1 JPA 
            Url:
            http://localhost:8080/com.sap.mobile.platform.server.sample.customization.persistence.jpa.wab/? 
            5.2 Properties 
            Output in console:
            The full URL is: https://www.yahoo.com:443/altsearch 
            5.3 Servlet
            http://localhost:8080/NoSlash
            Output in browser:
            Service processed by NoSlash at URL http://localhost:8080/NoSlash 
            5.4 Filters
            If you open url http://localhost:8080/public/abc in browser, output in SMP console:

            noauthfilter forwarding /public/abc to /abc
