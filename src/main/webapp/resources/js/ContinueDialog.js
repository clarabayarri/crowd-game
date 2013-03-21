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
    
    this.tileBounds = null;
    
    this.init = function(/**String*/ word, /**Bounds*/ bounds) {
    	this.startupGameObject(bounds);
    	
    	this.word = word;
    	
    	var tileOrigin = new Point().init(this.bounds.width * 0.1, this.bounds.height * 0.2);
    	this.tileBounds = new Bounds().init(tileOrigin, this.bounds.width * 0.8, this.bounds.height * 0.6);
    	
    	this.loadButton();
    	    	
    	return this;
    }
    
    this.loadButton = function() {
    	var center = this.tileBounds.center();
    	var buttonOrigin = new Point().init(center.x - 100, this.tileBounds.origin.y + this.tileBounds.height * 0.7);
    	var buttonBounds = new Bounds().init(buttonOrigin, 200, 50);
    	this.button = new ContinueButton().startupContinueButton(buttonBounds);
    }
    
    this.draw = function (/**CanvasRenderingContext2D*/ context) {
    	//darken background
    	context.fillStyle = "rgba(42, 42, 42, 0.5)";
    	shadow(context, 5);
    	context.fillRect(this.bounds.origin.x, this.bounds.origin.y, this.bounds.width, this.bounds.height);
    	eraseShadow(context);
    	
    	// draw tile
    	context.fillStyle = "rgba(248, 238, 207, 1.0)";
    	roundRect(context, this.tileBounds.origin.x, this.tileBounds.origin.y, this.tileBounds.width, this.tileBounds.height, this.tileBounds.height*0.1, true, false);
    	
    	// draw text
    	context.lineWidth=1;
    	context.fillStyle="#000000";
    	context.lineStyle="#000000";
    	context.font="bold 46px sans-serif";
    	context.textAlign = "center";
    	context.textBaseline = "middle";
    	var center = this.tileBounds.center();
    	context.fillText(this.word, center.x, this.tileBounds.origin.y + this.tileBounds.height*0.3);
    	
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