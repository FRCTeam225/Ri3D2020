package org.lib.framework;

import java.util.TreeMap;

/**
 * InterpolatingTree records up to `max` timestamped observations
 * Given a timestamp that is newer than the oldest item in the tree,
 * it will perform linear interpolation between the two closest elements
 * and return an estimation of the value at that time
 * 
 * Based on 254's InterpolatingTreeMap
 * https://github.com/Team254/FRC-2016-Public/blob/master/src/com/team254/lib/util/InterpolatingTreeMap.java
 * This is a simplified version that only operates on the Double type.
 */

public class InterpolatingTree extends TreeMap<Double, Double> {
	private static final long serialVersionUID = 3012421018624074920L;
	int max;
	public InterpolatingTree(int max) {
		this.max = max;
	}
	
	public Double put(Double key, Double value) {
		if ( size() >= max )
			remove(firstKey());
		super.put(key, value);
		return value;
	}
	
	public Double getInterpolated(Double x) {
		Double y = get(x);
		if ( y == null ) {
			Double topX = ceilingKey(x);
			Double bottomX = floorKey(x);
			
			if ( topX == null && bottomX == null )
				return null;
			else if ( topX == null )
				return get(bottomX);
			else if ( bottomX == null )
				return get(topX);
			
			Double topY = get(topX);
			Double bottomY = get(bottomX);
			
			double dydx = (topY-bottomY)/(topX-bottomX);
			
			return (dydx*(x-bottomX))+bottomY;
		}
		return y;
	}
}
