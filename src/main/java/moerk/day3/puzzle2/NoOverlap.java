package moerk.day3.puzzle2;

import moerk.day3.Claim;
import moerk.day3.ClaimParser;

import java.util.List;

/**
 * @author matthias
 */
public class NoOverlap {
	public static void main( String[] args ) {
		List<Claim> claims = new ClaimParser().parseClaims();

		for (Claim c1 : claims) {
			boolean overlaps = false;
			for (Claim c2: claims) {
				if (c1 != c2 && c1.overlaps(c2)) {
					overlaps = true;
					break;
				}
			}

			if (!overlaps) {
				System.out.println(c1.getId());
				break;
			}
		}
	}
}