package View;

import Model.Character.Hero;
import Model.Character.Monster;
import Model.GameScreenStack;
import Model.Items.GameItem;
import View.Battle.BattleManager;
import View.Battle.BattleTurnManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.awt.event.KeyEvent;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class BattleScreenTest {

    @Mock
    private GameScreenStack gameScreenStackMock;

    @Mock
    private Hero heroMock;

    @Mock
    private Monster monsterMock;

    @Mock
    private BattleTurnManager turnManagerMock;

    @Mock
    private GameItem gameItemMock;

    @InjectMocks
    private BattleScreen battleScreen;



    @BeforeEach
    void setUp() {
        when(gameItemMock.getItemName()).thenReturn("Health Potion");
        GameItem[] initialItems = {gameItemMock};
        heroMock = new Hero("Elf", 100, 20, 5, 0.3, initialItems);
        when(heroMock.getName()).thenReturn("Elf");
        battleScreen = new BattleScreen(gameScreenStackMock, heroMock, monsterMock);
        battleScreen.setTurnManager(turnManagerMock);
    }

    @Test
    void testKeyPressed_UpArrow_DecrementsSelectedOption() {
        battleScreen.setSelected(1);
        battleScreen.keyPressed(KeyEvent.VK_UP);
        assert battleScreen.getSelected() == 0;
    }

    @Test
    void testKeyPressed_DownArrow_IncrementsSelectedOption() {
        battleScreen.setSelected(0);
        battleScreen.keyPressed(KeyEvent.VK_DOWN);
        assert battleScreen.getSelected() == 1;
    }

    @Test
    void testKeyPressed_Enter_CallsHeroAttack() {
        battleScreen.setSelected(0);
        BattleManager battleManagerMock = mock(BattleManager.class);
        battleScreen.setBattleManager(battleManagerMock);
        when(turnManagerMock.getTurn()).thenReturn(true);

        battleScreen.keyPressed(KeyEvent.VK_ENTER);

        verify(battleManagerMock).heroAttack();
    }

    @Test
    void testKeyPressed_Enter_CallsHealHero() {
        battleScreen.setSelected(1);
        BattleManager battleManagerMock = mock(BattleManager.class);
        battleScreen.setBattleManager(battleManagerMock);
        when(heroMock.getMaxHP()).thenReturn(100);
        when(heroMock.getHP()).thenReturn(50);

        battleScreen.keyPressed(KeyEvent.VK_ENTER);

        verify(battleManagerMock).healHero();
        assert battleScreen.isHealed();
    }

    @Test
    void testKeyPressed_Enter_OpensInventoryScreen() {
        battleScreen.setSelected(2);
        battleScreen.keyPressed(KeyEvent.VK_ENTER);

        verify(gameScreenStackMock).addScreen(any(InventoryScreen.class));
    }

    @Test
    void testKeyPressed_Enter_ExitsWhenMonsterIsDead() {
        when(monsterMock.checkIfDead()).thenReturn(true);
        battleScreen.setSelected(0);
        battleScreen.keyPressed(KeyEvent.VK_ENTER);

        verify(gameScreenStackMock).backToPreviousState();
    }

    @Test
    void testKeyPressed_Enter_ExitsWhenHeroIsDead() {
        when(heroMock.checkIfDead()).thenReturn(true);
        battleScreen.setSelected(0);
        battleScreen.keyPressed(KeyEvent.VK_ENTER);

        verify(gameScreenStackMock).clearStack();
        verify(gameScreenStackMock).addScreen(any(MainMenu.class));
    }
}