package generated;

import java.io.File;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.ArrayList;
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


class HyvaTietaa2017Ohjelma {

	final private File xml = new File("hyvaTietaa2017.xml");
	final private File xsd = new File("hyvaTietaa2017.xsd");

	private Events events;

	public void toObject() throws JAXBException, SAXException {
		final JAXBContext jaxbContext = JAXBContext.newInstance(Events.class);
		final Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();

		events = (Events) jaxbUnmarshaller.unmarshal(xml);
	}

	public void toXML() throws JAXBException, SAXException {

		final JAXBContext jaxbContext = JAXBContext.newInstance(Events.class);
		Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

		jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
		jaxbMarshaller.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");
/*
		jaxbMarshaller.setProperty(
				Marshaller.JAXB_NO_NAMESPACE_SCHEMA_LOCATION,
				"hyvaTietaa2017.xsd");

		SchemaFactory schemaFactory = SchemaFactory
				.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
		Schema schema = schemaFactory.newSchema(xsd);
		jaxbMarshaller.setSchema(schema);
*/
		jaxbMarshaller.marshal(events, xml);
	}

	// LAITA TÄSTÄ ALASPÄIN OLEVA KOODI Viopeen

	public void listaaEvents() {
		Events.Event tapahtuma = new Events.Event();
		for(int i = 0; i < events.getEvent().size(); i++) {
			tapahtuma = events.event.get(i);
			System.out.println(tapahtuma.getDate().getDay()+"."+tapahtuma.getDate().getMonth()+"."+tapahtuma.getDate().getYear()+" "+tapahtuma.getName()+" ("+tapahtuma.getUrl()+")");
		}
	}
	
	public void getEvent() {
		List<Events.Event> list = events.getEvent();
		Scanner sc = new Scanner(System.in);
		System.out.print("Anna haettavan tapahtuman nimi: ");
		String searchedEvent = sc.nextLine();
		int i = 0;
		int index = -1;
		//Tapahtuman haku
		while(index == -1 && i < list.size()) {
			Events.Event event = list.get(i);
			if(event.getName().equals(searchedEvent)) {
				String name = event.getName();
				int day = event.getDate().getDay();
				int month = event.getDate().getMonth();
				int year = event.getDate().getYear();
				String url = event.getUrl();
				Short flag = event.getFlagDay();
				String alternateNames = event.getAlternateNames();
				if (flag != null) {
					System.out.println(day+"."+month+"."+year+" "+name+" ("+url+") Liputuspäivä");
					index = i;
				} else if (alternateNames != null) {
					System.out.println(day+"."+month+"."+year+" "+name+" ("+url+") "+alternateNames);
					index = i;
				} else if (flag == null && alternateNames == null){
					System.out.println(day+"."+month+"."+year+" "+name+" ("+url+") ");
					//Jatketaan tapahtumien hakua, jos samalla nimellä löytyy toinenkin tapahtuma
					for (int x = i+1; x < list.size(); x++) {
						event = list.get(x);
						day = event.getDate().getDay();
						month = event.getDate().getMonth();
						year = event.getDate().getYear();
						if(event.getName().equals(searchedEvent)) {
							if (flag != null) {
								System.out.println(day+"."+month+"."+year+" "+name+" ("+url+") Liputuspäivä");
								index = i;
							} else if (alternateNames != null) {
								System.out.println(day+"."+month+"."+year+" "+name+" ("+url+") "+alternateNames);
								index = i;
							} else if (flag == null && alternateNames == null){
								System.out.println(day+"."+month+"."+year+" "+name+" ("+url+") ");
							}
						}
					}
					index = i;
				}
			} else {
				i++;
				if(i == list.size()) {
					System.out.println("Tapahtumaa ei ole annetulla nimellä");
				}
			}
		}
	}
	
