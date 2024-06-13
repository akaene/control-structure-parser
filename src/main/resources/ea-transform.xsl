<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0"
                xmlns="http://www.eclipse.org/uml2/3.0.0/UML"
                xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
                xmlns:xmi="http://www.omg.org/spec/XMI/20131001"
                xmlns:umldi="http://www.omg.org/spec/UML/20161101/UMLDI">
    <xsl:output indent="yes"/>

    <xsl:template match="xmi:XMI" priority="5">
        <xmi:XMI xmi:version="2.1" xmlns:uml="http://www.eclipse.org/uml2/3.0.0/UML">
            <xsl:copy-of select="namespace::*[not(. = 'http://www.omg.org/spec/UML/20161101')]"/>
            <xsl:apply-templates select="@* | node()"/>
        </xmi:XMI>
    </xsl:template>

    <xsl:template match="uml:*" xmlns:uml="http://www.omg.org/spec/UML/20161101" priority="5">
        <xsl:element name="{name()}" namespace="http://www.eclipse.org/uml2/3.0.0/UML">
            <xsl:apply-templates select="@* | node()"/>
        </xsl:element>
    </xsl:template>

    <!-- Remove all umldi:Diagram elements and their descendants -->
    <xsl:template match="umldi:Diagram" priority="5" />

    <!-- Remove all xmi:Extension elements with extender attribute value 'Enterprise Architect' -->
    <xsl:template match="xmi:Extension[@extender='Enterprise Architect']" priority="5" />

    <!-- Identity template to copy the rest of the nodes as is, adjusting for new UML namespace -->
    <xsl:template match="@* | node()" priority="1">
        <xsl:copy>
            <xsl:apply-templates select="@* | node()"/>
        </xsl:copy>
    </xsl:template>

    <xsl:template match="*[not(string-length(namespace-uri()) > 0)]" priority="2">
        <xsl:element name="{name()}">
            <xsl:apply-templates select="@* | node()"/>
        </xsl:element>
    </xsl:template>

    <xsl:template match="*[namespace-uri() and not(namespace-uri()='http://www.omg.org/spec/UML/20161101')]" priority="2">
        <xsl:element name="{name()}" namespace="{namespace-uri()}">
            <xsl:apply-templates select="@* | node()"/>
        </xsl:element>
    </xsl:template>

</xsl:stylesheet>
