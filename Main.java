import DataBase.DBManager;
import DataBase.Models.Employee;

import java.sql.Connection;
import java.util.List;
import java.util.Map;

public class Main {

    public static void PrintData(List<Map<String,String>> data){
        for (Map<String,String> mp : data){
            System.out.print(mp.get("id")+" ");
            for (String key: mp.keySet()){
                if (key.equals("id")){
                    continue;
                }
                System.out.print(mp.get(key)+" ");
            }
            System.out.println("\n--------------------------");
        }
    }

    public static void main(String[] args){
        DBManager db = new DBManager();
        Connection connection = db.connection_db("students","postgres","43554453");
        Employee employee = new Employee("Employee");
    }
}
