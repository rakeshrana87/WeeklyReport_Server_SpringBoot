package com.weekly.report.controller;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
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

import com.weekly.report.pojo.Card;
import com.weekly.report.pojo.ConfigProperties;
import com.weekly.report.pojo.Properties;
import com.weekly.report.pojo.Property;
import com.weekly.report.pojo.WeeklyReport;


@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@EnableZuulProxy
@EnableDiscoveryClient
public class WeeklyReportController {

	private ConfigProperties config;
	
	@Autowired
	private WeeklyReport WeeklyReport;

	@Autowired
	public void setConfig(ConfigProperties config) {
		this.config = config;

		System.out.println("config...................." + config.getUsername());
	}

	@RequestMapping(value = "downloadFile", method = RequestMethod.GET)
	public StreamingResponseBody getSteamingFile(HttpServletResponse response) throws IOException {
		response.setContentType("application/pdf");
		response.setHeader("Content-Disposition", "attachment; filename=\"Test.pdf\"");
		InputStream inputStream = new FileInputStream(new File(
				"C:\\Users\\ezraksi\\git\\WeeklyReport_Server_SpringBoot\\WeeklyReport_Server_SpringBoot\\Test.pdf"));
		return outputStream -> {
			int nRead;
			byte[] data = new byte[1024];
			while ((nRead = inputStream.read(data, 0, data.length)) != -1) {
				System.out.println("Writing some bytes..");
				outputStream.write(data, 0, nRead);
			}
		};
	}

	@RequestMapping(value = "ppt/downloadFile", method = RequestMethod.GET, produces = "application/pptx")
	public StreamingResponseBody getSteamingFilePppt(HttpServletResponse response) throws IOException {
		response.setContentType("application/pptx");
		response.setHeader("Content-Disposition", "attachment; filename=\"Weekly_Plan.pptx\"");
		InputStream inputStream = new FileInputStream(new File(
				"C:\\Users\\ezraksi\\git\\WeeklyReport_Server_SpringBoot\\WeeklyReport_Server_SpringBoot\\Weekly_Plan.pptx"));
		return outputStream -> {
			int nRead;
			byte[] data = new byte[1024];
			while ((nRead = inputStream.read(data, 0, data.length)) != -1) {
				System.out.println("Writing some bytes..");
				outputStream.write(data, 0, nRead);
			}
		};
	}

