/**
    A letter cube
*/
function WordCube()
{
    this.id = 0;
    
    this.letter = null;
    
    this.visible = true;
    
    this.startupWordCube = function(/**Number*/ id, /**String*/ letter, /**Bounds*/ bounds) {
    	this.startupGameObject(bounds);
    	this.id = id;
    	this.letter = letter;
    	return this;
    }
    
    this.copy = function() {
    	var newOrigin = new Point().init(this.bounds.origin.x, this.bounds.origin.y);
    	var newBounds = new Bounds().init(newOrigin, this.bounds.width, this.bounds.height);
    	return new WordCube().startupWordCube(this.id, this.letter, newBounds);
    }
    
    /**
        The render loop
    */
    this.draw = function (/**CanvasRenderingContext2D*/ context)
    {
    	this.updatePosition();
    	if (this.visible && this.letter != " ") {
    		// draw tile
    		drawTile(context, this.bounds.origin.x, this.bounds.origin.y, this.bounds.width, this.bounds.height);
    		
    		// draw text
    		var center = this.bounds.center();
    		drawText(context, this.letter, center.x, center.y, FONT_BOLD_46, COLOR_DARK_GREY);
    	}
        
    };
    
    this.containsPoint = function (/**Number*/ x, /**Number*/ y) {
    	if (x < this.x) return false;
    	if (y < this.y) return false;
    	if (x > this.x + this.width) return false;
    	if (y > this.y + this.height) return false;
    	return true;
    };
}
WordCube.prototype = new GameObject;
