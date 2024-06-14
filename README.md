# System Control Structure Parser

Library for reading system control structure for system-theoretic process analysis (STPA).

Currently supported artifacts:

- SysML XMI/UML (based on prototype at https://github.com/kbss-cvut/xmi-parser-mwe)
  - `.zip` archive containing a model file and relevant profile files
  - `*.xmi`/`*.uml`/`*.xml` file. The file or the directory containing the file should also contain relevant profile data
  - `.graphml` files (produced by [yEd](https://www.yworks.com/products/yed) and [yEd Live](https://www.yworks.com/products/yed-live))

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

## How to Use

`ControlStructureParsers` is able to find the correct parser and use it to parse the specified model file.

```java
import com.akaene.stpa.scs.parser.ControlStructureParsers;

final File input = // get input file
final Model model = ControlStructureParsers.parse(input);
```

But it is also possible to use the parser implementations directly.

## License

MIT
