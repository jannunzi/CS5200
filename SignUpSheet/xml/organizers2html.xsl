<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
	<xsl:output method="xml" indent="yes"/>
	<xsl:template match="/">
		<html>
		<head>
		</head>
		<body>
		<xsl:for-each select="organizers/organizer">
			<h1><xsl:value-of select="@firstName"/> <xsl:value-of select="@lastName"/></h1>
			<xsl:for-each select="sheet">
			<h2><xsl:value-of select="@name"/></h2>
			<table>
				<xsl:for-each select="time-slot">
				<tr>
					<td><xsl:value-of select="@slotDate"/></td>
					<td><xsl:value-of select="@who"/></td>
					<td><xsl:value-of select="@notes"/></td>
				</tr>
				</xsl:for-each>
			</table>
			</xsl:for-each>
		</xsl:for-each>
		</body>
		</html>
	</xsl:template>
</xsl:stylesheet>