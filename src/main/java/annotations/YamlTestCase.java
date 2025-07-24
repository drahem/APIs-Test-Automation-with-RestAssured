package annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface YamlTestCase {
    /**
     * Path to YAML file relative to src/test/resources/testdata/
     * Example: "events/create_events.yml"
     */
    String value();



    /**
     * Jira tickets associated with this test case (optional)
     */
    String[] jiraLinks() default {};

    /**
     * Tags for test categorization (optional)
     */
    String[] tags() default {};
}