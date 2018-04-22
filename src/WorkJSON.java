import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class WorkJSON {
    public String toJSON(Policeman policeman){
        GsonBuilder gsonBuilder = new GsonBuilder();
        Gson gson = gsonBuilder.create();
        return gson.toJson(policeman);
    }
    public Policeman intoJSON(String string){
        GsonBuilder gsonBuilder = new GsonBuilder();
        Gson gson = gsonBuilder.create();
        return gson.fromJson(string,Policeman.class);
    }
}
