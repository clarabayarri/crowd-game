/**
    A letter cube
*/
function ContinueButton()
{
    this.text = "continuar";
    
    this.startupContinueButton = function(/**Bounds*/ bounds) {
    	this.startupGameObject(bounds);
    	
    	return this;
    }
    
    /**
        The render loop
    */
    this.draw = function (/**CanvasRenderingContext2D*/ context)
    {
    	if (this.visible && this.letter != " ") {
    		// draw tile
    		context.fillStyle = "rgba(218, 119, 117, 1.0)";
    		context.fillRect(this.bounds.origin.x, this.bounds.origin.y, this.bounds.width, this.bounds.height);
    		
    		// draw text
    		context.lineWidth=1;
    		context.fillStyle="#000000";
    		context.lineStyle="#000000";
    		context.font="bold 36px sans-serif";
    		context.textAlign = "center";
    		context.textBaseline = "middle";
    		var center = this.bounds.center();
    		context.fillText(this.text, center.x, center.y);
    	}
        
    };
}
ContinueButton.prototype = new GameObject;