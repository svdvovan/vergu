import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

/**
 * Created by SretenskyVD on 16.02.2018.
 */
public class top_vergo {
    public static void main(String[] args) throws IOException {
        System.setProperty("javax.net.ssl.trustStore", "s:/vergo.crt.jks");
        String Path = "https://vergo.su/zhenschiny/topy/";
        int Page = 0;
        for (int count = 0; count <= 1; count++)

        {

            if (Page == 1) {
                Path = "https://vergo.su/zhenschiny/topy/?page=2";
            }
//            if (Page == 2) {
//                Path = "https://vergo.su/zhenschiny/losiny-women/?page=3";
//            }

            Document doc1 = Jsoup.connect(Path).get();
            Elements lHref = doc1.getElementsByClass("caption").select("a[href]");

            Elements links1 = doc1.getElementsByClass("caption").select("h4");

            int y = 0;
            for (Element link1 : links1) {
                Elements prices = doc1.getElementsByClass("price");
                System.out.println();
                System.out.print("Топы" + " ; "  + link1.text() + " ; " + prices.get(y).text()  );
                String addressUrl = lHref.get(y).attr("abs:href");
                Document doc2 = Jsoup.connect(addressUrl).get();
                Elements razmeres = doc2.select("option");

                int numRazmeras = 6;
                String[] allRazmeras = new String[numRazmeras];
                allRazmeras[0] = "--- Выберите ---";
                allRazmeras[1] = "XXS";
                allRazmeras[2] = "XS";
                allRazmeras[3] = "S";
                allRazmeras[4] = "M";
                allRazmeras[5] = "L";
                int allRazmerasIndex = 0;

                for (Element razmer : razmeres) {
                    while (allRazmerasIndex < numRazmeras && !allRazmeras[allRazmerasIndex].equals(razmer.text())) {
                        System.out.print("; " + allRazmeras[allRazmerasIndex] + " ; 0");
                        ++allRazmerasIndex;
                    }
                    if (allRazmerasIndex < numRazmeras) {
                        ++allRazmerasIndex;
                    }
                    System.out.print(" ; " + razmer.text() + " ; " + "10");
                }
                while (allRazmerasIndex < numRazmeras) {
                    System.out.print("; " + allRazmeras[allRazmerasIndex] + " ; 0");
                    ++allRazmerasIndex;
                }
                Elements id_product = doc2.select("input[name = product_id]");
                String idName = id_product.attr("value");
                System.out.print(" ; " + idName);

                Elements opisanie = doc2.getElementsByClass("tab-pane active");
                System.out.print(" ; " + opisanie.text()) ;

                Elements pictures = doc2.getElementsByClass("thumbnail");

                int z = 0;
                for (Element picture : pictures) {
                    System.out.print(" ; " +  pictures.get(z).attr("href"));
                    z++;

                }
                y++;
            }




            Page++;
        }

    }
}


