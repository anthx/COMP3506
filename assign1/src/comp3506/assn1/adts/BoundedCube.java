package comp3506.assn1.adts;

/**
 * A three-dimensional data structure that holds items in a positional relationship to each other.
 * Each cell in the data structure can hold multiple items.
 * A bounded cube has a specified maximum size in each dimension.
 * The root of each dimension is indexed from zero.
 * 
 * @author Anthony Carrick
 *
 * @param <T> The type of element held in the data structure.
 */
public class BoundedCube<T> implements Cube<T> {

    // Array of Array of Array of Array of T
	
	private TraversableQueue<T>[][][] cube;
	private int cubeLength;
	private int cubeBreadth;
	private int cubeHeight;
	/**
	 * 
	 * @param length  Maximum size in the 'x' dimension.
	 * @param breadth Maximum size in the 'y' dimension.
	 * @param height  Maximum size in the 'z' dimension.
	 * @throws IllegalArgumentException If provided dimension sizes are not positive.
	 */
	@SuppressWarnings("unchecked")
	public BoundedCube(int length, int breadth, int height) throws IllegalArgumentException {
		cubeLength = length;
		cubeBreadth = breadth;
		cubeHeight = height;
		
		if (length < 1 || breadth < 1 || height < 1) {
			throw new IllegalArgumentException("Size must be positive");
		}
		
		cube = (TraversableQueue<T>[][][]) new TraversableQueue[cubeLength][cubeBreadth][cubeHeight];
	}

	/**
	 * Adds a new element to a queue at a spot given.
	 * O(1) because array indexed
	 */
	@Override
	public void add(int x, int y, int z, T element) throws IndexOutOfBoundsException {
		if (x > cubeLength || y > cubeBreadth || z > cubeHeight) {
			throw new IndexOutOfBoundsException("Can't add to outside of cube bounds");
		}
		if (cube[x][y][z] == null) {
			cube[x][y][z] = new TraversableQueue<T>();
		}
		cube[x][y][z].enqueue(element);
		
	}

	/**
	 * Gets the element at a spot.
	 */
	@Override
	public T get(int x, int y, int z) throws IndexOutOfBoundsException {
		if (x > cubeLength || y > cubeBreadth || z > cubeHeight) {
			throw new IndexOutOfBoundsException("Can't get to outside of cube bounds");
		}
		if (cube[x][y][z] == null) {
			return null;
		}
		T result = cube[x][y][z].dequeue();
		return result;
	}

	@Override
	public IterableQueue<T> getAll(int x, int y, int z) throws IndexOutOfBoundsException {
		IterableQueue<T> result = cube[x][y][z];
		return result;
	}

	/**
	 * O(1)
	 */
	@Override
	public boolean isMultipleElementsAt(int x, int y, int z) throws IndexOutOfBoundsException {
		boolean result = cube[x][y][z].size() > 1;
		return result;
	}

	
	@Override
	public boolean remove(int x, int y, int z, T element) throws IndexOutOfBoundsException {
		if (x > cubeLength || y > cubeBreadth || z > cubeHeight) {
			throw new IndexOutOfBoundsException("Can't get to outside of cube bounds");
		}
		
		boolean result = cube[x][y][z].remove(element);
		
		return result;
	}

	/**
	 * Let's remove the queue and recreate it at the same spot instead of clearing the elements.
	 * Running time: O(c)
	 * Memory: O(1)
	 */
	@Override
	public void removeAll(int x, int y, int z) throws IndexOutOfBoundsException {
		
		if (x > cubeLength || y > cubeBreadth || z > cubeHeight) {
			throw new IndexOutOfBoundsException("Can't get to outside of cube bounds");
		}
		cube[x][y][z] = null;
		cube[x][y][z] = new TraversableQueue<T>();
	}

	/**
	 * Rebuilds the cube fresh
	 * 
	 * Actually, we have iterate it and null everything, else you get null pointers.
	 * Time: O(n) where n is the number of things in the cube i.e. length * width * height
	 */
	@Override
	public void clear() {
		
		for (int i=0; i < cubeLength; i++) {
			for (int j = 0; j < cubeBreadth; j++) {
				for (int k = 0; k < cubeHeight; k++) {
					cube[i][j][k] = null;
				}
			}
		}
	}
	
	//A linked list or even a relational database would be better but this works.
	
}
