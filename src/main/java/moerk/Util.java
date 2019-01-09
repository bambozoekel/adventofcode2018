package moerk;

import com.google.common.base.Charsets;
import com.google.common.io.Resources;

import java.io.IOException;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author matthias
 */
public class Util {
	public static List<String> lines( String resource ) {
		try {
			return Resources.asCharSource( Resources.getResource( resource ), Charsets.UTF_8 ).readLines();
		}
		catch ( IOException e ) {
			throw new RuntimeException( e );
		}
	}

	public static <T> List<T> read( String resource, Function<String, T> mapper ) {
		return lines( resource )
			.stream()
			.map( mapper )
			.collect( Collectors.toList());
	}
}