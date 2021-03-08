
import com.google.gson.*;
import edu.uci.ics.crawler4j.crawler.Page;
import edu.uci.ics.crawler4j.parser.HtmlParseData;
import okhttp3.*;
import org.apache.commons.io.FileUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.IntStream;

public class Processor {
    private final String credentail;
    public String dateFrom = "01 March 2021 1:40am";

    public Processor() {
        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create(new GsonBuilder()
                        .setLenient()
                        .enableComplexMapKeySerialization()
                        .serializeNulls()
                        .create()))
                .baseUrl("http://www.menkomo.com/wp-json/")
                .client(new OkHttpClient.Builder()
                        .readTimeout(60, TimeUnit.SECONDS)
                        .connectTimeout(60, TimeUnit.SECONDS)
                        .build())
                .build();
        credentail = Credentials.basic("gsabbih", "Fredponksa45$");//Zm42 euXr jy2F 5NRU Nipl 7Dk6
    }

    //
    public void process(Page page) {
        //Joynews

        String url = page.getWebURL().getURL();
        System.out.println("URL: " + url);
        if (page.getParseData() instanceof HtmlParseData) {

            HtmlParseData parseData = (HtmlParseData) page.getParseData();
//            processJoyNews(parseData, CountryUtils.GHANA);

            if (url.startsWith("https://www.diyanu.com/collections/men/products/")) {
                processDiyano(Enumerations.CATEGORY_MEN, url, parseData);
            }
            if (url.startsWith("https://www.gipcghana.com/press-and-media/news-headlines/")) {
            }
            if (url.startsWith("https://www.fashionghana.com/site/")) {
//                processGhanaFashionNews(parseData, CountryUtils.GHANA, CategoryUtils.FASHION);
            }
            if (url.startsWith("https://www.ghanaweb.com/GhanaHomePage/business")) {
            }
        }


    }

    private void processDiyano(String category, String url, HtmlParseData data) {
        Category category1 = new Category(category);
        String html = data.getHtml();
        String paymentUrl = url;

        // extract
        Document doc = Jsoup.parse(html);
        Element slideshow = doc.getElementsByClass("Product__SlideshowNavScroller").first();
        Elements achors = slideshow.getElementsByTag("a");
        List<String> imageUrls = new ArrayList<>();
        achors.stream().forEach((e) -> {
            imageUrls.add(e.attr("href"));
        });
        String description = doc.getElementsByClass("ProductMeta__Description").text();
        String price = doc.getElementsByClass("ProductMeta__Price Price Text--subdued u-h4").text();
        String productName = doc.getElementsByClass("ProductMeta__Title Heading u-h2").text();

        System.out.println("IMAGE: "+imageUrls);

    }

}
