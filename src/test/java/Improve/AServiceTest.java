package Improve;

import org.junit.Test;
import com.application.Project_Version_03_Spring_Boot.repository.ARepository;
import com.application.Project_Version_03_Spring_Boot.service.AService;
import com.application.Project_Version_03_Spring_Boot.repository.CRepository;
import com.application.Project_Version_03_Spring_Boot.service.DService;
import org.junit.Assert;

import javax.swing.text.Position;

public class AServiceTest {

    /* @Test
    public void AdditionTest() {

        // Values
        int a = 2;
        int b = 2;

        // AService Object
        ARepository aRepository = new AService();

        // Test Verification
        Assert.assertEquals(4, aRepository.Addition(a, b));
    } */

    @Test
    public void SubtractionTest() {

        // Values
        int c = 10;
        int d = 5;

        // AService Object
        CRepository cRepository = new DService();

        // Test Verification
        Assert.assertEquals(true, cRepository.Subtraction(c, d));
    }
}