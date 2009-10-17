package org.mvnsearch.snippet.domain.manager.impl;

import junit.framework.TestCase;
import org.mvnsearch.snippet.AppBaseTest;
import org.unitils.spring.annotation.SpringBean;

import java.util.List;

/**
 * snippet service implementation test
 *
 * @author linux_china@hotmail.com
 */
public class SnippetServiceImplTest extends AppBaseTest {
    @SpringBean("snippetService")
    private SnippetServiceImpl snippetService;

    /**
     * test for find snippet with name and icon
     *
     * @throws Exception exception
     */
    public void testFindMnemonicListWithNameAndIcon() throws Exception {
        String prefix = "spring";
        List<String> info = snippetService.findMnemonicListWithNameAndIcon(prefix);
        for (String s : info) {
            System.out.println(s);
        }
    }
}
