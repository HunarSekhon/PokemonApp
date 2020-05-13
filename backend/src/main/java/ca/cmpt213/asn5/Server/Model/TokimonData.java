package ca.cmpt213.asn5.Server.Model;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * A class for managing tokimon such as delete tokimon, add tokimon to a list and
 * write Tokimons to a json file
 */

public class TokimonData {
    private List<Tokimon> tokimons;

    private static TokimonData tokiData = null;

    public TokimonData(){
        tokimons = new ArrayList<>();
    }

    public static TokimonData getInstance(){
        if (tokiData == null){
            tokiData = new TokimonData();
        }
        return tokiData;
    }

    public void add(Tokimon tokimon){
        tokimons.add(tokimon);
    }

    public boolean delete(int id){
        int tokimonIndex = -1;
        for (Tokimon t : tokimons){
            if (t.getId() == id){
                tokimonIndex = tokimons.indexOf(t);
                continue;
            }
        }
        if(tokimonIndex > -1){
            tokimons.remove(tokimonIndex);
        }
        return true;
    }
    public List<Tokimon> getTokimons(){
        return tokimons;
    }

    public Tokimon getTokimonById(int id){
        for (Tokimon t : tokimons){
            if(t.getId() == id){
                return t;
            }
        }
        return null;
    }
    public void toJson(){
        try {
            Gson gson =new GsonBuilder().setPrettyPrinting().create();
            String json = gson.toJson(tokimons);
            FileWriter writer = new FileWriter("data/tokimon.json");
            writer.write(json);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
