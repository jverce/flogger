package app.managers;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class MainManagerTest {
    @Test
    void testMainManagerThread() {
        MainManager mainManager = new MainManager();
        assertNotNull(mainManager);
    }
}
