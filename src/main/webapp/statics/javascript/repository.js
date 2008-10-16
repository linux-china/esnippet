Ext.namespace("Repository");
var win;  //glabal win
/**
 * syntax hight lighter
 */
function syntaxHighlighter() {
    dp.SyntaxHighlighter.HighlightAll('code');
}
//layout global object
var Layout = {
    /**
     * get list panel
     * @type Repository.ListPanel
     */
    getListPanel : function() {
        return Ext.getCmp("repository:listPanel");
    },
    /**
     * get detail tab panel
     * @type Repository.DetailTabPanel
     */
    getDetailTabPanel : function() {
        return Ext.getCmp("repository:detailTabPanel");
    },
    /**
     * get snippet form window
     * @type Repository.SnippetFormWindow
     */
    getSnippetFormWindow : function() {
        if (win == null) {
            win = new Repository.SnippetFormWindow();
        }
        return win;
    },
    /**
     * set tab icon
     * @param tabPanel  {Ext.TabPanel} tab panel
     * @param panel  {Ext.Panel}  panel
     * @param image  image url
     */
    setTabIcon : function(tabPanel, panel, image) {
        var element = Ext.fly(tabPanel.getTabEl(panel)).child('.x-tab-strip-text');
        element.setStyle('background-image', 'url(' + image + ')');
        element.setStyle('background-repeat', 'no-repeat');
        element.setStyle('background-position', '0 3px');
        element.setStyle('padding-left', '20px');
    },
    /**
     * get status bar panel
     * @type Repository.StatusBarPanel
     */
    getStatusBarPanel : function() {
        return Ext.getCmp("repository:statusBarPanel");
    }
};

/**
 * code panel with syntax hight lighter
 * @param config
 */
Repository.CodePanel = function(config) {
    Ext.apply(this, config);
    this.on("show", syntaxHighlighter);
};
Ext.extend(Repository.CodePanel, Ext.Panel);

/**
 * category tree panel
 * @param listPanel {Repository.ListPanel}  list panel
 */
Repository.CategoryTreePanel = function(listPanel) {
    Repository.CategoryTreePanel.superclass.constructor.call(this, {
        id: 'repository:categoryTree',
        el: 'repository:categoryTree',
        title: 'Category',
        autoScroll:true,
        animate:true,
        enableDD:false,
        containerScroll: false,
        tools:[{id:'up',qtip:'create a new Snippet',handler:function() {
            var formWin = Layout.getSnippetFormWindow();
            var categoryId = null;
            var node = Ext.getCmp("repository:categoryTree").getSelectionModel().getSelectedNode();
            if (node != null) {
                categoryId = node.attributes['id'];
            }
            if (categoryId > 0) {  //category selected
                formWin.resetForm(categoryId);
                formWin.show();
            } else { //no category selected
                Ext.Msg.alert("Error", "Please select a category to add snippet!");
            }
        }}],
        loader: new Ext.tree.TreeLoader({
            dataUrl:'/category/index.json'
        }),
        root: new Ext.tree.AsyncTreeNode({
            id:0,
            text:'Snippet Repository',
            icon: '/statics/images/repository.png',
            expanded:true
        }),
        rootVisible: true,
        width:180
    });
    this.addEvents({directorySelect:true});
    //fire directory select event
    this.getSelectionModel().on({
        'selectionchange' : function(node) {
            this.fireEvent('directorySelect', node);
        },
        scope:this
    });
    //directory selected
    this.on('directorySelect', function(node) {
        if (node && node.selNode.id > 0) {
            var category = node.selNode;
            listPanel.reloadByCategory(category.id);
        } else if (node && node.selNode.id == -1) { //RSS load
            listPanel.reloadByWord(null);
        }
    });

};
Ext.extend(Repository.CategoryTreePanel, Ext.tree.TreePanel);

//tag cloud panel
Repository.TagCloudPanel = function() {
    Repository.TagCloudPanel.superclass.constructor.call(this, {
        id: 'repository:tagCloudPanel',
        el: 'repository:tagCloudPanel',
        title: 'Tag Cloud',
        autoScroll:false,
        animate:true,
        enableDD:false,
        html:'<div id="tagCloudDiv"></div>',
        width:180
    });
    //load tag content
    this.loadTagContent = function() {
        var updater = Ext.get("tagCloudDiv").getUpdater();
        updater.update({
            url: "/snippet/tagCloud.piece",
            method: 'POST'
        });
    };
};
Ext.extend(Repository.TagCloudPanel, Ext.Panel);


