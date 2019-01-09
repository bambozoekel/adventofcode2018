package moerk.day14.puzzle1;

/**
 * @author matthias
 */
public class TenRecipes {
	public static void main( String[] args ) {
		int learningCurve = 306281;

		int[] recipes = new int[learningCurve + 20];
		recipes[0] = 3;
		recipes[1] = 7;
		int size = 2;

		int[] recipeIndices = new int[2];
		recipeIndices[0] = 0;
		recipeIndices[1] = 1;

		while ( size < learningCurve + 10 ) {
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

		for ( int i = learningCurve; i < learningCurve + 10; i++ ) {
			System.out.print( recipes[i] );
		}
	}
}