package Model;

 import org.sqlite.SQLiteDataSource;

 import java.sql.*;
 import java.util.Random;
 import java.util.Scanner;
public class MonsterFactory {
    SQLiteDataSource ds = null;

    private final Random RAND = new Random();

    private final int goblin = 0;

    private final int ogre = 1;

    private final int leach = 2;

    private final int skeleton = 3;

    private final int MONSTER_COUNT = 4;

    Monster[] myMonsters;
    public MonsterFactory() {
        myMonsters = new Monster[4];
        establishConnection();
        createTable();
        fillTable();
        queryDataBase();
    }

    private void establishConnection() {
        try {
            ds = new SQLiteDataSource();
            ds.setUrl("jdbc:sqlite:questions.db");
        } catch ( Exception e ) {
            e.printStackTrace();
            System.exit(0);
        }
    }

    private void createTable() {
        String query = "CREATE TABLE IF NOT EXISTS monsters ( " +
                "NAME TEXT NOT NULL, " +
                "HP INT NOT NULL, " +
                "DAMAGE INT NOT NULL, " +
                "SPEED INT NOT NULL, " +
                "DODGE_RATE DOUBLE NOT NULL," +
                "HEAL_MIN INT NOT NULL, " +
                "HEAL_RANGE INT NOT NULL )";
        try ( Connection conn = ds.getConnection();
              Statement stmt = conn.createStatement(); ) {
            int rv = stmt.executeUpdate( query );
            System.out.println( "executeUpdate() returned " + rv );
        } catch ( SQLException e ) {
            e.printStackTrace();
            System.exit( 0 );
        }
    }

    private void fillTable() {
        String query1 = "INSERT INTO monsters ( NAME , HP , DAMAGE , SPEED , " +
                        "DODGE_RATE , HEAL_MIN , HEAL_RANGE, LOOT_ONE, LOOT_TWO" +
                        ") VALUES ( 'Goblin' , 70 , 30 , 3 , 0.3 , 10 , 20 )";
        String query2 = "INSERT INTO monsters ( NAME , HP , DAMAGE , SPEED , " +
                        "DODGE_RATE , HEAL_MIN , HEAL_RANGE, LOOT_ONE, LOOT_TWO" +
                        ") VALUES ( 'Ogre' , 100 , 50 , 1 , 0.1 , 5 , 10 )";
        String query3 = "INSERT INTO monsters ( NAME , HP , DAMAGE , SPEED , " +
                        "DODGE_RATE , HEAL_MIN , HEAL_RANGE, LOOT_ONE, LOOT_TWO" +
                        ") VALUES ( 'Leach' , 55 , 15 , 6 , 0.3 , 20 , 20 )";
        String query4 = "INSERT INTO monsters ( NAME , HP , DAMAGE , SPEED , " +
                        "DODGE_RATE , HEAL_MIN , HEAL_RANGE, LOOT_ONE, LOOT_TWO" +
                        ") VALUES ( 'Skeleton' , 60 , 25 , 2 , 0.1 , 5 , 20 )";


        try ( Connection conn = ds.getConnection();
              Statement stmt = conn.createStatement(); ) {
            int rv = stmt.executeUpdate( query1 );
            System.out.println( "1st executeUpdate() returned " + rv );

            rv = stmt.executeUpdate( query2 );
            System.out.println( "2nd executeUpdate() returned " + rv );

            rv = stmt.executeUpdate( query3 );
            System.out.println( "3rd executeUpdate() returned " + rv );

            rv = stmt.executeUpdate( query4 );
            System.out.println( "4th executeUpdate() returned " + rv );
        } catch ( SQLException e ) {
            e.printStackTrace();
            System.exit( 0 );
        }
    }

    private void queryDataBase() {
        String query = "SELECT * FROM monsters";
        try ( Connection conn = ds.getConnection();
              Statement stmt = conn.createStatement(); ) {

            ResultSet rs = stmt.executeQuery(query);

            int count = 0;
            while ( rs.next() && count < MONSTER_COUNT) {
                String name = rs.getString("NAME");
                int hp = rs.getInt("HP");
                int damage = rs.getInt("DAMAGE");
                int speed = rs.getInt("SPEED");
                double dodgeRate = rs.getDouble("DODGE_RATE");
                int healMin = rs.getInt("HEAL_MIN");
                int healMaxBound = rs.getInt("HEAL_MAX_BOUND");
                myMonsters[count++] = new Monster(name, hp, damage, speed, dodgeRate, healMin, healMaxBound);
            }
        } catch ( SQLException e ) {
            e.printStackTrace();
            System.exit( 0 );
        }
        System.out.println("press enter to close program/window");
        Scanner input = new Scanner(System.in);
        input.nextLine();
    }

    public Monster getRandomMonster() {
        int  randomMonster = RAND.nextInt(MONSTER_COUNT);
        Monster monster = myMonsters[randomMonster];
        monster.addLoot();
        return monster;
    }

    public Monster getGoblin() {
        Monster monster = myMonsters[goblin];
        monster.addLoot();
        return monster;
    }

    public Monster getOgre() {
        Monster monster = myMonsters[ogre];
        monster.addLoot();
        return monster;
    }

    public Monster getLeach() {
        Monster monster = myMonsters[leach];
        monster.addLoot();
        return monster;
    }

    public Monster getSkeleton() {
        Monster monster = myMonsters[skeleton];
        monster.addLoot();
        return monster;
    }
}
