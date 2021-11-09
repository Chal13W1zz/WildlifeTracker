import org.junit.Rule;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.sql2o.Connection;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class SightingTest {
    @Rule
    public DatabaseRule database = new DatabaseRule();



    @Test
    public void Sighting_instantiatesCorrectly_true(){
        Sighting sighting = newSighting();
        assertTrue(sighting instanceof Sighting);
    }

    @Test
    public void Sighting_instantiatesCorrectlyWithData(){
        Sighting sighting = newSighting();
        assertEquals("Chalie,false,Zone A", sighting.getRangerName()+","+sighting.isEndangered() +","+ sighting.getLocation());
    }
    @Test
    public void equals_returnsTrueIfAllFieldsAreTheSame(){
        Sighting sighting = newSighting();
        Sighting sighting1 = newSighting();
        assertTrue(sighting.equals(sighting1));
    }

    @Test
    public void save_insertsObjectIntoDatabase_Sighting(){
        Sighting sighting= newSighting();
        sighting.saveSighting();
        assertEquals(Sighting.getAllSightings().get(0), sighting);
    }

    @Test
    public void getAllSightingsReturnsAllInstancesOfSightinng_true(){
        Sighting sighting = newSighting();
        sighting.saveSighting();
        Sighting sighting1 = new Sighting("Wizz",false,"Zone D");
        sighting1.saveSighting();
        assertEquals(Sighting.getAllSightings().get(0), sighting);
        assertEquals(Sighting.getAllSightings().get(1), sighting1);
    }

    @Test
    public void saveSightingsAssignsIdToObject(){
        Sighting sighting = newSighting();
        sighting.saveSighting();
        Sighting savedSighting = Sighting.getAllSightings().get(0);
        assertEquals(savedSighting.getId(),sighting.getId());
    }

    @Test
    public void findSightingsByIdReturnsCorrectSighting(){
        Sighting sighting = newSighting();
        sighting.saveSighting();
        Sighting sighting1 = new Sighting("Wizz",false,"Zone D");
        sighting1.saveSighting();
        assertEquals(Sighting.findSightingById(sighting1.getId()),sighting1);
    }

    @AfterEach
    public void tearDown(){
        try (Connection conn = DB.sql2o.open()){
            String deleteSightingSQuery = "DELETE FROM sightings *";
            conn.createQuery(deleteSightingSQuery).executeUpdate();
        }
        }

    //helper method
    public Sighting newSighting(){
        return new Sighting("Chalie",false,"Zone A");
    }

}