package dataProvider;

import org.testng.annotations.DataProvider;

public class DataProviderSource {

    @DataProvider(name = "user1")
    public static Object[][] provideData() {
        return new Object[][] {
                {"NikitaMelnikQATestLab@gmail.com", "QATestLab22121989"}
        };
    }
}