/**
 * navigation menu panel
 * @param listPanel   {Repository.ListPanel} list panel
 */
Repository.MenuPanel = function(listPanel) {
    //category tree panel
    var categoryTree = new Repository.CategoryTreePanel(listPanel);
    categoryTree.root.expand(true, false);
    //tag cloud panel
    var tagCloundPanel = new Repository.TagCloudPanel();

    Repository.MenuPanel.superclass.constructor.call(this, {
        region:'west',
        el: 'repository:menu',
        id: 'repository:menu',
        title: 'Navigation',
        iconCls: 'menuHeaderIcon',
        deferredRender:false,
        split:true,
        width: 200,
        minSize: 175,
        maxSize: 400,
        collapsible: true,
        margins:'0 0 5 5',
        layout:'accordion',
        layoutConfig:{
            animate:true
        },
        items:[categoryTree, tagCloundPanel]
    });
};
Ext.extend(Repository.MenuPanel, Ext.Panel);

/**
 * status bar
 * @param snippetTotal  snippet total
 */
Repository.StatusBarPanel = function(snippetTotal) {
    var snippetCount = new Ext.Toolbar.TextItem('Snippets: ' + snippetTotal);
    var userCount = new Ext.Toolbar.TextItem('Users: 50');
    var clock = new Ext.Toolbar.TextItem('');

    var statusBar = new Ext.StatusBar({
        defaultText: 'Code Snippet Repository',
        items: [clock, ' ', userCount, ' ', snippetCount, ' ']
    });
    Repository.StatusBarPanel.superclass.constructor.call(this, {
        id:"repository:statusBarPanel",
        el:'repository:statusBarPanel',
        region:'south',
        layout: 'fit',
        bbar: statusBar,
        listeners: {
            'render': {
                fn: function() {
                    Ext.fly(snippetCount.getEl().parentNode).addClass('x-status-text-panel');
                    Ext.fly(userCount.getEl().parentNode).addClass('x-status-text-panel');
                    Ext.fly(clock.getEl().parentNode).addClass('x-status-text-panel');
                    Ext.fly(clock.getEl()).update(new Date().format('F j, Y'));
                }
            }
        }
    });
    /**
     * update status text
     * @param statusText {String} message
     */
    this.updateStatusText = function(statusText) {
        statusBar.setText(statusText);
    };
};
Ext.extend(Repository.StatusBarPanel, Ext.Panel);

/**
 * search field panel
 * @param listPanel {Repository.ListPanel} grid list panel
 */
Repository.SearchPanel = function(listPanel) {
    var searchField = new Ext.form.TextField({
        name:'q',
        emptyText:'search......',
        border : false,
        width: 200,
        margins: '5 0 0 0',
        hideLabel: true
    });

    Repository.SearchPanel.superclass.constructor.call(this, {
        id: 'Repository:searchPanel',
        region:'east',
        layout: "form",
        border : false,
        width: 200,
        margins: '5 0 0 0',
        items: [searchField]
    });
    //fire event deal
    searchField.on("specialkey", function(field, event) {
        var keyword = field.getValue();
        listPanel.reloadByWord(keyword);
    });
};
Ext.extend(Repository.SearchPanel, Ext.Panel);

/**
 * header panel
 *
 * @param listPanel {Repository.ListPanel} grid list panel
 */
Repository.HeaderPanel = function(listPanel) {
    var searchPanel = new Repository.SearchPanel(listPanel);
    Repository.HeaderPanel.superclass.constructor.call(this, {
        region:'north',
        id: 'repository:header',
        el: 'repository:header',
        layout: "border",
        border: false,
        height:50,
        items:[ searchPanel, { region: 'west',  border: false, contentEl: 'header:logo', width:300},
            { region: 'center',  border: false, contentEl: 'header:welcome'}]
    });
};
Ext.extend(Repository.HeaderPanel, Ext.Panel);

/**
 * snippet list panel
 */
