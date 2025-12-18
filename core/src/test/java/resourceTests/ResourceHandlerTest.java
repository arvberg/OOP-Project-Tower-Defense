package resourceTests;

import com.IONA.TowerDefense.model.models.ResourceHandler;
import com.IONA.TowerDefense.model.ui.playerui.Resource;
import com.IONA.TowerDefense.model.ui.playerui.ResourceHP;
import com.IONA.TowerDefense.model.ui.playerui.ResourceMoney;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ResourceHandlerTest {
    private ResourceHandler resourceHandler;

    @BeforeEach
    void setup(){
        resourceHandler = new ResourceHandler();
    }

    @Test
    void initialValuesAreCorrect(){
        assertEquals(100, resourceHandler.getLives());
        assertEquals(1000, resourceHandler.getMoney());
        assertEquals(0, resourceHandler.getScore());

    }

    @Test
    void resourcesAreInitialized(){
        List<Resource> resources = resourceHandler.getResources();

        assertEquals(2, resources.size());
        assertTrue(resources.stream().anyMatch(r -> r instanceof ResourceHP));
        assertTrue(resources.stream().anyMatch(r -> r instanceof ResourceMoney));
    }

    @Test
    void gainMoneyIncreasesMoneyAndScore(){
        resourceHandler.gainMoney(250);

        assertEquals(1250, resourceHandler.getMoney());
        assertEquals(250, resourceHandler.getScore());
    }

    @Test
    void spendMoneyDecreasesMoney(){
        resourceHandler.spendMoney(250);

        assertEquals(750, resourceHandler.getMoney());
        assertEquals(0, resourceHandler.getScore());
    }

    @Test
    void setLivesIsNonNegative(){
        resourceHandler.setLives(-250);

        assertEquals(0, resourceHandler.getLives());
    }

    @Test
    void updateHpResourceUpdatesTextAndValue() {
        resourceHandler.setLives(75);
        resourceHandler.updateHpResource();

        ResourceHP hpResource = resourceHandler.getResources().stream()
            .filter(r -> r instanceof ResourceHP)
            .map(r -> (ResourceHP) r)
            .findFirst()
            .orElseThrow();

        assertEquals("75", hpResource.textBar);
    }

    @Test
    void updateMoneyResourceUpdatesTextAndValue() {
        resourceHandler.gainMoney(500);
        resourceHandler.updateMoneyResource();

        ResourceMoney moneyResource = resourceHandler.getResources().stream()
            .filter(r -> r instanceof ResourceMoney)
            .map(r -> (ResourceMoney) r)
            .findFirst()
            .orElseThrow();

        assertEquals("1500", moneyResource.textBar);
    }

    @Test
    void resetResourcesRestoresDefaultsAndUpdatesUI() {
        resourceHandler.setLives(10);
        resourceHandler.spendMoney(900);

        resourceHandler.resetResources();

        assertEquals(100, resourceHandler.getLives());
        assertEquals(100, resourceHandler.getMoney());

        ResourceHP hpResource = resourceHandler.getResources().stream()
            .filter(r -> r instanceof ResourceHP)
            .map(r -> (ResourceHP) r)
            .findFirst()
            .orElseThrow();

        ResourceMoney moneyResource = resourceHandler.getResources().stream()
            .filter(r -> r instanceof ResourceMoney)
            .map(r -> (ResourceMoney) r)
            .findFirst()
            .orElseThrow();

        assertEquals("100", hpResource.textBar);
        assertEquals("100", moneyResource.textBar);
    }
}
