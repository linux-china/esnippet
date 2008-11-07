package org.mvnsearch.snippet;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.unitils.UnitilsJUnit3;
import org.unitils.spring.annotation.SpringApplicationContext;

/**
 * base unit test for project application
 *
 * @author linux_china@hotmail.com
 */
@SpringApplicationContext({"classpath:/applicationContext.xml", "classpath:/applicationContext-domains.xml", "classpath:/applicationContext-test.xml"})
public class AppBaseTest extends UnitilsJUnit3 {
    /**
     * void test
     */
    public void testVoid() {
        assertTrue(true);
    }

}
