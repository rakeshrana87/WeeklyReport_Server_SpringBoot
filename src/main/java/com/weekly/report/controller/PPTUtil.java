package com.weekly.report.controller;

import java.awt.Color;
import java.awt.Rectangle;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;

import org.apache.poi.xslf.usermodel.SlideLayout;
import org.apache.poi.xslf.usermodel.XMLSlideShow;
import org.apache.poi.xslf.usermodel.XSLFShape;
import org.apache.poi.xslf.usermodel.XSLFSlide;
import org.apache.poi.xslf.usermodel.XSLFSlideLayout;
import org.apache.poi.xslf.usermodel.XSLFSlideMaster;
import org.apache.poi.xslf.usermodel.XSLFTable;
import org.apache.poi.xslf.usermodel.XSLFTableCell;
import org.apache.poi.xslf.usermodel.XSLFTableRow;
import org.apache.poi.xslf.usermodel.XSLFTextParagraph;
import org.apache.poi.xslf.usermodel.XSLFTextRun;
import org.apache.poi.xslf.usermodel.XSLFTextShape;

import com.weekly.report.pojo.WeeklyReport;

public class PPTUtil {
private static int count=0;
	public static void createPptFromHtml(WeeklyReport weeklyReport) throws IOException {
		count++;
		System.out.println("report for : " + weeklyReport.getAssigneTeam());
		
		// creating a new empty slide show
		XMLSlideShow ppt = new XMLSlideShow();
		
		// getting the slide master object
		XSLFSlideMaster slideMaster = ppt.getSlideMasters().get(0);

		// get the desired slide layout
		XSLFSlideLayout titleLayout = slideMaster.getLayout(SlideLayout.TITLE_AND_CONTENT);

		// creating a slide with title layout
		XSLFSlide slide1 = ppt.createSlide(titleLayout);
		XSLFTextShape shape = slide1.getPlaceholder(0);
		shape.setText(weeklyReport.getTitle());
		shape.addNewTextParagraph().addLineBreak();
		shape.addNewTextParagraph().addNewTextRun().setText("line break");
		XSLFShape tableShape = slide1.getPlaceholder(1);

		// selecting the place holder in it
		// XSLFTextShape[] titles = slide1.getPlaceholders();

		// selection of body placeholder
		// body.clearText();

		XSLFTable table = createTable(4, 3, slide1, weeklyReport);
		
		// setting the title init
		
		// creating an FileOutputStream object
		File file = new File("c://users/ezraksi//reactApp//"+weeklyReport.getAssigneTeam()+weeklyReport.hashCode()+".pptx");
		FileOutputStream out;
		try {
			out = new FileOutputStream(file);

			// saving the changes to a file
			ppt.write(out);
			System.out.println("Presentation created successfully");
			out.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
public static void  createCsvFile(WeeklyReport weeklyReport) {
	count ++;
	File file = new File("c://users/ezraksi//reactApp//"+weeklyReport.getAssigneTeam()+weeklyReport.hashCode()+count+"sample.csv");
	FileWriter fw=null;
	try {
		StringBuilder builder = new StringBuilder();
		
		builder.append(weeklyReport.getAssigneTeam());
		builder.append("\n");
		builder.append(weeklyReport.getEstimatedhours());
		builder.append(weeklyReport.getImpediments());
		builder.append(weeklyReport.getProgress_of_US());
		builder.append(weeklyReport.getRemainingHours());
		builder.append(weeklyReport.getSummary());
		 fw = new FileWriter(file);
		fw.write(builder.toString());
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}finally {
		try {
			fw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
	private static XSLFTable createTable(int numColumns, int numRows, XSLFSlide slide, WeeklyReport weeklyReport) {

		XSLFTable tbl = slide.createTable();
		tbl.setAnchor(new Rectangle(50, 50, 450, 300));

		// rows

		for (int rownum = 0; rownum < numRows; rownum++) {
			XSLFTableRow tr = tbl.addRow();
			tr.setHeight(50);

			for (int i = 0; i < numColumns; i++) {
				XSLFTableCell cell1 = tr.addCell();

			//	cell1.setText("Cell1" + (i + 1));

				// tr2.mergeCells(0, last);;
				XSLFTextParagraph p1 = cell1.addNewTextParagraph();
				XSLFTextRun r1 = p1.addNewTextRun();

			//	r1.setText("Cell3 " + (1 + 1));
				if (i % 2 == 0)
					cell1.setFillColor(new Color(208, 216, 232));
				else
					cell1.setFillColor(new Color(233, 247, 244));

			}
		}
		int count = 0;
		for (XSLFTableRow row : tbl.getRows()) {
			if (count == 0) {
				XSLFTableCell cell0 = row.getCells().get(0);
				cell0.setText("Summary/Overall progress/Risks:" + weeklyReport.getSummary() + "\n" + "data \n "
						+ "Impediments:" + weeklyReport.getImpediments() + " \n");
			}
			if (count == 1) {
				XSLFTableCell cell0 = row.getCells().get(0);
				cell0.setText("Decisions/Actions needed:");
			}
			if (count < 2) {
				row.mergeCells(0, numColumns - 1);
			}
			if (count >= 2) {
				XSLFTableCell cell0 = row.getCells().get(0);
				cell0.setText("Overall Progress: " + weeklyReport.getProgress_of_US());
				XSLFTableCell cell1 = row.getCells().get(1);
				XSLFTableCell cell2 = row.getCells().get(2);
				cell1.setText("Actual hours estimated" + weeklyReport.getEstimatedhours() + "\n" + "Remaining hours : "
						+ weeklyReport.getRemainingHours());
				System.out.println("weekly,,,,,,,,," + weeklyReport.getEstimatedhours() + "\n" + "weessascs,......."
						+ weeklyReport.getRemainingHours() + "cell remainingours: " + cell1.getText());
				cell2.setText("Status Planned delivery  to master \n" + "image \n " + "Status current sprint \n");
			}
			count++;
		}

		return tbl;

	}

}
