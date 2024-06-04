package Model.Tests;

import Model.Character.Monster;
import Model.Character.MonsterFactory;

public class MonsterFactoryTests {

    public static void main(String[] theArgs) {
        MonsterFactory mf = new MonsterFactory();
        System.out.println(getOgreTest(mf));
        System.out.println(getGoblinTest(mf));
        System.out.println(getLeachTest(mf));
        System.out.println(getSkeletonTest(mf));
        System.out.println(getRandomMonsterTest(mf));
    }

    private static String getOgreTest(MonsterFactory theFactory) {
        Monster ogre = theFactory.getOgre();
        if ("Ogre".equals(ogre.getName())) {
            return "Ogre created";
        } else return "Ogre not created!";
    }

    private static String getGoblinTest(MonsterFactory theFactory) {
        Monster goblin = theFactory.getGoblin();
        if ("Goblin".equals(goblin.getName())) {
            return "Goblin created";
        } else return "Goblin not created!";
    }

    private static String getLeachTest(MonsterFactory theFactory) {
        Monster leach = theFactory.getLeach();
        if ("Leach".equals(leach.getName())) {
            return "Leach created";
        } else return "Leach not created!";
    }

    private static String getSkeletonTest(MonsterFactory theFactory) {
        Monster skeleton = theFactory.getSkeleton();
        if ("Skeleton".equals(skeleton.getName())) {
            return "Skeleton created";
        } else return "Skeleton not created!";
    }

    private static String getRandomMonsterTest(MonsterFactory theFactory) {
        Monster random = theFactory.getSkeleton();
        if ("Skeleton".equals(random.getName())) {
            return "Skeleton created";
        } else if ("Ogre".equals(random.getName())) {
            return "Ogrecreated";
        } else if ("Leach".equals(random.getName())) {
            return "Leach created";
        } else if ("Goblin".equals(random.getName())) {
            return "Goblin created";
        }else return "Monster not created!";
    }
}
