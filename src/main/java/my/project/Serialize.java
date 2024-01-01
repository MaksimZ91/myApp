package my.project;
import com.google.gson.Gson;

public class Serialize {
    private Gson gson;

    public Serialize() {
        this.gson = new Gson();
    }

    public   String serialize(Object object){
        return gson.toJson(object);
    }

    public Object deSerialize(String json, Class clazz){
        return gson.fromJson(json, clazz);    }
}
