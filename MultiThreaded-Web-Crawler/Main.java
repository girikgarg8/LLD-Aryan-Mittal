import java.util.*;
import java.util.concurrent.*;

public class WebCrawler {
  private String hostName;
  private final Map <String, Boolean> urlHashMap = new ConcurrentHashMap<>();
  private final AtomicInteger numOfUrlsToParse = new AtomicInteger(0);
  private final ExeceutorService executor = Executors.newFixedThreadPool(5);
  private HtmlParser htmlParser;

    class Task implements Runnable {
        private String url;

        public Task(String url) {
            this.url = url;
        }

        @Override
        public void run() {
            for (String extractedUrl : htmlParser.getUrls(url)) {
                String curHostName = extractedUrl.split("/")[2];
                if (curHostName.equals(hostName) && urlHashMap.putIfAbsent(currHostName, true) == null) {
                    numOfUrlsToParse.getAndIncrement();
                    executor.submit(new Task(extractedUrl));
                }
            }
            numOfUrlsToParse.getAndDecrement();
        }
    }


    public List <String> crawl(String startUrl, HtmlParser htmlParser) {
        hostName = startUrl.split("/")[2];
        this.htmlParser = htmlParser;

        urlHashMap.put(startUrl, true);
        numOfUrlsToParse.getAndIncrement();

        executor.submit(new Task(startUrl));

        while (numOfUrlsToParse.get() > 0) {
            try {
                Thread.sleep(80);
            }
            catch (InterruptedException ex) {

            }
        }

        executor.shutdown();
        return new ArrayList<>(urlHashMap.keySet());
    }
};