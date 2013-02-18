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
    
    this.startupAnswerLayout = function(/**Array*/ answers, /**Number*/ x, /**Number*/ y, /**Number*/ width, /**Number*/ height) {
    	this.startupGameObject(x, y, width, height);
    	this.answers = answers;
    	
    	var childSize = Math.min(this.width * 0.95 / this.answers.length, this.height * 0.9);
    	var padding = childSize * 0.05;
    	var childrenY = (this.height * 0.9 - childSize);
    	var childrenX = (this.width - childSize*this.answers.length - padding * (this.answers.length)) / 2;
    	for(var i = 0; i < this.answers.length; ++i) {
    		var child = new WordCube();
    		child.startupWordCube(this.answers[i], this.x + childrenX + ((childSize + padding)*i) + padding, this.y + childrenY + padding, childSize - padding, childSize - padding);
    		this.children.push(child);
    	}
    	
    	return this;
    }
    
    /**
        The render loop
    */
    this.draw = function (/**CanvasRenderingContext2D*/ context)
    {
        // draw own background
        context.fillStyle = "rgba(218, 119, 117, 1.0)";
        context.fillRect(this.x, this.y, this.width, this.height);
        
        context.fillStyle = "rgba(210, 19, 117, 1.0)";
        context.fillRect(this.x, this.y + this.height * 0.9, this.width, this.height*0.1);
        
        // then draw the children
        for (x in this.children)
        {
            if (this.children[x].draw)
            {
                this.children[x].draw(context);
            }
        }
        
    };
    
    
    this.getClickedAnswer = function (/**Number*/ x, /**Number*/ y) {
    	for (var child in this.children) {
    		if (this.children[child].containsPoint(x,y)) {
    			return this.children[child].letter;
    		}
    	}
    	return null;
    };
    
}

AnswerLayout.prototype = new GameObject;