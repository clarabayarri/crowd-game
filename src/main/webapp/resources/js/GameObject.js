/**
    
*/
function GameObject()
{
    /**
        The bounds for this object
        @type Bounds
    */
    this.bounds = null;
    
    this.visible = true;
    
    this.animated = false;
    this.origin = null;
    this.target = null;
    this.stepX = 2;
    this.stepY = 5;
    this.threshold = 3;
    this.delay = 0;
    this.animationCallback = null;
    
    this.startupGameObject = function(/**Bounds*/ bounds)
    {
        this.bounds = bounds;
        this.origin = new Point().init(this.bounds.origin.x, this.bounds.origin.y);
        return this;
    }
    
    this.moveTo = function(/**Point*/ point) {
    	this.bounds.origin = point;
    }
    
    this.hide = function() {
    	this.visible = false;
    }
    
    this.show = function() {
    	this.visible = true;
    }
    
    this.animateMoveTo = function(/**Point*/ point, /**Number*/ delay, /**Function*/ callback) {
    	if (this.animated) {
    		this.bounds.origin.x = this.target.x;
    		this.bounds.origin.y = this.target.y;
    	}
    	this.animated = true;
    	this.origin = new Point().init(this.bounds.origin.x, this.bounds.origin.y);
    	this.target = new Point().init(point.x, point.y);
    	this.delay = delay;
    	this.animationCallback = callback;
    }
    
    this.animateMoveToPoints = function(/**Array*/ points, /**Number*/ delay, /**Function*/ callback) {
    	if (points.length == 1) {
    		this.animateMoveTo(points[0], delay, callback);
    	} else {
    		var _this = this;
    		this.animateMoveTo(points[0], delay, function() {
    			_this.animateMoveToPoints(points.slice(1), 0, callback);
    		});
    	}
    }
    
    // To be called at each render
    this.updatePosition = function() {
    	if (this.delay > 0) {
    		--this.delay;
    	} else if (this.animated) {
    		this.updatePositionX();
    		this.updatePositionY();
    		if (this.bounds.origin.x == this.target.x && this.bounds.origin.y == this.target.y) {
    			this.animated = false;
    			if (this.animationCallback) this.animationCallback();
    		}
    	}
    }
    
    this.updatePositionX = function() {
    	if (this.target.x != this.bounds.origin.x) {
    		var totalDistX = this.target.x - this.origin.x;
    		var currentDistX = this.bounds.origin.x - this.origin.x;
    		var remainingDistX = this.target.x - this.bounds.origin.x
    		var updatedDistX = currentDistX + this.stepX * (remainingDistX/Math.abs(remainingDistX));
    		
    		if (Math.abs(totalDistX - updatedDistX) <= this.threshold) {
    			this.bounds.origin.x = this.target.x;
    		} else {
    			this.bounds.origin.x = this.origin.x + updatedDistX;
    		}
    	}
    }
    
    this.updatePositionY = function() {
    	if (this.target.y != this.bounds.origin.y) {
    		var totalDistY = this.target.y - this.origin.y;
    		var currentDistY = this.bounds.origin.y - this.origin.y;
    		var remainingDistY = this.target.y - this.bounds.origin.y;
    		var updatedDistY = currentDistY + this.stepY * (remainingDistY/Math.abs(remainingDistY));
    		
    		if (Math.abs(totalDistY - updatedDistY) <= this.threshold) {
    			this.bounds.origin.y = this.target.y;
    		} else {
    			this.bounds.origin.y = this.origin.y + updatedDistY;
    		}
    	}
    }
}