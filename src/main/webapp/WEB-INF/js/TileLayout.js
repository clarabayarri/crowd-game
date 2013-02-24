/**
    A manager for the tiles in a layout
*/
function TileLayout()
{
    /** An array of game objects 
        @type Array
    */
    this.children = new Array();
    
    /** An array of strings representing the letters
    	@type Array
    */
    this.letters = null;
    
    this.startupTileLayout = function(/**Array*/ letters, /**Bounds*/ bounds) {
    	this.startupGameObject(bounds);
    	this.letters = letters;
    	
    	return this;
    }
    
    this.getClickedTile = function(/**Point*/ point) {
    	for (var child in this.children) {
    		if (this.children[child].bounds && this.children[child].bounds.containsPoint(point)) {
    			return this.children[child].copy();
    		}
    	}
    	return null;
    }
    
    this.hideChildWithId = function(/**Number*/ id) {
    	for (var child in this.children) {
    		if (this.children[child].id && this.children[child].id == id) {
    			this.children[child].hide();
    		}
    	}
    }
    
    this.showAllChildren = function() {
    	this.show();
    	for (var child in this.children) {
    		if (!this.children[child].visible && this.children[child].show) {
    			this.children[child].show();
    		}
    	}
    }
    
}

TileLayout.prototype = new GameObject;