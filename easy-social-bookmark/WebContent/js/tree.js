
var KEY_ENTER=0x0D;
var KEY_END = 0x23;
var KEY_HOME = 0x24;
var KEY_LEFT = 0x25;
var KEY_UP = 0x26;
var KEY_RIGHT = 0x27;
var KEY_DOWN = 0x28;

var ROLE_TREEITEM = "treeitem";
var ROLE_TREE = "tree";
var ROLE_GROUP = "group";
var STATE_EXPANDED = "aria-expanded";

/******************* Begin  generic helper functions ****************/

/* @method public getTarget() - helper function to extract the element which fired the event from the event object.
* @param event - event object
* @return - the appropriate target based on the varibles supported in the event object.
*/
function getTarget(event) {
  var target = null;
  if (event.target) {
    target = event.target;
  }
  else if (event.srcElement) {
    target = event.srcElement;
  }
  return target;
}

var DEBUG = true;
/* @method public debugOut() - helper function to use the dump() command to send information to the dump window in Firefox
* or to use alert() where dump() is not supported.  To enable the console window in Firefox start FF  with the command line option
* "-console". Also, set the about:config option "browser.dom.window.dump.enabled" to true.
* @param debugStr - the string to output.
*/
var DEBUG = true;  // turn on and off output of debugging strings
function debugOut(debugStr) {
  if (DEBUG) {
    if (window.dump) {
      dump(debugStr + "\n");
    }
    else if (alert) {
      alert(debugStr);
    }
  }
}
/********************* End  generic helper functions ****************/
COLLAPSE = "minus.gif";
EXPAND= "plus.gif";

var TEXT_NODE=3; // IE does not recognize Node.TEXT_NODE nor nodeObj.TEXT_NODE so need to define

/* public isText() - helper function to determine if a particular node is a TEXT_NODE or not.
* Generally text nodes are ignored.
* @param node - the node to test
* @return boolean - true if node is a node.TEXT_NODE, false otherwise
*/
function getRole(node)
{
  try {
    var role = node.getAttribute("role");
    return role ? role : "";
  } catch(ex) {
    return "";
  }
};

function isText(node)
{
  return node && node.nodeType == TEXT_NODE;
};

/* @method public isGroupClosed() - function to determine if a treeitem is expanded or collapsed.
* @param groupItem - DOM object representing the treeitem
* @return boolean - true is the item is NOT expanded; false if IS expanded.
*/
function isGroupClosed(groupItem)
{
  var treeItemForGroup = getRelativeTreeItem(groupItem, -1, false);
  return !treeItemForGroup || treeItemForGroup.getAttribute("aria-expanded") == 'false';
};

/*  @method public getFirstTreeItem() - find the first item in the tree
* @param tree - the Tree object
* @return - the first treeitem or null;
*/
function getFirstTreeItem(tree)
{
  var first = tree.firstChild;
  var treeItem = null;
  // FF reports firstChild as text node while IE does not
  while (first && isText(first) ) {
    first = first.nextSibling;  // tree had better have a child that is not text!
  }
  if (first && first.childNodes) {
      for (var i=0; i<first.childNodes.length; i++) {
      if (getRole(first.childNodes[i]) == ROLE_TREEITEM) {
        treeItem = first.childNodes[i];
        break;
      }
    }
  }
  return treeItem;
};

/*  @method public getLastTreeITem - finds the last item in the tree
* @param tree - the tree object
* @return - the last treeitem
* If the tree has any expandable nodes, the children will be in <divs> that are marked as groups.
* Thus, the last child of the tree might be a group node that also has children.
*/
function getLastTreeItem(tree)
{
  var lastTreeItem = tree;
  do {
    lastTreeItem = lastTreeItem.lastChild;
    if (isText(lastTreeItem)) {
      lastTreeItem = lastTreeItem.previousSibling;
    }
  }
  while (getRole(lastTreeItem) == ROLE_GROUP);
  return  getTreeItemFromPar(lastTreeItem);
};