Repository.ListPanel = function() {
    var paramName = "";
    var paramValue = "";
    var sm = new Ext.grid.RowSelectionModel({singleSelect:true});
    this.store = new Ext.data.JsonStore({
        url: '/snippet/query.json',
        id: 'id',
        fields: [
            {name: 'id'},
            {name: 'name', type: 'string'},
            {name: 'mnemonic', type: 'string'}
        ],
        autoLoad: true
    });
    this.columns = [
        new Ext.grid.RowNumberer(),
        {header:'Name',width:280, dataIndex:'name',sortable:true}
    ];
    Repository.ListPanel.superclass.constructor.call(this, {
        id: 'repository:listPanel',
        el:'repository:listPanel',
        sm: sm,
        store: this.store,
        viewConfig: new Ext.grid.GridView({emptyText:'No Snippet Found!'})
    });
    this.getSelectionModel().on("selectionchange", function(sm) {
        if (sm.getSelected()) {
            var snippet = sm.getSelected().data;
            Layout.getStatusBarPanel().updateStatusText("Mnemonic: " + snippet.mnemonic);
            Layout.getDetailTabPanel().refreshDetail(snippet.id);
        }
    });
    /**
     * refresh snippets
     */
    this.refreshSnippets = function() {
        if (paramName == 'category') {
            this.reloadByCategory(paramValue);
        } else if (paramName == 'tag') {
            this.reloadByTag(paramValue);
        } else {
            this.reloadByWord(paramValue);
        }
    };
    /**
     * load snippets by category
     * @param catId  category id
     */
    this.reloadByCategory = function (catId) {
        paramName = "category";
        paramValue = catId;
        this.store.load({
            params :{category:catId},
            add:false
        });
    };
    /**
     * load snippets by tag
     * @param tag tag name
     */
    this.reloadByTag = function (tag) {
        paramName = "tag";
        paramValue = tag;
        this.store.load({
            params :{tag:tag},
            add:false
        });
    };
    /**
     * load snippets by word
     * @param word
     */
    this.reloadByWord = function (word) {
        paramName = "q";
        paramValue = word;
        this.store.load({
            params :{q:word},
            add:false
        });
    };
};
Ext.extend(Repository.ListPanel, Ext.grid.GridPanel);

/**
 * snippet edit form window
 */
