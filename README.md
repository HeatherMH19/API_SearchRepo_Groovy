# Running the GitHub API Search Repositories Tests

---
## Pre-requisites
* Groovy 3.0.8 or higher
* Java SE Development Kit (JDK) 16.0.2 or higher

### Groovy
Groovy download information can be found here:
> https://groovy.apache.org/download.html

If you use the Windows installer, let it create the GROOVY_HOME environment variable and add the extension to Path.  Otherwise, you can manually add the Path modification yourself.

When done, you should see something like this in your system environment variables:

|Variable|Value|
|---|---|
|GROOVY_HOME|C:\yourPathExample\groovy-3.0.8|

The installer will add "%GROOVY_HOME%\bin" to the Path variable.  If you aren't using the installer or prefer not to add the GROOVY_HOME variable, you can add the following to your Path variable:

> C:\yourPathExample\groovy-3.0.8\bin

To verify everything is working as expected at this point, open command line (cmd), type "groovy -version," and hit Enter.  All being well, you will receive no errors and see information about your installed version of Groovy.

### JDK
Information about Java downloads and installation can be found here:
> https://www.oracle.com/java/technologies/javase-downloads.html

As with Groovy before, you will need to modify the Path variable.  The addition should look like this:

> C:\yourPathExample\Java\jdk-16.0.2\bin

## Run all tests for Search Repositories API

1. Open command line
2. Change directories to the location of the project's test scripts
   1. Example command: cd C:\myproject\sdet-coding-challenge\src
3. Use the groovy command to run the Test Suite script
   1. groovy TestSuite_API_SearchRepo.groovy

## Run a specific test script

1. Open command line
2. Change directories to the location of the project's test scripts
    1. Example command: cd C:\myproject\sdet-coding-challenge\src
3. Use the groovy command to run the specific test script you want
   1. groovy API_SearchRepo_User.groovy