/* @method public getRelativeTreeItem()
* This function finds the next relative tree item in the hierarchy.  It uses the structure of the HTML to determine
*   the next relative item.  The current structure is:
    *each treeitem is a span within a div:
        <div><span treeitem /></div>
    *expandable nodes also contain an img within the div:
        <div><img expand/collapse /><span treeitem1 /></div>
    *childnodes are grouped within a div with role=group:
        <div group><div><span treeitem1.1 /></div><div><span treeitem1.2 /></div></div>
* @param treeitem:  div object (may be parent of treeItem span  or group div)
* @param delta: -1=previous; 1=next
*   @param wrap: true = wrap the searches; false = do not wrap when reach top or bottom
* @ returns the actual treeitem span object
*/
function getRelativeTreeItem(treeItem, delta, wrap)
{
// treeItem is really the parent div of the treeItem
  // delta: -1 = prev, 1 = next
  if (delta == 1) {  // Next
    var newTreeItem = treeItem.nextSibling;
    while (isText(newTreeItem)) {
      newTreeItem = newTreeItem.nextSibling;
    }
  // if(newTreeItem)
    //  debugOut(newTreeItem.id + " " + getRole(newTreeItem) );

    var treeItemSpan = null;
    if (newTreeItem) {
      var role = getRole(newTreeItem);
      if (role == ROLE_GROUP) {
        if (isGroupClosed(newTreeItem)) {
          return getRelativeTreeItem(newTreeItem, 1, wrap);
        } // end of closed group
        else {
          newTreeItem = newTreeItem.firstChild;
          if (isText(newTreeItem)) {
            newTreeItem = newTreeItem.nextSibling;
          }
        } // end of open group
       } // end of if group
       if (newTreeItem && treeItemSpan == null) {
          //extract the actual treeItem span from the div
          for(var i=0; i < newTreeItem.childNodes.length; i++ ) {
            treeItemSpan = newTreeItem.childNodes[i]; //debugOut(treeItemSpan.tagName + " " + getRole(treeItemSpan) + "\n");
            if (treeItemSpan.nodeName == "SPAN" && getRole(treeItemSpan) == ROLE_TREEITEM) {
              return treeItemSpan;
            }
          }
        }
       } // end of if newTreeItem
   } // end of next
  else {    // Prev
    var newTreeItem = treeItem.previousSibling;
    if (isText(newTreeItem)) {
      newTreeItem = newTreeItem.previousSibling;
    }
    if (newTreeItem) {
      var role;
      while ((role = getRole(newTreeItem)) == ROLE_GROUP) {
        if (isGroupClosed(newTreeItem)) {
          return getRelativeTreeItem(newTreeItem, -1, wrap);
        } // end of if closed group
        else {
          newTreeItem = newTreeItem.lastChild;
          if (isText(newTreeItem)) {
            newTreeItem = newTreeItem.previousSibling;
          }
        } // end of open group
      }  // end of while
       if (newTreeItem && treeItemSpan == null) {
          //extract the actual treeItem span from the div
          for(var j=0; j < newTreeItem.childNodes.length; j++ ) {
            treeItemSpan = newTreeItem.childNodes[j];
            if (treeItemSpan.nodeName == "SPAN" && getRole(treeItemSpan) == ROLE_TREEITEM) {
              return treeItemSpan;
            }
          }
        }
    } // end of if newTreeItem
  } // end of prev
  if (getRole(treeItem.parentNode) == ROLE_TREE) {
    if (!wrap) {
      return null;
    }
    var tree = getTree(treeItem)
    return (delta == 1)? getFirstTreeItem(tree) : getLastTreeItem(tree);
  }
  return getRelativeTreeItem(treeItem.parentNode, delta, wrap); // Recursive
};

/* @method public getTree() - given a treeitem object, find the parent tree.
* @param treeItem - the object with role of treeitem.
* @return - the tree object or null if not found.
* Searches the parent hierarchy until an object with role== tree is found.
*/
function getTree(treeItem)
{
  var tree = treeItem;
  try {

    while (getRole(tree) != ROLE_TREE) {
          tree = tree.parentNode;
    }
  } catch(ex) {
    debugOut("error in getTree(); " + ex.message);
    return null; // No tree container for tree item
  }
  return tree;
};

