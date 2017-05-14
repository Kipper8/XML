package projekti;

import java.io.IOException;
import java.util.List;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.xml.sax.SAXException;

import java.util.Iterator; 

public class SportsmlMMOhjelma {
	private Document doc;
	final private String xmlFile = "sportsmlMM.xml";

	public void toJDOM() throws JDOMException, IOException, SAXException {
		SAXBuilder builder = new SAXBuilder();
		doc = builder.build(xmlFile);
	}

		public static void main(String[] args) {
			try {
				SportsmlMMOhjelma sovellus = new SportsmlMMOhjelma();

				sovellus.toJDOM();
				// TEE METODI haeTulokset
				sovellus.haeTulokset();

			} catch (JDOMException e) {
				e.printStackTrace();
				System.out
						.println("XMl-dokumentti ei ole oikein muodostettu, koska "
								+ e.getMessage());
			} catch (IOException e) {
				e.printStackTrace();
			} catch (SAXException e) {
				e.printStackTrace();
			}

	}

	// Palauta t�st� l�htien Viopeen

	public void haeTulokset() {
		List<Element> results = doc.getRootElement().getChildren("tournament");
		for (int i = 0; i < results.size(); i++) {
			// <tournament>
			Element tournament = results.get(i);
			// <tournament-metadata>
			Element tournamentMeta = tournament.getChild("tournament-metadata");
			// <home-location>
			Element homelocation = tournamentMeta.getChild("site").getChild("site-metadata").getChild("home-location");
			// <tournament-division>
			List<Element> divisions = tournament.getChildren("tournament-division");
			
			System.out.println(homelocation.getAttributeValue("city")+
					" "+tournamentMeta.getAttributeValue("tournament-name")+
					" "+tournamentMeta.getAttributeValue("start-date-time")+
					" - "+tournamentMeta.getAttributeValue("end-date-time"));
			System.out.print("\n");
			//Results
			for (int x = 0; x < divisions.size(); x++) {
				Element division = divisions.get(x);
				// <sports-content-code>
				Element sportscode = division.getChild("tournament-division-metadata").getChild("sports-content-codes").getChild("sports-content-code");
				// <tournament-round>
				Element round = division.getChild("tournament-round");
				// <sports-event>
				Element finals = round.getChild("sports-event").getChild("event-metadata");
				// <player>
				Element sportsEvent = round.getChild("sports-event");
				List<Element> players = sportsEvent.getChildren("player");
				
				System.out.println(sportscode.getAttributeValue("code-key")+
						" "+finals.getAttributeValue("event-name"));
				
				for(int a = 0; a < players.size(); a++) {
					Element player = players.get(a);
					// <player-metadata>
					Element playerMeta = player.getChild("player-metadata");
					// players name & home
					Element name = playerMeta.getChild("name");
					Element home = playerMeta.getChild("home-location");
					// <player-stats>
					Element playerStats = player.getChild("player-stats");
					// rank & medal
					Element rank = playerStats.getChild("rank");
					Element award = playerStats.getChild("award");
					
					if(award != null) {
						System.out.println(rank.getAttributeValue("value")+
								". "+name.getAttributeValue("full")+
								" "+home.getAttributeValue("country")+
								" "+playerStats.getAttributeValue("score")+
								" "+award.getAttributeValue("name"));
					} else {
						System.out.println(rank.getAttributeValue("value")+
								". "+name.getAttributeValue("full")+
								" "+home.getAttributeValue("country")+
								" "+playerStats.getAttributeValue("score"));
					}
				}
				System.out.print("\n");
			}
			System.out.print("\n");
		}
	}
}
