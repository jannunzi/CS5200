<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
	<xsl:output method="xml" indent="yes"/>
	<xsl:template match="/">
		<slots>
			<xsl:for-each select="organizers/organizer/sheet/time-slot">
				<slot>
					<xsl:attribute name="when">
						<xsl:value-of select="@slotDate"/>
					</xsl:attribute>
					<xsl:attribute name="who">
						<xsl:value-of select="@who"/>
					</xsl:attribute>
					<where>
						<xsl:attribute name="street1">
							<xsl:value-of select="../where/@street1"/>
						</xsl:attribute>
						<xsl:attribute name="street2">
							<xsl:value-of select="../where/@street2"/>
						</xsl:attribute>
						<xsl:attribute name="city">
							<xsl:value-of select="../where/@city"/>
						</xsl:attribute>
						<xsl:attribute name="state">
							<xsl:value-of select="../where/@state"/>
						</xsl:attribute>
						<xsl:attribute name="zip">
							<xsl:value-of select="../where/@zip"/>
						</xsl:attribute>
					</where>
					<organizer>
						<xsl:attribute name="firstName">
							<xsl:value-of select="../../@firstName"/>
						</xsl:attribute>
						<xsl:attribute name="lastName">
							<xsl:value-of select="../../@lastName"/>
						</xsl:attribute>
						<xsl:attribute name="email">
							<xsl:value-of select="../../@email"/>
						</xsl:attribute>
					</organizer>
				</slot>
			</xsl:for-each>
		</slots>
	</xsl:template>
</xsl:stylesheet>