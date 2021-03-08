

import edu.uci.ics.crawler4j.crawler.CrawlConfig;
import edu.uci.ics.crawler4j.crawler.CrawlController;
import edu.uci.ics.crawler4j.fetcher.PageFetcher;
import edu.uci.ics.crawler4j.robotstxt.RobotstxtConfig;
import edu.uci.ics.crawler4j.robotstxt.RobotstxtServer;

import java.util.ArrayList;
import java.util.List;

//import org.springframework.stereotype.Service;

//@Service
public class Controller {

    public void start() {// collect statistics
        try {
            init();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void init() throws Exception {
        String crawlStorageFolder = "C:\\Users\\CECSAdmin\\OneDrive - University of Tennessee at Chattanooga\\Projects\\stylzcrawler\\crawl";
        ///Users/admin/CLionProjects/menkomo/crawl/temp";
        int numberOfCrawlers = 7;

        CrawlConfig config = new CrawlConfig();
        config.setCrawlStorageFolder(crawlStorageFolder);
//        config.setResumableCrawling(true);
//        config.setShutdownOnEmptyQueue(false);

        // Instantiate the controller for this crawl.
        PageFetcher pageFetcher = new PageFetcher(config);
        RobotstxtConfig robotstxtConfig = new RobotstxtConfig();
        robotstxtConfig.setEnabled(true);
//        robotstxtConfig.set
        RobotstxtServer robotstxtServer = new RobotstxtServer(robotstxtConfig, pageFetcher);
        CrawlController controller = new CrawlController(config, pageFetcher, robotstxtServer);

        // For each crawl, you need to add some seed urls. These are the first
        // URLs that are fetched and then the crawler starts following links
        // which are found in these pages
        controller.addSeed("https://www.diyanu.com/collections/men/products/");
        List<String> links = new ArrayList<>();
        links.add("https://www.diyanu.com/");
//        links.add("https://www.myjoyonline.com/");

        //
        Processor processor = new Processor();

        // The factory which creates instances of crawlers.
        CrawlController.WebCrawlerFactory<Crawler> factory = () -> new Crawler(links, processor);

        // Start the crawl. This is a blocking operation, meaning that your code
        // will reach the line after this only when crawling is finished.
        controller.start(factory, numberOfCrawlers);
    }
}
