import com.eclipsesource.json.JsonObject;
import com.eclipsesource.json.JsonValue;

import java.util.Random;

public class Pokemon{
    public Random getRand() {
        return RAND;
    }
    private final Random RAND = new Random();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private String name;
Pokemon(){
    // exempel på hur man kan använda Jsonvalue nu med id
   JsonValue jv = PokeAPI.getRequest(randomId());
    JsonObject jo = jv.asObject().get("species").asObject();
    String s = jo.get("name").asString();
    setName(s);
    System.out.println(getName());

}

public int randomId(){

    int id = getRand().nextInt(1292);
    id++;
    return id;

}

}
