package moerk.day17.puzzle1;

import moerk.day17.Scan;
import moerk.day17.ScanPrinter;
import moerk.day17.ScanReader;
import moerk.day17.Type;

import java.awt.Point;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Optional;

/**
 * @author matthias
 */
public class WaterCounter {
	public static void main( String[] args ) {
		//33998 telling door code (te hoog)
		//33728 telling uit resultaat (te hoog) waarde boven de laagste y-waarde klei meegeteld
		//33724

		Scan scan = new ScanReader().read();

		Deque<Point> stack = new LinkedList<>();
		Point current = new Point( 500, 0 );
		boolean flowing = true;

		while ( flowing ) {
			Optional<Point> next = flow( scan, current.x, current.y );
			if ( next.isPresent() ) {
				stack.push( current );
				current = next.get();
			}
			else {
				if ( scan.get( current.x, current.y + 1 ) != Type.SAND ) {
					if ( stack.isEmpty() ) {
						flowing = false;
					}
					else {
						current = stack.pop();
					}
				}
			}
		}

//		new ScanPrinter().print( scan );
		System.out.println( "Wet tiles: " + scan.getWaterPositionCount() );
		System.out.println( "Water: " + scan.getWaterCount() );
	}

	private static Optional<Point> flow( Scan scan, int x, int y ) {
		int bottomY = drop( scan, x, y );

		if ( bottomY == scan.getMaxY() || scan.get( x, bottomY + 1 ) == Type.FLOW ) {
			scan.set( x, bottomY, Type.FLOW );
		}
		else {
			if ( isInBasin( scan, x, bottomY ) ) {
				int leftX = moveLeft( scan, x, bottomY );
				int rightX = moveRight( scan, x, bottomY );

				for ( int newX = leftX; newX <= rightX; newX++ ) {
					scan.set( newX, bottomY, Type.WATER );
				}
			}
			else {
				int newX = moveLeftUntilGap( scan, x, bottomY );
				if ( newX == x ) {
					newX = moveRightUntilGap( scan, x, bottomY );
				}

				if ( scan.get( newX, bottomY + 1 ) == Type.SAND ) {
					return Optional.of( new Point( newX, bottomY ) );
				}
				else {
					scan.set( newX, bottomY, Type.FLOW );
				}
			}
		}

		return Optional.empty();
	}

	private static int drop( Scan scan, int x, int y ) {
		while ( scan.get( x, y + 1 ) == Type.SAND && y < scan.getMaxY() ) {
			y++;
		}

		return y;
	}

	private static boolean isInBasin( Scan scan, int x, int y ) {
		int leftX = x - 1;
		while ( scan.get( leftX, y ) != Type.CLAY && scan.get( leftX, y ) != Type.WATER && leftX > scan.getMinX() ) {
			leftX--;
		}

		if ( leftX == scan.getMinX() - 1 ) {
			return false;
		}

		int rightX = x + 1;
		while ( scan.get( rightX, y ) != Type.CLAY && scan.get( rightX, y ) != Type.WATER && rightX < scan.getMaxX() ) {
			rightX++;
		}

		if ( rightX == scan.getMaxX() + 1 ) {
			return false;
		}

		for ( int dx = leftX + 1; dx <= rightX - 1; dx++ ) {
			if ( scan.get( dx, y + 1 ) != Type.CLAY && scan.get( dx, y + 1 ) != Type.WATER ) {
				return false;
			}
		}

		return true;
	}

	private static int moveLeft( Scan scan, int x, int y ) {
		int leftX = x;
		while ( scan.get( leftX - 1, y ) != Type.CLAY && scan.get( leftX - 1, y ) != Type.WATER ) {
			leftX--;
		}

		return leftX;
	}

	private static int moveRight( Scan scan, int x, int y ) {
		int rightX = x;
		while ( scan.get( rightX + 1, y ) != Type.CLAY && scan.get( rightX + 1, y ) != Type.WATER ) {
			rightX++;
		}

		return rightX;
	}

	private static int moveLeftUntilGap( Scan scan, int x, int y ) {
		int leftX = x;
		while ( scan.get( leftX - 1, y ) == Type.SAND && (scan.get( leftX, y + 1 ) == Type.CLAY || scan.get( leftX, y + 1 ) == Type.WATER) ) {
			leftX--;
		}

		return leftX;
	}

	private static int moveRightUntilGap( Scan scan, int x, int y ) {
		int rightX = x;
		while ( scan.get( rightX + 1, y ) == Type.SAND && (scan.get( rightX, y + 1 ) == Type.CLAY || scan.get( rightX, y + 1 ) == Type.WATER) ) {
			rightX++;
		}

		return rightX;
	}
}