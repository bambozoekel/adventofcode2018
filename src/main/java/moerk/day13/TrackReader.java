package moerk.day13;

import moerk.Util;

import java.util.List;
import java.util.Optional;

/**
 * @author matthias
 */
public class TrackReader {
	public Track read() {
		Track.Builder builder = new Track.Builder();

		List<String> lines = Util.lines( "day13/tracks.txt" );
		TrackPartFactory factory = new TrackPartFactory();

		for ( int y = 0; y < lines.size(); y++ ) {
			String line = lines.get( y );

			for ( int x = 0; x < line.length(); x++ ) {
				String part = line.substring( x, x + 1 );

				Optional<TrackPart> trackPart = factory.create( part );
				if ( trackPart.isPresent() ) {
					builder.addPart( x, y, trackPart.get() );
				}
				else {
					Optional<Direction> direction = Direction.fromLabel( part );
					if ( direction.isPresent() ) {
						builder.addPart( x, y, factory.asTrackPart( direction.get() ) );
						builder.addCart( new Cart( x, y, direction.get() ) );
					}
				}
			}
		}

		return builder.build();
	}


}