var gNewFocus;  // global variable of the item to receive focus
/*  @method public setFocus()  - uses setTimout to set focus to the specified item.
* @param  newFocus - object to call focus() method on
* This method is needed in order to queue the focus to an item during an event handler.
*/
function setFocus(newFocus)
{
  if (newFocus) {
    gNewFocus = newFocus;  // must store in a global variable so object is available when setTimeout occurs.
    setTimeout("gNewFocus.focus();", 0);
  }
};

/*  @method public treeItemEvent - main event handler for capturing key movements.
* @param event - event object
* @returns boolean on whether to propagate (true) or consume (false) the event.
* Event handlers are set on the tree object and thus there is some special casing for images and other objects within
* the tree that are not treeitems.
*/
function treeItemEvent(event)
{ // debugOut(event.type + " : " + event.keyCode);
  var treeItem = getTarget(event); //event.target;
  if (treeItem.tagName.toLowerCase() == "img") {
    var tree = getTree(treeItem);
    // get the treeItem from tree.lastFocus
    if (tree.lastFocus == "undefined") {
      tree.lastFocus = getFirstTreeItem(tree);
    }
    treeItem = tree.lastFocus;
  }
  var hasChildren = treeItem.getAttribute("aria-expanded");
  var isItemOpen = hasChildren && hasChildren == 'true';
  var focusDelta = 0;  // (-1 = up, 0 = no change, 1 = down)

  var toggleitem = false;
  if (event.type == "dblclick" && event.button == 0) {
    toggleitem = hasChildren;
    getTree(treeItem)._incrementalString = "";
  }
  else if (event.type == "keydown") {
    if (event.altKey) {
      return true;  // Browser should use this, the tree view doesnot need alt-modified keys
    }
    // XXX Implement multiple selection (ctrl+arrow, shift+arrow, ctrl+space)
    if (event.keyCode == KEY_HOME) {
      try {
        treeItemFocus(getFirstTreeItem(getTree(treeItem)),true);
      } catch(ex) {  }
      debugOut(ex);
      return false;
    }
    if (event.keyCode == KEY_ENTER) {
      toggleitem = hasChildren;
    }
    else if (event.keyCode == KEY_DOWN) {
      focusDelta = 1;
    }
    else if (event.keyCode == KEY_UP) {
      focusDelta = -1;
    }
    else if (event.keyCode == KEY_LEFT) {
      if (isItemOpen) {
        toggleitem = true;
      }
      else {
        var groupItem = treeItem.parentNode.parentNode;  // relies on treeitems being spans nested in divs
        if (getRole(groupItem) == ROLE_GROUP) {
          var nextItem = getRelativeTreeItem(groupItem, -1, false);
          treeItemFocus(nextItem, true);

        }
      }
    }
    else if (event.keyCode == KEY_RIGHT) {
      if (isItemOpen) {
        focusDelta = 1;
      }
      else {
        toggleitem = hasChildren;
      }
    }
    else if (event.keyCode == KEY_END) {
      try {
        var newTreeItemFocus = getLastTreeItem(getTree(treeItem));
        treeItemFocus(newTreeItemFocus,true);
      } catch(ex) { }
      debugOut(ex);
      return false;   // Consume the event
    }
    else {
      return true;  // We didn't need key, don't consume event
    }
  }
  else if (event.type == "keypress") {
    // Implement incremental find here, instead of keydown, because we
    // need to capture printable characters in a keypress handler
    var tree = getTree(treeItem);
    tree._incrementalString = "";
    var key;
    if (event.charCode) {
      key = event.charCode;
    }
    else {
      key = event.keyCode;  // IE uses keyCode - for keypress event the value should be the same as event.charCode
    }
    if (key > 32 && !event.altKey && !event.ctrlKey &&
       !event.shiftKey && !event.metaKey) {
      key = String.fromCharCode(key);
    }
    else {
      tree._incrementalString = "";
      return true;
    }
    key = key.toLowerCase();
    if (event.timeStamp - tree._lastKeyTime > 1000) {
      tree._incrementalString = key;
    }
    else {
      // IE does not have event.timeStamp so will always end up in this else in IE
      tree._incrementalString += key;
     }
    tree._lastKeyTime = event.timeStamp;
    var length = tree._incrementalString.length;
    var incrementalString = tree._incrementalString;
    var charIndex = 1;
    while (charIndex < length && incrementalString[charIndex] == incrementalString[charIndex - 1])
      charIndex++;
    // If all letters in incremental string are same, just try to match the first one
    if (charIndex == length) {
      length = 1;
      incrementalString = incrementalString.substring(0, length);
    }
    var origTreeItem = treeItem;
    if (length == 1) {
      treeItem = getRelativeTreeItem(treeItem.parentNode, 1, true);
    }
    do {
      var text = treeItem.firstChild.data;
      if (treeItem.firstChild.data.substring(0, length).toLowerCase() == incrementalString) {
        treeItemFocus(treeItem, true);
        return false;
      }
      treeItem = getRelativeTreeItem(treeItem.parentNode, 1, true);
    } while (treeItem != origTreeItem);
    return false;
  }
  else if (event.type == "click") {
      getTree(treeItem)._incrementalString = '';
  }
  else {
    return true; // continue propagating event;
  }
  if (toggleitem) {
    toggleExpandCollapse(treeItem, isItemOpen);

    return false; // consume event
  }
  if (focusDelta) {
    //try {
      // treeitem is a span, must pass parent div to getRelativeTreeItem();
      var nextItem = getRelativeTreeItem(treeItem.parentNode, focusDelta, false);
      if (nextItem) {
        treeItemFocus(nextItem, true);
      }
    //} catch(ex) { debugOut("error in focusDelta " + ex.message); }
    return false;
  }
  return true;  // Browser can still use event
};

