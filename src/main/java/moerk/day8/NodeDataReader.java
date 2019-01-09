package moerk.day8;

import moerk.Util;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author matthias
 */
public class NodeDataReader {
	public List<Integer> read() {
		String[] parts = Util.lines("day8/tree.txt")
			.get(0)
			.split( " " );

		return Arrays.stream( parts )
			.map(Integer::valueOf)
			.collect( Collectors.toList());
	}
}