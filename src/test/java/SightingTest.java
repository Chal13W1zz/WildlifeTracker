import org.junit.Rule;
import org.junit.jupiter.api.Test;

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
        assertEquals("Chalie,false,Zone A", sighting.getRangerName()+","+sighting.isEndangeredAnimal() +","+ sighting.getLocation());
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
        assertTrue(sighting.getAllSightings().get(0).equals(sighting));
    }

    @Test
    public void getAllSightingsReturnsAllInstancesOfSightinng_true(){
        Sighting sighting = newSighting();
        sighting.saveSighting();
        Sighting sighting1 = new Sighting("Wizz",false,"Zone D");
        sighting1.saveSighting();
        assertEquals(true,Sighting.getAllSightings().get(1).equals(sighting));
        assertEquals(true,Sighting.getAllSightings().get(2).equals(sighting1));
    }

    @Test
    public void saveSightingsAssignsIdToObject(){
        Sighting sighting = newSighting();
        sighting.saveSighting();
        Sighting savedSighting = Sighting.getAllSightings().get(1);
        assertEquals(savedSighting.getSightingId(),sighting.getSightingId());
    }

    @Test
    public void findSightingsByIdReturnsCorrectSighting(){
        Sighting sighting = newSighting();
        sighting.saveSighting();
        Sighting sighting1 = new Sighting("Wizz",false,"Zone D");
        sighting1.saveSighting();
        assertEquals(Sighting.findSightingById(sighting1.getSightingId()),sighting1);
    }

    //helper method
    public Sighting newSighting(){
        return new Sighting("Chalie",false,"Zone A");
    }

}