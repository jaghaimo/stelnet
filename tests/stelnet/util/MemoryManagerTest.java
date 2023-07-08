package stelnet.util;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import com.fs.starfarer.api.campaign.rules.MemoryAPI;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class MemoryManagerTest {

    private MemoryAPI memoryApi;
    private MemoryManager memoryManager;

    @Before
    public void init() {
        memoryApi = mock(MemoryAPI.class);
        memoryManager = new MemoryManager(memoryApi);
        MemoryManager.setInstance(memoryManager);
    }

    @After
    public void teardown() {
        MemoryManager.reset();
    }

    @Test
    public void givenCustomMemoryManager_whenGettingInstance_thenItIsThatInstance() {
        final MemoryManager instance = MemoryManager.getInstance();
        assertEquals(instance, memoryManager);
    }

    @Test
    public void givenMockedMemoryApi_whenCheckingSetKey_thenItIsCorrect() {
        when(memoryApi.contains("setKey")).thenReturn(true);
        when(memoryApi.getBoolean("setKey")).thenReturn(false);
        final boolean resultFalse = memoryManager.getBoolean("setKey", true);
        assertFalse(resultFalse);
        verify(memoryApi).getBoolean("setKey");
        verify(memoryApi, never()).set("setKey", true);
    }

    @Test
    public void givenMockedMemoryApi_whenCheckingUnsetKey_thenItIsDefault() {
        when(memoryApi.contains("unsetKey")).thenReturn(false);
        final boolean resultTrue = memoryManager.getBoolean("unsetKey", true);
        assertTrue(resultTrue);
        verify(memoryApi, never()).getBoolean("unsetKey");
        verify(memoryApi).set("unsetKey", true);
    }
}
