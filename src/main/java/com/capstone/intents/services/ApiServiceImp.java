package com.capstone.intents.services;

import com.capstone.intents.model.ApiResponse;
import com.capstone.intents.model.Code;
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

    private String urlStart = "https://www.reserveamerica.com/campgroundSearch.do?pstate=";
    private String urlEnd = "&xml=true&expwith=1&expfits=1";

    @Override
    public List<ApiResponse> getSearch(Code code) {

        List<ApiResponse> apiResponses = new ArrayList<>();
        try {
            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            Document document = documentBuilder.parse(urlStart + code.getState() + urlEnd);
            document.getDocumentElement().normalize();
            NodeList nodeList = document.getElementsByTagName("result");
            for (int i = 0; i < nodeList.getLength(); i++) {
                Node node = nodeList.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element elem = (Element) node;
                    ApiResponse apiResponse = new ApiResponse(
                            elem.getAttribute("agencyIcon"),
                            elem.getAttribute("agencyName"),
                            elem.getAttribute("contractID"),
                            elem.getAttribute("contractType"),
                            elem.getAttribute("facilityID"),
                            elem.getAttribute("facilityName"),
                            elem.getAttribute("faciltyPhoto"),
                            elem.getAttribute("favorite"),
                            elem.getAttribute("latitude"),
                            elem.getAttribute("listingOnly"),
                            elem.getAttribute("longitude"),
                            elem.getAttribute("regionName"),
                            elem.getAttribute("shortName"),
                            elem.getAttribute("sitesWithAmps"),
                            elem.getAttribute("sitesWithPetsAllowed"),
                            elem.getAttribute("sitesWithSewerHookup"),
                            elem.getAttribute("siteWithWaterHookup"),
                            elem.getAttribute("sitesWithWaterfront"),
                            elem.getAttribute("state")
                    );
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
