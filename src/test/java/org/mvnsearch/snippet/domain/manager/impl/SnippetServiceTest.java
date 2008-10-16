package org.mvnsearch.snippet.domain.manager.impl;

import junit.framework.TestCase;
import org.mvnsearch.snippet.domain.manager.SnippetService;
import com.caucho.hessian.client.HessianProxyFactory;

/**
 * snippet service remoting test case
 *
 * @author linux_china@hotmail.com
 */
public class SnippetServiceTest extends TestCase {
    private SnippetService snippetService;

    /**
     * initial service
     *
     * @throws Exception
     */
    @Override
    protected void setUp() throws Exception {
        super.setUp();
        String url = "http://127.0.0.1:8080/remoting/snippetService";
        HessianProxyFactory factory = new HessianProxyFactory();
        snippetService = (SnippetService) factory.create(SnippetService.class, url);

    }

    /**
     * test method for render template
     *
     * @throws Exception exception
     */
    public void testRenderTemplate() throws Exception {
        System.out.println(snippetService.renderTemplate("jc", null, null, null));
    }
}
