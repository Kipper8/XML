<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
	<xsl:output method="html" indent="yes" />
	<xsl:template match="/">
		<html>
			<head>
				<title>Ajopaivakirja</title>
			</head>
			<body>
				<table border="1" cellpadding="5" cellspacing="0">
					<tbody>
						<tr>
							<th>Lähtöaika</th>
							<th>Lähtöpaikka</th>
							<th>Tuloaika</th>
							<th>Tulopaikka</th>
							<th>Matka ja korvaus</th>
						</tr>
						<xsl:apply-templates select="ajopaivakirja/ajo"/>
					</tbody>
				</table>
			</body>
		</html>
	</xsl:template>
	<xsl:template match="ajo">
		<tr>
			<th><xsl:apply-templates select="alku"/></th>
			<th><xsl:value-of select="alku/paikka"/></th>
			<th><xsl:apply-templates select="loppu"/></th>
			<th><xsl:value-of select="loppu/paikka"/></th>
			<th><xsl:value-of select="loppu/lukema - alku/lukema"/>&#160;kilometriä,&#160;<xsl:value-of select="(loppu/lukema - alku/lukema) * 0.41"/>&#160;euroa</th>
		</tr>
	</xsl:template>
	<xsl:template match="alku">
		<xsl:value-of select="aika/@paiva"/>&#160;
		<xsl:value-of select="aika/@kello"/>
	</xsl:template>
	<xsl:template match="loppu">
		<xsl:value-of select="aika/@paiva"/>&#160;
		<xsl:value-of select="aika/@kello"/>
	</xsl:template>
</xsl:stylesheet>