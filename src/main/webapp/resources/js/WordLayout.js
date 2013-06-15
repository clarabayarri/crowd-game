/**
    A manager for the letters in the word
*/
function WordLayout()
{
    /** An array of strings representing the displayed letters
    	@type Array
    */
    this.displayedWord = new Array();
    
    this.init = function(/**Array*/ letters, /**Bounds*/ bounds) {
    	this.startupTileLayout(letters, bounds);
    	
    	this.loadOriginalWord();
    	
    	return this;
    }
    
    /**
        The render loop
    */
    this.draw = function (/**CanvasRenderingContext2D*/ context)
    {
        // draw own background
        //context.fillStyle = "rgba(218, 119, 117, 1.0)";
        //context.fillRect(this.bounds.origin.x, this.bounds.origin.y, this.bounds.width, this.bounds.height);
        
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
    	for (var child in this.letters) {
    		if (this.letters[child] == " ") {
    			this.children[child].letter = answer;
    			this.displayedWord[child] = answer;
    		}
    	}
    };
    
    this.appendChosenAnswer = function(/**String */ answer) {
    	var lastChild = this.children[this.children.length -1];
    	var childOrigin = new Point().init(lastChild.bounds.origin.x + lastChild.bounds.width + 2 * lastChild.bounds.width * 0.05, lastChild.bounds.origin.y);
    	var childBounds = new Bounds().init(childOrigin, lastChild.bounds.width, lastChild.bounds.height);
    	var child = new WordCube().startupWordCube(lastChild.id + 1, answer, childBounds);
    	this.children.push(child);
    	this.displayedWord.push(answer);
    }
    
    this.setChosenAnswerForTileUnderPoint = function(/**Point*/ point, /**String */ answer) {
    	for (var child in this.children) {
    		if (this.children[child].bounds && this.children[child].bounds.containsPoint(point)) {
    			this.children[child].letter = answer;
    			this.displayedWord[child] = answer;
    			return;
    		}
    	}
    };
    
    this.loadOriginalWord = function() {
    	this.displayedWord.splice(0, this.displayedWord.length);
    	for(var i = 0; i < this.letters.length; ++i) {
    		this.displayedWord.push(this.letters[i]);
    	}
    	this.loadChildren();
    }
    
    this.loadChildren = function() {
    	this.children.splice(0, this.children.length);
    	this.childSize = Math.min(this.bounds.width / this.displayedWord.length, this.bounds.height);
    	this.childrenY = (this.bounds.height - this.childSize) / 2;
    	this.childrenX = (this.bounds.width - this.childSize*this.displayedWord.length) / 2;
    	var padding = this.childSize * 0.05;
    	for(var i = 0; i < this.displayedWord.length; ++i) {
    		var childOrigin = new Point().init(this.bounds.origin.x + this.childrenX + (this.childSize*i) + padding, this.bounds.origin.y + this.childrenY + padding);
    		var childBounds = new Bounds().init(childOrigin, this.childSize - 2*padding, this.childSize - 2*padding);
    		var child = new WordCube().startupWordCube(i, this.displayedWord[i], childBounds);
    		this.children.push(child);
    	}
    }
    
    this.getDisplayedWord = function() {
    	var text = "";
    	for (var i = 0; i < this.displayedWord.length; ++i) {
    		if (this.children[i].visible) {
    			text = text + this.displayedWord[i];
    		}
    	}
    	return text;
    };
    
    this.removeChildWithId = function(/**Number*/ id) {
    	var child = this.indexOfChildWithId(id);
    	this.displayedWord.splice(child,1);
    	this.loadChildren();
    }
    
    this.indexOfChildWithId = function(/**Number*/ id) {
    	for (var child in this.children) {
    		if (this.children[child].id == id) {
    			return child;
    		}
    	}
    }
    
    this.exchangeChildrenWithIds = function(/**Number*/ id1, /**Number*/ id2) {
    	var childIndex1 = this.indexOfChildWithId(id1);
    	var childIndex2 = this.indexOfChildWithId(id2);
    	var child1 = this.children[childIndex1];
    	var child2 = this.children[childIndex2];
    	if (child1 != child2) {
    		var padding = this.childSize * 0.05;
    		var childOrigin1 = new Point().init(this.bounds.origin.x + this.childrenX + (this.childSize*id1) + padding, this.bounds.origin.y + this.childrenY + padding);
    		var childOrigin2 = new Point().init(this.bounds.origin.x + this.childrenX + (this.childSize*id2) + padding, this.bounds.origin.y + this.childrenY + padding);
    		child1.moveTo(childOrigin2);
    		child2.animateMoveTo(childOrigin1, 0, null);
    		child1.id = id2;
    		child2.id = id1;
    		this.children[childIndex1] = child2;
    		this.children[childIndex2] = child1;
    		this.displayedWord[childIndex1] = child2.letter;
    		this.displayedWord[childIndex2] = child1.letter;
    	}
    }
    
    this.getCutIndexForPoint = function(/**Point*/ point) {
        var bigOrigin = new Point().init(this.bounds.origin.x - this.children[0].bounds.width/2, this.bounds.origin.y);
    	var bigBounds = new Bounds().init(bigOrigin, this.bounds.width + this.children[0].bounds.width, this.bounds.height);
        if (bigBounds.containsPoint(point)) {
    		var index = 0;
    		for (var i = 0; i < this.children.length; ++i) {
    			if (point.x > this.children[i].bounds.origin.x + (this.children[i].bounds.width / 2)) {
    				++index;
    			}
    		}
    		return index;
    	}
    }
    
    this.addSpaceAtIndex = function(/**Number*/ index) {
    	this.displayedWord.splice(index, 0, " ");
    	this.loadChildren();
    }
    
    this.addLetterAtIndex = function(/**String*/ letter, /**Number*/ index) {
    	this.displayedWord.splice(index, 0, letter);
    	this.loadChildren();
    }
    
    
    this.animateSuccess = function(/**Function*/ callback) {
    	for (var i = 0; i < this.children.length; ++i) {
    		var child = this.children[i];
    		var padding = this.childSize * 0.05;
    		var childOrigin = new Point().init(this.bounds.origin.x + this.childrenX + (this.childSize*i) + padding, this.bounds.origin.y + this.childrenY + padding);
    		var points = new Array();
    		points[0] = new Point().init(childOrigin.x, childOrigin.y - 70);
    		points[1] = new Point().init(childOrigin.x, childOrigin.y + 40);
    		points[2] = new Point().init(childOrigin.x, childOrigin.y - 25);
    		points[3] = new Point().init(childOrigin.x, childOrigin.y);
       		if (i == this.children.length - 1) {
    			child.animateMoveToPoints(points, i*15, callback);
    		} else {
    			child.animateMoveToPoints(points, i*15, null);
    		}
    		
    	}
    }
    
    this.animateFail = function(/**Function*/ callback) {
    	for (var i = 0; i < this.children.length; ++i) {
    		var child = this.children[i];
    		var padding = this.childSize * 0.05;
    		var childOrigin = new Point().init(this.bounds.origin.x + this.childrenX + (this.childSize*i) + padding, this.bounds.origin.y + this.childrenY + padding);
    		var points = new Array();
    		points[0] = new Point().init(childOrigin.x + 20, childOrigin.y);
    		points[1] = new Point().init(childOrigin.x - 20, childOrigin.y);
    		points[2] = new Point().init(childOrigin.x + 10, childOrigin.y);
    		points[3] = new Point().init(childOrigin.x, childOrigin.y);
    			if (i == this.children.length - 1) {
    			child.animateMoveToPoints(points, 0, callback);
    		} else {
    			child.animateMoveToPoints(points, 0, null);
    		}
    		
    	}
    }
    
}

WordLayout.prototype = new TileLayout;