# System Control Structure Parser

Library for reading system control structure for system-theoretic process analysis (STPA).

Currently supported artifacts:

- SysML XMI/UML (based on prototype at https://github.com/kbss-cvut/xmi-parser-mwe)
  - `.zip` archive containing a model file and relevant profile files
  - `*.xmi`/`*.uml`/`*.xml` file. The file or the directory containing the file should also contain relevant profile data

Tested on XMI artifacts produced by [Modelio](https://www.modelio.org/index.htm) and [Enterprise Architect](https://sparxsystems.com/products/ea/).

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
