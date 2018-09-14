package com.weekly.report.controller;

import java.util.List;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.codec.Base64;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import com.itextpdf.text.html.simpleparser.HTMLWorker;
import com.itextpdf.text.pdf.PdfWriter;
import com.weekly.report.pojo.Card;
import com.weekly.report.pojo.ConfigProperties;
import com.weekly.report.pojo.Properties;
import com.weekly.report.pojo.Property;
import com.weekly.report.pojo.WeeklyReport;

public class WeeklyReportController_unsed {

	private List<Card> extractContent(Document doc) {
		List<Card> card = new ArrayList<>();
		Map<String, String> card_detail = new HashMap<String, String>();
		NodeList nodeList = doc.getDocumentElement().getChildNodes();

		for (int i = 0; i < nodeList.getLength(); i++) {

			// We have encountered an <employee> tag.
			Node node = nodeList.item(i);
			if (node instanceof Element) {

				NodeList childNodes = node.getChildNodes();

				for (int j = 0; j < childNodes.getLength(); j++) {
					Node cNode = childNodes.item(j);
					String name = cNode.getNodeName();
					// System.out.println( "node name: " + cNode.getNodeName());
					// Identifying the child tag of employee encountered.
					if (cNode instanceof Element) {
						String content = cNode.getLastChild().getTextContent().trim();
						// System.out.println("content: " + content);
						// card_detail.put(name,content);
						Card cards = new Card();
						switch (cNode.getNodeName()) {
						case "card_type":
							cards.setCard_type(content);
							System.out.println(".........card type" + content);
							break;
						case "version":
							cards.setVersion(content);
							System.out.println(".........version" + content);
							break;
						case "location":
							cards.setNumber(content);
							System.out.println(".........card location" + content);
							break;

						case "number":
							cards.setNumber(content);
							System.out.println(".........car number" + content);
							break;

						case "id":
							cards.setId(content);
							System.out.println(".........car id" + content);
							break;

						case "name":
							cards.setName(content);
							System.out.println(".........card name" + content);
							break;
						}

						card.add(cards);
					}
				}
			}
		}
		// System.out.println(".........card......aad......." + card_detail );
		System.out.println("list size : " + card.size());
		return card;
	}

	private void writePdfFromHtml(String nodeName) {
		System.out.println("insidded:......... wite to pdf file");
		try {

			System.out.println("describe: " + nodeName);
			// String k = "<html><body> This is my Project </body></html>";

			OutputStream file = new FileOutputStream(new File("Test.pdf"));
			com.itextpdf.text.Document document = new com.itextpdf.text.Document();
			PdfWriter.getInstance(document, file);
			document.open();
			HTMLWorker htmlWorker = new HTMLWorker(document);
			// String fileContent=FileUtils.readFileToString(/*new
			// File("C:\\Users\\ezraksi\\git\\WeeklyReport_Server_SpringBoot\\WeeklyReport_Server_SpringBoot\\Protocol
			// Message Specification SDP Account Management (RPC) Protocol Version
			// 5.x.html"));
			htmlWorker.parse(new StringReader(nodeName));

			document.close();
			file.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
