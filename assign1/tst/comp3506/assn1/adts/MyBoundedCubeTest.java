package comp3506.assn1.adts;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

/**
 * 
 * @author anthony Carrick
 *
 */
public class MyBoundedCubeTest {

	@Test(expected = IllegalArgumentException.class)
	public void emptyCube() {
		Cube<Object> testCube = new BoundedCube<Object>(0, 0, 0);
		testCube.clear();
	}
	
	@Test()
	public <T> void removeAll() {
		Cube<Object> testCube = new BoundedCube<>(5, 5, 5);
		testCube.add(1, 1, 1, new TraversableQueue<T>());
		testCube.add(2, 2, 2, new Object());
		
		testCube.removeAll(1, 1, 1);
		
		assertThat("Empty queue is returned", testCube.getAll(1, 1, 1).size(), is( 0 ));
	}
	
	@Test()
	public void testRemove() {
		Integer element0 = new Integer(0);
		Integer element1 = new Integer(1);
		
		Cube<Object> testCube = new BoundedCube<>(5, 5, 5);
		testCube.add(1, 1, 1, element0);
		testCube.add(1, 1, 1, element1);
		
		testCube.remove(1, 1, 1, element1);
		
		assertThat("Size is too big for the remove method having worked", testCube.getAll(1, 1, 1).size(), is(1));
	}
}
