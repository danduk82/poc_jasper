package danduk.poc.jasper;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;

public class JasperStuffGenerator {
	public static void main(String[] args) throws Exception {
		try {
			//File reportFile = new File("/home/aborghi/JaspersoftWorkspace/MyReports/Blank_A4.jrxml");
			String reportFile = "/home/aborghi/JaspersoftWorkspace/MyReports/Blank_A4.jrxml";
			String outputPdfFile = "/home/aborghi/test/jasper/simple.pdf";
			List<Item> itemList = new ArrayList<Item>();
			
			Item A = new Item();
			A.setName("A");
			A.setPrice(10000.0);
			itemList.add(A);
			
			Item B = new Item();
			B.setName("B");
			B.setPrice(990.0);
			itemList.add(B);
			
			JRDataSource jrDataSource = new JRBeanCollectionDataSource(itemList);
			Map<String, Object> parameters = new HashMap<String, Object>();
			parameters.put("CollectionBeanParam", jrDataSource);
			
			// load jrxml and compile it
			JasperDesign jasperDesign = JRXmlLoader.load(reportFile);
			JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);
			/* Using compiled version(.jasper) of Jasper report to generate PDF */
			JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, new JREmptyDataSource());
			/* outputStream to create PDF */
	        OutputStream outputStream = new FileOutputStream(new File(outputPdfFile));
	        /* Write content to PDF file */
	        JasperExportManager.exportReportToPdfStream(jasperPrint, outputStream);
	
	        System.out.println("File Generated");
		} catch  (JRException ex) {
            ex.printStackTrace();
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        }
		
	}
}
