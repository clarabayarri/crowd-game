/**
    A point and dimensions that represent the bounds of an area
*/
function Bounds()
{
    /**
        The position on the X axis
        @type Number
    */
    this.origin = null;
    /**
        The width in pixels
        @type Number
    */
    this.width = 0;
    /**
        The height in pixels
        @type Number
    */
    this.height = 0;
    
    this.init = function(/**Point*/ origin, /**Number*/ width, /**Number*/ height) {
    	this.origin = origin;
    	this.width = width;
    	this.height = height;
    	return this;
    }
    
    this.containsPoint = function (/**Number*/ x, /**Number*/ y) {
    	if (x < this.origin.x) return false;
    	if (y < this.origin.y) return false;
    	if (x > this.origin.x + this.width) return false;
    	if (y > this.origin.y + this.height) return false;
    	return true;
    };
    
    this.center = function() {
    	return new Point().init(this.origin.x + this.width/2, this.origin.y + this.height/2);
    }
}





