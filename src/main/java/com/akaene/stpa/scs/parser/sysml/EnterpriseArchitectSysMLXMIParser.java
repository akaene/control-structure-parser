package com.akaene.stpa.scs.parser.sysml;

import com.akaene.stpa.scs.exception.ControlStructureParserException;
import com.akaene.stpa.scs.model.Model;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.xml.namespace.QName;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.Attribute;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;

/**
 * Control structure parser supporting SysML XMI artifacts produced by Enterprise Architect.
 * <p>
 * Such artifacts are by default not readable by Eclipse Modeling Framework (EMF).
 */
public class EnterpriseArchitectSysMLXMIParser extends EMFSysMLXMIParser {

    private static final Logger LOG = LoggerFactory.getLogger(EnterpriseArchitectSysMLXMIParser.class);

    @Override
    public Model parse(File input) {
        final File transformedInput = transformToEMFReadable(input);
        try {
            return super.parse(transformedInput);
        } finally {
            transformedInput.delete();
        }
    }

    public static boolean isEnterpriseArchitectFile(File input) {
        LOG.trace("Checking if input file '{}' was produced by Enterprise Architect.", input);
        try {
            final XMLInputFactory inputFactory = XMLInputFactory.newInstance();
            final XMLEventReader eventReader = inputFactory.createXMLEventReader(new FileInputStream(input));
            while (eventReader.hasNext()) {
                final XMLEvent event = eventReader.nextEvent();
                if (event.isStartElement()) {
                    final StartElement startElement = event.asStartElement();
                    if (startElement.getName()
                                    .equals(new QName("http://www.omg.org/spec/XMI/20131001", "Documentation"))) {
                        final Attribute exporter = startElement.getAttributeByName(new QName("exporter"));
                        if (exporter != null && exporter.getValue().equals("Enterprise Architect")) {
                            return true;
                        }
                    }

                }
            }
            return false;
        } catch (IOException | XMLStreamException e) {
            throw new ControlStructureParserException(
                    "Unable to resolve whether file was generated by Enterprise Architect", e);
        }
    }

    private static File transformToEMFReadable(File input) {
        try {
            final TransformerFactory transformerFactory = TransformerFactory.newInstance();
            final Source transformerSource = new StreamSource(
                    EnterpriseArchitectSysMLXMIParser.class.getClassLoader().getResourceAsStream("ea-transform.xsl"));
            final Transformer transformer = transformerFactory.newTransformer(transformerSource);
            final Source toTransform = new StreamSource(input);
            final File output = Files.createTempFile("scsparser-",
                                                     input.getName().substring(input.getName().indexOf('.'))).toFile();
            LOG.trace("Enterprise Architect transformation target is '{}'.", output);
            output.deleteOnExit();
            final Result target = new StreamResult(output);
            transformer.transform(toTransform, target);
            return output;
        } catch (TransformerException e) {
            LOG.error("Unable to transform file.", e);
            throw new ControlStructureParserException("Unable to transform Enterprise Architect file.", e);
        } catch (IOException e) {
            LOG.error("Unable to create transformation target file.", e);
            throw new ControlStructureParserException("Unable to transform Enterprise Architect file.", e);
        }
    }
}