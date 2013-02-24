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
    	var childWidth = this.bounds.width;
    	var childHeight = this.bounds.height / this.letters.length;
    	var childrenX = (this.bounds.width - childWidth) / 2;
    	var childrenY = (this.bounds.height - childHeight*this.letters.length) / 2;
    	var padding = childHeight * 0.05;
    	for(var i = 0; i < this.letters.length; ++i) {
    		var childOrigin = new Point().init(this.bounds.origin.x + childrenX + padding, this.bounds.origin.y + childrenY + (childHeight*i) + padding);
    		var childBounds = new Bounds().init(childOrigin, childWidth - 2*padding, childHeight - 2*padding);
    		var child = new WordCube().startupWordCube(i, this.letters[i], childBounds);
    		this.children.push(child);
    	}
    }
    
    /**
        The render loop
    */
    this.draw = function (/**CanvasRenderingContext2D*/ context)
    {
    	if (this.visible) {
    		// draw own background
    		context.fillStyle = "rgba(218, 119, 117, 1.0)";
    		context.fillRect(this.bounds.origin.x, this.bounds.origin.y, this.bounds.width, this.bounds.height);
    		
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