	@RequestMapping(path = "get/carddetails/xml", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Card> getCardDetailsFromXml() {
		System.out.println("get....................." + config.getUsername());
		return parseXml(null);
	}
	@RequestMapping(path = "get/carddetails", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Card> getCardDetails() {
		System.out.println("get....................." + config.getUsername());
		return getCardDetail();
	}

	@RequestMapping(path = "/create/carddetails", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public Card postCardDetails(@RequestBody Card card) {

		return card;
	}

	@RequestMapping(path = "/update/carddetails", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public void updateCardDetails(/*@RequestBody Card card*/) {
		String s="https://mingle.epk.ericsson.se/api/v2/projects/classic_sdp/cards/4232.xml?card[name]=Updated dummy card for Heckathan6 &card[properties][][name]=Estimated hours&card[properties][][value]=15&card[properties][][name]=Remaining hours&card[properties][][value]=15";
		updateCardId(s);
	//	return card;
	}

	private void updateCardId(String s) {
		// TODO Auto-generated method stub
		RestTemplate restTemplate = new RestTemplate();

		String plainCreds = config.getUsername() + ":" + config.getPassword();
		System.out.println("plaintCresd......" + plainCreds);
		byte[] plainCredsBytes = plainCreds.getBytes();
		byte[] base64CredsBytes = Base64.encode(plainCredsBytes);
		String base64Creds = new String(base64CredsBytes);
		System.out.println("base64: " + base64Creds);
		HttpHeaders headers = new HttpHeaders();
		headers.add("Authorization", "Basic " + base64Creds);
		HttpEntity<String> request = new HttpEntity<String>(headers);
		try {
		ResponseEntity<String> response = restTemplate.exchange(
				s, HttpMethod.PUT, request,
				String.class);
		String mingleDetails = response.getBody();
		 System.out.println("Resposne is: " + mingleDetails);
		writeToFile(mingleDetails);
		}catch(Exception e) {
			e.printStackTrace();
		}
		}

	@RequestMapping(path = "/delete/carddetails", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
	public String deleteCardDetails(@RequestBody Card card) {

		return " delete weekly demo";
	}

	private List<Card> getCardDetail() {
		RestTemplate restTemplate = new RestTemplate();

		String plainCreds = config.getUsername() + ":" + config.getPassword();
		System.out.println("plaintCresd......" + plainCreds);
		byte[] plainCredsBytes = plainCreds.getBytes();
		byte[] base64CredsBytes = Base64.encode(plainCredsBytes);
		String base64Creds = new String(base64CredsBytes);
		System.out.println("base64: " + base64Creds);
		HttpHeaders headers = new HttpHeaders();
		headers.add("Authorization", "Basic " + base64Creds);
		HttpEntity<String> request = new HttpEntity<String>(headers);
		ResponseEntity<String> response = restTemplate.exchange(
				"https://mingle.epk.ericsson.se/api/v2/projects/classic_sdp/cards.xml", HttpMethod.GET, request,
				String.class);
		String mingleDetails = response.getBody();
		 System.out.println("Resposne is: " + mingleDetails);
		writeToFile(mingleDetails);
		
	return parseXml(mingleDetails);

	}

	private void writeToFile(String mingleDetails) {
		File file = new File("abcd.xml");
		try (FileWriter fileWriter = new FileWriter(file)) {
			BufferedWriter bfw = new BufferedWriter(fileWriter);

			bfw.write(mingleDetails);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		//parseXml("");
	}
	
	private List<Card> parseXml(String mingleDetails) {
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder db = null;
		List<Card> cards = new ArrayList<>();

		try {
			
			  InputSource is = new InputSource();
			  is.setCharacterStream(new
			  StringReader(mingleDetails));
			 

			db = dbf.newDocumentBuilder();
			File file = new File("Cards_detail.xml");

			Document doc = db.parse(file);
		//	System.out.println("Root element :" + doc.getDocumentElement().getNodeName());
			// cards= extractContent(doc);
			cards = parsingByTag(doc);
			Map<String, List<Card>> map = sortTeamWise(cards);
			extractFieldsRequired(map);

			// need to write ppt as per Team and extract fields for the same .
			// then with tht object write to ppt.
			// which will donloaded from server.

			// insert /update operations are alspe supported where need to make new card in
			// team first need to consult and see permissions are present or not
		} catch (SAXException e) {
			e.printStackTrace();
			// handle SAXException
		} catch (IOException e) {
			e.printStackTrace();
			// handle IOException
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("exception occured ");
		}
		return cards;
	}

	private void extractFieldsRequired(Map<String, List<Card>> map) {
		
		for(String sitring: map.keySet()) {
			System.out.println("key system .............................../n \n" + sitring );
		List<Card> card=map.get(sitring);
		for(Card crd:card) {
			
		createReportForTeam( crd);
		}
		}
		}

	private void createReportForTeam(Card card) {
	for(Property prop:card.getProperties().getProperty()) {

			switch (prop.getName()) {
			case "estimatedhours":
				WeeklyReport.setEstimatedhours(prop.getValue());
				break;
			case "remainingHours":
				WeeklyReport.setRemainingHours(prop.getValue());
				break;
			case "assigneTeam":
				WeeklyReport.setAssigneTeam(prop.getValue());
				break;
			case "progress_of_US":
				WeeklyReport.setProgress_of_US(prop.getValue());
				break;
			case "totalETremaininghours":
				WeeklyReport.setTotalETremaininghours(prop.getValue());
				break;
			case "Assigned team":
				WeeklyReport.setAssigneTeam(prop.getValue());
				break;
				
			/*case "totalETactualhours":
				WeeklyReport.setTotalETactualhours(prop.getValue());
				break;
			case "totalETactualhours":
				WeeklyReport.setTotalETactualhours(prop.getValue());
				break;*/
			default:
				WeeklyReport.setTitle("NIp .sdsdsdasdsd");
				WeeklyReport.setSummary("ek ladki ko dekha \n " + "to aise lga jaise \n");
				WeeklyReport.setImpediments("khuch na chl rha sab bakwas:");
				break;
			}
	}
	
	try {
		PPTUtil.createCsvFile(WeeklyReport);
		PPTUtil.createPptFromHtml(WeeklyReport);
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	}

	private Map<String, List<Card>> sortTeamWise(List<Card> cards) {
		Map<String, List<Card>> mapList = new HashMap<>();
		for(Card card :cards) {
	for(Property prop: card.getProperties().getProperty()) {
		if(prop.getName().equalsIgnoreCase("Assigned team")){
if(mapList.get(prop.getValue())!=null) {
	mapList.get(prop.getValue()).add(card);
		}
else {
	List<Card> cardList = new ArrayList<>();
	cardList.add(card);
	mapList.put(prop.getValue(), cardList)	;
		}

}

	}
		
		
		}

		System.out.println("Sorted Map: " + mapList);
		System.out.println("map list: "  + mapList.keySet().size());
		return mapList;
	
	}
	private List<Card> parsingByTag(Document doc) {
	List<Card> cards = new ArrayList<Card>();
		//	NodeList nListCards = doc.getElementsByTagName("cards");
		int count =0;
		//for (int temp1 = 0; temp1 < nListCards.getLength(); temp1++) {
		
			NodeList nList = doc.getElementsByTagName("card");
			
//card list....
			for (int temp = 0; temp < nList.getLength(); temp++) {
				Card card = new Card();
				
				List<Property> propertyList = new ArrayList<>();
				Node nNode = nList.item(temp);
				
	//	for (int temp1 = 0; temp1 < nNode.getChildNodes().getLength() - 1; temp1++) {
		//	String nn=nNode.getChildNodes().item(temp1).getNodeValue();
		//	System.out.println("vaue: " + nn);
			if (nNode.getNodeType() == Node.ELEMENT_NODE) {
						
					Element eElement = (Element) nNode;
					if (eElement.getElementsByTagName("description") == null) {
					//	writePdfFromHtml(eElement.getElementsByTagName("description").item(temp).getTextContent());
				//		break;
					}
					if (eElement.getElementsByTagName("card_type") == null || eElement.getElementsByTagName("card_type").item(0)==null) {
						count++;
						continue;
					}
					count--;
					card.setCard_type(eElement.getElementsByTagName("card_type").item(0).getTextContent());
					card.setCreated_by(eElement.getElementsByTagName("created_by").item(0).getTextContent());
					

					// card.setDescription(eElement.getElementsByTagName("description").item(temp).getTextContent());
					card.setModified_by(eElement.getElementsByTagName("modified_by").item(0).getTextContent());
					card.setName(eElement.getElementsByTagName("name").item(0).getTextContent());
					card.setProject(eElement.getElementsByTagName("project").item(0).getTextContent());
					card.setRendered_description(
							eElement.getElementsByTagName("rendered_description").item(0).getTextContent());
					card.setTags(eElement.getElementsByTagName("tags").item(0).getTextContent());
					card.setVersion(
							eElement.getElementsByTagName("version").item(0).getTextContent());
					card.setId(eElement.getElementsByTagName("id").item(0).getTextContent());
					card.setProject_card_rank(Integer.parseInt(
							eElement.getElementsByTagName("project_card_rank").item(0).getTextContent()));
					card.setModified_on(eElement.getElementsByTagName("modified_on").item(0).getTextContent());
					card.setCreated_on(eElement.getElementsByTagName("created_on").item(0).getTextContent());
					card.setNumber(
							eElement.getElementsByTagName("number").item(0).getTextContent());
					System.out.println("card .........: " + card.getName());
					System.out.println("card .........: " + card.getModified_on());
					System.out.println("properties....."
							+ eElement.getElementsByTagName("properties").item(0).getChildNodes().getLength());

				//child nodes retrieved..
					NodeList nodlIts = eElement.getElementsByTagName("properties").item(0).getChildNodes();
					for (int temp2 = 0; temp2 < nodlIts.getLength() - 1; temp2++) {
						Property property = new Property();
						Element eElementT = (Element) nodlIts;
						// System.out.println("text" +eElement.getTextContent());
						// System.out.println("name....." + nodlIts.item(temp1).getTextContent());
						if (eElementT.getElementsByTagName("name").item(temp2) == null
								|| eElementT.getElementsByTagName("value").item(temp2) == null) {
							continue;

						} else {
							
							if(eElementT.getElementsByTagName("name") ==null|| eElementT.getElementsByTagName("name").item(temp2)==null || eElementT.getElementsByTagName("value") ==null || eElementT.getElementsByTagName("value").item(temp2) ==null  )
							
							{
								continue;
							}
								System.out.println(
									"name: " + eElementT.getElementsByTagName("name").item(temp2).getTextContent());
							property.setName(eElementT.getElementsByTagName("name").item(temp2).getTextContent());

							System.out.println("value: "
									+ eElementT.getElementsByTagName("value").item(temp2).getTextContent());
							property.setValue(eElementT.getElementsByTagName("value").item(temp2).getTextContent());
							propertyList.add(property);
						}

					}
					Properties properties = new Properties();
					properties.setProperty(propertyList);
					card.setProperties(properties);
					//System.out.println("card poulated/...........................##########################: " + card.getName() + "\n" + "count is......." + count);
					
					//System.out.println("size of card list : " + cards.size());
				cards.add(card);
				//}
			}
		
		}// TODO Auto-generated method stub
		return cards;
	}

	

}
