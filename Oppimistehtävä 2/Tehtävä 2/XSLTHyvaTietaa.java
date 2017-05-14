import java.io.File;
import java.io.IOException;
import java.io.StringReader;

public class XSLTHyvaTietaa {

	public static void main(String[] args)
			throws javax.xml.transform.TransformerException, IOException {

		try {

			String xslt = "<?xml version='1.0' encoding='UTF-8'?> <xsl:stylesheet version='1.0' 	xmlns:xsl='http://www.w3.org/1999/XSL/Transform'> 	<xsl:output method='html' indent='yes' /> 	<xsl:template match='/'> 		<html> 			<head> 				<title>Vuosi 2017</title> 			</head> 			<body> 				<xsl:apply-templates select='events/event'/> 			</body> 		</html> 	</xsl:template> 	<xsl:template match='event'> 		<p>Päivä:&#160;<xsl:value-of select='date'/> 		<br/>Tapahtuma:&#160;<xsl:value-of select='name'/> 		<br/>Kuvaus:&#160;<xsl:value-of select='description'/> 		<br/>url:&#160;<xsl:value-of select='url'/></p> 	</xsl:template> </xsl:stylesheet> ";
			
			StringReader readerXSLT = new StringReader(xslt);
			final File xmlFile = new File("hyvaTietaa2017.xml");
			final File result = new File("hyvaTietaa2017.html");

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
					.println("Muunnoksen tulos on tiedostossa hyvaTietaa2017.html");

		} catch (javax.xml.transform.TransformerException ex) {
			System.out.println("Muunnos ei onnistu, koska ");
			System.out.println(ex.getMessage());
		} catch (Exception ex) {
			System.out.println("Ongelmia, koska ");
			System.out.println(ex.getMessage());
		}
	}
}
