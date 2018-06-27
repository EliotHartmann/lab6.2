

import com.google.gson.Gson;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;


public class DBManager<T>{


    private Connection connection;
    private Class<?> aClass;

    DBManager(Class<?> aClass){
        this.aClass = aClass;
    }

    public Class<?> getaClass() {
        return aClass;
    }

    public void create (){

        Field[] fs = aClass.getDeclaredFields();
        String query = "CREATE TABLE \"" +aClass.getName() + "\" (\n";

        //PRIMARY KEY вида ID_имякласса
        //поле primary key нужно для insert'a

        for (Field f : fs) {

            try{
                if(f.getName().equals("ID_" + aClass.getName())){
                    query += "ID_" + aClass.getName() + " integer PRIMARY KEY, \n";
                }else

                query += f.getName() + " " + SQLTypes.valueOf(f.getType().toString().toUpperCase()).getName() + ", \n";
            }catch (IllegalArgumentException e){
                if(f.getType()==String.class){
                    query += f.getName() + " text, \n";
                }else if(f.getAnnotation(toJson.class)!=null){
                    query += f.getName() + " jsonb, \n";
                }else {
                    DBManager dbManager = new DBManager(f.getType());
                    dbManager.create();
                    query += f.getName() + " integer REFERENCES \"" + f.getType().getName() + "\", \n";
                }
            }

        }
        query = query.substring(0, query.length()-3) + "\n )";
        System.out.println(query);
        sendQuery(query);
    }

    public void insert(T object){                     //insert
        Field[] fs = aClass.getDeclaredFields();
        String query = "INSERT INTO \"" + aClass.getName() + "\" VALUES (";

        for(Field f : fs){
            try {
                f.setAccessible(true);
            if (check_type(f)){
                    query += f.get(object) + ", ";
            }else {
                if(f.getType()==String.class){
                    query += "'" + f.get(object) + "', ";
                }else if(f.getAnnotation(toJson.class)!=null){
                    query += "'" + (new Gson().toJson(f.get(object)))+"', \n";
                }else {
                    DBManager dbManager = new DBManager(f.getType());
                    dbManager.insert(f.get(object));
                    query += dbManager.getPrimaryKey(f.get(object)) + ", ";
                }
            }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        query = query.substring(0, query.length()-2);
        query += ");";
        System.out.println(query);
        sendQuery(query);
    }

    public String getPrimaryKey(T object){

        for(Field field : object.getClass().getDeclaredFields()){
            if (field.getName().equals("ID_" + aClass.getName())) {
                field.setAccessible(true);
                try {
//                    System.out.println(field.get(object).toString());
                    return field.get(object).toString();
                } catch (IllegalAccessException e) {
                    return e.getMessage();
                }

            }
        }
        return null;
    }


    private boolean check_type(Field field){
        Object type = field.getType();
        try{
            SQLTypes.valueOf(type.toString().toUpperCase());
            return true;
        }catch (IllegalArgumentException e){
            return false;
        }
    }

    public void delete(T object){
        String query = "DELETE FROM " + aClass.getName() + " WHERE ID_" + aClass.getName() + " = " + getPrimaryKey(object);
        System.out.println(query);
    }

    private void sendQuery(String query){
        try {
            Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/lab8", "postgres", "12345");
            connection.createStatement().execute(query);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public ResultSet executeQuery(String query){
        try{
            Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/lab8", "postgres", "12345");
            return connection.createStatement().executeQuery(query);
        }catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    public void dropTable(){
        String query = "DROP TABLE \"" + aClass.getName() + "\"";
        System.out.println(query);
        sendQuery(query);
    }

    public T getObject(ResultSet resultSet){
        Field[] fields = aClass.getDeclaredFields();
        try {
            Object object = aClass.newInstance();
            if (resultSet.next()) {
                for (Field field : fields) {
                    field.setAccessible(true);
                    Object type = field.getType();

                    if (type == int.class) {
                        field.set(object, Integer.parseInt(resultSet.getString(field.getName())));
                    } else if (type == String.class || type == char.class) {
                        field.set(object, resultSet.getString(field.getName()));
                    } else if (type == boolean.class) {
                        field.set(object, Boolean.parseBoolean(resultSet.getString(field.getName())));
                    } else if (type == double.class) {
                        field.set(object, Double.parseDouble(resultSet.getString(field.getName())));
                    } else if (type == byte.class) {
                        field.set(object, Byte.parseByte(resultSet.getString(field.getName())));
                    } else if (type == float.class) {
                        field.set(object, Float.parseFloat(resultSet.getString(field.getName())));
                    } else if (type == long.class) {
                        field.set(object, Long.parseLong(resultSet.getString(field.getName())));
                    } else if (type == short.class) {
                        field.set(object, Short.parseShort(resultSet.getString(field.getName())));
                    } else {
                        if(field.getAnnotationsByType(toJson.class)!=null){
                            Gson gson = new Gson();
                            field.set(object, gson.fromJson(resultSet.getString(field.getName()), field.getType()));
                        }else {
                            DBManager dbManager = new DBManager(type.getClass());
                            ResultSet resultSet1;
                            String query;

                            query = "SELECT * FROM " + type + " WHERE ID_" + type + " = " + resultSet.getString(field.getName()) + ";";
                            System.out.println(query);
                            resultSet1 = dbManager.executeQuery(query);
                            while (resultSet1.next()) {
                                field.set(object, dbManager.getObject(resultSet1));
                            }
                        }

                    }

                }
                return (T) object;
            }
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }



}
