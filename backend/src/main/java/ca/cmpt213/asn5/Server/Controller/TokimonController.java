package ca.cmpt213.asn5.Server.Controller;

import ca.cmpt213.asn5.Server.Model.Tokimon;
import ca.cmpt213.asn5.Server.Model.TokimonData;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Tokimon controller class that handles all request made to create, get or delete tokimon.(Rest API)
 */

@RestController
public class TokimonController {

    private AtomicLong counter = new AtomicLong();
    private TokimonData tokimonData = TokimonData.getInstance();

    @GetMapping(value = "/api/tokimon/all")
    public List<Tokimon> index(){
        return tokimonData.getTokimons();
    }

    @GetMapping(value = "/api/tokimon/{id}")
    public ResponseEntity<?> show(@PathVariable String id){
        List<Tokimon> dataTokimons =  tokimonData.getTokimons();
        int tokimonId = Integer.parseInt(id);
        for (Tokimon tokimon : dataTokimons) {
            if (tokimon.getId() == tokimonId) {
                tokimonData.getTokimonById(tokimonId);
                return new ResponseEntity<>(tokimonData.getTokimonById(tokimonId), HttpStatus.OK);
            }
        }
        return new ResponseEntity<>( "TOKIMON NOT FOUND", HttpStatus.NOT_FOUND);
    }

    @PostMapping(value = "/api/tokimon/add")
    public ResponseEntity<?> add(@RequestBody Tokimon toki){
        String name = toki.getName();
        Double fire = toki.getFire();
        Double fly = toki.getFly();
        Double water = toki.getWater();
        Double electric = toki.getElectric();
        Double freeze = toki.getFreeze();
        String color = toki.getColor();
        Double height = toki.getHeight();
        Double strength = toki.getStrength();
        Double weight = toki.getWeight();
        Tokimon toki1 = new Tokimon(name,  weight, height, fly, fire, water, electric, freeze, strength, color);
        toki1.setId(counter.incrementAndGet());
        tokimonData.add(toki1);
        tokimonData.toJson();
        return new ResponseEntity<>("CREATED ",HttpStatus.CREATED);

    }

    @DeleteMapping("/api/tokimon/{id}")
    public ResponseEntity<?> delete(@PathVariable String id){
        int tokimonId = Integer.parseInt(id);
        List<Tokimon> t =  tokimonData.getTokimons();
        for (Tokimon a : t){
            if(a.getId() == tokimonId){
                tokimonData.delete(tokimonId);
                tokimonData.toJson();
                return new ResponseEntity<>("TOKIMON DELETED ",HttpStatus.OK);
            }
        }
        return new ResponseEntity<>("TOKIMON NOT FOUND ",HttpStatus.NO_CONTENT);
    }

}