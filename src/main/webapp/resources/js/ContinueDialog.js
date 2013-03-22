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
    	drawBackgroundShadow(context, this.bounds.origin.x, this.bounds.origin.y, this.bounds.width, this.bounds.height)
    	
    	// draw tile
    	drawTile(context, this.tileBounds.origin.x, this.tileBounds.origin.y, this.tileBounds.width, this.tileBounds.height);
    	    	
    	// draw text
    	var center = this.tileBounds.center();
    	drawText(context, this.word, center.x, this.tileBounds.origin.y + this.tileBounds.height*0.3, FONT_BOLD_46, COLOR_DARK_GREY);
    	
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