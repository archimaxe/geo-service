import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.netology.entity.Country;
import ru.netology.entity.Location;
import ru.netology.geo.GeoServiceImpl;


public class GeoServiceTest {
    @Test
    void verifyingLocationMethod() {
        GeoServiceImpl geoService = new GeoServiceImpl();
        Location locationGeoService = geoService.byIp("172.123.123");
        Assertions.assertEquals(Country.RUSSIA, locationGeoService.getCountry());
    }


    @Test
    void verifyingLocationMethodReturnLocalHost() {
        GeoServiceImpl geoService = new GeoServiceImpl();
        Location locationGeoService = geoService.byIp("127.0.0.0");
        Assertions.assertEquals(null, locationGeoService.getCity());
    }
}