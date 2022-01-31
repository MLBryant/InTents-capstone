package com.capstone.intents.services;

import com.capstone.intents.model.ApiResponse;
import com.capstone.intents.model.Code;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class ApiServiceImp implements ApiService{

    private String url = "http://api.amp.active.com/camping/campgrounds?";
    private String key = "&api_key=g7axq96uwacmnxe9fv69gjzz";

    @Override
    public List<ApiResponse> getSearch(Code code) {

        List<ApiResponse> apiResponses = new ArrayList<>();
        System.out.println(code);
        try {
            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            Document document = documentBuilder.parse("http://api.amp.active.com/camping/campgrounds?pstate=LA&api_key=g7axq96uwacmnxe9fv69gjzz");
            document.getDocumentElement().normalize();
            NodeList nodeList = document.getElementsByTagName("response");
            XmlMapper xmlMapper = new XmlMapper();
            ObjectMapper objectMapper = new ObjectMapper();
            for (int i = 0; i < nodeList.getLength(); i++) {
                Node node = nodeList.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element elem = (Element) node;
                    ApiResponse apiResponse = new ApiResponse(
                            elem.getElementsByTagName("agencyIcon").item(0).getTextContent(),
                            elem.getElementsByTagName("agencyName").item(0).getTextContent(),
                            elem.getElementsByTagName("contractId").item(0).getTextContent(),
                            elem.getElementsByTagName("contractType").item(0).getTextContent(),
                            elem.getElementsByTagName("facilityId").item(0).getTextContent(),
                            elem.getElementsByTagName("facilityName").item(0).getTextContent(),
                            elem.getElementsByTagName("facilityPhoto").item(0).getTextContent(),
                            elem.getElementsByTagName("favorite").item(0).getTextContent(),
                            elem.getElementsByTagName("latitude").item(0).getTextContent(),
                            elem.getElementsByTagName("listingOnly").item(0).getTextContent(),
                            elem.getElementsByTagName("longitude").item(0).getTextContent(),
                            elem.getElementsByTagName("regionName").item(0).getTextContent(),
                            elem.getElementsByTagName("shortName").item(0).getTextContent(),
                            elem.getElementsByTagName("sitesWithAmps").item(0).getTextContent(),
                            elem.getElementsByTagName("sitesWithPetsAllowed").item(0).getTextContent(),
                            elem.getElementsByTagName("sitesWithSewerHookup").item(0).getTextContent(),
                            elem.getElementsByTagName("siteWithWaterHookup").item(0).getTextContent(),
                            elem.getElementsByTagName("sitesWithWaterfront").item(0).getTextContent(),
                            elem.getElementsByTagName("state").item(0).getTextContent()
                    );
                    System.out.println(apiResponse);
//                    ApiResponse apiResponse = xmlMapper.readValue((XMLStreamReader) elem, ApiResponse.class);
//                    String json = objectMapper.writeValueAsString(apiResponse);
                    apiResponses.add(apiResponse);
                }
            }
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        }
        return apiResponses;
    }
}
