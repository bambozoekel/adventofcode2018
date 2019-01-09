package moerk.day3.puzzle1;

import moerk.day3.Claim;
import moerk.day3.ClaimParser;

import java.awt.Point;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author matthias
 */
public class Overlap {
	public static void main( String[] args ) {
		Set<Point> overlappingPoints = new HashSet<>();

		List<Claim> claims = new ClaimParser().parseClaims();
		for ( int i = 0; i < claims.size()-1;i++) {
			Claim c1 = claims.get(i);

			for ( int j = i + 1; j < claims.size(); j++) {
				Claim c2 = claims.get(j);
				overlappingPoints.addAll(c1.getOverlappingPoints(c2));
			}
		}

		System.out.println(overlappingPoints.size());
	}
}