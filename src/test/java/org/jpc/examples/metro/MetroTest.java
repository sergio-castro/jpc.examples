package org.jpc.examples.metro;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;
import static org.junit.Assert.fail;

import java.util.List;
import java.util.NoSuchElementException;

import org.jpc.examples.metro.model.Line;
import org.jpc.examples.metro.model.Metro;
import org.junit.Test;

public class MetroTest extends MetroExampleTest {


	@Test
	public void testAllLines() {
		Metro metro = metro();
		List<Line> lines = metro.lines();
		assertNotNull(lines);
		assertEquals(lines.size(), 6);
		
//		System.out.println("Number of lines: " + lines.size());
//		System.out.print("Lines: ");
//		for(ILine line : lines) {
//			System.out.print(line + " ");
//		}
//		System.out.println();
		
	}
	
	@Test
	public void testIsLine() {
		Metro metro = metro();
		Line line = metro.line("central");
		assertNotNull(line);
		//System.out.println("Line: " + line);
		
		try {
			line = metro.line("line1");
			fail();
		} catch(NoSuchElementException e){}
	}
	
}