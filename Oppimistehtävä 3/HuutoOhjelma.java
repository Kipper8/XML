package eta3;

import java.io.File;
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Iterator;
import java.util.Scanner;

import javax.xml.XMLConstants;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeConstants;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;

import org.xml.sax.SAXException;

class HuutoOhjelma {

	final private File xml = new File("huuto3.xml");
	final private File xsd = new File("huuto3.xsd");

	private Entry entry;

	public void toXML() throws JAXBException, SAXException {

		final JAXBContext jaxbContext = JAXBContext.newInstance(Entry.class);
		Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

		jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
		jaxbMarshaller.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");
		/*
		 * jaxbMarshaller.setProperty(
		 * Marshaller.JAXB_NO_NAMESPACE_SCHEMA_LOCATION, "huuto3.xsd");
		 * 
		 * SchemaFactory schemaFactory = SchemaFactory
		 * .newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI); Schema schema =
		 * schemaFactory.newSchema(xsd); jaxbMarshaller.setSchema(schema);
		 */

		jaxbMarshaller.marshal(entry, xml);
	}

	// LAITA TÄSTÄ ALASPÄIN OLEVA KOODI Viopeen

	public void lisaaEntry() {
		
		/*
		Päivämäärän tekeminen
		GregorianCalendar tempPaivays = new GregorianCalendar();
		tempPaivays.set(Calendar.YEAR, vv);
		tempPaivays.set(Calendar.MONTH, kk - 1);
		tempPaivays.set(Calendar.DATE, pp);
		XMLGregorianCalendar paivays = null;
		try {
			paivays = DatatypeFactory.newInstance().newXMLGregorianCalendar(
					tempPaivays);
			paivays.setTimezone(DatatypeConstants.FIELD_UNDEFINED);
			paivays.setTime(DatatypeConstants.FIELD_UNDEFINED,
					DatatypeConstants.FIELD_UNDEFINED,
					DatatypeConstants.FIELD_UNDEFINED,
					DatatypeConstants.FIELD_UNDEFINED);
		} catch (DatatypeConfigurationException e) {
		}
		*/
	

	}

	public static void main(final String[] args) {

		HuutoOhjelma ohjelma = new HuutoOhjelma();

		try {
			ohjelma.lisaaEntry();
			ohjelma.toXML();
		} catch (final JAXBException e) {
			e.printStackTrace();
		} catch (final SAXException e) {
			System.out.print("Validointi ei onnistunut");
		}
	}

}
