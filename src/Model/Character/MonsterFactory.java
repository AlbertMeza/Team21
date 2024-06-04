package Model.Character;

 import org.sqlite.SQLiteDataSource;

 import java.sql.*;
 import java.util.Random;
 import java.util.Scanner;

/**
 * MonsterFactory class is used for generating
 * monsters by interfacing with a SQLite database.
 *
 * @author Austin Maggert
 * @version Spring 2024
 */
public class MonsterFactory {

    /**
     * ds is a SQLite data source for connecting
     * to a SQLite database
     */
    SQLiteDataSource ds = null;

    /**
     * RAND constant is a random generator for
     * randomness in the monster generator
     */

    private final Random RAND = new Random();

    /**
     * GOBLIN constant is an int used to represent
     * goblin monsters in the monster generator
     */
    private final int GOBLIN = 0;

    /**
     * OGRE constant is an int used to represent
     * ogre monsters in the monster generator
     */
    private final int OGRE = 1;

    /**
     * LEACH constant is an int used to represent
     * leach monsters in the monster generator
     */
    private final int LEACH = 2;

    /**
     * SKELETON constant is an int used to represent
     * skeleton monsters in the monster generator
     */
    private final int SKELETON = 3;

    /**
     * MONSTER_COUNT constant represents the
     * number of monsters that can be generated
     */
    private final int MONSTER_COUNT = 4;

    /**
     * myMonsters field is an array that
     * contains one of each monster. Each monster
     * in this list have no loot.
     */
    Monster[] myMonsters;

    /**
     * MonsterFactory constructor fills the myMonsters
     * array field with one of each monster type from
     * the SQLite database.
     */
    public MonsterFactory() {
        myMonsters = new Monster[4];
        establishConnection();
        createTable();
        fillTable();
        queryDataBase();
    }

    /**
     * establishConnection method connects
     * this class with the SQLite database
     */
    private void establishConnection() {
        try {
            ds = new SQLiteDataSource();
            ds.setUrl("jdbc:sqlite:Monsters.db");
        } catch ( Exception e ) {
            e.printStackTrace();
            System.exit(0);
        }
    }

    /**
     * createTable method creates a table of monsters in the SQLite database
     */
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
            stmt.close();
            conn.close();
        } catch ( SQLException e ) {
            e.printStackTrace();
            System.exit( 0 );
        }
    }

    /**
     * fillTable method fills the SQLite database with monster entries
     */
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
            rv = stmt.executeUpdate( query2 );
            rv = stmt.executeUpdate( query3 );
            rv = stmt.executeUpdate( query4 );
            stmt.close();
            conn.close();
        } catch ( SQLException e ) {
            e.printStackTrace();
            System.exit( 0 );
        }
    }

    /**
     * queryDataBase method fills myMonsters
     * with the MONSTER_COUNT amount of monsters
     * from the monsters table from the SQLite database
     */
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
                stmt.close();
                conn.close();
            }
        } catch ( SQLException e ) {
            e.printStackTrace();
            System.exit( 0 );
        }
    }

    /**
     * getRandomMonster method gets a random monster from
     * myMonsters and gives it loot based on the monster type
     *
     * @return returns a monster object
     */
    public Monster getRandomMonster() {
        int  randomMonster = RAND.nextInt(MONSTER_COUNT);
        Monster monster = myMonsters[randomMonster];
        monster.addLoot();
        return monster;
    }

    /**
     * getGoblin method gets a goblin from myMonsters
     * and adds the goblin loot
     *
     * @return returns a Goblin monster object
     */
    public Monster getGoblin() {
        Monster monster = myMonsters[GOBLIN];
        monster.addLoot();
        return monster;
    }

    /**
     * getOgre method gets an ogre from myMonsters
     * and adds the ogre loot
     *
     * @return returns an Ogre monster object
     */
    public Monster getOgre() {
        Monster monster = myMonsters[OGRE];
        monster.addLoot();
        return monster;
    }

    /**
     * getLeach method gets a leach from myMonsters
     * and adds the leach loot
     *
     * @return returns a Leach monster object
     */
    public Monster getLeach() {
        Monster monster = myMonsters[LEACH];
        monster.addLoot();
        return monster;
    }

    /**
     * getSkeleton method gets an skeleton from myMonsters
     * and adds the skeleton loot
     *
     * @return returns a Skeleton monster object
     */
    public Monster getSkeleton() {
        Monster monster = myMonsters[SKELETON];
        monster.addLoot();
        return monster;
    }
}
