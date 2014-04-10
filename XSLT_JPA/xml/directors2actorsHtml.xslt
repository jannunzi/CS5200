<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
	<xsl:template match="/">
		<html>
		<head>
		<link href="css/bootstrap.css" rel="stylesheet"/>
		</head>
		<body>
		<h1>Hello World</h1>
		<table class="table">
			<thead>
				<tr>
					<th>First</th>
					<th>Last</th>
					<th>Movies</th>
				</tr>
			</thead>
			<tbody>
				<xsl:for-each select="directors/director/movie/actor">
				<xsl:choose>
					<xsl:when test="@oscars &gt; 4">
						<tr style="background-color:red">
							<td><xsl:value-of select="@firstName"/></td>
							<td><xsl:value-of select="@lastName"/></td>
							<td>
								<xsl:value-of select="../@name"></xsl:value-of>
							</td>
						</tr>
					</xsl:when>
					<xsl:when test="@oscars &gt; 2">
						<tr style="background-color:yellow">
							<td><xsl:value-of select="@firstName"/></td>
							<td><xsl:value-of select="@lastName"/></td>
							<td>
								<xsl:value-of select="../@name"></xsl:value-of>
							</td>
						</tr>
					</xsl:when>
					<xsl:otherwise>
						<tr>
							<td><xsl:value-of select="@firstName"/></td>
							<td><xsl:value-of select="@lastName"/></td>
							<td>
								<xsl:value-of select="../@name"></xsl:value-of>
							</td>
						</tr>
					</xsl:otherwise>
				</xsl:choose>
				</xsl:for-each>
			</tbody>
		</table>
		</body>
		</html>
	</xsl:template>
</xsl:stylesheet>