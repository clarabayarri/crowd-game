/**
    A letter cube
*/
function WordCube()
{
    /** An array of strings representing the letters
    	@type Array
    */
    this.letter = null;
    
    this.startupWordCube = function(/**String*/ letter, /**Number*/ x, /**Number*/ y, /**Number*/ width, /**Number*/ height) {
    	this.startupGameObject(x, y, width, height);
    	this.letter = letter;
    }
    
    /**
        The render loop
    */
    this.draw = function (/**CanvasRenderingContext2D*/ context)
    {
    	if (this.letter != " ") {
    		// draw tile
    		context.fillStyle = "rgba(248, 238, 207, 1.0)";
    		context.fillRect(this.x, this.y, this.width, this.height);
    		
    		// draw text
    		context.lineWidth=1;
    		context.fillStyle="#000000";
    		context.lineStyle="#000000";
    		context.font="bold 36px sans-serif";
    		context.textAlign = "center";
    		context.textBaseline = "middle";
    		context.fillText(this.letter, this.x + this.width / 2, this.y + this.height / 2);
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