import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.io.IOException;


public class Crawling {
    public static void main(String[] args) {
        String url= "https://finance.naver.com/sise/sise_market_sum.nhn?&page=1";
        Connection connection= Jsoup.connect(url);


        try{
            Document document = connection.get();
            String thead = getStockHeader(document);
            //System.out.println(document);
            System.out.println(thead);

        }catch(IOException e){
            throw new RuntimeException(e);
        }
    }


    private static String getStockHeader(Document document) {
        Elements stockTableBody = document.select("table.type_2 thead tr");
        StringBuilder builder = new StringBuilder();
        for(Element element : stockTableBody){
            for(Element td : element.select("th")){
                builder.append(td.text());
                builder.append("  ");
            }
            break;
        }
        return builder.toString();
    }
}