/* @method treeItemClick() is the event handler for clicks inside of the tree.  The item clicked upon receives focus.
* @param event - the event object
* @return boolean - true to propagate the event, false to consume it
*/
function treeItemClick(event) {
  var treeItem = getTarget(event);
  bEventContinue = true;
  if (isValidTreeItem(treeItem) ) {
    // only process if click was on an actual tree item
    treeItemFocus(treeItem, false);  // update the tabindex and lastFocused variables, item already has focus from being clicked upon
    bEventContinue = false;
  }
  else {
    if (treeItem.tagName.toLowerCase() != "img") {
      //reset focus to existing treeItem (can always find Tree from any child)
      var curTreeItem = getTree(treeItem).lastFocus;
      if (curTreeItem)
        setFocus(curTreeItem);
        bEventContinue = false;
    }
    // else not image so return true - only images are for expand/collapse which have their own event handler
  }
  return bEventContinue;

};

/* @method isValidTreeItem() - determines if object is actually a tree item
* @param treeItem - the possible tree item object
* @return boolean true if is an object with role of treeitem; false if not
*/
function isValidTreeItem(treeItem)
{
  var bIsTreeItem = false;
  var testItem = treeItem;

  if (isText(testItem)) {
    testItem = testItem.nextSibling;
  }
  if (getRole(testItem) == ROLE_TREEITEM ) {
    bIsTreeItem = true;
  }

  return bIsTreeItem;
};

/* @method public treeItemFocus - caches the last focus item on the tree and set the appropriate style and tabindex.
* Updates the style and tabindex of the previously focused treeitem.
* @param treeItem - the tree item to receive focus
* @param bFocus - whether or not to actually set focus to the item.  In cases where this is called from a click handler there
*   is no need to set focus because the click has set the focus.
*/
function treeItemFocus(treeItem, bFocus)
{
  // Cache last focused tree item on the tree
  // The last focused item is always the only item in the tab order for this tree,
  // so we need to set its tabindex to 0, and the others to -1
 // var treeItem = getTarget(event);
  var tree = getTree(treeItem);
  if (typeof tree.lastFocus == "undefined") {
    tree.lastFocus = getFirstTreeItem(tree); //debugOut(tree.lastFocus.firstChild.nodeValue);
  }
  tree.lastFocus.tabIndex = "-1";
  tree.lastFocus.className="treeitem";
  tree.lastFocus = treeItem;
  treeItem.tabIndex = "0";
  treeItem.className="treeitemfocus";
  if (bFocus == true) {
    setFocus(treeItem);
  }
};

