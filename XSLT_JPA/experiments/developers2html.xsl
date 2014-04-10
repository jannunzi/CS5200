<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
	<xsl:template match="/">
		<html>
		<head>
		<link href="css/bootstrap.css" rel="stylesheet"/>
		</head>
		<body>
		<h1>Developers</h1>
		<table class="table">
			<thead>
				<tr>
					<th>First Name</th>
					<th>Applications</th>
				</tr>
			</thead>
			<xsl:for-each select="team/developer">
			<xsl:sort select="@salary"/>
			<tbody>
				<tr>
					<td><xsl:value-of select="@firstName"/></td>
					<td>
						<ul>
							<xsl:for-each select="application">
							<xsl:choose>
								<xsl:when test="@price = 0.0">
									<li style="background-color:beige"><xsl:value-of select="@name"/></li>
								</xsl:when>
								<xsl:otherwise>
									<li><xsl:value-of select="@name"/></li>
								</xsl:otherwise>
							</xsl:choose>
							</xsl:for-each>
						</ul>
					</td>
				</tr>
			</tbody>
			</xsl:for-each>
		</table>
		</body>
		</html>
	</xsl:template>
</xsl:stylesheet>