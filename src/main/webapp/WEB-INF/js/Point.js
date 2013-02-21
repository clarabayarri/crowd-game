/**
    A point
*/
function Point()
{
    /**
        The position on the X axis
        @type Number
    */
    this.x = 0;
    /**
        The position on the Y axis
        @type Number
    */
    this.y = 0;
    
    this.init = function(/**Number*/ x, /**Number*/ y) {
    	this.x = x;
    	this.y = y;
    	return this;
    }
    
    this.sumPoint = function(/**Point*/ point) {
    	return new Point().init(this.x + point.x, this.y + point.y);
    }
    
    this.sumValue = function(/**Number*/ x, /**Number*/ y) {
    	return new Point().init(this.x + x, this.y + y);
    }
}