Repository.SnippetFormWindow = function() {
    var iconStore = new Ext.data.JsonStore({
        url:'/metaData.json?type=icon',
        fields: ["id","value"],
        id:'value',
        autoLoad:true
    });

    var languageStore = new Ext.data.JsonStore({
        url:'/metaData.json?type=language',
        fields: ["id","value"],
        id:'value',
        autoLoad:true
    });

    var formPanel = new Ext.FormPanel({
        labelAlign: 'top',
        bodyStyle:'padding:5px',
        width: 600,
        items: [{
            layout:'column',
            border:false,
            items:[{
                columnWidth:.5,
                layout: 'form',
                border:false,
                items: [{
                    xtype:'textfield',
                    fieldLabel: 'Name',
                    name: 'name',
                    anchor:'95%',
                    allowBlank:false
                }, {
                    xtype:'textfield',
                    fieldLabel: 'Mnemonic',
                    name: 'mnemonic',
                    anchor:'95%',
                    allowBlank:false
                }]
            },{
                columnWidth:.5,
                layout: 'form',
                border:false,
                items: [{
                    xtype:'textfield',
                    fieldLabel: 'Category ID',
                    name: 'categoryId',
                    id:'categoryId',
                    anchor:'95%',
                    allowBlank:false
                },{
                    xtype:'combo',
                    fieldLabel: 'Language',
                    name: 'language',
                    hiddenName:'language',
                    store: languageStore,
                    mode:'local',
                    forceSelection:true,
                    displayField:'value',
                    valueField:'id',
                    triggerAction: 'all',
                    anchor:'95%',
                    allowBlank:false
                }]
            }]
        },  {
            layout:'column',
            border:false,
            items:[{
                columnWidth:.5,
                layout: 'form',
                border:false,
                items: [{
                    xtype:'textfield',
                    fieldLabel: 'Author',
                    name: 'author',
                    anchor:'95%',
                    allowBlank:false
                }, {
                    xtype:'textfield',
                    fieldLabel: 'Key Words',
                    name: 'keywords',
                    anchor:'95%',
                    allowBlank:false
                }]
            },{
                columnWidth:.5,
                layout: 'form',
                border:false,
                items: [{
                    xtype:'combo',
                    fieldLabel: 'Icon',
                    store: iconStore,
                    name: 'icon',
                    hiddenName:'icon',
                    mode:'local',
                    forceSelection:true,
                    displayField:'value',
                    valueField:'id',
                    triggerAction: 'all',
                    anchor:'95%'
                },{
                    xtype:'textfield',
                    fieldLabel: 'Type(1:File, 0:Fragment)',
                    name: 'type',
                    allowBlank:false,
                    anchor:'95%'
                }]
            }]
        },
            {
                xtype:'tabpanel',
                plain:true,
                deferredRender:false,
                activeTab: 0,
                height:320,
                defaults:{bodyStyle:'padding:10px'},
                items:[{
                    title:'Description',
                    layout:'form',
                    defaults: {width: 230},
                    defaultType: 'textarea',
                    items: [
                        {
                            xtype:'hidden',
                            name:'id',
                            id: 'snippet_hidden_id'
                        },
                        {
                            fieldLabel: 'Description',
                            hideLabel:true,
                            name: 'description',
                            allowBlank:false,
                            width:580,
                            height:320
                        }]
                },{
                    title:'Code',
                    layout:'form',
                    defaults: {width: 230},
                    defaultType: 'textarea',
                    items: [{
                        fieldLabel: 'Code',
                        hideLabel:true,
                        name: 'code',
                        allowBlank:false,
                        width:580,
                        height:320
                    }]
                },{
                    cls:'x-plain',
                    title:'Example',
                    layout:'fit',
                    items: {
                        xtype:'textarea',
                        name:'example',
                        fieldLabel:'Example',
                        hideLabel:true
                    }
                }]
            }],
        buttons: [{
            text: 'Save',
            type: 'submit',
            handler: function() {
                formPanel.getForm().submit({url:'/snippet/save.json', waitMsg:'Saving Data...',success:function(form, action) {
                    if (!form.isValid()) {   //validat
                        Ext.Msg.alert("Error", "Please fill all required fields!");
                    } else {
                        var snippetId = action.result.snippet.id;
                        var h = Ext.getCmp("snippet_hidden_id");
                        h.getEl().dom.value = snippetId;
                        Layout.getSnippetFormWindow().setTitle('<font color="red">Save Successfully!</font>');
                        Layout.getListPanel().refreshSnippets();
                    }
                }});
            }
        },{
            text: 'Close',
            handler: function() {
                Layout.getSnippetFormWindow().hide();
            }
        }]
    });

    Repository.SnippetFormWindow.superclass.constructor.call(this, {
        title:'Snippet Edit',
        layout:'fit',
        width:640,
        height:600,
        resizable:false,
        closeAction:'hide',
        plain: true,
        items: [formPanel]
    });
    /**
     * reload snippet information for edit
     * @param snippetId   snippet id
     */
    this.reloadSnippet = function (snippetId) {
        this.setTitle("Edit Snippet");
        formPanel.getForm().load({
            waitTitle:'Loading....' ,
            url:'/snippet/show.json',
            params: {id:snippetId}
        });
    };
    /**
     * reset form
     * @param categoryId category id
     */
    this.resetForm = function(categoryId) {
        this.setTitle("Create Snippet");
        formPanel.getForm().reset();
        formPanel.getForm().setValues({categoryId:categoryId});
    };
};
Ext.extend(Repository.SnippetFormWindow, Ext.Window);

/**
 * snippet comment panel
 */
