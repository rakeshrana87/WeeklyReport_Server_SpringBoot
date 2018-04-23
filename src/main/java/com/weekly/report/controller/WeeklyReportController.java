package com.weekly.report.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

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
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.weekly.report.pojo.Card;
import com.weekly.report.pojo.Properties;
import com.weekly.report.pojo.Property;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@EnableZuulProxy
@EnableDiscoveryClient
public class WeeklyReportController {

	@RequestMapping(path = "get/carddetails", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public Card getCardDetails() {
		System.out.println("get");
		return parseXml(null);
	}

	@RequestMapping(path = "/create/carddetails", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes=MediaType.APPLICATION_JSON_VALUE)
	public Card postCardDetails(@RequestBody Card card) {

		return card;
	}

	@RequestMapping(path = "/update/carddetails", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE,consumes=MediaType.APPLICATION_JSON_VALUE)
	public Card updateCardDetails(@RequestBody Card card) {

		return card;
	}

	@RequestMapping(path = "/delete/carddetails", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
	public String deleteCardDetails(@RequestBody Card card) {

		return " delete weekly demo";
	}

	public Card thelloWorld() {
		RestTemplate restTemplate = new RestTemplate();
		
		String plainCreds = "";
		byte[] plainCredsBytes = plainCreds.getBytes();
		byte[] base64CredsBytes = Base64.encode(plainCredsBytes);
		String base64Creds = new String(base64CredsBytes);
		System.out.println("base64: " + base64Creds);
		HttpHeaders headers = new HttpHeaders();
		headers.add("Authorization", "Basic " + base64Creds);
		HttpEntity<String> request = new HttpEntity<String>(headers);
		ResponseEntity<String> response = restTemplate.exchange(
				"https://mingle.epk.ericsson.se/api/v2/projects/classic_sdp/cards/2039.xml", HttpMethod.GET, request,
				String.class);
		String mingleDetails = null;// response.getBody();

		return parseXml(mingleDetails);

	}

	private Card parseXml(String mingleDetails) {
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder db = null;
Card card = new Card();
Properties properties = new Properties();

List<Property> propertyList = new ArrayList<>();


		try {
			 db = dbf.newDocumentBuilder();
			File file = new File("C:\\Users\\ezraksi\\my-file.xml");

			Document doc = db.parse(file);
			System.out.println("Root element :" + doc.getDocumentElement().getNodeName());

			NodeList nList = doc.getElementsByTagName("card");

			System.out.println("----------------------------" + nList.getLength());

			for (int temp = 0; temp < nList.getLength(); temp++) {

				Node nNode = nList.item(temp);

				// System.out.println("\nCurrent Element :" + nNode.getNodeName());

				if (nNode.getNodeType() == Node.ELEMENT_NODE) {

					Element eElement = (Element) nNode;
					System.out.println("value" + eElement.getNodeValue());
					// System.out.println("text" +eElement.getTextContent());
					System.out.println("name....." + eElement.getElementsByTagName("name").item(temp).getNodeName());
					System.out.println("text: " + eElement.getElementsByTagName("name").item(temp).getTextContent());
					System.out.println("map1.. " + eElement.getElementsByTagName("name").item(temp).getNodeValue());
					// System.out.println("map....." +
					// eElement.getElementsByTagName("description").item(0).getNodeName());
					System.out.println(
							"cardtype.. " + eElement.getElementsByTagName("card_type").item(temp).getTextContent());
					
		try {
					card.setCard_type(eElement.getElementsByTagName("card_type").item(temp).getTextContent());
					card.setCreated_by(eElement.getElementsByTagName("created_by").item(temp).getTextContent());
					//card.setDescription(eElement.getElementsByTagName("description").item(temp).getTextContent());
					card.setModified_by(eElement.getElementsByTagName("modified_by").item(temp).getTextContent());
					card.setName(eElement.getElementsByTagName("name").item(temp).getTextContent());
					card.setProject(eElement.getElementsByTagName("project").item(temp).getTextContent());
					card.setProperties(properties);
					card.setRendered_description(eElement.getElementsByTagName("rendered_description").item(temp).getTextContent());
					card.setTags(eElement.getElementsByTagName("tags").item(temp).getTextContent());
					card.setVersion(Integer.parseInt(eElement.getElementsByTagName("version").item(temp).getTextContent()));
					card.setId(Integer.parseInt(eElement.getElementsByTagName("id").item(temp).getTextContent()));
					card.setProject_card_rank(Integer.parseInt(eElement.getElementsByTagName("project_card_rank").item(temp).getTextContent()));
					card.setModified_on(eElement.getElementsByTagName("modified_on").item(temp).getTextContent());
					card.setCreated_on(eElement.getElementsByTagName("created_on").item(temp).getTextContent());
					card.setNumber(Integer.parseInt(eElement.getElementsByTagName("number").item(temp).getTextContent()));
					
		}catch(Exception e) {
			e.printStackTrace();
		}
					System.out.println("perperties....."
							+ eElement.getElementsByTagName("properties").item(temp).getChildNodes().getLength());

					NodeList nodlIts = eElement.getElementsByTagName("properties").item(temp).getChildNodes();
					for (int temp1 = 0; temp1 < nodlIts.getLength() - 1; temp1++) {
						Property property = new Property();
						Element eElementT = (Element) nodlIts;
						// System.out.println("text" +eElement.getTextContent());
						// System.out.println("name....." + nodlIts.item(temp1).getTextContent());
						if (eElementT.getElementsByTagName("name").item(temp1) == null
								|| eElementT.getElementsByTagName("value").item(temp1) == null) {
							break;

						} else {
							System.out.println(
									"name: " + eElementT.getElementsByTagName("name").item(temp1).getTextContent());
							property.setName(eElementT.getElementsByTagName("name").item(temp1).getTextContent()); 
							
							System.out.println(
									"value: " + eElementT.getElementsByTagName("value").item(temp1).getTextContent());
							property.setValue(eElementT.getElementsByTagName("value").item(temp1).getTextContent());
						propertyList.add(property);
						}

					}
				properties.setProperty(propertyList);
card.setProperties(properties);
					Node tempNode = eElement.getElementsByTagName("properties").item(temp);
					

				}
			}
		} catch (SAXException e) {
			e.printStackTrace();
			// handle SAXException
		} catch (IOException e) {
			e.printStackTrace();
			// handle IOException
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return card;
	}

}
