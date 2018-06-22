import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class WorkJSON {
    public static String toJSON(Policeman policeman){
        GsonBuilder gsonBuilder = new GsonBuilder();
        Gson gson = gsonBuilder.create();
        return gson.toJson(policeman);
    }
    public static Policeman intoJSON(String string){
        GsonBuilder gsonBuilder = new GsonBuilder();
        Gson gson = gsonBuilder.create();
        return gson.fromJson(string,Policeman.class);
    }
}
