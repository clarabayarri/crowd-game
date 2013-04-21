/**
    A manager for the letters in the word
*/
function Dialog()
{
    /** An array of strings representing the displayed letters
    	@type Array
    */
    
    this.button = null;
    
    this.tileBounds = null;
    
    this.startupDialog = function(/**Bounds*/ bounds) {
    	this.startupGameObject(bounds);
    	
    	var tileOrigin = new Point().init(this.bounds.width * 0.1, this.bounds.height * 0.2);
    	this.tileBounds = new Bounds().init(tileOrigin, this.bounds.width * 0.8, this.bounds.height * 0.6);
    	
    	this.loadInternalViews();
    	    	
    	return this;
    }
    
    this.loadInternalViews = function() {}
    
    this.onClick = function(/**Point*/ clickPoint) {
    	if (this.button.bounds.containsPoint(clickPoint)) {
    		return true;
    	}
    	return false;
    }
    
    this.draw = function (/**CanvasRenderingContext2D*/ context) {
        //darken background
        drawBackgroundShadow(context, this.bounds.origin.x, this.bounds.origin.y, this.bounds.width, this.bounds.height)
        
        // draw tile
        drawTile(context, this.tileBounds.origin.x, this.tileBounds.origin.y, this.tileBounds.width, this.tileBounds.height);
                
        if (this.button) {
            this.button.draw(context);
        }

        this.drawInternal(context);
    }

    this.drawInternal = function(/**CanvasRenderingContext2D*/ context) {}
}

Dialog.prototype = new GameObject;