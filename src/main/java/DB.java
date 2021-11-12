import org.sql2o.Sql2o;

public class DB {
//    static int getHerokuAssignedPort() {
//        ProcessBuilder processBuilder = new ProcessBuilder();
//        if (processBuilder.environment().get("PORT") != null) {
//            return Integer.parseInt(processBuilder.environment().get("PORT"));
//        }
//        return 4567;
//    }

   public static Sql2o sql2o = new Sql2o("jdbc:postgresql://localhost:5432/wildlife_tracker", "moringa","sparkpass");
//   public static Sql2o sql2o = new Sql2o("jdbc:postgresql://ec2-52-4-100-65.compute-1.amazonaws.com:4567/deo8hkvqhbmgk0", "ktlmxxpgdqojao","47368f076edf3862c08913271d5a7317b73770c13ee3766d1e48400d9d306aae");
}
