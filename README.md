# SoftwareMill Common project

Most of the modules are usable stand-alone and do not require other modules. Simply include the jar in your
project and you're ready to use it. See the individual module READMEs for more information.

Jars and sources are available in our Maven repositories:

    <repository>
        <id>softwaremill-snapshots</id>
        <name>SoftwareMill Snapshots</name>
        <url>https://nexus.softwaremill.com/content/repositories/snapshots</url>
    </repository>
    <repository>
        <id>softwaremill-releases</id>
        <name>SoftwareMill Releases</name>
        <url>https://nexus.softwaremill.com/content/repositories/releases</url>
    </repository>

To use SoftwareMill Common in a project define a parent section in the main pom of the project:

    <parent>
        <groupId>com.softwaremill.common</groupId>
        <artifactId>softwaremill-parent</artifactId>
        <version>[VERSION]</version>
    </parent>

where [VERSION] is the version you want to use in your project.

**Important Note:** Since version 74 SoftwareMill Common uses *com.softwaremill* package names instead of *pl.softwaremill* that were present in version 73 and earlier.

# Modules overview

## [CDI extensions](/softwaremill-cdi/)

* Transaction interceptors
* Stackable security interceptors
* Implementation of assisted inject in Weld (autofactories)
* Object-services (polymorphic extension methods)
* Static bean injection

## [CDI+JSF2 integration utilities](/softwaremill-faces/)

* Transaction phase listeners ("open session in view")
* Security phase listeners
* Navigation handlers
* i18n, messaging, validation utilities

## [Configuration reader](/softwaremill-conf/)

Reads key-value configuration files either from JBoss's conf directory (which have priority) or from the classpath. 

## [Amazon AWS Utilities](/softwaremill-sqs/)

## [Softwaremill Parent](/softwaremill-parent/)

Our BOM.

## Testing utilities

### [Test-with-DB framework using Arquillian](/softwaremill-test/softwaremill-test-db)

Lets you run tests that use a database.

### [Selenium+JBoss UI testing utilities](/softwaremill-test/softwaremill-test-ui-web/)

### [FEST Assert for joda time classes](/softwaremill-test/softwaremill-test-util/)

### [Test server](/softwaremill-test/softwaremill-test-server/)

For testing of requests sent to server

## [Java utilities](/softwaremill-util/)

## [Backup utilities](/softwaremill-backup/)

Scripts and java classes to backup SimpleDB domains and upload them to S3.

## [Debug utilities](/softwaremill-debug/)

A timing interceptor + web filter + portable extension for profiling CDI beans.

## [Paypal](/softwaremill-paypal/)

Paypal utilities for handling paypal requests (IPN) and generating paypal pay buttons (custom cart).

---

Licensed under the Apache2 license. [Softwaremill 2010-2012](http://softwaremill.com/).
