<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" lang="en" xml:lang="en">
<head>
    <title>Code Snippet Repository Beta</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta name="description" content="Code Snippet Repository: free code snippet repository "/>
    <link rel="stylesheet" type="text/css" href="/statics/javascript/extjs/resources/css/ext-all.css"/>
    <link rel="stylesheet" type="text/css" href="/statics/javascript/syntaxhighlighter/syntax-highlighter.css"/>
    <link rel="stylesheet" type="text/css" href="/statics/stylesheets/snippet.css"/>
    <link rel="alternate" type="application/rss+xml" title="Code Snippet Reposity RSS 2.0" href="/snippet/showRss.action"/>
    <script type="text/javascript" src="/statics/javascript/syntaxhighlighter/shCore.js"></script>
    <script type="text/javascript" src="/statics/javascript/syntaxhighlighter/shBrushLanguages.js"></script>
    <script type="text/javascript" src="/statics/javascript/yui/utilities.js"></script>
    <script type="text/javascript" src="/statics/javascript/extjs/ext-yui-adapter.js"></script>
    <script type="text/javascript" src="/statics/javascript/extjs/ext-all.js"></script>
    <script type="text/javascript" src="/statics/javascript/repository.js"></script>

    <script type="text/javascript">
        //ExtJS settings
        Ext.BLANK_IMAGE_URL = '/statics/javascript/extjs/resources/images/default/s.gif';
        Ext.QuickTips.init();
        Ext.state.Manager.setProvider(new Ext.state.CookieProvider());
    </script>
    <script type="text/javascript">
        Ext.onReady(function() {
            //snippet grid panel
            var gridPanel = new Repository.ListPanel();
            //header panel
            var headerPanel = new Repository.HeaderPanel(gridPanel);
            // status bar
            var statusBarPanel = new Repository.StatusBarPanel(${snippet_count});
            //construct menu
            var menuPanel = new Repository.MenuPanel(gridPanel);
            //snippet list panel
            var listPanel = new Ext.Panel({
                title:'Snippet List',
                region:'west',
                iconCls:'listHeaderIcon',
                layout:'fit',
                tools:[{id:'gear',qtip:'Edit Snippet',
                    handler: function(event, toolEl, panel) {
                        var formWin = Layout.getSnippetFormWindow();
                        var snippetId = 0;
                        var selected = Layout.getListPanel().getSelectionModel().getSelected();
                        if (selected != null) {
                            snippetId = selected.data.id;
                        }
                        formWin.reloadSnippet(snippetId);
                        formWin.show();
                    }}],
                width:300,
                items:[gridPanel]
            });
            //snippet info panel
            var detailTabPanel = new Repository.DetailTabPanel();
            //content panel for position
            var contentPanel = new Ext.Panel({
                region:'center',
                border: false,
                layout:'border',
                items:[listPanel,detailTabPanel]
            });
            //layout configuration
            var viewport = new Ext.Viewport({
                layout:'border',
                items:[headerPanel, menuPanel, contentPanel,statusBarPanel]
            });
            detailTabPanel.initIcons();
            viewport.doLayout();
            Ext.getCmp("repository:tagCloudPanel").loadTagContent();
        });
    </script>
</head>
<body>
<!-- layout -->
<div id="repository:menu">
    <div id="repository:categoryTree"/>
    <div id="repository:tagCloudPanel"/>
</div>
<div id="content">
    <div id="repository:listPanel"/>
    <div id="repository:detailTabPanel">
        <div class="x-tab" id="detailCode" title="Code">
            <div id="snippetDetailCodeDiv">
                What is snippet repository? <br/>
                You have write a lot of code, and only 1% code can be used again by Ctrl+C and Ctrl+V. You search them in the directory and get the file and
                find them, then copy the code and put them in the editor. It is very low efficient and all these code can be shared with others. <br/>
                Snippet repository collects all excellent snippets, and developers can visit repository to find gems. They can add comments, modify code and to
                make the snippet excellent. <br/>
                eSnippet Pro plugin can be integrated in IDE, and developers can use snippets easily, and this is reason to make Snippet repository and eSnippet
                Pro plugin. The following is screen shot in IntelliJ IDEA>
                <img src="/statics/images/projects/snippet_tool_window.png" alt="eSnippet Pro plugin"/>
                <br/><br/>
                Java Code:
                <pre name="code" class="java:nocontrols">
                     public class Demo {
                      public static void main(String[] args) {
                       System.out.println("Hi guest!");
                      }
                    }
                </pre>
                <br/>
                Ruby Code:
                <pre name="code" class="java:nocontrols">
                     require 'net/http'

                     puts "Hi guest!";
                </pre>
            </div>
        </div>
        <div class="x-tab" id="detailNotes" title="Example">
            <div id="snippetDetailNotesDiv">Notes here</div>
        </div>
    </div>
</div>
<div id="repository:header" style="height:50px">
    <div id="header:logo">
        <img src="/statics/images/logo_small_w.gif" alt="Code Snippet Repository" align="left"/>
        &nbsp;&nbsp;
        <a href="#">
            <img src="/statics/images/help.png" alt="Help" title="Help"/>
        </a>
        <a href="http://book.mvnsearch.org" target="_blank">
            <img src="/statics/images/book.png" alt="Book Shelf" title="Book Shelf"/>
        </a>
    </div>
    <div id="header:welcome">
        <h1 align="center"><br/>Code Snippet 2.0 beta</h1>
    </div>
</div>
<div id="repository:statusBarPanel"/>
<script type="text/javascript">
    syntaxHighlighter();
</script>
</body>
</html>
