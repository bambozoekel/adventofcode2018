package moerk.day14.puzzle2;

/**
 * @author matthias
 */
public class LearningCurve {
	public static void main( String[] args ) {
		int[] recipes = new int[100000000];
		recipes[0] = 3;
		recipes[1] = 7;
		int size = 2;

		int[] recipeIndices = new int[2];
		recipeIndices[0] = 0;
		recipeIndices[1] = 1;

		while ( size < 7 || (isNotDone( recipes, size ) && isNotDone( recipes, size - 1 )) ) {
			int newRecipeBase = 0;

			for ( int i = 0; i < recipeIndices.length; i++ ) {
				newRecipeBase += recipes[recipeIndices[i]];
			}

			if ( newRecipeBase < 10 ) {
				recipes[size] = newRecipeBase;
				size++;
			}
			else {
				String s = Integer.toString( newRecipeBase );
				for ( int i = 0; i < s.length(); i++ ) {
					recipes[size] = s.charAt( i ) - 48;
					size++;
				}
			}

			for ( int i = 0; i < recipeIndices.length; i++ ) {
				recipeIndices[i] = (recipeIndices[i] + 1 + recipes[recipeIndices[i]]) % size;
			}
		}
System.out.println();
		System.out.println( size - 6 );
		//1048541
	}

	private static boolean isNotDone( int[] recipes, int s ) {
		return recipes[s - 6] != 3 ||
			recipes[s-5] != 0 ||
			recipes[s-4] != 6 ||
			recipes[s-3] != 2 ||
			recipes[s-2] != 8 ||
			recipes[s-1] != 1;
	}
}