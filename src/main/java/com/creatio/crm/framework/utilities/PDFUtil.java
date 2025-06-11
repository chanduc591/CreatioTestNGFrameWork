package com.creatio.crm.framework.utilities;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

public class PDFUtil {
	
	public static String getPDFText(String fileName) {
		String text = "";
		
		try {
			
			//read the File
			FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+"\\Files\\"+fileName);
			
			//Load pdf file into PDDocument class to analyze the PDF file
			PDDocument document = PDDocument.load(fis);
			
			//Copy Text from PDF file
			PDFTextStripper pdf = new PDFTextStripper();
			
			//Adding specific pages to copy text
			pdf.setStartPage(2);
			pdf.setEndPage(2);
			
			text = pdf.getText(document);			
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}		
		
		return text;
	}

}
