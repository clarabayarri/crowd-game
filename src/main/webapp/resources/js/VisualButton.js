/**
    A continue button
*/
function VisualButton()
{
    this.text = null;
    this.color = COLOR_WHITE;
    
    this.startupVisualButton = function(/**Bounds*/ bounds, /**String*/ text) {
    	this.startupGameObject(bounds);
    	this.text = text;
    	return this;
    }
    
    /**
        The render loop
    */
    this.draw = function (/**CanvasRenderingContext2D*/ context)
    {
    	if (this.visible) {
    		// draw tile
    		drawRibbon(context, this.bounds.origin.x, this.bounds.origin.y, this.bounds.width, this.bounds.height, this.color);
    		
            if (this.text) {
                // draw text
                var center = this.bounds.center();
                drawText(context, this.text, center.x, center.y, FONT_RIBBON_24, this.color);
            }
    		
    	}
        
    };
}
VisualButton.prototype = new GameObject;