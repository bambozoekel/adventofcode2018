package moerk.day15;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;

import java.awt.Point;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author matthias
 */
public class Grotto {
	private final Entity[][] map;
	private final BiMap<Point, Warrior> warriors;
	private final Map<Point, Set<Point>> neighbours;

	public Grotto( int width, int height ) {
		map = new Entity[width][height];
		warriors = HashBiMap.create();

		neighbours = new HashMap<>();
		for ( int x = 0; x < width; x++ ) {
			for ( int y = 0; y < height; y++ ) {
				Point p = new Point( x, y );
				neighbours.put( p, getDirectNeighbours( p, width, height ) );
			}
		}
	}

	public void set( int x, int y, Entity entity ) {
		map[x][y] = entity;

		if ( entity instanceof Warrior ) {
			warriors.put( new Point( x, y ), (Warrior)entity );
		}
	}

	public Set<Warrior> getWarriors() {
		return warriors.values();
	}

	public int getX( Warrior warrior ) {
		return warriors.inverse().get( warrior ).x;
	}

	public int getY( Warrior warrior ) {
		return warriors.inverse().get( warrior ).y;
	}

	public Set<Warrior> getAttackableOpponents( Warrior warrior ) {
		return neighbours.get( warriors.inverse().get( warrior ) )
			.stream()
			.map( warriors::get )
			.filter( o -> o != null && o.isEnemy( warrior ) )
			.collect( Collectors.toSet() );
	}

	private Set<Point> getDirectNeighbours( Point p, int width, int height ) {
		Set<Point> n = new HashSet<>();
		for ( int x = Math.max( 0, p.x - 1 ); x <= Math.min( width, p.x + 1 ); x++ ) {
			for ( int y = Math.max( 0, p.y - 1 ); y <= Math.min( height, p.y + 1 ); y++ ) {
				if ( Math.abs( p.x - x ) + Math.abs( p.y - y ) == 1 ) {
					n.add( new Point( x, y ) );
				}
			}
		}

		return n;
	}

	public Set<Point> getNextLocationToClosestOpponent( Warrior warrior ) {
		return getLocationsOfClosestOpponents( warrior )
			.stream()
			.min( Comparator.comparing( Point::getY ).thenComparing( Comparator.comparing( Point::getX ) ) )
			.map( p -> possibleNextSteps( warrior, p ) )
			.orElse( Collections.emptySet() );
	}


	private Set<Point> getLocationsOfClosestOpponents( Warrior warrior ) {
		Set<Point> beenThere = new HashSet<>();
		Queue<Point> current = new LinkedList<>();
		Set<Point> locations = new HashSet<>();

		Point start = warriors.inverse().get( warrior );

		beenThere.add( start );

		current.offer( start );

		//TODO Volgens mij vind je hier niet alle tegenstanders die even dichtbij zijn,
		//maar toevallig diegene die het eerst gevonden wordt en dus niet in volgorde
		//van de reading order
		while ( !current.isEmpty() && locations.isEmpty() ) {
			Point p = current.poll();
			for ( Point n : neighbours.get( p ) ) {
				if ( beenThere.add( n ) && map[n.x][n.y].passable() ) {
					if ( isNeighbourOfEnemy( n, warrior ) ) {
						locations.add( n );
					}
					else {
						current.offer( n );
					}
				}
			}
		}

		return locations;
	}

	private boolean isNeighbourOfEnemy( Point p, Warrior warrior ) {
		return neighbours.get( p )
			.stream()
			.map( warriors::get )
			.anyMatch( w -> w != null && w.isEnemy( warrior ) );
	}

	private Set<Point> possibleNextSteps( Warrior warrior, Point closest ) {
		Set<Point> nextSteps = neighbours.get( warriors.inverse().get( warrior ) )
			.stream()
			.filter( p -> map[p.x][p.y].passable() )
			.collect( Collectors.toSet() );

		if ( nextSteps.contains( closest) ) {
			return Collections.singleton( closest );
		}

		Set<Point> beenThere = new HashSet<>();
		Queue<Point> current = new LinkedList<>();
		Set<Point> locations = new HashSet<>();

		beenThere.add( closest );
		current.offer( closest );

		while ( !current.isEmpty() && locations.isEmpty() ) {
			Point p = current.poll();
			for ( Point n : neighbours.get( p ) ) {
				if ( beenThere.add( n ) && map[n.x][n.y].passable() ) {
					if ( nextSteps.contains( n ) ) {
						locations.add( n );
					}
					else {
						current.offer( n );
					}
				}
			}
		}

		return locations;
	}

	public void move( Warrior warrior, Point location ) {
		Point currentLocation = warriors.inverse().remove( warrior );
		map[currentLocation.x][currentLocation.y] = EmptySpace.INSTANCE;
		map[location.x][location.y] = warrior;
		warriors.put( location, warrior );
		System.out.println( warrior.getClass().getSimpleName() + " (" + warrior.getHitPoints() + ") moved from " + currentLocation.x + "x" + currentLocation.y + " to " + location.x + "x" + location.y );
	}

	public void cleanUp() {
		for ( Iterator<Map.Entry<Point, Warrior>> i = warriors.entrySet().iterator(); i.hasNext(); ) {
			Map.Entry<Point, Warrior> entry = i.next();
			if ( entry.getValue().isDead() ) {
				Point p = entry.getKey();
				map[p.x][p.y] = EmptySpace.INSTANCE;
				i.remove();
				System.out.println( "Removed dead " + entry.getValue().getClass().getSimpleName() + " from " + p.x + "x" + p.y );
			}
		}
	}

	public void print() {
		for ( int y = 0; y < map[0].length; y++ ) {
			for ( int x = 0; x < map.length; x++ ) {
				System.out.print( map[x][y] );
			}
			System.out.println();
		}
	}
}