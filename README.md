# RestAssured API Testing Automation Framework
This project has a RestAssured based API testing framework, it uses RestAssured -API testing library, Junit or TestNG  and Cucumber - for Test Scenario writing and Running tests and for Cucumber Html Reporting, Extent Reports (library for interactive and detailed reports for tests). This framework can be used for any Restful application to create API tests.

# Contents
* [Framework Details](#FrameworkDetails)
    * [Framework - What and Why?](#ww)
    * [Project structure](#structure)
    * [Properties](#properties)
* [Packages](#package)
    * [Reports](#reports)
* [Installation](#install)
    * [Steps to follow to setup API automation in local system](#steps)
* [How to Run BDD test Scenarios](#testRun)

# Framework Details<a name="FrameworkDetails"></a>

#### Framework - What and Why?<a name="ww"></a>
> For any software requirement, certain common and basic tasks need to be performed. Such tasks would have been already solved and would be available as open-source/free projects. One can use that and build their code on top of it to solve the software requirements specific to them. Such base code is called a framework

#### Project structure<a name="structure"></a>
> This project uses a standard Maven Java project with standard java folder structure and POM.xml

#### Properties<a name="properties"></a>
> `src/test/resources/constants.properties` is a simple constants properties file to store various constants like application URL, DB & SSH details.


#### Reports<a name="reports"></a>
> Using Cucumber library generating the Cucumber Html report, we can create interactive and detailed reports for our API test results. We can add events, tags, devices, authors or any other relevant information we decide is essential to create an informative and stunning report. Test Reports can be found in `workingDir/ExtentReports/ExtentReportResults.html`


### 1.com.restfulbooker.apitest.actions
#### HttpOperation.java
> Its a Java Enum type, which is implemented to have a set of different HttpOperation constants. Which can be used to create, read, update, and delete (or CRUD) operations, respectively.

#### ValidatorOperation.java
> Its a Java Enum type, which is implemented to have a set of different ValidatorOperation constants. Which can be used in response json assertions.

### 2.com.restfulbooker.apitest.interfaces
#### IApi.java
> It contains all the methods needed to write API tests and provides basic assertion APIs. It is the blueprint of the `API.java` class.

### 3.com.restfulbooker.apitest.listeners

#### ExtentManager Class:
> In this class, we created an `ExtentReports` object and it can be reachable via `getReporter()` method. Also, we need to set ExtentReports report HTML file location.



### 4.com.restfulbooker.apitest.restassuredFunctions
#### API.java
> The `API.java` class which implements the IApi interface defines complete functionality for the methods declared in the `IApi interface`.

### 5.com.restfulbooker.apitest.utilities
#### DBConnection.java
> A Singleton Java class for MySQL DB connection. It contains insert, update, truncate, and query methods, apart from that it has a singleton pattern to connect to MySQL database. All database constants(URL,dbname, username, etc) can be found in Constants.properties file.


#### SshConnectionManager.java
> Its a custom Java helper class, which contains some reusable functions for reading CSV / TSV files, common JSON path assertions, etc.

### 6.com.restfulbooker.apitest.baseAPI
`com.restfulbooker.apitest.baseAPI` represents application’s each API entities with web service’s request data properties like request headers & request body data. For each API endpoint, one java file should be there with different functions/methods for each HTTP action. (eg:- for /users API, one java file (users.java) would be created. If /users support GET, POST&PUT HTTP methods then 3 unique functions/methods should be there in users.java file to invoke different HTTP actions of /users API).  In order for the tests to work properly, names of the fields(request data(headers, body)) must match the application's API structure convention.

# Installation<a name="install"></a>
#### Steps to follow to setup API automation in local system:<a name="steps"></a>

**Install Java: [Skip this step if already installed]**
- Check if Java is installed.
    - In terminal enter java -version to check if java is installed in the system.
    - In terminal enter javac -version to check if java compiler is installed in the system.
    - Any version of java greater than 1.8 is supported.
    - Install java development kit if not available.

**Install Eclipse / Any other latest IDE [Skip this step if already installed]:**
- Install eclipse photon if not available.
    - Download eclipse installer.
    - Run eclipse installer.
    - Select install eclipse for java developers.
    - Open workbench.

**Get Code base:**
- Clone restassuredFramework
    - url : https://github.com/traviprasad/api-automation-framework.git

**Setup project in Eclipse or IntelliJ:**
- File -> Open Project from File System -> Browse the folder and open the cloned project.
- Open -> Help -> Eclipse Marketplace -> Search testng -> Install Testng for eclipse plugin -> Restart eclipse.
- Install Cucumber Java and cucumber related plugins and restart IDE(eclipse)
- Maven will be available by default, with eclipse. To check, right click on project -> should have an option called maven.
```
Possible issues:
In case of error in pom.xml file ->Cannot read lifecycle mapping metadata for artifact org.apache.maven.plugins:mav
  - In terminal open Users/<profile_name>/.m2
  - Run rm -r repository
  - Right click on project -> Update project
In case of error in all import statements
  - Click on src/main/java folder -> build path -> remove from build path
  - Click on src -> main -> java ->right click -> build path -> use as source folder
  - Refresh the project
````
**Set up verification**
- In business logic package -> Right click on any java file -> run as testng test.
-

## How to Run BDD test Scenarios<a name="testRun"></a>

### To run tests
Go to your project directory from terminal and execute following commands
* `mvn clean test` to run all the tests.

##### Run scenario with 1 tag
* `mvn test  -Dcucumber.options="--tags '@getaccount'"`
* `mvn test  -Dcucumber.options="--tags '@scenario1'"`
* `mvn test  -Dcucumber.options="--tags '@transactions'"`
* `mvn test  -Dcucumber.options="--tags '@regression'"`

##### Run scenario with tag1 OR tag2
* `mvn test  -Dcucumber.options="--tags '@accounts or @transactions'"`

##### Run scenario with tag1 AND tag2
* ` mvn test  -Dcucumber.options="--tags '@accounts and @transactions'"`

##### Rerun scenario with tag:
* `mvn test  -Dcucumber.options="--tags '@regression or @smoke'"`

##### Ignore a tag and rerun failed scenario:
* `mvn test  -Dcucumber.options="--tags '@regression and not @ignore' --plugin rerun:rerun/failed_scenarios.txt"`


### Running test on different profiles

##### Local execution on Build2 ACT
* `mvn test -Plocal`

##### Local execution on Build2 MFA
* `mvn test -Plocalmfa`

##### Default Profile i.e. Build2 ACT with Zap
* `mvn test`


### Microservice level execution

#### All API scenarios:
`mvn clean test -Plocalmfa -Dcucumber.options="--tags '@api and not @ignore and not @bug and not @wip and not @manual'"`

#### Accounts scenarios:
`mvn clean test -Plocalmfa -Dcucumber.options="--tags '@accounts and not @ignore and not @bug and not @wip and not @manual'"`

#### Balances scenarios:
`mvn clean test -Plocalmfa -Dcucumber.options="--tags '@balances and not @ignore and not @bug and not @wip and not @manual'"`

#### Products scenarios:
`mvn clean test -Plocalmfa -Dcucumber.options="--tags '@products and not @ignore and not @bug and not @wip and not @manual'"`

#### Transaction scenarios:
`mvn clean test -Plocalmfa -Dcucumber.options="--tags '@transactions and not @ignore and not @bug and not @wip and not @manual'"`

#### Entitlements scenarios:
`mvn clean test -Plocalmfa -Dcucumber.options="--tags '@entitlements and not @ignore and not @bug and not @wip and not @manual'"`

#### INT/E2E scenarios:
`mvn clean test -Pintegration -Dcucumber.options="--tags '@AccountsE2E and not @ignore and not @bug and not @wip and not @manual'"`