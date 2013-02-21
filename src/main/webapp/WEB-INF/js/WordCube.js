/**
    A letter cube
*/
function WordCube()
{
    /** An array of strings representing the letters
    	@type Array
    */
    this.letter = null;
    
    this.startupWordCube = function(/**String*/ letter, /**Bounds*/ bounds) {
    	this.startupGameObject(bounds);
    	this.letter = letter;
    	return this;
    }
    
    /**
        The render loop
    */
    this.draw = function (/**CanvasRenderingContext2D*/ context)
    {
    	if (this.letter != " ") {
    		// draw tile
    		context.fillStyle = "rgba(248, 238, 207, 1.0)";
    		context.fillRect(this.bounds.origin.x, this.bounds.origin.y, this.bounds.width, this.bounds.height);
    		
    		// draw text
    		context.lineWidth=1;
    		context.fillStyle="#000000";
    		context.lineStyle="#000000";
    		context.font="bold 36px sans-serif";
    		context.textAlign = "center";
    		context.textBaseline = "middle";
    		var center = this.bounds.center();
    		context.fillText(this.letter, center.x, center.y);
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