import java.io.File;
import java.io.IOException;
import java.io.StringReader;

public class XSLTFinnkino {

	public static void main(String[] args)
			throws javax.xml.transform.TransformerException, IOException {

		try {

			String xslt = "<?xml version='1.0' encoding='UTF-8'?> <xsl:stylesheet version='1.0' 	xmlns:xsl='http://www.w3.org/1999/XSL/Transform'> 	<xsl:output method='html' indent='yes' /> 	<xsl:template match='/'> 	<html> 		<head> 			<title>Elokuvat</title> 		</head> 		<body> 			<h2>Elokuvat</h2> 			<xsl:apply-templates select='Events/Event'/> 		</body> 	</html> 	</xsl:template> 	<xsl:template match='Event'> 		<p> 		Nimi:&#160;<xsl:value-of select='Title'/>&#160;(<xsl:value-of select='OriginalTitle'/>) 		<br/>Kuvaus:&#160;<xsl:value-of select='ShortSynopsis'/> 		<br/>Vuosi:&#160;<xsl:value-of select='ProductionYear'/> 		<br/>Esitykseen:&#160;<xsl:value-of select='substring (dtLocalRelease,1,10)'/> 		<br/>Ohjaajat:&#160;<xsl:apply-templates select='Directors/Director'/> 		<br/>Näyttelijät:&#160;<xsl:apply-templates select='Cast/Actor'/> 		</p> 	</xsl:template> 	<xsl:template match='Director'> 		<xsl:value-of select='FirstName'/>&#160;<xsl:value-of select='LastName'/>&#160; 	</xsl:template> 	<xsl:template match='Actor'> 		<xsl:value-of select='FirstName'/>&#160;<xsl:value-of select='LastName'/>,&#160; 	</xsl:template> </xsl:stylesheet> ";
			
			StringReader readerXSLT = new StringReader(xslt);
			final File xmlFile = new File("finnkino2.xml");
			final File result = new File("finnkino2.html");

			javax.xml.transform.Source xmlSource = new javax.xml.transform.stream.StreamSource(
					xmlFile);
			javax.xml.transform.Source xsltSource = new javax.xml.transform.stream.StreamSource(
					readerXSLT);
			javax.xml.transform.Result htmlResult = new javax.xml.transform.stream.StreamResult(
					result);

			javax.xml.transform.TransformerFactory transFact = javax.xml.transform.TransformerFactory
					.newInstance();

			javax.xml.transform.Transformer trans = transFact
					.newTransformer(xsltSource);

			trans.transform(xmlSource, htmlResult);
			System.out
					.println("Muunnoksen tulos on tiedostossa finnkino2.html");

		} catch (javax.xml.transform.TransformerException ex) {
			System.out.println("Muunnos ei onnistu, koska ");
			System.out.println(ex.getMessage());
		} catch (Exception ex) {
			System.out.println("Ongelmia, koska ");
			System.out.println(ex.getMessage());
		}
	}
}
