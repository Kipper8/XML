<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
	<xsl:output method="html" indent="yes" />
	<xsl:template match="/">
		<html>
			<head>
				<title>Katsojatilasto</title>
			</head>
			<body>
				<xsl:apply-templates select="toplista/tiedot"/>
				<xsl:apply-templates select="toplista/ohjelmat/ohjelma"/>
			</body>
		</html>
	</xsl:template>
	<xsl:template match="tiedot">
		<h2><b>Katsojatilasto&#160;<xsl:value-of select="viikko"/>/<xsl:value-of select="vuosi"/></b></h2>
	</xsl:template>
	<xsl:template match="ohjelma">
		<p>
		<b>Sija:</b>&#160;<xsl:value-of select="@jnro"/>
		<br/>
		<b>Nimi:</b>&#160;<xsl:value-of select="nimi"/>
		<br/>
		<b>Kanava:</b>&#160;<xsl:value-of select="@kanava"/>
		<br/>
		<b>Katsojamaara:</b>&#160;<xsl:value-of select="katsojamaara"/>
		</p>
	</xsl:template>
</xsl:stylesheet>