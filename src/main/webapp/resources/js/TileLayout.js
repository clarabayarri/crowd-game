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
    
    /** Offsets for children */
    this.childrenX = 0;
    this.childrenY = 0;
    this.childSize = 0;
    
    this.startupTileLayout = function(/**Array*/ letters, /**Bounds*/ bounds) {
    	this.startupGameObject(bounds);
    	this.letters = letters;
    	
    	return this;
    }
    
    this.getClickedTile = function(/**Point*/ point) {
    	if (this.bounds.containsPoint(point) && 
    		point.x > (this.bounds.origin.x + this.childrenX) && 
    		point.x < (this.bounds.origin.x + this.bounds.width - this.childrenX) && 
    		point.y > (this.bounds.origin.y + this.childrenY) && 
    		point.y < (this.bounds.origin.y + this.bounds.height - this.childrenY)) {
    		var id = Math.floor((point.x - this.childrenX - this.bounds.origin.x) / this.childSize);
    		return this.children[id].copy();
    	}
    	return null;
    }
    
    this.hideChildWithId = function(/**Number*/ id) {
    	for (var child in this.children) {
    		if (this.children[child].id === id) {
    			this.children[child].hide();
    			return;
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