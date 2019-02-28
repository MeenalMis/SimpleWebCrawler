package com.claimService.com;

import java.io.IOException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.deloitte.claimervice.BasicWebCrawler;

@RunWith(SpringJUnit4ClassRunner.class)
public class BasicWebCrawlerTest {

BasicWebCrawler basicWebCrawler;	
	
@Test
public void testGetPageLinks() throws IOException{
	Mockito.mock(BasicWebCrawler.class);
	new BasicWebCrawler().getPageLinks("https://www.redhat.com/", "https://www.redhat.com");
	Mockito.verify(BasicWebCrawler.class, Mockito.times(1));
}
}
