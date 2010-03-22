/**
 * An UltraEdit 15-16 script that add snippets feature.
 *
 * For more snippets, please visit http://snippet.mvnsearch.org
 *
 * @author   Jacky Chan <linux_china@hotmail.com>
 * @version  2010-03-22
 */

// The string used in the snippets to represent where the cursor should be moved to.
var cursor_string = '$cursor$';

//////// Snippets List //////////////////////////////////////////////////////////////

// Each property of this object should be a snippet, with the name being the trigger string
// and the value being an array of the lines that will replace the trigger string.
var snippet_list = {
    // HTML
    'js.name': [
        'String.prototype.trim = function() {',
        '  return this.replace(/^\\s+|\\s+$/g,"");',
        '}'
    ],
    'dl': [
        '<dl>',
        '	<dt>$cursor$</dt>',
        '	<dd></dd>',
        '</dl>'
    ]
};


//////// CODE //////////////////////////////////////////////////////////////////

// For some reason, prior to version 16 currentColumnNum reported column numbers starting from 0,
// but gotoLine() and gotoLineSelect() expect column numbers starting from 1.
var current_column_offset = (typeof UltraEdit.activeDocumentIdx == 'undefined' ? 1 : 0);
var doc = UltraEdit.activeDocument;  // For easier reference.
var original_line = doc.currentLineNum;
var original_column = doc.currentColumnNum + current_column_offset;

// If no text is currently selected, select the word under the cursor.
if (! doc.isSel()) {
    doc.selectLine();
    var selection = UltraEdit.activeDocument.selection.replace(/^[\s\t\n]+/m, '').replace(/[\s\t\n]+$/m, '')
//    UltraEdit.outputWindow.write("titl = " + selection);
    // If selectWord() didn't select anything, try CTRL+SHIFT+LEFT ARROW.

        doc.startSelect();
         doc.key('CTRL+LEFT ARROW');
        doc.endSelect();
       UltraEdit.outputWindow.write("titl = " + doc.selection);
}

// Try to get a snippet from the list that matches the selected text.
var snippet = snippet_list[doc.selection];

// If there is no snippet, just move the cursor back to where it was.
if (! snippet) {
    doc.gotoLine(original_line, original_column);
} else {
    var snippet_code;
    // If the snippet is only a single line, just use that string as is.
    if (snippet.length == 1) {
        snippet_code = snippet[0];
    }
    // Otherwise, join the snippet's lines into a single string, adding the current line's
    // leading whitespace to all of the following lines in the process.
    else {
        // The selected trigger string has to be deleted now because that selection can't
        // be restored after we make other selections.
        doc.deleteText();
        original_line = doc.currentLineNum;
        original_column = doc.currentColumnNum + current_column_offset;
        // Get the current line's leading whitespace, being careful to not include any whitespace
        // that was after the trigger string now that the trigger string has been deleted.
        doc.gotoLineSelect(doc.currentLineNum, 1);
        var leading_whitespace = doc.selection.replace(/^(\s*).*$/, '$1');
        // Go to the end of the line to check for EOF, and because selectLine() stops at soft word-wrap line breaks.
        doc.gotoLine(doc.currentLineNum, 20000);
        // If the current line is the last in the file and therefore doesn't have a line ending
        // then insert another line.
        if (doc.isEof()) {
            doc.insertLine();
            doc.deleteToStartOfLine();  // Needed because insertLine() might preserve indentation.
            doc.gotoLine(original_line, 20000);
        }
        // Get the line ending from the current line.
        doc.selectLine();
        var line_ending = doc.selection.replace(/^.*([\r\n]*)$/, '$1');
        snippet_code = snippet.join(line_ending + leading_whitespace);
        // Move the cursor back to the insertion point.
        doc.gotoLine(original_line, original_column);
    }
    // Output the snippet.  If there is a cursor string in the snippet then output it
    // in two halves so the cursor can be moved to the specified position in the middle.
    if (snippet_code.indexOf(cursor_string) > -1) {
        var snippet_half = snippet_code.split(cursor_string, 2);
        doc.write(snippet_half[0]);
        var cursor_line = doc.currentLineNum;
        var cursor_column = doc.currentColumnNum + current_column_offset;
        doc.write(snippet_half[1]);
        doc.gotoLine(cursor_line, cursor_column);
    } else {
        doc.write(snippet_code);
    }
}