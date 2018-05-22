package scrapper;

import org.jsoup.Jsoup;
import org.jsoup.helper.Validate;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

public class HtmlScrapper {
    private static final String url="http://poznan-project.psnc.pl/getresults.php";


    public static void main(String[] args) throws IOException {
        print("Fetching %s...", url);


        //FORM
        /*action: extended
extended_window_lastname_mode: both
groom_firstname: 0
groom_lastname: Kowalski
bride_firstname: 0
bride_lastname: Nowak
lastname:
parish_id:
parish_distance:
year_from: 1800
year_to: 1899
last_update:
confession: 0
threshold: 60
*/
        Document doc = Jsoup.connect(url)
                .data("action", "extended")
                .data("extended_window_lastname_mode", "both")
                .data("groom_firstname", "0")
                .data("groom_lastname", "Kowalski")
                .data("bride_firstname", "0")
                .data("bride_lastname", "Nowak")
                .data("lastname", "")
                .data("parish_id", "")
                .data("parish_distance", "")
                .data("year_from", "1800")
                .data("year_to", "1899")
                .data("last_update", "")
                .data("confession", "0")
                .data("threshold", "60")
                .post();

        System.out.println(doc.toString());

        Elements links = doc.select("a[href]");
        Elements media = doc.select("[src]");
        Elements imports = doc.select("link[href]");

        print("\nMedia: (%d)", media.size());
        for (Element src : media) {
            if (src.tagName().equals("img"))
                print(" * %s: <%s> %sx%s (%s)",
                        src.tagName(), src.attr("abs:src"), src.attr("width"), src.attr("height"),
                        trim(src.attr("alt"), 20));
            else
                print(" * %s: <%s>", src.tagName(), src.attr("abs:src"));
        }

        print("\nImports: (%d)", imports.size());
        for (Element link : imports) {
            print(" * %s <%s> (%s)", link.tagName(),link.attr("abs:href"), link.attr("rel"));
        }

        print("\nLinks: (%d)", links.size());
        for (Element link : links) {
            print(" * a: <%s>  (%s)", link.attr("abs:href"), trim(link.text(), 35));
        }
    }

    private static void print(String msg, Object... args) {
        System.out.println(String.format(msg, args));
    }

    private static String trim(String s, int width) {
        if (s.length() > width)
            return s.substring(0, width-1) + ".";
        else
            return s;
    }
}
