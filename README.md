[![GitHub stars](https://img.shields.io/github/stars/ngoanh2n/jsoup-xpath.svg?style=social&label=Star&maxAge=2592000)](https://github.com/ngoanh2n/jsoup-xpath/stargazers/)
[![GitHub watchers](https://img.shields.io/github/watchers/ngoanh2n/jsoup-xpath.svg?style=social&label=Watch&maxAge=2592000)](https://github.com/ngoanh2n/jsoup-xpath/watchers/)
[![GitHub forks](https://img.shields.io/github/forks/ngoanh2n/jsoup-xpath.svg?style=social&label=Fork&maxAge=2592000)](https://github.com/ngoanh2n/jsoup-xpath/network/members/)

[![Maven Central](https://maven-badges.herokuapp.com/maven-central/io.github.ngoanh2n/jsoup-xpath/badge.svg)](https://maven-badges.herokuapp.com/maven-central/io.github.ngoanh2n/jsoup-xpath)
[![GitHub release](https://img.shields.io/github/release/ngoanh2n/jsoup-xpath.svg)](https://github.com/ngoanh2n/jsoup-xpath/releases/)
[![javadoc](https://javadoc.io/badge2/io.github.ngoanh2n/jsoup-xpath/javadoc.svg)](https://javadoc.io/doc/io.github.ngoanh2n/jsoup-xpath)
[![Build Status](https://travis-ci.org/ngoanh2n/jsoup-xpath.svg?branch=master)](https://travis-ci.org/ngoanh2n/jsoup-xpath)
[![License: MIT](https://img.shields.io/badge/License-MIT-blueviolet.svg)](https://opensource.org/licenses/MIT)
[![badge-jdk](https://img.shields.io/badge/jdk-8-blue.svg)](http://www.oracle.com/technetwork/java/javase/downloads/index.html)
[![GitHub contributors](https://img.shields.io/github/contributors/ngoanh2n/jsoup-xpath.svg)](https://github.com/ngoanh2n/jsoup-xpath/graphs/contributors/)
[![GitHub issues](https://img.shields.io/github/issues/ngoanh2n/jsoup-xpath.svg)](https://github.com/ngoanh2n/jsoup-xpath/issues/)
[![GitHub issues-closed](https://img.shields.io/github/issues-closed/ngoanh2n/jsoup-xpath.svg)](https://github.com/ngoanh2n/jsoup-xpath/issues?q=is%3Aissue+is%3Aclosed)
[![GitHub pull-requests](https://img.shields.io/github/issues-pr/ngoanh2n/jsoup-xpath.svg)](https://github.com/ngoanh2n/jsoup-xpath/pulls/)
[![GitHub pull-requests closed](https://img.shields.io/github/issues-pr-closed/ngoanh2n/jsoup-xpath.svg)](https://github.com/ngoanh2n/jsoup-xpath/pulls?q=is%3Apulls+is%3Aclosed)

- [How To Use](#how-to-use)
  - [Gradle Project](#gradle-project)
  - [Maven Project](#maven-project)
- [How To Use](#how-to-use-1)
  - [Convert Jsoup Node To XPath](#convert-jsoup-node-to-xpath)
  - [Find Jsoup Node By XpathContext](#find-jsoup-node-by-xpathcontext)
    - [Find Element](#find-element)
    - [Find Elements](#find-elements)

## How To Use
### Gradle Project
Add the `jsoup-xpath` dependency to your `build.gradle`
```gradle
dependencies {
    testImplementation("io.github.ngoanh2n:jsoup-xpath:1.0.0")
}
```

### Maven Project
Add the `jsoup-xpath` dependency to your `pom.xml`
```xml
<dependencies>
    [...]
    <dependency>
        <groupId>io.github.ngoanh2n</groupId>
        <artifactId>jsoup-xpath</artifactId>
        <version>1.0.0</version>
        <scope>test</scope>
    </dependency>
    [...]
</dependencies>
```

## How To Use
### Convert Jsoup Node To XPath
`node` can be:
- `org.jsoup.nodes.Node`
- `org.jsoup.nodes.Element`

```java
NodeXpath xpath = new NodeXpath(org.jsoup.nodes.Node)
xpath.getLocationPath() /* //html/body/div/input[2] */
```

### Find Jsoup Node By XpathContext
```java
XpathContext context = new XpathContext(org.jsoup.nodes.Document)
```

#### Find Element
```java
XpathContext#findElement("//html/body/div[2]")
```

#### Find Elements
```java
XpathContext#findElements("//html/body/div[2]/div")
```
