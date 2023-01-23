package DataBase.Models;

import DataBase.Fields;
import DataBase.Model;

public class Employee extends Model {
    public Employee(String name){
        super(name);
        fields.put("name", Fields.VARCHAR.field);
        fields.put("soname", Fields.VARCHAR.field);
        fields.put("oklad", Fields.INTEGER.field);
    }
}