Repository.CommentPanel = function () {
    var ds = new Ext.data.JsonStore({
        url: '/snippet/showComments.json',
        id: 'id',
        fields: [
            {name: 'id',mapping:"id"},
            {name: 'commentator', type: 'string'},
            {name: 'commentatorEmail', type: 'string'},
            {name: 'subject', type: 'string'},
            {name: 'content', type: 'string'},
            {name: 'createdAt', type: 'string'}
        ],
        autoLoad: true
    });

    // Custom rendering Template for the View
    var resultTpl = new Ext.XTemplate(
            '<tpl for=".">',
            '<div class="comment-item">',
            '<h3><span>{createdAt}<br /> by {commentator}</span>',
            '{subject}</h3>',
            '<p>{content}</p>',
            '</div></tpl>'
            );
    var formPanel = new Ext.form.FormPanel({
        title:'Leave a message?',
        region:'south',
        collapsible: true,
        titleCollapse:false,
        split:true,
        height: 200,
        items: [
            {     xtype: 'hidden',
                name: 'id',
                allowBlank:false
            },
            {     xtype: 'textfield',
                fieldLabel: 'Author',
                name: 'commentator',
                allowBlank:false,
                width: 400
            },
            {     xtype: 'textfield',
                fieldLabel: 'Email',
                name: 'commentatorEmail',
                allowBlank:false,
                width: 400
            },
            {     xtype: 'textfield',
                fieldLabel: 'Title',
                name: 'subject',
                allowBlank:false,
                width: 400
            }
            ,{
                xtype: 'textarea',
                fieldLabel: 'Message',
                name: 'content',
                allowBlank:false,
                width: 400,
                height:120
            }
        ],
        buttons : [{
            text: 'Save',
            type: 'submit',
            handler: function() {
                if (!formPanel.getForm().isValid()) {
                    Ext.Msg.alert('Alert', 'Please fill correct information!');
                    return;
                }
                if (formPanel.getForm().getValues(false).id == '') {
                    Ext.Msg.alert('Alert', 'You should select a book then add your comment!');
                } else {
                    formPanel.getForm().submit({url:'/snippet/addComment.json', waitMsg:'Saving Data...',
                        success: function() {
                            Ext.getCmp("repository:comment").loadComments(formPanel.getForm().getValues(false).id);
                        }});
                }
            }
        }]
    });
    var commentListPanel = new Ext.Panel({
        title:'Comment list',
        region:'center',
        items:new Ext.DataView({
            tpl: resultTpl,
            store: ds,
            emptyText: 'No Comment Found!',
            itemSelector: 'div.comment-item'
        })
    });
    Repository.CommentPanel.superclass.constructor.call(this, {
        id: 'repository:comment',
        title: 'Comment',
        autoScroll:true,
        deferredRender:false,
        layout:'border',
        items: [commentListPanel,formPanel]
    });
    //load comments
    this.loadComments = function(snippetId) {
        ds.reload({
            params :{id: snippetId},
            add:false,
            callback: function(records, options, result) {
                Ext.getCmp("repository:comment").setTitle("Comments(" + records.length + ")");
            }
        });
        formPanel.getForm().setValues({id:snippetId});
    };
};
Ext.extend(Repository.CommentPanel, Ext.Panel);

/**
 * snippet detail info tab panel
 */
Repository.DetailTabPanel = function() {
    Repository.DetailTabPanel.superclass.constructor.call(this, {
        deferredRender:false,
        autoTabs:true,
        activeTab:0,
        id:"repository:detailTabPanel",
        el:'repository:detailTabPanel',
        autoWidth:false,
        autoScroll:true,
        region:'center'
    });
    /**
     * refresh snippet detail information
     * @param snippetId snippet id
     */
    this.refreshDetail = function(snippetId) {
        var updater = Ext.get("snippetDetailCodeDiv").getUpdater();
        updater.update({
            url: "/snippet/showPart.action",
            method: 'POST',
            disableCaching: true,
            params: {id: snippetId,part:'code'}
        });
        updater.on("update", syntaxHighlighter);
        var updater2 = Ext.get("detailNotes").getUpdater();
        updater2.update({
            url: "/snippet/showPart.action",
            method: 'POST',
            disableCaching: true,
            params: {id: snippetId,part:'example'}
        });
        Ext.getCmp("repository:comment").loadComments(snippetId);
    };
    /**
     *init icon for tab
     */
    this.initIcons = function() {
        this.add(new Repository.CommentPanel());
        Layout.setTabIcon(this, this.items.itemAt(0), '/statics/images/source.png');
        Layout.setTabIcon(this, this.items.itemAt(1), '/statics/images/example.png');
        Layout.setTabIcon(this, this.items.itemAt(2), '/statics/images/comment.png');
    };
};
Ext.extend(Repository.DetailTabPanel, Ext.TabPanel);

/**
 * snippet detail view panel, include general info, description and code
 */
Repository.DetailViewPanel = function() {
    //code panel
    var codePanel = new Ext.Panel({
        title:'Code',
        region:'center'
    });
    var infoPanel = new Ext.Panel({
        title:'General Info',
        region:'north'
    });

    Repository.DetailViewPanel.superclass.constructor.call(this, {
        id: 'repository:detailView',
        layout: "border",
        border: false,
        height:50,
        items:[infoPanel, codePanel]
    });
};
Ext.extend(Repository.DetailViewPanel, Ext.Panel);
