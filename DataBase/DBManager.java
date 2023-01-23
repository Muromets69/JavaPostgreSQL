package DataBase;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DBManager {
    public Connection connection_db(String dbname,String user,String pass){
        Connection connection = null;
        try{
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/"+dbname,user,pass);
            if (connection==null){
                System.out.println("Connection is failed!");
            }else {
                System.out.println("Connected!");
            }

        }catch (Exception e){
            System.out.println(e+"");
        }
        return connection;
    }

    public void ExecuteToDB(Connection connection,String command){
        Statement statement;
        try{
            statement = connection.createStatement();
            statement.executeUpdate(command);
            System.out.println(command+"\nCommand success executed =)");
        }catch (Exception e){
            System.out.println("Error!\n"+e);
        }
    }

    public void PrintField(Model model){
        System.out.print("id ");
        for (String key:model.fields.keySet()){
            if (key.equals("id")){
                continue;
            }
            System.out.print(key+" ");
        }
        System.out.println("\n--------------------------");
    }
    public List<Map<String,String>> ReadAllData(Connection connection,Model model){
        List<Map<String,String>> list = new ArrayList<>();
        Statement statement;
        ResultSet res;
        try{
            statement = connection.createStatement();
            res = statement.executeQuery(String.format("select * from %s",model.name));
            while (res.next()){
                Map<String,String> mp = new HashMap<>();
                mp.put("id",res.getString("id"));
                for (String field:model.fields.keySet()){
                    if (field.equals("id")){
                        continue;
                    }
                    mp.put(field,res.getString(field));
                }
                list.add(mp);
            }
        } catch (Exception e){
            System.out.println(""+e);
        }
        PrintField(model);
        return list;
    }

    public List<Map<String,String>> ReadDataByField(Connection connection,Model model,String value){
        List<Map<String,String>> list = new ArrayList<>();
        Statement statement;
        ResultSet res;
        try{
            statement = connection.createStatement();
            res = statement.executeQuery(String.format("select * from %s where id='%s'",model.name,value));
            while (res.next()){
                Map<String,String> mp = new HashMap<>();
                mp.put("id",res.getString("id"));
                for (String fl:model.fields.keySet()){
                    if (fl.equals("id")){
                        continue;
                    }
                    mp.put(fl,res.getString(fl));
                }
                list.add(mp);
            }
        } catch (Exception e){
            System.out.println(""+e);
        }
        PrintField(model);
        return list;
    }

    public List<Map<String,String>> ReadDataByField(Connection connection,Model model,String field,String value){
        List<Map<String,String>> list = new ArrayList<>();
        Statement statement;
        ResultSet res;
        try{
            statement = connection.createStatement();
            res = statement.executeQuery(String.format("select * from %s where %s='%s'",model.name,field,value));
            while (res.next()){
                Map<String,String> mp = new HashMap<>();
                mp.put("id",res.getString("id"));
                for (String fl:model.fields.keySet()){
                    if (fl.equals("id")){
                        continue;
                    }
                    mp.put(fl,res.getString(fl));
                }
                list.add(mp);
            }
        } catch (Exception e){
            System.out.println(""+e);
        }
        PrintField(model);
        return list;
    }
}
