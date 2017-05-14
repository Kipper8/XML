<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
	<xsl:output method="html" indent="yes" />
	<xsl:template match="/">
	<html>
		<head>
			<title>Elokuvat</title>
		</head>
		<body>
			<h2>Elokuvat</h2>
			<xsl:apply-templates select="Events/Event"/>
		</body>
	</html>
	</xsl:template>
	<xsl:template match="Event">
		<p>
		Nimi:&#160;<xsl:value-of select="Title"/>&#160;(<xsl:value-of select="OriginalTitle"/>)
		<br/>Kuvaus:&#160;<xsl:value-of select="ShortSynopsis"/>
		<br/>Vuosi:&#160;<xsl:value-of select="ProductionYear"/>
		<br/>Esitykseen:&#160;<xsl:value-of select="substring (dtLocalRelease,1,10)"/>
		<br/>Ohjaajat:&#160;<xsl:apply-templates select="Directors/Director"/>
		<br/>Näyttelijät:&#160;<xsl:apply-templates select="Cast/Actor"/>
		</p>
	</xsl:template>
	<xsl:template match="Director">
		<xsl:value-of select="FirstName"/>&#160;<xsl:value-of select="LastName"/>&#160;
	</xsl:template>
	<xsl:template match="Actor">
		<xsl:value-of select="FirstName"/>&#160;<xsl:value-of select="LastName"/>,&#160;
	</xsl:template>
</xsl:stylesheet>