import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

public class toDo {
    public void toDo() throws FileNotFoundException {
    }

    public HashMap<String,Point> readDo(String name) throws FileNotFoundException {
        Scanner scanner = new Scanner(new File("/home/zgymko/IdeaProjects/lab15/src/main/resources/"+name+".txt"));
        HashMap<String,Point>list = new HashMap<String,Point>();

            while (scanner.hasNext()) {
                String str;
                str = scanner.nextLine();
                Point point = new Point();

                String topic = str.trim();

                str = scanner.nextLine();
                String b = str.trim();
                point.setDate(b);


                str = scanner.nextLine();
                b = str.trim();
                point.setFinal_date(b);

                str = scanner.nextLine();
                b = str.trim();
                point.setStatus(b);

                str = scanner.nextLine();
                b = str.trim();
                point.setDescription(b);

                list.put(topic,point);

            }

        return list;
    }
    public void addDo(String name,String final_date,String task,String topic,String status) throws IOException {
        Scanner scanner = new Scanner(new File("/home/zgymko/IdeaProjects/lab15/src/main/resources/"+name+".txt"));
        HashMap<String,Point> list = readDo(name);
        Point point = new Point();

        SimpleDateFormat formatForDateNow = new SimpleDateFormat(" dd.MM.yyyy ");
        Date dateNow = new Date();
        String date = formatForDateNow.format(dateNow);

        int i = list.size();
        point.setFinal_date(final_date);
        point.setDate(date);
        point.setDescription(task);
        point.setStatus(status);
        topic=topic.trim();
        list.put(topic,point);
        Files.write(Paths.get("/home/zgymko/IdeaProjects/lab15/src/main/resources/"+name+".txt"),
                list.entrySet().stream().map(k->k.getKey()+"\n"+
                        k.getValue().getDate()+"\n"+
                        k.getValue().getFinal_date()+"\n"+
                        k.getValue().getStatus()+"\n"+
                        k.getValue().getDescription()).collect(Collectors.toList()),
                StandardCharsets.UTF_8);


    }
    public String checkIn(String name) throws FileNotFoundException {
         HashMap<String,Point> list = readDo(name);

       StringBuilder str = new StringBuilder();

        for(Map.Entry<String,Point> entry : list.entrySet()) {
            if( entry.getValue().getStatus().equals("in progress")) {
                str.append("<a>"+entry.getKey()+"</a><br>");
                str.append("<a>"+entry.getValue().getDate()+"</a>");
                str.append("<a>"+entry.getValue().getStatus()+"</a><br>");
                str.append("<a>"+entry.getValue().getFinal_date()+"</a><br>");
                str.append("<a>"+entry.getValue().getDescription()+"</a><br>");
                str.append("<br>");
                str.append("<br>");
            }
        }
        return str.toString();
    }
    public void setAs(String name, String topic, String status) throws IOException {
        HashMap<String,Point> list = readDo(name);
        Point point = new Point();
        topic=topic.trim();
        for(Map.Entry<String,Point> entry : list.entrySet()) {
            if( entry.getKey().equals(topic)) {
                point= entry.getValue();
                point.setStatus(status);
                break;
            }
        }
        list.remove(topic);
        list.put(topic,point);
        Files.write(Paths.get("/home/zgymko/IdeaProjects/lab15/src/main/resources/"+name+".txt"),
                list.entrySet().stream().map(k->k.getKey()+"\n"+
                        k.getValue().getDate()+"\n"+
                        k.getValue().getFinal_date()+"\n"+
                        k.getValue().getStatus()+"\n"+
                        k.getValue().getDescription()).collect(Collectors.toList()),
                StandardCharsets.UTF_8);
    }
    public String checkDone(String name) throws FileNotFoundException {
        HashMap<String,Point> list = readDo(name);
        StringBuilder str = new StringBuilder();
        for(Map.Entry<String,Point> entry : list.entrySet()) {
            if( entry.getValue().getStatus().equals("done")) {
                str.append("<a>"+entry.getKey()+"</a><br>");
                str.append("<a>"+entry.getValue().getDate()+"</a>");
                str.append("<a>"+entry.getValue().getStatus()+"</a><br>");
                str.append("<a>"+entry.getValue().getFinal_date()+"</a><br>");
                str.append("<a>"+entry.getValue().getDescription()+"</a><br>");
                str.append("<br>");
                str.append("<br>");
            }
        }
        return str.toString();
    }
    public Boolean hasTopic(String name,String topic) throws FileNotFoundException {
        HashMap<String,Point> list = readDo(name);
        if(list.containsKey(topic)) {
            return true;
        } else {
            return false;
        }
    }
/*
    public synchronized String getDesk() {
        StringBuilder str = new StringBuilder();
        str.append("<ul id=\"spisok\">");
        String[] array = new String[book.size()];
        String[] array2 = new String[book.size()];
        for (int i = 0; i < book.size(); i++) {
            StringBuilder string = new StringBuilder();
            string.append("button");
            string.append(i+1);
            array[i] = string.toString();

            StringBuilder string1 = new StringBuilder();
            string1.append("list");
            string1.append(i+1);
            array2[i] = string1.toString();

        }
        int i = 0;
        str.append(System.lineSeparator());
        for (Map.Entry<String, ArrayList<String>> nm : book.entrySet()) {
            str.append("<li>");
            int a=i+1;
            str.append(a);
            str.append(".");
            str.append(nm.getKey());
            str.append(System.lineSeparator());
            str.append("<button id = \"" + array[i] + "\" onclick=\"myFunction('" + array[i] + "','" + array2[i] + "')\">[+]</button>");
            str.append(System.lineSeparator());
            str.append("<ul id = \"" + array2[i] + "\" style=\"display:none\">");
            // str.append(System.lineSeparator());
            for (String s : nm.getValue()) {
                str.append("<li>" + s + "</li>");
                // str.append(System.lineSeparator());
            }


            str.append("</ul></li>");
            str.append(System.lineSeparator());
            str.append(System.lineSeparator());
            i++;
        }
        str.append("</ul>");
        return str.toString();
    }

    public synchronized void reset() {
        book.clear();
    }

    public synchronized void add(String name, String level) {
        if (level.equals("0")) {
            ArrayList<String> list = new ArrayList<>();
            book.put(name, list);
        } else {
            int i = 1;
            for (Map.Entry<String, ArrayList<String>> mp : book.entrySet()) {
                if (i == Integer.parseInt(level)) {
                    ArrayList<String> list = mp.getValue();
                    list.add(name);
                    mp.setValue(list);
                    break;
                } else {
                    i++;
                }
            }
        }
    }*/
}
