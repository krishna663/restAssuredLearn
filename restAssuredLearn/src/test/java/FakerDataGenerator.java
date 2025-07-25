import net.datafaker.Faker;
import org.testng.annotations.Test;


public class FakerDataGenerator {

    @Test
    void testGenerateData()
    {
        Faker faker = new Faker();
       System.out.println(faker.name().fullName());

       //similarly we can create various random data by using faker class

    }
}
