import edu.uci.ics.crawler4j.crawler.Page;
import edu.uci.ics.crawler4j.crawler.WebCrawler;
import edu.uci.ics.crawler4j.parser.HtmlParseData;
import edu.uci.ics.crawler4j.url.WebURL;
import lombok.SneakyThrows;

import java.util.List;
import java.util.regex.Pattern;

public class Crawler extends WebCrawler {
    private final static Pattern EXCLUSIONS
            = Pattern.compile(".*(\\.(css|js|xml|gif|jpg|png|mp3|mp4|zip|gz|pdf))$");
    private final static Pattern EXCLUSIONS_URL_1
            = Pattern.compile("https://www.myjoyonline.com/business/.*/.*/.*");
    private final static Pattern EXCLUSIONS_URL_2
            = Pattern.compile("https://www\\.myjoyonline\\.com/category/business/page/.*");
    private final List<String> crawldomains;
    private Processor controller;
//    private WpApiClient wpApiClient;

    public Crawler(List<String> crawldomains, Processor c) {
        this.crawldomains = crawldomains;
        this.controller = c;
//        this.wpApiClient=wpApiClient;
    }

    @Override
    public boolean shouldVisit(Page referringPage, WebURL url) {
        String href = url.getURL().toLowerCase();
        if (EXCLUSIONS.matcher(href).matches()
//                ||EXCLUSIONS_URL_1.matcher(href).matches()
//                ||EXCLUSIONS_URL_2.matcher(href).matches()
        ) {
            return false;
        }
//        if (EXCLUSIONS_URL_1.matcher(href).matches()
//        ) {
//            return false;
//        }

        for (String crawlDomain : crawldomains) {
            if (href.startsWith(crawlDomain)) {
                return true;
            }
        }
        return false;//  && href.startsWith("https://www.myjoyonline.com/category/business/");
    }

    @SneakyThrows
    @Override
    public void visit(Page page) {
        String url = page.getWebURL().getURL();
        System.out.println("URL: " + url);
//        page.getWebURL().?
        if (page.getParseData() instanceof HtmlParseData) {

            HtmlParseData parseData = (HtmlParseData) page.getParseData();
            controller.process(page);

            logger.debug("Testing phase" + parseData.getOutgoingUrls().iterator().next().getURL());

        }

    }
}

