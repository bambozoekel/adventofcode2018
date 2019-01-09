package moerk.day2.puzzle2;

import moerk.Util;

import java.util.List;

/**
 * @author matthias
 */
public class Diff {
	public static void main( String[] args ) {
		List<String> ids = Util.lines("day2/puzzle1/ids.txt");

		for ( int i = 0; i < ids.size() - 1; i++ ) {
			String id1 = ids.get(i);
			for ( int j = i + 1; j < ids.size(); j++ ) {
				String id2 = ids.get(j);

				int pos = -1;
				for (int k = 0; k < id1.length(); k++) {
					if (id1.charAt(k) != id2.charAt(k)) {
						if (pos == -1) {
							pos = k;
						}
						else {
							pos = -1;
							break;
						}
					}
				}

				if (pos > -1) {
					System.out.println(id1);
					System.out.println(id2);
					System.out.println(pos);
					System.out.println(id1.substring(0,pos)+id1.substring(pos+1));
					break;
				}
			}
		}
	}
}