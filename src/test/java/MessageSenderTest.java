import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import ru.netology.entity.Country;
import ru.netology.entity.Location;
import ru.netology.geo.GeoService;
import ru.netology.i18n.LocalizationService;
import ru.netology.i18n.LocalizationServiceImpl;
import ru.netology.sender.MessageSender;
import ru.netology.sender.MessageSenderImpl;
import java.util.HashMap;
import java.util.Map;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class MessageSenderTest {

    @Test
    void msgRusTest() {
        LocalizationService localizationServiceSpy = Mockito.spy(LocalizationServiceImpl.class);

        Location location = Mockito.mock(Location.class);
        when(location.getCountry()).thenReturn(Country.RUSSIA);

        GeoService geoService = Mockito.mock(GeoService.class);
        when(geoService.byIp(any())).thenReturn(location);

        Map<String, String> headers = new HashMap<>();
        headers.put(MessageSenderImpl.IP_ADDRESS_HEADER, "172.44.183.00");
        MessageSender messageSender = new MessageSenderImpl(geoService, localizationServiceSpy);
        String expected = "Добро пожаловать";
        assertEquals(expected, messageSender.send(headers));
    }

    @Test
    void msgEngTest() {
        LocalizationService localizationServiceSpy = Mockito.spy(LocalizationServiceImpl.class);

        Location location = Mockito.mock(Location.class);
        when(location.getCountry()).thenReturn(Country.USA);

        GeoService geoService = Mockito.mock(GeoService.class);
        when(geoService.byIp(any())).thenReturn(location);

        Map<String, String> headers = new HashMap<>();
        headers.put(MessageSenderImpl.IP_ADDRESS_HEADER, "96.44.183.00");
        MessageSender messageSender = new MessageSenderImpl(geoService, localizationServiceSpy);
        String expected = "Welcome";
        assertEquals(expected, messageSender.send(headers));
    }
}