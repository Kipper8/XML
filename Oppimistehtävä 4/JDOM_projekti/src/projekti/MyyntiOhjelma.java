package projekti;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;
import org.xml.sax.SAXException;
import java.util.Iterator;

public class MyyntiOhjelma {

	private Document doc;
	final private String xmlFile = "myynti4.xml";

	public void toJDOM() throws JDOMException, IOException, SAXException {
		SAXBuilder builder = new SAXBuilder();
		doc = builder.build(xmlFile);
	}

	public void toXML() throws IOException, SAXException {
		XMLOutputter fmt = new XMLOutputter();
		fmt.setFormat(Format.getPrettyFormat().setEncoding("UTF-8"));
		FileWriter writer = new FileWriter(xmlFile);
		fmt.output(doc, writer);
	}

	// Palauta koodi tästä lähtien Viopeen
	
	public static void main(String[] args) throws Exception {
		MyyntiOhjelma sovellus = new MyyntiOhjelma();
		try {
			sovellus.toJDOM();

			int valinta = 0;

			Scanner input = new Scanner(System.in);

			do {
				System.out.println("\n1. Listaa myyntikohteet");
				System.out.println("2. Hae myyntikohde");
				System.out.println("3. Poista myyntikohde");
				System.out.println("4. Muuta myyntikohteen kuvausta");
				System.out.println("0. Lopeta");
				System.out.print("Anna valintasi: ");
				valinta = input.nextInt();
				input.nextLine();

				switch (valinta) {
				case 1:
					sovellus.listaaMyyntikohteet();
					break;

				case 2:
					sovellus.haeMyyntikohde();
					break;

				case 3:
					sovellus.poistaMyyntikohde();
					break;
					
				case 4:
					sovellus.muutaMyyntikohteenKuvaus();
					break;
				}

			} while (valinta != 0);
			sovellus.toXML();

		} catch (JDOMException e) {
			e.printStackTrace();
			System.out.println("XMl-dokumentti ei ole well-formed, koska "
					+ e.getMessage());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}


	public void listaaMyyntikohteet() {
		List<Element> myyntikohteet = doc.getRootElement().getChildren("asunto");
		for (int i = 0; i < myyntikohteet.size(); i++) {
			System.out.print("\n");
			Element myyntikohde = myyntikohteet.get(i);
			
			System.out.println("Kohdenumero: "+myyntikohde.getAttributeValue("kohdenumero"));
			System.out.println("Sijainti: "+myyntikohde.getChildText("osoite"));
			System.out.println("Rakennusvuosi: "+myyntikohde.getAttributeValue("rakennusvuosi"));
			System.out.println("Kuvaus: "+myyntikohde.getChildText("kuvaus"));
			System.out.println("Pinta-ala: "+myyntikohde.getChildText("pinta-ala"));
			
			List<Element> hinnat = myyntikohde.getChildren("hinta");
			System.out.print("Hinta: ");
			for (int k= 0; k < hinnat.size(); k++) {
				Element hinta = hinnat.get(k);
				System.out.print(hinta.getText() + " (" + hinta.getAttributeValue("tyyppi") + ") ");
			}
			System.out.print("\n");
		}
	}
	public Element etsiKohde(String id) {
		Element myyntikohde = null;
		
		List<Element> myyntikohteet = doc.getRootElement().getChildren("asunto");
		int i = 0;
		
		while (myyntikohde == null && i < myyntikohteet.size()) {
			if (myyntikohteet.get(i).getAttributeValue("kohdenumero").equals(id))
				myyntikohde = myyntikohteet.get(i);
			else
			  i++;
		}
		
		return myyntikohde;
	}
	public void haeMyyntikohde() {
		Scanner input = new Scanner(System.in);
		System.out.print("Anna haettavan myyntikohteen kohdenumero: ");
		String id = input.nextLine();
		Element myyntikohde = etsiKohde(id);
		if (myyntikohde != null) {
			System.out.print("\n");
			System.out.println("Kohdenumero: "+myyntikohde.getAttributeValue("kohdenumero"));
			System.out.println("Sijainti: "+myyntikohde.getChildText("osoite"));
			System.out.println("Rakennusvuosi: "+myyntikohde.getAttributeValue("rakennusvuosi"));
			System.out.println("Kuvaus: "+myyntikohde.getChildText("kuvaus"));
			System.out.println("Pinta-ala: "+myyntikohde.getChildText("pinta-ala"));
			
			List<Element> hinnat = myyntikohde.getChildren("hinta");
			System.out.print("Hinta: ");
			for (int k= 0; k < hinnat.size(); k++) {
				Element hinta = hinnat.get(k);
				System.out.print(hinta.getText() + " (" + hinta.getAttributeValue("tyyppi") + ") ");
			}
			System.out.print("\n");
		} else {
			System.out.println("Kohdetta ei ole numerolla "+id);
		}	
	}
	public void poistaMyyntikohde() {
		Scanner input = new Scanner(System.in);
		System.out.print("Anna poistettavan myyntikohteen kohdenumero: ");
		String id = input.nextLine();
		Element myyntikohde = etsiKohde(id);
		if(myyntikohde != null) {
			myyntikohde.detach();
			System.out.println("Kohde numerolla "+id+" poistettiin");
		} else {
			System.out.println("Kohdetta ei ole numerolla "+id);
		}
	}
	public void muutaMyyntikohteenKuvaus() {
		Scanner input = new Scanner(System.in);
		System.out.print("Anna muutettavan myyntikohteen kohdenumero: ");
		String id = input.nextLine();
		Element myyntikohde = etsiKohde(id);
		if(myyntikohde != null) {
			System.out.println("Entinen kuvaus: "+myyntikohde.getChildText("kuvaus"));
			System.out.print("Anna uusi kuvaus: ");
			String uusiKuvaus = input.nextLine();
			myyntikohde.getChild("kuvaus").setText(uusiKuvaus);
		} else {
			System.out.println("Kohdetta ei ole numerolla "+id);
		}
	}
}