/* @method toggleExpandCollaps() - toggles expand and collapse of the node.
* Called from imgToggle() or keyboard event handlers.
* @parm treeItem - object to check for expand/collapse capability
*/
function toggleExpandCollapse(treeItem, isItemOpen)
{
  treeItem.setAttribute("aria-expanded", isItemOpen? "false" : "true");
  var image = treeItem.previousSibling;
  if (isText(image)) {
    image = image.previousSibling;
  }
  var group = treeItem.parentNode
  group = group.nextSibling;
  if (isText(group)) {
    group = group.nextSibling;
  }
  if (!isItemOpen) {
    image.src = COLLAPSE;
    group.className="group";  //treeitem is span within div followed by group div
  }
  else {
    image.src = EXPAND;
    group.className="collapsedgroup";
  }
};

/*  @method public imgToggle() - event handler for the img tags that contain the expand/collapse icons.
/*  @param event - the event object
*/
function imgToggle(event)
{
  var imgItem = getTarget(event);
  if (!imgItem || imgItem.tagName.toLowerCase() != "img") {
    alert(imgItem);
  }
  var treeItemDiv = imgItem.parentNode;  // parent of expand/collapse img is div
  var treeItem = getTreeItemFromPar(treeItemDiv);
  var hasChildren = treeItem.getAttribute("aria-expanded") != null;
  var isItemOpen = hasChildren && treeItem.getAttribute("aria-expanded") == 'true';
  var tree= getTree(treeItem);
  if (isItemOpen) {
    // if closing: need to determine if current focus is on a treeitem within the hierarchy of this item - if so,
    // need to update focus to this item.
    // next sibling of expand/collapse item is group; check all children of group and its next siblings to see if any have focus
    var searchStart = treeItemDiv.nextSibling;
    while (searchStart && isText(searchStart) ) {
      searchStart = searchStart.nextSibling;
    }
    var bFound = false;
    bFound = isContainedChild(searchStart, tree.lastFocus);
    if (bFound) {
      treeItemFocus(treeItem,false); //update stored focus item; focus will actually be set with setFocus() call below
    }
  }
  toggleExpandCollapse(treeItem, isItemOpen);
  setFocus(tree.lastFocus);
}

/* @method public getTreeItemFromPar() - helper function to extract the inner span from the parent div.
* @param itemDiv - the div object that contains the actual treeitem span
* @return - the span object which is the treeitem.
*/
function getTreeItemFromPar(itemDiv)
{
  var treeItem = null;
  if (itemDiv && itemDiv.childNodes) {
    for (var i=0; i<itemDiv.childNodes.length; i++) {
      if (getRole(itemDiv.childNodes[i]) == ROLE_TREEITEM) {
        treeItem = itemDiv.childNodes[i];
        break;
      }
    }
  }
  return treeItem;
};

/* @method public isContainedChild() -  see if the item is found as any child within a parent tree. This is called when the
* mouse is used to collapse an item.  Must check to see if focus is currently in any children of the item being closed. This
* function recursively checks the childNodes of the parent item to see if the specifed item is found.
* @param parent - object where search for children begins
* @param item - treeItem object being search for within parent
* @returns - boolean true if item found, false if not.
*/

function isContainedChild(parent,item)
{
  var bFound = false;
  if (parent.hasChildNodes()) {
    for (var i=0; i<parent.childNodes.length; i++) {
      if (parent.childNodes[i] == item) {
        bFound = true;
      }
      else {
        bFound = isContainedChild(parent.childNodes[i],item);
      }
      if (bFound == true) {
        break;
      }
    }
  }
  return bFound;
};

