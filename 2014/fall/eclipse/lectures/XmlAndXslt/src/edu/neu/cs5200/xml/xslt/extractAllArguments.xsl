<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="2.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

	<xsl:output method="xml" indent="yes"></xsl:output>

	<xsl:template match="/">
		<Arguments>
			<xsl:apply-templates></xsl:apply-templates>
		</Arguments>
	</xsl:template>
	
	<xsl:template match="/CLASS-INSTANCES/CLASS-INSTANCE/BLL-INSTANCES/BLL-INSTANCE/ARGUMENT">
		<Argument>
			<Name>
				<xsl:value-of select="@NAME"></xsl:value-of>
			</Name>
			<Type>
				<xsl:value-of select="@DATA_TYPE"></xsl:value-of>
			</Type>
			<Description>
				<xsl:value-of select="@DESCRIPTION"></xsl:value-of>
			</Description>
		</Argument>
	</xsl:template>
	
</xsl:stylesheet>