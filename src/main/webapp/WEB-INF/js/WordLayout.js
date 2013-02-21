/**
    A manager for the letters in the word
*/
function WordLayout()
{
    /** An array of game objects 
        @type Array
    */
    this.children = new Array();
    
    /** An array of strings representing the letters
    	@type Array
    */
    this.word = null;
    
    /** An array of strings representing the displayed letters
    	@type Array
    */
    this.displayedWord = new Array();
    
    this.init = function(/**Array*/ word, /**Bounds*/ bounds) {
    	this.startupGameObject(bounds);
    	this.word = word;
    	
    	this.loadOriginalWord();
    	
    	return this;
    }
    
    /**
        The render loop
    */
    this.draw = function (/**CanvasRenderingContext2D*/ context)
    {
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
    };
    
    this.setChosenAnswer = function(/**String */ answer) {
    	for (var child in this.word) {
    		if (this.word[child] == " ") {
    			this.children[child].letter = answer;
    			this.displayedWord[child] = answer;
    		}
    	}
    };
    
    this.loadOriginalWord = function() {
    	this.displayedWord.splice(0, this.displayedWord.length);
    	this.children.splice(0, this.children.length);
    	var childSize = Math.min(this.bounds.width / this.word.length, this.bounds.height);
    	var childrenY = (this.bounds.height - childSize) / 2;
    	var childrenX = (this.bounds.width - childSize*this.word.length) / 2;
    	var padding = childSize * 0.05;
    	for(var i = 0; i < this.word.length; ++i) {
    		var childOrigin = new Point().init(this.bounds.origin.x + childrenX + (childSize*i) + padding, this.bounds.origin.y + childrenY + padding);
    		var childBounds = new Bounds().init(childOrigin, childSize - 2*padding, childSize - 2*padding);
    		var child = new WordCube().startupWordCube(this.word[i], childBounds);
    		this.children.push(child);
    		this.displayedWord.push(this.word[i]);
    	}
    }
    
    this.getDisplayedWord = function() {
    	var text = "";
    	for (var i = 0; i < this.displayedWord.length; ++i) {
    		text = text + this.displayedWord[i];
    	}
    	return text;
    };
    
}

WordLayout.prototype = new GameObject;