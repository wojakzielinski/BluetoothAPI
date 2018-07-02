package scrapper;

import org.jsoup.Jsoup;
import org.jsoup.helper.Validate;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.Calendar;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class HtmlScrapper {
    private static final String url = "http://poznan-project.psnc.pl/getresults.php";
    private static final String htmlPage = "<html>\n" +
            " <head></head>\n" +
            " <body>\n" +
            "  <div id=\"commenthelp\" style=\"display: none\"> \n" +
            "   <p><b>Informacje odnośnie dodawania komentarza</b></p> \n" +
            "   <p>Ten komentarz będzie czytany jedynie przez osoby, kt&oacute;re r&oacute;wnież odnajdą ten ślub w wyszukiwarce. Komentarz <b>NIE JEST</b> zatem właściwą drogą kontaktu z zespołem pracującym nad Projektem. Jeśli to jest Państwa celem, to wszelkie pytania odnośnie funkcjonowania Projektu, jak też zapytania dotyczące historii własnej rodziny, prosimy formułować na <a href=\"http://www.wtg-gniazdo.org\">Forum WTG &quot;Gniazdo&quot;</a>. Jest tam wątek poświęcony Projektowi.</p> \n" +
            "   <p>Uwaga: jeśli jesteś zalogowany, to w przyszłości będziesz miał możliwość usunięcia komentarza.</p> \n" +
            "  </div> \n" +
            "  <div class=\"isa_success\" id=\"short_statistics\"> \n" +
            "   <h3>Wyszukiwanie zakończone sukcesem</h3> \n" +
            "   <ul> \n" +
            "    <li>Czas przeszukiwania: 4.58 sekund</li> \n" +
            "    <li>Wyniki zgodne: 2</li> \n" +
            "    <li>Wyniki powyżej progu 60%: 5</li> \n" +
            "   </ul> \n" +
            "   <div id=\"full_statistics\" style=\"display: none\"> \n" +
            "    <h2>Statistics</h2> \n" +
            "    <h3>Time statistics:</h3> \n" +
            "    <ul> \n" +
            "     <li><b>total</b> time: <b>4.58</b> sekund</li> \n" +
            "     <li><b>query</b> time: <b>4.37</b> sekund</li> \n" +
            "     <li><b>search</b> time: <b>0.17</b> sekund</li> \n" +
            "     <li><b>sort</b> time: <b>0</b> sekund</li> \n" +
            "     <li><b>comments</b> time: <b>0.05</b> sekund</li> \n" +
            "     <li><b>log_statistics</b> time: <b>0.04</b> sekund</li> \n" +
            "    </ul> \n" +
            "    <h3>Scored results:</h3> \n" +
            "    <ul> \n" +
            "     <li><b>Total results</b> found by DM soundex: <b>4220</b> </li> \n" +
            "     <li>Results above or equal [100] <b>2</b> <b>(exact matches)</b> </li> \n" +
            "     <li>Results above or equal [90] <b>0</b> </li> \n" +
            "     <li>Results above or equal [80] <b>0</b> </li> \n" +
            "     <li>Results above or equal [70] <b>0</b> </li> \n" +
            "     <li>Results above or equal [0] <b>5</b> <b>(all above threshold [60])</b> </li> \n" +
            "    </ul> \n" +
            "    <h3>Comments:</h3> \n" +
            "    <ul> \n" +
            "     <li><b>Found comments</b> for results: <b>1</b></li> \n" +
            "    </ul> \n" +
            "   </div> \n" +
            "  </div> \n" +
            "  <p class=\"center\"><a href=\"#extendedsearch\">Nowe wyszukiwanie rozszerzone</a></p> \n" +
            "  <div class=\"entry\"> \n" +
            "   <!--result entries --> \n" +
            "   <div id=\"map-canvas-resmap\"></div> \n" +
            "   <script type=\"text/javascript\" id=\"runscript_resmap\">\n" +
            "                results_map.locations.length = 0;\n" +
            "                                results_map.locations.push( { id:\"1229\", name:\"Opalenica (Opalenitza)\", lat:\"52.311\", lon:\"16.411\", num100:0.37021276595745, num90:0, confession: 1 } );\n" +
            "                                results_map.locations.push( { id:\"1230\", name:\"Opalenica (Opalenitza)\", lat:\"52.311\", lon:\"16.419\", num100:1, num90:0, confession: 3 } );\n" +
            "                            </script> \n" +
            "   <p class=\"center smaller\"> <input type=\"checkbox\" id=\"resmap_showexactonly\" onclick=\"results_map.redraw()\" />Pokaż tylko dokładne wyniki <input type=\"checkbox\" id=\"resmap_showconf1\" onclick=\"results_map.redraw()\" checked=\"checked\" /><span class=\"match1\">Katolickie</span> <input type=\"checkbox\" id=\"resmap_showconf2\" onclick=\"results_map.redraw()\" checked=\"checked\" /><span class=\"match2\">Ewangelickie</span> <input type=\"checkbox\" id=\"resmap_showconf3\" onclick=\"results_map.redraw()\" checked=\"checked\" /><span class=\"match3\">Urzędy Stanu Cywilnego</span> </p> \n" +
            "   <h2>Wyniki zgodne</h2> \n" +
            "   <div class=\"small-toolbox\"> \n" +
            "    <a class=\"small-comment\" href=\"javascript:showNewCommentForm('20152976','ad60d12cc779c9fbd93b1f7c6bf46a7d')\">+ Komentarz</a> \n" +
            "    <br /> \n" +
            "    <a class=\"small-act\" href=\"pages/arch_location/arch2.htm?iframe=true\" rel=\"prettyPhoto\" title=\"\">Akt oryginalny</a> \n" +
            "    <br /> \n" +
            "    <a class=\"small-act\" id=\"cb_add_20152976_4d0a5241cab76626bd27978162d71dea\" href=\"javascript:addItem('20152976_4d0a5241cab76626bd27978162d71dea')\">Dodaj do schowka</a> \n" +
            "    <a class=\"small-act\" id=\"cb_rem_20152976_4d0a5241cab76626bd27978162d71dea\" style=\"display:none\" href=\"javascript:removeItem('20152976_4d0a5241cab76626bd27978162d71dea')\">Usuń ze schowka</a> \n" +
            "   </div> \n" +
            "   <h3 class=\"result exact3\"> Urząd Stanu Cywilnego <b><a href=\"pages/parish_files/gro9.htm?iframe=true\" rel=\"prettyPhoto\"> Opalenica</a>, </b> wpis <b>4 / 1897</b> </h3> \n" +
            "   <ul class=\"result\"> \n" +
            "    <li class=\"groom\"> Franz <b>Zwierzyński</b> (ur. 1867) <small> <img src=\"pic/matchbar/bar100.gif\" /> 100% </small> <br /> <small> ojciec: Theodor Zwierzyński , matka: Marie Dyderska </small> </li> \n" +
            "    <li class=\"bride\"> Marie <b>Pusiak</b> (ur. 1869) <small> <img src=\"pic/matchbar/bar100.gif\" /> 100% </small> <br /><small> ojciec: Anton Pusiak , matka: Marie Ziemek </small> </li> \n" +
            "    <li class=\"addcomment\" id=\"new_comment_for_20152976\" style=\"display: none\"></li> \n" +
            "   </ul> \n" +
            "   <div class=\"small-toolbox\"> \n" +
            "    <a class=\"small-comment\" href=\"javascript:showNewCommentForm('20138212','e87439c5415e4ba5ce33adb6ff805945')\">+ Komentarz</a> \n" +
            "    <br /> \n" +
            "    <a class=\"small-act\" href=\"pages/arch_location/arch1.htm?iframe=true\" rel=\"prettyPhoto\" title=\"\">Akt oryginalny</a> \n" +
            "    <br /> \n" +
            "    <a class=\"small-act\" id=\"cb_add_20138212_4d0a5241cab76626bd27978162d71dea\" href=\"javascript:addItem('20138212_4d0a5241cab76626bd27978162d71dea')\">Dodaj do schowka</a> \n" +
            "    <a class=\"small-act\" id=\"cb_rem_20138212_4d0a5241cab76626bd27978162d71dea\" style=\"display:none\" href=\"javascript:removeItem('20138212_4d0a5241cab76626bd27978162d71dea')\">Usuń ze schowka</a> \n" +
            "   </div> \n" +
            "   <h3 class=\"result exact1\"> Parafia katolicka <b><a href=\"pages/parish_files/gro9.htm?iframe=true\" rel=\"prettyPhoto\"> Opalenica</a>, </b> wpis <b>7 / 1897</b> </h3> \n" +
            "   <ul class=\"result\"> \n" +
            "    <li class=\"groom\"> Franciscus <b>Zwierzyński</b> (ur. 1867) <small> <img src=\"pic/matchbar/bar100.gif\" /> 100% </small> <br /> <small> ojciec: Theodorus Zwierzyński , matka: Marianna Dyderska </small> </li> \n" +
            "    <li class=\"bride\"> Marianna <b>Pusiak</b> (ur. 1869) <small> <img src=\"pic/matchbar/bar100.gif\" /> 100% </small> <br /><small> ojciec: Antonius Puziak , matka: Marianna Ziemek </small> </li> \n" +
            "    <li class=\"comment\" id=\"comment_33743\"> <b>Agnieszka</b> (e-mail: strega30@onet.eu):<br /><i>W naszym drzewie.</i><br /> (06-12-2013) </li> \n" +
            "    <li class=\"addcomment\" id=\"new_comment_for_20138212\" style=\"display: none\"></li> \n" +
            "   </ul> \n" +
            "   <h2>Wyniki przybliżone</h2> \n" +
            "   <div class=\"small-toolbox\"> \n" +
            "    <a class=\"small-comment\" href=\"javascript:showNewCommentForm('9388108','706d26931098e0319c1eaab26fec9411')\">+ Komentarz</a> \n" +
            "    <br /> \n" +
            "    <a class=\"small-act\" href=\"pages/arch_location/arch1.htm?iframe=true\" rel=\"prettyPhoto\" title=\"\">Akt oryginalny</a> \n" +
            "    <br /> \n" +
            "    <a class=\"small-act\" id=\"cb_add_9388108_73e1742236b1b47af1dbe74e2734a99d\" href=\"javascript:addItem('9388108_73e1742236b1b47af1dbe74e2734a99d')\">Dodaj do schowka</a> \n" +
            "    <a class=\"small-act\" id=\"cb_rem_9388108_73e1742236b1b47af1dbe74e2734a99d\" style=\"display:none\" href=\"javascript:removeItem('9388108_73e1742236b1b47af1dbe74e2734a99d')\">Usuń ze schowka</a> \n" +
            "   </div> \n" +
            "   <h3 class=\"result rough1\"> Parafia katolicka <b><a href=\"pages/parish_files/sro8.htm?iframe=true\" rel=\"prettyPhoto\"> Iwno</a>, </b> wpis <b>1 / 1831</b> </h3> \n" +
            "   <ul class=\"result\"> \n" +
            "    <li class=\"groom_widow\"> Nicolaus <b>Zbierski</b> (38 lat, <i>wdowiec</i>) <small> <img src=\"pic/matchbar/bar60.gif\" /> 68% </small> </li> \n" +
            "    <li class=\"bride_widow\"> Barbara Ludwiczka z domu <b>Puczak</b> (30 lat, <i>wdowa</i>) <small> <img src=\"pic/matchbar/bar60.gif\" /> 67% </small> </li> \n" +
            "    <li class=\"addcomment\" id=\"new_comment_for_9388108\" style=\"display: none\"></li> \n" +
            "   </ul> \n" +
            "   <div class=\"small-toolbox\"> \n" +
            "    <a class=\"small-comment\" href=\"javascript:showNewCommentForm('33243503','6fc568eeb9ce1cc31e287fb4b78cd00c')\">+ Komentarz</a> \n" +
            "    <br /> \n" +
            "    <a class=\"small-act\" href=\"pages/arch_location/arch2.htm?iframe=true\" rel=\"prettyPhoto\" title=\"\">Akt oryginalny</a> \n" +
            "    <br /> \n" +
            "    <a class=\"small-act\" id=\"cb_add_33243503_197ca913b86ede7291f0e31924a70a49\" href=\"javascript:addItem('33243503_197ca913b86ede7291f0e31924a70a49')\">Dodaj do schowka</a> \n" +
            "    <a class=\"small-act\" id=\"cb_rem_33243503_197ca913b86ede7291f0e31924a70a49\" style=\"display:none\" href=\"javascript:removeItem('33243503_197ca913b86ede7291f0e31924a70a49')\">Usuń ze schowka</a> \n" +
            "   </div> \n" +
            "   <h3 class=\"result rough3\"> Urząd Stanu Cywilnego <b><a href=\"pages/parish_files/sre25.htm?iframe=true\" rel=\"prettyPhoto\"> Żabno</a>, </b> wpis <b>7 / 1888</b> </h3> \n" +
            "   <ul class=\"result\"> \n" +
            "    <li class=\"groom\"> Matthaeus <b>Cwojdziński</b> (ur. 1843) <small> <img src=\"pic/matchbar/bar60.gif\" /> 62% </small> <br /> <small> ojciec: Andreas Cwojdziński , matka: Rosalie Idzionka </small> </li> \n" +
            "    <li class=\"bride\"> Marie <b>Piesiak</b> (ur. 1867) <small> <img src=\"pic/matchbar/bar70.gif\" /> 71% </small> <br /><small> ojciec: Johann Piesiak , matka: Agnes Walkowiak </small> </li> \n" +
            "    <li class=\"addcomment\" id=\"new_comment_for_33243503\" style=\"display: none\"></li> \n" +
            "   </ul> \n" +
            "   <div class=\"small-toolbox\"> \n" +
            "    <a class=\"small-comment\" href=\"javascript:showNewCommentForm('1951499','199b3be46a2d03d846d8c41bf0b63472')\">+ Komentarz</a> \n" +
            "    <br /> \n" +
            "    <a class=\"small-act\" href=\"pages/arch_location/arch1.htm?iframe=true\" rel=\"prettyPhoto\" title=\"\">Akt oryginalny</a> \n" +
            "    <br /> \n" +
            "    <a class=\"small-act\" id=\"cb_add_1951499_197ca913b86ede7291f0e31924a70a49\" href=\"javascript:addItem('1951499_197ca913b86ede7291f0e31924a70a49')\">Dodaj do schowka</a> \n" +
            "    <a class=\"small-act\" id=\"cb_rem_1951499_197ca913b86ede7291f0e31924a70a49\" style=\"display:none\" href=\"javascript:removeItem('1951499_197ca913b86ede7291f0e31924a70a49')\">Usuń ze schowka</a> \n" +
            "   </div> \n" +
            "   <h3 class=\"result rough1\"> Parafia katolicka <b><a href=\"pages/parish_files/sre3.htm?iframe=true\" rel=\"prettyPhoto\"> Brodnica</a>, </b> wpis <b>4 / 1888</b> </h3> \n" +
            "   <ul class=\"result\"> \n" +
            "    <li class=\"groom_widow\"> Matthaeus <b>Cwojdziński</b> (44 lat, <i>wdowiec</i>) <small> <img src=\"pic/matchbar/bar60.gif\" /> 62% </small> </li> \n" +
            "    <li class=\"bride\"> Marianna <b>Piesiak</b> (21 lat) <small> <img src=\"pic/matchbar/bar70.gif\" /> 71% </small> </li> \n" +
            "    <li class=\"addcomment\" id=\"new_comment_for_1951499\" style=\"display: none\"></li> \n" +
            "   </ul> \n" +
            "   <!--end of result entries--> \n" +
            "  </div>  \n" +
            " </body>\n" +
            "</html>\n";


    public List<Person> getPersons(String groomFirstName, String groomLastName,
                                    String brideFirstName, String brideLastName) {
                /*
        * Franciscus Zwierzyński (ur. 1867)   100%
        ojciec: Theodorus Zwierzyński	, matka: Marianna Dyderska
        Marianna Pusiak (ur. 1869)
        ojciec: Antonius Puziak	, matka: Marianna Ziemek
        * */
//        Document doc = Jsoup.connect(url).timeout((int)TimeUnit.SECONDS.toMillis(60L))
//                .data("action", "extended")
//                .data("extended_window_lastname_mode", "both")
//                .data("groom_firstname", groomFirstName)
//                .data("groom_lastname", groomLastName)
//                .data("bride_firstname", brideFirstName)
//                .data("bride_lastname", brideLastName)
//                .data("lastname", "")
//                .data("parish_id", "")
//                .data("parish_distance", "")
//                .data("year_from", "1800")
//                .data("year_to", "1899")
//                .data("last_update", "")
//                .data("confession", "0")
//                .data("threshold", "60")
//                .post();
        Document doc = Jsoup.parse(htmlPage);

        Elements results = doc.select("ul.result");
        System.out.println("UlTags.size() = " + results.size());
        for (Element element : results) {
            Element groom = element.select("li.groom").first();
            if (groom == null)
                groom = element.select("li.groom_widow").first();

            Element bride = element.select("li.bride").first();
            if (bride == null)
                bride = element.select("li.bride_widow").first();
            System.out.println("GROOM_HTML CODE ="  + groom.toString());
            System.out.println("GROOM_person=" + getPersonFromLiTag(groom).toString());
            System.out.println("BRIDE_HTML CODE ="  + bride.toString());
            System.out.println("BRIDE_person=" + getPersonFromLiTag(bride).toString());
        }
        return null;
    }

    private Person getPersonFromLiTag(Element htmlTagElement){
        Person person = new Person();
        String dataText = htmlTagElement.text();
        String[] names = dataText.split("\\(")[0].trim().split(" ");
        person.setFirstName(names[0]);
        person.setLastName(names[1]);
        int bornYear = 0;
        if(dataText.contains("lat")){
            int age = Integer.parseInt(dataText.split("\\(.")[1].trim().split("lat")[0].trim());
            bornYear = Calendar.getInstance().get(Calendar.YEAR) - age;
        } else if (dataText.contains("ur.")){
            bornYear = Integer.parseInt(dataText.split("\\(ur.")[1].trim().split("\\)")[0].trim());
        }
        person.setBornYear(bornYear);
        if(names.length == 5 && dataText.contains("z domu")){
            person.setMaidenName(names[4]);
        } else if(names.length != 2){
            System.out.println("Splitting problem!!!: "+dataText);
        }
        person.setFather(getFather(dataText));
        return person;
    }

    private Person getFather(String dataText){
        try {
            Person person = new Person();
//            System.out.println("LELELELELE");
//            System.out.println(dataText.split("ojciec:")[1].trim());
//            System.out.println(dataText.split("ojciec:")[1].trim().split(",")[0].trim());
//            System.out.println(dataText.split("ojciec:")[1].trim().split(",")[0].trim().split(" ")[0]);
//            System.out.println(dataText.split("ojciec:")[1].trim().split(",")[0].trim().split(" ")[1]);
            String[] firstAndLastName = dataText.split("ojciec:")[1].trim().split(",")[0].trim().split(" ");
            person.setFirstName(firstAndLastName[0]);
            person.setLastName(firstAndLastName[1]);
            return person;
        }catch (ArrayIndexOutOfBoundsException e){
            return null;
        }
    }
}
