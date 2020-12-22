import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

public class ToDo {
    public void ToDo() throws FileNotFoundException {
    }

    public HashMap<String,Point> readDo(String name) throws FileNotFoundException {
        Scanner scanner = new Scanner(new File(System.getProperty("user.dir")+"/"+name+".txt"));
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
                point.setFinalDate(b);
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

    public void addDo(String name,String finalDate,String task,String topic,String status) throws IOException {
        Scanner scanner = new Scanner(new File(System.getProperty("user.dir")+"/"+name+".txt"));
        HashMap<String,Point> list = readDo(name);
        Point point = new Point();
        SimpleDateFormat formatForDateNow = new SimpleDateFormat(" dd.MM.yyyy ");
        Date dateNow = new Date();
        String date = formatForDateNow.format(dateNow);
        point.setFinalDate(finalDate);
        point.setDate(date);
        point.setDescription(task);
        point.setStatus(status);
        topic=topic.trim();
        list.put(topic,point);
        Files.write(Paths.get(System.getProperty("user.dir")+"/"+name+".txt"),
                list.entrySet().stream().map(k->k.getKey()+"\n"+
                        k.getValue().getDate()+"\n"+
                        k.getValue().getFinalDate()+"\n"+
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
                str.append("<a>"+entry.getValue().getFinalDate()+"</a><br>");
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
        Files.write(Paths.get(System.getProperty("user.dir")+"/"+name+".txt"),
                list.entrySet().stream().map(k->k.getKey()+"\n"+
                        k.getValue().getDate()+"\n"+
                        k.getValue().getFinalDate()+"\n"+
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
                str.append("<a>"+entry.getValue().getFinalDate()+"</a><br>");
                str.append("<a>"+entry.getValue().getDescription()+"</a><br>");
                str.append("<br>");
                str.append("<br>");
            }
        }
        return str.toString();
    }
}
