import java.text.SimpleDateFormat;
import java.util.*;

public class Desk {
    private static HashMap<String,String> book = new HashMap<String,String>();

    public Desk() {
    }

    public synchronized void add(String name,String number) {
        SimpleDateFormat formatForDateNow = new SimpleDateFormat(" dd.MM.yyyy  hh:mm:ss a zzz");
        Date dateNow = new Date();
        StringBuilder str = new StringBuilder();
        str.append(name);
        str.append(" ");
        str.append(formatForDateNow.format(dateNow));
        String val = str.toString();

            book.put(val,number);
        }



    public synchronized String getDesk() {
        StringBuilder str = new StringBuilder();
        for (Map.Entry<String,String> nm :book.entrySet()) {
            str.append("<p>");
            str.append(nm.getKey());
            str.append("<br>");
            str.append(nm.getValue());
            str.append("</p>");
        }
        return str.toString();
    }

    public synchronized void reset() {
        book.clear();
    }
}
