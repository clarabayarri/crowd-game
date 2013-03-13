/**
    A manager for the letters in the word
*/
function ContinueDialog()
{
    /** An array of strings representing the displayed letters
    	@type Array
    */
    this.word = null;
    
    this.button = null;
    
    this.init = function(/**String*/ word, /**Bounds*/ bounds) {
    	this.startupGameObject(bounds);
    	
    	this.word = word;
    	this.loadButton();
    	    	
    	return this;
    }
    
    this.loadButton = function() {
    	var buttonOrigin = new Point().init(this.bounds.origin.x + this.bounds.width * 0.3, this.bounds.origin.y + this.bounds.height * 0.7);
    	var buttonBounds = new Bounds().init(buttonOrigin, this.bounds.width * 0.4, this.bounds.height * 0.2);
    	this.button = new ContinueButton().startupContinueButton(buttonBounds);
    }
    
    this.draw = function (/**CanvasRenderingContext2D*/ context) {
    	// draw tile
    	context.fillStyle = "rgba(248, 238, 207, 1.0)";
    	context.fillRect(this.bounds.origin.x, this.bounds.origin.y, this.bounds.width, this.bounds.height);
    	
    	// draw text
    	context.lineWidth=1;
    	context.fillStyle="#000000";
    	context.lineStyle="#000000";
    	context.font="bold 46px sans-serif";
    	context.textAlign = "center";
    	context.textBaseline = "middle";
    	var center = this.bounds.center();
    	context.fillText(this.word, center.x, this.bounds.origin.y + this.bounds.height*0.3);
    	
    	if (this.button) {
    		this.button.draw(context);
    	}
    }
    
    this.onClick = function(/**Point*/ clickPoint) {
    	if (this.button.bounds.containsPoint(clickPoint)) {
    		return true;
    	}
    	return false;
    }
}

ContinueDialog.prototype = new GameObject;