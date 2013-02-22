/**
    A manager for the letters in the word
*/
function AnswerLayout()
{
    /** An array of game objects 
        @type Array
    */
    this.children = new Array();
    
    /** An array of strings representing the letters
    	@type Array
    */
    this.answers = null;
    
    this.init = function(/**Array*/ answers, /**Bounds*/ bounds) {
    	this.startupGameObject(bounds);
    	this.answers = answers;
    	
    	this.loadChildren();
    	return this;
    }
    
    this.loadChildren = function() {
    	var childSize = Math.min(this.bounds.width * 0.95 / this.answers.length, this.bounds.height * 0.9);
    	var padding = childSize * 0.05;
    	var childrenY = (this.bounds.height * 0.9 - childSize);
    	var childrenX = (this.bounds.width - childSize*this.answers.length - padding * (this.answers.length)) / 2;
    	for(var i = 0; i < this.answers.length; ++i) {
    		var childOrigin = new Point().init(this.bounds.origin.x + childrenX + ((childSize + padding)*i) + padding, this.bounds.origin.y + childrenY + padding);
    		var childBounds = new Bounds().init(childOrigin, childSize - padding, childSize - padding);
    		var child = new WordCube().startupWordCube(this.answers[i], childBounds);
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
    
    
    this.getClickedAnswer = function (/**Point*/ point) {
    	var clickedTile = this.getClickedTile(point);
    	if (clickedTile != null) return clickedTile.letter;
    	return null;
    };
    
    this.getClickedTile = function(/**Point*/ point) {
    	for (var child in this.children) {
    		if (this.children[child].bounds.containsPoint(point)) {
    			return this.children[child].copy();
    		}
    	}
    	return null;
    }
    
    this.hideChildUnderPoint = function(/**Point*/ point) {
    	for (var child in this.children) {
    		if (this.children[child].bounds.containsPoint(point)) {
    			this.children[child].hide();
    		}
    	}
    }
    
    this.showAllChildren = function() {
    	for (var child in this.children) {
    		if (!this.children[child].visible) {
    			this.children[child].show();
    		}
    	}
    }
    
}

AnswerLayout.prototype = new GameObject;