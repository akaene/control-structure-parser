# System Control Structure Parser

Library for reading system control structure for system-theoretic process analysis (STPA).

Currently supported artifacts:

- SysML XMI/UML (based on prototype at https://github.com/kbss-cvut/xmi-parser-mwe)

## Requirements

- Java 21

## How to Get

Use the following dependency:

```xml
<dependency>
    <groupId>com.akaene.stpa</groupId>
    <artifactId>control-structure-parser</artifactId>
</dependency>
```

Do not forget to add the AKAENE Maven repository:

```xml

<repositories>
    <repository>
        <id>akaene</id>
        <name>AKAENE Maven repository</name>
        <url>https://dev.akaene.com/m2repo</url>
    </repository>
</repositories>
```

## License

MIT
