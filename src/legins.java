/**
 * Created by SretenskyVD on 15.02.2018.
 */
import java.io.IOException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class legins {
    public static void main(String[] args) throws IOException {

        System.setProperty("javax.net.ssl.trustStore", "s:/vergo.crt.jks");
        String Path = "https://vergo.su/zhenschiny/losiny-women/";
        int Page = 0;
        for (int count = 0; count <= 2; count++)

        {

            if (Page == 1) {
                Path = "https://vergo.su/zhenschiny/losiny-women/?page=2";
            }
            if (Page == 2) {
                Path = "https://vergo.su/zhenschiny/losiny-women/?page=3";
            }

            Document doc1 = Jsoup.connect(Path).get();
//            Elements lHref = doc1.select("a.d_block");
            Elements lHref = doc1.getElementsByClass("caption").select("a[href]");
//            Elements links1 = doc1.getElementsByClass("product_name got_bold");
            Elements links1 = doc1.getElementsByClass("caption").select("h4");
            Elements links2 = doc1.getElementsByClass("price");
            // Elements prices = doc1.getElementsByClass("current_price got_bold pink");
  //          Elements prices = doc1.getElementsByClass("price");

            //   Elements Categorys = doc1.getElementsByClass("title1 got_light mb30");
//            Elements Categorys = doc1.getElementsByClass("col-sm-9");
            //Elements Categorys = doc1.select("div.content");
            // будем из карточки брать id    Elements Names = doc1.select("a[data-product]");


            int y = 0;
            for (Element link1 : links1) {
                Elements prices = doc1.getElementsByClass("price");
                System.out.println();
//                System.out.print(Categorys.text() + " ; " + Names.get(y).attr("data-product") + " ; " + link1.text() + " ; " + prices.get(y).text() +" ; "+ "полиамид 80%, лайкра 20%" );
//                String addressUrl = lHref.get(y).attr("abs:href");

                System.out.print("Лосины" + " ; "  + link1.text() + " ; " + prices.get(y).text()  ); //";" + lHref.get(y).attr("abs:href"));
               String addressUrl = lHref.get(y).attr("abs:href");
                Document doc2 = Jsoup.connect(addressUrl).get();
                Elements razmeres = doc2.select("option");
 //               System.out.print(razmeres.text());

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
//                Elements id_product = doc2.select("product_id");
//                System.out.print(id_product.text()+ " ; ");
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
//String addressUrl = links1.get(y).attr("abs:href");
//                try {
//
//                    Document doc2 = Jsoup.connect(addressUrl).get();
//                    Elements razmeres = doc2.getElementsByClass("skuLabel prop_RAZMER_");
//
//                    int numRazmeras = 5;
//                    String[] allRazmeras = new String[numRazmeras];
//                    allRazmeras[0] = "XXS";
//                    allRazmeras[1] = "XS";
//                    allRazmeras[2] = "S";
//                    allRazmeras[3] = "M";
//                    allRazmeras[4] = "L";
//                    int allRazmerasIndex = 0;
//
//                    for (Element razmer : razmeres) {
//                        while (allRazmerasIndex < numRazmeras && !allRazmeras[allRazmerasIndex].equals(razmer.text())) {
//                            System.out.print("; " + allRazmeras[allRazmerasIndex] + " ; 0");
//                            ++allRazmerasIndex;
//                        }
//                        if (allRazmerasIndex < numRazmeras) {
//                            ++allRazmerasIndex;
//                        }
//                        System.out.print(" ; " + razmer.text() + " ; " + "10");
//                    }
//                    while (allRazmerasIndex < numRazmeras) {
//                        System.out.print("; " + allRazmeras[allRazmerasIndex] + " ; 0");
//                        ++allRazmerasIndex;
//                    }
//
//                    Elements pictures = doc2.getElementsByClass("zoomer image");
//
//                    int z = 0;
//                    for (Element picture : pictures) {
//                        System.out.print(" ; https://bfide.ru" + pictures.get(z).attr("src"));
//                        z++;
//                    }
//
//                }
//                catch (java.util.zip.ZipException e){
//
//                    Document doc2 = Jsoup.connect(addressUrl).get();
//                    Elements razmeres = doc2.getElementsByClass("skuLabel prop_RAZMER_");
//                    int numRazmeras = 5;
//                    String[] allRazmeras = new String[numRazmeras];
//                    allRazmeras[0] = "XXS";
//                    allRazmeras[1] = "XS";
//                    allRazmeras[2] = "S";
//                    allRazmeras[3] = "M";
//                    allRazmeras[4] = "L";
//                    int allRazmerasIndex = 0;
//                    for (Element razmer : razmeres) {
//                        while (allRazmerasIndex < numRazmeras && !allRazmeras[allRazmerasIndex].equals(razmer.text())) {
//                            System.out.print("; " + allRazmeras[allRazmerasIndex] + " ; 0");
//                            ++allRazmerasIndex;
//                        }
//                        if (allRazmerasIndex < numRazmeras) {
//                            ++allRazmerasIndex;
//                        }
//                        System.out.print(" ; " + razmer.text() + " ; " + "10");
//                    }
//                    while (allRazmerasIndex < numRazmeras) {
//                        System.out.print("; " + allRazmeras[allRazmerasIndex] + " ; 0");
//                        ++allRazmerasIndex;
//                    }
//                    Elements pictures = doc2.getElementsByClass("zoomer image");
//                    int z = 0;
//                    for (Element picture : pictures) {
//                        System.out.print(" ; https://bfide.ru" + pictures.get(z).attr("src"));
//                        z++;
//                    }
//
//                }
//
//            y++;
//
            //         }
//
//            Page++;
//
//        }
//
//
////    }
//        }
//    }
//}