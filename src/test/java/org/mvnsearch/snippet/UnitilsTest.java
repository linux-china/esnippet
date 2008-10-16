package org.mvnsearch.snippet;

import org.unitils.UnitilsJUnit3;
import org.unitils.hibernate.HibernateUnitils;

/**
 * Some util method for Unitils, such as database schema rebuid and Hibernate mapping check
 *
 * @author linux_china@hotmail.com
 */
public class UnitilsTest extends UnitilsJUnit3 {
    /**
     * test method to update database schema
     *
     * @throws Exception exception during testing
     */
    public void testUpdateDatabaseSchema() throws Exception {
        System.out.println("Rebuild database schema......");
    }

    /**
     * test method to update database schema
     */
    public void testMappingToDatabase() {
        HibernateUnitils.assertMappingWithDatabaseConsistent();
    }

}
