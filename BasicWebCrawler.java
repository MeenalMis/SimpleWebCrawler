package com.deloitte.claimervice;

import java.io.IOException;
import java.util.ArrayList;

import org.apache.commons.lang.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;



/**
 * @author meenalmishra
 * BasicWebCrawler class crawls a given URL. It restricts the external links and only accesses the single domain sites.
 *
 */
public class BasicWebCrawler {

	private ArrayList<String> links;
	private String tempURL;

    public BasicWebCrawler() {
        links = new ArrayList<String>();
    }

    /**
     * @param URL, tempLink
     * This method accepts URL to crawl the sublinks present in that site. Also tempLink is used to keep a check for every incoming URL is of same domain.
     * The method is recursively called on the update of variable URL.
     *
     */
    public void getPageLinks(String URL, String tempLink) {
        if (!links.contains(URL) && URL.contains(tempLink)) {
            try {
                //4. (i) If not add it to the index
            	if(!links.isEmpty()) {
            	for(int i=0;i<links.size();i++) {
            		String st = links.get(i).substring(0, links.get(i).lastIndexOf("/"));
            		int index = links.get(i).indexOf("/");
            	if (index==(URL.indexOf("/"))) {
            		if(!StringUtils.difference(st, URL).isEmpty()) {
            		tempURL = "-"+ StringUtils.difference(st, URL);
            		break;
            		}else {
            			tempURL = URL;
            		}
            		
            	}
            		}
        		System.out.println(tempURL);
            	links.add(URL);
            	}
            	else {
            		links.add(URL);
            		System.out.println(URL);
            	}

                Document document = Jsoup.connect(URL).get();
                Elements linksOnPage = document.select("a[href]");
                
                for (Element page : linksOnPage) {
                	if(page.attr("abs:href").contains(URL.substring(URL.indexOf("//"))));
                	{
                    getPageLinks(page.attr("abs:href"), tempLink);
                	}
                }
            } catch (IOException e) {
                System.err.println("For '" + URL + "': " + e.getMessage());
            }
        }
    }

	public static void main(String[] args) {
        new BasicWebCrawler().getPageLinks("https://www.redhat.com/", "https://www.redhat.com");

	}

}
