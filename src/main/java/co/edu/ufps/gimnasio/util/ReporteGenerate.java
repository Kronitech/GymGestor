package co.edu.ufps.gimnasio.util;


import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import co.edu.ufps.gimnasio.model.entity.UsuarioMembresia;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRXlsExporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
@Service
public class ReporteGenerate {
	
	
	 public byte[] exportToPdf(Map<String, Object> params) throws JRException, FileNotFoundException {
	        return JasperExportManager.exportReportToPdf(getReport(params));
	    }

	

	 private JasperPrint getReport(Map<String, Object> params) throws FileNotFoundException, JRException {
	        InputStream jrxmlInputStream = getJrxmlInputStream();
	        JasperPrint report = JasperFillManager.fillReport(
	            JasperCompileManager.compileReport(jrxmlInputStream),
	            params,
	            new JREmptyDataSource()
	        );

	        return report;
	    }

	    private InputStream getJrxmlInputStream() throws FileNotFoundException {
	        // Intenta cargar el archivo desde el sistema de archivos en el entorno de desarrollo
	        try {
	            return new FileInputStream(ResourceUtils.getFile("classpath:reporte.jrxml"));
	        } catch (FileNotFoundException e) {
	            // Si no se encuentra en el sistema de archivos, intenta cargar desde el classpath
	            return getClass().getResourceAsStream("/reporte.jrxml");
	        }
	    }
	    public byte[ ] comprobantePago(Map<String, Object> params)throws JRException, FileNotFoundException {
			   
			   return JasperExportManager.exportReportToPdf(getComprobante(params));
		   }
	    private JasperPrint getComprobante(Map<String, Object> params) throws FileNotFoundException, JRException {
	        InputStream jrxmlInputStream = getJrxmlInputStreamComprobante();
	        JasperPrint report = JasperFillManager.fillReport(
	            JasperCompileManager.compileReport(jrxmlInputStream),
	            params,
	            new JREmptyDataSource()
	        );

	        return report;
	    }

	    private InputStream getJrxmlInputStreamComprobante() throws FileNotFoundException {
	        // Intenta cargar el archivo desde el sistema de archivos en el entorno de desarrollo
	        try {
	            return new FileInputStream(ResourceUtils.getFile("classpath:recibo.jrxml"));
	        } catch (FileNotFoundException e) {
	            // Si no se encuentra en el sistema de archivos, intenta cargar desde el classpath
	            return getClass().getResourceAsStream("/recibo.jrxml");
	        }
	    }
	    /*
	    public byte[] exportToXls(List<UsuarioMembresia> list) throws JRException, FileNotFoundException {
	        ByteArrayOutputStream byteArray = new ByteArrayOutputStream();
	        SimpleOutputStreamExporterOutput output = new SimpleOutputStreamExporterOutput(byteArray);
	        JRXlsExporter exporter = new JRXlsExporter();
	        exporter.setExporterInput(new SimpleExporterInput(getReport(list,params)));
	        exporter.setExporterOutput(output);
	        exporter.exportReport();
	        output.close();
	        return byteArray.toByteArray();
	    }
	    */
}

