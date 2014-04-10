<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
	<xsl:output method="xml" indent="yes"/>
	<xsl:template match="/">
		<actors>
			<xsl:for-each select="directors/director/movie/actor">
			<xsl:sort select="@lastName" order="descending"/>
			<actor>
				<first><xsl:value-of select="@firstName"/></first>
				<last><xsl:value-of select="@lastName"/></last>
				<movie><xsl:value-of select="../@name"></xsl:value-of></movie>
				<director>
					<first><xsl:value-of select="../../@firstName"></xsl:value-of></first>
					<last><xsl:value-of select="../../@lastName"></xsl:value-of></last>
				</director>
			</actor>
			</xsl:for-each>
		</actors>
	</xsl:template>
</xsl:stylesheet>