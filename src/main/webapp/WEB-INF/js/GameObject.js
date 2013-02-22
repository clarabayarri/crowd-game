/**
    The base class for all elements that appear in the game.
    @author <a href="mailto:matthewcasperson@gmail.com">Matthew Casperson</a>
    @class
*/
function GameObject()
{
    /**
        The bounds for this object
        @type Bounds
    */
    this.bounds = null;
    
    /**
        Initialises the object, and adds it to the list of objects held by the GameObjectManager.
        @param x        The position on the X axis
        @param y        The position on the Y axis
        @param z        The z order of the element (elements in the background have a lower z value)
    */
    this.startupGameObject = function(/**Bounds*/ bounds)
    {
        this.bounds = bounds;
        return this;
    }
    
    this.moveTo = function(/**Point*/ point) {
    	this.bounds.origin = point;
    }
}