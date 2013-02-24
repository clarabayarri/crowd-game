/**
    A manager for the letters in the word
*/
function AnswerLayout()
{
    
    this.init = function(/**Array*/ letters, /**Bounds*/ bounds) {
    	this.startupTileLayout(letters, bounds);
    	
    	this.loadChildren();
    	return this;
    }
    
    this.loadChildren = function() {
    	this.children.splice(0,this.children.length);
    	var childSize = Math.min(this.bounds.width * 0.95 / this.letters.length, this.bounds.height * 0.9);
    	var padding = childSize * 0.05;
    	var childrenY = (this.bounds.height * 0.9 - childSize);
    	var childrenX = (this.bounds.width - childSize*this.letters.length - padding * (this.letters.length)) / 2;
    	for(var i = 0; i < this.letters.length; ++i) {
    		var childOrigin = new Point().init(this.bounds.origin.x + childrenX + ((childSize + padding)*i) + padding, this.bounds.origin.y + childrenY + padding);
    		var childBounds = new Bounds().init(childOrigin, childSize - padding, childSize - padding);
    		var child = new WordCube().startupWordCube(i, this.letters[i], childBounds);
    		this.children.push(child);
    	}
    	
    }
    
    /**
        The render loop
    */
    this.draw = function (/**CanvasRenderingContext2D*/ context)
    {
        // draw own background
        context.fillStyle = "rgba(218, 119, 117, 1.0)";
        context.fillRect(this.bounds.origin.x, this.bounds.origin.y, this.bounds.width, this.bounds.height);
        
        context.fillStyle = "rgba(210, 19, 117, 1.0)";
        context.fillRect(this.bounds.origin.x, this.bounds.origin.y + this.bounds.height * 0.9, this.bounds.width, this.bounds.height*0.1);
        
        // then draw the children
        for (x in this.children)
        {
            if (this.children[x].draw)
            {
                this.children[x].draw(context);
            }
        }
        
    };
    
}

AnswerLayout.prototype = new TileLayout;