package utils;

import annotations.YamlTestCase;
import org.testng.IAnnotationTransformer;
import org.testng.annotations.ITestAnnotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

public class YamlTestInterceptor implements IAnnotationTransformer {

    @Override
    public void transform(ITestAnnotation annotation,
                          Class testClass,
                          Constructor testConstructor,
                          Method testMethod) {

        YamlTestCase yamlAnnotation = testMethod.getAnnotation(YamlTestCase.class);
        if (yamlAnnotation != null) {
            // Set data provider configuration
            annotation.setDataProvider("yamlDataProvider");
            annotation.setDataProviderClass(YamlDataProvider.class);


        }
    }
}