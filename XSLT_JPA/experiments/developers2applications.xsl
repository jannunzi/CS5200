<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
	<xsl:output indent="yes" media-type="text/xml" method="xml"></xsl:output>
	<xsl:template match="/">
		<applications>
		<xsl:for-each select="team/developer/application">
			<application>
				<name><xsl:value-of select="@name"/></name>
				<developer>
					<xsl:value-of select="../@firstName"/>
				</developer>
			</application>
		</xsl:for-each>
		</applications>
	</xsl:template>
</xsl:stylesheet>