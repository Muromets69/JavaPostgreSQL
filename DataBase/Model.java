package DataBase;


import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Model {
    public String name;
    public Map<String,String> fields = new HashMap<>();
    public Model(String name){
        this.name = name;
        fields.put("id","SERIAL");
    }

    public String CreateTable(Connection connection){
        try {
            DatabaseMetaData dbm = connection.getMetaData();
            ResultSet tables = dbm.getTables(null, null, name, null);
            if (tables.next()) {
                StringBuilder command = new StringBuilder("create table " + name + "(");
                command.append("id ").append(fields.get("id")).append(",");
                for (Map.Entry<String, String> ent : fields.entrySet()) {
                    if (ent.getKey().equals("id")) {
                        continue;
                    }
                    if (ent.getValue().equals("varchar")) {
                        command.append(ent.getKey()).append(" ").append(ent.getValue()).append(",");
                    } else {
                        command.append(ent.getKey()).append(" ").append(ent.getValue()).append(",");
                    }
                }
                command.append("primary key (id));");
                return command.toString();
            }
        }
        catch (SQLException e){
            System.out.println(""+e);
        }
        return "";
    }
    public String CreateColumn(Map<String,String> par){
       StringBuilder command = new StringBuilder(String.format("insert into %s(", name));
       boolean atfirst = true;
       Map<String,String> map = new HashMap<>();

       for (String key: par.keySet()){
           for (String tablekey: fields.keySet()){
               if (key.equals(tablekey)){
                   map.put(key,par.get(key));
               }
           }
       }
       for (String key:map.keySet()){
           if (atfirst){
               command.append(key);
               atfirst = false;
               continue;
           }
           command.append(",").append(key);
       }
       atfirst = true;
        command.append(") values(");
       for (String res: map.values()){
           if (atfirst){
               command.append("'").append(res).append("'");
               atfirst = false;
               continue;
           }
           command.append(",'").append(res).append("'");
       }
       command.append(");");
       return command.toString();
    }

    public String UpdateById(String id,String field,String value){
        return String.format("update %s set %s='%s' where id='%s'",name,field,value,id);
    }

    public List<Map<String,String>> GetAllData(Connection connection){
        return new DBManager().ReadAllData(connection,this);
    }

    public String DeleteById(String id){
        return String.format("delete from %s where id='%s'",name,id);
    }

    public String DeleteByField(String field,String value){
        return String.format("delete from %s where %s='%s'",name,field,value);
    }
    public List<Map<String,String>> GetDataById(Connection connection,String val){
         return new DBManager().ReadDataByField(connection,this,val);
    }

    public List<Map<String,String>> GetDataByField(Connection connection,String field,String val){
        return new DBManager().ReadDataByField(connection,this,field,val);
    }
}
