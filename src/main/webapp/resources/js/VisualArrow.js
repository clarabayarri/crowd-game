/**
    A continue button
*/
function VisualArrow()
{
    this.text = null;
    
    this.startupVisualArrow = function(/**Bounds*/ bounds, /**String*/ text) {
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
    		drawArrow(context, this.bounds.origin.x, this.bounds.origin.y, this.bounds.width, this.bounds.height);
    		
            if (this.text) {
                // draw text
                var center = this.bounds.center();
                drawText(context, this.text, center.x + 10, center.y, FONT_RIBBON_20, COLOR_DARK_GREY);
            }
    		
    	}
        
    };
}
VisualArrow.prototype = new GameObject;