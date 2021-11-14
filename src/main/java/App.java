import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static spark.Spark.*;

public class App {
    static int getHerokuAssignedPort() {
        ProcessBuilder processBuilder = new ProcessBuilder();
        if (processBuilder.environment().get("PORT") != null) {
            return Integer.parseInt(processBuilder.environment().get("PORT"));
        }
        return 4567;
    }
    public static void main(String[] args) {
        port(getHerokuAssignedPort());
        staticFileLocation("/public");
        get("/",(request,response)->{
            Map<String, Object> model=new HashMap<>();
//            Sighting sighting = new Sighting("Chalie",false,"Zone A");
//            sighting.saveSighting();
            List<Sighting> allsightings = Sighting.getAllSightings();
            model.put("allsightings",allsightings);
            List<Animal> animalsInSighting = Sighting.getAnimalsInSighting();
            model.put("animalsInSighting",animalsInSighting);
           // System.out.println(allsightings.get(0).getRangerName());
            return new ModelAndView(model,"index.hbs");
        },new HandlebarsTemplateEngine());

        //display new sighting form
        get("/sighting/new",(request, response)->{
            Map<String, Object>model = new HashMap<>();
            return new ModelAndView(model,"new-sighting.hbs");
        }, new HandlebarsTemplateEngine());

        //process new sighting
        post("sighting/create",(request, response)->{
            String rangerName = request.queryParams("rangerName");
            boolean endangered = Boolean.parseBoolean(request.queryParams("endangered"));
            String location = request.queryParams("location");
            Sighting newsighting = new Sighting(rangerName,endangered,location);
            newsighting.saveSighting();
            String animalName = request.queryParams("animalName");
            String health = request.queryParams("health");
            String animalAge = request.queryParams("animalAge");
            Animal newAnimal = new Animal(animalName,health,animalAge, newsighting.getId());
            newAnimal.saveAnimal();
            response.redirect("/");
            return null;
        },new HandlebarsTemplateEngine());

        get("/animal/:id",(request, response)->{
            Map<String, Object>model = new HashMap<>();
            Integer id = Integer.parseInt(request.params("id"));
            Animal foundAnimal = Animal.findAnimalById(id);
            Sighting foundSighting = Sighting.findSightingById(foundAnimal.getSightingId());
            model.put("sighting",foundSighting);
            model.put("animal",foundAnimal);
            return new ModelAndView(model,"sighting.hbs");
        },new HandlebarsTemplateEngine());
    }
}
