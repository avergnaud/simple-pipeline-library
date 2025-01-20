import static org.junit.Assert.assertEquals;

class StudentTest extends GroovyTestCase {

    void testAdd() {
        def expected = 5
        assertEquals(Helper.add(2, 3), expected)
    }

}
