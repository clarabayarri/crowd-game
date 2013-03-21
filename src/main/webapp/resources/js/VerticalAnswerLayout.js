/**
    A manager for the letters in the word
*/
function VerticalAnswerLayout()
{
    
    this.init = function(/**Array*/ letters, /**Bounds*/ bounds) {
    	this.startupTileLayout(letters, bounds);
    	
    	this.loadChildren();
    	return this;
    }
    
    this.loadChildren = function() {
    	this.children.splice(0, this.children.length);
    	var childWidth = this.bounds.width;
    	this.childSize = this.bounds.height / this.letters.length;
    	this.childrenX = (this.bounds.width - childWidth) / 2;
    	this.childrenY = (this.bounds.height - this.childSize*this.letters.length) / 2;
    	var padding = this.childSize * 0.05;
    	for(var i = 0; i < this.letters.length; ++i) {
    		var childOrigin = new Point().init(this.bounds.origin.x + this.childrenX + 2*padding, this.bounds.origin.y + this.childrenY + (this.childSize*i) + padding);
    		var childBounds = new Bounds().init(childOrigin, childWidth - 4*padding, this.childSize - 2*padding);
    		var child = new WordCube().startupWordCube(i, this.letters[i], childBounds);
    		this.children.push(child);
    	}
    }
    
    this.getClickedTile = function(/**Point*/ point) {
    	if (this.bounds.containsPoint(point) && 
    		point.x > (this.bounds.origin.x + this.childrenX) && 
    		point.x < (this.bounds.origin.x + this.bounds.width - this.childrenX) && 
    		point.y > (this.bounds.origin.y + this.childrenY) && 
    		point.y < (this.bounds.origin.y + this.bounds.height - this.childrenY)) {
    		var id = Math.floor((point.y - this.childrenY - this.bounds.origin.y) / this.childSize);
    		return this.children[id].copy();
    	}
    	return null;
    }
    
    /**
        The render loop
    */
    this.draw = function (/**CanvasRenderingContext2D*/ context)
    {
    	if (this.visible) {
    		// draw own background
    		context.shadowColor = '#000000';
    		context.shadowBlur = 5;
    		context.shadowOffsetX = 2;
    		context.shadowOffsetY = 2; 
    		context.fillStyle = "rgba(13, 114, 98, 1.0)";
    		context.fillRect(this.bounds.origin.x, this.bounds.origin.y, this.bounds.width, this.bounds.height);
    		context.shadowBlur = 0;
    		context.shadowOffsetX = 0;
    		context.shadowOffsetY = 0;
    		
    		// then draw the children
    		for (x in this.children)
    		{
    		    if (this.children[x].draw)
    		    {
    		        this.children[x].draw(context);
    		    }
    		}
    	}
    };
    
}

VerticalAnswerLayout.prototype = new TileLayout;