	public void deleteEvent() {
		List<Events.Event> list = events.getEvent();
		Scanner sc = new Scanner(System.in);
		int line = 0;
		int line2 = 0;
		//Listataan kaikki tapahtumat
		for (int i = 0; i < list.size(); i++) {
			Events.Event event = list.get(i);
			String name = event.getName();
			int day = event.getDate().getDay();
			int month = event.getDate().getMonth();
			int year = event.getDate().getYear();
			String url = event.getUrl();
			line = list.indexOf(event) + 1;
			System.out.println(line+". "+day+"."+month+"."+year+" "+name+" ("+url+")");
		}
		System.out.print("Anna poistettavan tapahtuman numero: ");
		int number = sc.nextInt();
		//Tekee indeksilistauksen
		List<Integer> numlist = new ArrayList<Integer>();
		for (int i = 0; i < list.size(); i++) {
			Events.Event event = list.get(i);
			line2 = list.indexOf(event) + 1;
			numlist.add(line2);
		}
		line = number -1;
		//Tarkistaa, että löytyykö annettu numero listauksesta, jos ei niin virheilmoitus
		if (!numlist.contains(number)) {
			System.out.println("Tapahtumaa ei ole numerolla "+(line+1));
			return;
		}
		Events.Event event = events.getEvent().remove(line);
		System.out.println("Tapahtuma poistettiin numerolla "+number);
	}
	
	public void modifyEventsUrl() {
		List<Events.Event> list = events.getEvent();
		Scanner sc = new Scanner(System.in);
		int line = 0;
		int line2 = 0;
		//Listataan kaikki tapahtumat
		for (int i = 0; i < list.size(); i++) {
			Events.Event event = list.get(i);
			String name = event.getName();
			int day = event.getDate().getDay();
			int month = event.getDate().getMonth();
			int year = event.getDate().getYear();
			String url = event.getUrl();
			line = list.indexOf(event) + 1;
			System.out.println(line+". "+day+"."+month+"."+year+" "+name+" ("+url+")");
		}
		System.out.print("Anna muutettavan tapahtuman numero: ");
		int number = sc.nextInt();
		//Tekee indeksilistauksen
		List<Integer> numlist = new ArrayList<Integer>();
		for (int i = 0; i < list.size(); i++) {
			Events.Event event = list.get(i);
			line2 = list.indexOf(event) + 1;
			numlist.add(line2);
		}
		line = number -1;
		//Tarkistaa, että löytyykö annettu numero listauksesta, jos ei niin virheilmoitus
		if (!numlist.contains(number)) {
			System.out.println("Tapahtumaa ei ole numerolla "+(line+1));
			return;
		}
		System.out.println("Anna tapahtuman uusi www:");
		String newUrl = sc.next();
		Events.Event event = events.getEvent().get(line);
		event.setUrl(newUrl);
	}
	
	public static void main(final String[] args) {

		HyvaTietaa2017Ohjelma sovellus = new HyvaTietaa2017Ohjelma();

		try {

			sovellus.toObject();

			Scanner input = new Scanner(System.in);
			int valinta = -1;

			do {
				System.out.println("\n1 = Listaa tapahtumat");
				System.out.println("2 = Hae tapahtumat nimellä");
				System.out.println("3 = Poista tapahtuma");
				System.out.println("4 = Muuta tapahtuman url");
				System.out.println("0 = Lopeta");
				System.out.print("Anna valintasi: ");
				valinta = input.nextInt();
				input.nextLine();
				switch (valinta) {
				case 1:
					sovellus.listaaEvents();
					break;
				case 2:
					sovellus.getEvent();
					break;
				case 3:
					sovellus.deleteEvent();
					break;
				case 4:
					sovellus.modifyEventsUrl();
					break;
				}

			} while (valinta != 0);

			sovellus.toXML();

		} catch (final JAXBException e) {
			e.printStackTrace();
		} catch (final SAXException e) {
			System.out.print("Validointi ei onnistunut");
		}
	}
}
