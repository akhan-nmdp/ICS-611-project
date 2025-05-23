package com.baeldung.web.reports;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.export.JRXlsAbstractExporterParameter;
import net.sf.jasperreports.engine.export.JRXlsExporter;

/**
 * = JasperReportsXlsExporter
 * 
 * A JasperReports exporter to export the report in XLS formats. This class
 * implements the interface {@link JasperReportsExporter}
 */
public class JasperReportsXlsExporter implements JasperReportsExporter {

	/**
	 * Generates a ByteArrayOutputStream from the provided JasperReport using
	 * the {@link JRXlsExporter}. After that, the generated bytes array is
	 * written in the {@link HttpServletResponse}
	 * 
	 * @param jp
	 *            The generated JasperReport.
	 * @param fileName
	 *            The fileName of the exported JasperReport
	 * @param response
	 *            The HttpServletResponse where generated report has been
	 *            written
	 * @throws JRException
	 *             during JasperReport export.
	 * @throws IOException
	 *             when writes the ByteArrayOutputStream into the
	 *             HttpServletResponse
	 */
	@Override
	public void export(JasperPrint jp, String fileName, HttpServletResponse response) throws JRException, IOException {

		ByteArrayOutputStream baos = new ByteArrayOutputStream();

		// Create a JRXlsExporter instance
		JRXlsExporter exporter = new JRXlsExporter();

		// Here we assign the parameters jp and baos to the exporter
		exporter.setParameter(JRExporterParameter.JASPER_PRINT, jp);
		exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, baos);

		// Excel specific parameters
		exporter.setParameter(JRXlsAbstractExporterParameter.IS_ONE_PAGE_PER_SHEET, Boolean.FALSE);
		exporter.setParameter(JRXlsAbstractExporterParameter.IS_REMOVE_EMPTY_SPACE_BETWEEN_ROWS, Boolean.TRUE);
		exporter.setParameter(JRXlsAbstractExporterParameter.IS_WHITE_PAGE_BACKGROUND, Boolean.FALSE);

		// Retrieve the exported report in PDF format
		exporter.exportReport();

		// Specifies the response header
		response.setHeader("Content-Disposition", "inline; filename=" + fileName);

		// Make sure to set the correct content type
		// Each format has its own content type
		response.setContentType("application/vnd.ms-excel");
		response.setContentLength(baos.size());

		// Retrieve the output stream
		ServletOutputStream outputStream = response.getOutputStream();
		// Write to the output stream
		baos.writeTo(outputStream);
		// Flush the stream
		outputStream.flush();

	}
}