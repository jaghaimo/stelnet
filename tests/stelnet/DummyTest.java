package stelnet;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

import com.fs.starfarer.api.campaign.econ.MarketAPI;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

/**
 * Dummy test to check correct configuration of testing framework.
 */
@RunWith(MockitoJUnitRunner.class)
public class DummyTest {

    @Test
    public void givenMockedMarket_whenCheckingName_thenItIsCustom() {
        final String marketName = "Custom";
        final MarketAPI market = mock(MarketAPI.class);
        when(market.getName()).thenReturn(marketName);
        assertEquals(marketName, market.getName());
        verify(market).getName();
    }
}
